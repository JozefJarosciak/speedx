package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzab;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class zzrr implements ThreadFactory {
    private final String Bf;
    private final AtomicInteger Bg;
    private final ThreadFactory Bh;
    private final int mPriority;

    public zzrr(String str) {
        this(str, 0);
    }

    public zzrr(String str, int i) {
        this.Bg = new AtomicInteger();
        this.Bh = Executors.defaultThreadFactory();
        this.Bf = (String) zzab.zzb((Object) str, (Object) "Name must not be null");
        this.mPriority = i;
    }

    public Thread newThread(Runnable runnable) {
        Thread newThread = this.Bh.newThread(new zzrs(runnable, this.mPriority));
        String str = this.Bf;
        newThread.setName(new StringBuilder(String.valueOf(str).length() + 13).append(str).append("[").append(this.Bg.getAndIncrement()).append("]").toString());
        return newThread;
    }
}
