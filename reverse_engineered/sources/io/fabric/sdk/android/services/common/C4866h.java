package io.fabric.sdk.android.services.common;

import android.os.Process;

/* compiled from: BackgroundPriorityRunnable */
/* renamed from: io.fabric.sdk.android.services.common.h */
public abstract class C4866h implements Runnable {
    /* renamed from: a */
    protected abstract void mo6248a();

    public final void run() {
        Process.setThreadPriority(10);
        mo6248a();
    }
}
