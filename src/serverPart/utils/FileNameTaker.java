package serverPart.utils;

import dto.Command;
import dto.Message;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * класс возврата имени файла с данными
 */
public class FileNameTaker {
    /**
     * метод, возвращающий имя файла с данными из переменной окружения
     *
     * @return filename
     */
    public static Message getFileNameFromEnvironment(Command envName) throws IOException {
        try {
            String fileName = System.getenv(envName.getParameterOfCommand());
            if (fileName == null) {
                return new Message(1, 1, "No environment with this name");
            } else {
                Parser.outFile(new FileInputStream(Parser.filePath(fileName)));
                return new Message(1, 1, "Data upload successfully");
            }
        } catch (FileNotFoundException ex) {
            return new Message(1, 1, "File not found");
        }
    }

    //Проверяет сообщение о получении имени файла
    public static boolean checkFileName(Message message) {
        return message.getContentString().equals("Data upload successfully");
    }
}
