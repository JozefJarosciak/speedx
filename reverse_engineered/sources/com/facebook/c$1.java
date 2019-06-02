package com.facebook;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* compiled from: FacebookSdk */
class c$1 implements ThreadFactory {
    /* renamed from: a */
    private final AtomicInteger f13494a = new AtomicInteger(0);

    c$1() {
    }

    public Thread newThread(Runnable runnable) {
        return new Thread(runnable, "FacebookSdk #" + this.f13494a.incrementAndGet());
    }
}
