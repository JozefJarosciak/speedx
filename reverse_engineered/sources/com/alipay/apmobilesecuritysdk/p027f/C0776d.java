package com.alipay.apmobilesecuritysdk.p027f;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p024c.C0766a;
import com.alipay.apmobilesecuritysdk.p028g.C0783a;
import com.alipay.p029b.p030a.p031a.p032a.C0789a;
import com.alipay.sdk.cons.C0845b;
import org.json.JSONObject;

/* renamed from: com.alipay.apmobilesecuritysdk.f.d */
public final class C0776d {
    /* renamed from: a */
    private static C0775c m2953a(String str) {
        try {
            if (!C0789a.m3020a(str)) {
                JSONObject jSONObject = new JSONObject(str);
                return new C0775c(jSONObject.optString("apdid"), jSONObject.optString("deviceInfoHash"), jSONObject.optString("timestamp"), jSONObject.optString(C0845b.f2062c), jSONObject.optString(C0845b.f2066g));
            }
        } catch (Throwable e) {
            C0766a.m2926a(e);
        }
        return null;
    }

    /* renamed from: a */
    public static synchronized void m2954a() {
        synchronized (C0776d.class) {
        }
    }

    /* renamed from: a */
    public static synchronized void m2955a(Context context) {
        synchronized (C0776d.class) {
            C0783a.m3004a(context, "vkeyid_profiles_v4", "key_deviceid_v4", "");
            C0783a.m3005a("wxcasxx_v4", "key_wxcasxx_v4", "");
        }
    }

    /* renamed from: a */
    public static synchronized void m2956a(Context context, C0775c c0775c) {
        synchronized (C0776d.class) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("apdid", c0775c.m2948a());
                jSONObject.put("deviceInfoHash", c0775c.m2949b());
                jSONObject.put("timestamp", c0775c.m2950c());
                jSONObject.put(C0845b.f2062c, c0775c.m2951d());
                jSONObject.put(C0845b.f2066g, c0775c.m2952e());
                String jSONObject2 = jSONObject.toString();
                C0783a.m3004a(context, "vkeyid_profiles_v4", "key_deviceid_v4", jSONObject2);
                C0783a.m3005a("wxcasxx_v4", "key_wxcasxx_v4", jSONObject2);
            } catch (Throwable e) {
                C0766a.m2926a(e);
            }
        }
    }

    /* renamed from: b */
    public static synchronized C0775c m2957b() {
        C0775c a;
        synchronized (C0776d.class) {
            String a2 = C0783a.m3003a("wxcasxx_v4", "key_wxcasxx_v4");
            a = C0789a.m3020a(a2) ? null : C0776d.m2953a(a2);
        }
        return a;
    }

    /* renamed from: b */
    public static synchronized C0775c m2958b(Context context) {
        C0775c a;
        synchronized (C0776d.class) {
            String a2 = C0783a.m3002a(context, "vkeyid_profiles_v4", "key_deviceid_v4");
            if (C0789a.m3020a(a2)) {
                a2 = C0783a.m3003a("wxcasxx_v4", "key_wxcasxx_v4");
            }
            a = C0776d.m2953a(a2);
        }
        return a;
    }

    /* renamed from: c */
    public static synchronized C0775c m2959c(Context context) {
        C0775c a;
        synchronized (C0776d.class) {
            String a2 = C0783a.m3002a(context, "vkeyid_profiles_v4", "key_deviceid_v4");
            a = C0789a.m3020a(a2) ? null : C0776d.m2953a(a2);
        }
        return a;
    }
}
