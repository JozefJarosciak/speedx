package com.tencent.p191a.p192a.p193a.p194a;

import android.content.Context;
import android.provider.Settings.System;
import android.util.Log;

/* renamed from: com.tencent.a.a.a.a.e */
public final class C4398e extends C4394f {
    public C4398e(Context context) {
        super(context);
    }

    /* renamed from: a */
    protected final void mo6050a(String str) {
        synchronized (this) {
            Log.i("MID", "write mid to Settings.System");
            System.putString(this.a.getContentResolver(), C4400h.m17244f("4kU71lN96TJUomD1vOU9lgj9Tw=="), str);
        }
    }

    /* renamed from: a */
    protected final boolean mo6051a() {
        return C4400h.m17240a(this.a, "android.permission.WRITE_SETTINGS");
    }

    /* renamed from: b */
    protected final String mo6052b() {
        String string;
        synchronized (this) {
            Log.i("MID", "read mid from Settings.System");
            string = System.getString(this.a.getContentResolver(), C4400h.m17244f("4kU71lN96TJUomD1vOU9lgj9Tw=="));
        }
        return string;
    }
}
