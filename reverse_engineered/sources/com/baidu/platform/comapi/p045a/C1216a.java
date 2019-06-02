package com.baidu.platform.comapi.p045a;

import android.content.Context;

/* renamed from: com.baidu.platform.comapi.a.a */
public class C1216a {
    /* renamed from: a */
    private static int f3601a = 621133959;

    /* renamed from: a */
    public static boolean m4555a(Context context) {
        return C1216a.m4557c(context);
    }

    /* renamed from: b */
    private static int m4556b(Context context) {
        int i = 0;
        try {
            i = context.getPackageManager().getPackageInfo("com.baidu.BaiduMap", 64).signatures[0].hashCode();
        } catch (Exception e) {
        }
        return i;
    }

    /* renamed from: c */
    private static boolean m4557c(Context context) {
        return C1216a.m4556b(context) == f3601a;
    }
}
