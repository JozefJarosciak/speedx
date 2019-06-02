package ch.qos.logback.classic.spi;

import ch.qos.logback.classic.Logger;
import java.io.Serializable;
import java.util.Comparator;

public class LoggerComparator implements Serializable, Comparator<Logger> {
    private static final long serialVersionUID = 1;

    public int compare(Logger logger, Logger logger2) {
        return logger.getName().equals(logger2.getName()) ? 0 : logger.getName().equals(org.slf4j.Logger.ROOT_LOGGER_NAME) ? -1 : logger2.getName().equals(org.slf4j.Logger.ROOT_LOGGER_NAME) ? 1 : logger.getName().compareTo(logger2.getName());
    }
}
