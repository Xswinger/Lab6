package serverPart.mainClasses;

import dto.Command;
import dto.Message;
import serverPart.CommandHandler;
import serverPart.Commands;
import serverPart.commandsClasses.*;
import serverPart.exceptions.ScriptUnknownCommandException;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static serverPart.mainClasses.History.history;

/**
 * Класс выборки команды
 */
public class Invoker {
    private static Invoker instance = null;
    public static Invoker getInstance() {
        if (instance == null) {
            instance = new Invoker();
        }
        return instance;
    }
    private final List<CommandHandler> commandHandlers = new ArrayList<>();

    public void registerHandler(CommandHandler commandHandler) {
        commandHandlers.add(commandHandler);
    }

    /**
     * Метод, вызывающий определенную команду (для ручного ввода)
     *
     * @throws IOException - если в команде execute_script будет исключение
     */
    public List<Message> choiceCommandManual(Command command) throws IOException {
        try {
            for (CommandHandler commandHandler : commandHandlers) {
                if (commandHandler.support(command.getNameOfCommand())) {
                    history.add(Commands.getShortNameCommand(command));
                    return commandHandler.executeManual(command);
                }
            }
            return new ArrayList<>(Collections.singleton(new Message(1, 1,
                    "Unknown command")));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            return new ArrayList<>(Collections.singleton(new Message(1, 1,
                    "Incorrect parameter of command")));
        }
    }

    /**
     * Метод, вызывающий определенную команду (для исполнения скрипта)
     *
     * @throws IOException - если в команде execute_script будет исключение
     */
    public List<Message> choiceCommandScript(Command command, BufferedReader bufferedReader) throws IOException, ScriptUnknownCommandException {
        try {
            for (CommandHandler commandHandler : commandHandlers) {
                if (commandHandler.support(command.getNameOfCommand())) {
                    history.add(Commands.getShortNameCommand(command));
                    return commandHandler.executeScript(command, bufferedReader);
                }
            }
            return new ArrayList<>(Collections.singleton(new Message(1, 1,
                    "Unknown command")));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            return new ArrayList<>(Collections.singleton(new Message(1, 1,
                    "Incorrect parameter of command")));
        }
    }

    public void initializeCommand() {
        new AddElementCommand();
        new AddIfMaxCommand();
        new ClearCommand();
        new ExecuteScriptCommand();
        new FilterByDistanceCommand();
        new HelpCommand();
        new GroupCountingByNameCommand();
        new HistoryCommand();
        new InfoCommand();
        new PrintDescending();
        new RemoveGreaterCommand();
        new RemoveByIdCommand();
        new SaveCommand();
        new ShowCommand();
        new UpdateIdCommand();
    }
}
