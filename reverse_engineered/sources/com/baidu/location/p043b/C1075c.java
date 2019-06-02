package com.baidu.location.p043b;

import android.support.v4.os.EnvironmentCompat;

/* renamed from: com.baidu.location.b.c */
public final class C1075c {
    /* renamed from: a */
    public static String m3887a(int i) {
        if (C1085h.m3963h()) {
            return "WIFI";
        }
        switch (i) {
            case 1:
            case 2:
            case 4:
            case 7:
            case 11:
                return "2G";
            case 3:
            case 5:
            case 6:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
                return "3G";
            case 13:
                return "4G";
            default:
                return EnvironmentCompat.MEDIA_UNKNOWN;
        }
    }
}
