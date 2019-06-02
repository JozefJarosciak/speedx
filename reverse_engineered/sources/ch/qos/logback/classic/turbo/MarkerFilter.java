package ch.qos.logback.classic.turbo;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

public class MarkerFilter extends MatchingFilter {
    Marker markerToMatch;

    public FilterReply decide(Marker marker, Logger logger, Level level, String str, Object[] objArr, Throwable th) {
        return !isStarted() ? FilterReply.NEUTRAL : marker == null ? this.onMismatch : marker.contains(this.markerToMatch) ? this.onMatch : this.onMismatch;
    }

    public void setMarker(String str) {
        if (str != null) {
            this.markerToMatch = MarkerFactory.getMarker(str);
        }
    }

    public void start() {
        if (this.markerToMatch != null) {
            super.start();
        } else {
            addError("The marker property must be set for [" + getName() + "]");
        }
    }
}
