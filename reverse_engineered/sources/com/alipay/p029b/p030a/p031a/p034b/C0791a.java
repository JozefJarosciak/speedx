package com.alipay.p029b.p030a.p031a.p034b;

import android.content.Context;

/* renamed from: com.alipay.b.a.a.b.a */
public final class C0791a {
    /* renamed from: a */
    private static C0791a f1854a = new C0791a();

    private C0791a() {
    }

    /* renamed from: a */
    public static C0791a m3029a() {
        return f1854a;
    }

    /* renamed from: a */
    public static String m3030a(Context context) {
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 16).versionName;
        } catch (Exception e) {
            return "0.0.0";
        }
    }
}
