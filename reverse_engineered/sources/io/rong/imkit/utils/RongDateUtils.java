package io.rong.imkit.utils;

import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class RongDateUtils {
    private static final int OTHER = 2014;
    private static final int TODAY = 6;
    private static final int YESTERDAY = 15;

    public static int judgeDate(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        Calendar instance2 = Calendar.getInstance();
        instance2.add(5, -1);
        instance2.set(11, 0);
        instance2.set(12, 0);
        instance2.set(13, 0);
        instance2.set(14, 0);
        Calendar instance3 = Calendar.getInstance();
        instance3.add(5, 1);
        instance3.set(11, 0);
        instance3.set(12, 0);
        instance3.set(13, 0);
        instance3.set(14, 0);
        Calendar instance4 = Calendar.getInstance();
        instance4.setTime(date);
        if (instance4.before(instance2)) {
            return OTHER;
        }
        if (instance4.before(instance)) {
            return 15;
        }
        if (instance4.before(instance3)) {
            return 6;
        }
        return OTHER;
    }

    public static String getConversationListFormatDate(Date date) {
        if (date == null) {
            return "";
        }
        switch (judgeDate(date)) {
            case 6:
                return formatDate(date, "HH:mm");
            case 15:
                return String.format(RongContext.getInstance().getResources().getString(C4974R.string.rc_yesterday_format), new Object[]{formatDate(date, "HH:mm")});
            case OTHER /*2014*/:
                return formatDate(date, "yyyy-MM-dd");
            default:
                return null;
        }
    }

    public static String getConversationFormatDate(Date date) {
        if (date == null) {
            return "";
        }
        switch (judgeDate(date)) {
            case 6:
                return formatDate(date, "HH:mm");
            case 15:
                return String.format(RongContext.getInstance().getResources().getString(C4974R.string.rc_yesterday_format), new Object[]{formatDate(date, "HH:mm")});
            case OTHER /*2014*/:
                return formatDate(date, "yyyy-MM-dd HH:mm");
            default:
                return null;
        }
    }

    public static boolean isShowChatTime(long j, long j2) {
        if (judgeDate(new Date(j)) != judgeDate(new Date(j2)) || j - j2 > ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD) {
            return true;
        }
        return false;
    }

    public static String formatDate(Date date, String str) {
        return new SimpleDateFormat(str).format(date);
    }
}
