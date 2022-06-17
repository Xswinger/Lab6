package serverPart.interfaces;

import dto.Message;

import java.io.IOException;
import java.util.List;

public interface CommandManualNoParameters {
    List<Message> executeManual() throws IOException;
}
