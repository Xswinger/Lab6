package serverPart.commandsClasses;

import dto.Command;
import dto.Message;
import dto.Route;
import serverPart.interfaces.CommandManualWithParameters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static serverPart.Manager.routes;

/**
 * Класс команды filter_by_distance
 */
public class FilterByDistanceCommand implements CommandManualWithParameters {
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
}
