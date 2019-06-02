package com.alipay.apmobilesecuritysdk.p027f;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.alipay.p029b.p030a.p031a.p032a.C0789a;
import com.alipay.p029b.p030a.p031a.p032a.p033a.C0787b;
import com.alipay.p029b.p030a.p031a.p032a.p033a.C0788c;
import com.alipay.p029b.p030a.p031a.p038d.C0806c;
import com.alipay.sdk.cons.C0844a;
import java.util.UUID;

/* renamed from: com.alipay.apmobilesecuritysdk.f.h */
public final class C0780h {
    /* renamed from: a */
    private static String f1833a = "";

    /* renamed from: a */
    public static String m2975a(Context context) {
        try {
            String a = C0806c.m3129a(context, "vkeyid_settings", "last_apdid_env", "");
            if (C0789a.m3020a(a)) {
                return "";
            }
            a = C0788c.m3015b(C0788c.m3012a(), a);
            return C0789a.m3020a(a) ? "" : a;
        } catch (Throwable th) {
            return "";
        }
    }

    /* renamed from: a */
    public static void m2976a(Context context, String str) {
        try {
            Editor edit = context.getSharedPreferences("vkeyid_settings", 0).edit();
            if (edit != null) {
                edit.putString("last_apdid_env", C0788c.m3013a(C0788c.m3012a(), str));
                edit.commit();
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public static void m2977a(Context context, String str, long j) {
        try {
            Editor edit = context.getSharedPreferences("vkeyid_settings", 0).edit();
            if (edit != null) {
                edit.putString("vkey_valid" + str, C0788c.m3013a(C0788c.m3012a(), String.valueOf(j)));
                edit.commit();
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public static void m2978a(Context context, boolean z) {
        try {
            Editor edit = context.getSharedPreferences("vkeyid_settings", 0).edit();
            if (edit != null) {
                edit.putString("log_switch", z ? C0788c.m3013a(C0788c.m3012a(), C0844a.f2048d) : C0788c.m3013a(C0788c.m3012a(), "0"));
                edit.commit();
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    public static void m2979b(Context context, String str) {
        try {
            Editor edit = context.getSharedPreferences("vkeyid_settings", 0).edit();
            if (edit != null) {
                edit.putString("agent_switch", C0788c.m3013a(C0788c.m3012a(), str));
                edit.commit();
            }
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    public static boolean m2980b(Context context) {
        boolean z = false;
        try {
            String a = C0806c.m3129a(context, "vkeyid_settings", "log_switch", "");
            if (!C0789a.m3020a(a)) {
                a = C0788c.m3015b(C0788c.m3012a(), a);
                if (!C0789a.m3020a(a)) {
                    z = a.equals(C0844a.f2048d);
                }
            }
        } catch (Throwable th) {
        }
        return z;
    }

    /* renamed from: c */
    public static long m2981c(Context context, String str) {
        long j = 0;
        try {
            String string = context.getSharedPreferences("vkeyid_settings", 0).getString("vkey_valid" + str, "");
            if (!C0789a.m3020a(string)) {
                string = C0788c.m3015b(C0788c.m3012a(), string);
                if (!C0789a.m3020a(string)) {
                    j = Long.parseLong(string);
                }
            }
        } catch (Throwable th) {
        }
        return j;
    }

    /* renamed from: c */
    public static synchronized String m2982c(Context context) {
        String a;
        synchronized (C0780h.class) {
            if (C0789a.m3020a(f1833a)) {
                a = C0806c.m3129a(context, "alipay_vkey_random", "random", "");
                f1833a = a;
                if (C0789a.m3020a(a)) {
                    f1833a = C0787b.m3011a(UUID.randomUUID().toString());
                    a = "alipay_vkey_random";
                    String str = "random";
                    String str2 = f1833a;
                    if (str2 != null) {
                        Editor edit = context.getSharedPreferences(a, 0).edit();
                        if (edit != null) {
                            edit.clear();
                            edit.putString(str, str2);
                            edit.commit();
                        }
                    }
                }
            }
            a = f1833a;
        }
        return a;
    }
}
