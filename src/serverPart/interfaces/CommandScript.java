package serverPart.interfaces;

import dto.Command;
import dto.Message;

import java.io.IOException;
import java.util.List;

public interface CommandScript {
    boolean support(String commandName);
    List<Message> executeScript(Command command, Object... args) throws IOException;
}
