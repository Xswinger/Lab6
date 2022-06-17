package serverPart;

import dto.Route;

import java.io.IOException;
import java.time.LocalDate;
import java.util.HashSet;

public class Manager {

    public static HashSet<Route> routes;
    public static LocalDate dateOfInitialization;

    private static final org.slf4j.Logger logger = Logger.getLogger("Manager");

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        logger.info("Start Server");
        while (true) {
            logger.info("Waiting to connect...");
            Server.receiveData();
        }
    }
}
