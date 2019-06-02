package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.ILoggingEvent;
import org.slf4j.Marker;

public class MarkerConverter extends ClassicConverter {
    private static String EMPTY = "";

    public String convert(ILoggingEvent iLoggingEvent) {
        Marker marker = iLoggingEvent.getMarker();
        return marker == null ? EMPTY : marker.toString();
    }
}
