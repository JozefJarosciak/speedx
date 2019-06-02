package io.fabric.sdk.android.services.network;

import com.alipay.sdk.cons.C0845b;
import io.fabric.sdk.android.C4835k;
import io.fabric.sdk.android.C4836b;
import java.util.Locale;
import java.util.Map;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;

/* compiled from: DefaultHttpRequestFactory */
/* renamed from: io.fabric.sdk.android.services.network.b */
public class C4925b implements C4924c {
    /* renamed from: a */
    private final C4835k f17265a;
    /* renamed from: b */
    private C4656e f17266b;
    /* renamed from: c */
    private SSLSocketFactory f17267c;
    /* renamed from: d */
    private boolean f17268d;

    public C4925b() {
        this(new C4836b());
    }

    public C4925b(C4835k c4835k) {
        this.f17265a = c4835k;
    }

    /* renamed from: a */
    public HttpRequest mo6260a(HttpMethod httpMethod, String str, Map<String, String> map) {
        HttpRequest a;
        switch (httpMethod) {
            case GET:
                a = HttpRequest.m19299a((CharSequence) str, (Map) map, true);
                break;
            case POST:
                a = HttpRequest.m19304b((CharSequence) str, (Map) map, true);
                break;
            case PUT:
                a = HttpRequest.m19307d((CharSequence) str);
                break;
            case DELETE:
                a = HttpRequest.m19308e((CharSequence) str);
                break;
            default:
                throw new IllegalArgumentException("Unsupported HTTP method!");
        }
        if (m19353a(str) && this.f17266b != null) {
            SSLSocketFactory a2 = m19352a();
            if (a2 != null) {
                ((HttpsURLConnection) a.m19324a()).setSSLSocketFactory(a2);
            }
        }
        return a;
    }

    /* renamed from: a */
    private boolean m19353a(String str) {
        return str != null && str.toLowerCase(Locale.US).startsWith(C0845b.f2060a);
    }

    /* renamed from: a */
    private synchronized SSLSocketFactory m19352a() {
        if (this.f17267c == null && !this.f17268d) {
            this.f17267c = m19354b();
        }
        return this.f17267c;
    }

    /* renamed from: b */
    private synchronized SSLSocketFactory m19354b() {
        SSLSocketFactory a;
        this.f17268d = true;
        try {
            a = C4926d.m19356a(this.f17266b);
            this.f17265a.mo6215a("Fabric", "Custom SSL pinning enabled");
        } catch (Throwable e) {
            this.f17265a.mo6222d("Fabric", "Exception while validating pinned certs", e);
            a = null;
        }
        return a;
    }
}
