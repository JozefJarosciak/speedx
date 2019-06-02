package com.alipay.apmobilesecuritysdk.p027f;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import com.alipay.p029b.p030a.p031a.p032a.C0789a;
import com.alipay.p029b.p030a.p031a.p032a.p033a.C0788c;
import com.alipay.p029b.p030a.p031a.p038d.C0806c;

/* renamed from: com.alipay.apmobilesecuritysdk.f.g */
public final class C0779g {
    /* renamed from: a */
    public static synchronized String m2971a(Context context, String str) {
        String a;
        synchronized (C0779g.class) {
            a = C0806c.m3129a(context, "openapi_file_pri", "openApi" + str, "");
            if (C0789a.m3020a(a)) {
                a = "";
            } else {
                a = C0788c.m3015b(C0788c.m3012a(), a);
                if (C0789a.m3020a(a)) {
                    a = "";
                }
            }
        }
        return a;
    }

    /* renamed from: a */
    public static synchronized void m2972a() {
        synchronized (C0779g.class) {
        }
    }

    /* renamed from: a */
    public static synchronized void m2973a(Context context) {
        synchronized (C0779g.class) {
            Editor edit = context.getSharedPreferences("openapi_file_pri", 0).edit();
            if (edit != null) {
                edit.clear();
                edit.commit();
            }
        }
    }

    /* renamed from: a */
    public static synchronized void m2974a(Context context, String str, String str2) {
        synchronized (C0779g.class) {
            try {
                Editor edit = context.getSharedPreferences("openapi_file_pri", 0).edit();
                if (edit != null) {
                    edit.putString("openApi" + str, C0788c.m3013a(C0788c.m3012a(), str2));
                    edit.commit();
                }
            } catch (Throwable th) {
            }
        }
    }
}
