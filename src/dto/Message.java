package dto;

import sun.nio.cs.Surrogate;

import java.io.*;
import java.util.UUID;

//Класс для сообщений от сервера
public class Message implements Serializable {

    private UUID uuid;
    private int messageNum;

    private int messageCount;

    private String contentString;

    private Route contentRoute;

    public Message(int messageNum, int messageCount, String contentString) {
        this.uuid = UUID.randomUUID();
        this.messageNum = messageNum;
        this.messageCount = messageCount;
        this.contentString = contentString;
    }

    public Message(int messageNum, int messageCount, Route contentRoute) {
        this.uuid = UUID.randomUUID();
        this.messageNum = messageNum;
        this.messageCount = messageCount;
        this.contentRoute = contentRoute;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Route getContentRoute() {
        return contentRoute;
    }

    public String getContentString() {
        return contentString;
    }

    public int getMessageCount() {
        return messageCount;
    }

    public int getMessageNum() {
        return messageNum;
    }

    public void setContentString(String contentString) {
        this.contentString = contentString;
    }

    public void setMessageCount(int messageCount) {
        this.messageCount = messageCount;
    }

    public void setMessageNum(int messageNum) {
        this.messageNum = messageNum;
    }

    public void setContentRoute(Route contentRoute) {
        this.contentRoute = contentRoute;
    }

    public static byte[] serialize(Message message) throws IOException {
        try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
             ObjectOutputStream objStream = new ObjectOutputStream(byteStream)) {
            objStream.writeObject(message);
            return byteStream.toByteArray();
        }
    }

    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
             ObjectInputStream objStream = new ObjectInputStream(byteStream)) {
            return objStream.readObject();
        }
    }
}
