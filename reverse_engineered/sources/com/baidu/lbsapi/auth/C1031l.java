package com.baidu.lbsapi.auth;

import android.os.Handler;
import android.os.Looper;

/* renamed from: com.baidu.lbsapi.auth.l */
class C1031l extends Thread {
    /* renamed from: a */
    Handler f2298a = null;
    /* renamed from: b */
    private Object f2299b = new Object();
    /* renamed from: c */
    private boolean f2300c = false;

    C1031l() {
    }

    C1031l(String str) {
        super(str);
    }

    /* renamed from: a */
    public void m3628a() {
        if (C1017a.f2273a) {
            C1017a.m3589a("Looper thread quit()");
        }
        this.f2298a.getLooper().quit();
    }

    /* renamed from: b */
    public void m3629b() {
        synchronized (this.f2299b) {
            try {
                if (!this.f2300c) {
                    this.f2299b.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /* renamed from: c */
    public void m3630c() {
        synchronized (this.f2299b) {
            this.f2300c = true;
            this.f2299b.notifyAll();
        }
    }

    public void run() {
        Looper.prepare();
        this.f2298a = new Handler();
        if (C1017a.f2273a) {
            C1017a.m3589a("new Handler() finish!!");
        }
        Looper.loop();
        if (C1017a.f2273a) {
            C1017a.m3589a("LooperThread run() thread id:" + String.valueOf(Thread.currentThread().getId()));
        }
    }
}
