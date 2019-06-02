package com.alipay.sdk.packet;

import android.text.TextUtils;
import com.alipay.sdk.app.statistic.C0825c;
import com.alipay.sdk.sys.C0869a;
import com.alipay.sdk.util.C0880h;
import com.j256.ormlite.stmt.query.SimpleComparison;

/* renamed from: com.alipay.sdk.packet.a */
public final class C0858a {
    /* renamed from: a */
    public static String m3322a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        String[] split = str.split(C0869a.f2161b);
        if (split.length == 0) {
            return "";
        }
        Object obj = null;
        Object obj2 = null;
        Object obj3 = null;
        Object obj4 = null;
        for (String str2 : split) {
            if (TextUtils.isEmpty(obj4)) {
                obj4 = !str2.contains("biz_type") ? null : C0858a.m3326e(str2);
            }
            if (TextUtils.isEmpty(obj3)) {
                obj3 = !str2.contains("biz_no") ? null : C0858a.m3326e(str2);
            }
            if (TextUtils.isEmpty(obj2)) {
                obj2 = (!str2.contains(C0825c.f1950G) || str2.startsWith(C0825c.f1949F)) ? null : C0858a.m3326e(str2);
            }
            if (TextUtils.isEmpty(obj)) {
                if (str2.contains("app_userid")) {
                    obj = C0858a.m3326e(str2);
                } else {
                    obj = null;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        if (!TextUtils.isEmpty(obj4)) {
            stringBuilder.append("biz_type=" + obj4 + C0880h.f2220b);
        }
        if (!TextUtils.isEmpty(obj3)) {
            stringBuilder.append("biz_no=" + obj3 + C0880h.f2220b);
        }
        if (!TextUtils.isEmpty(obj2)) {
            stringBuilder.append("trade_no=" + obj2 + C0880h.f2220b);
        }
        if (!TextUtils.isEmpty(obj)) {
            stringBuilder.append("app_userid=" + obj + C0880h.f2220b);
        }
        String stringBuilder2 = stringBuilder.toString();
        if (stringBuilder2.endsWith(C0880h.f2220b)) {
            return stringBuilder2.substring(0, stringBuilder2.length() - 1);
        }
        return stringBuilder2;
    }

    /* renamed from: b */
    private static String m3323b(String str) {
        if (str.contains("biz_type")) {
            return C0858a.m3326e(str);
        }
        return null;
    }

    /* renamed from: c */
    private static String m3324c(String str) {
        if (str.contains("biz_no")) {
            return C0858a.m3326e(str);
        }
        return null;
    }

    /* renamed from: d */
    private static String m3325d(String str) {
        if (!str.contains(C0825c.f1950G) || str.startsWith(C0825c.f1949F)) {
            return null;
        }
        return C0858a.m3326e(str);
    }

    /* renamed from: e */
    private static String m3326e(String str) {
        String[] split = str.split(SimpleComparison.EQUAL_TO_OPERATION);
        if (split.length <= 1) {
            return null;
        }
        String str2 = split[1];
        if (str2.contains("\"")) {
            return str2.replaceAll("\"", "");
        }
        return str2;
    }

    /* renamed from: f */
    private static String m3327f(String str) {
        if (str.contains("app_userid")) {
            return C0858a.m3326e(str);
        }
        return null;
    }
}
