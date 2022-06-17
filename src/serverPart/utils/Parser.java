package serverPart.utils;

import dto.Command;
import dto.Message;
import serverPart.Logger;
import serverPart.Manager;
import dto.Coordinates;
import dto.locations.locationTo.Location;
import dto.Route;
import serverPart.exceptions.WrongInputDataException;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.*;

/**
 *
 */
public class Parser {

    private static final org.slf4j.Logger logger = Logger.getLogger("Parser");
    /**
     * Метод для чтения данных из файла
     *
     * @param fileInputStream
     * @return Коллекция элементов
     * @throws IOException
     */
    public static Message outFile(FileInputStream fileInputStream) throws IOException {
        logger.info("Reading data from a file");
        try {
            Manager.routes = new HashSet<>();
            Manager.dateOfInitialization = LocalDate.now();
            BufferedInputStream file = new BufferedInputStream(fileInputStream);
            String str = "";
            int i;
            List<String> list = new ArrayList<>();
            while (true) {
                i = file.read();
                if (i != 10 && i != -1 && i != 13) {
                    str += (char) i;
                }
                if (i == 10) {
                    list.add(str);
                    str = "";
                }
                if (i == -1) {
                    list.add(str);
                    break;
                }
            }
            boolean sizeIsNull = false;
            if (Objects.equals(list.get(0), "") && list.size() == 1) {
                list.clear();
                sizeIsNull = true;
            }
            for (i = 0; i < list.size(); i++) {
                String[] data = list.get(i).trim().split(",");
                if (data.length == 13) {
                    Manager.routes.add(new Route(Route.checkId(Long.parseLong(data[0])), Route.checkName(data[1]),
                            Route.checkCoordinate(new Coordinates(Coordinates.checkX(Long.parseLong(data[2])),
                                    Coordinates.checkY(Integer.parseInt(data[3])))), Route.checkData(LocalDate.parse(data[4])),
                            new dto.locations.locationFrom.Location(Float.parseFloat(data[5]),
                                    Double.parseDouble(data[6]),
                                    dto.locations.locationFrom.Location.checkZ(Integer.parseInt(data[7]))),
                            Route.checkLocationTo(new Location(Location.checkX(Integer.parseInt(data[8])),
                                    Long.parseLong(data[9]), Float.parseFloat(data[10]), Location.checkName(data[11]))),
                            Route.checkDistance(Integer.parseInt(data[12]))));
                } else if (data.length == 10) {
                    Manager.routes.add(new Route(Route.checkId(Long.parseLong(data[0])), Route.checkName(data[1]),
                            Route.checkCoordinate(new Coordinates(Coordinates.checkX(Long.parseLong(data[2])),
                                    Coordinates.checkY(Integer.parseInt(data[3])))), Route.checkData(LocalDate.parse(data[4])),
                            null,
                            new Location(Location.checkX(Integer.parseInt(data[5])),
                                    Long.parseLong(data[6]), Float.parseFloat(data[7]), Location.checkName(data[8])),
                            Route.checkDistance(Integer.parseInt(data[9]))));
                } else {
                    throw new WrongInputDataException(String.valueOf(i + 1));
                }
            }
            file.close();
            logger.info("Reading data from a file");
            if (sizeIsNull) {
                return new Message(1,1, "File empty");
            }
            else{
                return new Message(1,1, "Data upload successfully");
            }
        } catch (FileNotFoundException ex) {
            Manager.routes.clear();
            logger.warn("File not found");
            return new Message(1,1, "File with this name is not found");
        } catch (NumberFormatException ex) {
            Manager.routes.clear();
            logger.warn("Invalid input variable format");
            return new Message(1,1, "Invalid input variable format");
        } catch (WrongInputDataException | IndexOutOfBoundsException | DateTimeParseException ex) {
            Manager.routes.clear();
            logger.warn("Invalid input string format in file");
            return new Message(1,1, "Invalid input string format (change input data in your file)");
        }
    }

    /**
     * Метод для записи данных в файл
     *
     * @param routes
     * @param filename
     * @return
     * @throws IOException
     */
    public static Message InFile(HashSet<Route> routes, String filename) throws IOException {
        logger.info("Writing data in file");
        try {
            List<String> list = new ArrayList<>();
            for (Route route : routes) {
                String strData;
                String valueZ;
                if (route.getFrom() == null) {
                    strData = route.getId() + "," + route.getName() + "," + route.getCoordinates().getX() + ","
                            + route.getCoordinates().getY() + "," + route.getCreationDate() + ","
                            + route.getTo().getX() + "," + route.getTo().getY() + "," + (int) route.getTo().getZ()
                            + "," + route.getTo().getName() + "," + route.getDistance();
                } else {
                    valueZ = String.valueOf(route.getFrom().getZ());
                    strData = route.getId() + "," + route.getName() + "," + route.getCoordinates().getX() + ","
                            + route.getCoordinates().getY() + ","
                            + route.getCreationDate() + "," + (int) route.getFrom().getX() + ","
                            + (int) route.getFrom().getY() + "," + valueZ + ","
                            + route.getTo().getX() + "," + route.getTo().getY() + "," + (int) route.getTo().getZ() + ","
                            + route.getTo().getName() + "," + route.getDistance();
                }
                list.add(strData);

            }
            int i = 0;
            BufferedOutputStream file = new BufferedOutputStream(new FileOutputStream(filePath(filename)));
            for (String data : list) {
                file.write(data.getBytes());
                if (i != (list.size() - 1)) {
                    file.write(10);
                }
                i++;
            }
            file.close();
            logger.info("Collection saved successfully");
            return new Message(1,1,"Collection saved successfully");
        } catch (FileNotFoundException ex) {
            logger.warn("File not found");
            return new Message(1,1,"File with that name not found");
        }
    }

    /**
     * Метод, возвращающий полный путь файла
     *
     * @param filename
     * @return
     */
    public static String filePath(String filename) {
        File path = new File(Parser.class.getProtectionDomain().getCodeSource().getLocation().getPath());
        String propertiesPath = path.getParentFile().getAbsolutePath();
        return (propertiesPath + "/" + filename);
    }
}

