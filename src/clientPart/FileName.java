package clientPart;

import dto.Command;
import dto.Message;

import java.util.Scanner;

public class FileName {
    //Получение имени переменной окружения
    public static Command formFileNameCommand() {
        System.out.println("Write the name of the environment variable containing the file name:");
        return new Command("environment", new Scanner(System.in).nextLine());
    }

    //Проверка ответа от сервера
    public static boolean checkFileName(Message[] message) {
        return message[0].getContentString().equals("Data upload successfully");
    }
}
