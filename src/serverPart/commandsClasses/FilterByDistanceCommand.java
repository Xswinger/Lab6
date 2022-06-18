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

/**
 * Класс команды filter_by_distance
 */
public class FilterByDistanceCommand implements CommandHandler {
    private static final org.slf4j.Logger logger = Logger.getLogger("FilterByDistanceCommand");

    public FilterByDistanceCommand() {
        Invoker.getInstance().registerHandler(this);
    }
    /**
     * Метод execute команды add
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual(Command command) {
        if (routes.stream().noneMatch(route -> route.getDistance() ==
                Integer.parseInt(command.getParameterOfCommand()))) {
            return new ArrayList<>(Collections.singletonList(new Message(1, 1,
                    "No collection elements found with this distance value")));
        }
        int size = (int) routes.stream().filter(route -> route.getDistance() ==
                Integer.parseInt(command.getParameterOfCommand())).count();
        List<Message> arrayOfMessage = new ArrayList<>();
        routes.stream().filter(route -> route.getDistance() ==
                Integer.parseInt(command.getParameterOfCommand())).forEach(route ->
                arrayOfMessage.add(new Message(arrayOfMessage.size() + 1, size, route)));
        return arrayOfMessage;
    }

    @Override
    public List<Message> executeScript(Command command, Object... args) throws IOException {
        return executeManual(command);
    }

    @Override
    public boolean support(String commandName) {
        return "filter_by_distance".equals(commandName);
    }
}
