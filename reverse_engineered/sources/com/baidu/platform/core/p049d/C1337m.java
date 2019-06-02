package com.baidu.platform.core.p049d;

import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.platform.base.C1211d;

/* renamed from: com.baidu.platform.core.d.m */
class C1337m implements C1211d<WalkingRouteResult> {
    /* renamed from: a */
    final /* synthetic */ C1334j f3968a;

    C1337m(C1334j c1334j) {
        this.f3968a = c1334j;
    }

    /* renamed from: a */
    public void m5134a(WalkingRouteResult walkingRouteResult) {
        if (this.f3968a.f3965b != null) {
            this.f3968a.f3965b.onGetWalkingRouteResult(walkingRouteResult);
        }
    }
}
