package com.baidu.platform.core.p049d;

import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.platform.base.C1211d;

/* renamed from: com.baidu.platform.core.d.l */
class C1336l implements C1211d<MassTransitRouteResult> {
    /* renamed from: a */
    final /* synthetic */ C1334j f3967a;

    C1336l(C1334j c1334j) {
        this.f3967a = c1334j;
    }

    /* renamed from: a */
    public void m5132a(MassTransitRouteResult massTransitRouteResult) {
        if (this.f3967a.f3965b != null) {
            this.f3967a.f3965b.onGetMassTransitRouteResult(massTransitRouteResult);
        }
    }
}
