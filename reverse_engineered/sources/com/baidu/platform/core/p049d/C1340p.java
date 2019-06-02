package com.baidu.platform.core.p049d;

import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.platform.base.C1211d;

/* renamed from: com.baidu.platform.core.d.p */
class C1340p implements C1211d<BikingRouteResult> {
    /* renamed from: a */
    final /* synthetic */ C1334j f3971a;

    C1340p(C1334j c1334j) {
        this.f3971a = c1334j;
    }

    /* renamed from: a */
    public void m5140a(BikingRouteResult bikingRouteResult) {
        if (this.f3971a.f3965b != null) {
            this.f3971a.f3965b.onGetBikingRouteResult(bikingRouteResult);
        }
    }
}
