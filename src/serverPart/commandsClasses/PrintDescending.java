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
 * Класс команды print_descending
 */
public class PrintDescending implements CommandHandler {
    private static final org.slf4j.Logger logger = Logger.getLogger("PrintDescending");

    public PrintDescending() {
        Invoker.getInstance().registerHandler(this);
    }
    /**
     * Метод execute команды print_descending
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual(Command command) {
        List<Message> arrayOfMessage = new ArrayList<>();
        if (routes.size() == 0) {
            return new ArrayList<>(Collections.singleton(new Message(1, 1,
                    "Cannot sort: collection is empty")));
        }
        List<Route> sortedList = routes.stream().sorted(Comparator.comparing(Route::getSize)).collect(Collectors.toList());
        Collections.reverse(sortedList);
        sortedList.forEach(route -> arrayOfMessage.add(new Message(arrayOfMessage.size() + 1, sortedList.size(), route)));
        logger.info("Command completed");
        return arrayOfMessage;
    }

    @Override
    public boolean support(String commandName) {
        return "print_descending".equals(commandName);
    }

    @Override
    public List<Message> executeScript(Command command, Object... args) throws IOException {
        return executeManual(command);
    }
}
