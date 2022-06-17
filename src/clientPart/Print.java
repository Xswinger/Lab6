package clientPart;

import dto.Message;
import dto.Route;

public class Print {
    //Вывод ответа от сервера
    public static void printContent(Message[] arrayOfContent) {
        for (Message message : arrayOfContent) {
            if (message.getContentString() == null) {
                routeToString(message.getContentRoute());
            } else {
                System.out.println(message.getContentString());
            }
        }
        System.out.println();
    }

    //Вывод в терминал объектов в читаемой форме
    private static void routeToString(Route route) {
        if (route.getFrom() == null) {
            System.out.printf("%-7s %-14s %-7s %-7s %-18s %-7s %-7s %-9s %-10s %-3s%n",
                    "Id: " + route.getId(), "Name: " + route.getName(), "X: " + route.getCoordinates().getX(), "Y: " +
                    route.getCoordinates().getY(), "Date: " + route.getCreationDate(),
                    "X: " + route.getTo().getX(), "Y: " + route.getTo().getY(), "Z: " + route.getTo().getZ(),
                    "Name: " + route.getTo().getName(), "Distance: " + route.getDistance());
        } else {
            System.out.printf("%-7s %-14s %-7s %-7s %-18s %-9s %-9s %-7s %-7s %-7s %-7s %-10s %-3s%n",
                    "Id: " + route.getId(), "Name: " + route.getName(), "X: " + route.getCoordinates().getX(), "Y: " +
                    route.getCoordinates().getY(), "Date: " + route.getCreationDate(),
                    "X: " + route.getFrom().getX(), "Y: " + route.getFrom().getY(), "Z: " + route.getFrom().getZ(),
                    "X: " + route.getTo().getX(), "Y: " + route.getTo().getY(), "Z: " +
                    route.getTo().getZ(), "Name: " + route.getTo().getName(), "Distance " + route.getDistance());
        }
    }
}
