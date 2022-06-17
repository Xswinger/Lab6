package serverPart.commandsClasses;

import dto.Message;
import dto.Route;
import serverPart.Logger;
import serverPart.interfaces.CommandManualNoParameters;

import java.util.*;
import java.util.stream.Collectors;

import static serverPart.Manager.routes;

/**
 * Класс команды group_counting_by_name
 */
public class GroupCountingByNameCommand implements CommandManualNoParameters {
    private static final org.slf4j.Logger logger = Logger.getLogger("GroupCountingByNameCommand");
    /**
     * Метод execute команды group_counting_by_name
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual() {
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
}
