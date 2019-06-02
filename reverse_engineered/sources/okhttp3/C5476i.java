package okhttp3;

import java.lang.ref.Reference;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.internal.C5481j;
import okhttp3.internal.C5584k;
import okhttp3.internal.C5586l;
import okhttp3.internal.http.C5582p;
import okhttp3.internal.p206a.C5480b;

/* compiled from: ConnectionPool */
/* renamed from: okhttp3.i */
public final class C5476i {
    /* renamed from: c */
    static final /* synthetic */ boolean f17627c = (!C5476i.class.desiredAssertionStatus());
    /* renamed from: d */
    private static final Executor f17628d = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), C5586l.m20324a("OkHttp ConnectionPool", true));
    /* renamed from: a */
    final C5584k f17629a;
    /* renamed from: b */
    boolean f17630b;
    /* renamed from: e */
    private final int f17631e;
    /* renamed from: f */
    private final long f17632f;
    /* renamed from: g */
    private final Runnable f17633g;
    /* renamed from: h */
    private final Deque<C5480b> f17634h;

    /* compiled from: ConnectionPool */
    /* renamed from: okhttp3.i$1 */
    class C54751 implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C5476i f17626a;

        C54751(C5476i c5476i) {
            this.f17626a = c5476i;
        }

        public void run() {
            while (true) {
                long a = this.f17626a.m19736a(System.nanoTime());
                if (a != -1) {
                    if (a > 0) {
                        long j = a / 1000000;
                        a -= j * 1000000;
                        synchronized (this.f17626a) {
                            try {
                                this.f17626a.wait(j, (int) a);
                            } catch (InterruptedException e) {
                            }
                        }
                    }
                } else {
                    return;
                }
            }
        }
    }

    public C5476i() {
        this(5, 5, TimeUnit.MINUTES);
    }

    public C5476i(int i, long j, TimeUnit timeUnit) {
        this.f17633g = new C54751(this);
        this.f17634h = new ArrayDeque();
        this.f17629a = new C5584k();
        this.f17631e = i;
        this.f17632f = timeUnit.toNanos(j);
        if (j <= 0) {
            throw new IllegalArgumentException("keepAliveDuration <= 0: " + j);
        }
    }

    /* renamed from: a */
    C5480b m19737a(C5460a c5460a, C5582p c5582p) {
        if (f17627c || Thread.holdsLock(this)) {
            for (C5480b c5480b : this.f17634h) {
                if (c5480b.f17643h.size() < c5480b.f17642g && c5460a.equals(c5480b.mo6694a().f17592a) && !c5480b.f17644i) {
                    c5582p.m20298a(c5480b);
                    return c5480b;
                }
            }
            return null;
        }
        throw new AssertionError();
    }

    /* renamed from: a */
    void m19738a(C5480b c5480b) {
        if (f17627c || Thread.holdsLock(this)) {
            if (!this.f17630b) {
                this.f17630b = true;
                f17628d.execute(this.f17633g);
            }
            this.f17634h.add(c5480b);
            return;
        }
        throw new AssertionError();
    }

    /* renamed from: b */
    boolean m19739b(C5480b c5480b) {
        if (!f17627c && !Thread.holdsLock(this)) {
            throw new AssertionError();
        } else if (c5480b.f17644i || this.f17631e == 0) {
            this.f17634h.remove(c5480b);
            return true;
        } else {
            notifyAll();
            return false;
        }
    }

    /* renamed from: a */
    long m19736a(long j) {
        C5480b c5480b = null;
        long j2 = Long.MIN_VALUE;
        synchronized (this) {
            long j3;
            int i = 0;
            int i2 = 0;
            for (C5480b c5480b2 : this.f17634h) {
                if (m19735a(c5480b2, j) > 0) {
                    i2++;
                } else {
                    C5480b c5480b3;
                    int i3 = i + 1;
                    long j4 = j - c5480b2.f17645j;
                    if (j4 > j2) {
                        long j5 = j4;
                        c5480b3 = c5480b2;
                        j3 = j5;
                    } else {
                        c5480b3 = c5480b;
                        j3 = j2;
                    }
                    j2 = j3;
                    c5480b = c5480b3;
                    i = i3;
                }
            }
            if (j2 >= this.f17632f || i > this.f17631e) {
                this.f17634h.remove(c5480b);
                C5586l.m20329a(c5480b.mo6697b());
                return 0;
            } else if (i > 0) {
                j3 = this.f17632f - j2;
                return j3;
            } else if (i2 > 0) {
                j3 = this.f17632f;
                return j3;
            } else {
                this.f17630b = false;
                return -1;
            }
        }
    }

    /* renamed from: a */
    private int m19735a(C5480b c5480b, long j) {
        List list = c5480b.f17643h;
        int i = 0;
        while (i < list.size()) {
            if (((Reference) list.get(i)).get() != null) {
                i++;
            } else {
                C5481j.m19770c().mo6700a(5, "A connection to " + c5480b.mo6694a().m19697a().m19686a() + " was leaked. Did you forget to close a response body?", null);
                list.remove(i);
                c5480b.f17644i = true;
                if (list.isEmpty()) {
                    c5480b.f17645j = j - this.f17632f;
                    return 0;
                }
            }
        }
        return list.size();
    }
}
