package com.alipay.sdk.util;

import android.content.Context;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import com.alipay.sdk.app.statistic.C0823a;
import com.alipay.sdk.app.statistic.C0825c;
import com.alipay.sdk.encrypt.C0855e;

/* renamed from: com.alipay.sdk.util.i */
public final class C0881i {
    /* renamed from: a */
    private static String f2226a = null;

    /* renamed from: b */
    private static boolean m3452b(Context context, String str) {
        boolean z = false;
        try {
            z = PreferenceManager.getDefaultSharedPreferences(context).contains(str);
        } catch (Throwable th) {
        }
        return z;
    }

    /* renamed from: a */
    public static void m3449a(Context context, String str) {
        try {
            PreferenceManager.getDefaultSharedPreferences(context).edit().remove(str).commit();
        } catch (Throwable th) {
        }
    }

    /* renamed from: a */
    public static void m3450a(Context context, String str, String str2) {
        try {
            Object a = C0855e.m3294a(C0881i.m3448a(context), str2);
            if (!TextUtils.isEmpty(str2) && TextUtils.isEmpty(a)) {
                C0823a.m3184a(C0825c.f1953c, C0825c.f1973w, String.format("%s,%s", new Object[]{str, str2}));
            }
            PreferenceManager.getDefaultSharedPreferences(context).edit().putString(str, a).commit();
        } catch (Throwable th) {
        }
    }

    /* renamed from: b */
    public static String m3451b(Context context, String str, String str2) {
        String str3 = null;
        try {
            String string = PreferenceManager.getDefaultSharedPreferences(context).getString(str, str2);
            if (!TextUtils.isEmpty(string)) {
                str3 = C0855e.m3296b(C0881i.m3448a(context), string);
            }
            if (!TextUtils.isEmpty(string) && TextUtils.isEmpty(r0)) {
                C0823a.m3184a(C0825c.f1953c, C0825c.f1972v, String.format("%s,%s", new Object[]{str, string}));
            }
        } catch (Exception e) {
        }
        return str3;
    }

    /* renamed from: a */
    private static String m3448a(Context context) {
        if (TextUtils.isEmpty(f2226a)) {
            String str = "";
            try {
                str = context.getApplicationContext().getPackageName();
            } catch (Throwable th) {
            }
            f2226a = (str + "0000000000000000000000000000").substring(0, 24);
        }
        return f2226a;
    }
}
