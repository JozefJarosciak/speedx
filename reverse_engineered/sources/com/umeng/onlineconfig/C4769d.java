package com.umeng.onlineconfig;

import android.content.Context;
import android.content.SharedPreferences;

/* compiled from: OnlineConfigStoreHelper */
/* renamed from: com.umeng.onlineconfig.d */
public class C4769d {
    /* renamed from: a */
    private static C4769d f16716a = null;
    /* renamed from: b */
    private static Context f16717b = null;
    /* renamed from: c */
    private static String f16718c = null;
    /* renamed from: d */
    private static final String f16719d = "onlineconfig_agent_online_setting_";

    public C4769d(Context context) {
        f16717b = context.getApplicationContext();
        f16718c = context.getPackageName();
    }

    /* renamed from: a */
    public static synchronized C4769d m18741a(Context context) {
        C4769d c4769d;
        synchronized (C4769d.class) {
            if (f16716a == null) {
                f16716a = new C4769d(context);
            }
            c4769d = f16716a;
        }
        return c4769d;
    }

    /* renamed from: a */
    public SharedPreferences m18742a() {
        return f16717b.getSharedPreferences(f16719d + f16718c, 0);
    }
}
