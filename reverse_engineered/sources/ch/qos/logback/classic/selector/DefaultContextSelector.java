package ch.qos.logback.classic.selector;

import ch.qos.logback.classic.LoggerContext;
import java.util.Arrays;
import java.util.List;

public class DefaultContextSelector implements ContextSelector {
    private LoggerContext defaultLoggerContext;

    public DefaultContextSelector(LoggerContext loggerContext) {
        this.defaultLoggerContext = loggerContext;
    }

    public LoggerContext detachLoggerContext(String str) {
        return this.defaultLoggerContext;
    }

    public List<String> getContextNames() {
        return Arrays.asList(new String[]{this.defaultLoggerContext.getName()});
    }

    public LoggerContext getDefaultLoggerContext() {
        return this.defaultLoggerContext;
    }

    public LoggerContext getLoggerContext() {
        return getDefaultLoggerContext();
    }

    public LoggerContext getLoggerContext(String str) {
        return this.defaultLoggerContext.getName().equals(str) ? this.defaultLoggerContext : null;
    }
}
