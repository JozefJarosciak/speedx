package io.rong.photoview.log;

public final class LogManager {
    private static Logger logger = new LoggerDefault();

    public static void setLogger(Logger logger) {
        logger = logger;
    }

    public static Logger getLogger() {
        return logger;
    }
}
