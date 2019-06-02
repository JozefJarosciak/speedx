package com.alipay.apmobilesecuritysdk.p025d;

import android.content.Context;
import com.alipay.apmobilesecuritysdk.p024c.C0766a;
import com.alipay.apmobilesecuritysdk.p027f.C0777e;
import com.alipay.apmobilesecuritysdk.p027f.C0778f;
import com.alipay.apmobilesecuritysdk.p028g.C0783a;
import com.alipay.p029b.p030a.p031a.p032a.C0789a;
import com.alipay.p029b.p030a.p031a.p034b.C0792b;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.alipay.apmobilesecuritysdk.d.c */
public final class C0769c {
    /* renamed from: a */
    public static Map<String, String> m2929a(Context context) {
        String str;
        C0792b a = C0792b.m3031a();
        Map<String, String> hashMap = new HashMap();
        C0778f a2 = C0777e.m2960a(context);
        String a3 = C0792b.m3032a(context);
        String b = C0792b.m3035b(context);
        String l = C0792b.m3054l(context);
        String o = C0792b.m3060o(context);
        String n = C0792b.m3058n(context);
        if (a2 != null) {
            if (C0789a.m3020a(a3)) {
                a3 = a2.m2961a();
            }
            if (C0789a.m3020a(b)) {
                b = a2.m2963b();
            }
            if (C0789a.m3020a(l)) {
                l = a2.m2965c();
            }
            if (C0789a.m3020a(o)) {
                o = a2.m2967d();
            }
            if (C0789a.m3020a(n)) {
                n = a2.m2969e();
            }
            str = n;
            n = o;
            o = l;
            l = b;
            b = a3;
        } else {
            str = n;
            n = o;
            o = l;
            l = b;
            b = a3;
        }
        C0778f c0778f = new C0778f(b, l, o, n, str);
        if (context != null) {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("imei", c0778f.m2961a());
                jSONObject.put("imsi", c0778f.m2963b());
                jSONObject.put("mac", c0778f.m2965c());
                jSONObject.put("bluetoothmac", c0778f.m2967d());
                jSONObject.put("gsi", c0778f.m2969e());
                a3 = jSONObject.toString();
                C0783a.m3005a("device_feature_file_name", "device_feature_file_key", a3);
                C0783a.m3004a(context, "device_feature_prefs_name", "device_feature_prefs_key", a3);
            } catch (Throwable e) {
                C0766a.m2926a(e);
            }
        }
        hashMap.put("AD1", b);
        hashMap.put("AD2", l);
        hashMap.put("AD3", C0792b.m3044g(context));
        hashMap.put("AD5", C0792b.m3048i(context));
        hashMap.put("AD6", C0792b.m3050j(context));
        hashMap.put("AD7", C0792b.m3052k(context));
        hashMap.put("AD8", o);
        hashMap.put("AD9", C0792b.m3056m(context));
        hashMap.put("AD10", str);
        hashMap.put("AD11", C0792b.m3038d());
        hashMap.put("AD12", a.m3077e());
        hashMap.put("AD13", C0792b.m3041f());
        hashMap.put("AD14", C0792b.m3045h());
        hashMap.put("AD15", C0792b.m3047i());
        hashMap.put("AD16", C0792b.m3049j());
        hashMap.put("AD17", "");
        hashMap.put("AD18", n);
        hashMap.put("AD19", C0792b.m3062p(context));
        hashMap.put("AD20", C0792b.m3051k());
        hashMap.put("AD21", C0792b.m3042f(context));
        hashMap.put("AD22", "");
        hashMap.put("AD23", C0792b.m3053l());
        hashMap.put("AD24", C0789a.m3027f(C0792b.m3046h(context)));
        hashMap.put("AD26", C0792b.m3040e(context));
        hashMap.put("AD27", C0792b.m3063q());
        hashMap.put("AD28", C0792b.m3067s());
        hashMap.put("AD29", C0792b.m3071u());
        hashMap.put("AD30", C0792b.m3065r());
        hashMap.put("AD31", C0792b.m3069t());
        hashMap.put("AD32", C0792b.m3059o());
        hashMap.put("AD33", C0792b.m3061p());
        hashMap.put("AD34", C0792b.m3068s(context));
        hashMap.put("AD35", C0792b.m3070t(context));
        hashMap.put("AD36", C0792b.m3066r(context));
        hashMap.put("AD37", C0792b.m3057n());
        hashMap.put("AD38", C0792b.m3055m());
        hashMap.put("AD39", C0792b.m3037c(context));
        hashMap.put("AD40", C0792b.m3039d(context));
        hashMap.put("AD41", C0792b.m3034b());
        hashMap.put("AD42", C0792b.m3036c());
        hashMap.put("AL3", C0792b.m3064q(context));
        return hashMap;
    }
}
