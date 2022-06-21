package serverPart;

import dto.Command;
import dto.IdGenerator;
import dto.Message;
import serverPart.mainClasses.Invoker;
import serverPart.utils.FileNameTaker;

import java.io.IOException;
import java.net.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Server {
    private static int serverPort = 63228;
    private static final DatagramSocket datagramSocket;

    static {
        try {
            datagramSocket = new DatagramSocket(serverPort);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public static String savingFile;
    public static boolean checkFileUpload;
    private static final byte[] inputDataBuffer = new byte[1024];

    private static final org.slf4j.Logger logger = Logger.getLogger("Server");


    public static void connectionSetup(DatagramPacket inputPacket) throws IOException {
        datagramSocket.receive(inputPacket);
        InetAddress senderAddress = inputPacket.getAddress();
        serverPort = inputPacket.getPort();
        logger.info("New request received");
        byte[] sendingDataBuffer = Message.serialize(new Message(1, 1, "Connection successfully"));
        DatagramPacket sendingPacket = new DatagramPacket(sendingDataBuffer, sendingDataBuffer.length,
                senderAddress, serverPort);
        datagramSocket.connect(senderAddress, serverPort);
        logger.info("Connecting to the client at address: {}, port: {}", senderAddress.getHostAddress(), serverPort);
        datagramSocket.send(sendingPacket);

    }

    //Получение команды, ее обработка и отправка назад
    public static void receiveData() throws IOException, ClassNotFoundException {
        checkFileUpload = false;
        DatagramPacket inputPacket = new DatagramPacket(inputDataBuffer, inputDataBuffer.length);
        DatagramPacket sendingPacket;
        connectionSetup(inputPacket);
        Invoker.getInstance().initializeCommand();
        while (true) {
            specialCommand();
            logger.info("Waiting new receive...");
            datagramSocket.receive(inputPacket);
            InetAddress senderAddress = inputPacket.getAddress();
            serverPort = inputPacket.getPort();
            Command command = (Command) Command.deserialize(inputPacket.getData());
            logger.info("New request received");
            byte[] sendingDataBuffer;
            if (!checkFileUpload) {
                logger.info("Executing a request to get filename from environment variable");
                Message message = FileNameTaker.getFileNameFromEnvironment(command);
                checkFileUpload = FileNameTaker.checkFileName(message);
                savingFile = System.getenv(command.getParameterOfCommand());
                sendingDataBuffer = Message.serialize(message);
                sendingPacket = new DatagramPacket(sendingDataBuffer, sendingDataBuffer.length,
                        senderAddress, serverPort);
                logger.info("Sending a response");
                datagramSocket.send(sendingPacket);
                //Отдельный if для команд с отправкой клиенту 1 сообщения
            } else if (Arrays.stream(new String[]{"history", "help", "execute_script", "show", "filter_by_distance",
                    "print_descending", "exit"}).noneMatch(s -> s.equals(command.getNameOfCommand()))) {
                logger.info("Command to execute: '{}', parameter: '{}'", command.getNameOfCommand(),
                        command.getParameterOfCommand());
                sendingDataBuffer = Message.serialize(Invoker.getInstance().choiceCommandManual(command).get(0));
                sendingPacket = new DatagramPacket(sendingDataBuffer, sendingDataBuffer.length,
                        senderAddress, serverPort);
                logger.info("Sending a response");
                datagramSocket.send(sendingPacket);
            } else if (command.getNameOfCommand().equals("exit")) {
                logger.info("End of work with the client");
                checkFileUpload = false;
                sendingDataBuffer = Message.serialize(Invoker.getInstance().choiceCommandManual(command).get(0));
                sendingPacket = new DatagramPacket(sendingDataBuffer, sendingDataBuffer.length,
                        senderAddress, serverPort);
                IdGenerator.getInstance().zeroingIdSet();
                IdGenerator.getInstance().zeroingId();
                datagramSocket.send(sendingPacket);
                datagramSocket.disconnect();
                break;
            } else {
                logger.info("Command to execute: '{}', parameter: '{}'", command.getNameOfCommand(),
                        command.getParameterOfCommand());
                List<Message> arrayOfMessage = Invoker.getInstance().choiceCommandManual(command);
                int messageCount = arrayOfMessage.get(0).getMessageCount();
                sendingDataBuffer = Message.serialize(arrayOfMessage.get(0));
                sendingPacket = new DatagramPacket(sendingDataBuffer, sendingDataBuffer.length,
                        senderAddress, serverPort);
                logger.info("Sending control packet");
                datagramSocket.send(sendingPacket);
                //Цикл отправки сообщений
                for (int g = 1; g < messageCount; g++) {
                    sendingDataBuffer = Message.serialize(arrayOfMessage.get(g));
                    DatagramPacket sendingNewPacket = new DatagramPacket(sendingDataBuffer, sendingDataBuffer.length,
                            senderAddress, serverPort);
                    logger.info("Sending packet № {}", g+1);
                    datagramSocket.send(sendingNewPacket);
                }
            }
        }
    }

    private static void specialCommand() throws IOException {
        if (System.in.available() > 0) {
            if (new Scanner(System.in).nextLine().equals("sc")) {
                logger.info("Executing a special command");
                System.out.println((new Invoker().choiceCommandManual(new Command("exit"))).
                        get(0).getContentString());
            }
        }
    }
}
