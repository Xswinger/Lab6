package serverPart.commandsClasses;

import dto.Message;
import dto.Route;
import serverPart.interfaces.CommandManualNoParameters;

import java.util.*;
import java.util.stream.Collectors;

import static serverPart.Manager.routes;

/**
 * Класс команды group_counting_by_name
 */
public class GroupCountingByNameCommand implements CommandManualNoParameters {
    /**
     * Метод execute команды group_counting_by_name
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual() {
        if (routes.size() != 0) {
            return new ArrayList<>(Collections.singleton(new Message(1, 1,
                    new ArrayList<>(routes).stream().collect(Collectors.groupingBy(Route::getName,
                            Collectors.counting())).toString())));
        } else {
            return new ArrayList<>(Collections.singleton(new Message(1, 1,
                    "Cannot group collection items")));
        }
    }
}
