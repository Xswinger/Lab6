package serverPart.commandsClasses;

import dto.Command;
import dto.Message;
import serverPart.CommandHandler;
import serverPart.Logger;
import serverPart.mainClasses.Invoker;
import serverPart.exceptions.ScriptUnknownCommandException;
import serverPart.interfaces.CommandManual;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static serverPart.utils.Parser.filePath;

/**
 * Класс команды execute_script
 */
public class ExecuteScriptCommand implements CommandHandler {
    private static final org.slf4j.Logger logger = Logger.getLogger("ExecuteScriptCommand");

    public ExecuteScriptCommand() {
        Invoker.getInstance().registerHandler(this);
    }
    /**
     * Метод execute команды execute_script
     *
     * @return Message[]
     * @throws IOException - все ошибки, возможные при исполнении скрипта
     */
    public List<Message> executeManual(Command command) throws IOException {
        try {
            File file = new File(filePath(command.getParameterOfCommand()));
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            final Command[] scriptCommand = new Command[1];
            List<Message> bufferListMessage = new ArrayList<>();
            bufferedReader.lines().forEachOrdered(line -> {
                String[] lineParts = line.split(" ");
                if (lineParts.length == 2) {
                    scriptCommand[0] = new Command(lineParts[0], lineParts[1]);
                } else {
                    scriptCommand[0] = new Command(line.split(" ")[0]);
                }
                List<Message> messages;
                try {
                    messages = Invoker.getInstance().choiceCommandScript(scriptCommand[0], bufferedReader);
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException | IOException |
                         ScriptUnknownCommandException e) {
                    throw new RuntimeException(e);
                }
                bufferListMessage.addAll(messages);
            });
            bufferListMessage.get(0).setMessageCount(bufferListMessage.size() + 1);
            bufferListMessage.add(new Message(bufferListMessage.size() + 1,
                    bufferListMessage.size() + 1, "Script execution completed"));
            logger.warn("Script execution completed");
            return bufferListMessage;
        } catch (FileNotFoundException ex) {
            logger.warn("The specified file cannot be found");
            return new ArrayList<>(Collections.singleton(new Message(1, 1,
                    "The specified file cannot be found")));
        }
    }

    @Override
    public boolean support(String commandName) {
        return "execute_script".equals(commandName);
    }

    @Override
    public List<Message> executeScript(Command command, Object... args) throws IOException {
        List<Message> bufferListMessage = new ArrayList<>();
        logger.warn("Execute script command in script");
        bufferListMessage.add(new Message(1, 1,
                "Cannot execute script into another script"));
        return bufferListMessage;
    }
}
