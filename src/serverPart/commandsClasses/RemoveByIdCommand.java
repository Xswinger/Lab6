package serverPart.commandsClasses;

import dto.Command;
import dto.Message;

import static serverPart.Manager.routes;

import serverPart.interfaces.CommandManualWithParameters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Класс команды remove_by_id
 */
public class RemoveByIdCommand implements CommandManualWithParameters {
    /**
     * Метод execute команды remove_by_id
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual(Command command) {
        if (routes.removeIf(route -> route.getId() == Integer.parseInt(command.getParameterOfCommand()))) {
            return new ArrayList<>(Collections.singleton(new Message(1, 1,
                    "Element deleted successfully")));
        } else {
            return new ArrayList<>(Collections.singleton(new Message(1, 1,
                    "Element with this id was not found")));
        }
    }
}
