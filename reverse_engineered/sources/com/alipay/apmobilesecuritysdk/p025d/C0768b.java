package com.alipay.apmobilesecuritysdk.p025d;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p027f.C0780h;
import com.alipay.p029b.p030a.p031a.p032a.C0789a;
import com.alipay.sdk.cons.C0845b;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.alipay.apmobilesecuritysdk.d.b */
public final class C0768b {
    /* renamed from: a */
    public static synchronized Map<String, String> m2928a(Context context, Map<String, String> map) {
        Map<String, String> hashMap;
        synchronized (C0768b.class) {
            hashMap = new HashMap();
            String a = C0789a.m3019a(map, C0845b.f2062c, "");
            String a2 = C0789a.m3019a(map, C0845b.f2066g, "");
            String a3 = C0789a.m3019a(map, "userId", "");
            String a4 = C0789a.m3019a(map, "appName", "");
            String a5 = C0789a.m3019a(map, "appKeyClient", "");
            String a6 = C0789a.m3019a(map, "tmxSessionId", "");
            String c = C0780h.m2982c(context);
            hashMap.put("AC1", a);
            hashMap.put("AC2", a2);
            hashMap.put("AC3", "");
            hashMap.put("AC4", c);
            hashMap.put("AC5", a3);
            hashMap.put("AC6", a6);
            hashMap.put("AC7", "");
            hashMap.put("AC8", a4);
            hashMap.put("AC9", a5);
        }
        return hashMap;
    }
}
