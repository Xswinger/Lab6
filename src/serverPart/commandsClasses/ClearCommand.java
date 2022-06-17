package serverPart.commandsClasses;

import dto.Id;
import dto.Message;
import serverPart.Logger;
import serverPart.interfaces.CommandManualNoParameters;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static serverPart.Manager.routes;

/**
 * Класс команды clear
 */
public class ClearCommand implements CommandManualNoParameters {

    private static final org.slf4j.Logger logger = Logger.getLogger("ClearCommand");
    /**
     * Метод execute команды clear
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual() {
        routes.clear();
        Id.zeroingId();
        logger.info("Collection cleared");
        return new ArrayList<>(Collections.singleton(new Message(1, 1, "Collection cleared")));
    }
}
