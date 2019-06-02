package io.fabric.sdk.android.services.concurrency;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.util.Log;
import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class AsyncTask<Params, Progress, Result> {
    /* renamed from: a */
    private static final int f17089a = Runtime.getRuntime().availableProcessors();
    /* renamed from: b */
    public static final Executor f17090b = new ThreadPoolExecutor(f17092d, f17093e, 1, TimeUnit.SECONDS, f17095g, f17094f);
    /* renamed from: c */
    public static final Executor f17091c = new C4901c();
    /* renamed from: d */
    private static final int f17092d = (f17089a + 1);
    /* renamed from: e */
    private static final int f17093e = ((f17089a * 2) + 1);
    /* renamed from: f */
    private static final ThreadFactory f17094f = new C48931();
    /* renamed from: g */
    private static final BlockingQueue<Runnable> f17095g = new LinkedBlockingQueue(128);
    /* renamed from: h */
    private static final C4899b f17096h = new C4899b();
    /* renamed from: i */
    private static volatile Executor f17097i = f17091c;
    /* renamed from: j */
    private final C4894d<Params, Result> f17098j = new C48952(this);
    /* renamed from: k */
    private final FutureTask<Result> f17099k = new FutureTask<Result>(this, this.f17098j) {
        /* renamed from: a */
        final /* synthetic */ AsyncTask f17211a;

        protected void done() {
            try {
                this.f17211a.m19022d(get());
            } catch (Throwable e) {
                Log.w("AsyncTask", e);
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occured while executing doInBackground()", e2.getCause());
            } catch (CancellationException e3) {
                this.f17211a.m19022d(null);
            }
        }
    };
    /* renamed from: l */
    private volatile Status f17100l = Status.PENDING;
    /* renamed from: m */
    private final AtomicBoolean f17101m = new AtomicBoolean();
    /* renamed from: n */
    private final AtomicBoolean f17102n = new AtomicBoolean();

    /* renamed from: io.fabric.sdk.android.services.concurrency.AsyncTask$1 */
    static class C48931 implements ThreadFactory {
        /* renamed from: a */
        private final AtomicInteger f17208a = new AtomicInteger(1);

        C48931() {
        }

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "AsyncTask #" + this.f17208a.getAndIncrement());
        }
    }

    /* renamed from: io.fabric.sdk.android.services.concurrency.AsyncTask$d */
    private static abstract class C4894d<Params, Result> implements Callable<Result> {
        /* renamed from: b */
        Params[] f17209b;

        private C4894d() {
        }
    }

    /* renamed from: io.fabric.sdk.android.services.concurrency.AsyncTask$2 */
    class C48952 extends C4894d<Params, Result> {
        /* renamed from: a */
        final /* synthetic */ AsyncTask f17210a;

        C48952(AsyncTask asyncTask) {
            this.f17210a = asyncTask;
            super();
        }

        public Result call() throws Exception {
            this.f17210a.f17102n.set(true);
            Process.setThreadPriority(10);
            return this.f17210a.m19023e(this.f17210a.mo6233a(this.b));
        }
    }

    public enum Status {
        PENDING,
        RUNNING,
        FINISHED
    }

    /* renamed from: io.fabric.sdk.android.services.concurrency.AsyncTask$a */
    private static class C4898a<Data> {
        /* renamed from: a */
        final AsyncTask f17213a;
        /* renamed from: b */
        final Data[] f17214b;

        C4898a(AsyncTask asyncTask, Data... dataArr) {
            this.f17213a = asyncTask;
            this.f17214b = dataArr;
        }
    }

    /* renamed from: io.fabric.sdk.android.services.concurrency.AsyncTask$b */
    private static class C4899b extends Handler {
        public C4899b() {
            super(Looper.getMainLooper());
        }

        public void handleMessage(Message message) {
            C4898a c4898a = (C4898a) message.obj;
            switch (message.what) {
                case 1:
                    c4898a.f17213a.m19024f(c4898a.f17214b[0]);
                    return;
                case 2:
                    c4898a.f17213a.m19031b(c4898a.f17214b);
                    return;
                default:
                    return;
            }
        }
    }

    /* renamed from: io.fabric.sdk.android.services.concurrency.AsyncTask$c */
    private static class C4901c implements Executor {
        /* renamed from: a */
        final LinkedList<Runnable> f17217a;
        /* renamed from: b */
        Runnable f17218b;

        private C4901c() {
            this.f17217a = new LinkedList();
        }

        public synchronized void execute(final Runnable runnable) {
            this.f17217a.offer(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C4901c f17216b;

                public void run() {
                    try {
                        runnable.run();
                    } finally {
                        this.f17216b.m19228a();
                    }
                }
            });
            if (this.f17218b == null) {
                m19228a();
            }
        }

        /* renamed from: a */
        protected synchronized void m19228a() {
            Runnable runnable = (Runnable) this.f17217a.poll();
            this.f17218b = runnable;
            if (runnable != null) {
                AsyncTask.f17090b.execute(this.f17218b);
            }
        }
    }

    /* renamed from: a */
    protected abstract Result mo6233a(Params... paramsArr);

    /* renamed from: d */
    private void m19022d(Result result) {
        if (!this.f17102n.get()) {
            m19023e(result);
        }
    }

    /* renamed from: e */
    private Result m19023e(Result result) {
        f17096h.obtainMessage(1, new C4898a(this, result)).sendToTarget();
        return result;
    }

    public final Status h_() {
        return this.f17100l;
    }

    /* renamed from: a */
    protected void mo6234a() {
    }

    /* renamed from: a */
    protected void mo6235a(Result result) {
    }

    /* renamed from: b */
    protected void m19031b(Progress... progressArr) {
    }

    /* renamed from: b */
    protected void mo6236b(Result result) {
        m19032d();
    }

    /* renamed from: d */
    protected void m19032d() {
    }

    /* renamed from: e */
    public final boolean m19033e() {
        return this.f17101m.get();
    }

    /* renamed from: a */
    public final boolean m19029a(boolean z) {
        this.f17101m.set(true);
        return this.f17099k.cancel(z);
    }

    /* renamed from: a */
    public final AsyncTask<Params, Progress, Result> m19025a(Executor executor, Params... paramsArr) {
        if (this.f17100l != Status.PENDING) {
            switch (this.f17100l) {
                case RUNNING:
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                case FINISHED:
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.f17100l = Status.RUNNING;
        mo6234a();
        this.f17098j.f17209b = paramsArr;
        executor.execute(this.f17099k);
        return this;
    }

    /* renamed from: f */
    private void m19024f(Result result) {
        if (m19033e()) {
            mo6236b((Object) result);
        } else {
            mo6235a((Object) result);
        }
        this.f17100l = Status.FINISHED;
    }
}
