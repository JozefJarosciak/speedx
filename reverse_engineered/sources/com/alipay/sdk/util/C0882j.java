package com.alipay.sdk.util;

import com.alipay.sdk.app.C0822i;
import com.alipay.sdk.app.statistic.C0823a;
import com.alipay.sdk.app.statistic.C0825c;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.alipay.sdk.util.j */
public final class C0882j {
    /* renamed from: a */
    public static final String f2227a = "resultStatus";
    /* renamed from: b */
    public static final String f2228b = "memo";
    /* renamed from: c */
    public static final String f2229c = "result";

    /* renamed from: a */
    public static Map<String, String> m3455a(String str) {
        C0822i a = C0822i.m3178a(C0822i.CANCELED.f1938h);
        Map<String, String> hashMap = new HashMap();
        hashMap.put(f2227a, Integer.toString(a.f1938h));
        hashMap.put(f2228b, a.f1939i);
        hashMap.put(f2229c, "");
        try {
            hashMap = C0882j.m3456b(str);
        } catch (Throwable th) {
            C0823a.m3185a(C0825c.f1952b, C0825c.f1956f, th);
        }
        return hashMap;
    }

    /* renamed from: a */
    private static Map<String, String> m3454a() {
        C0822i a = C0822i.m3178a(C0822i.CANCELED.f1938h);
        Map<String, String> hashMap = new HashMap();
        hashMap.put(f2227a, Integer.toString(a.f1938h));
        hashMap.put(f2228b, a.f1939i);
        hashMap.put(f2229c, "");
        return hashMap;
    }

    /* renamed from: b */
    private static Map<String, String> m3456b(String str) {
        String[] split = str.split(C0880h.f2220b);
        Map<String, String> hashMap = new HashMap();
        for (String str2 : split) {
            String substring = str2.substring(0, str2.indexOf("={"));
            String str3 = substring + "={";
            hashMap.put(substring, str2.substring(str3.length() + str2.indexOf(str3), str2.lastIndexOf(C0880h.f2222d)));
        }
        return hashMap;
    }

    /* renamed from: a */
    private static String m3453a(String str, String str2) {
        String str3 = str2 + "={";
        return str.substring(str3.length() + str.indexOf(str3), str.lastIndexOf(C0880h.f2222d));
    }
}
