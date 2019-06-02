package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.ILoggingEvent;
import java.util.concurrent.atomic.AtomicLong;

public class LocalSequenceNumberConverter extends ClassicConverter {
    AtomicLong sequenceNumber = new AtomicLong(System.currentTimeMillis());

    public String convert(ILoggingEvent iLoggingEvent) {
        return Long.toString(this.sequenceNumber.getAndIncrement());
    }
}
