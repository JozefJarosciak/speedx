package okhttp3;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Proxy.Type;

/* compiled from: Route */
public final class aa {
    /* renamed from: a */
    final C5460a f17592a;
    /* renamed from: b */
    final Proxy f17593b;
    /* renamed from: c */
    final InetSocketAddress f17594c;

    public aa(C5460a c5460a, Proxy proxy, InetSocketAddress inetSocketAddress) {
        if (c5460a == null) {
            throw new NullPointerException("address == null");
        } else if (proxy == null) {
            throw new NullPointerException("proxy == null");
        } else if (inetSocketAddress == null) {
            throw new NullPointerException("inetSocketAddress == null");
        } else {
            this.f17592a = c5460a;
            this.f17593b = proxy;
            this.f17594c = inetSocketAddress;
        }
    }

    /* renamed from: a */
    public C5460a m19697a() {
        return this.f17592a;
    }

    /* renamed from: b */
    public Proxy m19698b() {
        return this.f17593b;
    }

    /* renamed from: c */
    public InetSocketAddress m19699c() {
        return this.f17594c;
    }

    /* renamed from: d */
    public boolean m19700d() {
        return this.f17592a.f17589i != null && this.f17593b.type() == Type.HTTP;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof aa)) {
            return false;
        }
        aa aaVar = (aa) obj;
        if (this.f17592a.equals(aaVar.f17592a) && this.f17593b.equals(aaVar.f17593b) && this.f17594c.equals(aaVar.f17594c)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return ((((this.f17592a.hashCode() + 527) * 31) + this.f17593b.hashCode()) * 31) + this.f17594c.hashCode();
    }
}
