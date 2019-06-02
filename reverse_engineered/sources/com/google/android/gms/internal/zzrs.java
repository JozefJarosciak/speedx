package com.google.android.gms.internal;

import android.os.Process;

class zzrs implements Runnable {
    private final int mPriority;
    private final Runnable zzw;

    public zzrs(Runnable runnable, int i) {
        this.zzw = runnable;
        this.mPriority = i;
    }

    public void run() {
        Process.setThreadPriority(this.mPriority);
        this.zzw.run();
    }
}
