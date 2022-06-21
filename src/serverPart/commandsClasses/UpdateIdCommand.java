package serverPart.commandsClasses;

import dto.*;
import serverPart.CommandHandler;
import serverPart.Logger;
import serverPart.interfaces.CommandManual;
import serverPart.interfaces.CommandScript;
import serverPart.mainClasses.Invoker;
import serverPart.utils.ChangeFieldValue;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static serverPart.Manager.routes;

/**
 * Класс команды update_id
 */
public class UpdateIdCommand implements CommandHandler {
    private static final org.slf4j.Logger logger = Logger.getLogger("UpdateIdCommand");

    public UpdateIdCommand() {
        Invoker.getInstance().registerHandler(this);
    }
    /**
     * Метод execute команды update_id
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual(Command command) {
        int id = Integer.parseInt(command.getParameterOfCommand());
        Route addedRoute = command.getRouteOfCommand();
        return getMessages(id, addedRoute);
    }

    /**
     * Метод execute команды update
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeScript(Command command, Object... args) throws IOException {
        int id = Integer.parseInt(command.getParameterOfCommand());
        BufferedReader bufferedReader = (BufferedReader) args[1];
        Route addedRoute = CreatingElement.CreatingElementScript(bufferedReader);
        return getMessages(id, addedRoute);
    }

    private List<Message> getMessages(int id, Route addedRoute) {
        String backMessage = "Element with id " + id + " not found";
        if (routes.stream().anyMatch(route -> id == route.getId())) {
            return new ArrayList<>(Collections.singleton(new Message(1, 1,
                    ChangeFieldValue.ChangeFieldValue(routes.stream().filter
                            (route -> id == route.getId()).findFirst().get(), addedRoute, id))));
        }
        logger.info("Command completed");
        return new ArrayList<>(Collections.singleton(new Message(1, 1, backMessage)));
    }

    @Override
    public boolean support(String commandName) {
        return "update".equals(commandName);
    }
}
