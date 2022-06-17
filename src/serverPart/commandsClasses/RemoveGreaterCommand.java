package serverPart.commandsClasses;

import dto.Command;
import dto.Message;
import dto.Route;
import serverPart.Logger;
import serverPart.interfaces.CommandManualWithParameters;
import serverPart.interfaces.CommandScript;
import dto.CreatingElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

import static serverPart.Manager.routes;

/**
 * Класс команды remove_greater
 */
public class RemoveGreaterCommand implements CommandManualWithParameters, CommandScript {
    private static final org.slf4j.Logger logger = Logger.getLogger("RemoveGreaterCommand");
    /**
     * Метод execute команды remove_greater
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual(Command command) {
        Set<Route> setToRemove = new HashSet<>();
        routes.stream().filter(route -> (command.getRouteOfCommand().getName()).compareTo(route.getName()) < 0).forEach(setToRemove::add);
        logger.info("Command completed");
        return getMessages(setToRemove);
    }

    /**
     * Метод execute команды remove_greater
     */
    @Override
    public List<Message> executeScript(Command command, Object... args) throws IOException {
        BufferedReader bufferedReader = (BufferedReader) args[0];
        Route addedRoute = CreatingElement.CreatingElementScript(bufferedReader);
        Set<Route> setToRemove = new HashSet<>();
        routes.stream().filter(route -> (addedRoute.getName()).compareTo(route.getName()) < 0).forEach(setToRemove::add);
        return getMessages(setToRemove);
    }

    private ArrayList<Message> getMessages(Set<Route> setToRemove) {
        if (routes.removeAll(setToRemove)) {
            logger.info("Command completed: Removal completed successfully");
            return new ArrayList<>(Collections.singleton(new Message(1, 1,
                    "Removal completed successfully")));
        } else {
            logger.info("Command completed: No element removed");
            return new ArrayList<>(Collections.singleton(new Message(1, 1,
                    "No element removed")));
        }
    }
}
