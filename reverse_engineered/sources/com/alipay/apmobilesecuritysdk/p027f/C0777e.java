package com.alipay.apmobilesecuritysdk.p027f;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p024c.C0766a;
import com.alipay.apmobilesecuritysdk.p028g.C0783a;
import com.alipay.p029b.p030a.p031a.p032a.C0789a;
import org.json.JSONObject;

/* renamed from: com.alipay.apmobilesecuritysdk.f.e */
public final class C0777e {
    /* renamed from: a */
    public static C0778f m2960a(Context context) {
        if (context == null) {
            return null;
        }
        String a = C0783a.m3002a(context, "device_feature_prefs_name", "device_feature_prefs_key");
        if (C0789a.m3020a(a)) {
            a = C0783a.m3003a("device_feature_file_name", "device_feature_file_key");
        }
        if (C0789a.m3020a(a)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(a);
            C0778f c0778f = new C0778f();
            c0778f.m2962a(jSONObject.getString("imei"));
            c0778f.m2964b(jSONObject.getString("imsi"));
            c0778f.m2966c(jSONObject.getString("mac"));
            c0778f.m2968d(jSONObject.getString("bluetoothmac"));
            c0778f.m2970e(jSONObject.getString("gsi"));
            return c0778f;
        } catch (Throwable e) {
            C0766a.m2926a(e);
            return null;
        }
    }
}
