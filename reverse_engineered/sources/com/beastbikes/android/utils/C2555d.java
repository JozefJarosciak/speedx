package com.beastbikes.android.utils;

import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateUtils;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1373R;
import com.mapbox.services.commons.tidy.Tidy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import org.apache.commons.cli.HelpFormatter;

/* compiled from: DateFormatUtil */
/* renamed from: com.beastbikes.android.utils.d */
public class C2555d {
    /* renamed from: a */
    public static String m12796a(long j) {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ").format(Long.valueOf(j));
    }

    /* renamed from: a */
    public static String m12799a(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(date);
    }

    /* renamed from: a */
    public static String m12798a(String str) {
        return C2555d.m12808c(C2555d.m12820h(str));
    }

    /* renamed from: b */
    public static String m12804b(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(date);
    }

    /* renamed from: b */
    public static String m12802b(long j) {
        long j2;
        long j3;
        long j4 = 0;
        if (j > 0) {
            j2 = j / 3600;
            j3 = (j % 3600) / 60;
            j4 = (j % 3600) % 60;
        } else {
            j3 = 0;
            j2 = 0;
        }
        return String.format(Locale.getDefault(), "%02d:%02d:%02d", new Object[]{Long.valueOf(j2), Long.valueOf(j3), Long.valueOf(j4)});
    }

    /* renamed from: c */
    public static String m12807c(long j) {
        long j2;
        long j3 = 0;
        if (j > 0) {
            j2 = j / 60;
            j3 = j % 60;
        } else {
            j2 = 0;
        }
        return String.format(Locale.getDefault(), "%02d:%02d", new Object[]{Long.valueOf(j2), Long.valueOf(j3)});
    }

    /* renamed from: c */
    public static String m12808c(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        try {
            simpleDateFormat.setTimeZone(TimeZone.getDefault());
            return simpleDateFormat.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: b */
    public static long m12800b(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ");
        try {
            simpleDateFormat.setTimeZone(TimeZone.getDefault());
            return simpleDateFormat.parse(str).getTime();
        } catch (Exception e) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            simpleDateFormat.setTimeZone(TimeZone.getDefault());
            try {
                return simpleDateFormat.parse(str).getTime();
            } catch (Exception e2) {
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                simpleDateFormat.setTimeZone(TimeZone.getDefault());
                try {
                    return simpleDateFormat.parse(str).getTime();
                } catch (ParseException e3) {
                    try {
                        SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(Tidy.DATE_FORMAT);
                        simpleDateFormat2.setTimeZone(TimeZone.getDefault());
                        return simpleDateFormat2.parse(str).getTime();
                    } catch (Exception e4) {
                        e3.printStackTrace();
                        return 0;
                    }
                }
            }
        }
    }

    /* renamed from: c */
    public static long m12805c(String str) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        try {
            return simpleDateFormat.parse(str).getTime();
        } catch (Exception e) {
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            simpleDateFormat.setTimeZone(TimeZone.getDefault());
            try {
                return simpleDateFormat.parse(str).getTime();
            } catch (Exception e2) {
                e2.printStackTrace();
                return 0;
            }
        }
    }

    /* renamed from: a */
    public static String m12795a(int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.valueOf(i));
        if (i2 < 10) {
            stringBuilder.append("-0");
        } else {
            stringBuilder.append(HelpFormatter.DEFAULT_OPT_PREFIX);
        }
        stringBuilder.append(String.valueOf(i2)).append("-01 00:00:00");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        try {
            Date parse = simpleDateFormat.parse(stringBuilder.toString());
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ");
            simpleDateFormat.setTimeZone(TimeZone.getDefault());
            return simpleDateFormat.format(parse);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: b */
    public static String m12801b(int i, int i2) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.valueOf(i));
        if (i2 < 10) {
            stringBuilder.append("-0");
        } else {
            stringBuilder.append(HelpFormatter.DEFAULT_OPT_PREFIX);
        }
        stringBuilder.append(String.valueOf(i2 + 1)).append("-01 00:00:00");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        try {
            Date parse = simpleDateFormat.parse(stringBuilder.toString());
            simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ");
            simpleDateFormat.setTimeZone(TimeZone.getDefault());
            return simpleDateFormat.format(parse);
        } catch (ParseException e) {
            e.printStackTrace();
            return "";
        }
    }

    /* renamed from: d */
    public static int m12810d(String str) {
        Date h = C2555d.m12820h(str);
        Calendar instance = Calendar.getInstance();
        if (h == null) {
            instance.setTime(new Date());
        } else {
            instance.setTime(h);
        }
        return instance.get(1);
    }

    /* renamed from: e */
    public static int m12813e(String str) {
        Date h = C2555d.m12820h(str);
        Calendar instance = Calendar.getInstance();
        if (h == null) {
            instance.setTime(new Date());
        } else {
            instance.setTime(h);
        }
        return instance.get(2) + 1;
    }

    /* renamed from: d */
    public static int m12809d(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date(j));
        return instance.get(1);
    }

    /* renamed from: e */
    public static int m12812e(long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date(j));
        return instance.get(2) + 1;
    }

    /* renamed from: a */
    public static CharSequence m12794a(Context context, String str) {
        long c = C2555d.m12805c(str);
        if (Math.abs(System.currentTimeMillis() - c) < 300000) {
            return context.getString(C1373R.string.feedback_activity_just_now);
        }
        return DateUtils.getRelativeTimeSpanString(c);
    }

    /* renamed from: f */
    public static String m12815f(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM.dd HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(Long.valueOf(j));
    }

    /* renamed from: g */
    public static String m12818g(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(Long.valueOf(j));
    }

    /* renamed from: h */
    public static String m12819h(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        simpleDateFormat.setTimeZone(TimeZone.getDefault());
        return simpleDateFormat.format(Long.valueOf(j));
    }

    /* renamed from: f */
    public static String m12816f(String str) {
        return C2555d.m12811d(C2555d.m12820h(str));
    }

    /* renamed from: d */
    public static String m12811d(Date date) {
        if (date == null) {
            return "";
        }
        Context applicationContext = BeastBikes.j().getApplicationContext();
        long time = date.getTime();
        long currentTimeMillis = System.currentTimeMillis() - time;
        if (currentTimeMillis < 300000) {
            return applicationContext.getResources().getText(C1373R.string.justnow).toString();
        }
        if (currentTimeMillis < 3600000) {
            return ((int) ((currentTimeMillis / 1000) / 60)) + "" + applicationContext.getResources().getText(C1373R.string.minutesago);
        }
        if (time > C2556e.m12827c()) {
            return ((int) ((currentTimeMillis / 1000) / 3600)) + "" + applicationContext.getResources().getText(C1373R.string.hours_ago);
        }
        if (time > C2556e.m12828d()) {
            return applicationContext.getResources().getText(C1373R.string.yesterday).toString();
        }
        return C2555d.m12804b(date);
    }

    /* renamed from: e */
    public static String m12814e(Date date) {
        if (date == null) {
            return C2555d.m12808c(new Date());
        }
        return C2555d.m12808c(new Date(date.getTime() + ((long) 0)));
    }

    /* renamed from: g */
    public static long m12817g(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        return C2555d.m12805c(C2555d.m12799a(C2555d.m12820h(str)));
    }

    /* renamed from: h */
    public static Date m12820h(String str) {
        Date date = null;
        if (!TextUtils.isEmpty(str)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ");
            try {
                simpleDateFormat.setTimeZone(TimeZone.getDefault());
                date = simpleDateFormat.parse(str);
            } catch (Exception e) {
                simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
                try {
                    simpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
                    date = simpleDateFormat.parse(str);
                } catch (Exception e2) {
                    simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
                    try {
                        simpleDateFormat.setTimeZone(TimeZone.getDefault());
                        date = simpleDateFormat.parse(str);
                    } catch (ParseException e3) {
                        try {
                            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(Tidy.DATE_FORMAT);
                            simpleDateFormat2.setTimeZone(TimeZone.getTimeZone("UTC"));
                            date = simpleDateFormat2.parse(str);
                        } catch (Exception e4) {
                            e3.printStackTrace();
                        }
                    }
                }
            }
        }
        return date;
    }

    /* renamed from: a */
    public static String m12797a(Context context, long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date(j));
        int i = instance.get(5);
        if (i != 1) {
            return String.valueOf(i);
        }
        int i2 = instance.get(2) + 1;
        return String.format(context.getString(C1373R.string.label_month), new Object[]{Integer.valueOf(i2)}) + i;
    }

    /* renamed from: b */
    public static String m12803b(Context context, long j) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(new Date(j));
        switch (instance.get(7)) {
            case 1:
                return context.getString(C1373R.string.label_sunday);
            case 2:
                return context.getString(C1373R.string.label_monday);
            case 3:
                return context.getString(C1373R.string.label_tuesday);
            case 4:
                return context.getString(C1373R.string.label_wednesday);
            case 5:
                return context.getString(C1373R.string.label_thursday);
            case 6:
                return context.getString(C1373R.string.label_friday);
            case 7:
                return context.getString(C1373R.string.label_saturday);
            default:
                return context.getString(C1373R.string.label_monday);
        }
    }

    /* renamed from: c */
    public static String m12806c(int i, int i2) {
        if (TextUtils.equals(Locale.CHINESE.getLanguage(), Locale.getDefault().getLanguage())) {
            return i + "." + (i2 + 1);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM yyyy", Locale.getDefault());
        Calendar instance = Calendar.getInstance();
        instance.set(i, i2, 1);
        return simpleDateFormat.format(instance.getTime());
    }
}
