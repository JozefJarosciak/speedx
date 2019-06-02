package com.tencent.wxop.stat.common;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

/* renamed from: com.tencent.wxop.stat.common.p */
public class C4544p {
    /* renamed from: a */
    private static SharedPreferences f16116a = null;

    /* renamed from: a */
    public static int m18090a(Context context, String str, int i) {
        return C4544p.m18092a(context).getInt(C4539k.m18045a(context, new StringBuilder(StatConstants.MTA_COOPERATION_TAG).append(str).toString()), i);
    }

    /* renamed from: a */
    public static long m18091a(Context context, String str, long j) {
        return C4544p.m18092a(context).getLong(C4539k.m18045a(context, new StringBuilder(StatConstants.MTA_COOPERATION_TAG).append(str).toString()), j);
    }

    /* renamed from: a */
    static synchronized SharedPreferences m18092a(Context context) {
        SharedPreferences sharedPreferences;
        synchronized (C4544p.class) {
            sharedPreferences = context.getSharedPreferences(".mta-wxop", 0);
            f16116a = sharedPreferences;
            if (sharedPreferences == null) {
                f16116a = PreferenceManager.getDefaultSharedPreferences(context);
            }
            sharedPreferences = f16116a;
        }
        return sharedPreferences;
    }

    /* renamed from: a */
    public static String m18093a(Context context, String str, String str2) {
        return C4544p.m18092a(context).getString(C4539k.m18045a(context, new StringBuilder(StatConstants.MTA_COOPERATION_TAG).append(str).toString()), str2);
    }

    /* renamed from: b */
    public static void m18094b(Context context, String str, int i) {
        String a = C4539k.m18045a(context, new StringBuilder(StatConstants.MTA_COOPERATION_TAG).append(str).toString());
        Editor edit = C4544p.m18092a(context).edit();
        edit.putInt(a, i);
        edit.commit();
    }

    /* renamed from: b */
    public static void m18095b(Context context, String str, long j) {
        String a = C4539k.m18045a(context, new StringBuilder(StatConstants.MTA_COOPERATION_TAG).append(str).toString());
        Editor edit = C4544p.m18092a(context).edit();
        edit.putLong(a, j);
        edit.commit();
    }

    /* renamed from: b */
    public static void m18096b(Context context, String str, String str2) {
        String a = C4539k.m18045a(context, new StringBuilder(StatConstants.MTA_COOPERATION_TAG).append(str).toString());
        Editor edit = C4544p.m18092a(context).edit();
        edit.putString(a, str2);
        edit.commit();
    }
}
