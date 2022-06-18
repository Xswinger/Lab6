package dto;

import clientPart.InputData;
import dto.locations.locationFrom.Location;

import java.io.BufferedReader;
import java.io.IOException;
import java.time.LocalDate;

public class CreatingElement {
    //Создание элемента коллекции через терминал
    public static Route CreatingElementManual() {
        String name = Route.checkName(InputData.InputName());
        Coordinates coordinates = Route.checkCoordinate(new Coordinates(Coordinates.checkX(InputData.InputCoordinatesX()),
                Coordinates.checkY(InputData.InputCoordinatesY())));
        LocalDate creationDate = LocalDate.now();
        Location from = null;
        Object X = InputData.InputLocationFromX();
        if (!X.toString().equals("none")) {
            from = new Location(Float.parseFloat(X.toString()), InputData.InputLocationFromY(),
                    Location.checkZ(InputData.InputLocationFromZ()));
        }
        dto.locations.locationTo.Location to = Route.checkLocationTo(new
                dto.locations.locationTo.Location(dto.locations.locationTo.Location.checkX(InputData.InputLocationToX()),
                InputData.InputLocationToY(), InputData.InputLocationToZ(), Route.checkName(InputData.InputLocationName())));
        int distance = Route.checkDistance(InputData.InputDistance());
        return checkFrom(from, X, name, coordinates, creationDate, to, distance);

    }

    //Создание элемента коллекции через скрипт
    public static Route CreatingElementScript(BufferedReader bufferedReader) throws IOException {
        String bufferName = bufferedReader.readLine();
        String bufferCoordinateX = bufferedReader.readLine();
        String bufferCoordinateY = bufferedReader.readLine();
        String bufferFromX = bufferedReader.readLine();
        String bufferFromY = "";
        String bufferFromZ = "";
        Location from = null;
        if (!((Object) bufferFromX).toString().equals("")) {
            bufferFromY = bufferedReader.readLine();
            bufferFromZ = bufferedReader.readLine();
        }
        String bufferToX = bufferedReader.readLine();
        String bufferToY = bufferedReader.readLine();
        String bufferToZ = bufferedReader.readLine();
        String bufferToName = bufferedReader.readLine();
        String bufferDistance = bufferedReader.readLine();
        String name = Route.checkName(bufferName);
        Coordinates coordinates = Route.checkCoordinate(new Coordinates(Coordinates.checkX(Long.parseLong(bufferCoordinateX)),
                Coordinates.checkY(Integer.parseInt(bufferCoordinateY))));
        LocalDate creationDate = LocalDate.now();
        if (!((Object) bufferFromX).toString().equals("")) {
            from = new Location(Float.parseFloat(((Object) bufferFromX).toString()), Double.parseDouble(bufferFromY),
                    Location.checkZ(Integer.parseInt(bufferFromZ)));
        }
        dto.locations.locationTo.Location to = Route.checkLocationTo(new
                dto.locations.locationTo.Location(dto.locations.locationTo.Location.checkX(Integer.parseInt(bufferToX)),
                Long.parseLong(bufferToY), Float.parseFloat(bufferToZ), Route.checkName(bufferToName)));
        int distance = Route.checkDistance(Integer.parseInt(bufferDistance));
        return checkFrom(from, bufferFromX, name, coordinates, creationDate, to, distance);
    }

    private static Route checkFrom(Location from, Object x, String name, Coordinates coordinates, LocalDate creationDate, dto.locations.locationTo.Location to, int distance) {
        Route addedRoute;
        if ("none".equals(x.toString())) {
            addedRoute = new Route(IdGenerator.getInstance().addId(), name, coordinates, creationDate, to, distance);
        } else {
            addedRoute = new Route(IdGenerator.getInstance().addId(), name, coordinates, creationDate, from, to, distance);
        }
        return addedRoute;
    }

}
