package serverPart.commandsClasses;


import dto.Command;
import dto.Message;
import dto.Route;
import serverPart.CommandHandler;
import serverPart.Logger;
import serverPart.interfaces.CommandManual;
import serverPart.mainClasses.Invoker;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static serverPart.Manager.routes;

/**
 * Класс команды show
 */
public class ShowCommand implements CommandHandler {
    private static final org.slf4j.Logger logger = Logger.getLogger("ShowCommand");

    public ShowCommand() {
        Invoker.getInstance().registerHandler(this);
    }
    /**
     * Метод execute команды show
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual(Command command) {
        if (routes.size() != 0) {
            List<Message> arrayOfMessages = new ArrayList<>();
            arrayOfMessages.add(new Message(1, routes.size() + 1,
                    "Collection elements:"));
            List<Route> sortedRoute = routes.stream().sorted(Comparator.comparing(Route::getSize)).collect(Collectors.toList());
            sortedRoute.forEach(route -> arrayOfMessages.add(
                    new Message(arrayOfMessages.size(), sortedRoute.size() + 1, route)));
            logger.info("Command completed");
            return arrayOfMessages;
        } else {
            logger.info("Command completed: The collection has no elements");
            return new ArrayList<>(Collections.singleton(new Message(1, 1,
                    "The collection has no elements")));
        }

    }

    @Override
    public boolean support(String commandName) {
        return "show".equals(commandName);
    }

    @Override
    public List<Message> executeScript(Command command, Object... args) throws IOException {
        return executeManual(command);
    }
}
