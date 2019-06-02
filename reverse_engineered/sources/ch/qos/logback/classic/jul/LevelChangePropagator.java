package ch.qos.logback.classic.jul;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.LogManager;

@Deprecated
public class LevelChangePropagator extends ContextAwareBase implements LoggerContextListener, LifeCycle {
    boolean isStarted = false;
    private Set julLoggerSet = new HashSet();
    boolean resetJUL = false;

    private void propagate(Logger logger, Level level) {
        addInfo("Propagating " + level + " level on " + logger + " onto the JUL framework");
        java.util.logging.Logger asJULLogger = JULHelper.asJULLogger(logger);
        this.julLoggerSet.add(asJULLogger);
        asJULLogger.setLevel(JULHelper.asJULLevel(level));
    }

    private void propagateExistingLoggerLevels() {
        for (Logger logger : ((LoggerContext) this.context).getLoggerList()) {
            if (logger.getLevel() != null) {
                propagate(logger, logger.getLevel());
            }
        }
    }

    public boolean isResetResistant() {
        return false;
    }

    public boolean isStarted() {
        return this.isStarted;
    }

    public void onLevelChange(Logger logger, Level level) {
        propagate(logger, level);
    }

    public void onReset(LoggerContext loggerContext) {
    }

    public void onStart(LoggerContext loggerContext) {
    }

    public void onStop(LoggerContext loggerContext) {
    }

    public void resetJULLevels() {
        LogManager logManager = LogManager.getLogManager();
        Enumeration loggerNames = logManager.getLoggerNames();
        while (loggerNames.hasMoreElements()) {
            String str = (String) loggerNames.nextElement();
            java.util.logging.Logger logger = logManager.getLogger(str);
            if (JULHelper.isRegularNonRootLogger(logger) && logger.getLevel() != null) {
                addInfo("Setting level of jul logger [" + str + "] to null");
                logger.setLevel(null);
            }
        }
    }

    public void setResetJUL(boolean z) {
        this.resetJUL = z;
    }

    public void start() {
        if (this.resetJUL) {
            resetJULLevels();
        }
        propagateExistingLoggerLevels();
        this.isStarted = true;
    }

    public void stop() {
        this.isStarted = false;
    }
}
