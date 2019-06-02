package com.tencent.wxop.stat;

import org.apache.http.HttpResponse;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.protocol.HttpContext;

/* renamed from: com.tencent.wxop.stat.j */
class C4552j extends DefaultConnectionKeepAliveStrategy {
    /* renamed from: a */
    final /* synthetic */ C4551i f16133a;

    C4552j(C4551i c4551i) {
        this.f16133a = c4551i;
    }

    public long getKeepAliveDuration(HttpResponse httpResponse, HttpContext httpContext) {
        long keepAliveDuration = super.getKeepAliveDuration(httpResponse, httpContext);
        return keepAliveDuration == -1 ? 30000 : keepAliveDuration;
    }
}
