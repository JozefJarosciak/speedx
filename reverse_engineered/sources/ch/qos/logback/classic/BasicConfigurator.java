package ch.qos.logback.classic;

import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.encoder.Encoder;
import ch.qos.logback.core.status.InfoStatus;
import ch.qos.logback.core.status.StatusManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Deprecated
public class BasicConfigurator {
    static final BasicConfigurator hiddenSingleton = new BasicConfigurator();

    private BasicConfigurator() {
    }

    public static void configure(LoggerContext loggerContext) {
        StatusManager statusManager = loggerContext.getStatusManager();
        if (statusManager != null) {
            statusManager.add(new InfoStatus("Setting up default configuration.", loggerContext));
        }
        Appender consoleAppender = new ConsoleAppender();
        consoleAppender.setContext(loggerContext);
        consoleAppender.setName("console");
        Encoder patternLayoutEncoder = new PatternLayoutEncoder();
        patternLayoutEncoder.setContext(loggerContext);
        patternLayoutEncoder.setPattern("%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n");
        patternLayoutEncoder.start();
        consoleAppender.setEncoder(patternLayoutEncoder);
        consoleAppender.start();
        loggerContext.getLogger(Logger.ROOT_LOGGER_NAME).addAppender(consoleAppender);
    }

    public static void configureDefaultContext() {
        configure((LoggerContext) LoggerFactory.getILoggerFactory());
    }
}
