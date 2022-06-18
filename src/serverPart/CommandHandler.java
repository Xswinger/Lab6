package serverPart;

import serverPart.interfaces.CommandManual;
import serverPart.interfaces.CommandScript;

public interface CommandHandler extends CommandManual, CommandScript {
    boolean support(String commandName);
}
