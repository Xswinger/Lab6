package serverPart;

import dto.Route;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;

public class Manager {

    public static HashSet<Route> routes;
    public static LocalDate dateOfInitialization;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        while (true) {
            System.out.println("Waiting to connect...");
            Server.receiveData();
        }
    }
}
