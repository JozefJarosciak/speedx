package p203u.aly;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/* compiled from: UMCCTimeRange */
/* renamed from: u.aly.bt */
public class bt {
    /* renamed from: a */
    public static String m21776a(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return String.valueOf(((long) ((((instance.get(12) / 6) + 1) + (instance.get(11) * 10)) - 1)) + (bt.m21780d(j) * 240));
    }

    /* renamed from: d */
    private static long m21780d(long j) {
        long j2 = 0;
        try {
            long time = new SimpleDateFormat("yyyy").parse("1970").getTime();
            long j3 = (j - time) / 86400000;
            if ((j - time) % 86400000 > 0) {
                j2 = 1;
            }
            return j2 + j3;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    /* renamed from: b */
    public static long m21778b(long j) {
        return bt.m21775a(j, 1001);
    }

    /* renamed from: c */
    public static long m21779c(long j) {
        return bt.m21775a(j, 1002);
    }

    /* renamed from: a */
    private static long m21775a(long j, int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        int i2 = ((instance.get(12) / 6) + 1) + (instance.get(11) * 10);
        int i3 = instance.get(13);
        int i4 = 0;
        if (i == 1002) {
            i4 = 360 - (((instance.get(12) % 6) * 60) + i3);
        } else if (i == 1001) {
            i4 = 60 - (i3 % 60);
            if (i2 % 6 == 0) {
                i4 += 60;
            }
        }
        return (long) (i4 * 1000);
    }

    /* renamed from: a */
    public static boolean m21777a(long j, long j2) {
        if (bt.m21781e(j) == bt.m21781e(j2)) {
            return true;
        }
        return false;
    }

    /* renamed from: e */
    private static int m21781e(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return instance.get(5);
    }
}
