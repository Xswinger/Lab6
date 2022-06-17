package serverPart.utils;

import dto.Id;
import dto.Route;

import java.time.LocalDate;

//Класс генерации полей в элементы коллекции
public class AssignmentOfAutomaticallyGeneratedFields {
    public static Route generate(Route route) {
        Id.increaseId();
        route.setId(Id.addId());
        route.setCreationData(LocalDate.now());
        return route;
    }
}
