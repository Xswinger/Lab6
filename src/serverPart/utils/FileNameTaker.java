package serverPart.utils;

import dto.Command;
import dto.Message;
import org.omg.CORBA.Environment;
import serverPart.Logger;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * класс возврата имени файла с данными
 */
public class FileNameTaker {

    private static final org.slf4j.Logger logger = Logger.getLogger("FileNameTaker");
    /**
     * метод, возвращающий имя файла с данными из переменной окружения
     *
     * @return filename
     */
    public static Message getFileNameFromEnvironment(Command envName) throws IOException {
        String fileName = System.getenv(envName.getParameterOfCommand());
        if (fileName == null) {
            logger.warn("Environment {} not found", envName.getParameterOfCommand());
            return new Message(1, 1, "No environment with this name");
        } else {
            return Parser.outFile(new FileInputStream(Parser.filePath(fileName)));
        }
    }

    //Проверяет сообщение о получении имени файла
    public static boolean checkFileName(Message message) {
        return message.getContentString().equals("Data upload successfully");
    }
}
