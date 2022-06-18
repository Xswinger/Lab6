package serverPart.commandsClasses;

import dto.*;
import org.slf4j.LoggerFactory;
import serverPart.CommandHandler;
import serverPart.interfaces.CommandManual;
import serverPart.interfaces.CommandScript;
import serverPart.mainClasses.Invoker;
import serverPart.utils.AssignmentOfAutomaticallyGeneratedFields;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static serverPart.Manager.routes;

/**
 * Класс команды add
 */
public class AddElementCommand implements CommandHandler {

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger("AddElementCommand");

    public AddElementCommand() {
        Invoker.getInstance().registerHandler(this);
    }
    /**
     * Метод execute команды add(ручной ввод)
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual(Command command) {
        try {
            routes.add(AssignmentOfAutomaticallyGeneratedFields.generate(command.getRouteOfCommand()));
            logger.info("Element added successfully");
            return new ArrayList<>(Collections.singletonList(new Message(1, 1,
                    "Element added successfully")));
        } catch (NumberFormatException ex) {
            logger.warn("Invalid input variable format");
            return new ArrayList<>(Collections.singletonList(new Message(1, 1,
                    ex.getMessage() + " - Invalid input variable format")));
        }
    }

    /**
     * Метод execute команды add(исполнение скрипта)
     */
    @Override
    public List<Message> executeScript(Command command, Object... args) throws IOException {
        try {
            BufferedReader bufferedReader = (BufferedReader) args[0];
            routes.add(AssignmentOfAutomaticallyGeneratedFields.generate(CreatingElement.CreatingElementScript(bufferedReader)));
            return new ArrayList<>(Collections.singletonList(new Message(1, 1,
                    "Element added successfully")));
        } catch (NumberFormatException ex) {
            return new ArrayList<>(Collections.singletonList(new Message(1, 1,
                    "Error during element creation:\n" + ex.getMessage() + " - Invalid input variable format")));
        }
    }

    @Override
    public boolean support(String commandName) {
        return "add".equals(commandName);
    }
}
