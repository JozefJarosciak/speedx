package cn.jpush.android.service;

import android.os.PowerManager.WakeLock;

/* renamed from: cn.jpush.android.service.t */
public final class C0481t {
    /* renamed from: a */
    private static C0481t f900a = null;
    /* renamed from: b */
    private WakeLock f901b = null;

    private C0481t() {
    }

    /* renamed from: a */
    public static C0481t m1538a() {
        if (f900a == null) {
            f900a = new C0481t();
        }
        return f900a;
    }

    /* renamed from: a */
    public final void m1539a(WakeLock wakeLock) {
        this.f901b = wakeLock;
    }

    /* renamed from: b */
    public final WakeLock m1540b() {
        return this.f901b;
    }
}
