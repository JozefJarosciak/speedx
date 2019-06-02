package com.beastbikes.android.utils.p080a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.preference.PreferenceManager;

/* compiled from: DefaultSP */
/* renamed from: com.beastbikes.android.utils.a.a */
public class C1454a {
    /* renamed from: a */
    private static C1454a f6807a = new C1454a();
    /* renamed from: b */
    private SharedPreferences f6808b = null;

    private C1454a() {
    }

    /* renamed from: a */
    public static C1454a m7990a() {
        return f6807a;
    }

    /* renamed from: a */
    private SharedPreferences m7989a(Context context) {
        if (this.f6808b == null) {
            this.f6808b = PreferenceManager.getDefaultSharedPreferences(context);
        }
        return this.f6808b;
    }

    /* renamed from: a */
    public void m7995a(Context context, OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        m7989a(context).registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    /* renamed from: b */
    public void m7997b(Context context, OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        m7989a(context).unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    /* renamed from: a */
    public Editor m7993a(Context context, String str, Object obj) {
        Editor edit = m7989a(context).edit();
        if (obj instanceof String) {
            edit.putString(str, (String) obj);
        } else if (obj instanceof Integer) {
            edit.putInt(str, ((Integer) obj).intValue());
        } else if (obj instanceof Boolean) {
            edit.putBoolean(str, ((Boolean) obj).booleanValue());
        } else if (obj instanceof Float) {
            edit.putFloat(str, ((Float) obj).floatValue());
        } else if (obj instanceof Long) {
            edit.putLong(str, ((Long) obj).longValue());
        } else {
            edit.putString(str, obj.toString());
        }
        return edit;
    }

    /* renamed from: a */
    public int m7991a(Context context, String str, int i) {
        return m7989a(context).getInt(str, i);
    }

    /* renamed from: a */
    public String m7994a(Context context, String str, String str2) {
        return m7989a(context).getString(str, str2);
    }

    /* renamed from: a */
    public boolean m7996a(Context context, String str, boolean z) {
        return m7989a(context).getBoolean(str, z);
    }

    /* renamed from: a */
    public Editor m7992a(Context context, String str) {
        Editor edit = m7989a(context).edit();
        edit.remove(str);
        return edit;
    }
}
