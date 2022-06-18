package serverPart.commandsClasses;

import dto.Command;
import dto.Message;
import serverPart.CommandHandler;
import serverPart.Commands;
import serverPart.Logger;
import serverPart.mainClasses.Invoker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Класс команды help
 */
public class HelpCommand implements CommandHandler {
    private static final org.slf4j.Logger logger = Logger.getLogger("HelpCommand");

    public HelpCommand() {
        Invoker.getInstance().registerHandler(this);
    }

    /**
     * Метод execute команды help
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual(Command command) throws IOException {
        List<Message> arrayOfMessage = new ArrayList<>();
        Arrays.stream(Commands.values()).forEachOrdered(commands ->
                arrayOfMessage.add(new Message(arrayOfMessage.size() + 1, Commands.values().length,
                        Commands.getFullNameCommand(commands) + " : " + Commands.getDescription(commands))));
        logger.info("Help command in manual mode completed");
        return arrayOfMessage;
    }

    @Override
    public List<Message> executeScript(Command command, Object... args) {
        List<Message> arrayOfMessage = new ArrayList<>();
        Arrays.stream(Commands.values()).forEachOrdered(commands ->
                arrayOfMessage.add(new Message(arrayOfMessage.size() + 1, Commands.values().length,
                        Commands.getFullNameCommand(commands) + " : " + Commands.getDescription(commands))));
        logger.info("Help command in script mode completed");
        return arrayOfMessage;
    }

    @Override
    public boolean support(String commandName) {
        return "help".equals(commandName);
    }
}
