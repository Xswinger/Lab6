package serverPart;

import org.slf4j.LoggerFactory;

public class Logger {
    private static ch.qos.logback.classic.Logger logger;

    public static String getClassName(String className) {
        return className;
    }

    public static org.slf4j.Logger getLogger(String nameClass) {
        return LoggerFactory.getLogger(nameClass);
    }

    public static void setLogger(ch.qos.logback.classic.Logger logger) {
        Logger.logger = logger;
    }
}
