package serverPart.commandsClasses;

import dto.Command;
import dto.IdGenerator;
import dto.Message;
import serverPart.CommandHandler;
import serverPart.Logger;
import serverPart.interfaces.CommandManual;
import serverPart.mainClasses.Invoker;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static serverPart.Manager.routes;

/**
 * Класс команды clear
 */
public class ClearCommand implements CommandHandler {

    private static final org.slf4j.Logger logger = Logger.getLogger("ClearCommand");

    public ClearCommand() {
        Invoker.getInstance().registerHandler(this);
    }
    /**
     * Метод execute команды clear
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual(Command command) {
        routes.clear();
        IdGenerator.getInstance().zeroingId();
        logger.info("Collection cleared");
        return new ArrayList<>(Collections.singleton(new Message(1, 1, "Collection cleared")));
    }
    @Override
    public List<Message> executeScript(Command command, Object... args) {
        return executeManual(command);
    }

    @Override
    public boolean support(String commandName) {
        return "clear".equals(commandName);
    }
}
