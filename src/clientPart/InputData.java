package clientPart;

import java.util.Scanner;

public class InputData {
    private static final Scanner scanner = new Scanner(System.in);

    //Ввод значений каждого поля объекта
    public static String InputName() {
        System.out.println("Enter name:");
        return scanner.nextLine();
    }

    public static String InputLocationName() {
        System.out.println("Enter Location name:");
        return scanner.nextLine();
    }

    public static Object InputLocationFromX() {
        System.out.println("Enter Location coordinate x: (optional, press Enter to skip input Location)");
        String input = scanner.nextLine();
        if (input.equals("")) {
            return "none";
        } else {
            return Float.parseFloat(input);
        }
    }

    public static double InputLocationFromY() {
        System.out.println("Enter Location coordinate y:");
        return Double.parseDouble(scanner.nextLine());
    }

    public static Integer InputLocationFromZ() {
        System.out.println("Enter Location coordinate z:");
        return Integer.parseInt(scanner.nextLine());
    }

    public static Integer InputLocationToX() {
        System.out.println("Enter Location coordinate x:");
        return Integer.parseInt(scanner.nextLine());
    }

    public static long InputLocationToY() {
        System.out.println("Enter Location coordinate y:");
        return Long.parseLong(scanner.nextLine());
    }

    public static float InputLocationToZ() {
        System.out.println("Enter Location coordinate z:");
        return Float.parseFloat(scanner.nextLine());
    }

    public static long InputCoordinatesX() {
        System.out.println("Enter Coordinates x:");
        return Long.parseLong(scanner.nextLine());
    }

    public static Integer InputCoordinatesY() {
        System.out.println("Enter Coordinates y:");
        return Integer.parseInt(scanner.nextLine());
    }

    public static Integer InputDistance() {
        System.out.println("Enter Distance:");
        return Integer.parseInt(scanner.nextLine());
    }
}
