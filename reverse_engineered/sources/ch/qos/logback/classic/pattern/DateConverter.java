package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.util.CachingDateFormatter;
import java.util.List;
import java.util.TimeZone;

public class DateConverter extends ClassicConverter {
    CachingDateFormatter cachingDateFormatter = null;
    long lastTimestamp = -1;
    String timestampStrCache = null;

    public String convert(ILoggingEvent iLoggingEvent) {
        return this.cachingDateFormatter.format(iLoggingEvent.getTimeStamp());
    }

    public void start() {
        String firstOption = getFirstOption();
        if (firstOption == null) {
            firstOption = CoreConstants.ISO8601_PATTERN;
        }
        String str = firstOption.equals(CoreConstants.ISO8601_STR) ? CoreConstants.ISO8601_PATTERN : firstOption;
        try {
            this.cachingDateFormatter = new CachingDateFormatter(str);
        } catch (Throwable e) {
            addWarn("Could not instantiate SimpleDateFormat with pattern " + str, e);
            this.cachingDateFormatter = new CachingDateFormatter(CoreConstants.ISO8601_PATTERN);
        }
        List optionList = getOptionList();
        if (optionList != null && optionList.size() > 1) {
            this.cachingDateFormatter.setTimeZone(TimeZone.getTimeZone((String) optionList.get(1)));
        }
    }
}
