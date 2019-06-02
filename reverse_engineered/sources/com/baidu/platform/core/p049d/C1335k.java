package com.baidu.platform.core.p049d;

import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.platform.base.C1211d;

/* renamed from: com.baidu.platform.core.d.k */
class C1335k implements C1211d<TransitRouteResult> {
    /* renamed from: a */
    final /* synthetic */ C1334j f3966a;

    C1335k(C1334j c1334j) {
        this.f3966a = c1334j;
    }

    /* renamed from: a */
    public void m5130a(TransitRouteResult transitRouteResult) {
        if (this.f3966a.f3965b != null) {
            this.f3966a.f3965b.onGetTransitRouteResult(transitRouteResult);
        }
    }
}
