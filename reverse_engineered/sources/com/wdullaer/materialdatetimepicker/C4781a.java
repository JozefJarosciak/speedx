package com.wdullaer.materialdatetimepicker;

import android.content.Context;
import android.database.ContentObserver;
import android.os.SystemClock;
import android.os.Vibrator;
import android.provider.Settings.System;

/* compiled from: HapticFeedbackController */
/* renamed from: com.wdullaer.materialdatetimepicker.a */
public class C4781a {
    /* renamed from: a */
    private final Context f16731a;
    /* renamed from: b */
    private final ContentObserver f16732b = new ContentObserver(this, null) {
        /* renamed from: a */
        final /* synthetic */ C4781a f16730a;

        public void onChange(boolean z) {
            this.f16730a.f16734d = C4781a.m18759b(this.f16730a.f16731a);
        }
    };
    /* renamed from: c */
    private Vibrator f16733c;
    /* renamed from: d */
    private boolean f16734d;
    /* renamed from: e */
    private long f16735e;

    /* renamed from: b */
    private static boolean m18759b(Context context) {
        return System.getInt(context.getContentResolver(), "haptic_feedback_enabled", 0) == 1;
    }

    public C4781a(Context context) {
        this.f16731a = context;
    }

    /* renamed from: a */
    public void m18761a() {
        if (m18760c(this.f16731a)) {
            this.f16733c = (Vibrator) this.f16731a.getSystemService("vibrator");
        }
        this.f16734d = C4781a.m18759b(this.f16731a);
        this.f16731a.getContentResolver().registerContentObserver(System.getUriFor("haptic_feedback_enabled"), false, this.f16732b);
    }

    /* renamed from: c */
    private boolean m18760c(Context context) {
        return context.getPackageManager().checkPermission("android.permission.VIBRATE", context.getPackageName()) == 0;
    }

    /* renamed from: b */
    public void m18762b() {
        this.f16733c = null;
        this.f16731a.getContentResolver().unregisterContentObserver(this.f16732b);
    }

    /* renamed from: c */
    public void m18763c() {
        if (this.f16733c != null && this.f16734d) {
            long uptimeMillis = SystemClock.uptimeMillis();
            if (uptimeMillis - this.f16735e >= 125) {
                this.f16733c.vibrate(50);
                this.f16735e = uptimeMillis;
            }
        }
    }
}
