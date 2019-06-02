package com.beastbikes.android.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import org.apache.commons.cli.HelpFormatter;
import org.apache.http.HttpStatus;

/* compiled from: CalendarUtils */
/* renamed from: com.beastbikes.android.utils.c */
public class C2554c {
    /* renamed from: a */
    public static int m12788a(int i, int i2) {
        switch (i2 + 1) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 2:
                if ((i % 4 != 0 || i % 100 == 0) && i % HttpStatus.SC_BAD_REQUEST != 0) {
                    return 28;
                }
                return 29;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            default:
                return -1;
        }
    }

    /* renamed from: b */
    public static int m12791b(int i, int i2) {
        Calendar instance = Calendar.getInstance();
        instance.set(i, i2, 1);
        return instance.get(7);
    }

    /* renamed from: a */
    public static String m12790a(long j, long j2) {
        return new SimpleDateFormat("MM.dd", Locale.getDefault()).format(new Date((((24 * j2) * 3600) * 1000) + j));
    }

    /* renamed from: b */
    public static String m12792b(long j, long j2) {
        return new SimpleDateFormat("EEE", Locale.getDefault()).format(new Date((((24 * j2) * 3600) * 1000) + j));
    }

    /* renamed from: c */
    public static String m12793c(long j, long j2) {
        Calendar instance = Calendar.getInstance();
        long j3 = ((((7 * j2) * 24) * 3600) * 1000) + j;
        instance.setTimeInMillis(j3);
        long j4 = (long) instance.get(7);
        if (j4 - 1 == 0) {
            j4 = 7;
        } else {
            j4--;
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd", Locale.getDefault());
        return simpleDateFormat.format(new Date(j3 - ((((j4 - 1) * 24) * 3600) * 1000))) + HelpFormatter.DEFAULT_OPT_PREFIX + simpleDateFormat.format(new Date(((((7 - j4) * 24) * 3600) * 1000) + j3));
    }

    /* renamed from: a */
    public static int m12789a(int i, int i2, int i3) {
        Calendar instance = Calendar.getInstance();
        int i4 = instance.get(1);
        int i5 = instance.get(2) + 1;
        int i6 = instance.get(5);
        Calendar instance2 = Calendar.getInstance();
        instance2.set(i, i2 - 1, i3);
        if (instance.before(instance2)) {
            return 0;
        }
        int i7 = i4 - i;
        if (i5 > i2) {
            return i7;
        }
        if (i5 != i2) {
            return i7 - 1;
        }
        if (i6 < i3) {
            return i7 - 1;
        }
        return i7;
    }
}
