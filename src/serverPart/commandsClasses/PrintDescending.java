package serverPart.commandsClasses;

import dto.Message;
import dto.Route;
import serverPart.Logger;
import serverPart.interfaces.CommandManualNoParameters;

import java.util.*;
import java.util.stream.Collectors;

import static serverPart.Manager.routes;

/**
 * Класс команды print_descending
 */
public class PrintDescending implements CommandManualNoParameters {
    private static final org.slf4j.Logger logger = Logger.getLogger("PrintDescending");
    /**
     * Метод execute команды print_descending
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual() {
        List<Message> arrayOfMessage = new ArrayList<>();
        if (routes.size() == 0) {
            return new ArrayList<>(Collections.singleton(new Message(1, 1,
                    "Cannot sort: collection is empty")));
        }
        List<Route> sortedList = routes.stream().sorted(Comparator.comparing(Route::getName).reversed()).collect(Collectors.toList());
        Collections.reverse(sortedList);
        sortedList.forEach(route -> arrayOfMessage.add(new Message(arrayOfMessage.size() + 1, sortedList.size(), route)));
        logger.info("Command completed");
        return arrayOfMessage;
    }
}
