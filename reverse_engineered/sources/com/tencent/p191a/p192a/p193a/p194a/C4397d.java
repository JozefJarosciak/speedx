package com.tencent.p191a.p192a.p193a.p194a;

import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.util.Log;

/* renamed from: com.tencent.a.a.a.a.d */
final class C4397d extends C4394f {
    public C4397d(Context context) {
        super(context);
    }

    /* renamed from: a */
    protected final void mo6050a(String str) {
        synchronized (this) {
            Log.i("MID", "write mid to sharedPreferences");
            Editor edit = PreferenceManager.getDefaultSharedPreferences(this.a).edit();
            edit.putString(C4400h.m17244f("4kU71lN96TJUomD1vOU9lgj9Tw=="), str);
            edit.commit();
        }
    }

    /* renamed from: a */
    protected final boolean mo6051a() {
        return true;
    }

    /* renamed from: b */
    protected final String mo6052b() {
        String string;
        synchronized (this) {
            Log.i("MID", "read mid from sharedPreferences");
            string = PreferenceManager.getDefaultSharedPreferences(this.a).getString(C4400h.m17244f("4kU71lN96TJUomD1vOU9lgj9Tw=="), null);
        }
        return string;
    }
}
