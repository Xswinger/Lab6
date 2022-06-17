package serverPart.commandsClasses;

import dto.Message;
import serverPart.Commands;
import serverPart.Logger;
import serverPart.interfaces.CommandManualNoParameters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


/**
 * Класс команды help
 */
public class HelpCommand implements CommandManualNoParameters {
    private static final org.slf4j.Logger logger = Logger.getLogger("HelpCommand");
    /**
     * Метод execute команды help
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual() throws IOException {
        List<Message> arrayOfMessage = new ArrayList<>();
        Arrays.stream(Commands.values()).forEachOrdered(commands ->
                arrayOfMessage.add(new Message(arrayOfMessage.size() + 1, Commands.values().length,
                        Commands.getNameCommand(commands) + " : " + Commands.getDescription(commands))));
        logger.info("Command completed");
        return arrayOfMessage;
    }
}
