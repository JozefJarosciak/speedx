package okhttp3.internal.p206a;

import ch.qos.logback.core.CoreConstants;
import java.io.IOException;
import java.lang.ref.Reference;
import java.net.ConnectException;
import java.net.ProtocolException;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLSocket;
import okhttp3.C5460a;
import okhttp3.C5473g;
import okhttp3.C5474h;
import okhttp3.C5590j;
import okhttp3.C5604p;
import okhttp3.C5621w;
import okhttp3.C5621w.C5620a;
import okhttp3.C5627y;
import okhttp3.HttpUrl;
import okhttp3.Protocol;
import okhttp3.aa;
import okhttp3.internal.C5481j;
import okhttp3.internal.C5491b;
import okhttp3.internal.C5586l;
import okhttp3.internal.C5587m;
import okhttp3.internal.framed.C5517c;
import okhttp3.internal.framed.C5517c.C5479b;
import okhttp3.internal.framed.C5517c.C5511a;
import okhttp3.internal.framed.C5525d;
import okhttp3.internal.framed.ErrorCode;
import okhttp3.internal.http.C5564d;
import okhttp3.internal.http.C5576j;
import okhttp3.internal.http.C5582p;
import okhttp3.internal.http.RouteException;
import okhttp3.internal.p207b.C5487c;
import okio.C5520s;
import okio.C5635d;
import okio.C5636e;
import okio.C5647m;
import org.apache.http.HttpStatus;
import org.apache.http.protocol.HTTP;

/* compiled from: RealConnection */
/* renamed from: okhttp3.internal.a.b */
public final class C5480b extends C5479b implements C5474h {
    /* renamed from: b */
    public Socket f17637b;
    /* renamed from: c */
    public volatile C5517c f17638c;
    /* renamed from: d */
    public int f17639d;
    /* renamed from: e */
    public C5636e f17640e;
    /* renamed from: f */
    public C5635d f17641f;
    /* renamed from: g */
    public int f17642g;
    /* renamed from: h */
    public final List<Reference<C5582p>> f17643h = new ArrayList();
    /* renamed from: i */
    public boolean f17644i;
    /* renamed from: j */
    public long f17645j = Long.MAX_VALUE;
    /* renamed from: k */
    private final aa f17646k;
    /* renamed from: l */
    private Socket f17647l;
    /* renamed from: m */
    private C5604p f17648m;
    /* renamed from: n */
    private Protocol f17649n;

    public C5480b(aa aaVar) {
        this.f17646k = aaVar;
    }

    /* renamed from: a */
    public void m19758a(int i, int i2, int i3, List<C5590j> list, boolean z) throws RouteException {
        if (this.f17649n != null) {
            throw new IllegalStateException("already connected");
        }
        C5491b c5491b = new C5491b(list);
        if (this.f17646k.m19697a().m19694i() != null || list.contains(C5590j.f18017c)) {
            RouteException routeException = null;
            while (this.f17649n == null) {
                try {
                    if (this.f17646k.m19700d()) {
                        m19751a(i, i2, i3, c5491b);
                    } else {
                        m19753b(i, i2, i3, c5491b);
                    }
                } catch (IOException e) {
                    C5586l.m20329a(this.f17637b);
                    C5586l.m20329a(this.f17647l);
                    this.f17637b = null;
                    this.f17647l = null;
                    this.f17640e = null;
                    this.f17641f = null;
                    this.f17648m = null;
                    this.f17649n = null;
                    if (routeException == null) {
                        routeException = new RouteException(e);
                    } else {
                        routeException.addConnectException(e);
                    }
                    if (!z || !c5491b.m19810a(e)) {
                        throw routeException;
                    }
                }
            }
            return;
        }
        throw new RouteException(new UnknownServiceException("CLEARTEXT communication not supported: " + list));
    }

    /* renamed from: a */
    private void m19751a(int i, int i2, int i3, C5491b c5491b) throws IOException {
        C5621w g = m19756g();
        HttpUrl a = g.m20519a();
        int i4 = 0;
        while (true) {
            i4++;
            if (i4 > 21) {
                throw new ProtocolException("Too many tunnel connections attempted: " + 21);
            }
            m19755c(i, i2, i3, c5491b);
            g = m19750a(i2, i3, g, a);
            if (g == null) {
                m19752a(i2, i3, c5491b);
                return;
            }
            C5586l.m20329a(this.f17647l);
            this.f17647l = null;
            this.f17641f = null;
            this.f17640e = null;
        }
    }

    /* renamed from: b */
    private void m19753b(int i, int i2, int i3, C5491b c5491b) throws IOException {
        m19755c(i, i2, i3, c5491b);
        m19752a(i2, i3, c5491b);
    }

    /* renamed from: c */
    private void m19755c(int i, int i2, int i3, C5491b c5491b) throws IOException {
        Proxy b = this.f17646k.m19698b();
        Socket createSocket = (b.type() == Type.DIRECT || b.type() == Type.HTTP) ? this.f17646k.m19697a().m19688c().createSocket() : new Socket(b);
        this.f17647l = createSocket;
        this.f17647l.setSoTimeout(i2);
        try {
            C5481j.m19770c().mo6701a(this.f17647l, this.f17646k.m19699c(), i);
            this.f17640e = C5647m.m20714a(C5647m.m20721b(this.f17647l));
            this.f17641f = C5647m.m20713a(C5647m.m20716a(this.f17647l));
        } catch (ConnectException e) {
            throw new ConnectException("Failed to connect to " + this.f17646k.m19699c());
        }
    }

    /* renamed from: a */
    private void m19752a(int i, int i2, C5491b c5491b) throws IOException {
        if (this.f17646k.m19697a().m19694i() != null) {
            m19754b(i, i2, c5491b);
        } else {
            this.f17649n = Protocol.HTTP_1_1;
            this.f17637b = this.f17647l;
        }
        if (this.f17649n == Protocol.SPDY_3 || this.f17649n == Protocol.HTTP_2) {
            this.f17637b.setSoTimeout(0);
            C5517c a = new C5511a(true).m19893a(this.f17637b, this.f17646k.m19697a().m19686a().m19676f(), this.f17640e, this.f17641f).m19894a(this.f17649n).m19895a((C5479b) this).m19896a();
            a.m19959d();
            this.f17642g = a.m19955b();
            this.f17638c = a;
            return;
        }
        this.f17642g = 1;
    }

    /* renamed from: b */
    private void m19754b(int i, int i2, C5491b c5491b) throws IOException {
        Throwable th;
        Socket socket;
        AssertionError assertionError;
        Throwable th2;
        String str = null;
        C5460a a = this.f17646k.m19697a();
        try {
            Socket socket2 = (SSLSocket) a.m19694i().createSocket(this.f17647l, a.m19686a().m19676f(), a.m19686a().m19677g(), true);
            try {
                C5590j a2 = c5491b.m19809a((SSLSocket) socket2);
                if (a2.m20365d()) {
                    C5481j.m19770c().mo6702a((SSLSocket) socket2, a.m19686a().m19676f(), a.m19690e());
                }
                socket2.startHandshake();
                C5604p a3 = C5604p.m20398a(socket2.getSession());
                if (a.m19695j().verify(a.m19686a().m19676f(), socket2.getSession())) {
                    a.m19696k().m19731a(a.m19686a().m19676f(), a3.m20400b());
                    if (a2.m20365d()) {
                        str = C5481j.m19770c().mo6699a((SSLSocket) socket2);
                    }
                    this.f17637b = socket2;
                    this.f17640e = C5647m.m20714a(C5647m.m20721b(this.f17637b));
                    this.f17641f = C5647m.m20713a(C5647m.m20716a(this.f17637b));
                    this.f17648m = a3;
                    this.f17649n = str != null ? Protocol.get(str) : Protocol.HTTP_1_1;
                    if (socket2 != null) {
                        C5481j.m19770c().mo6746b((SSLSocket) socket2);
                        return;
                    }
                    return;
                }
                Certificate certificate = (X509Certificate) a3.m20400b().get(0);
                throw new SSLPeerUnverifiedException("Hostname " + a.m19686a().m19676f() + " not verified:\n    certificate: " + C5473g.m19726a(certificate) + "\n    DN: " + certificate.getSubjectDN().getName() + "\n    subjectAltNames: " + C5487c.m19797a(certificate));
            } catch (Throwable e) {
                th = e;
                socket = socket2;
                assertionError = th;
                try {
                    if (C5586l.m20330a(assertionError)) {
                        throw new IOException(assertionError);
                    }
                    throw assertionError;
                } catch (Throwable th3) {
                    th2 = th3;
                }
            } catch (Throwable e2) {
                th = e2;
                socket = socket2;
                th2 = th;
                if (socket != null) {
                    C5481j.m19770c().mo6746b((SSLSocket) socket);
                }
                C5586l.m20329a(socket);
                throw th2;
            }
        } catch (AssertionError e3) {
            assertionError = e3;
            if (C5586l.m20330a(assertionError)) {
                throw new IOException(assertionError);
            }
            throw assertionError;
        }
    }

    /* renamed from: a */
    private C5621w m19750a(int i, int i2, C5621w c5621w, HttpUrl httpUrl) throws IOException {
        String str = "CONNECT " + C5586l.m20320a(httpUrl, true) + " HTTP/1.1";
        C5627y a;
        do {
            C5564d c5564d = new C5564d(null, this.f17640e, this.f17641f);
            this.f17640e.timeout().mo6838a((long) i, TimeUnit.MILLISECONDS);
            this.f17641f.timeout().mo6838a((long) i2, TimeUnit.MILLISECONDS);
            c5564d.m20199a(c5621w.m20521c(), str);
            c5564d.mo6754c();
            a = c5564d.m20205d().m20548a(c5621w).m20551a();
            long a2 = C5576j.m20268a(a);
            if (a2 == -1) {
                a2 = 0;
            }
            C5520s b = c5564d.m20202b(a2);
            C5586l.m20338b(b, Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
            b.close();
            switch (a.m20570b()) {
                case 200:
                    if (this.f17640e.mo6810b().mo6818f() && this.f17641f.mo6810b().mo6818f()) {
                        return null;
                    }
                    throw new IOException("TLS tunnel buffered too many bytes!");
                case HttpStatus.SC_PROXY_AUTHENTICATION_REQUIRED /*407*/:
                    c5621w = this.f17646k.m19697a().m19689d().mo6689a(this.f17646k, a);
                    if (c5621w != null) {
                        break;
                    }
                    throw new IOException("Failed to authenticate with proxy");
                default:
                    throw new IOException("Unexpected response code for CONNECT: " + a.m20570b());
            }
        } while (!"close".equalsIgnoreCase(a.m20567a("Connection")));
        return c5621w;
    }

    /* renamed from: g */
    private C5621w m19756g() throws IOException {
        return new C5620a().m20507a(this.f17646k.m19697a().m19686a()).m20505a("Host", C5586l.m20320a(this.f17646k.m19697a().m19686a(), true)).m20505a("Proxy-Connection", HTTP.CONN_KEEP_ALIVE).m20505a("User-Agent", C5587m.m20343a()).m20510a();
    }

    /* renamed from: a */
    public aa mo6694a() {
        return this.f17646k;
    }

    /* renamed from: d */
    public void m19764d() {
        C5586l.m20329a(this.f17647l);
    }

    /* renamed from: b */
    public Socket mo6697b() {
        return this.f17637b;
    }

    /* renamed from: a */
    public boolean m19761a(boolean z) {
        if (this.f17637b.isClosed() || this.f17637b.isInputShutdown() || this.f17637b.isOutputShutdown()) {
            return false;
        }
        if (this.f17638c != null || !z) {
            return true;
        }
        int soTimeout;
        try {
            soTimeout = this.f17637b.getSoTimeout();
            this.f17637b.setSoTimeout(1);
            if (this.f17640e.mo6818f()) {
                this.f17637b.setSoTimeout(soTimeout);
                return false;
            }
            this.f17637b.setSoTimeout(soTimeout);
            return true;
        } catch (SocketTimeoutException e) {
            return true;
        } catch (IOException e2) {
            return false;
        } catch (Throwable th) {
            this.f17637b.setSoTimeout(soTimeout);
        }
    }

    /* renamed from: a */
    public void mo6696a(C5525d c5525d) throws IOException {
        c5525d.m20005a(ErrorCode.REFUSED_STREAM);
    }

    /* renamed from: a */
    public void mo6695a(C5517c c5517c) {
        this.f17642g = c5517c.m19955b();
    }

    /* renamed from: e */
    public C5604p m19765e() {
        return this.f17648m;
    }

    /* renamed from: f */
    public boolean m19766f() {
        return this.f17638c != null;
    }

    /* renamed from: c */
    public Protocol mo6698c() {
        if (this.f17638c == null) {
            return this.f17649n != null ? this.f17649n : Protocol.HTTP_1_1;
        } else {
            return this.f17638c.m19946a();
        }
    }

    public String toString() {
        return "Connection{" + this.f17646k.m19697a().m19686a().m19676f() + ":" + this.f17646k.m19697a().m19686a().m19677g() + ", proxy=" + this.f17646k.m19698b() + " hostAddress=" + this.f17646k.m19699c() + " cipherSuite=" + (this.f17648m != null ? this.f17648m.m20399a() : "none") + " protocol=" + this.f17649n + CoreConstants.CURLY_RIGHT;
    }
}
