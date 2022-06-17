package serverPart.utils;

import dto.Route;
import dto.locations.locationFrom.Location;
import serverPart.Logger;

//Меняет поля выбранного объекта на поля отправленного объекта
public class ChangeFieldValue {

    private static final org.slf4j.Logger logger = Logger.getLogger("ChangeFieldValue");
    public static String ChangeFieldValue(Route editableRoute, Route newRoute, long id) {
        try {
            if (newRoute.getFrom() != null) {
                editableRoute.setId(id);
                editableRoute.setName(newRoute.getName());
                editableRoute.setCoordinates(newRoute.getCoordinates().getX(), newRoute.getCoordinates().getY());
                editableRoute.setCreationData(newRoute.getCreationDate());
                editableRoute.setFrom(new Location(newRoute.getFrom().getX(), newRoute.getFrom().getY(), newRoute.getFrom().getZ()));
            } else {
                editableRoute.setId(id);
                editableRoute.setName(newRoute.getName());
                editableRoute.setCoordinates(newRoute.getCoordinates().getX(), newRoute.getCoordinates().getY());
                editableRoute.setCreationData(newRoute.getCreationDate());
                editableRoute.setFrom();
            }
            editableRoute.setTo(newRoute.getTo().getX(), newRoute.getTo().getY(), newRoute.getTo().getZ(), newRoute.getTo().getName());
            editableRoute.setDistance(newRoute.getDistance());
            logger.info("Element update successfully");
            return ("Element update successfully");
        } catch (NumberFormatException ex) {
            logger.info("Invalid input variable format");
            return ("Invalid input variable format");
        }
    }
}
