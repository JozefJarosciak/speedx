package com.facebook.internal;

import com.facebook.C1472c;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.FutureTask;

/* compiled from: LockOnGetVariable */
/* renamed from: com.facebook.internal.l */
public class C3024l<T> {
    /* renamed from: a */
    private T f13577a;
    /* renamed from: b */
    private CountDownLatch f13578b = new CountDownLatch(1);

    public C3024l(final Callable<T> callable) {
        C1472c.d().execute(new FutureTask(new Callable<Void>(this) {
            /* renamed from: b */
            final /* synthetic */ C3024l f13576b;

            public /* synthetic */ Object call() throws Exception {
                return m14614a();
            }

            /* renamed from: a */
            public Void m14614a() throws Exception {
                try {
                    this.f13576b.f13577a = callable.call();
                    return null;
                } finally {
                    this.f13576b.f13578b.countDown();
                }
            }
        }));
    }
}
