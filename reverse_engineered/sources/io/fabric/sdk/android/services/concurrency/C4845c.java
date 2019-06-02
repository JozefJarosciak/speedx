package io.fabric.sdk.android.services.concurrency;

import io.fabric.sdk.android.services.concurrency.AsyncTask.Status;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

/* compiled from: PriorityAsyncTask */
/* renamed from: io.fabric.sdk.android.services.concurrency.c */
public abstract class C4845c<Params, Progress, Result> extends AsyncTask<Params, Progress, Result> implements C4842a<C4844h>, C4843e, C4844h {
    /* renamed from: a */
    private final C4905f f17103a = new C4905f();

    /* compiled from: PriorityAsyncTask */
    /* renamed from: io.fabric.sdk.android.services.concurrency.c$a */
    private static class C4904a<Result> implements Executor {
        /* renamed from: a */
        private final Executor f17222a;
        /* renamed from: b */
        private final C4845c f17223b;

        public C4904a(Executor executor, C4845c c4845c) {
            this.f17222a = executor;
            this.f17223b = c4845c;
        }

        public void execute(Runnable runnable) {
            this.f17222a.execute(new C4902d<Result>(this, runnable, null) {
                /* renamed from: a */
                final /* synthetic */ C4904a f17221a;

                /* renamed from: a */
                public <T extends C4842a<C4844h> & C4843e & C4844h> T mo6252a() {
                    return this.f17221a.f17223b;
                }
            });
        }
    }

    /* renamed from: c */
    public /* synthetic */ void mo6230c(Object obj) {
        m19040a((C4844h) obj);
    }

    /* renamed from: a */
    public final void m19042a(ExecutorService executorService, Params... paramsArr) {
        super.m19025a(new C4904a(executorService, this), (Object[]) paramsArr);
    }

    public int compareTo(Object obj) {
        return Priority.m19234a(this, obj);
    }

    /* renamed from: a */
    public void m19040a(C4844h c4844h) {
        if (h_() != Status.PENDING) {
            throw new IllegalStateException("Must not add Dependency after task is running");
        }
        ((C4842a) ((C4843e) m19048g())).mo6230c(c4844h);
    }

    /* renamed from: c */
    public boolean mo6231c() {
        return ((C4842a) ((C4843e) m19048g())).mo6231c();
    }

    /* renamed from: b */
    public Priority mo6228b() {
        return ((C4843e) m19048g()).mo6228b();
    }

    /* renamed from: b */
    public void mo6229b(boolean z) {
        ((C4844h) ((C4843e) m19048g())).mo6229b(z);
    }

    /* renamed from: f */
    public boolean mo6232f() {
        return ((C4844h) ((C4843e) m19048g())).mo6232f();
    }

    /* renamed from: a */
    public void mo6227a(Throwable th) {
        ((C4844h) ((C4843e) m19048g())).mo6227a(th);
    }

    /* renamed from: g */
    public <T extends C4842a<C4844h> & C4843e & C4844h> T m19048g() {
        return this.f17103a;
    }
}
