package com.twitter.sdk.android.core;

import io.fabric.sdk.android.C1520c;
import java.io.IOException;
import java.net.HttpURLConnection;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSocketFactory;
import retrofit.client.Client;
import retrofit.client.OkClient;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.client.UrlConnectionClient;

/* compiled from: DefaultClient */
/* renamed from: com.twitter.sdk.android.core.f */
public class C4578f implements Client {
    /* renamed from: a */
    final Client f16205a;
    /* renamed from: b */
    final SSLSocketFactory f16206b;

    /* compiled from: DefaultClient */
    /* renamed from: com.twitter.sdk.android.core.f$1 */
    class C45811 extends OkClient {
        /* renamed from: a */
        final /* synthetic */ C4578f f16209a;

        C45811(C4578f c4578f) {
            this.f16209a = c4578f;
        }

        protected HttpURLConnection openConnection(Request request) throws IOException {
            return this.f16209a.m18146a(super.openConnection(request));
        }
    }

    /* compiled from: DefaultClient */
    /* renamed from: com.twitter.sdk.android.core.f$2 */
    class C45822 extends UrlConnectionClient {
        /* renamed from: a */
        final /* synthetic */ C4578f f16210a;

        C45822(C4578f c4578f) {
            this.f16210a = c4578f;
        }

        protected HttpURLConnection openConnection(Request request) throws IOException {
            return this.f16210a.m18146a(super.openConnection(request));
        }
    }

    public C4578f(SSLSocketFactory sSLSocketFactory) {
        this.f16206b = sSLSocketFactory;
        if (m18145a()) {
            this.f16205a = new C45811(this);
        } else {
            this.f16205a = new C45822(this);
        }
    }

    public Response execute(Request request) throws IOException {
        return this.f16205a.execute(request);
    }

    /* renamed from: a */
    private boolean m18145a() {
        boolean z;
        boolean z2 = true;
        try {
            Class.forName("com.squareup.okhttp.OkUrlFactory");
            z = true;
        } catch (ClassNotFoundException e) {
            z = false;
        }
        try {
            Class.forName("com.squareup.okhttp.OkHttpClient");
        } catch (ClassNotFoundException e2) {
            z2 = false;
        }
        if (z2 == z) {
            return z2;
        }
        C1520c.h().mo6215a("Twitter", "Retrofit detected an unsupported OkHttp on the classpath.\nTo use OkHttp with this version of Retrofit, you'll need:\n1. com.squareup.okhttp:okhttp:1.6.0 (or newer)\n2. com.squareup.okhttp:okhttp-urlconnection:1.6.0 (or newer)\nNote that OkHttp 2.0.0+ is supported!");
        return false;
    }

    /* renamed from: a */
    HttpURLConnection m18146a(HttpURLConnection httpURLConnection) {
        if (this.f16206b != null && (httpURLConnection instanceof HttpsURLConnection)) {
            ((HttpsURLConnection) httpURLConnection).setSSLSocketFactory(this.f16206b);
        }
        return httpURLConnection;
    }
}
