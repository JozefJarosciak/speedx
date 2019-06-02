package com.alipay.android.phone.mrpc.core;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* renamed from: com.alipay.android.phone.mrpc.core.n */
final class C0753n implements ThreadFactory {
    /* renamed from: a */
    private final AtomicInteger f1766a = new AtomicInteger(1);

    C0753n() {
    }

    public final Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "com.alipay.mobile.common.transport.http.HttpManager.HttpWorker #" + this.f1766a.getAndIncrement());
        thread.setPriority(4);
        return thread;
    }
}
