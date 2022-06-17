package clientPart;

import dto.Message;
import dto.Route;

public class Print {
    //Вывод ответа от сервера
    public static void printContent(Message[] arrayOfContent) {
        for (Message message : arrayOfContent) {
            if (message.getContentString() == null) {
                System.out.println(routeToString(message.getContentRoute()));
            } else {
                System.out.println(message.getContentString());
            }
        }
        System.out.println();
    }

    //Вывод в терминал объектов в читаемой форме
    private static String routeToString(Route route) {
        if (route.getFrom() == null) {
            return route.getId() + " " + route.getName() + " " + route.getCoordinates().getX() + " " +
                    route.getCoordinates().getY() + " " + route.getCreationDate() + " " + route.getTo().getX() + " " + route.getTo().getY() + " " +
                    route.getTo().getZ() + " " + route.getTo().getName() + " " + route.getDistance();
        } else {
            return route.getId() + " " + route.getName() + " " + route.getCoordinates().getX() + " " +
                    route.getCoordinates().getY() + " " + route.getCreationDate() + " " + route.getFrom().getX() + " " +
                    route.getFrom().getY() + " " + route.getFrom().getZ() + " " + route.getTo().getX() + " " + route.getTo().getY() + " " +
                    route.getTo().getZ() + " " + route.getTo().getName() + " " + route.getDistance();
        }
    }
}
