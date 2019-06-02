package com.alipay.android.phone.mrpc.core;

import org.apache.http.HttpResponse;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;

/* renamed from: com.alipay.android.phone.mrpc.core.f */
final class C0743f implements ConnectionKeepAliveStrategy {
    /* renamed from: a */
    final /* synthetic */ C0741d f1745a;

    C0743f(C0741d c0741d) {
        this.f1745a = c0741d;
    }

    public final long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
        return 180000;
    }
}
