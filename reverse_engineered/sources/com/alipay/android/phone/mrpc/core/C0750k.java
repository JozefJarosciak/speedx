package com.alipay.android.phone.mrpc.core;

import android.text.format.Time;
import ch.qos.logback.core.CoreConstants;
import com.baidu.mapapi.UIMsg.m_AppUI;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* renamed from: com.alipay.android.phone.mrpc.core.k */
public final class C0750k {
    /* renamed from: a */
    private static final Pattern f1753a = Pattern.compile("([0-9]{1,2})[- ]([A-Za-z]{3,9})[- ]([0-9]{2,4})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])");
    /* renamed from: b */
    private static final Pattern f1754b = Pattern.compile("[ ]([A-Za-z]{3,9})[ ]+([0-9]{1,2})[ ]([0-9]{1,2}:[0-9][0-9]:[0-9][0-9])[ ]([0-9]{2,4})");

    /* renamed from: com.alipay.android.phone.mrpc.core.k$a */
    private static class C0749a {
        /* renamed from: a */
        int f1750a;
        /* renamed from: b */
        int f1751b;
        /* renamed from: c */
        int f1752c;

        C0749a(int i, int i2, int i3) {
            this.f1750a = i;
            this.f1751b = i2;
            this.f1752c = i3;
        }
    }

    /* renamed from: a */
    public static long m2862a(String str) {
        int c;
        int d;
        C0749a e;
        int i;
        int i2 = 1;
        Matcher matcher = f1753a.matcher(str);
        int b;
        if (matcher.find()) {
            b = C0750k.m2863b(matcher.group(1));
            c = C0750k.m2864c(matcher.group(2));
            d = C0750k.m2865d(matcher.group(3));
            e = C0750k.m2866e(matcher.group(4));
            i = b;
        } else {
            Matcher matcher2 = f1754b.matcher(str);
            if (matcher2.find()) {
                c = C0750k.m2864c(matcher2.group(1));
                b = C0750k.m2863b(matcher2.group(2));
                C0749a e2 = C0750k.m2866e(matcher2.group(3));
                d = C0750k.m2865d(matcher2.group(4));
                e = e2;
                i = b;
            } else {
                throw new IllegalArgumentException();
            }
        }
        if (d >= 2038) {
            d = 2038;
            c = 0;
        } else {
            i2 = i;
        }
        Time time = new Time("UTC");
        time.set(e.f1752c, e.f1751b, e.f1750a, i2, c, d);
        return time.toMillis(false);
    }

    /* renamed from: b */
    private static int m2863b(String str) {
        return str.length() == 2 ? ((str.charAt(0) - 48) * 10) + (str.charAt(1) - 48) : str.charAt(0) - 48;
    }

    /* renamed from: c */
    private static int m2864c(String str) {
        switch (((Character.toLowerCase(str.charAt(0)) + Character.toLowerCase(str.charAt(1))) + Character.toLowerCase(str.charAt(2))) - 291) {
            case 9:
                return 11;
            case 10:
                return 1;
            case 22:
                return 0;
            case 26:
                return 7;
            case 29:
                return 2;
            case 32:
                return 3;
            case 35:
                return 9;
            case 36:
                return 4;
            case 37:
                return 8;
            case 40:
                return 6;
            case 42:
                return 5;
            case 48:
                return 10;
            default:
                throw new IllegalArgumentException();
        }
    }

    /* renamed from: d */
    private static int m2865d(String str) {
        if (str.length() != 2) {
            return str.length() == 3 ? ((((str.charAt(0) - 48) * 100) + ((str.charAt(1) - 48) * 10)) + (str.charAt(2) - 48)) + 1900 : str.length() == 4 ? ((((str.charAt(0) - 48) * 1000) + ((str.charAt(1) - 48) * 100)) + ((str.charAt(2) - 48) * 10)) + (str.charAt(3) - 48) : 1970;
        } else {
            int charAt = ((str.charAt(0) - 48) * 10) + (str.charAt(1) - 48);
            return charAt >= 70 ? charAt + 1900 : charAt + m_AppUI.MSG_APP_DATA_OK;
        }
    }

    /* renamed from: e */
    private static C0749a m2866e(String str) {
        int i;
        int charAt = str.charAt(0) - 48;
        if (str.charAt(1) != CoreConstants.COLON_CHAR) {
            i = 2;
            charAt = (charAt * 10) + (str.charAt(1) - 48);
        } else {
            i = 1;
        }
        i++;
        int i2 = i + 1;
        i = ((str.charAt(i) - 48) * 10) + (str.charAt(i2) - 48);
        i2 = (i2 + 1) + 1;
        return new C0749a(charAt, i, ((str.charAt(i2) - 48) * 10) + (str.charAt(i2 + 1) - 48));
    }
}
