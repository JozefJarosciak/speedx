package okhttp3.internal.http;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;
import java.net.SocketAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import okhttp3.C5460a;
import okhttp3.HttpUrl;
import okhttp3.aa;
import okhttp3.internal.C5584k;

/* compiled from: RouteSelector */
/* renamed from: okhttp3.internal.http.n */
public final class C5580n {
    /* renamed from: a */
    private final C5460a f17979a;
    /* renamed from: b */
    private final C5584k f17980b;
    /* renamed from: c */
    private Proxy f17981c;
    /* renamed from: d */
    private InetSocketAddress f17982d;
    /* renamed from: e */
    private List<Proxy> f17983e = Collections.emptyList();
    /* renamed from: f */
    private int f17984f;
    /* renamed from: g */
    private List<InetSocketAddress> f17985g = Collections.emptyList();
    /* renamed from: h */
    private int f17986h;
    /* renamed from: i */
    private final List<aa> f17987i = new ArrayList();

    public C5580n(C5460a c5460a, C5584k c5584k) {
        this.f17979a = c5460a;
        this.f17980b = c5584k;
        m20279a(c5460a.m19686a(), c5460a.m19693h());
    }

    /* renamed from: a */
    public boolean m20287a() {
        return m20282e() || m20280c() || m20284g();
    }

    /* renamed from: b */
    public aa m20288b() throws IOException {
        if (!m20282e()) {
            if (m20280c()) {
                this.f17981c = m20281d();
            } else if (m20284g()) {
                return m20285h();
            } else {
                throw new NoSuchElementException();
            }
        }
        this.f17982d = m20283f();
        aa aaVar = new aa(this.f17979a, this.f17981c, this.f17982d);
        if (!this.f17980b.m20314c(aaVar)) {
            return aaVar;
        }
        this.f17987i.add(aaVar);
        return m20288b();
    }

    /* renamed from: a */
    public void m20286a(aa aaVar, IOException iOException) {
        if (!(aaVar.m19698b().type() == Type.DIRECT || this.f17979a.m19692g() == null)) {
            this.f17979a.m19692g().connectFailed(this.f17979a.m19686a().m19669a(), aaVar.m19698b().address(), iOException);
        }
        this.f17980b.m20312a(aaVar);
    }

    /* renamed from: a */
    private void m20279a(HttpUrl httpUrl, Proxy proxy) {
        if (proxy != null) {
            this.f17983e = Collections.singletonList(proxy);
        } else {
            this.f17983e = new ArrayList();
            Collection select = this.f17979a.m19692g().select(httpUrl.m19669a());
            if (select != null) {
                this.f17983e.addAll(select);
            }
            this.f17983e.removeAll(Collections.singleton(Proxy.NO_PROXY));
            this.f17983e.add(Proxy.NO_PROXY);
        }
        this.f17984f = 0;
    }

    /* renamed from: c */
    private boolean m20280c() {
        return this.f17984f < this.f17983e.size();
    }

    /* renamed from: d */
    private Proxy m20281d() throws IOException {
        if (m20280c()) {
            List list = this.f17983e;
            int i = this.f17984f;
            this.f17984f = i + 1;
            Proxy proxy = (Proxy) list.get(i);
            m20278a(proxy);
            return proxy;
        }
        throw new SocketException("No route to " + this.f17979a.m19686a().m19676f() + "; exhausted proxy configurations: " + this.f17983e);
    }

    /* renamed from: a */
    private void m20278a(Proxy proxy) throws IOException {
        int g;
        String str;
        this.f17985g = new ArrayList();
        String f;
        if (proxy.type() == Type.DIRECT || proxy.type() == Type.SOCKS) {
            f = this.f17979a.m19686a().m19676f();
            g = this.f17979a.m19686a().m19677g();
            str = f;
        } else {
            SocketAddress address = proxy.address();
            if (address instanceof InetSocketAddress) {
                InetSocketAddress inetSocketAddress = (InetSocketAddress) address;
                f = C5580n.m20277a(inetSocketAddress);
                g = inetSocketAddress.getPort();
                str = f;
            } else {
                throw new IllegalArgumentException("Proxy.address() is not an InetSocketAddress: " + address.getClass());
            }
        }
        if (g < 1 || g > 65535) {
            throw new SocketException("No route to " + str + ":" + g + "; port is out of range");
        }
        if (proxy.type() == Type.SOCKS) {
            this.f17985g.add(InetSocketAddress.createUnresolved(str, g));
        } else {
            List a = this.f17979a.m19687b().mo6765a(str);
            int size = a.size();
            for (int i = 0; i < size; i++) {
                this.f17985g.add(new InetSocketAddress((InetAddress) a.get(i), g));
            }
        }
        this.f17986h = 0;
    }

    /* renamed from: a */
    static String m20277a(InetSocketAddress inetSocketAddress) {
        InetAddress address = inetSocketAddress.getAddress();
        if (address == null) {
            return inetSocketAddress.getHostName();
        }
        return address.getHostAddress();
    }

    /* renamed from: e */
    private boolean m20282e() {
        return this.f17986h < this.f17985g.size();
    }

    /* renamed from: f */
    private InetSocketAddress m20283f() throws IOException {
        if (m20282e()) {
            List list = this.f17985g;
            int i = this.f17986h;
            this.f17986h = i + 1;
            return (InetSocketAddress) list.get(i);
        }
        throw new SocketException("No route to " + this.f17979a.m19686a().m19676f() + "; exhausted inet socket addresses: " + this.f17985g);
    }

    /* renamed from: g */
    private boolean m20284g() {
        return !this.f17987i.isEmpty();
    }

    /* renamed from: h */
    private aa m20285h() {
        return (aa) this.f17987i.remove(0);
    }
}
