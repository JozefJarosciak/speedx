package com.alipay.apmobilesecuritysdk.p027f;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p024c.C0766a;
import com.alipay.apmobilesecuritysdk.p028g.C0783a;
import com.alipay.p029b.p030a.p031a.p032a.C0789a;
import org.json.JSONObject;

/* renamed from: com.alipay.apmobilesecuritysdk.f.a */
public final class C0773a {
    /* renamed from: a */
    private static C0774b m2938a(String str) {
        try {
            if (!C0789a.m3020a(str)) {
                JSONObject jSONObject = new JSONObject(str);
                return new C0774b(jSONObject.optString("apdid"), jSONObject.optString("deviceInfoHash"), jSONObject.optString("timestamp"));
            }
        } catch (Throwable e) {
            C0766a.m2926a(e);
        }
        return null;
    }

    /* renamed from: a */
    public static synchronized void m2939a() {
        synchronized (C0773a.class) {
        }
    }

    /* renamed from: a */
    public static synchronized void m2940a(Context context) {
        synchronized (C0773a.class) {
            C0783a.m3004a(context, "vkeyid_profiles_v3", "deviceid", "");
            C0783a.m3005a("wxcasxx_v3", "wxcasxx", "");
        }
    }

    /* renamed from: a */
    public static synchronized void m2941a(Context context, C0774b c0774b) {
        synchronized (C0773a.class) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("apdid", c0774b.m2945a());
                jSONObject.put("deviceInfoHash", c0774b.m2946b());
                jSONObject.put("timestamp", c0774b.m2947c());
                String jSONObject2 = jSONObject.toString();
                C0783a.m3004a(context, "vkeyid_profiles_v3", "deviceid", jSONObject2);
                C0783a.m3005a("wxcasxx_v3", "wxcasxx", jSONObject2);
            } catch (Throwable e) {
                C0766a.m2926a(e);
            }
        }
    }

    /* renamed from: b */
    public static synchronized C0774b m2942b() {
        C0774b a;
        synchronized (C0773a.class) {
            String a2 = C0783a.m3003a("wxcasxx_v3", "wxcasxx");
            a = C0789a.m3020a(a2) ? null : C0773a.m2938a(a2);
        }
        return a;
    }

    /* renamed from: b */
    public static synchronized C0774b m2943b(Context context) {
        C0774b a;
        synchronized (C0773a.class) {
            String a2 = C0783a.m3002a(context, "vkeyid_profiles_v3", "deviceid");
            if (C0789a.m3020a(a2)) {
                a2 = C0783a.m3003a("wxcasxx_v3", "wxcasxx");
            }
            a = C0773a.m2938a(a2);
        }
        return a;
    }

    /* renamed from: c */
    public static synchronized C0774b m2944c(Context context) {
        C0774b a;
        synchronized (C0773a.class) {
            String a2 = C0783a.m3002a(context, "vkeyid_profiles_v3", "deviceid");
            a = C0789a.m3020a(a2) ? null : C0773a.m2938a(a2);
        }
        return a;
    }
}
