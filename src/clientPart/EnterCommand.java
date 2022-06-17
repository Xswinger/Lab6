package clientPart;

import dto.Command;
import dto.CreatingElement;

import java.util.Arrays;
import java.util.Scanner;

public class EnterCommand {
    //Выбор создания запроса команды или получения файла
    public static Command selectionRequest(boolean fileNameCheck) {
        if (fileNameCheck) {
            return enterCommand();
        } else {
            return FileName.formFileNameCommand();
        }
    }

    //передавать как enum имя команды
    public static Command enterCommand() {
        try {
            System.out.println("Enter command:");
            Scanner lineWithCommand = new Scanner(System.in);
            String inputData = lineWithCommand.nextLine();
            Command inputCommand;
            if (inputData.split(" ").length == 2) {
                inputCommand = new Command(inputData.split(" ")[0], inputData.split(" ")[1]);
            } else {
                inputCommand = new Command(inputData.split(" ")[0]);
            }
            if (Command.checkCommand(inputCommand.getNameOfCommand())) {
                if (Arrays.stream(new String[]{"add", "add_if_max", "remove_greater"}).anyMatch(s -> s.equals(inputCommand.getNameOfCommand()))) {
                    return new Command(inputCommand.getNameOfCommand(), CreatingElement.CreatingElementManual());
                } else if (inputCommand.getNameOfCommand().equals("update")) {
                    return new Command(inputCommand.getNameOfCommand(), inputCommand.getParameterOfCommand(), CreatingElement.CreatingElementManual());
                } else {
                    return inputCommand;
                }
            } else {
                return new Command("Unknown command", "Unknown command");
            }
        } catch (NumberFormatException ex) {
            return new Command("Invalid input variable format for this parameter");
        }
    }
}
