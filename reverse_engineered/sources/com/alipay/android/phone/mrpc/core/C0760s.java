package com.alipay.android.phone.mrpc.core;

import android.content.Context;

/* renamed from: com.alipay.android.phone.mrpc.core.s */
public final class C0760s {
    /* renamed from: a */
    private static Boolean f1800a = null;

    /* renamed from: a */
    public static final boolean m2910a(Context context) {
        if (f1800a != null) {
            return f1800a.booleanValue();
        }
        try {
            Boolean valueOf = Boolean.valueOf((context.getPackageManager().getApplicationInfo(context.getPackageName(), 0).flags & 2) != 0);
            f1800a = valueOf;
            return valueOf.booleanValue();
        } catch (Exception e) {
            return false;
        }
    }
}
