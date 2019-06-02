package com.alipay.android.phone.mrpc.core;

import android.net.SSLCertificateSocketFactory;
import android.support.v4.media.session.PlaybackStateCompat;
import android.util.Base64;
import android.util.Log;
import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
import com.alipay.sdk.cons.C0845b;
import com.alipay.sdk.packet.C0861d;
import com.google.common.net.HttpHeaders;
import com.loopj.android.http.AsyncHttpClient;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.security.Security;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import javax.net.ssl.HttpsURLConnection;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpHost;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.params.HttpClientParams;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.RequestWrapper;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.java_websocket.WebSocket;

/* renamed from: com.alipay.android.phone.mrpc.core.b */
public final class C0739b implements HttpClient {
    /* renamed from: a */
    public static long f1736a = 160;
    /* renamed from: b */
    private static String[] f1737b = new String[]{"text/", "application/xml", "application/json"};
    /* renamed from: c */
    private static final HttpRequestInterceptor f1738c = new C0740c();
    /* renamed from: d */
    private final HttpClient f1739d;
    /* renamed from: e */
    private RuntimeException f1740e = new IllegalStateException("AndroidHttpClient created and never closed");
    /* renamed from: f */
    private volatile C0738b f1741f;

    /* renamed from: com.alipay.android.phone.mrpc.core.b$a */
    private class C0737a implements HttpRequestInterceptor {
        /* renamed from: a */
        final /* synthetic */ C0739b f1733a;

        private C0737a(C0739b c0739b) {
            this.f1733a = c0739b;
        }

        public final void process(HttpRequest httpRequest, HttpContext httpContext) {
            C0738b a = this.f1733a.f1741f;
            if (a != null && Log.isLoggable(a.f1734a, a.f1735b) && (httpRequest instanceof HttpUriRequest)) {
                Log.println(a.f1735b, a.f1734a, C0739b.m2842a((HttpUriRequest) httpRequest));
            }
        }
    }

    /* renamed from: com.alipay.android.phone.mrpc.core.b$b */
    private static class C0738b {
        /* renamed from: a */
        private final String f1734a;
        /* renamed from: b */
        private final int f1735b;
    }

    private C0739b(ClientConnectionManager clientConnectionManager, HttpParams httpParams) {
        this.f1739d = new C0741d(this, clientConnectionManager, httpParams);
    }

    /* renamed from: a */
    public static C0739b m2840a(String str) {
        HttpParams basicHttpParams = new BasicHttpParams();
        HttpProtocolParams.setVersion(basicHttpParams, HttpVersion.HTTP_1_1);
        HttpProtocolParams.setUseExpectContinue(basicHttpParams, false);
        HttpConnectionParams.setStaleCheckingEnabled(basicHttpParams, true);
        HttpConnectionParams.setConnectionTimeout(basicHttpParams, 20000);
        HttpConnectionParams.setSoTimeout(basicHttpParams, 30000);
        HttpConnectionParams.setSocketBufferSize(basicHttpParams, 8192);
        HttpClientParams.setRedirecting(basicHttpParams, true);
        HttpClientParams.setAuthenticating(basicHttpParams, false);
        HttpProtocolParams.setUserAgent(basicHttpParams, str);
        SchemeRegistry schemeRegistry = new SchemeRegistry();
        schemeRegistry.register(new Scheme(HttpHost.DEFAULT_SCHEME_NAME, PlainSocketFactory.getSocketFactory(), 80));
        schemeRegistry.register(new Scheme(C0845b.f2060a, SSLCertificateSocketFactory.getHttpSocketFactory(30000, null), WebSocket.DEFAULT_WSS_PORT));
        ClientConnectionManager threadSafeClientConnManager = new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry);
        ConnManagerParams.setTimeout(basicHttpParams, ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD);
        ConnManagerParams.setMaxConnectionsPerRoute(basicHttpParams, new ConnPerRouteBean(10));
        ConnManagerParams.setMaxTotalConnections(basicHttpParams, 50);
        Security.setProperty("networkaddress.cache.ttl", "-1");
        HttpsURLConnection.setDefaultHostnameVerifier(SSLSocketFactory.STRICT_HOSTNAME_VERIFIER);
        return new C0739b(threadSafeClientConnManager, basicHttpParams);
    }

    /* renamed from: a */
    public static InputStream m2841a(HttpEntity httpEntity) {
        InputStream content = httpEntity.getContent();
        if (content == null) {
            return content;
        }
        Header contentEncoding = httpEntity.getContentEncoding();
        if (contentEncoding == null) {
            return content;
        }
        String value = contentEncoding.getValue();
        if (value == null) {
            return content;
        }
        return value.contains(AsyncHttpClient.ENCODING_GZIP) ? new GZIPInputStream(content) : content;
    }

    /* renamed from: a */
    static /* synthetic */ String m2842a(HttpUriRequest httpUriRequest) {
        Object uri;
        HttpEntity entity;
        OutputStream byteArrayOutputStream;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("curl ");
        for (Header header : httpUriRequest.getAllHeaders()) {
            if (!(header.getName().equals("Authorization") || header.getName().equals(HttpHeaders.COOKIE))) {
                stringBuilder.append("--header \"");
                stringBuilder.append(header.toString().trim());
                stringBuilder.append("\" ");
            }
        }
        URI uri2 = httpUriRequest.getURI();
        if (httpUriRequest instanceof RequestWrapper) {
            HttpRequest original = ((RequestWrapper) httpUriRequest).getOriginal();
            if (original instanceof HttpUriRequest) {
                uri = ((HttpUriRequest) original).getURI();
                stringBuilder.append("\"");
                stringBuilder.append(uri);
                stringBuilder.append("\"");
                if (httpUriRequest instanceof HttpEntityEnclosingRequest) {
                    entity = ((HttpEntityEnclosingRequest) httpUriRequest).getEntity();
                    if (entity != null && entity.isRepeatable()) {
                        if (entity.getContentLength() >= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
                            byteArrayOutputStream = new ByteArrayOutputStream();
                            entity.writeTo(byteArrayOutputStream);
                            if (C0739b.m2848b(httpUriRequest)) {
                                stringBuilder.append(" --data-ascii \"").append(byteArrayOutputStream.toString()).append("\"");
                            } else {
                                stringBuilder.insert(0, "echo '" + Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2) + "' | base64 -d > /tmp/$$.bin; ");
                                stringBuilder.append(" --data-binary @/tmp/$$.bin");
                            }
                        } else {
                            stringBuilder.append(" [TOO MUCH DATA TO INCLUDE]");
                        }
                    }
                }
                return stringBuilder.toString();
            }
        }
        URI uri3 = uri2;
        stringBuilder.append("\"");
        stringBuilder.append(uri);
        stringBuilder.append("\"");
        if (httpUriRequest instanceof HttpEntityEnclosingRequest) {
            entity = ((HttpEntityEnclosingRequest) httpUriRequest).getEntity();
            if (entity.getContentLength() >= PlaybackStateCompat.ACTION_PLAY_FROM_MEDIA_ID) {
                stringBuilder.append(" [TOO MUCH DATA TO INCLUDE]");
            } else {
                byteArrayOutputStream = new ByteArrayOutputStream();
                entity.writeTo(byteArrayOutputStream);
                if (C0739b.m2848b(httpUriRequest)) {
                    stringBuilder.append(" --data-ascii \"").append(byteArrayOutputStream.toString()).append("\"");
                } else {
                    stringBuilder.insert(0, "echo '" + Base64.encodeToString(byteArrayOutputStream.toByteArray(), 2) + "' | base64 -d > /tmp/$$.bin; ");
                    stringBuilder.append(" --data-binary @/tmp/$$.bin");
                }
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: a */
    public static AbstractHttpEntity m2844a(byte[] bArr) {
        if (((long) bArr.length) < f1736a) {
            return new ByteArrayEntity(bArr);
        }
        OutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStream gZIPOutputStream = new GZIPOutputStream(byteArrayOutputStream);
        gZIPOutputStream.write(bArr);
        gZIPOutputStream.close();
        AbstractHttpEntity byteArrayEntity = new ByteArrayEntity(byteArrayOutputStream.toByteArray());
        byteArrayEntity.setContentEncoding(AsyncHttpClient.ENCODING_GZIP);
        new StringBuilder("gzip size:").append(bArr.length).append("->").append(byteArrayEntity.getContentLength());
        return byteArrayEntity;
    }

    /* renamed from: a */
    public static void m2845a(HttpRequest httpRequest) {
        httpRequest.addHeader("Accept-Encoding", AsyncHttpClient.ENCODING_GZIP);
    }

    /* renamed from: b */
    public static long m2846b(String str) {
        return C0750k.m2862a(str);
    }

    /* renamed from: b */
    public static void m2847b(HttpRequest httpRequest) {
        httpRequest.addHeader("Connection", HTTP.CONN_KEEP_ALIVE);
    }

    /* renamed from: b */
    private static boolean m2848b(HttpUriRequest httpUriRequest) {
        Header[] headers = httpUriRequest.getHeaders("content-encoding");
        if (headers != null) {
            for (Header value : headers) {
                if (AsyncHttpClient.ENCODING_GZIP.equalsIgnoreCase(value.getValue())) {
                    return true;
                }
            }
        }
        Header[] headers2 = httpUriRequest.getHeaders(C0861d.f2132d);
        if (headers2 == null) {
            return true;
        }
        for (Header header : headers2) {
            for (String startsWith : f1737b) {
                if (header.getValue().startsWith(startsWith)) {
                    return false;
                }
            }
        }
        return true;
    }

    /* renamed from: a */
    public final void m2849a(HttpRequestRetryHandler httpRequestRetryHandler) {
        ((DefaultHttpClient) this.f1739d).setHttpRequestRetryHandler(httpRequestRetryHandler);
    }

    public final <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler) {
        return this.f1739d.execute(httpHost, httpRequest, responseHandler);
    }

    public final <T> T execute(HttpHost httpHost, HttpRequest httpRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) {
        return this.f1739d.execute(httpHost, httpRequest, responseHandler, httpContext);
    }

    public final <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler) {
        return this.f1739d.execute(httpUriRequest, responseHandler);
    }

    public final <T> T execute(HttpUriRequest httpUriRequest, ResponseHandler<? extends T> responseHandler, HttpContext httpContext) {
        return this.f1739d.execute(httpUriRequest, responseHandler, httpContext);
    }

    public final HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest) {
        return this.f1739d.execute(httpHost, httpRequest);
    }

    public final HttpResponse execute(HttpHost httpHost, HttpRequest httpRequest, HttpContext httpContext) {
        return this.f1739d.execute(httpHost, httpRequest, httpContext);
    }

    public final HttpResponse execute(HttpUriRequest httpUriRequest) {
        return this.f1739d.execute(httpUriRequest);
    }

    public final HttpResponse execute(HttpUriRequest httpUriRequest, HttpContext httpContext) {
        return this.f1739d.execute(httpUriRequest, httpContext);
    }

    public final ClientConnectionManager getConnectionManager() {
        return this.f1739d.getConnectionManager();
    }

    public final HttpParams getParams() {
        return this.f1739d.getParams();
    }
}
