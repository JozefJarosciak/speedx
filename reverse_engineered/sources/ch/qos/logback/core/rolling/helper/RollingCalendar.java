package ch.qos.logback.core.rolling.helper;

import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.spi.ContextAwareBase;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class RollingCalendar extends GregorianCalendar {
    static final TimeZone GMT_TIMEZONE = TimeZone.getTimeZone("GMT");
    private static final long serialVersionUID = -5937537740925066161L;
    PeriodicityType periodicityType = PeriodicityType.ERRONEOUS;

    public RollingCalendar(TimeZone timeZone, Locale locale) {
        super(timeZone, locale);
    }

    public static int diffInMonths(long j, long j2) {
        if (j > j2) {
            throw new IllegalArgumentException("startTime cannot be larger than endTime");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTimeInMillis(j2);
        return (instance2.get(2) - instance.get(2)) + ((instance2.get(1) - instance.get(1)) * 12);
    }

    private void setPeriodicityType(PeriodicityType periodicityType) {
        this.periodicityType = periodicityType;
    }

    public PeriodicityType computePeriodicityType(String str) {
        RollingCalendar rollingCalendar = new RollingCalendar(GMT_TIMEZONE, Locale.getDefault());
        Date date = new Date(0);
        if (str != null) {
            for (PeriodicityType periodicityType : PeriodicityType.VALID_ORDERED_LIST) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat(str);
                simpleDateFormat.setTimeZone(GMT_TIMEZONE);
                String format = simpleDateFormat.format(date);
                rollingCalendar.setPeriodicityType(periodicityType);
                String format2 = simpleDateFormat.format(new Date(rollingCalendar.getNextTriggeringMillis(date)));
                if (format != null && format2 != null && !format.equals(format2)) {
                    return periodicityType;
                }
            }
        }
        return PeriodicityType.ERRONEOUS;
    }

    public Date getNextTriggeringDate(Date date) {
        return getRelativeDate(date, 1);
    }

    public long getNextTriggeringMillis(Date date) {
        return getNextTriggeringDate(date).getTime();
    }

    public PeriodicityType getPeriodicityType() {
        return this.periodicityType;
    }

    public Date getRelativeDate(Date date, int i) {
        setTime(date);
        switch (this.periodicityType) {
            case TOP_OF_MILLISECOND:
                add(14, i);
                break;
            case TOP_OF_SECOND:
                set(14, 0);
                add(13, i);
                break;
            case TOP_OF_MINUTE:
                set(13, 0);
                set(14, 0);
                add(12, i);
                break;
            case TOP_OF_HOUR:
                set(12, 0);
                set(13, 0);
                set(14, 0);
                add(11, i);
                break;
            case TOP_OF_DAY:
                set(11, 0);
                set(12, 0);
                set(13, 0);
                set(14, 0);
                add(5, i);
                break;
            case TOP_OF_WEEK:
                set(7, getFirstDayOfWeek());
                set(11, 0);
                set(12, 0);
                set(13, 0);
                set(14, 0);
                add(3, i);
                break;
            case TOP_OF_MONTH:
                set(5, 1);
                set(11, 0);
                set(12, 0);
                set(13, 0);
                set(14, 0);
                add(2, i);
                break;
            default:
                throw new IllegalStateException("Unknown periodicity type.");
        }
        return getTime();
    }

    public void init(String str) {
        this.periodicityType = computePeriodicityType(str);
    }

    public long periodsElapsed(long j, long j2) {
        if (j > j2) {
            throw new IllegalArgumentException("Start cannot come before end");
        }
        long j3 = j2 - j;
        switch (this.periodicityType) {
            case TOP_OF_MILLISECOND:
                return j3;
            case TOP_OF_SECOND:
                return j3 / 1000;
            case TOP_OF_MINUTE:
                return j3 / ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD;
            case TOP_OF_HOUR:
                return (long) (((int) j3) / CoreConstants.MILLIS_IN_ONE_HOUR);
            case TOP_OF_DAY:
                return j3 / 86400000;
            case TOP_OF_WEEK:
                return j3 / 604800000;
            case TOP_OF_MONTH:
                return (long) diffInMonths(j, j2);
            default:
                throw new IllegalStateException("Unknown periodicity type.");
        }
    }

    public void printPeriodicity(ContextAwareBase contextAwareBase) {
        switch (this.periodicityType) {
            case TOP_OF_MILLISECOND:
                contextAwareBase.addInfo("Roll-over every millisecond.");
                return;
            case TOP_OF_SECOND:
                contextAwareBase.addInfo("Roll-over every second.");
                return;
            case TOP_OF_MINUTE:
                contextAwareBase.addInfo("Roll-over every minute.");
                return;
            case TOP_OF_HOUR:
                contextAwareBase.addInfo("Roll-over at the top of every hour.");
                return;
            case HALF_DAY:
                contextAwareBase.addInfo("Roll-over at midday and midnight.");
                return;
            case TOP_OF_DAY:
                contextAwareBase.addInfo("Roll-over at midnight.");
                return;
            case TOP_OF_WEEK:
                contextAwareBase.addInfo("Rollover at the start of week.");
                return;
            case TOP_OF_MONTH:
                contextAwareBase.addInfo("Rollover at start of every month.");
                return;
            default:
                contextAwareBase.addInfo("Unknown periodicity.");
                return;
        }
    }
}
