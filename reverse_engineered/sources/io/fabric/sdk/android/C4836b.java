package io.fabric.sdk.android;

import android.util.Log;

/* compiled from: DefaultLogger */
/* renamed from: io.fabric.sdk.android.b */
public class C4836b implements C4835k {
    /* renamed from: a */
    private int f17071a;

    public C4836b(int i) {
        this.f17071a = i;
    }

    public C4836b() {
        this.f17071a = 4;
    }

    /* renamed from: a */
    public boolean mo6217a(String str, int i) {
        return this.f17071a <= i;
    }

    /* renamed from: a */
    public void mo6216a(String str, String str2, Throwable th) {
        if (mo6217a(str, 3)) {
            Log.d(str, str2, th);
        }
    }

    /* renamed from: b */
    public void m18999b(String str, String str2, Throwable th) {
        if (mo6217a(str, 2)) {
            Log.v(str, str2, th);
        }
    }

    /* renamed from: c */
    public void mo6220c(String str, String str2, Throwable th) {
        if (mo6217a(str, 5)) {
            Log.w(str, str2, th);
        }
    }

    /* renamed from: d */
    public void mo6222d(String str, String str2, Throwable th) {
        if (mo6217a(str, 6)) {
            Log.e(str, str2, th);
        }
    }

    /* renamed from: a */
    public void mo6215a(String str, String str2) {
        mo6216a(str, str2, null);
    }

    /* renamed from: b */
    public void mo6218b(String str, String str2) {
        m18999b(str, str2, null);
    }

    /* renamed from: c */
    public void mo6219c(String str, String str2) {
        mo6220c(str, str2, null);
    }

    /* renamed from: d */
    public void mo6221d(String str, String str2) {
        mo6222d(str, str2, null);
    }

    /* renamed from: a */
    public void mo6214a(int i, String str, String str2) {
        m18994a(i, str, str2, false);
    }

    /* renamed from: a */
    public void m18994a(int i, String str, String str2, boolean z) {
        if (z || mo6217a(str, i)) {
            Log.println(i, str, str2);
        }
    }
}
