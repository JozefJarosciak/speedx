package com.umeng.analytics;

import android.content.Context;
import android.text.TextUtils;

/* compiled from: InternalConfig */
/* renamed from: com.umeng.analytics.g */
public class C4753g {
    /* renamed from: a */
    private static String[] f16659a = new String[2];

    /* renamed from: a */
    public static String[] m18669a(Context context) {
        if (!TextUtils.isEmpty(f16659a[0]) && !TextUtils.isEmpty(f16659a[1])) {
            return f16659a;
        }
        if (context != null) {
            String[] a = C4762j.m18682a(context).m18696a();
            if (a != null) {
                f16659a[0] = a[0];
                f16659a[1] = a[1];
                return f16659a;
            }
        }
        return null;
    }
}
