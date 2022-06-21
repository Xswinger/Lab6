package clientPart;

import dto.Command;
import dto.Message;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.PortUnreachableException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.UUID;
import java.util.stream.IntStream;

public class Client {

    private static DatagramChannel datagramChannel;

    private static final String HOST = "localhost";
    private static final int PORT = 63228;

    private static final SocketAddress serverAddress = new InetSocketAddress(HOST, PORT);
    private static UUID uuidLastRequest;
    //Чекер состояния получения имени файла
    public static boolean fileNameCheck = false;

    //Инициализация канала, цикл выборки поведения в зависимости от команды
    public static void channelInitialize() throws IOException, ClassNotFoundException, InterruptedException {
        datagramChannel = DatagramChannel.open();
        datagramChannel.connect(serverAddress);
        datagramChannel.configureBlocking(false);
        Message[] arrayOfMessages;
        ByteBuffer bufferReceive = ByteBuffer.allocate(1000);
        boolean connectionSetup = connectionToServer(bufferReceive);
        bufferReceive.clear();
        while (connectionSetup) {
            Command sendingCommand = EnterCommand.selectionRequest(fileNameCheck);
            if (sendingCommand.getNameOfCommand().equals("error")) {
                System.out.println(sendingCommand);
                continue;
            }
            if (sendingCommand.getNameOfCommand().equals("Unknown command") ||
                    sendingCommand.getNameOfCommand().equals("Invalid input variable format for this parameter")) {
                System.out.println(sendingCommand.getNameOfCommand());
                continue;
            }
            try {
                arrayOfMessages = sendCommandAndReceiveAnswer(bufferReceive, sendingCommand);
            }
            catch (PortUnreachableException e){
                fileNameCheck = false;
                break;
            }
            if (!fileNameCheck) {
                fileNameCheck = FileName.checkFileName(arrayOfMessages);
                Print.printContent(arrayOfMessages);
            } else if (arrayOfMessages[0].getContentString() != null &&
                    arrayOfMessages[0].getContentString().equals("Collection saved successfully")) {
                Print.printContent(arrayOfMessages);
                System.out.println("Exiting...");
                datagramChannel.disconnect();
                System.exit(1);
            } else {
                Print.printContent(arrayOfMessages);
            }
        }
        Print.printContent(new Message[]{new Message(1,1,"Connection lost")});
    }

    //Получение пакетов с данными
    private static Message[] sendCommandAndReceiveAnswer(ByteBuffer bufferReceive, Command sendingClass) throws IOException, InterruptedException, ClassNotFoundException {
        bufferReceive.clear();
        ByteBuffer bufferSend = ByteBuffer.wrap(Command.serialize(sendingClass));
        datagramChannel.write(bufferSend);
        Message firstMessage = waitingReceive(bufferReceive);
        uuidLastRequest = firstMessage.getUuid();
        Message[] arrayOfMessages = new Message[firstMessage.getMessageCount()];
        arrayOfMessages[0] = firstMessage;
        int limit = firstMessage.getMessageCount();
        bufferReceive.clear();
        for (int i = firstMessage.getMessageNum(); i < limit; i++) {
            datagramChannel.read(bufferReceive);
            arrayOfMessages[i] = (Message) Message.deserialize(bufferReceive.array());
            bufferReceive.clear();
        }
        return arrayOfMessages;
    }

    private static Message waitingReceive(ByteBuffer bufferReceive) throws IOException, ClassNotFoundException, InterruptedException {
        Thread.sleep(50);
        datagramChannel.read(bufferReceive);
        while ((((Message) Message.deserialize(bufferReceive.array())).getUuid().equals(uuidLastRequest))) {
            datagramChannel.read(bufferReceive);
            Thread.sleep(50);
        }
        return (Message) Message.deserialize(bufferReceive.array());
    }

    //Установление соединения с сервером(отправка и принятие сообщения, всего 3 попытки)
    public static boolean connectionToServer(ByteBuffer bufferReceive) throws InterruptedException, IOException, ClassNotFoundException {
        System.out.println("Send request to server");
        datagramChannel.write(ByteBuffer.wrap(Command.serialize(new Command("test"))));
        for (int i = 0; i < 3; i++) {
            System.out.println("Waiting answer from server(Attempt № " + (i + 1) + ")");
            Thread.sleep(250);
            try {
                datagramChannel.read(bufferReceive);
            }
            catch (PortUnreachableException ex){
                System.out.println("Fail to connect\n");
                continue;
            }
            if (IntStream.range(0, bufferReceive.array().length).parallel().allMatch(j ->
                    bufferReceive.array()[j] == 0)) {
                System.out.println("Fail to connect\n");
            } else {
                System.out.println("Connect successfully");
                Message message = (Message) Message.deserialize(bufferReceive.array());
                uuidLastRequest = message.getUuid();
                return true;
            }
        }
        return false;
    }
}
