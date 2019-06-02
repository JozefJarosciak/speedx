package com.beastbikes.framework.android.p056e;

import android.content.Context;
import android.os.AsyncTask;
import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* compiled from: AsyncTaskQueue */
/* renamed from: com.beastbikes.framework.android.e.a */
public class C2794a {
    /* renamed from: a */
    private static final Logger f13010a = LoggerFactory.getLogger(C2794a.class);
    /* renamed from: b */
    private static final ThreadFactory f13011b = new C27911();
    /* renamed from: c */
    private final C2793a f13012c = new C2793a(this);
    /* renamed from: d */
    private final LinkedBlockingDeque<AsyncTask> f13013d = new LinkedBlockingDeque();
    /* renamed from: e */
    private final ScheduledExecutorService f13014e = Executors.newSingleThreadScheduledExecutor(f13011b);

    /* compiled from: AsyncTaskQueue */
    /* renamed from: com.beastbikes.framework.android.e.a$1 */
    static class C27911 implements ThreadFactory {
        /* renamed from: a */
        private final AtomicInteger f13004a = new AtomicInteger(1);

        C27911() {
        }

        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "QueuedAsyncTask #" + this.f13004a.getAndIncrement());
        }
    }

    /* compiled from: AsyncTaskQueue */
    /* renamed from: com.beastbikes.framework.android.e.a$a */
    private static final class C2793a implements Executor {
        /* renamed from: a */
        private final Deque<Runnable> f13007a = new LinkedList();
        /* renamed from: b */
        private Runnable f13008b;
        /* renamed from: c */
        private final C2794a f13009c;

        public C2793a(C2794a c2794a) {
            this.f13009c = c2794a;
        }

        public synchronized void execute(final Runnable runnable) {
            this.f13007a.offer(new Runnable(this) {
                /* renamed from: b */
                final /* synthetic */ C2793a f13006b;

                public void run() {
                    try {
                        runnable.run();
                    } catch (Throwable th) {
                        C2794a.f13010a.error(th.getMessage(), th);
                    } finally {
                        this.f13006b.m13736a();
                    }
                }
            });
            if (this.f13008b == null) {
                m13736a();
            }
        }

        /* renamed from: a */
        protected synchronized void m13736a() {
            Runnable runnable = (Runnable) this.f13007a.poll();
            this.f13008b = runnable;
            if (runnable != null) {
                try {
                    this.f13009c.f13014e.execute(this.f13008b);
                } catch (Throwable e) {
                    C2794a.f13010a.error(e.getMessage(), e);
                }
            }
        }

        /* renamed from: a */
        protected synchronized void m13737a(Object obj) {
            this.f13007a.clear();
        }
    }

    C2794a(Context context) {
    }

    /* renamed from: a */
    public <Params, Progress, Result> AsyncTask<Params, Progress, Result> m13740a(AsyncTask<Params, Progress, Result> asyncTask, Params... paramsArr) {
        this.f13013d.add(asyncTask);
        return asyncTask.executeOnExecutor(this.f13012c, paramsArr);
    }

    /* renamed from: a */
    public void m13742a(Object obj) {
        this.f13012c.m13737a(obj);
        if (!this.f13013d.isEmpty()) {
            while (true) {
                AsyncTask asyncTask = (AsyncTask) this.f13013d.poll();
                if (asyncTask != null && !asyncTask.isCancelled()) {
                    asyncTask.cancel(true);
                } else {
                    return;
                }
            }
        }
    }

    /* renamed from: a */
    public void m13741a() {
        m13742a(null);
        this.f13014e.shutdownNow();
    }
}
