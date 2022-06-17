package clientPart;

import dto.Command;
import dto.Message;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.UUID;
import java.util.stream.IntStream;

public class Client {

    private static DatagramChannel datagramChannel;

    private static final String HOST = "helios.cs.ifmo.ru";
    private static final int PORT = 63226;

    private static final SocketAddress serverAddress = new InetSocketAddress(HOST, PORT);
    private static UUID uuidLastRequest;
    //Чекер состояния получения имени файла(все изменения инкапсулировать в этот класс)
    public static boolean fileNameCheck = false;

    //Инициализация канала, цикл выборки поведения в зависимости от команды
    public static void channelInitialize() throws IOException, ClassNotFoundException, InterruptedException {
        datagramChannel = DatagramChannel.open();
        datagramChannel.connect(serverAddress);
        datagramChannel.configureBlocking(false);
        Message[] arrayOfMessages = new Message[1];
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
            arrayOfMessages = sendCommandAndReceiveAnswer(bufferReceive, sendingCommand);
            if (!fileNameCheck) {
                fileNameCheck = FileName.checkFileName(arrayOfMessages);
                Print.printContent(arrayOfMessages);
            } else if (arrayOfMessages[0].getContentString() != null && arrayOfMessages[0].getContentString().equals("Collection saved successfully")) {
                Print.printContent(arrayOfMessages);
                System.out.println("Exiting...");
                System.exit(1);
            } else {
                Print.printContent(arrayOfMessages);
            }
        }
        arrayOfMessages[0] = new Message(1, 1, "No connection");
        Print.printContent(arrayOfMessages);
    }

    //Получение пакетов с данными
    private static Message[] sendCommandAndReceiveAnswer(ByteBuffer bufferReceive, Command sendingClass) throws IOException, InterruptedException, ClassNotFoundException {
        bufferReceive.clear();
        ByteBuffer bufferSend = ByteBuffer.wrap(Command.serialize(sendingClass));
        datagramChannel.send(bufferSend, serverAddress);
        Message firstMessage = waitingReceive(bufferReceive);
        uuidLastRequest = firstMessage.getUuid();
        Message[] arrayOfMessages = new Message[firstMessage.getMessageCount()];
        arrayOfMessages[0] = firstMessage;
        int limit = firstMessage.getMessageCount();
        bufferReceive.clear();
        for (int i = firstMessage.getMessageNum(); i < limit; i++) {
            datagramChannel.receive(bufferReceive);
            arrayOfMessages[i] = (Message) Message.deserialize(bufferReceive.array());
            bufferReceive.clear();
        }
        return arrayOfMessages;
    }

    private static Message waitingReceive(ByteBuffer bufferReceive) throws IOException, ClassNotFoundException, InterruptedException {
        Thread.sleep(100);
        datagramChannel.receive(bufferReceive);
        while ((((Message) Message.deserialize(bufferReceive.array())).getUuid().equals(uuidLastRequest))) {
            System.out.println("---");
            datagramChannel.receive(bufferReceive);
            Thread.sleep(100);
        }
        return (Message) Message.deserialize(bufferReceive.array());
    }

    //Установление соединения с сервером(отправка и принятие сообщения, всего 3 попытки)
    public static boolean connectionToServer(ByteBuffer bufferReceive) throws InterruptedException, IOException, ClassNotFoundException {
        System.out.println("Send request to server");
        datagramChannel.send(ByteBuffer.wrap(Command.serialize(new Command("test"))), serverAddress);
        for (int i = 0; i < 3; i++) {
            System.out.println("Waiting answer from server(Attempt № " + (i + 1) + ")");
            Thread.sleep(200);
            datagramChannel.receive(bufferReceive);
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
