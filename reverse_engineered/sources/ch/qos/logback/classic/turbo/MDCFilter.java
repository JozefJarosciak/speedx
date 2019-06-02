package ch.qos.logback.classic.turbo;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.core.spi.FilterReply;
import org.slf4j.MDC;
import org.slf4j.Marker;

public class MDCFilter extends MatchingFilter {
    String MDCKey;
    String value;

    public FilterReply decide(Marker marker, Logger logger, Level level, String str, Object[] objArr, Throwable th) {
        if (this.MDCKey == null) {
            return FilterReply.NEUTRAL;
        }
        return this.value.equals(MDC.get(this.MDCKey)) ? this.onMatch : this.onMismatch;
    }

    public void setMDCKey(String str) {
        this.MDCKey = str;
    }

    public void setValue(String str) {
        this.value = str;
    }
}
