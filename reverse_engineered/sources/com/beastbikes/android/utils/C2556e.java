package com.beastbikes.android.utils;

import com.avos.avoscloud.AVException;
import com.beastbikes.android.BeastBikes;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/* compiled from: DateUtil */
/* renamed from: com.beastbikes.android.utils.e */
public class C2556e {
    /* renamed from: a */
    public static String m12823a(Date date) {
        if (date == null) {
            return "";
        }
        String str;
        boolean contains = BeastBikes.j().getResources().getConfiguration().locale.getLanguage().contains("zh");
        long time = date.getTime();
        if (C2556e.m12824a(time)) {
            Calendar instance = GregorianCalendar.getInstance();
            instance.setTime(date);
            int i = instance.get(11);
            str = "HH:mm";
            if (i > 17) {
                if (contains) {
                    str = "晚上 hh:mm";
                }
            } else if (i < 0 || i > 6) {
                if (i <= 11 || i > 17) {
                    if (contains) {
                        str = "上午 hh:mm";
                    }
                } else if (contains) {
                    str = "下午 hh:mm";
                }
            } else if (contains) {
                str = "凌晨 hh:mm";
            }
        } else {
            str = C2556e.m12826b(time) ? contains ? "昨天 HH:mm" : "MM-dd HH:mm" : contains ? "M月d日 HH:mm" : "MM-dd HH:mm";
        }
        if (contains) {
            return new SimpleDateFormat(str, Locale.CHINA).format(date);
        }
        return new SimpleDateFormat(str, Locale.US).format(date);
    }

    /* renamed from: a */
    private static boolean m12824a(long j) {
        C2582y b = C2556e.m12825b();
        if (j <= b.m12908a() || j >= b.m12910b()) {
            return false;
        }
        return true;
    }

    /* renamed from: b */
    private static boolean m12826b(long j) {
        C2582y a = C2556e.m12821a();
        if (j <= a.m12908a() || j >= a.m12910b()) {
            return false;
        }
        return true;
    }

    /* renamed from: a */
    public static String m12822a(int i) {
        int i2;
        int i3 = i / 60;
        if (i3 >= 60) {
            i2 = i3 / 60;
            i3 %= 60;
        } else {
            i2 = 0;
        }
        int i4 = i % 60;
        return String.format("%02d:%02d:%02d", new Object[]{Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4)});
    }

    /* renamed from: a */
    public static C2582y m12821a() {
        Calendar instance = Calendar.getInstance();
        instance.add(5, -1);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        long time = instance.getTime().getTime();
        Calendar instance2 = Calendar.getInstance();
        instance2.add(5, -1);
        instance2.set(11, 23);
        instance2.set(12, 59);
        instance2.set(13, 59);
        instance2.set(14, AVException.UNKNOWN);
        long time2 = instance2.getTime().getTime();
        C2582y c2582y = new C2582y();
        c2582y.m12909a(time);
        c2582y.m12911b(time2);
        return c2582y;
    }

    /* renamed from: b */
    public static C2582y m12825b() {
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        long time = instance.getTime().getTime();
        Calendar instance2 = Calendar.getInstance();
        instance2.set(11, 23);
        instance2.set(12, 59);
        instance2.set(13, 59);
        instance2.set(14, AVException.UNKNOWN);
        long time2 = instance2.getTime().getTime();
        C2582y c2582y = new C2582y();
        c2582y.m12909a(time);
        c2582y.m12911b(time2);
        return c2582y;
    }

    /* renamed from: c */
    public static long m12827c() {
        return ((System.currentTimeMillis() / 86400000) * 86400000) - ((long) TimeZone.getDefault().getRawOffset());
    }

    /* renamed from: d */
    public static long m12828d() {
        return (((System.currentTimeMillis() / 86400000) * 86400000) - ((long) TimeZone.getDefault().getRawOffset())) - 86400000;
    }
}
