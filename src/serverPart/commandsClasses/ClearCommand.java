package serverPart.commandsClasses;

import dto.Id;
import dto.Message;
import serverPart.interfaces.CommandManualNoParameters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static serverPart.Manager.routes;

/**
 * Класс команды clear
 */
public class ClearCommand implements CommandManualNoParameters {
    /**
     * Метод execute команды clear
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual() {
        routes.clear();
        Id.zeroingId();
        return new ArrayList<>(Collections.singleton(new Message(1, 1, "Collection cleared")));
    }
}
