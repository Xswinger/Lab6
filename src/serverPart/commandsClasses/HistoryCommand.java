package serverPart.commandsClasses;

import dto.Command;
import dto.Message;
import serverPart.CommandHandler;
import serverPart.Logger;
import serverPart.interfaces.CommandManual;
import serverPart.mainClasses.History;
import serverPart.mainClasses.Invoker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс команды history
 */
public class HistoryCommand implements CommandHandler {
    private static final org.slf4j.Logger logger = Logger.getLogger("HistoryCommand");

    public HistoryCommand() {
        Invoker.getInstance().registerHandler(this);
    }
    /**
     * Метод execute команды collection
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual(Command command) {
        List<String> listWithCommands = History.history;
        List<Message> arrayOfMessage = new ArrayList<>();
        if (listWithCommands.size() > 12) {
            listWithCommands.stream().limit(12).forEachOrdered(executedCommand -> arrayOfMessage.add(
                    new Message(arrayOfMessage.size() + 1, 12, executedCommand)));
        } else {
            listWithCommands.stream().limit(12).forEachOrdered(executedCommand -> arrayOfMessage.add(
                    new Message(arrayOfMessage.size() + 1, listWithCommands.size(), executedCommand)));
        }
        logger.info("Command completed");
        return arrayOfMessage;
    }

    @Override
    public boolean support(String commandName) {
        return "history".equals(commandName);
    }

    @Override
    public List<Message> executeScript(Command command, Object... args) throws IOException {
        return executeManual(command);
    }
}
