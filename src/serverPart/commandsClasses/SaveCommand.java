package serverPart.commandsClasses;

import dto.Command;
import dto.Message;
import serverPart.CommandHandler;
import serverPart.interfaces.CommandManual;
import serverPart.mainClasses.Invoker;
import serverPart.utils.Parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static serverPart.Manager.routes;
import static serverPart.Server.savingFile;

/**
 * Класс команды exit
 */

public class SaveCommand implements CommandHandler {

    public SaveCommand() {
        Invoker.getInstance().registerHandler(this);
    }
    /**
     * Метод execute команды exit
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual(Command command) throws IOException {
        return new ArrayList<>(Collections.singleton(Parser.InFile(routes, savingFile)));
    }

    @Override
    public boolean support(String commandName) {
        return "exit".equals(commandName);
    }

    @Override
    public List<Message> executeScript(Command command, Object... args) throws IOException {
        return executeManual(command);
    }
}
