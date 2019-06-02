package okhttp3.internal.http;

import com.alipay.sdk.util.C0880h;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okhttp3.C5569z;
import okhttp3.C5607q;
import okhttp3.C5607q.C5606a;
import okhttp3.C5621w;
import okhttp3.C5627y;
import okhttp3.C5627y.C5626a;
import okhttp3.internal.C5497d;
import okhttp3.internal.C5586l;
import okhttp3.internal.p206a.C5480b;
import okio.C5492r;
import okio.C5520s;
import okio.C5522t;
import okio.C5635d;
import okio.C5636e;
import okio.C5637c;
import okio.C5640i;
import okio.C5647m;
import org.apache.http.protocol.HTTP;

/* compiled from: Http1xStream */
/* renamed from: okhttp3.internal.http.d */
public final class C5564d implements C5563i {
    /* renamed from: a */
    private final C5582p f17916a;
    /* renamed from: b */
    private final C5636e f17917b;
    /* renamed from: c */
    private final C5635d f17918c;
    /* renamed from: d */
    private C5574g f17919d;
    /* renamed from: e */
    private int f17920e = 0;

    /* compiled from: Http1xStream */
    /* renamed from: okhttp3.internal.http.d$a */
    private abstract class C5557a implements C5520s {
        /* renamed from: a */
        protected final C5640i f17898a;
        /* renamed from: b */
        protected boolean f17899b;
        /* renamed from: c */
        final /* synthetic */ C5564d f17900c;

        private C5557a(C5564d c5564d) {
            this.f17900c = c5564d;
            this.f17898a = new C5640i(this.f17900c.f17917b.timeout());
        }

        public C5522t timeout() {
            return this.f17898a;
        }

        /* renamed from: a */
        protected final void m20173a(boolean z) throws IOException {
            if (this.f17900c.f17920e != 6) {
                if (this.f17900c.f17920e != 5) {
                    throw new IllegalStateException("state: " + this.f17900c.f17920e);
                }
                this.f17900c.m20188a(this.f17898a);
                this.f17900c.f17920e = 6;
                if (this.f17900c.f17916a != null) {
                    this.f17900c.f17916a.m20299a(!z, this.f17900c);
                }
            }
        }
    }

    /* compiled from: Http1xStream */
    /* renamed from: okhttp3.internal.http.d$b */
    private final class C5558b implements C5492r {
        /* renamed from: a */
        final /* synthetic */ C5564d f17901a;
        /* renamed from: b */
        private final C5640i f17902b;
        /* renamed from: c */
        private boolean f17903c;

        private C5558b(C5564d c5564d) {
            this.f17901a = c5564d;
            this.f17902b = new C5640i(this.f17901a.f17918c.timeout());
        }

        public C5522t timeout() {
            return this.f17902b;
        }

        /* renamed from: a */
        public void mo6706a(C5637c c5637c, long j) throws IOException {
            if (this.f17903c) {
                throw new IllegalStateException("closed");
            } else if (j != 0) {
                this.f17901a.f17918c.mo6829k(j);
                this.f17901a.f17918c.mo6811b("\r\n");
                this.f17901a.f17918c.mo6706a(c5637c, j);
                this.f17901a.f17918c.mo6811b("\r\n");
            }
        }

        public synchronized void flush() throws IOException {
            if (!this.f17903c) {
                this.f17901a.f17918c.flush();
            }
        }

        public synchronized void close() throws IOException {
            if (!this.f17903c) {
                this.f17903c = true;
                this.f17901a.f17918c.mo6811b("0\r\n\r\n");
                this.f17901a.m20188a(this.f17902b);
                this.f17901a.f17920e = 3;
            }
        }
    }

    /* compiled from: Http1xStream */
    /* renamed from: okhttp3.internal.http.d$c */
    private class C5559c extends C5557a {
        /* renamed from: d */
        final /* synthetic */ C5564d f17904d;
        /* renamed from: e */
        private long f17905e = -1;
        /* renamed from: f */
        private boolean f17906f = true;
        /* renamed from: g */
        private final C5574g f17907g;

        C5559c(C5564d c5564d, C5574g c5574g) throws IOException {
            this.f17904d = c5564d;
            super();
            this.f17907g = c5574g;
        }

        public long read(C5637c c5637c, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.b) {
                throw new IllegalStateException("closed");
            } else if (!this.f17906f) {
                return -1;
            } else {
                if (this.f17905e == 0 || this.f17905e == -1) {
                    m20175a();
                    if (!this.f17906f) {
                        return -1;
                    }
                }
                long read = this.f17904d.f17917b.read(c5637c, Math.min(j, this.f17905e));
                if (read == -1) {
                    m20173a(false);
                    throw new ProtocolException("unexpected end of stream");
                }
                this.f17905e -= read;
                return read;
            }
        }

        /* renamed from: a */
        private void m20175a() throws IOException {
            if (this.f17905e != -1) {
                this.f17904d.f17917b.mo6834q();
            }
            try {
                this.f17905e = this.f17904d.f17917b.mo6833n();
                String trim = this.f17904d.f17917b.mo6834q().trim();
                if (this.f17905e < 0 || !(trim.isEmpty() || trim.startsWith(C0880h.f2220b))) {
                    throw new ProtocolException("expected chunk size and optional extensions but was \"" + this.f17905e + trim + "\"");
                } else if (this.f17905e == 0) {
                    this.f17906f = false;
                    this.f17907g.m20251a(this.f17904d.m20206e());
                    m20173a(true);
                }
            } catch (NumberFormatException e) {
                throw new ProtocolException(e.getMessage());
            }
        }

        public void close() throws IOException {
            if (!this.b) {
                if (this.f17906f && !C5586l.m20332a((C5520s) this, 100, TimeUnit.MILLISECONDS)) {
                    m20173a(false);
                }
                this.b = true;
            }
        }
    }

    /* compiled from: Http1xStream */
    /* renamed from: okhttp3.internal.http.d$d */
    private final class C5560d implements C5492r {
        /* renamed from: a */
        final /* synthetic */ C5564d f17908a;
        /* renamed from: b */
        private final C5640i f17909b;
        /* renamed from: c */
        private boolean f17910c;
        /* renamed from: d */
        private long f17911d;

        private C5560d(C5564d c5564d, long j) {
            this.f17908a = c5564d;
            this.f17909b = new C5640i(this.f17908a.f17918c.timeout());
            this.f17911d = j;
        }

        public C5522t timeout() {
            return this.f17909b;
        }

        /* renamed from: a */
        public void mo6706a(C5637c c5637c, long j) throws IOException {
            if (this.f17910c) {
                throw new IllegalStateException("closed");
            }
            C5586l.m20326a(c5637c.m20623a(), 0, j);
            if (j > this.f17911d) {
                throw new ProtocolException("expected " + this.f17911d + " bytes but received " + j);
            }
            this.f17908a.f17918c.mo6706a(c5637c, j);
            this.f17911d -= j;
        }

        public void flush() throws IOException {
            if (!this.f17910c) {
                this.f17908a.f17918c.flush();
            }
        }

        public void close() throws IOException {
            if (!this.f17910c) {
                this.f17910c = true;
                if (this.f17911d > 0) {
                    throw new ProtocolException("unexpected end of stream");
                }
                this.f17908a.m20188a(this.f17909b);
                this.f17908a.f17920e = 3;
            }
        }
    }

    /* compiled from: Http1xStream */
    /* renamed from: okhttp3.internal.http.d$e */
    private class C5561e extends C5557a {
        /* renamed from: d */
        final /* synthetic */ C5564d f17912d;
        /* renamed from: e */
        private long f17913e;

        public C5561e(C5564d c5564d, long j) throws IOException {
            this.f17912d = c5564d;
            super();
            this.f17913e = j;
            if (this.f17913e == 0) {
                m20173a(true);
            }
        }

        public long read(C5637c c5637c, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.b) {
                throw new IllegalStateException("closed");
            } else if (this.f17913e == 0) {
                return -1;
            } else {
                long read = this.f17912d.f17917b.read(c5637c, Math.min(this.f17913e, j));
                if (read == -1) {
                    m20173a(false);
                    throw new ProtocolException("unexpected end of stream");
                }
                this.f17913e -= read;
                if (this.f17913e == 0) {
                    m20173a(true);
                }
                return read;
            }
        }

        public void close() throws IOException {
            if (!this.b) {
                if (!(this.f17913e == 0 || C5586l.m20332a((C5520s) this, 100, TimeUnit.MILLISECONDS))) {
                    m20173a(false);
                }
                this.b = true;
            }
        }
    }

    /* compiled from: Http1xStream */
    /* renamed from: okhttp3.internal.http.d$f */
    private class C5562f extends C5557a {
        /* renamed from: d */
        final /* synthetic */ C5564d f17914d;
        /* renamed from: e */
        private boolean f17915e;

        private C5562f(C5564d c5564d) {
            this.f17914d = c5564d;
            super();
        }

        public long read(C5637c c5637c, long j) throws IOException {
            if (j < 0) {
                throw new IllegalArgumentException("byteCount < 0: " + j);
            } else if (this.b) {
                throw new IllegalStateException("closed");
            } else if (this.f17915e) {
                return -1;
            } else {
                long read = this.f17914d.f17917b.read(c5637c, j);
                if (read != -1) {
                    return read;
                }
                this.f17915e = true;
                m20173a(true);
                return -1;
            }
        }

        public void close() throws IOException {
            if (!this.b) {
                if (!this.f17915e) {
                    m20173a(false);
                }
                this.b = true;
            }
        }
    }

    public C5564d(C5582p c5582p, C5636e c5636e, C5635d c5635d) {
        this.f17916a = c5582p;
        this.f17917b = c5636e;
        this.f17918c = c5635d;
    }

    /* renamed from: a */
    public void mo6750a(C5574g c5574g) {
        this.f17919d = c5574g;
    }

    /* renamed from: a */
    public C5492r mo6748a(C5621w c5621w, long j) throws IOException {
        if (HTTP.CHUNK_CODING.equalsIgnoreCase(c5621w.m20518a("Transfer-Encoding"))) {
            return m20207f();
        }
        if (j != -1) {
            return m20194a(j);
        }
        throw new IllegalStateException("Cannot stream a request body without chunked encoding or a known content length!");
    }

    /* renamed from: a */
    public void mo6749a() {
        C5480b b = this.f17916a.m20300b();
        if (b != null) {
            b.m19764d();
        }
    }

    /* renamed from: a */
    public void mo6752a(C5621w c5621w) throws IOException {
        this.f17919d.m20254b();
        m20199a(c5621w.m20521c(), C5578l.m20272a(c5621w, this.f17919d.m20256d().mo6694a().m19698b().type()));
    }

    /* renamed from: b */
    public C5626a mo6753b() throws IOException {
        return m20205d();
    }

    /* renamed from: a */
    public C5569z mo6747a(C5627y c5627y) throws IOException {
        return new C5577k(c5627y.m20574f(), C5647m.m20714a(m20190b(c5627y)));
    }

    /* renamed from: b */
    private C5520s m20190b(C5627y c5627y) throws IOException {
        if (!C5574g.m20239a(c5627y)) {
            return m20202b(0);
        }
        if (HTTP.CHUNK_CODING.equalsIgnoreCase(c5627y.m20567a("Transfer-Encoding"))) {
            return m20203b(this.f17919d);
        }
        long a = C5576j.m20268a(c5627y);
        if (a != -1) {
            return m20202b(a);
        }
        return m20208g();
    }

    /* renamed from: c */
    public void mo6754c() throws IOException {
        this.f17918c.flush();
    }

    /* renamed from: a */
    public void m20199a(C5607q c5607q, String str) throws IOException {
        if (this.f17920e != 0) {
            throw new IllegalStateException("state: " + this.f17920e);
        }
        this.f17918c.mo6811b(str).mo6811b("\r\n");
        int a = c5607q.m20411a();
        for (int i = 0; i < a; i++) {
            this.f17918c.mo6811b(c5607q.m20412a(i)).mo6811b(": ").mo6811b(c5607q.m20414b(i)).mo6811b("\r\n");
        }
        this.f17918c.mo6811b("\r\n");
        this.f17920e = 1;
    }

    /* renamed from: d */
    public C5626a m20205d() throws IOException {
        if (this.f17920e == 1 || this.f17920e == 3) {
            C5626a a;
            C5581o a2;
            do {
                try {
                    a2 = C5581o.m20289a(this.f17917b.mo6834q());
                    a = new C5626a().m20545a(a2.f17988a).m20541a(a2.f17989b).m20543a(a2.f17990c).m20547a(m20206e());
                } catch (Throwable e) {
                    IOException iOException = new IOException("unexpected end of stream on " + this.f17916a);
                    iOException.initCause(e);
                    throw iOException;
                }
            } while (a2.f17989b == 100);
            this.f17920e = 4;
            return a;
        }
        throw new IllegalStateException("state: " + this.f17920e);
    }

    /* renamed from: e */
    public C5607q m20206e() throws IOException {
        C5606a c5606a = new C5606a();
        while (true) {
            String q = this.f17917b.mo6834q();
            if (q.length() == 0) {
                return c5606a.m20405a();
            }
            C5497d.f17702a.mo6773a(c5606a, q);
        }
    }

    /* renamed from: f */
    public C5492r m20207f() {
        if (this.f17920e != 1) {
            throw new IllegalStateException("state: " + this.f17920e);
        }
        this.f17920e = 2;
        return new C5558b();
    }

    /* renamed from: a */
    public C5492r m20194a(long j) {
        if (this.f17920e != 1) {
            throw new IllegalStateException("state: " + this.f17920e);
        }
        this.f17920e = 2;
        return new C5560d(j);
    }

    /* renamed from: a */
    public void mo6751a(C5579m c5579m) throws IOException {
        if (this.f17920e != 1) {
            throw new IllegalStateException("state: " + this.f17920e);
        }
        this.f17920e = 3;
        c5579m.m20276a(this.f17918c);
    }

    /* renamed from: b */
    public C5520s m20202b(long j) throws IOException {
        if (this.f17920e != 4) {
            throw new IllegalStateException("state: " + this.f17920e);
        }
        this.f17920e = 5;
        return new C5561e(this, j);
    }

    /* renamed from: b */
    public C5520s m20203b(C5574g c5574g) throws IOException {
        if (this.f17920e != 4) {
            throw new IllegalStateException("state: " + this.f17920e);
        }
        this.f17920e = 5;
        return new C5559c(this, c5574g);
    }

    /* renamed from: g */
    public C5520s m20208g() throws IOException {
        if (this.f17920e != 4) {
            throw new IllegalStateException("state: " + this.f17920e);
        } else if (this.f17916a == null) {
            throw new IllegalStateException("streamAllocation == null");
        } else {
            this.f17920e = 5;
            this.f17916a.m20302d();
            return new C5562f();
        }
    }

    /* renamed from: a */
    private void m20188a(C5640i c5640i) {
        C5522t a = c5640i.m20694a();
        c5640i.m20693a(C5522t.f17784b);
        a.mo6840f();
        a.l_();
    }
}
