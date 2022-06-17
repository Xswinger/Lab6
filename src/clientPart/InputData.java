package clientPart;

import dto.Coordinates;
import dto.Route;
import dto.locations.locationFrom.Location;

import java.util.Scanner;

public class InputData {
    private static final Scanner scanner = new Scanner(System.in);

    //Ввод значений каждого поля объекта
    public static String InputName() {
        try {
            System.out.println("Enter name:");
            return Route.checkName(scanner.nextLine().trim());
        }
        catch (NumberFormatException e){
            System.out.println("Invalid format of field 'name'");
            return InputName();
        }
    }

    public static String InputLocationName() {
        try {
            System.out.println("Enter Location name:");
            return dto.locations.locationTo.Location.checkName(scanner.nextLine().trim());
        }
        catch (NumberFormatException e){
            System.out.println("Invalid format of Location's field 'name'");
            return InputLocationName();
        }
    }

    public static Object InputLocationFromX() {
        try {
            System.out.println("Enter Location coordinate x: (optional, press Enter to skip input Location)");
            String input = scanner.nextLine().trim();
            if (input.equals("")) {
                return "none";
            } else {
                return Float.parseFloat(input);
            }
        }
        catch (NumberFormatException e){
            System.out.println("Invalid format of Location's field 'x'");
            return InputLocationFromX();
        }
    }

    public static double InputLocationFromY() {
        try {
            System.out.println("Enter Location coordinate y:");
            return Double.parseDouble(scanner.nextLine().trim());
        }
        catch (NumberFormatException e){
            System.out.println("Invalid format of Location's field 'y'");
            return InputLocationFromY();
        }
    }

    public static Integer InputLocationFromZ() {
        try {
            System.out.println("Enter Location coordinate z:");
            return Location.checkZ(Integer.parseInt(scanner.nextLine().trim()));
        }
        catch (NumberFormatException e){
            System.out.println("Invalid format of Location's field 'z'");
            return InputLocationFromZ();
        }
    }

    public static Integer InputLocationToX() {
        try {
            System.out.println("Enter Location coordinate x:");
            return dto.locations.locationTo.Location.checkX(Integer.parseInt(scanner.nextLine().trim()));
        }
        catch (NumberFormatException e){
            System.out.println("Invalid format of Location's field 'x'");
            return InputLocationToX();
        }
    }

    public static long InputLocationToY() {
        try {
            System.out.println("Enter Location coordinate y:");
            return Long.parseLong(scanner.nextLine().trim());
        }
        catch (NumberFormatException e){
            System.out.println("Invalid format of Location's field 'y'");
            return InputLocationToY();
        }
    }

    public static float InputLocationToZ() {
        try {
            System.out.println("Enter Location coordinate z:");
            return Float.parseFloat(scanner.nextLine().trim());
        }
        catch (NumberFormatException e){
            System.out.println("Invalid format of Location's field 'z'");
            return InputLocationToZ();
        }
    }

    public static long InputCoordinatesX() {
        try {
            System.out.println("Enter Coordinates x:");
            return Coordinates.checkX(Long.parseLong(scanner.nextLine().trim()));
        }
        catch (NumberFormatException e){
            System.out.println("Invalid format of Coordinate's field 'x'");
            return InputCoordinatesX();
        }
    }

    public static Integer InputCoordinatesY() {
        try {
            System.out.println("Enter Coordinates y:");
            return Coordinates.checkY(Integer.parseInt(scanner.nextLine().trim()));
        }
        catch (NumberFormatException e){
            System.out.println("Invalid format of Coordinate's field 'y'");
            return InputCoordinatesY();
        }
    }

    public static Integer InputDistance() {
        try {
            System.out.println("Enter Distance:");
            return Route.checkDistance(Integer.parseInt(scanner.nextLine().trim()));
        }
        catch (NumberFormatException e){
            System.out.println("Invalid format of field 'distance'");
            return InputDistance();
        }
    }
}
