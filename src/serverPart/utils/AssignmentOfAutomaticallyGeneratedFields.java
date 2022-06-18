package serverPart.utils;

import dto.IdGenerator;
import dto.Route;

import java.time.LocalDate;

//Класс генерации полей в элементы коллекции
public class AssignmentOfAutomaticallyGeneratedFields {
    public static Route generate(Route route) {
        IdGenerator.getInstance().increaseId();
        route.setId(IdGenerator.getInstance().addId());
        route.setCreationData(LocalDate.now());
        return route;
    }
}
