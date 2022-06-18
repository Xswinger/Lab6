package serverPart.commandsClasses;

import dto.Command;
import dto.Message;
import serverPart.CommandHandler;
import serverPart.Logger;
import serverPart.interfaces.CommandManual;
import serverPart.mainClasses.Invoker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static serverPart.Manager.routes;
import static serverPart.Manager.dateOfInitialization;

/**
 * Класс команды info
 */
public class InfoCommand implements CommandHandler {
    private static final org.slf4j.Logger logger = Logger.getLogger("InfoCommand");

    public InfoCommand() {
        Invoker.getInstance().registerHandler(this);
    }
    /**
     * Метод execute команды info
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual(Command command) {
        return new ArrayList<>(Collections.singleton(new Message(1, 1,
                "Collection information:"
                        + "\nCollection type: " + routes.getClass().getName()
                        + "\nInitialization date: " + dateOfInitialization
                        + "\nAmount of elements: " + routes.size())));
    }

    @Override
    public boolean support(String commandName) {
        return "info".equals(commandName);
    }

    @Override
    public List<Message> executeScript(Command command, Object... args) throws IOException {
        return executeManual(command);
    }
}
