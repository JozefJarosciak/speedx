package com.umeng.analytics;

/* compiled from: SafeRunnable */
/* renamed from: com.umeng.analytics.i */
public abstract class C4747i implements Runnable {
    /* renamed from: a */
    public abstract void mo6180a();

    public void run() {
        try {
            mo6180a();
        } catch (Throwable th) {
            if (th != null) {
                th.printStackTrace();
            }
        }
    }
}
