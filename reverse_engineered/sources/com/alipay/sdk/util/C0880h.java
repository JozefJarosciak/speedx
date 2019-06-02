package com.alipay.sdk.util;

import android.content.Context;
import android.text.TextUtils;
import com.alipay.sdk.app.statistic.C0823a;
import com.alipay.sdk.app.statistic.C0825c;
import com.alipay.sdk.sys.C0869a;

/* renamed from: com.alipay.sdk.util.h */
public final class C0880h {
    /* renamed from: a */
    public static final String f2219a = "pref_trade_token";
    /* renamed from: b */
    public static final String f2220b = ";";
    /* renamed from: c */
    public static final String f2221c = "result={";
    /* renamed from: d */
    public static final String f2222d = "}";
    /* renamed from: e */
    public static final String f2223e = "trade_token=\"";
    /* renamed from: f */
    public static final String f2224f = "\"";
    /* renamed from: g */
    public static final String f2225g = "trade_token=";

    /* renamed from: a */
    private static void m3447a(Context context, String str) {
        Object obj = null;
        try {
            if (!TextUtils.isEmpty(str)) {
                String[] split = str.split(f2220b);
                int i = 0;
                while (i < split.length) {
                    if (split[i].startsWith(f2221c) && split[i].endsWith(f2222d)) {
                        String[] split2 = split[i].substring(8, split[i].length() - 1).split(C0869a.f2161b);
                        int i2 = 0;
                        while (i2 < split2.length) {
                            if (split2[i2].startsWith(f2223e) && split2[i2].endsWith("\"")) {
                                obj = split2[i2].substring(13, split2[i2].length() - 1);
                                break;
                            } else if (split2[i2].startsWith(f2225g)) {
                                obj = split2[i2].substring(12);
                                break;
                            } else {
                                i2++;
                            }
                        }
                    }
                    i++;
                }
            }
            if (!TextUtils.isEmpty(obj)) {
                C0881i.m3450a(context, f2219a, obj);
            }
        } catch (Throwable th) {
            C0823a.m3185a(C0825c.f1952b, C0825c.f1975y, th);
        }
    }

    /* renamed from: a */
    private static String m3446a(String str) {
        String str2 = null;
        if (!TextUtils.isEmpty(str)) {
            String[] split = str.split(f2220b);
            int i = 0;
            while (i < split.length) {
                if (split[i].startsWith(f2221c) && split[i].endsWith(f2222d)) {
                    String[] split2 = split[i].substring(8, split[i].length() - 1).split(C0869a.f2161b);
                    int i2 = 0;
                    while (i2 < split2.length) {
                        if (split2[i2].startsWith(f2223e) && split2[i2].endsWith("\"")) {
                            str2 = split2[i2].substring(13, split2[i2].length() - 1);
                            break;
                        } else if (split2[i2].startsWith(f2225g)) {
                            str2 = split2[i2].substring(12);
                            break;
                        } else {
                            i2++;
                        }
                    }
                }
                i++;
            }
        }
        return str2;
    }

    /* renamed from: a */
    private static String m3445a(Context context) {
        return C0881i.m3451b(context, f2219a, "");
    }
}
