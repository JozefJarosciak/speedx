package com.baidu.platform.core.p049d;

import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.platform.base.C1211d;

/* renamed from: com.baidu.platform.core.d.o */
class C1339o implements C1211d<DrivingRouteResult> {
    /* renamed from: a */
    final /* synthetic */ C1334j f3970a;

    C1339o(C1334j c1334j) {
        this.f3970a = c1334j;
    }

    /* renamed from: a */
    public void m5138a(DrivingRouteResult drivingRouteResult) {
        if (this.f3970a.f3965b != null) {
            this.f3970a.f3965b.onGetDrivingRouteResult(drivingRouteResult);
        }
    }
}
