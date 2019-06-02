package okhttp3;

import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.C5596r.C5572a;
import okhttp3.C5621w.C5620a;
import okhttp3.internal.C5481j;
import okhttp3.internal.C5503h;
import okhttp3.internal.http.C5574g;
import okhttp3.internal.http.C5582p;
import okhttp3.internal.http.RequestException;
import okhttp3.internal.http.RouteException;
import org.apache.http.protocol.HTTP;

/* compiled from: RealCall */
/* renamed from: okhttp3.v */
final class C5618v implements C5468e {
    /* renamed from: a */
    volatile boolean f18142a;
    /* renamed from: b */
    C5621w f18143b;
    /* renamed from: c */
    C5574g f18144c;
    /* renamed from: d */
    private final C5614u f18145d;
    /* renamed from: e */
    private boolean f18146e;

    /* compiled from: RealCall */
    /* renamed from: okhttp3.v$a */
    class C5616a implements C5572a {
        /* renamed from: a */
        final /* synthetic */ C5618v f18135a;
        /* renamed from: b */
        private final int f18136b;
        /* renamed from: c */
        private final C5621w f18137c;
        /* renamed from: d */
        private final boolean f18138d;

        C5616a(C5618v c5618v, int i, C5621w c5621w, boolean z) {
            this.f18135a = c5618v;
            this.f18136b = i;
            this.f18137c = c5621w;
            this.f18138d = z;
        }

        /* renamed from: b */
        public C5474h mo6760b() {
            return null;
        }

        /* renamed from: a */
        public C5621w mo6758a() {
            return this.f18137c;
        }

        /* renamed from: a */
        public C5627y mo6759a(C5621w c5621w) throws IOException {
            if (this.f18136b >= this.f18135a.f18145d.m20479v().size()) {
                return this.f18135a.m20492a(c5621w, this.f18138d);
            }
            C5596r c5596r = (C5596r) this.f18135a.f18145d.m20479v().get(this.f18136b);
            C5627y intercept = c5596r.intercept(new C5616a(this.f18135a, this.f18136b + 1, c5621w, this.f18138d));
            if (intercept != null) {
                return intercept;
            }
            throw new NullPointerException("application interceptor " + c5596r + " returned null");
        }
    }

    /* compiled from: RealCall */
    /* renamed from: okhttp3.v$b */
    final class C5617b extends C5503h {
        /* renamed from: a */
        final /* synthetic */ C5618v f18139a;
        /* renamed from: c */
        private final C5469f f18140c;
        /* renamed from: d */
        private final boolean f18141d;

        private C5617b(C5618v c5618v, C5469f c5469f, boolean z) {
            this.f18139a = c5618v;
            super("OkHttp %s", c5618v.m20497d().toString());
            this.f18140c = c5469f;
            this.f18141d = z;
        }

        /* renamed from: a */
        String m20484a() {
            return this.f18139a.f18143b.m20519a().m19676f();
        }

        /* renamed from: b */
        protected void mo6710b() {
            Throwable e;
            Object obj = 1;
            Object obj2 = null;
            try {
                C5627y a = this.f18139a.m20488a(this.f18141d);
                if (this.f18139a.f18142a) {
                    try {
                        this.f18140c.onFailure(this.f18139a, new IOException("Canceled"));
                    } catch (IOException e2) {
                        e = e2;
                        if (obj == null) {
                            this.f18140c.onFailure(this.f18139a, e);
                        } else {
                            try {
                                C5481j.m19770c().mo6700a(4, "Callback failure for " + this.f18139a.m20490e(), e);
                            } catch (Throwable th) {
                                this.f18139a.f18145d.m20476s().m20391b(this);
                            }
                        }
                        this.f18139a.f18145d.m20476s().m20391b(this);
                    }
                }
                this.f18140c.onResponse(this.f18139a, a);
                this.f18139a.f18145d.m20476s().m20391b(this);
            } catch (IOException e3) {
                e = e3;
                obj = obj2;
                if (obj == null) {
                    C5481j.m19770c().mo6700a(4, "Callback failure for " + this.f18139a.m20490e(), e);
                } else {
                    this.f18140c.onFailure(this.f18139a, e);
                }
                this.f18139a.f18145d.m20476s().m20391b(this);
            }
        }
    }

    protected C5618v(C5614u c5614u, C5621w c5621w) {
        this.f18145d = c5614u;
        this.f18143b = c5621w;
    }

    /* renamed from: a */
    public C5621w mo6778a() {
        return this.f18143b;
    }

    /* renamed from: b */
    public C5627y mo6780b() throws IOException {
        synchronized (this) {
            if (this.f18146e) {
                throw new IllegalStateException("Already Executed");
            }
            this.f18146e = true;
        }
        try {
            this.f18145d.m20476s().m20390a(this);
            C5627y a = m20488a(false);
            if (a != null) {
                return a;
            }
            throw new IOException("Canceled");
        } finally {
            this.f18145d.m20476s().m20388a((C5468e) this);
        }
    }

    /* renamed from: a */
    public void mo6779a(C5469f c5469f) {
        m20494a(c5469f, false);
    }

    /* renamed from: a */
    void m20494a(C5469f c5469f, boolean z) {
        synchronized (this) {
            if (this.f18146e) {
                throw new IllegalStateException("Already Executed");
            }
            this.f18146e = true;
        }
        this.f18145d.m20476s().m20389a(new C5617b(c5469f, z));
    }

    /* renamed from: c */
    public void mo6781c() {
        this.f18142a = true;
        if (this.f18144c != null) {
            this.f18144c.m20258f();
        }
    }

    /* renamed from: e */
    private String m20490e() {
        return (this.f18142a ? "canceled call" : "call") + " to " + m20497d();
    }

    /* renamed from: d */
    HttpUrl m20497d() {
        return this.f18143b.m20519a().m19671c("/...");
    }

    /* renamed from: a */
    private C5627y m20488a(boolean z) throws IOException {
        return new C5616a(this, 0, this.f18143b, z).mo6759a(this.f18143b);
    }

    /* renamed from: a */
    C5627y m20492a(C5621w c5621w, boolean z) throws IOException {
        C5621w a;
        C5574g a2;
        Object obj;
        Throwable th;
        C5602x d = c5621w.m20522d();
        if (d != null) {
            C5620a f = c5621w.m20524f();
            C5608s contentType = d.contentType();
            if (contentType != null) {
                f.m20505a("Content-Type", contentType.toString());
            }
            long contentLength = d.contentLength();
            if (contentLength != -1) {
                f.m20505a("Content-Length", Long.toString(contentLength));
                f.m20511b("Transfer-Encoding");
            } else {
                f.m20505a("Transfer-Encoding", HTTP.CHUNK_CODING);
                f.m20511b("Content-Length");
            }
            a = f.m20510a();
        } else {
            a = c5621w;
        }
        this.f18144c = new C5574g(this.f18145d, a, false, false, z, null, null, null);
        int i = 0;
        while (!this.f18142a) {
            try {
                this.f18144c.m20250a();
                this.f18144c.m20260h();
                C5627y c = this.f18144c.m20255c();
                a = this.f18144c.m20261i();
                if (a == null) {
                    if (!z) {
                        this.f18144c.m20257e();
                    }
                    return c;
                }
                C5582p g = this.f18144c.m20259g();
                int i2 = i + 1;
                if (i2 > 20) {
                    g.m20301c();
                    throw new ProtocolException("Too many follow-up requests: " + i2);
                }
                if (!this.f18144c.m20252a(a.m20519a())) {
                    g.m20301c();
                    g = null;
                } else if (g.m20295a() != null) {
                    throw new IllegalStateException("Closing the body of " + c + " didn't close its backing stream. Bad interceptor?");
                }
                this.f18144c = new C5574g(this.f18145d, a, false, false, z, g, null, c);
                i = i2;
            } catch (RequestException e) {
                throw e.getCause();
            } catch (RouteException e2) {
                a2 = this.f18144c.m20249a(e2.getLastConnectException(), true, null);
                if (a2 != null) {
                    obj = null;
                    this.f18144c = a2;
                } else {
                    throw e2.getLastConnectException();
                }
            } catch (IOException e3) {
                a2 = this.f18144c.m20249a(e3, false, null);
                if (a2 != null) {
                    obj = null;
                    this.f18144c = a2;
                } else {
                    throw e3;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        this.f18144c.m20257e();
        throw new IOException("Canceled");
        if (obj != null) {
            this.f18144c.m20259g().m20301c();
        }
        throw th;
    }
}
