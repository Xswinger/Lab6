package serverPart.commandsClasses;

import dto.Message;
import serverPart.Logger;
import serverPart.mainClasses.History;
import serverPart.interfaces.CommandManualNoParameters;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс команды history
 */
public class HistoryCommand implements CommandManualNoParameters {
    private static final org.slf4j.Logger logger = Logger.getLogger("HistoryCommand");
    /**
     * Метод execute команды collection
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual() {
        List<String> listWithCommands = History.history;
        List<Message> arrayOfMessage = new ArrayList<>();
        if (listWithCommands.size() > 12) {
            listWithCommands.stream().limit(12).forEachOrdered(command -> arrayOfMessage.add(
                    new Message(arrayOfMessage.size() + 1, 12, command)));
        } else {
            listWithCommands.stream().limit(12).forEachOrdered(command -> arrayOfMessage.add(
                    new Message(arrayOfMessage.size() + 1, listWithCommands.size(), command)));
        }
        logger.info("Command completed");
        return arrayOfMessage;
    }
}
