package okhttp3;

import com.alipay.sdk.cons.C0845b;
import java.net.Proxy;
import java.net.ProxySelector;
import java.util.List;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import okhttp3.HttpUrl.Builder;
import okhttp3.internal.C5586l;
import org.apache.http.HttpHost;

/* compiled from: Address */
/* renamed from: okhttp3.a */
public final class C5460a {
    /* renamed from: a */
    final HttpUrl f17581a;
    /* renamed from: b */
    final C5598n f17582b;
    /* renamed from: c */
    final SocketFactory f17583c;
    /* renamed from: d */
    final C5461b f17584d;
    /* renamed from: e */
    final List<Protocol> f17585e;
    /* renamed from: f */
    final List<C5590j> f17586f;
    /* renamed from: g */
    final ProxySelector f17587g;
    /* renamed from: h */
    final Proxy f17588h;
    /* renamed from: i */
    final SSLSocketFactory f17589i;
    /* renamed from: j */
    final HostnameVerifier f17590j;
    /* renamed from: k */
    final C5473g f17591k;

    public C5460a(String str, int i, C5598n c5598n, SocketFactory socketFactory, SSLSocketFactory sSLSocketFactory, HostnameVerifier hostnameVerifier, C5473g c5473g, C5461b c5461b, Proxy proxy, List<Protocol> list, List<C5590j> list2, ProxySelector proxySelector) {
        this.f17581a = new Builder().m19644a(sSLSocketFactory != null ? C0845b.f2060a : HttpHost.DEFAULT_SCHEME_NAME).m19647b(str).m19643a(i).m19650c();
        if (c5598n == null) {
            throw new NullPointerException("dns == null");
        }
        this.f17582b = c5598n;
        if (socketFactory == null) {
            throw new NullPointerException("socketFactory == null");
        }
        this.f17583c = socketFactory;
        if (c5461b == null) {
            throw new NullPointerException("proxyAuthenticator == null");
        }
        this.f17584d = c5461b;
        if (list == null) {
            throw new NullPointerException("protocols == null");
        }
        this.f17585e = C5586l.m20321a((List) list);
        if (list2 == null) {
            throw new NullPointerException("connectionSpecs == null");
        }
        this.f17586f = C5586l.m20321a((List) list2);
        if (proxySelector == null) {
            throw new NullPointerException("proxySelector == null");
        }
        this.f17587g = proxySelector;
        this.f17588h = proxy;
        this.f17589i = sSLSocketFactory;
        this.f17590j = hostnameVerifier;
        this.f17591k = c5473g;
    }

    /* renamed from: a */
    public HttpUrl m19686a() {
        return this.f17581a;
    }

    /* renamed from: b */
    public C5598n m19687b() {
        return this.f17582b;
    }

    /* renamed from: c */
    public SocketFactory m19688c() {
        return this.f17583c;
    }

    /* renamed from: d */
    public C5461b m19689d() {
        return this.f17584d;
    }

    /* renamed from: e */
    public List<Protocol> m19690e() {
        return this.f17585e;
    }

    /* renamed from: f */
    public List<C5590j> m19691f() {
        return this.f17586f;
    }

    /* renamed from: g */
    public ProxySelector m19692g() {
        return this.f17587g;
    }

    /* renamed from: h */
    public Proxy m19693h() {
        return this.f17588h;
    }

    /* renamed from: i */
    public SSLSocketFactory m19694i() {
        return this.f17589i;
    }

    /* renamed from: j */
    public HostnameVerifier m19695j() {
        return this.f17590j;
    }

    /* renamed from: k */
    public C5473g m19696k() {
        return this.f17591k;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof C5460a)) {
            return false;
        }
        C5460a c5460a = (C5460a) obj;
        if (this.f17581a.equals(c5460a.f17581a) && this.f17582b.equals(c5460a.f17582b) && this.f17584d.equals(c5460a.f17584d) && this.f17585e.equals(c5460a.f17585e) && this.f17586f.equals(c5460a.f17586f) && this.f17587g.equals(c5460a.f17587g) && C5586l.m20331a(this.f17588h, c5460a.f17588h) && C5586l.m20331a(this.f17589i, c5460a.f17589i) && C5586l.m20331a(this.f17590j, c5460a.f17590j) && C5586l.m20331a(this.f17591k, c5460a.f17591k)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode;
        int i = 0;
        int hashCode2 = (((((((((((this.f17581a.hashCode() + 527) * 31) + this.f17582b.hashCode()) * 31) + this.f17584d.hashCode()) * 31) + this.f17585e.hashCode()) * 31) + this.f17586f.hashCode()) * 31) + this.f17587g.hashCode()) * 31;
        if (this.f17588h != null) {
            hashCode = this.f17588h.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.f17589i != null) {
            hashCode = this.f17589i.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode2 = (hashCode + hashCode2) * 31;
        if (this.f17590j != null) {
            hashCode = this.f17590j.hashCode();
        } else {
            hashCode = 0;
        }
        hashCode = (hashCode + hashCode2) * 31;
        if (this.f17591k != null) {
            i = this.f17591k.hashCode();
        }
        return hashCode + i;
    }
}
