package serverPart.commandsClasses;

import dto.*;
import serverPart.CommandHandler;
import serverPart.Logger;
import serverPart.interfaces.CommandManual;
import serverPart.interfaces.CommandScript;
import serverPart.mainClasses.Invoker;
import serverPart.utils.AssignmentOfAutomaticallyGeneratedFields;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static serverPart.Manager.routes;

/**
 * Класс команды add_if_max
 */
public class AddIfMaxCommand implements CommandHandler {

    private static final org.slf4j.Logger logger = Logger.getLogger("AddIfMaxCommand");

    public AddIfMaxCommand() {
        Invoker.getInstance().registerHandler(this);
    }
    /**
     * Метод execute команды add_if_max
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual(Command command) {
        if ((int) routes.stream().filter((route -> (command.getRouteOfCommand().getName())
                .compareTo(route.getName()) > 0)).count() == routes.size()) {
            routes.add(AssignmentOfAutomaticallyGeneratedFields.generate(command.getRouteOfCommand()));
            logger.info("Element added successfully");
            return new ArrayList<>(Collections.singletonList(new Message(1, 1,
                    "Element added successfully")));
        } else {
            logger.warn("The element was not added to the collection");
            return new ArrayList<>(Collections.singletonList(new Message(1, 1,
                    "The element was not added to the collection")));
        }
    }

    @Override
    public List<Message> executeScript(Command command, Object... args) throws IOException {
        BufferedReader bufferedReader = (BufferedReader) args[0];
        Route addedRoute = CreatingElement.CreatingElementScript(bufferedReader);
        if ((int) routes.stream().filter((route -> (command.getRouteOfCommand().getName())
                .compareTo(route.getName()) > 0)).count() == routes.size()) {
            routes.add(AssignmentOfAutomaticallyGeneratedFields.generate(addedRoute));
            logger.info("Element added successfully");
            return new ArrayList<>(Collections.singletonList(new Message(1, 1,
                    "Element added successfully")));
        } else {
            logger.warn("The element was not added to the collection");
            return new ArrayList<>(Collections.singletonList(new Message(1, 1,
                    "The element was not added to the collection")));
        }
    }

    @Override
    public boolean support(String commandName) {
        return "add_if_max".equals(commandName);
    }
}
