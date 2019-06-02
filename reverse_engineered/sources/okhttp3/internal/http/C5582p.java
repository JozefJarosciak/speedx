package okhttp3.internal.http;

import java.io.IOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;
import okhttp3.C5460a;
import okhttp3.C5476i;
import okhttp3.aa;
import okhttp3.internal.C5497d;
import okhttp3.internal.C5584k;
import okhttp3.internal.C5586l;
import okhttp3.internal.framed.ErrorCode;
import okhttp3.internal.framed.StreamResetException;
import okhttp3.internal.p206a.C5480b;

/* compiled from: StreamAllocation */
/* renamed from: okhttp3.internal.http.p */
public final class C5582p {
    /* renamed from: a */
    public final C5460a f17991a;
    /* renamed from: b */
    private aa f17992b;
    /* renamed from: c */
    private final C5476i f17993c;
    /* renamed from: d */
    private final C5580n f17994d;
    /* renamed from: e */
    private int f17995e;
    /* renamed from: f */
    private C5480b f17996f;
    /* renamed from: g */
    private boolean f17997g;
    /* renamed from: h */
    private boolean f17998h;
    /* renamed from: i */
    private C5563i f17999i;

    public C5582p(C5476i c5476i, C5460a c5460a) {
        this.f17993c = c5476i;
        this.f17991a = c5460a;
        this.f17994d = new C5580n(c5460a, m20294g());
    }

    /* renamed from: a */
    public C5563i m20296a(int i, int i2, int i3, boolean z, boolean z2) throws RouteException, IOException {
        try {
            C5563i c5566e;
            C5480b b = m20292b(i, i2, i3, z, z2);
            if (b.f17638c != null) {
                c5566e = new C5566e(this, b.f17638c);
            } else {
                b.mo6697b().setSoTimeout(i2);
                b.f17640e.timeout().mo6838a((long) i2, TimeUnit.MILLISECONDS);
                b.f17641f.timeout().mo6838a((long) i3, TimeUnit.MILLISECONDS);
                c5566e = new C5564d(this, b.f17640e, b.f17641f);
            }
            synchronized (this.f17993c) {
                this.f17999i = c5566e;
            }
            return c5566e;
        } catch (IOException e) {
            throw new RouteException(e);
        }
    }

    /* renamed from: b */
    private C5480b m20292b(int i, int i2, int i3, boolean z, boolean z2) throws IOException, RouteException {
        C5480b a;
        while (true) {
            a = m20290a(i, i2, i3, z);
            synchronized (this.f17993c) {
                if (a.f17639d != 0) {
                    if (a.m19761a(z2)) {
                        break;
                    }
                    m20302d();
                } else {
                    break;
                }
            }
        }
        return a;
    }

    /* renamed from: a */
    private C5480b m20290a(int i, int i2, int i3, boolean z) throws IOException, RouteException {
        C5480b c5480b;
        synchronized (this.f17993c) {
            if (this.f17997g) {
                throw new IllegalStateException("released");
            } else if (this.f17999i != null) {
                throw new IllegalStateException("stream != null");
            } else if (this.f17998h) {
                throw new IOException("Canceled");
            } else {
                c5480b = this.f17996f;
                if (c5480b == null || c5480b.f17644i) {
                    c5480b = C5497d.f17702a.mo6769a(this.f17993c, this.f17991a, this);
                    if (c5480b != null) {
                        this.f17996f = c5480b;
                    } else {
                        aa aaVar;
                        aa aaVar2 = this.f17992b;
                        if (aaVar2 == null) {
                            aaVar2 = this.f17994d.m20288b();
                            synchronized (this.f17993c) {
                                this.f17992b = aaVar2;
                                this.f17995e = 0;
                            }
                            aaVar = aaVar2;
                        } else {
                            aaVar = aaVar2;
                        }
                        c5480b = new C5480b(aaVar);
                        m20298a(c5480b);
                        synchronized (this.f17993c) {
                            C5497d.f17702a.mo6776b(this.f17993c, c5480b);
                            this.f17996f = c5480b;
                            if (this.f17998h) {
                                throw new IOException("Canceled");
                            }
                        }
                        c5480b.m19758a(i, i2, i3, this.f17991a.m19691f(), z);
                        m20294g().m20313b(c5480b.mo6694a());
                    }
                }
            }
        }
        return c5480b;
    }

    /* renamed from: a */
    public void m20299a(boolean z, C5563i c5563i) {
        synchronized (this.f17993c) {
            if (c5563i != null) {
                if (c5563i == this.f17999i) {
                    if (!z) {
                        C5480b c5480b = this.f17996f;
                        c5480b.f17639d++;
                    }
                }
            }
            throw new IllegalStateException("expected " + this.f17999i + " but was " + c5563i);
        }
        m20291a(z, false, true);
    }

    /* renamed from: a */
    public C5563i m20295a() {
        C5563i c5563i;
        synchronized (this.f17993c) {
            c5563i = this.f17999i;
        }
        return c5563i;
    }

    /* renamed from: g */
    private C5584k m20294g() {
        return C5497d.f17702a.mo6771a(this.f17993c);
    }

    /* renamed from: b */
    public synchronized C5480b m20300b() {
        return this.f17996f;
    }

    /* renamed from: c */
    public void m20301c() {
        m20291a(false, true, false);
    }

    /* renamed from: d */
    public void m20302d() {
        m20291a(true, false, false);
    }

    /* renamed from: a */
    private void m20291a(boolean z, boolean z2, boolean z3) {
        C5480b c5480b = null;
        synchronized (this.f17993c) {
            if (z3) {
                this.f17999i = null;
            }
            if (z2) {
                this.f17997g = true;
            }
            if (this.f17996f != null) {
                if (z) {
                    this.f17996f.f17644i = true;
                }
                if (this.f17999i == null && (this.f17997g || this.f17996f.f17644i)) {
                    m20293b(this.f17996f);
                    if (this.f17996f.f17643h.isEmpty()) {
                        this.f17996f.f17645j = System.nanoTime();
                        if (C5497d.f17702a.mo6775a(this.f17993c, this.f17996f)) {
                            c5480b = this.f17996f;
                        }
                    }
                    this.f17996f = null;
                }
            }
        }
        if (c5480b != null) {
            C5586l.m20329a(c5480b.mo6697b());
        }
    }

    /* renamed from: e */
    public void m20303e() {
        synchronized (this.f17993c) {
            this.f17998h = true;
            C5563i c5563i = this.f17999i;
            C5480b c5480b = this.f17996f;
        }
        if (c5563i != null) {
            c5563i.mo6749a();
        } else if (c5480b != null) {
            c5480b.m19764d();
        }
    }

    /* renamed from: a */
    public void m20297a(IOException iOException) {
        boolean z;
        synchronized (this.f17993c) {
            if (iOException instanceof StreamResetException) {
                StreamResetException streamResetException = (StreamResetException) iOException;
                if (streamResetException.errorCode == ErrorCode.REFUSED_STREAM) {
                    this.f17995e++;
                }
                if (streamResetException.errorCode != ErrorCode.REFUSED_STREAM || this.f17995e > 1) {
                    this.f17992b = null;
                }
                z = false;
            } else {
                if (!(this.f17996f == null || this.f17996f.m19766f())) {
                    if (this.f17996f.f17639d == 0) {
                        if (!(this.f17992b == null || iOException == null)) {
                            this.f17994d.m20286a(this.f17992b, iOException);
                        }
                        this.f17992b = null;
                    }
                }
                z = false;
            }
            z = true;
        }
        m20291a(z, false, true);
    }

    /* renamed from: a */
    public void m20298a(C5480b c5480b) {
        c5480b.f17643h.add(new WeakReference(this));
    }

    /* renamed from: b */
    private void m20293b(C5480b c5480b) {
        int size = c5480b.f17643h.size();
        for (int i = 0; i < size; i++) {
            if (((Reference) c5480b.f17643h.get(i)).get() == this) {
                c5480b.f17643h.remove(i);
                return;
            }
        }
        throw new IllegalStateException();
    }

    /* renamed from: f */
    public boolean m20304f() {
        return this.f17992b != null || this.f17994d.m20287a();
    }

    public String toString() {
        return this.f17991a.toString();
    }
}
