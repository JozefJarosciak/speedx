package com.alipay.android.phone.mrpc.core;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.impl.client.DefaultRedirectHandler;
import org.apache.http.protocol.HttpContext;

/* renamed from: com.alipay.android.phone.mrpc.core.e */
final class C0742e extends DefaultRedirectHandler {
    /* renamed from: a */
    int f1743a;
    /* renamed from: b */
    final /* synthetic */ C0741d f1744b;

    C0742e(C0741d c0741d) {
        this.f1744b = c0741d;
    }

    public final boolean isRedirectRequested(HttpResponse httpResponse, HttpContext httpContext) {
        this.f1743a++;
        boolean isRedirectRequested = super.isRedirectRequested(httpResponse, httpContext);
        if (isRedirectRequested || this.f1743a >= 5) {
            return isRedirectRequested;
        }
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        return (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) ? true : isRedirectRequested;
    }
}
