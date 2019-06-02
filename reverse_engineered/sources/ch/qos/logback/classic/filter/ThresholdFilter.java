package ch.qos.logback.classic.filter;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.filter.Filter;
import ch.qos.logback.core.spi.FilterReply;

public class ThresholdFilter extends Filter<ILoggingEvent> {
    Level level;

    public FilterReply decide(ILoggingEvent iLoggingEvent) {
        return !isStarted() ? FilterReply.NEUTRAL : iLoggingEvent.getLevel().isGreaterOrEqual(this.level) ? FilterReply.NEUTRAL : FilterReply.DENY;
    }

    public void setLevel(String str) {
        this.level = Level.toLevel(str);
    }

    public void start() {
        if (this.level != null) {
            super.start();
        }
    }
}
