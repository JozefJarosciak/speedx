package ch.qos.logback.classic.pattern;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.util.LevelToSyslogSeverity;
import ch.qos.logback.core.net.SyslogAppenderBase;
import com.j256.ormlite.stmt.query.SimpleComparison;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class SyslogStartConverter extends ClassicConverter {
    int facility;
    long lastTimestamp = -1;
    final String localHostName = "localhost";
    SimpleDateFormat simpleFormat;
    String timesmapStr = null;

    String computeTimeStampString(long j) {
        String str;
        synchronized (this) {
            if (j != this.lastTimestamp) {
                this.lastTimestamp = j;
                this.timesmapStr = this.simpleFormat.format(new Date(j));
            }
            str = this.timesmapStr;
        }
        return str;
    }

    public String convert(ILoggingEvent iLoggingEvent) {
        StringBuilder stringBuilder = new StringBuilder();
        int convert = this.facility + LevelToSyslogSeverity.convert(iLoggingEvent);
        stringBuilder.append(SimpleComparison.LESS_THAN_OPERATION);
        stringBuilder.append(convert);
        stringBuilder.append(SimpleComparison.GREATER_THAN_OPERATION);
        stringBuilder.append(computeTimeStampString(iLoggingEvent.getTimeStamp()));
        stringBuilder.append(' ');
        stringBuilder.append("localhost");
        stringBuilder.append(' ');
        return stringBuilder.toString();
    }

    public void start() {
        Object obj = null;
        String firstOption = getFirstOption();
        if (firstOption == null) {
            addError("was expecting a facility string as an option");
            return;
        }
        this.facility = SyslogAppenderBase.facilityStringToint(firstOption);
        try {
            this.simpleFormat = new SimpleDateFormat("MMM dd HH:mm:ss", new DateFormatSymbols(Locale.US));
        } catch (Throwable e) {
            addError("Could not instantiate SimpleDateFormat", e);
            obj = 1;
        }
        if (obj == null) {
            super.start();
        }
    }
}
