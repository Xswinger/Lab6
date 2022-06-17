package serverPart.commandsClasses;

import dto.Message;
import serverPart.Logger;
import serverPart.interfaces.CommandManualNoParameters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static serverPart.Manager.routes;
import static serverPart.Manager.dateOfInitialization;

/**
 * Класс команды info
 */
public class InfoCommand implements CommandManualNoParameters {
    private static final org.slf4j.Logger logger = Logger.getLogger("InfoCommand");
    /**
     * Метод execute команды info
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual() {
        return new ArrayList<>(Collections.singleton(new Message(1, 1,
                "Collection information:"
                        + "\nCollection type: " + routes.getClass().getName()
                        + "\nInitialization date: " + dateOfInitialization
                        + "\nAmount of elements: " + routes.size())));
    }
}
