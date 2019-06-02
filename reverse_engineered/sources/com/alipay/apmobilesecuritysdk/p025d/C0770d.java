package com.alipay.apmobilesecuritysdk.p025d;

import android.content.Context;
import com.alipay.p029b.p030a.p031a.p034b.C0792b;
import com.alipay.p029b.p030a.p031a.p034b.C0794d;
import com.alipay.sdk.cons.C0844a;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.alipay.apmobilesecuritysdk.d.d */
public final class C0770d {
    /* renamed from: a */
    public static synchronized Map<String, String> m2930a(Context context) {
        Map<String, String> hashMap;
        synchronized (C0770d.class) {
            C0794d.m3078a();
            C0792b.m3031a();
            hashMap = new HashMap();
            hashMap.put("AE1", C0794d.m3081b());
            hashMap.put("AE2", (C0794d.m3082c() ? C0844a.f2048d : "0"));
            hashMap.put("AE3", (C0794d.m3080a(context) ? C0844a.f2048d : "0"));
            hashMap.put("AE4", C0794d.m3083d());
            hashMap.put("AE5", C0794d.m3084e());
            hashMap.put("AE6", C0794d.m3085f());
            hashMap.put("AE7", C0794d.m3086g());
            hashMap.put("AE8", C0794d.m3087h());
            hashMap.put("AE9", C0794d.m3088i());
            hashMap.put("AE10", C0794d.m3089j());
            hashMap.put("AE11", C0794d.m3090k());
            hashMap.put("AE12", C0794d.m3091l());
            hashMap.put("AE13", C0794d.m3092m());
            hashMap.put("AE14", C0794d.m3093n());
            hashMap.put("AE15", C0794d.m3094o());
            hashMap.put("AE21", C0792b.m3043g());
        }
        return hashMap;
    }
}
