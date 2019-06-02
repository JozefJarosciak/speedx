package com.alipay.sdk.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Proxy;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.alipay.sdk.cons.C0845b;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.net.URL;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

/* renamed from: com.alipay.sdk.net.a */
public final class C0856a {
    /* renamed from: a */
    public static final String f2119a = "application/octet-stream;binary/octet-stream";
    /* renamed from: b */
    public String f2120b;
    /* renamed from: c */
    private Context f2121c;

    private C0856a(Context context) {
        this(context, null);
    }

    public C0856a(Context context, String str) {
        this.f2121c = context;
        this.f2120b = str;
    }

    /* renamed from: a */
    private void m3299a(String str) {
        this.f2120b = str;
    }

    /* renamed from: a */
    private String m3298a() {
        return this.f2120b;
    }

    /* renamed from: b */
    private URL m3300b() {
        try {
            return new URL(this.f2120b);
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: a */
    public final HttpResponse m3306a(byte[] bArr, List<Header> list) throws Throwable {
        HttpResponse a;
        Object obj = null;
        new StringBuilder("requestUrl : ").append(this.f2120b);
        C0857b a2 = C0857b.m3307a();
        if (a2 != null) {
            try {
                HttpUriRequest httpGet;
                HttpParams params = a2.f2124c.getParams();
                String g;
                if (VERSION.SDK_INT >= 11) {
                    g = m3305g();
                    if (g == null || g.contains("wap")) {
                        URL b = m3300b();
                        if (b != null) {
                            C0845b.f2060a.equalsIgnoreCase(b.getProtocol());
                            Object property = System.getProperty("https.proxyHost");
                            String property2 = System.getProperty("https.proxyPort");
                            if (!TextUtils.isEmpty(property)) {
                                obj = new HttpHost(property, Integer.parseInt(property2));
                            }
                        }
                    }
                } else {
                    NetworkInfo f = m3304f();
                    if (f != null && f.isAvailable() && f.getType() == 0) {
                        g = Proxy.getDefaultHost();
                        int defaultPort = Proxy.getDefaultPort();
                        if (g != null) {
                            obj = new HttpHost(g, defaultPort);
                        }
                    }
                }
                if (obj != null) {
                    params.setParameter("http.route.default-proxy", obj);
                }
                if (bArr == null || bArr.length == 0) {
                    httpGet = new HttpGet(this.f2120b);
                } else {
                    httpGet = new HttpPost(this.f2120b);
                    HttpEntity byteArrayEntity = new ByteArrayEntity(bArr);
                    byteArrayEntity.setContentType(f2119a);
                    ((HttpPost) httpGet).setEntity(byteArrayEntity);
                    httpGet.addHeader("Accept-Charset", "UTF-8");
                    httpGet.addHeader("Connection", HTTP.CONN_KEEP_ALIVE);
                    httpGet.addHeader(HTTP.CONN_KEEP_ALIVE, "timeout=180, max=100");
                }
                if (list != null) {
                    for (Header addHeader : list) {
                        httpGet.addHeader(addHeader);
                    }
                }
                a = a2.m3321a(httpGet);
                Header[] headers = a.getHeaders("X-Hostname");
                if (!(headers == null || headers.length <= 0 || headers[0] == null)) {
                    a.getHeaders("X-Hostname")[0].toString();
                }
                headers = a.getHeaders("X-ExecuteTime");
                if (!(headers == null || headers.length <= 0 || headers[0] == null)) {
                    a.getHeaders("X-ExecuteTime")[0].toString();
                }
            } catch (Throwable th) {
            }
        }
        return a;
    }

    /* renamed from: c */
    private HttpHost m3301c() {
        String g;
        if (VERSION.SDK_INT >= 11) {
            g = m3305g();
            if (g != null && !g.contains("wap")) {
                return null;
            }
            URL b = m3300b();
            if (b == null) {
                return null;
            }
            C0845b.f2060a.equalsIgnoreCase(b.getProtocol());
            Object property = System.getProperty("https.proxyHost");
            String property2 = System.getProperty("https.proxyPort");
            if (TextUtils.isEmpty(property)) {
                return null;
            }
            return new HttpHost(property, Integer.parseInt(property2));
        }
        NetworkInfo f = m3304f();
        if (f == null || !f.isAvailable() || f.getType() != 0) {
            return null;
        }
        g = Proxy.getDefaultHost();
        int defaultPort = Proxy.getDefaultPort();
        if (g != null) {
            return new HttpHost(g, defaultPort);
        }
        return null;
    }

    /* renamed from: d */
    private HttpHost m3302d() {
        NetworkInfo f = m3304f();
        if (f == null || !f.isAvailable() || f.getType() != 0) {
            return null;
        }
        String defaultHost = Proxy.getDefaultHost();
        int defaultPort = Proxy.getDefaultPort();
        if (defaultHost != null) {
            return new HttpHost(defaultHost, defaultPort);
        }
        return null;
    }

    /* renamed from: e */
    private HttpHost m3303e() {
        String g = m3305g();
        if (g != null && !g.contains("wap")) {
            return null;
        }
        URL b = m3300b();
        if (b == null) {
            return null;
        }
        C0845b.f2060a.equalsIgnoreCase(b.getProtocol());
        Object property = System.getProperty("https.proxyHost");
        String property2 = System.getProperty("https.proxyPort");
        if (TextUtils.isEmpty(property)) {
            return null;
        }
        return new HttpHost(property, Integer.parseInt(property2));
    }

    /* renamed from: f */
    private NetworkInfo m3304f() {
        try {
            return ((ConnectivityManager) this.f2121c.getSystemService("connectivity")).getActiveNetworkInfo();
        } catch (Exception e) {
            return null;
        }
    }

    /* renamed from: g */
    private String m3305g() {
        try {
            NetworkInfo f = m3304f();
            if (f == null || !f.isAvailable()) {
                return "none";
            }
            if (f.getType() == 1) {
                return MapboxEvent.ATTRIBUTE_WIFI;
            }
            return f.getExtraInfo().toLowerCase();
        } catch (Exception e) {
            return "none";
        }
    }
}
