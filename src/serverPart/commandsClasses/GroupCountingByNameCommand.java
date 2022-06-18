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
 * Класс команды group_counting_by_name
 */
public class GroupCountingByNameCommand implements CommandHandler {
    private static final org.slf4j.Logger logger = Logger.getLogger("GroupCountingByNameCommand");

    public GroupCountingByNameCommand() {
        Invoker.getInstance().registerHandler(this);
    }
    /**
     * Метод execute команды group_counting_by_name
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual(Command command) {
        if (routes.size() != 0) {
            logger.info("Elements was group");
            return new ArrayList<>(Collections.singleton(new Message(1, 1,
                    new ArrayList<>(routes).stream().collect(Collectors.groupingBy(Route::getName,
                            Collectors.counting())).toString())));

        } else {
            logger.info("Cannot group collection items");
            return new ArrayList<>(Collections.singleton(new Message(1, 1,
                    "Cannot group collection items")));
        }
    }

    @Override
    public boolean support(String commandName) {
        return "group_counting_by_name".equals(commandName);
    }

    @Override
    public List<Message> executeScript(Command command, Object... args) throws IOException {
        return executeManual(command);
    }
}
