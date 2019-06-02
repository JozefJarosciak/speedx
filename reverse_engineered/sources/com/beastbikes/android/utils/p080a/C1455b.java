package com.beastbikes.android.utils.p080a;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;

/* compiled from: PacketSP */
/* renamed from: com.beastbikes.android.utils.a.b */
public class C1455b {
    /* renamed from: a */
    private static C1455b f6809a = new C1455b();
    /* renamed from: b */
    private SharedPreferences f6810b = null;

    private C1455b() {
    }

    /* renamed from: a */
    public static C1455b m7999a() {
        return f6809a;
    }

    /* renamed from: a */
    private SharedPreferences m7998a(Context context) {
        if (this.f6810b == null) {
            this.f6810b = context.getSharedPreferences(context.getPackageName(), 0);
        }
        return this.f6810b;
    }

    /* renamed from: a */
    public void m8000a(Context context, OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        m7998a(context).registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }

    /* renamed from: b */
    public void m8001b(Context context, OnSharedPreferenceChangeListener onSharedPreferenceChangeListener) {
        m7998a(context).unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener);
    }
}
