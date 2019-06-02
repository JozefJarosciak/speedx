package com.avos.avoscloud;

import com.alipay.sdk.cons.C0845b;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import org.apache.http.HttpHost;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.java_websocket.WebSocket;

public abstract class HttpClientUploader extends AVUploader {
    private static AtomicInteger counter = new AtomicInteger(0);
    protected static DefaultHttpClient httpClient;
    protected final AtomicReference<HttpPost> httpPostRef = new AtomicReference();

    public /* bridge */ /* synthetic */ boolean cancel(boolean z) {
        return super.cancel(z);
    }

    public /* bridge */ /* synthetic */ void execute() {
        super.execute();
    }

    public /* bridge */ /* synthetic */ boolean isCancelled() {
        return super.isCancelled();
    }

    public /* bridge */ /* synthetic */ void publishProgress(int i) {
        super.publishProgress(i);
    }

    protected HttpClientUploader(AVFile aVFile, SaveCallback saveCallback, ProgressCallback progressCallback) {
        super(aVFile, saveCallback, progressCallback);
    }

    protected static synchronized DefaultHttpClient getHttpClient() {
        DefaultHttpClient defaultHttpClient;
        synchronized (HttpClientUploader.class) {
            if (httpClient != null) {
                defaultHttpClient = httpClient;
            } else {
                HttpParams basicHttpParams = new BasicHttpParams();
                SchemeRegistry schemeRegistry = new SchemeRegistry();
                schemeRegistry.register(new Scheme(HttpHost.DEFAULT_SCHEME_NAME, PlainSocketFactory.getSocketFactory(), 80));
                schemeRegistry.register(new Scheme(C0845b.f2060a, SSLSocketFactory.getSocketFactory(), WebSocket.DEFAULT_WSS_PORT));
                HttpConnectionParams.setConnectionTimeout(basicHttpParams, AVOSCloud.getNetworkTimeout());
                HttpConnectionParams.setSoTimeout(basicHttpParams, AVOSCloud.getNetworkTimeout());
                HttpConnectionParams.setTcpNoDelay(basicHttpParams, true);
                httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager(basicHttpParams, schemeRegistry), basicHttpParams);
                defaultHttpClient = httpClient;
            }
        }
        return defaultHttpClient;
    }

    public void interruptImmediately() {
        HttpPost httpPost = (HttpPost) this.httpPostRef.get();
        if (httpPost != null) {
            httpPost.abort();
        }
        super.interruptImmediately();
    }

    protected void closeExpiredConnections() {
        if (counter.incrementAndGet() % 10 == 0) {
            try {
                if (httpClient != null && httpClient.getConnectionManager() != null) {
                    httpClient.getConnectionManager().closeExpiredConnections();
                    httpClient.getConnectionManager().closeIdleConnections(60, TimeUnit.SECONDS);
                }
            } catch (Exception e) {
            }
        }
    }
}
