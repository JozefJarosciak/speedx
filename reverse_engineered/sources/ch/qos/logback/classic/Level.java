package ch.qos.logback.classic;

import java.io.Serializable;

public final class Level implements Serializable {
    public static final Level ALL = new Level(Integer.MIN_VALUE, "ALL");
    public static final int ALL_INT = Integer.MIN_VALUE;
    public static final Integer ALL_INTEGER = Integer.valueOf(Integer.MIN_VALUE);
    public static final Level DEBUG = new Level(10000, "DEBUG");
    public static final int DEBUG_INT = 10000;
    public static final Integer DEBUG_INTEGER = Integer.valueOf(10000);
    public static final Level ERROR = new Level(ERROR_INT, "ERROR");
    public static final int ERROR_INT = 40000;
    public static final Integer ERROR_INTEGER = Integer.valueOf(ERROR_INT);
    public static final Level INFO = new Level(20000, "INFO");
    public static final int INFO_INT = 20000;
    public static final Integer INFO_INTEGER = Integer.valueOf(20000);
    public static final Level OFF = new Level(Integer.MAX_VALUE, "OFF");
    public static final int OFF_INT = Integer.MAX_VALUE;
    public static final Integer OFF_INTEGER = Integer.valueOf(Integer.MAX_VALUE);
    public static final Level TRACE = new Level(5000, "TRACE");
    public static final int TRACE_INT = 5000;
    public static final Integer TRACE_INTEGER = Integer.valueOf(5000);
    public static final Level WARN = new Level(30000, "WARN");
    public static final int WARN_INT = 30000;
    public static final Integer WARN_INTEGER = Integer.valueOf(30000);
    private static final long serialVersionUID = -814092767334282137L;
    public final int levelInt;
    public final String levelStr;

    private Level(int i, String str) {
        this.levelInt = i;
        this.levelStr = str;
    }

    public static Level fromLocationAwareLoggerInteger(int i) {
        switch (i) {
            case 0:
                return TRACE;
            case 10:
                return DEBUG;
            case 20:
                return INFO;
            case 30:
                return WARN;
            case 40:
                return ERROR;
            default:
                throw new IllegalArgumentException(i + " not a valid level value");
        }
    }

    private Object readResolve() {
        return toLevel(this.levelInt);
    }

    public static Level toLevel(int i) {
        return toLevel(i, DEBUG);
    }

    public static Level toLevel(int i, Level level) {
        switch (i) {
            case Integer.MIN_VALUE:
                return ALL;
            case 5000:
                return TRACE;
            case 10000:
                return DEBUG;
            case 20000:
                return INFO;
            case 30000:
                return WARN;
            case ERROR_INT /*40000*/:
                return ERROR;
            case Integer.MAX_VALUE:
                return OFF;
            default:
                return level;
        }
    }

    public static Level toLevel(String str) {
        return toLevel(str, DEBUG);
    }

    public static Level toLevel(String str, Level level) {
        return str == null ? level : str.equalsIgnoreCase("ALL") ? ALL : str.equalsIgnoreCase("TRACE") ? TRACE : str.equalsIgnoreCase("DEBUG") ? DEBUG : str.equalsIgnoreCase("INFO") ? INFO : str.equalsIgnoreCase("WARN") ? WARN : str.equalsIgnoreCase("ERROR") ? ERROR : str.equalsIgnoreCase("OFF") ? OFF : level;
    }

    public static int toLocationAwareLoggerInteger(Level level) {
        if (level == null) {
            throw new IllegalArgumentException("null level parameter is not admitted");
        }
        switch (level.toInt()) {
            case 5000:
                return 0;
            case 10000:
                return 10;
            case 20000:
                return 20;
            case 30000:
                return 30;
            case ERROR_INT /*40000*/:
                return 40;
            default:
                throw new IllegalArgumentException(level + " not a valid level value");
        }
    }

    public static Level valueOf(String str) {
        return toLevel(str, DEBUG);
    }

    public boolean isGreaterOrEqual(Level level) {
        return this.levelInt >= level.levelInt;
    }

    public int toInt() {
        return this.levelInt;
    }

    public Integer toInteger() {
        switch (this.levelInt) {
            case Integer.MIN_VALUE:
                return ALL_INTEGER;
            case 5000:
                return TRACE_INTEGER;
            case 10000:
                return DEBUG_INTEGER;
            case 20000:
                return INFO_INTEGER;
            case 30000:
                return WARN_INTEGER;
            case ERROR_INT /*40000*/:
                return ERROR_INTEGER;
            case Integer.MAX_VALUE:
                return OFF_INTEGER;
            default:
                throw new IllegalStateException("Level " + this.levelStr + ", " + this.levelInt + " is unknown.");
        }
    }

    public String toString() {
        return this.levelStr;
    }
}
