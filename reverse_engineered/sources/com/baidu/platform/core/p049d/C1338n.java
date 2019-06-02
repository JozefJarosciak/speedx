package com.baidu.platform.core.p049d;

import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.platform.base.C1211d;

/* renamed from: com.baidu.platform.core.d.n */
class C1338n implements C1211d<IndoorRouteResult> {
    /* renamed from: a */
    final /* synthetic */ C1334j f3969a;

    C1338n(C1334j c1334j) {
        this.f3969a = c1334j;
    }

    /* renamed from: a */
    public void m5136a(IndoorRouteResult indoorRouteResult) {
        if (this.f3969a.f3965b != null) {
            this.f3969a.f3965b.onGetIndoorRouteResult(indoorRouteResult);
        }
    }
}
