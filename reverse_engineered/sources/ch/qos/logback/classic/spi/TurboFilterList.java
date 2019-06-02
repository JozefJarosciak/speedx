package ch.qos.logback.classic.spi;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.turbo.TurboFilter;
import ch.qos.logback.core.spi.FilterReply;
import java.util.concurrent.CopyOnWriteArrayList;
import org.slf4j.Marker;

public final class TurboFilterList extends CopyOnWriteArrayList<TurboFilter> {
    private static final long serialVersionUID = 1;

    public FilterReply getTurboFilterChainDecision(Marker marker, Logger logger, Level level, String str, Object[] objArr, Throwable th) {
        if (size() == 1) {
            try {
                return ((TurboFilter) get(0)).decide(marker, logger, level, str, objArr, th);
            } catch (IndexOutOfBoundsException e) {
                return FilterReply.NEUTRAL;
            }
        }
        for (Object obj : toArray()) {
            FilterReply decide = ((TurboFilter) obj).decide(marker, logger, level, str, objArr, th);
            if (decide == FilterReply.DENY || decide == FilterReply.ACCEPT) {
                return decide;
            }
        }
        return FilterReply.NEUTRAL;
    }
}
