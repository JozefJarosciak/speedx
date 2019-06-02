package com.alipay.sdk.net;

import android.os.Build.VERSION;
import com.alipay.sdk.cons.C0845b;
import java.util.concurrent.TimeUnit;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HttpContext;
import org.java_websocket.WebSocket;

/* renamed from: com.alipay.sdk.net.b */
public final class C0857b {
    /* renamed from: a */
    public static final String f2122a = "msp";
    /* renamed from: b */
    static C0857b f2123b;
    /* renamed from: c */
    final DefaultHttpClient f2124c;

    /* renamed from: b */
    private static C0857b m3315b() {
        return f2123b;
    }

    /* renamed from: c */
    private static void m3316c() {
        f2123b = null;
    }

    private C0857b(HttpParams httpParams) {
        this.f2124c = new DefaultHttpClient(httpParams);
    }

    private C0857b(ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        this.f2124c = new DefaultHttpClient(clientConnectionManager, httpParams);
    }

    /* renamed from: a */
    public static C0857b m3307a() {
        if (f2123b == null) {
            HttpParams basicHttpParams = new BasicHttpParams();
            HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
            HttpConnectionParams.setStaleCheckingEnabled(basicHttpParams, true);
            basicHttpParams.setBooleanParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
            ConnManagerParams.setMaxTotalConnections(basicHttpParams, 50);
            ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(30));
            ConnManagerParams.setTimeout(basicHttpParams, 1000);
            HttpConnectionParams.setConnectionTimeout(basicHttpParams, 20000);
            HttpConnectionParams.setSoTimeout(basicHttpParams, 30000);
            HttpConnectionParams.setSocketBufferSize(basicHttpParams, 16384);
            HttpProtocolParams.setUseExpectContinue(basicHttpParams, false);
            HttpClientParams.setRedirecting(basicHttpParams, true);
            HttpClientParams.setAuthenticating(basicHttpParams, false);
            HttpProtocolParams.setUserAgent(basicHttpParams, f2122a);
            try {
                SocketFactory socketFactory = SSLSocketFactory.getSocketFactory();
                socketFactory.setHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
                Scheme scheme = new Scheme(C0845b.f2060a, socketFactory, WebSocket.DEFAULT_WSS_PORT);
                Scheme scheme2 = new Scheme(HttpHost.DEFAULT_SCHEME_NAME, PlainSocketFactory.getSocketFactory(), 80);
                SchemeRegistry schemeRegistry = new SchemeRegistry();
                schemeRegistry.register(scheme);
                schemeRegistry.register(scheme2);
                f2123b = new C0857b(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
            } catch (Exception e) {
                f2123b = new C0857b(basicHttpParams);
            }
        }
        return f2123b;
    }

    /* renamed from: d */
    private void m3317d() {
        ClientConnectionManager connectionManager = this.f2124c.getConnectionManager();
        if (connectionManager != null) {
            connectionManager.closeExpiredConnections();
            if (VERSION.SDK_INT >= 9) {
                connectionManager.closeIdleConnections(30, TimeUnit.MINUTES);
            }
        }
    }

    /* renamed from: e */
    private void m3318e() {
        ClientConnectionManager connectionManager = this.f2124c.getConnectionManager();
        if (connectionManager != null) {
            connectionManager.shutdown();
            f2123b = null;
        }
    }

    /* renamed from: f */
    private HttpParams m3319f() {
        return this.f2124c.getParams();
    }

    /* renamed from: g */
    private ClientConnectionManager m3320g() {
        return this.f2124c.getConnectionManager();
    }

    /* renamed from: a */
    public final HttpResponse m3321a(HttpUriRequest httpUriRequest) throws Exception {
        try {
            return this.f2124c.execute(httpUriRequest);
        } catch (Exception e) {
            throw e;
        }
    }

    /* renamed from: a */
    private HttpResponse m3314a(HttpUriRequest httpUriRequest, HttpContext httpContext) throws Exception {
        try {
            return this.f2124c.execute(httpUriRequest, httpContext);
        } catch (Throwable e) {
            throw new Exception(e);
        }
    }

    /* renamed from: a */
    private HttpResponse m3312a(HttpHost httpHost, HttpRequest httpRequest) throws Exception {
        try {
            return this.f2124c.execute(httpHost, httpRequest);
        } catch (Throwable e) {
            throw new Exception(e);
        }
    }

    /* renamed from: a */
    private HttpResponse m3313a(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) throws Exception {
        try {
            return this.f2124c.execute(httpHost, httpRequest, httpContext);
        } catch (Throwable e) {
            throw new Exception(e);
        }
    }

    /* renamed from: a */
    private <T> T m3310a(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) throws Exception {
        try {
            return this.f2124c.execute(httpUriRequest, responseHandler);
        } catch (Throwable e) {
            throw new Exception(e);
        }
    }

    /* renamed from: a */
    private <T> T m3311a(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws Exception {
        try {
            return this.f2124c.execute(httpUriRequest, responseHandler, httpContext);
        } catch (Throwable e) {
            throw new Exception(e);
        }
    }

    /* renamed from: a */
    private <T> T m3308a(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) throws Exception {
        try {
            return this.f2124c.execute(httpHost, httpRequest, responseHandler);
        } catch (Throwable e) {
            throw new Exception(e);
        }
    }

    /* renamed from: a */
    private <T> T m3309a(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) throws Exception {
        try {
            return this.f2124c.execute(httpHost, httpRequest, responseHandler, httpContext);
        } catch (Throwable e) {
            throw new Exception(e);
        }
    }
}
