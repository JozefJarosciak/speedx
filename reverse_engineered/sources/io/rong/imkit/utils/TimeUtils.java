package io.rong.imkit.utils;

import io.rong.imkit.C4974R;
import io.rong.imkit.RongContext;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
    public static String formatData(long j) {
        if (j == 0) {
            return "";
        }
        int i = (int) (j / 86400000);
        int currentTimeMillis = (int) (System.currentTimeMillis() / 86400000);
        if (i == currentTimeMillis) {
            return fromatDate(j, "HH:mm");
        }
        if (i + 1 != currentTimeMillis) {
            return fromatDate(j, "yyyy-MM-dd");
        }
        return String.format(RongContext.getInstance().getBaseContext().getResources().getString(C4974R.string.rc_yesterday_format), new Object[]{fromatDate(j, "HH:mm")});
    }

    public static String formatTime(long j) {
        if (j == 0) {
            return "";
        }
        int i = (int) (j / 86400000);
        int currentTimeMillis = (int) (System.currentTimeMillis() / 86400000);
        if (i == currentTimeMillis) {
            return fromatDate(j, "HH:mm");
        }
        if (i + 1 != currentTimeMillis) {
            return fromatDate(j, "yyyy-MM-dd HH:mm");
        }
        return String.format(RongContext.getInstance().getBaseContext().getResources().getString(C4974R.string.rc_yesterday_format), new Object[]{fromatDate(j, "HH:mm")});
    }

    private static String fromatDate(long j, String str) {
        return new SimpleDateFormat(str).format(new Date(j));
    }
}
