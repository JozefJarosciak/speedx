package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.CallerData;
import ch.qos.logback.classic.spi.ILoggingEvent;

public class LineOfCallerConverter extends ClassicConverter {
    public String convert(ILoggingEvent iLoggingEvent) {
        StackTraceElement[] callerData = iLoggingEvent.getCallerData();
        return (callerData == null || callerData.length <= 0) ? CallerData.NA : Integer.toString(callerData[0].getLineNumber());
    }
}
