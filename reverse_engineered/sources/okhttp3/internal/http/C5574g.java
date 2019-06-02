package okhttp3.internal.http;

import com.alipay.sdk.cons.C0844a;
import com.google.common.net.HttpHeaders;
import com.loopj.android.http.AsyncHttpClient;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketTimeoutException;
import java.security.cert.CertificateException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.C5460a;
import okhttp3.C5473g;
import okhttp3.C5474h;
import okhttp3.C5569z;
import okhttp3.C5591k;
import okhttp3.C5592l;
import okhttp3.C5596r;
import okhttp3.C5596r.C5572a;
import okhttp3.C5607q;
import okhttp3.C5607q.C5606a;
import okhttp3.C5608s;
import okhttp3.C5614u;
import okhttp3.C5621w;
import okhttp3.C5621w.C5620a;
import okhttp3.C5627y;
import okhttp3.C5627y.C5626a;
import okhttp3.HttpUrl;
import okhttp3.Protocol;
import okhttp3.aa;
import okhttp3.internal.C5497d;
import okhttp3.internal.C5498e;
import okhttp3.internal.C5586l;
import okhttp3.internal.C5587m;
import okhttp3.internal.http.C5554b.C5553a;
import okio.C5492r;
import okio.C5520s;
import okio.C5522t;
import okio.C5635d;
import okio.C5636e;
import okio.C5637c;
import okio.C5642k;
import okio.C5647m;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;

/* compiled from: HttpEngine */
/* renamed from: okhttp3.internal.http.g */
public final class C5574g {
    /* renamed from: e */
    private static final C5569z f17951e = new C55701();
    /* renamed from: a */
    final C5614u f17952a;
    /* renamed from: b */
    public final C5582p f17953b;
    /* renamed from: c */
    long f17954c = -1;
    /* renamed from: d */
    public final boolean f17955d;
    /* renamed from: f */
    private final C5627y f17956f;
    /* renamed from: g */
    private C5563i f17957g;
    /* renamed from: h */
    private boolean f17958h;
    /* renamed from: i */
    private final C5621w f17959i;
    /* renamed from: j */
    private C5621w f17960j;
    /* renamed from: k */
    private C5627y f17961k;
    /* renamed from: l */
    private C5627y f17962l;
    /* renamed from: m */
    private C5492r f17963m;
    /* renamed from: n */
    private C5635d f17964n;
    /* renamed from: o */
    private final boolean f17965o;
    /* renamed from: p */
    private final boolean f17966p;
    /* renamed from: q */
    private C5551a f17967q;
    /* renamed from: r */
    private C5554b f17968r;

    /* compiled from: HttpEngine */
    /* renamed from: okhttp3.internal.http.g$1 */
    static class C55701 extends C5569z {
        C55701() {
        }

        public C5608s contentType() {
            return null;
        }

        public long contentLength() {
            return 0;
        }

        public C5636e source() {
            return new C5637c();
        }
    }

    /* compiled from: HttpEngine */
    /* renamed from: okhttp3.internal.http.g$a */
    class C5573a implements C5572a {
        /* renamed from: a */
        final /* synthetic */ C5574g f17946a;
        /* renamed from: b */
        private final int f17947b;
        /* renamed from: c */
        private final C5621w f17948c;
        /* renamed from: d */
        private final C5474h f17949d;
        /* renamed from: e */
        private int f17950e;

        C5573a(C5574g c5574g, int i, C5621w c5621w, C5474h c5474h) {
            this.f17946a = c5574g;
            this.f17947b = i;
            this.f17948c = c5621w;
            this.f17949d = c5474h;
        }

        /* renamed from: b */
        public C5474h mo6760b() {
            return this.f17949d;
        }

        /* renamed from: a */
        public C5621w mo6758a() {
            return this.f17948c;
        }

        /* renamed from: a */
        public C5627y mo6759a(C5621w c5621w) throws IOException {
            C5596r c5596r;
            this.f17950e++;
            if (this.f17947b > 0) {
                c5596r = (C5596r) this.f17946a.f17952a.m20480w().get(this.f17947b - 1);
                C5460a a = mo6760b().mo6694a().m19697a();
                if (!c5621w.m20519a().m19676f().equals(a.m19686a().m19676f()) || c5621w.m20519a().m19677g() != a.m19686a().m19677g()) {
                    throw new IllegalStateException("network interceptor " + c5596r + " must retain the same host and port");
                } else if (this.f17950e > 1) {
                    throw new IllegalStateException("network interceptor " + c5596r + " must call proceed() exactly once");
                }
            }
            if (this.f17947b < this.f17946a.f17952a.m20480w().size()) {
                Object c5573a = new C5573a(this.f17946a, this.f17947b + 1, c5621w, this.f17949d);
                c5596r = (C5596r) this.f17946a.f17952a.m20480w().get(this.f17947b);
                C5627y intercept = c5596r.intercept(c5573a);
                if (c5573a.f17950e != 1) {
                    throw new IllegalStateException("network interceptor " + c5596r + " must call proceed() exactly once");
                } else if (intercept != null) {
                    return intercept;
                } else {
                    throw new NullPointerException("network interceptor " + c5596r + " returned null");
                }
            }
            this.f17946a.f17957g.mo6752a(c5621w);
            this.f17946a.f17960j = c5621w;
            if (this.f17946a.m20253a(c5621w) && c5621w.m20522d() != null) {
                C5635d a2 = C5647m.m20713a(this.f17946a.f17957g.mo6748a(c5621w, c5621w.m20522d().contentLength()));
                c5621w.m20522d().writeTo(a2);
                a2.close();
            }
            C5627y b = this.f17946a.m20248m();
            int b2 = b.m20570b();
            if ((b2 != 204 && b2 != 205) || b.m20575g().contentLength() <= 0) {
                return b;
            }
            throw new ProtocolException("HTTP " + b2 + " had non-zero Content-Length: " + b.m20575g().contentLength());
        }
    }

    public C5574g(C5614u c5614u, C5621w c5621w, boolean z, boolean z2, boolean z3, C5582p c5582p, C5579m c5579m, C5627y c5627y) {
        this.f17952a = c5614u;
        this.f17959i = c5621w;
        this.f17955d = z;
        this.f17965o = z2;
        this.f17966p = z3;
        if (c5582p == null) {
            c5582p = new C5582p(c5614u.m20472o(), C5574g.m20233a(c5614u, c5621w));
        }
        this.f17953b = c5582p;
        this.f17963m = c5579m;
        this.f17956f = c5627y;
    }

    /* renamed from: a */
    public void m20250a() throws RequestException, RouteException, IOException {
        if (this.f17968r == null) {
            if (this.f17957g != null) {
                throw new IllegalStateException();
            }
            C5621w b = m20241b(this.f17959i);
            C5498e a = C5497d.f17702a.mo6770a(this.f17952a);
            C5627y a2 = a != null ? a.m19845a(b) : null;
            this.f17968r = new C5553a(System.currentTimeMillis(), b, a2).m20168a();
            this.f17960j = this.f17968r.f17896a;
            this.f17961k = this.f17968r.f17897b;
            if (a != null) {
                a.m19847a(this.f17968r);
            }
            if (a2 != null && this.f17961k == null) {
                C5586l.m20327a(a2.m20575g());
            }
            if (this.f17960j == null && this.f17961k == null) {
                this.f17962l = new C5626a().m20548a(this.f17959i).m20554c(C5574g.m20243b(this.f17956f)).m20545a(Protocol.HTTP_1_1).m20541a(504).m20543a("Unsatisfiable Request (only-if-cached)").m20550a(f17951e).m20542a(this.f17954c).m20552b(System.currentTimeMillis()).m20551a();
            } else if (this.f17960j == null) {
                this.f17962l = this.f17961k.m20576h().m20548a(this.f17959i).m20554c(C5574g.m20243b(this.f17956f)).m20553b(C5574g.m20243b(this.f17961k)).m20551a();
                this.f17962l = m20244c(this.f17962l);
            } else {
                try {
                    this.f17957g = m20246k();
                    this.f17957g.mo6750a(this);
                    if (m20245j()) {
                        long a3 = C5576j.m20267a(b);
                        if (!this.f17955d) {
                            this.f17957g.mo6752a(this.f17960j);
                            this.f17963m = this.f17957g.mo6748a(this.f17960j, a3);
                        } else if (a3 > 2147483647L) {
                            throw new IllegalStateException("Use setFixedLengthStreamingMode() or setChunkedStreamingMode() for requests larger than 2 GiB.");
                        } else if (a3 != -1) {
                            this.f17957g.mo6752a(this.f17960j);
                            this.f17963m = new C5579m((int) a3);
                        } else {
                            this.f17963m = new C5579m();
                        }
                    }
                } catch (Throwable th) {
                    if (a2 != null) {
                        C5586l.m20327a(a2.m20575g());
                    }
                }
            }
        }
    }

    /* renamed from: j */
    private boolean m20245j() {
        return this.f17965o && m20253a(this.f17960j) && this.f17963m == null;
    }

    /* renamed from: k */
    private C5563i m20246k() throws RouteException, RequestException, IOException {
        return this.f17953b.m20296a(this.f17952a.m20457a(), this.f17952a.m20459b(), this.f17952a.m20460c(), this.f17952a.m20475r(), !this.f17960j.m20520b().equals("GET"));
    }

    /* renamed from: b */
    private static C5627y m20243b(C5627y c5627y) {
        if (c5627y == null || c5627y.m20575g() == null) {
            return c5627y;
        }
        return c5627y.m20576h().m20550a(null).m20551a();
    }

    /* renamed from: b */
    public void m20254b() {
        if (this.f17954c != -1) {
            throw new IllegalStateException();
        }
        this.f17954c = System.currentTimeMillis();
    }

    /* renamed from: a */
    boolean m20253a(C5621w c5621w) {
        return C5575h.m20264c(c5621w.m20520b());
    }

    /* renamed from: c */
    public C5627y m20255c() {
        if (this.f17962l != null) {
            return this.f17962l;
        }
        throw new IllegalStateException();
    }

    /* renamed from: d */
    public C5474h m20256d() {
        return this.f17953b.m20300b();
    }

    /* renamed from: a */
    public C5574g m20249a(IOException iOException, boolean z, C5492r c5492r) {
        this.f17953b.m20297a(iOException);
        if (!this.f17952a.m20475r()) {
            return null;
        }
        if ((c5492r != null && !(c5492r instanceof C5579m)) || !m20238a(iOException, z) || !this.f17953b.m20304f()) {
            return null;
        }
        return new C5574g(this.f17952a, this.f17959i, this.f17955d, this.f17965o, this.f17966p, m20259g(), (C5579m) c5492r, this.f17956f);
    }

    /* renamed from: a */
    private boolean m20238a(IOException iOException, boolean z) {
        boolean z2 = true;
        if (iOException instanceof ProtocolException) {
            return false;
        }
        if (iOException instanceof InterruptedIOException) {
            if (!((iOException instanceof SocketTimeoutException) && z)) {
                z2 = false;
            }
            return z2;
        } else if (((iOException instanceof SSLHandshakeException) && (iOException.getCause() instanceof CertificateException)) || (iOException instanceof SSLPeerUnverifiedException)) {
            return false;
        } else {
            return true;
        }
    }

    /* renamed from: l */
    private void m20247l() throws IOException {
        C5498e a = C5497d.f17702a.mo6770a(this.f17952a);
        if (a != null) {
            if (C5554b.m20169a(this.f17962l, this.f17960j)) {
                this.f17967q = a.m19844a(this.f17962l);
            } else if (C5575h.m20262a(this.f17960j.m20520b())) {
                try {
                    a.m19849b(this.f17960j);
                } catch (IOException e) {
                }
            }
        }
    }

    /* renamed from: e */
    public void m20257e() throws IOException {
        this.f17953b.m20301c();
    }

    /* renamed from: f */
    public void m20258f() {
        this.f17953b.m20303e();
    }

    /* renamed from: g */
    public C5582p m20259g() {
        if (this.f17964n != null) {
            C5586l.m20327a(this.f17964n);
        } else if (this.f17963m != null) {
            C5586l.m20327a(this.f17963m);
        }
        if (this.f17962l != null) {
            C5586l.m20327a(this.f17962l.m20575g());
        } else {
            this.f17953b.m20297a(null);
        }
        return this.f17953b;
    }

    /* renamed from: c */
    private C5627y m20244c(C5627y c5627y) throws IOException {
        if (!this.f17958h || !AsyncHttpClient.ENCODING_GZIP.equalsIgnoreCase(this.f17962l.m20567a("Content-Encoding")) || c5627y.m20575g() == null) {
            return c5627y;
        }
        C5520s c5642k = new C5642k(c5627y.m20575g().source());
        C5607q a = c5627y.m20574f().m20416b().m20406b("Content-Encoding").m20406b("Content-Length").m20405a();
        return c5627y.m20576h().m20547a(a).m20550a(new C5577k(a, C5647m.m20714a(c5642k))).m20551a();
    }

    /* renamed from: a */
    public static boolean m20239a(C5627y c5627y) {
        if (c5627y.m20569a().m20520b().equals("HEAD")) {
            return false;
        }
        int b = c5627y.m20570b();
        if ((b < 100 || b >= 200) && b != 204 && b != HttpStatus.SC_NOT_MODIFIED) {
            return true;
        }
        if (C5576j.m20268a(c5627y) != -1 || HTTP.CHUNK_CODING.equalsIgnoreCase(c5627y.m20567a("Transfer-Encoding"))) {
            return true;
        }
        return false;
    }

    /* renamed from: b */
    private C5621w m20241b(C5621w c5621w) throws IOException {
        C5620a f = c5621w.m20524f();
        if (c5621w.m20518a("Host") == null) {
            f.m20505a("Host", C5586l.m20320a(c5621w.m20519a(), false));
        }
        if (c5621w.m20518a("Connection") == null) {
            f.m20505a("Connection", HTTP.CONN_KEEP_ALIVE);
        }
        if (c5621w.m20518a("Accept-Encoding") == null) {
            this.f17958h = true;
            f.m20505a("Accept-Encoding", AsyncHttpClient.ENCODING_GZIP);
        }
        List a = this.f17952a.m20463f().mo6761a(c5621w.m20519a());
        if (!a.isEmpty()) {
            f.m20505a(HttpHeaders.COOKIE, m20232a(a));
        }
        if (c5621w.m20518a("User-Agent") == null) {
            f.m20505a("User-Agent", C5587m.m20343a());
        }
        return f.m20510a();
    }

    /* renamed from: a */
    private String m20232a(List<C5591k> list) {
        StringBuilder stringBuilder = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                stringBuilder.append("; ");
            }
            C5591k c5591k = (C5591k) list.get(i);
            stringBuilder.append(c5591k.m20374a()).append('=').append(c5591k.m20375b());
        }
        return stringBuilder.toString();
    }

    /* renamed from: h */
    public void m20260h() throws IOException {
        if (this.f17962l == null) {
            if (this.f17960j == null && this.f17961k == null) {
                throw new IllegalStateException("call sendRequest() first!");
            } else if (this.f17960j != null) {
                C5627y m;
                if (this.f17966p) {
                    this.f17957g.mo6752a(this.f17960j);
                    m = m20248m();
                } else if (this.f17965o) {
                    if (this.f17964n != null && this.f17964n.mo6810b().m20623a() > 0) {
                        this.f17964n.mo6817e();
                    }
                    if (this.f17954c == -1) {
                        if (C5576j.m20267a(this.f17960j) == -1 && (this.f17963m instanceof C5579m)) {
                            this.f17960j = this.f17960j.m20524f().m20505a("Content-Length", Long.toString(((C5579m) this.f17963m).m20274a())).m20510a();
                        }
                        this.f17957g.mo6752a(this.f17960j);
                    }
                    if (this.f17963m != null) {
                        if (this.f17964n != null) {
                            this.f17964n.close();
                        } else {
                            this.f17963m.close();
                        }
                        if (this.f17963m instanceof C5579m) {
                            this.f17957g.mo6751a((C5579m) this.f17963m);
                        }
                    }
                    m = m20248m();
                } else {
                    m = new C5573a(this, 0, this.f17960j, this.f17953b.m20300b()).mo6759a(this.f17960j);
                }
                m20251a(m.m20574f());
                if (this.f17961k != null) {
                    if (C5574g.m20240a(this.f17961k, m)) {
                        this.f17962l = this.f17961k.m20576h().m20548a(this.f17959i).m20554c(C5574g.m20243b(this.f17956f)).m20547a(C5574g.m20235a(this.f17961k.m20574f(), m.m20574f())).m20553b(C5574g.m20243b(this.f17961k)).m20549a(C5574g.m20243b(m)).m20551a();
                        m.m20575g().close();
                        m20257e();
                        C5498e a = C5497d.f17702a.mo6770a(this.f17952a);
                        a.m19846a();
                        a.m19848a(this.f17961k, this.f17962l);
                        this.f17962l = m20244c(this.f17962l);
                        return;
                    }
                    C5586l.m20327a(this.f17961k.m20575g());
                }
                this.f17962l = m.m20576h().m20548a(this.f17959i).m20554c(C5574g.m20243b(this.f17956f)).m20553b(C5574g.m20243b(this.f17961k)).m20549a(C5574g.m20243b(m)).m20551a();
                if (C5574g.m20239a(this.f17962l)) {
                    m20247l();
                    this.f17962l = m20244c(m20237a(this.f17967q, this.f17962l));
                }
            }
        }
    }

    /* renamed from: m */
    private C5627y m20248m() throws IOException {
        this.f17957g.mo6754c();
        C5627y a = this.f17957g.mo6753b().m20548a(this.f17960j).m20546a(this.f17953b.m20300b().m19765e()).m20542a(this.f17954c).m20552b(System.currentTimeMillis()).m20551a();
        if (!(this.f17966p && a.m20570b() == 101)) {
            a = a.m20576h().m20550a(this.f17957g.mo6747a(a)).m20551a();
        }
        if ("close".equalsIgnoreCase(a.m20569a().m20518a("Connection")) || "close".equalsIgnoreCase(a.m20567a("Connection"))) {
            this.f17953b.m20302d();
        }
        return a;
    }

    /* renamed from: a */
    private C5627y m20237a(final C5551a c5551a, C5627y c5627y) throws IOException {
        if (c5551a == null) {
            return c5627y;
        }
        C5492r a = c5551a.m20161a();
        if (a == null) {
            return c5627y;
        }
        final C5636e source = c5627y.m20575g().source();
        final C5635d a2 = C5647m.m20713a(a);
        return c5627y.m20576h().m20550a(new C5577k(c5627y.m20574f(), C5647m.m20714a(new C5520s(this) {
            /* renamed from: a */
            boolean f17941a;
            /* renamed from: e */
            final /* synthetic */ C5574g f17945e;

            public long read(C5637c c5637c, long j) throws IOException {
                try {
                    long read = source.read(c5637c, j);
                    if (read == -1) {
                        if (!this.f17941a) {
                            this.f17941a = true;
                            a2.close();
                        }
                        return -1;
                    }
                    c5637c.m20636a(a2.mo6810b(), c5637c.m20623a() - read, read);
                    a2.mo6836w();
                    return read;
                } catch (IOException e) {
                    if (!this.f17941a) {
                        this.f17941a = true;
                        c5551a.m20162b();
                    }
                    throw e;
                }
            }

            public C5522t timeout() {
                return source.timeout();
            }

            public void close() throws IOException {
                if (!(this.f17941a || C5586l.m20332a((C5520s) this, 100, TimeUnit.MILLISECONDS))) {
                    this.f17941a = true;
                    c5551a.m20162b();
                }
                source.close();
            }
        }))).m20551a();
    }

    /* renamed from: a */
    private static boolean m20240a(C5627y c5627y, C5627y c5627y2) {
        if (c5627y2.m20570b() == HttpStatus.SC_NOT_MODIFIED) {
            return true;
        }
        Date b = c5627y.m20574f().m20415b("Last-Modified");
        if (b != null) {
            Date b2 = c5627y2.m20574f().m20415b("Last-Modified");
            if (b2 != null && b2.getTime() < b.getTime()) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: a */
    private static C5607q m20235a(C5607q c5607q, C5607q c5607q2) throws IOException {
        int i;
        int i2 = 0;
        C5606a c5606a = new C5606a();
        int a = c5607q.m20411a();
        for (i = 0; i < a; i++) {
            String a2 = c5607q.m20412a(i);
            String b = c5607q.m20414b(i);
            if (!("Warning".equalsIgnoreCase(a2) && b.startsWith(C0844a.f2048d)) && (!C5576j.m20269a(a2) || c5607q2.m20413a(a2) == null)) {
                C5497d.f17702a.mo6774a(c5606a, a2, b);
            }
        }
        i = c5607q2.m20411a();
        while (i2 < i) {
            String a3 = c5607q2.m20412a(i2);
            if (!"Content-Length".equalsIgnoreCase(a3) && C5576j.m20269a(a3)) {
                C5497d.f17702a.mo6774a(c5606a, a3, c5607q2.m20414b(i2));
            }
            i2++;
        }
        return c5606a.m20405a();
    }

    /* renamed from: a */
    public void m20251a(C5607q c5607q) throws IOException {
        if (this.f17952a.m20463f() != C5592l.f18036a) {
            List a = C5591k.m20369a(this.f17959i.m20519a(), c5607q);
            if (!a.isEmpty()) {
                this.f17952a.m20463f().mo6762a(this.f17959i.m20519a(), a);
            }
        }
    }

    /* renamed from: i */
    public C5621w m20261i() throws IOException {
        if (this.f17962l == null) {
            throw new IllegalStateException();
        }
        C5474h b = this.f17953b.m20300b();
        aa a = b != null ? b.mo6694a() : null;
        int b2 = this.f17962l.m20570b();
        String b3 = this.f17959i.m20520b();
        switch (b2) {
            case 300:
            case HttpStatus.SC_MOVED_PERMANENTLY /*301*/:
            case HttpStatus.SC_MOVED_TEMPORARILY /*302*/:
            case HttpStatus.SC_SEE_OTHER /*303*/:
                break;
            case HttpStatus.SC_TEMPORARY_REDIRECT /*307*/:
            case 308:
                if (!(b3.equals("GET") || b3.equals("HEAD"))) {
                    return null;
                }
            case HttpStatus.SC_UNAUTHORIZED /*401*/:
                return this.f17952a.m20470m().mo6689a(a, this.f17962l);
            case HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED /*407*/:
                Proxy b4;
                if (a != null) {
                    b4 = a.m19698b();
                } else {
                    b4 = this.f17952a.m20461d();
                }
                if (b4.type() == Type.HTTP) {
                    return this.f17952a.m20471n().mo6689a(a, this.f17962l);
                }
                throw new ProtocolException("Received HTTP_PROXY_AUTH (407) code while not using proxy");
            case HttpStatus.SC_REQUEST_TIMEOUT /*408*/:
                Object obj = (this.f17963m == null || (this.f17963m instanceof C5579m)) ? 1 : null;
                if (this.f17965o && obj == null) {
                    return null;
                }
                return this.f17959i;
            default:
                return null;
        }
        if (!this.f17952a.m20474q()) {
            return null;
        }
        String a2 = this.f17962l.m20567a("Location");
        if (a2 == null) {
            return null;
        }
        HttpUrl c = this.f17959i.m20519a().m19671c(a2);
        if (c == null) {
            return null;
        }
        if (!c.m19670b().equals(this.f17959i.m20519a().m19670b()) && !this.f17952a.m20473p()) {
            return null;
        }
        C5620a f = this.f17959i.m20524f();
        if (C5575h.m20264c(b3)) {
            if (C5575h.m20265d(b3)) {
                f.m20506a("GET", null);
            } else {
                f.m20506a(b3, null);
            }
            f.m20511b("Transfer-Encoding");
            f.m20511b("Content-Length");
            f.m20511b("Content-Type");
        }
        if (!m20252a(c)) {
            f.m20511b("Authorization");
        }
        return f.m20507a(c).m20510a();
    }

    /* renamed from: a */
    public boolean m20252a(HttpUrl httpUrl) {
        HttpUrl a = this.f17959i.m20519a();
        return a.m19676f().equals(httpUrl.m19676f()) && a.m19677g() == httpUrl.m19677g() && a.m19670b().equals(httpUrl.m19670b());
    }

    /* renamed from: a */
    private static C5460a m20233a(C5614u c5614u, C5621w c5621w) {
        SSLSocketFactory j;
        HostnameVerifier k;
        C5473g c5473g = null;
        if (c5621w.m20526h()) {
            j = c5614u.m20467j();
            k = c5614u.m20468k();
            c5473g = c5614u.m20469l();
        } else {
            k = null;
            j = null;
        }
        return new C5460a(c5621w.m20519a().m19676f(), c5621w.m20519a().m19677g(), c5614u.m20465h(), c5614u.m20466i(), j, k, c5473g, c5614u.m20471n(), c5614u.m20461d(), c5614u.m20477t(), c5614u.m20478u(), c5614u.m20462e());
    }
}
