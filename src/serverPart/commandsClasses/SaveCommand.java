package serverPart.commandsClasses;

import dto.Message;
import serverPart.interfaces.CommandManualNoParameters;
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

public class SaveCommand implements CommandManualNoParameters {
    /**
     * Метод execute команды exit
     *
     * @return Message[]
     */
    @Override
    public List<Message> executeManual() throws IOException {
        return new ArrayList<>(Collections.singleton(new Message(1, 1,
                Parser.InFile(routes, savingFile))));
    }
}
