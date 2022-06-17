package serverPart.mainClasses;

import dto.Command;
import dto.Message;
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
    /**
     * Метод, вызывающий определенную команду (для исполнения скрипта)
     *
     * @throws IOException - если в команде execute_script будет исключение
     */
    public List<Message> choiceCommandScript(Command command, BufferedReader bufferedReader) throws IOException, ScriptUnknownCommandException {
        try {
            switch (command.getNameOfCommand()) {
                case "help":
                    history.add(Commands.getNameCommand(Commands.HELP));
                    return new HelpCommand().executeManual();
                case "info":
                    history.add(Commands.getNameCommand(Commands.INFO));
                    return new InfoCommand().executeManual();
                case "show":
                    history.add(Commands.getNameCommand(Commands.SHOW));
                    return new ShowCommand().executeManual();
                case "add":
                    history.add(Commands.getNameCommand(Commands.ADD));
                    return new AddElementCommand().executeScript(command, bufferedReader);
                case "update":
                    history.add(Commands.getNameCommand(Commands.UPDATE_ID));
                    return new UpdateIdCommand().executeScript(command, Integer.parseInt(command.getParameterOfCommand()), bufferedReader);
                case "remove_by_id":
                    history.add(Commands.getNameCommand(Commands.REMOVE_BY_ID));
                    return new RemoveByIdCommand().executeManual(command);
                case "clear":
                    history.add(Commands.getNameCommand(Commands.CLEAR));
                    return new ClearCommand().executeManual();
                case "execute_script":
                    history.add(Commands.getNameCommand(Commands.EXECUTE_SCRIPT));
                    return new ExecuteScriptCommand().executeManual(command);
                case "add_if_max":
                    history.add(Commands.getNameCommand(Commands.ADD_IF_MAX));
                    return new AddIfMaxCommand().executeScript(command, bufferedReader);
                case "remove_greater":
                    history.add(Commands.getNameCommand(Commands.REMOVE_GREATER));
                    return new RemoveGreaterCommand().executeScript(command, bufferedReader);
                case "history":
                    history.add(Commands.getNameCommand(Commands.HISTORY));
                    return new HistoryCommand().executeManual();
                case "group_counting_by_name":
                    history.add(Commands.getNameCommand(Commands.GROUP_COUNTING_BY_NAME));
                    return new GroupCountingByNameCommand().executeManual();
                case "filter_by_distance":
                    history.add(Commands.getNameCommand(Commands.FILTER_BY_DISTANCE));
                    return new FilterByDistanceCommand().executeManual(command);
                case "print_descending":
                    history.add(Commands.getNameCommand(Commands.PRINT_DESCENDING));
                    return new PrintDescending().executeManual();
                case "exit":
                    history.add(Commands.getNameCommand(Commands.EXIT));
                    return new SaveCommand().executeManual();
                default:
                    throw new ScriptUnknownCommandException(command.getNameOfCommand());
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            return new ArrayList<>(Collections.singleton(new Message(1, 1,
                    "Incorrect parameter of command")));
        }
    }

    /**
     * Метод, вызывающий определенную команду (для ручного ввода)
     *
     * @throws IOException - если в команде execute_script будет исключение
     */
    public List<Message> choiceCommandManual(Command command) throws IOException {
        try {
            switch (command.getNameOfCommand()) {
                case "help":
                    history.add(Commands.getNameCommand(Commands.HELP));
                    return new HelpCommand().executeManual();

                case "info":
                    history.add(Commands.getNameCommand(Commands.INFO));
                    return new InfoCommand().executeManual();

                case "show":
                    history.add(Commands.getNameCommand(Commands.SHOW));
                    return new ShowCommand().executeManual();

                case "add":
                    history.add(Commands.getNameCommand(Commands.ADD));
                    return new AddElementCommand().executeManual(command);

                case "update":
                    history.add(Commands.getNameCommand(Commands.UPDATE_ID));
                    return new UpdateIdCommand().executeManual(command);

                case "remove_by_id":
                    history.add(Commands.getNameCommand(Commands.REMOVE_BY_ID));
                    return new RemoveByIdCommand().executeManual(command);

                case "clear":
                    history.add(Commands.getNameCommand(Commands.CLEAR));
                    return new ClearCommand().executeManual();

                case "execute_script":
                    history.add(Commands.getNameCommand(Commands.EXECUTE_SCRIPT));
                    return new ExecuteScriptCommand().executeManual(command);

                case "add_if_max":
                    history.add(Commands.getNameCommand(Commands.ADD_IF_MAX));
                    return new AddIfMaxCommand().executeManual(command);

                case "remove_greater":
                    history.add(Commands.getNameCommand(Commands.REMOVE_GREATER));
                    return new RemoveGreaterCommand().executeManual(command);

                case "history":
                    history.add(Commands.getNameCommand(Commands.HISTORY));
                    return new HistoryCommand().executeManual();

                case "group_counting_by_name":
                    history.add(Commands.getNameCommand(Commands.GROUP_COUNTING_BY_NAME));
                    return new GroupCountingByNameCommand().executeManual();

                case "filter_by_distance":
                    history.add(Commands.getNameCommand(Commands.FILTER_BY_DISTANCE));
                    return new FilterByDistanceCommand().executeManual(command);

                case "print_descending":
                    history.add(Commands.getNameCommand(Commands.PRINT_DESCENDING));
                    return new PrintDescending().executeManual();
                case "exit":
                    history.add(Commands.getNameCommand(Commands.EXIT));
                    return new SaveCommand().executeManual();
                default:
                    return new ArrayList<>(Collections.singleton(new Message(1, 1,
                            "Unknown command")));
            }
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException ex) {
            return new ArrayList<>(Collections.singleton(new Message(1, 1,
                    "Incorrect parameter of command")));
        }
    }
}
