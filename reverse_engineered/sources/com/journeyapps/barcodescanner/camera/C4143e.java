package com.journeyapps.barcodescanner.camera;

import android.os.Handler;
import android.os.HandlerThread;

/* compiled from: CameraThread */
/* renamed from: com.journeyapps.barcodescanner.camera.e */
class C4143e {
    /* renamed from: a */
    private static final String f14766a = C4143e.class.getSimpleName();
    /* renamed from: b */
    private static C4143e f14767b;
    /* renamed from: c */
    private Handler f14768c;
    /* renamed from: d */
    private HandlerThread f14769d;
    /* renamed from: e */
    private int f14770e = 0;
    /* renamed from: f */
    private final Object f14771f = new Object();

    /* renamed from: a */
    public static C4143e m16618a() {
        if (f14767b == null) {
            f14767b = new C4143e();
        }
        return f14767b;
    }

    private C4143e() {
    }

    /* renamed from: a */
    protected void m16621a(Runnable runnable) {
        synchronized (this.f14771f) {
            m16619c();
            this.f14768c.post(runnable);
        }
    }

    /* renamed from: c */
    private void m16619c() {
        synchronized (this.f14771f) {
            if (this.f14768c == null) {
                if (this.f14770e <= 0) {
                    throw new IllegalStateException("CameraThread is not open");
                }
                this.f14769d = new HandlerThread("CameraThread");
                this.f14769d.start();
                this.f14768c = new Handler(this.f14769d.getLooper());
            }
        }
    }

    /* renamed from: d */
    private void m16620d() {
        synchronized (this.f14771f) {
            this.f14769d.quit();
            this.f14769d = null;
            this.f14768c = null;
        }
    }

    /* renamed from: b */
    protected void m16622b() {
        synchronized (this.f14771f) {
            this.f14770e--;
            if (this.f14770e == 0) {
                m16620d();
            }
        }
    }

    /* renamed from: b */
    protected void m16623b(Runnable runnable) {
        synchronized (this.f14771f) {
            this.f14770e++;
            m16621a(runnable);
        }
    }
}
