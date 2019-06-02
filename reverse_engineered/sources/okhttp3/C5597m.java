package okhttp3;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.C5618v.C5617b;
import okhttp3.internal.C5586l;

/* compiled from: Dispatcher */
/* renamed from: okhttp3.m */
public final class C5597m {
    /* renamed from: a */
    private int f18041a = 64;
    /* renamed from: b */
    private int f18042b = 5;
    /* renamed from: c */
    private ExecutorService f18043c;
    /* renamed from: d */
    private final Deque<C5617b> f18044d = new ArrayDeque();
    /* renamed from: e */
    private final Deque<C5617b> f18045e = new ArrayDeque();
    /* renamed from: f */
    private final Deque<C5618v> f18046f = new ArrayDeque();

    /* renamed from: a */
    public synchronized ExecutorService m20387a() {
        if (this.f18043c == null) {
            this.f18043c = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), C5586l.m20324a("OkHttp Dispatcher", false));
        }
        return this.f18043c;
    }

    /* renamed from: a */
    synchronized void m20389a(C5617b c5617b) {
        if (this.f18045e.size() >= this.f18041a || m20386c(c5617b) >= this.f18042b) {
            this.f18044d.add(c5617b);
        } else {
            this.f18045e.add(c5617b);
            m20387a().execute(c5617b);
        }
    }

    /* renamed from: b */
    synchronized void m20391b(C5617b c5617b) {
        if (this.f18045e.remove(c5617b)) {
            m20385b();
        } else {
            throw new AssertionError("AsyncCall wasn't running!");
        }
    }

    /* renamed from: b */
    private void m20385b() {
        if (this.f18045e.size() < this.f18041a && !this.f18044d.isEmpty()) {
            Iterator it = this.f18044d.iterator();
            while (it.hasNext()) {
                C5617b c5617b = (C5617b) it.next();
                if (m20386c(c5617b) < this.f18042b) {
                    it.remove();
                    this.f18045e.add(c5617b);
                    m20387a().execute(c5617b);
                }
                if (this.f18045e.size() >= this.f18041a) {
                    return;
                }
            }
        }
    }

    /* renamed from: c */
    private int m20386c(C5617b c5617b) {
        int i = 0;
        for (C5617b a : this.f18045e) {
            int i2;
            if (a.m20484a().equals(c5617b.m20484a())) {
                i2 = i + 1;
            } else {
                i2 = i;
            }
            i = i2;
        }
        return i;
    }

    /* renamed from: a */
    synchronized void m20390a(C5618v c5618v) {
        this.f18046f.add(c5618v);
    }

    /* renamed from: a */
    synchronized void m20388a(C5468e c5468e) {
        if (!this.f18046f.remove(c5468e)) {
            throw new AssertionError("Call wasn't in-flight!");
        }
    }
}
