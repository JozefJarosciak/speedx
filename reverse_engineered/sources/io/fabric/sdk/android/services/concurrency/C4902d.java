package io.fabric.sdk.android.services.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/* compiled from: PriorityFutureTask */
/* renamed from: io.fabric.sdk.android.services.concurrency.d */
public class C4902d<V> extends FutureTask<V> implements C4842a<C4844h>, C4843e, C4844h {
    /* renamed from: b */
    final Object f17220b;

    /* renamed from: c */
    public /* synthetic */ void mo6230c(Object obj) {
        m19237a((C4844h) obj);
    }

    public C4902d(Callable<V> callable) {
        super(callable);
        this.f17220b = m19236a((Object) callable);
    }

    public C4902d(Runnable runnable, V v) {
        super(runnable, v);
        this.f17220b = m19236a((Object) runnable);
    }

    public int compareTo(Object obj) {
        return ((C4843e) mo6252a()).compareTo(obj);
    }

    /* renamed from: a */
    public void m19237a(C4844h c4844h) {
        ((C4842a) ((C4843e) mo6252a())).mo6230c(c4844h);
    }

    /* renamed from: c */
    public boolean mo6231c() {
        return ((C4842a) ((C4843e) mo6252a())).mo6231c();
    }

    /* renamed from: b */
    public Priority mo6228b() {
        return ((C4843e) mo6252a()).mo6228b();
    }

    /* renamed from: b */
    public void mo6229b(boolean z) {
        ((C4844h) ((C4843e) mo6252a())).mo6229b(z);
    }

    /* renamed from: f */
    public boolean mo6232f() {
        return ((C4844h) ((C4843e) mo6252a())).mo6232f();
    }

    /* renamed from: a */
    public void mo6227a(Throwable th) {
        ((C4844h) ((C4843e) mo6252a())).mo6227a(th);
    }

    /* renamed from: a */
    public <T extends C4842a<C4844h> & C4843e & C4844h> T mo6252a() {
        return (C4842a) this.f17220b;
    }

    /* renamed from: a */
    protected <T extends C4842a<C4844h> & C4843e & C4844h> T m19236a(Object obj) {
        if (C4905f.m19246a(obj)) {
            return (C4842a) obj;
        }
        return new C4905f();
    }
}
