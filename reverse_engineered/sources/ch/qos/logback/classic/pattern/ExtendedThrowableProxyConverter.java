package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.StackTraceElementProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;

public class ExtendedThrowableProxyConverter extends ThrowableProxyConverter {
    protected void extraData(StringBuilder stringBuilder, StackTraceElementProxy stackTraceElementProxy) {
        ThrowableProxyUtil.subjoinPackagingData(stringBuilder, stackTraceElementProxy);
    }

    protected void prepareLoggingEvent(ILoggingEvent iLoggingEvent) {
    }
}
