package com.alipay.p029b.p030a.p031a.p038d;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import java.util.Map;

/* renamed from: com.alipay.b.a.a.d.c */
public final class C0806c {
    /* renamed from: a */
    public static String m3129a(Context context, String str, String str2, String str3) {
        return context.getSharedPreferences(str, 0).getString(str2, str3);
    }

    /* renamed from: a */
    public static void m3130a(Context context, String str, Map<String, String> map) {
        Editor edit = context.getSharedPreferences(str, 0).edit();
        if (edit != null) {
            edit.clear();
            for (String str2 : map.keySet()) {
                edit.putString(str2, (String) map.get(str2));
            }
            edit.commit();
        }
    }
}
