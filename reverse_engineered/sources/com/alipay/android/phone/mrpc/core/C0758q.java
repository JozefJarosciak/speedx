package com.alipay.android.phone.mrpc.core;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import com.alipay.sdk.util.C0880h;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.loopj.android.http.AsyncHttpClient;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URL;
import java.util.HashMap;
import java.util.concurrent.Callable;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.AbstractHttpEntity;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

/* renamed from: com.alipay.android.phone.mrpc.core.q */
public final class C0758q implements Callable<C0756u> {
    /* renamed from: e */
    private static final HttpRequestRetryHandler f1783e = new ad();
    /* renamed from: a */
    protected C0751l f1784a;
    /* renamed from: b */
    protected Context f1785b;
    /* renamed from: c */
    protected C0755o f1786c;
    /* renamed from: d */
    String f1787d;
    /* renamed from: f */
    private HttpUriRequest f1788f;
    /* renamed from: g */
    private HttpContext f1789g = new BasicHttpContext();
    /* renamed from: h */
    private CookieStore f1790h = new BasicCookieStore();
    /* renamed from: i */
    private CookieManager f1791i;
    /* renamed from: j */
    private AbstractHttpEntity f1792j;
    /* renamed from: k */
    private HttpHost f1793k;
    /* renamed from: l */
    private URL f1794l;
    /* renamed from: m */
    private int f1795m = 0;
    /* renamed from: n */
    private boolean f1796n = false;
    /* renamed from: o */
    private boolean f1797o = false;
    /* renamed from: p */
    private String f1798p = null;
    /* renamed from: q */
    private String f1799q;

    public C0758q(C0751l c0751l, C0755o c0755o) {
        this.f1784a = c0751l;
        this.f1785b = this.f1784a.f1757a;
        this.f1786c = c0755o;
    }

    /* renamed from: a */
    private static long m2894a(String[] strArr) {
        int i = 0;
        while (i < strArr.length) {
            if ("max-age".equalsIgnoreCase(strArr[i]) && strArr[i + 1] != null) {
                try {
                    return Long.parseLong(strArr[i + 1]);
                } catch (Exception e) {
                }
            }
            i++;
        }
        return 0;
    }

    /* renamed from: a */
    private static HttpUrlHeader m2895a(HttpResponse httpResponse) {
        HttpUrlHeader httpUrlHeader = new HttpUrlHeader();
        for (Header header : httpResponse.getAllHeaders()) {
            httpUrlHeader.setHead(header.getName(), header.getValue());
        }
        return httpUrlHeader;
    }

    /* renamed from: a */
    private C0756u m2896a(HttpResponse httpResponse, int i, String str) {
        Throwable th;
        ByteArrayOutputStream byteArrayOutputStream;
        String str2 = null;
        new StringBuilder("开始handle，handleResponse-1,").append(Thread.currentThread().getId());
        HttpEntity entity = httpResponse.getEntity();
        if (entity != null && httpResponse.getStatusLine().getStatusCode() == 200) {
            new StringBuilder("200，开始处理，handleResponse-2,threadid = ").append(Thread.currentThread().getId());
            try {
                OutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                try {
                    String str3;
                    long currentTimeMillis = System.currentTimeMillis();
                    m2898a(entity, byteArrayOutputStream2);
                    byte[] toByteArray = byteArrayOutputStream2.toByteArray();
                    this.f1797o = false;
                    this.f1784a.m2873c(System.currentTimeMillis() - currentTimeMillis);
                    this.f1784a.m2871a((long) toByteArray.length);
                    new StringBuilder("res:").append(toByteArray.length);
                    C0756u c0757p = new C0757p(C0758q.m2895a(httpResponse), i, str, toByteArray);
                    currentTimeMillis = C0758q.m2899b(httpResponse);
                    Header contentType = httpResponse.getEntity().getContentType();
                    if (contentType != null) {
                        HashMap a = C0758q.m2897a(contentType.getValue());
                        str2 = (String) a.get("charset");
                        str3 = (String) a.get("Content-Type");
                    } else {
                        str3 = null;
                    }
                    c0757p.m2888b(str3);
                    c0757p.m2892a(str2);
                    c0757p.m2891a(System.currentTimeMillis());
                    c0757p.m2893b(currentTimeMillis);
                    try {
                        byteArrayOutputStream2.close();
                        return c0757p;
                    } catch (IOException e) {
                        throw new RuntimeException("ArrayOutputStream close error!", e.getCause());
                    }
                } catch (Throwable th2) {
                    th = th2;
                    OutputStream outputStream = byteArrayOutputStream2;
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (IOException e2) {
                            throw new RuntimeException("ArrayOutputStream close error!", e2.getCause());
                        }
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                Throwable th4 = th3;
                byteArrayOutputStream = null;
                th = th4;
                if (byteArrayOutputStream != null) {
                    byteArrayOutputStream.close();
                }
                throw th;
            }
        } else if (entity != null) {
            return null;
        } else {
            httpResponse.getStatusLine().getStatusCode();
            return null;
        }
    }

    /* renamed from: a */
    private static HashMap<String, String> m2897a(String str) {
        HashMap<String, String> hashMap = new HashMap();
        for (String str2 : str.split(C0880h.f2220b)) {
            String[] split = str2.indexOf(61) == -1 ? new String[]{"Content-Type", str2} : str2.split(SimpleComparison.EQUAL_TO_OPERATION);
            hashMap.put(split[0], split[1]);
        }
        return hashMap;
    }

    /* renamed from: a */
    private void m2898a(HttpEntity httpEntity, OutputStream outputStream) {
        Closeable a = C0739b.m2841a(httpEntity);
        long contentLength = httpEntity.getContentLength();
        try {
            byte[] bArr = new byte[2048];
            while (true) {
                int read = a.read(bArr);
                if (read == -1 || this.f1786c.m2876h()) {
                    outputStream.flush();
                } else {
                    outputStream.write(bArr, 0, read);
                    if (this.f1786c.m2874f() != null && contentLength > 0) {
                        this.f1786c.m2874f();
                        C0755o c0755o = this.f1786c;
                    }
                }
            }
            outputStream.flush();
            C0759r.m2909a(a);
        } catch (Exception e) {
            e.getCause();
            throw new IOException("HttpWorker Request Error!" + e.getLocalizedMessage());
        } catch (Throwable th) {
            C0759r.m2909a(a);
        }
    }

    /* renamed from: b */
    private static long m2899b(HttpResponse httpResponse) {
        long j = 0;
        Header firstHeader = httpResponse.getFirstHeader("Cache-Control");
        if (firstHeader != null) {
            String[] split = firstHeader.getValue().split(SimpleComparison.EQUAL_TO_OPERATION);
            if (split.length >= 2) {
                try {
                    return C0758q.m2894a(split);
                } catch (NumberFormatException e) {
                }
            }
        }
        firstHeader = httpResponse.getFirstHeader("Expires");
        return firstHeader != null ? C0739b.m2846b(firstHeader.getValue()) - System.currentTimeMillis() : j;
    }

    /* renamed from: b */
    private URI m2900b() {
        String a = this.f1786c.m2877a();
        if (this.f1787d != null) {
            a = this.f1787d;
        }
        if (a != null) {
            return new URI(a);
        }
        throw new RuntimeException("url should not be null");
    }

    /* renamed from: c */
    private HttpUriRequest m2901c() {
        if (this.f1788f != null) {
            return this.f1788f;
        }
        if (this.f1792j == null) {
            byte[] b = this.f1786c.m2884b();
            CharSequence b2 = this.f1786c.m2883b(AsyncHttpClient.ENCODING_GZIP);
            if (b != null) {
                if (TextUtils.equals(b2, "true")) {
                    this.f1792j = C0739b.m2844a(b);
                } else {
                    this.f1792j = new ByteArrayEntity(b);
                }
                this.f1792j.setContentType(this.f1786c.m2885c());
            }
        }
        HttpEntity httpEntity = this.f1792j;
        if (httpEntity != null) {
            HttpUriRequest httpPost = new HttpPost(m2900b());
            httpPost.setEntity(httpEntity);
            this.f1788f = httpPost;
        } else {
            this.f1788f = new HttpGet(m2900b());
        }
        return this.f1788f;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: d */
    private com.alipay.android.phone.mrpc.core.C0756u m2902d() {
        /*
        r15 = this;
        r14 = 6;
        r13 = 3;
        r12 = 2;
        r4 = 1;
        r5 = 0;
    L_0x0005:
        r2 = r15.f1785b;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = "connectivity";
        r2 = r2.getSystemService(r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = (android.net.ConnectivityManager) r2;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = r2.getAllNetworkInfo();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r3 != 0) goto L_0x0047;
    L_0x0015:
        r2 = r5;
    L_0x0016:
        if (r2 != 0) goto L_0x0060;
    L_0x0018:
        r2 = new com.alipay.android.phone.mrpc.core.HttpException;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = 1;
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = "The network is not available";
        r2.<init>(r3, r6);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        throw r2;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
    L_0x0025:
        r2 = move-exception;
        r15.m2903e();
        r3 = r15.f1786c;
        r3 = r3.m2874f();
        if (r3 == 0) goto L_0x003e;
    L_0x0031:
        r3 = r15.f1786c;
        r3.m2874f();
        r3 = r15.f1786c;
        r2.getCode();
        r2.getMsg();
    L_0x003e:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        throw r2;
    L_0x0047:
        r6 = r3.length;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r5;
    L_0x0049:
        if (r2 >= r6) goto L_0x04ec;
    L_0x004b:
        r7 = r3[r2];	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r7 == 0) goto L_0x005d;
    L_0x004f:
        r8 = r7.isAvailable();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r8 == 0) goto L_0x005d;
    L_0x0055:
        r7 = r7.isConnectedOrConnecting();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r7 == 0) goto L_0x005d;
    L_0x005b:
        r2 = r4;
        goto L_0x0016;
    L_0x005d:
        r2 = r2 + 1;
        goto L_0x0049;
    L_0x0060:
        r2 = r15.f1786c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r2.m2874f();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r2 == 0) goto L_0x006f;
    L_0x0068:
        r2 = r15.f1786c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2.m2874f();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r15.f1786c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
    L_0x006f:
        r2 = r15.f1786c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r2.m2886d();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r2 == 0) goto L_0x00a2;
    L_0x0077:
        r3 = r2.isEmpty();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r3 != 0) goto L_0x00a2;
    L_0x007d:
        r3 = r2.iterator();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
    L_0x0081:
        r2 = r3.hasNext();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r2 == 0) goto L_0x00a2;
    L_0x0087:
        r2 = r3.next();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = (org.apache.http.Header) r2;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = r15.m2901c();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6.addHeader(r2);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        goto L_0x0081;
    L_0x0095:
        r2 = move-exception;
        r3 = new java.lang.RuntimeException;
        r4 = "Url parser error!";
        r2 = r2.getCause();
        r3.<init>(r4, r2);
        throw r3;
    L_0x00a2:
        r2 = r15.m2901c();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        com.alipay.android.phone.mrpc.core.C0739b.m2845a(r2);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r15.m2901c();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        com.alipay.android.phone.mrpc.core.C0739b.m2847b(r2);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r15.m2901c();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = "cookie";
        r6 = r15.m2907i();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r7 = r15.f1786c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r7 = r7.m2877a();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = r6.getCookie(r7);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2.addHeader(r3, r6);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r15.f1789g;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = "http.cookie-store";
        r6 = r15.f1790h;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2.setAttribute(r3, r6);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r15.f1784a;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r2.m2869a();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = f1783e;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2.m2849a(r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = java.lang.System.currentTimeMillis();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = new java.lang.StringBuilder;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = "By Http/Https to request. operationType=";
        r2.<init>(r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = r15.m2904f();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r2.append(r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = " url=";
        r2 = r2.append(r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = r15.f1788f;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = r3.getURI();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = r3.toString();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2.append(r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r15.f1784a;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r2.m2869a();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r8 = r2.getParams();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r9 = "http.route.default-proxy";
        r2 = r15.f1785b;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = 0;
        r10 = "connectivity";
        r2 = r2.getSystemService(r10);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = (android.net.ConnectivityManager) r2;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r2.getActiveNetworkInfo();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r2 == 0) goto L_0x04e9;
    L_0x011e:
        r2 = r2.isAvailable();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r2 == 0) goto L_0x04e9;
    L_0x0124:
        r10 = android.net.Proxy.getDefaultHost();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r11 = android.net.Proxy.getDefaultPort();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r10 == 0) goto L_0x04e9;
    L_0x012e:
        r2 = new org.apache.http.HttpHost;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2.<init>(r10, r11);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
    L_0x0133:
        if (r2 == 0) goto L_0x014a;
    L_0x0135:
        r3 = r2.getHostName();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r10 = "127.0.0.1";
        r3 = android.text.TextUtils.equals(r3, r10);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r3 == 0) goto L_0x014a;
    L_0x0141:
        r3 = r2.getPort();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r10 = 8087; // 0x1f97 float:1.1332E-41 double:3.9955E-320;
        if (r3 != r10) goto L_0x014a;
    L_0x0149:
        r2 = 0;
    L_0x014a:
        r8.setParameter(r9, r2);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r15.f1793k;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r2 == 0) goto L_0x0231;
    L_0x0151:
        r2 = r15.f1793k;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
    L_0x0153:
        r3 = r15.m2905g();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r8 = 80;
        if (r3 != r8) goto L_0x0168;
    L_0x015b:
        r2 = new org.apache.http.HttpHost;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = r15.m2906h();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = r3.getHost();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2.<init>(r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
    L_0x0168:
        r3 = r15.f1784a;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = r3.m2869a();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r8 = r15.f1788f;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r9 = r15.f1789g;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = r3.execute(r2, r8, r9);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r8 = java.lang.System.currentTimeMillis();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r15.f1784a;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = r8 - r6;
        r2.m2872b(r6);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r15.f1790h;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r2.getCookies();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = r15.f1786c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = r6.m2887e();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r6 == 0) goto L_0x0196;
    L_0x018f:
        r6 = r15.m2907i();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6.removeAllCookie();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
    L_0x0196:
        r6 = r2.isEmpty();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r6 != 0) goto L_0x024f;
    L_0x019c:
        r6 = r2.iterator();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
    L_0x01a0:
        r2 = r6.hasNext();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r2 == 0) goto L_0x024f;
    L_0x01a6:
        r2 = r6.next();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = (org.apache.http.cookie.Cookie) r2;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r7 = r2.getDomain();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r7 == 0) goto L_0x01a0;
    L_0x01b2:
        r7 = new java.lang.StringBuilder;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r7.<init>();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r8 = r2.getName();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r7 = r7.append(r8);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r8 = "=";
        r7 = r7.append(r8);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r8 = r2.getValue();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r7 = r7.append(r8);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r8 = "; domain=";
        r7 = r7.append(r8);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r8 = r2.getDomain();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r7 = r7.append(r8);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r2.isSecure();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r2 == 0) goto L_0x024c;
    L_0x01e1:
        r2 = "; Secure";
    L_0x01e3:
        r2 = r7.append(r2);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r2.toString();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r7 = r15.m2907i();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r8 = r15.f1786c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r8 = r8.m2877a();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r7.setCookie(r8, r2);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = android.webkit.CookieSyncManager.getInstance();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2.sync();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        goto L_0x01a0;
    L_0x0200:
        r2 = move-exception;
        r15.m2903e();
        r3 = r15.f1786c;
        r3 = r3.m2874f();
        if (r3 == 0) goto L_0x021b;
    L_0x020c:
        r3 = r15.f1786c;
        r3.m2874f();
        r3 = r15.f1786c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x021b:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r12);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0231:
        r2 = r15.m2906h();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = new org.apache.http.HttpHost;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r8 = r2.getHost();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r9 = r15.m2905g();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r2.getProtocol();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3.<init>(r8, r9, r2);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r15.f1793k = r3;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r15.f1793k;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        goto L_0x0153;
    L_0x024c:
        r2 = "";
        goto L_0x01e3;
    L_0x024f:
        r2 = r15.f1786c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r3.getStatusLine();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = r2.getStatusCode();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r3.getStatusLine();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r7 = r2.getReasonPhrase();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r6 == r2) goto L_0x02b9;
    L_0x0265:
        r2 = 304; // 0x130 float:4.26E-43 double:1.5E-321;
        if (r6 != r2) goto L_0x02b7;
    L_0x0269:
        r2 = r4;
    L_0x026a:
        if (r2 != 0) goto L_0x02b9;
    L_0x026c:
        r2 = new com.alipay.android.phone.mrpc.core.HttpException;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = r3.getStatusLine();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = r6.getStatusCode();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = java.lang.Integer.valueOf(r6);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = r3.getStatusLine();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r3 = r3.getReasonPhrase();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2.<init>(r6, r3);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        throw r2;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
    L_0x0286:
        r2 = move-exception;
        r15.m2903e();
        r3 = r15.f1786c;
        r3 = r3.m2874f();
        if (r3 == 0) goto L_0x02a1;
    L_0x0292:
        r3 = r15.f1786c;
        r3.m2874f();
        r3 = r15.f1786c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x02a1:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r12);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x02b7:
        r2 = r5;
        goto L_0x026a;
    L_0x02b9:
        r3 = r15.m2896a(r3, r6, r7);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = -1;
        if (r3 == 0) goto L_0x02cd;
    L_0x02c1:
        r2 = r3.m2889b();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r2 == 0) goto L_0x02cd;
    L_0x02c7:
        r2 = r3.m2889b();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r2.length;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = (long) r2;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
    L_0x02cd:
        r8 = -1;
        r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r2 != 0) goto L_0x02e8;
    L_0x02d3:
        r2 = r3 instanceof com.alipay.android.phone.mrpc.core.C0757p;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r2 == 0) goto L_0x02e8;
    L_0x02d7:
        r0 = r3;
        r0 = (com.alipay.android.phone.mrpc.core.C0757p) r0;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r0;
        r2 = r2.m2890a();	 Catch:{ Exception -> 0x04e6, HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497 }
        r6 = "Content-Length";
        r2 = r2.getHead(r6);	 Catch:{ Exception -> 0x04e6, HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497 }
        java.lang.Long.parseLong(r2);	 Catch:{ Exception -> 0x04e6, HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497 }
    L_0x02e8:
        r2 = r15.f1786c;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r2.m2877a();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r2 == 0) goto L_0x0310;
    L_0x02f0:
        r6 = r15.m2904f();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = android.text.TextUtils.isEmpty(r6);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        if (r6 != 0) goto L_0x0310;
    L_0x02fa:
        r6 = new java.lang.StringBuilder;	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6.<init>();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2 = r6.append(r2);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = "#";
        r2 = r2.append(r6);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r6 = r15.m2904f();	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
        r2.append(r6);	 Catch:{ HttpException -> 0x0025, URISyntaxException -> 0x0095, SSLHandshakeException -> 0x0200, SSLPeerUnverifiedException -> 0x0286, SSLException -> 0x0311, ConnectionPoolTimeoutException -> 0x0342, ConnectTimeoutException -> 0x0373, SocketTimeoutException -> 0x03a4, NoHttpResponseException -> 0x03d6, HttpHostConnectException -> 0x0408, UnknownHostException -> 0x0433, IOException -> 0x0466, NullPointerException -> 0x0497, Exception -> 0x04bd }
    L_0x0310:
        return r3;
    L_0x0311:
        r2 = move-exception;
        r15.m2903e();
        r3 = r15.f1786c;
        r3 = r3.m2874f();
        if (r3 == 0) goto L_0x032c;
    L_0x031d:
        r3 = r15.f1786c;
        r3.m2874f();
        r3 = r15.f1786c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x032c:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r14);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0342:
        r2 = move-exception;
        r15.m2903e();
        r3 = r15.f1786c;
        r3 = r3.m2874f();
        if (r3 == 0) goto L_0x035d;
    L_0x034e:
        r3 = r15.f1786c;
        r3.m2874f();
        r3 = r15.f1786c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x035d:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r13);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0373:
        r2 = move-exception;
        r15.m2903e();
        r3 = r15.f1786c;
        r3 = r3.m2874f();
        if (r3 == 0) goto L_0x038e;
    L_0x037f:
        r3 = r15.f1786c;
        r3.m2874f();
        r3 = r15.f1786c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x038e:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r13);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x03a4:
        r2 = move-exception;
        r15.m2903e();
        r3 = r15.f1786c;
        r3 = r3.m2874f();
        if (r3 == 0) goto L_0x03bf;
    L_0x03b0:
        r3 = r15.f1786c;
        r3.m2874f();
        r3 = r15.f1786c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x03bf:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = 4;
        r4 = java.lang.Integer.valueOf(r4);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x03d6:
        r2 = move-exception;
        r15.m2903e();
        r3 = r15.f1786c;
        r3 = r3.m2874f();
        if (r3 == 0) goto L_0x03f1;
    L_0x03e2:
        r3 = r15.f1786c;
        r3.m2874f();
        r3 = r15.f1786c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x03f1:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = 5;
        r4 = java.lang.Integer.valueOf(r4);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0408:
        r2 = move-exception;
        r15.m2903e();
        r3 = r15.f1786c;
        r3 = r3.m2874f();
        if (r3 == 0) goto L_0x0423;
    L_0x0414:
        r3 = r15.f1786c;
        r3.m2874f();
        r3 = r15.f1786c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x0423:
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = 8;
        r4 = java.lang.Integer.valueOf(r4);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0433:
        r2 = move-exception;
        r15.m2903e();
        r3 = r15.f1786c;
        r3 = r3.m2874f();
        if (r3 == 0) goto L_0x044e;
    L_0x043f:
        r3 = r15.f1786c;
        r3.m2874f();
        r3 = r15.f1786c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x044e:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = 9;
        r4 = java.lang.Integer.valueOf(r4);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0466:
        r2 = move-exception;
        r15.m2903e();
        r3 = r15.f1786c;
        r3 = r3.m2874f();
        if (r3 == 0) goto L_0x0481;
    L_0x0472:
        r3 = r15.f1786c;
        r3.m2874f();
        r3 = r15.f1786c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x0481:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r14);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x0497:
        r2 = move-exception;
        r15.m2903e();
        r3 = r15.f1795m;
        if (r3 > 0) goto L_0x04a7;
    L_0x049f:
        r2 = r15.f1795m;
        r2 = r2 + 1;
        r15.f1795m = r2;
        goto L_0x0005;
    L_0x04a7:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r5);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x04bd:
        r2 = move-exception;
        r15.m2903e();
        r3 = r15.f1786c;
        r3 = r3.m2874f();
        if (r3 == 0) goto L_0x04d8;
    L_0x04c9:
        r3 = r15.f1786c;
        r3.m2874f();
        r3 = r15.f1786c;
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r3.append(r2);
    L_0x04d8:
        r3 = new com.alipay.android.phone.mrpc.core.HttpException;
        r4 = java.lang.Integer.valueOf(r5);
        r2 = java.lang.String.valueOf(r2);
        r3.<init>(r4, r2);
        throw r3;
    L_0x04e6:
        r2 = move-exception;
        goto L_0x02e8;
    L_0x04e9:
        r2 = r3;
        goto L_0x0133;
    L_0x04ec:
        r2 = r5;
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.android.phone.mrpc.core.q.d():com.alipay.android.phone.mrpc.core.u");
    }

    /* renamed from: e */
    private void m2903e() {
        if (this.f1788f != null) {
            this.f1788f.abort();
        }
    }

    /* renamed from: f */
    private String m2904f() {
        if (!TextUtils.isEmpty(this.f1799q)) {
            return this.f1799q;
        }
        this.f1799q = this.f1786c.m2883b("operationType");
        return this.f1799q;
    }

    /* renamed from: g */
    private int m2905g() {
        URL h = m2906h();
        return h.getPort() == -1 ? h.getDefaultPort() : h.getPort();
    }

    /* renamed from: h */
    private URL m2906h() {
        if (this.f1794l != null) {
            return this.f1794l;
        }
        this.f1794l = new URL(this.f1786c.m2877a());
        return this.f1794l;
    }

    /* renamed from: i */
    private CookieManager m2907i() {
        if (this.f1791i != null) {
            return this.f1791i;
        }
        this.f1791i = CookieManager.getInstance();
        return this.f1791i;
    }

    /* renamed from: a */
    public final C0755o m2908a() {
        return this.f1786c;
    }

    public final /* synthetic */ Object call() {
        return m2902d();
    }
}
