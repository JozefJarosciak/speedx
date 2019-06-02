package okio;

import android.support.v4.media.session.PlaybackStateCompat;
import com.alipay.sdk.data.C0847a;
import java.io.IOException;
import java.io.InterruptedIOException;

/* compiled from: AsyncTimeout */
/* renamed from: okio.a */
public class C5523a extends C5522t {
    /* renamed from: a */
    private static C5523a f17788a;
    /* renamed from: c */
    private boolean f17789c;
    /* renamed from: d */
    private C5523a f17790d;
    /* renamed from: e */
    private long f17791e;

    /* compiled from: AsyncTimeout */
    /* renamed from: okio.a$a */
    private static final class C5631a extends Thread {
        public C5631a() {
            super("Okio Watchdog");
            setDaemon(true);
        }

        public void run() {
            while (true) {
                try {
                    C5523a e = C5523a.m19979e();
                    if (e != null) {
                        e.mo6725a();
                    }
                } catch (InterruptedException e2) {
                }
            }
        }
    }

    /* renamed from: c */
    public final void m19986c() {
        if (this.f17789c) {
            throw new IllegalStateException("Unbalanced enter/exit");
        }
        long j_ = j_();
        boolean k_ = k_();
        if (j_ != 0 || k_) {
            this.f17789c = true;
            C5523a.m19976a(this, j_, k_);
        }
    }

    /* renamed from: a */
    private static synchronized void m19976a(C5523a c5523a, long j, boolean z) {
        synchronized (C5523a.class) {
            if (f17788a == null) {
                f17788a = new C5523a();
                new C5631a().start();
            }
            long nanoTime = System.nanoTime();
            if (j != 0 && z) {
                c5523a.f17791e = Math.min(j, c5523a.mo6839d() - nanoTime) + nanoTime;
            } else if (j != 0) {
                c5523a.f17791e = nanoTime + j;
            } else if (z) {
                c5523a.f17791e = c5523a.mo6839d();
            } else {
                throw new AssertionError();
            }
            long b = c5523a.m19978b(nanoTime);
            C5523a c5523a2 = f17788a;
            while (c5523a2.f17790d != null && b >= c5523a2.f17790d.m19978b(nanoTime)) {
                c5523a2 = c5523a2.f17790d;
            }
            c5523a.f17790d = c5523a2.f17790d;
            c5523a2.f17790d = c5523a;
            if (c5523a2 == f17788a) {
                C5523a.class.notify();
            }
        }
    }

    public final boolean i_() {
        if (!this.f17789c) {
            return false;
        }
        this.f17789c = false;
        return C5523a.m19977a(this);
    }

    /* renamed from: a */
    private static synchronized boolean m19977a(C5523a c5523a) {
        boolean z;
        synchronized (C5523a.class) {
            for (C5523a c5523a2 = f17788a; c5523a2 != null; c5523a2 = c5523a2.f17790d) {
                if (c5523a2.f17790d == c5523a) {
                    c5523a2.f17790d = c5523a.f17790d;
                    c5523a.f17790d = null;
                    z = false;
                    break;
                }
            }
            z = true;
        }
        return z;
    }

    /* renamed from: b */
    private long m19978b(long j) {
        return this.f17791e - j;
    }

    /* renamed from: a */
    protected void mo6725a() {
    }

    /* renamed from: a */
    public final C5492r m19981a(final C5492r c5492r) {
        return new C5492r(this) {
            /* renamed from: b */
            final /* synthetic */ C5523a f18201b;

            /* renamed from: a */
            public void mo6706a(C5637c c5637c, long j) throws IOException {
                C5654u.m20769a(c5637c.f18210b, 0, j);
                long j2 = j;
                while (j2 > 0) {
                    C5651p c5651p = c5637c.f18209a;
                    long j3 = 0;
                    while (j3 < PlaybackStateCompat.ACTION_PREPARE_FROM_SEARCH) {
                        long j4 = ((long) (c5637c.f18209a.f18245c - c5637c.f18209a.f18244b)) + j3;
                        if (j4 >= j2) {
                            j3 = j2;
                            break;
                        } else {
                            c5651p = c5651p.f18248f;
                            j3 = j4;
                        }
                    }
                    this.f18201b.m19986c();
                    try {
                        c5492r.mo6706a(c5637c, j3);
                        j2 -= j3;
                        this.f18201b.m19984a(true);
                    } catch (IOException e) {
                        throw this.f18201b.m19985b(e);
                    } catch (Throwable th) {
                        this.f18201b.m19984a(false);
                    }
                }
            }

            public void flush() throws IOException {
                this.f18201b.m19986c();
                try {
                    c5492r.flush();
                    this.f18201b.m19984a(true);
                } catch (IOException e) {
                    throw this.f18201b.m19985b(e);
                } catch (Throwable th) {
                    this.f18201b.m19984a(false);
                }
            }

            public void close() throws IOException {
                this.f18201b.m19986c();
                try {
                    c5492r.close();
                    this.f18201b.m19984a(true);
                } catch (IOException e) {
                    throw this.f18201b.m19985b(e);
                } catch (Throwable th) {
                    this.f18201b.m19984a(false);
                }
            }

            public C5522t timeout() {
                return this.f18201b;
            }

            public String toString() {
                return "AsyncTimeout.sink(" + c5492r + ")";
            }
        };
    }

    /* renamed from: a */
    public final C5520s m19982a(final C5520s c5520s) {
        return new C5520s(this) {
            /* renamed from: b */
            final /* synthetic */ C5523a f18203b;

            public long read(C5637c c5637c, long j) throws IOException {
                this.f18203b.m19986c();
                try {
                    long read = c5520s.read(c5637c, j);
                    this.f18203b.m19984a(true);
                    return read;
                } catch (IOException e) {
                    throw this.f18203b.m19985b(e);
                } catch (Throwable th) {
                    this.f18203b.m19984a(false);
                }
            }

            public void close() throws IOException {
                try {
                    c5520s.close();
                    this.f18203b.m19984a(true);
                } catch (IOException e) {
                    throw this.f18203b.m19985b(e);
                } catch (Throwable th) {
                    this.f18203b.m19984a(false);
                }
            }

            public C5522t timeout() {
                return this.f18203b;
            }

            public String toString() {
                return "AsyncTimeout.source(" + c5520s + ")";
            }
        };
    }

    /* renamed from: a */
    final void m19984a(boolean z) throws IOException {
        if (i_() && z) {
            throw mo6724a(null);
        }
    }

    /* renamed from: b */
    final IOException m19985b(IOException iOException) throws IOException {
        return !i_() ? iOException : mo6724a(iOException);
    }

    /* renamed from: a */
    protected IOException mo6724a(IOException iOException) {
        IOException interruptedIOException = new InterruptedIOException(C0847a.f2089f);
        if (iOException != null) {
            interruptedIOException.initCause(iOException);
        }
        return interruptedIOException;
    }

    /* renamed from: e */
    static synchronized C5523a m19979e() throws InterruptedException {
        C5523a c5523a = null;
        synchronized (C5523a.class) {
            C5523a c5523a2 = f17788a.f17790d;
            if (c5523a2 == null) {
                C5523a.class.wait();
            } else {
                long b = c5523a2.m19978b(System.nanoTime());
                if (b > 0) {
                    long j = b / 1000000;
                    C5523a.class.wait(j, (int) (b - (1000000 * j)));
                } else {
                    f17788a.f17790d = c5523a2.f17790d;
                    c5523a2.f17790d = null;
                    c5523a = c5523a2;
                }
            }
        }
        return c5523a;
    }
}
