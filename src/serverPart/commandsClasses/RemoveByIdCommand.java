package serverPart.commandsClasses;

import dto.Command;
import dto.Message;

import static serverPart.Manager.routes;

import serverPart.CommandHandler;
import serverPart.Logger;
import serverPart.interfaces.CommandManual;
import serverPart.mainClasses.Invoker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс команды remove_by_id
 */
public class RemoveByIdCommand implements CommandHandler {
    private static final org.slf4j.Logger logger = Logger.getLogger("RemoveByIdCommand");

    public RemoveByIdCommand() {
        Invoker.getInstance().registerHandler(this);
    }
    /**
     * Метод execute команды remove_by_id
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual(Command command) {
        if (routes.removeIf(route -> route.getId() == Integer.parseInt(command.getParameterOfCommand()))) {
            logger.info("Command completed: Element deleted successfully");
            return new ArrayList<>(Collections.singleton(new Message(1, 1,
                    "Element deleted successfully")));
        } else {
            logger.info("Command completed: Element with this id was not found");
            return new ArrayList<>(Collections.singleton(new Message(1, 1,
                    "Element with this id was not found")));
        }
    }

    @Override
    public boolean support(String commandName) {
        return "remove_by_id".equals(commandName);
    }

    @Override
    public List<Message> executeScript(Command command, Object... args) throws IOException {
        return executeManual(command);
    }
}
