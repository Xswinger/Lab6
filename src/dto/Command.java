package dto;

import java.io.*;
import java.util.Arrays;

/**
 * Объект для передачи команд и их аргументов
 */
public class Command implements Serializable {

    private String nameOfCommand;
    private String parameterOfCommand;

    private Route routeOfCommand;

    public Command(String nameOfCommand, String parameterOfCommand, Route routeOfCommand) {
        this.nameOfCommand = nameOfCommand;
        this.parameterOfCommand = parameterOfCommand;
        this.routeOfCommand = routeOfCommand;
    }

    public Command(String nameOfCommand, String parameterOfCommand) {
        this.nameOfCommand = nameOfCommand;
        this.parameterOfCommand = parameterOfCommand;
    }

    public Command(String nameOfCommand) {
        this.nameOfCommand = nameOfCommand;
        this.parameterOfCommand = null;
    }

    public Command(String nameOfCommand, Route parameterOfCommand) {
        this.nameOfCommand = nameOfCommand;
        this.routeOfCommand = parameterOfCommand;
    }

    public Route getRouteOfCommand() {
        return routeOfCommand;
    }

    public String getNameOfCommand() {
        return nameOfCommand;
    }

    public String getParameterOfCommand() {
        return parameterOfCommand;
    }

    public void setRouteOfCommand(Route routeOfCommand) {
        this.routeOfCommand = routeOfCommand;
    }

    public void setNameOfCommand(String nameOfCommand) {
        this.nameOfCommand = nameOfCommand;
    }

    public void setParameterOfCommand(String parameterOfCommand) {
        this.parameterOfCommand = parameterOfCommand;
    }

    public static byte[] serialize(Command command) throws IOException {
        try (ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
             ObjectOutputStream objStream = new ObjectOutputStream(byteStream)) {
            objStream.writeObject(command);
            return byteStream.toByteArray();
        }
    }

    public static Object deserialize(byte[] bytes) throws IOException, ClassNotFoundException {
        try (ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
             ObjectInputStream objStream = new ObjectInputStream(byteStream);) {
            return objStream.readObject();
        }
    }

    public static boolean checkCommand(String commandName) {
        return Arrays.stream(ClientCommands.values()).anyMatch(commands -> true);
    }
}
