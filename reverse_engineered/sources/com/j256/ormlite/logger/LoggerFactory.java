package com.j256.ormlite.logger;

import com.j256.ormlite.logger.Log.Level;

public class LoggerFactory {
    public static final String LOG_TYPE_SYSTEM_PROPERTY = "com.j256.ormlite.logger.type";
    private static LogType logType;

    public enum LogType {
        ANDROID("android.util.Log", "com.j256.ormlite.android.AndroidLog"),
        SLF4J("org.slf4j.LoggerFactory", "com.j256.ormlite.logger.Slf4jLoggingLog"),
        COMMONS_LOGGING("org.apache.commons.logging.LogFactory", "com.j256.ormlite.logger.CommonsLoggingLog"),
        LOG4J2("org.apache.logging.log4j.LogManager", "com.j256.ormlite.logger.Log4j2Log"),
        LOG4J("org.apache.log4j.Logger", "com.j256.ormlite.logger.Log4jLog"),
        LOCAL(LocalLog.class.getName(), LocalLog.class.getName()) {
            public Log createLog(String str) {
                return new LocalLog(str);
            }

            public boolean isAvailable() {
                return true;
            }
        };
        
        private final String detectClassName;
        private final String logClassName;

        private LogType(String str, String str2) {
            this.detectClassName = str;
            this.logClassName = str2;
        }

        public Log createLog(String str) {
            try {
                return createLogFromClassName(str);
            } catch (Exception e) {
                Log localLog = new LocalLog(str);
                localLog.log(Level.WARNING, "Unable to call constructor with single String argument for class " + this.logClassName + ", so had to use local log: " + e.getMessage());
                return localLog;
            }
        }

        public boolean isAvailable() {
            if (!isAvailableTestClass()) {
                return false;
            }
            try {
                createLogFromClassName(getClass().getName()).isLevelEnabled(Level.INFO);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        Log createLogFromClassName(String str) throws Exception {
            return (Log) Class.forName(this.logClassName).getConstructor(new Class[]{String.class}).newInstance(new Object[]{str});
        }

        boolean isAvailableTestClass() {
            try {
                Class.forName(this.detectClassName);
                return true;
            } catch (Exception e) {
                return false;
            }
        }
    }

    private LoggerFactory() {
    }

    public static Logger getLogger(Class<?> cls) {
        return getLogger(cls.getName());
    }

    public static Logger getLogger(String str) {
        if (logType == null) {
            logType = findLogType();
        }
        return new Logger(logType.createLog(str));
    }

    public static String getSimpleClassName(String str) {
        String[] split = str.split("\\.");
        return split.length <= 1 ? str : split[split.length - 1];
    }

    private static LogType findLogType() {
        String property = System.getProperty(LOG_TYPE_SYSTEM_PROPERTY);
        if (property != null) {
            try {
                return LogType.valueOf(property);
            } catch (IllegalArgumentException e) {
                new LocalLog(LoggerFactory.class.getName()).log(Level.WARNING, "Could not find valid log-type from system property 'com.j256.ormlite.logger.type', value '" + property + "'");
            }
        }
        for (LogType logType : LogType.values()) {
            if (logType.isAvailable()) {
                return logType;
            }
        }
        return LogType.LOCAL;
    }
}
