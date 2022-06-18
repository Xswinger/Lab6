package serverPart.interfaces;

import dto.Command;
import dto.Message;

import java.io.IOException;
import java.util.List;

public interface CommandManual {
    List<Message> executeManual(Command command) throws IOException;
}
