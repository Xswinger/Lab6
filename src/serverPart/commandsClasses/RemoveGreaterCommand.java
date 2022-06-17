package serverPart.commandsClasses;

import dto.Command;
import dto.Message;
import dto.Route;
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
    /**
     * Метод execute команды remove_greater
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual(Command command) {
        Set<Route> setToRemove = new HashSet<>();
        routes.stream().filter(route -> (command.getRouteOfCommand().getName()).compareTo(route.getName()) < 0).forEach(setToRemove::add);
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
            return new ArrayList<>(Collections.singleton(new Message(1, 1,
                    "Removal completed successfully")));
        } else {
            return new ArrayList<>(Collections.singleton(new Message(1, 1,
                    "No element removed")));
        }
    }
}
