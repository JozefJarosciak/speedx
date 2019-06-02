package okhttp3;

import java.net.Proxy;
import java.net.ProxySelector;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.net.SocketFactory;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;
import okhttp3.C5468e.C5467a;
import okhttp3.C5607q.C5606a;
import okhttp3.internal.C5481j;
import okhttp3.internal.C5497d;
import okhttp3.internal.C5498e;
import okhttp3.internal.C5584k;
import okhttp3.internal.C5586l;
import okhttp3.internal.http.C5582p;
import okhttp3.internal.p206a.C5480b;
import okhttp3.internal.p207b.C5483a;
import okhttp3.internal.p207b.C5487c;

/* compiled from: OkHttpClient */
/* renamed from: okhttp3.u */
public class C5614u implements Cloneable, C5467a {
    /* renamed from: A */
    private static final List<C5590j> f18108A;
    /* renamed from: z */
    private static final List<Protocol> f18109z = C5586l.m20322a(Protocol.HTTP_2, Protocol.SPDY_3, Protocol.HTTP_1_1);
    /* renamed from: a */
    final C5597m f18110a;
    /* renamed from: b */
    final Proxy f18111b;
    /* renamed from: c */
    final List<Protocol> f18112c;
    /* renamed from: d */
    final List<C5590j> f18113d;
    /* renamed from: e */
    final List<C5596r> f18114e;
    /* renamed from: f */
    final List<C5596r> f18115f;
    /* renamed from: g */
    final ProxySelector f18116g;
    /* renamed from: h */
    final C5592l f18117h;
    /* renamed from: i */
    final C5463c f18118i;
    /* renamed from: j */
    final C5498e f18119j;
    /* renamed from: k */
    final SocketFactory f18120k;
    /* renamed from: l */
    final SSLSocketFactory f18121l;
    /* renamed from: m */
    final C5483a f18122m;
    /* renamed from: n */
    final HostnameVerifier f18123n;
    /* renamed from: o */
    final C5473g f18124o;
    /* renamed from: p */
    final C5461b f18125p;
    /* renamed from: q */
    final C5461b f18126q;
    /* renamed from: r */
    final C5476i f18127r;
    /* renamed from: s */
    final C5598n f18128s;
    /* renamed from: t */
    final boolean f18129t;
    /* renamed from: u */
    final boolean f18130u;
    /* renamed from: v */
    final boolean f18131v;
    /* renamed from: w */
    final int f18132w;
    /* renamed from: x */
    final int f18133x;
    /* renamed from: y */
    final int f18134y;

    /* compiled from: OkHttpClient */
    /* renamed from: okhttp3.u$1 */
    static class C56121 extends C5497d {
        C56121() {
        }

        /* renamed from: a */
        public void mo6773a(C5606a c5606a, String str) {
            c5606a.m20403a(str);
        }

        /* renamed from: a */
        public void mo6774a(C5606a c5606a, String str, String str2) {
            c5606a.m20407b(str, str2);
        }

        /* renamed from: a */
        public C5498e mo6770a(C5614u c5614u) {
            return c5614u.m20464g();
        }

        /* renamed from: a */
        public boolean mo6775a(C5476i c5476i, C5480b c5480b) {
            return c5476i.m19739b(c5480b);
        }

        /* renamed from: a */
        public C5480b mo6769a(C5476i c5476i, C5460a c5460a, C5582p c5582p) {
            return c5476i.m19737a(c5460a, c5582p);
        }

        /* renamed from: b */
        public void mo6776b(C5476i c5476i, C5480b c5480b) {
            c5476i.m19738a(c5480b);
        }

        /* renamed from: a */
        public C5584k mo6771a(C5476i c5476i) {
            return c5476i.f17629a;
        }

        /* renamed from: a */
        public void mo6772a(C5590j c5590j, SSLSocket sSLSocket, boolean z) {
            c5590j.m20360a(sSLSocket, z);
        }
    }

    /* compiled from: OkHttpClient */
    /* renamed from: okhttp3.u$a */
    public static final class C5613a {
        /* renamed from: a */
        C5597m f18083a = new C5597m();
        /* renamed from: b */
        Proxy f18084b;
        /* renamed from: c */
        List<Protocol> f18085c = C5614u.f18109z;
        /* renamed from: d */
        List<C5590j> f18086d = C5614u.f18108A;
        /* renamed from: e */
        final List<C5596r> f18087e = new ArrayList();
        /* renamed from: f */
        final List<C5596r> f18088f = new ArrayList();
        /* renamed from: g */
        ProxySelector f18089g = ProxySelector.getDefault();
        /* renamed from: h */
        C5592l f18090h = C5592l.f18036a;
        /* renamed from: i */
        C5463c f18091i;
        /* renamed from: j */
        C5498e f18092j;
        /* renamed from: k */
        SocketFactory f18093k = SocketFactory.getDefault();
        /* renamed from: l */
        SSLSocketFactory f18094l;
        /* renamed from: m */
        C5483a f18095m;
        /* renamed from: n */
        HostnameVerifier f18096n = C5487c.f17667a;
        /* renamed from: o */
        C5473g f18097o = C5473g.f17623a;
        /* renamed from: p */
        C5461b f18098p = C5461b.f17595a;
        /* renamed from: q */
        C5461b f18099q = C5461b.f17595a;
        /* renamed from: r */
        C5476i f18100r = new C5476i();
        /* renamed from: s */
        C5598n f18101s = C5598n.f18047c;
        /* renamed from: t */
        boolean f18102t = true;
        /* renamed from: u */
        boolean f18103u = true;
        /* renamed from: v */
        boolean f18104v = true;
        /* renamed from: w */
        int f18105w = 10000;
        /* renamed from: x */
        int f18106x = 10000;
        /* renamed from: y */
        int f18107y = 10000;

        /* renamed from: a */
        public C5613a m20445a(long j, TimeUnit timeUnit) {
            if (j < 0) {
                throw new IllegalArgumentException("timeout < 0");
            } else if (timeUnit == null) {
                throw new NullPointerException("unit == null");
            } else {
                long toMillis = timeUnit.toMillis(j);
                if (toMillis > 2147483647L) {
                    throw new IllegalArgumentException("Timeout too large.");
                } else if (toMillis != 0 || j <= 0) {
                    this.f18105w = (int) toMillis;
                    return this;
                } else {
                    throw new IllegalArgumentException("Timeout too small.");
                }
            }
        }

        /* renamed from: b */
        public C5613a m20450b(long j, TimeUnit timeUnit) {
            if (j < 0) {
                throw new IllegalArgumentException("timeout < 0");
            } else if (timeUnit == null) {
                throw new NullPointerException("unit == null");
            } else {
                long toMillis = timeUnit.toMillis(j);
                if (toMillis > 2147483647L) {
                    throw new IllegalArgumentException("Timeout too large.");
                } else if (toMillis != 0 || j <= 0) {
                    this.f18106x = (int) toMillis;
                    return this;
                } else {
                    throw new IllegalArgumentException("Timeout too small.");
                }
            }
        }

        /* renamed from: c */
        public C5613a m20452c(long j, TimeUnit timeUnit) {
            if (j < 0) {
                throw new IllegalArgumentException("timeout < 0");
            } else if (timeUnit == null) {
                throw new NullPointerException("unit == null");
            } else {
                long toMillis = timeUnit.toMillis(j);
                if (toMillis > 2147483647L) {
                    throw new IllegalArgumentException("Timeout too large.");
                } else if (toMillis != 0 || j <= 0) {
                    this.f18107y = (int) toMillis;
                    return this;
                } else {
                    throw new IllegalArgumentException("Timeout too small.");
                }
            }
        }

        /* renamed from: a */
        public C5613a m20446a(Proxy proxy) {
            this.f18084b = proxy;
            return this;
        }

        /* renamed from: a */
        public C5613a m20448a(C5598n c5598n) {
            if (c5598n == null) {
                throw new NullPointerException("dns == null");
            }
            this.f18101s = c5598n;
            return this;
        }

        /* renamed from: a */
        public C5613a m20447a(C5473g c5473g) {
            if (c5473g == null) {
                throw new NullPointerException("certificatePinner == null");
            }
            this.f18097o = c5473g;
            return this;
        }

        /* renamed from: a */
        public C5613a m20449a(C5596r c5596r) {
            this.f18087e.add(c5596r);
            return this;
        }

        /* renamed from: a */
        public List<C5596r> m20444a() {
            return this.f18088f;
        }

        /* renamed from: b */
        public C5614u m20451b() {
            return new C5614u();
        }
    }

    static {
        List arrayList = new ArrayList(Arrays.asList(new C5590j[]{C5590j.f18015a, C5590j.f18016b}));
        if (C5481j.m19770c().mo6703a()) {
            arrayList.add(C5590j.f18017c);
        }
        f18108A = C5586l.m20321a(arrayList);
        C5497d.f17702a = new C56121();
    }

    public C5614u() {
        this(new C5613a());
    }

    private C5614u(C5613a c5613a) {
        this.f18110a = c5613a.f18083a;
        this.f18111b = c5613a.f18084b;
        this.f18112c = c5613a.f18085c;
        this.f18113d = c5613a.f18086d;
        this.f18114e = C5586l.m20321a(c5613a.f18087e);
        this.f18115f = C5586l.m20321a(c5613a.f18088f);
        this.f18116g = c5613a.f18089g;
        this.f18117h = c5613a.f18090h;
        this.f18118i = c5613a.f18091i;
        this.f18119j = c5613a.f18092j;
        this.f18120k = c5613a.f18093k;
        Object obj = null;
        for (C5590j c5590j : this.f18113d) {
            Object obj2;
            if (obj != null || c5590j.m20361a()) {
                obj2 = 1;
            } else {
                obj2 = null;
            }
            obj = obj2;
        }
        if (c5613a.f18094l != null || obj == null) {
            this.f18121l = c5613a.f18094l;
            this.f18122m = c5613a.f18095m;
        } else {
            X509TrustManager z = m20456z();
            this.f18121l = m20453a(z);
            this.f18122m = C5483a.m19784a(z);
        }
        this.f18123n = c5613a.f18096n;
        this.f18124o = c5613a.f18097o.m19730a(this.f18122m);
        this.f18125p = c5613a.f18098p;
        this.f18126q = c5613a.f18099q;
        this.f18127r = c5613a.f18100r;
        this.f18128s = c5613a.f18101s;
        this.f18129t = c5613a.f18102t;
        this.f18130u = c5613a.f18103u;
        this.f18131v = c5613a.f18104v;
        this.f18132w = c5613a.f18105w;
        this.f18133x = c5613a.f18106x;
        this.f18134y = c5613a.f18107y;
    }

    /* renamed from: z */
    private X509TrustManager m20456z() {
        try {
            TrustManagerFactory instance = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance.init((KeyStore) null);
            TrustManager[] trustManagers = instance.getTrustManagers();
            if (trustManagers.length == 1 && (trustManagers[0] instanceof X509TrustManager)) {
                return (X509TrustManager) trustManagers[0];
            }
            throw new IllegalStateException("Unexpected default trust managers:" + Arrays.toString(trustManagers));
        } catch (GeneralSecurityException e) {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    private SSLSocketFactory m20453a(X509TrustManager x509TrustManager) {
        try {
            SSLContext instance = SSLContext.getInstance("TLS");
            instance.init(null, new TrustManager[]{x509TrustManager}, null);
            return instance.getSocketFactory();
        } catch (GeneralSecurityException e) {
            throw new AssertionError();
        }
    }

    /* renamed from: a */
    public int m20457a() {
        return this.f18132w;
    }

    /* renamed from: b */
    public int m20459b() {
        return this.f18133x;
    }

    /* renamed from: c */
    public int m20460c() {
        return this.f18134y;
    }

    /* renamed from: d */
    public Proxy m20461d() {
        return this.f18111b;
    }

    /* renamed from: e */
    public ProxySelector m20462e() {
        return this.f18116g;
    }

    /* renamed from: f */
    public C5592l m20463f() {
        return this.f18117h;
    }

    /* renamed from: g */
    C5498e m20464g() {
        return this.f18118i != null ? this.f18118i.f17596a : this.f18119j;
    }

    /* renamed from: h */
    public C5598n m20465h() {
        return this.f18128s;
    }

    /* renamed from: i */
    public SocketFactory m20466i() {
        return this.f18120k;
    }

    /* renamed from: j */
    public SSLSocketFactory m20467j() {
        return this.f18121l;
    }

    /* renamed from: k */
    public HostnameVerifier m20468k() {
        return this.f18123n;
    }

    /* renamed from: l */
    public C5473g m20469l() {
        return this.f18124o;
    }

    /* renamed from: m */
    public C5461b m20470m() {
        return this.f18126q;
    }

    /* renamed from: n */
    public C5461b m20471n() {
        return this.f18125p;
    }

    /* renamed from: o */
    public C5476i m20472o() {
        return this.f18127r;
    }

    /* renamed from: p */
    public boolean m20473p() {
        return this.f18129t;
    }

    /* renamed from: q */
    public boolean m20474q() {
        return this.f18130u;
    }

    /* renamed from: r */
    public boolean m20475r() {
        return this.f18131v;
    }

    /* renamed from: s */
    public C5597m m20476s() {
        return this.f18110a;
    }

    /* renamed from: t */
    public List<Protocol> m20477t() {
        return this.f18112c;
    }

    /* renamed from: u */
    public List<C5590j> m20478u() {
        return this.f18113d;
    }

    /* renamed from: v */
    public List<C5596r> m20479v() {
        return this.f18114e;
    }

    /* renamed from: w */
    public List<C5596r> m20480w() {
        return this.f18115f;
    }

    /* renamed from: a */
    public C5468e mo6777a(C5621w c5621w) {
        return new C5618v(this, c5621w);
    }
}
