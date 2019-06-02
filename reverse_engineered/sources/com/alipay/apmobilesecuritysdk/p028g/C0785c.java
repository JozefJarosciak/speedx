package com.alipay.apmobilesecuritysdk.p028g;

import android.os.Process;

/* renamed from: com.alipay.apmobilesecuritysdk.g.c */
final class C0785c implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C0784b f1850a;

    C0785c(C0784b c0784b) {
        this.f1850a = c0784b;
    }

    public final void run() {
        try {
            Process.setThreadPriority(0);
            while (!this.f1850a.f1849c.isEmpty()) {
                Runnable runnable = (Runnable) this.f1850a.f1849c.pollFirst();
                if (runnable != null) {
                    runnable.run();
                }
            }
        } catch (Exception e) {
        } finally {
            this.f1850a.f1848b = null;
        }
    }
}
