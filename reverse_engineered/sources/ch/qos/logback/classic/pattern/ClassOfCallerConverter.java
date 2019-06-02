package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class ClassOfCallerConverter extends NamedConverter {
    protected String getFullyQualifiedName(ILoggingEvent iLoggingEvent) {
        StackTraceElement[] callerData = iLoggingEvent.getCallerData();
        return (callerData == null || callerData.length <= 0) ? CallerData.NA : callerData[0].getClassName();
    }
}
