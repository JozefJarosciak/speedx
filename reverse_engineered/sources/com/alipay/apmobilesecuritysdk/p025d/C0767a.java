package com.alipay.apmobilesecuritysdk.p025d;

import android.content.Context;
import com.alipay.p029b.p030a.p031a.p032a.C0789a;
import com.alipay.p029b.p030a.p031a.p034b.C0791a;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.alipay.apmobilesecuritysdk.d.a */
public final class C0767a {
    /* renamed from: a */
    public static synchronized Map<String, String> m2927a(Context context, Map<String, String> map) {
        Map<String, String> hashMap;
        synchronized (C0767a.class) {
            String a = C0789a.m3019a(map, "appchannel", "");
            hashMap = new HashMap();
            hashMap.put("AA1", context.getPackageName());
            C0791a.m3029a();
            hashMap.put("AA2", C0791a.m3030a(context));
            hashMap.put("AA3", "security-sdk-token");
            hashMap.put("AA4", "3.2.0-20160621");
            hashMap.put("AA6", a);
        }
        return hashMap;
    }
}
