package io.fabric.sdk.android.services.common;

import android.os.SystemClock;
import android.util.Log;

/* compiled from: TimingMetric */
/* renamed from: io.fabric.sdk.android.services.common.q */
public class C4892q {
    /* renamed from: a */
    private final String f17203a;
    /* renamed from: b */
    private final String f17204b;
    /* renamed from: c */
    private final boolean f17205c;
    /* renamed from: d */
    private long f17206d;
    /* renamed from: e */
    private long f17207e;

    public C4892q(String str, String str2) {
        this.f17203a = str;
        this.f17204b = str2;
        this.f17205c = !Log.isLoggable(str2, 2);
    }

    /* renamed from: a */
    public synchronized void m19226a() {
        if (!this.f17205c) {
            this.f17206d = SystemClock.elapsedRealtime();
            this.f17207e = 0;
        }
    }

    /* renamed from: b */
    public synchronized void m19227b() {
        if (!this.f17205c) {
            if (this.f17207e == 0) {
                this.f17207e = SystemClock.elapsedRealtime() - this.f17206d;
                m19225c();
            }
        }
    }

    /* renamed from: c */
    private void m19225c() {
        Log.v(this.f17204b, this.f17203a + ": " + this.f17207e + "ms");
    }
}
