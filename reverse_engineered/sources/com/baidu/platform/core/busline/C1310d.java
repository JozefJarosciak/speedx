package com.baidu.platform.core.busline;

import com.baidu.mapapi.search.busline.BusLineResult;
import com.baidu.platform.base.C1211d;

/* renamed from: com.baidu.platform.core.busline.d */
class C1310d implements C1211d<BusLineResult> {
    /* renamed from: a */
    final /* synthetic */ C1309c f3954a;

    C1310d(C1309c c1309c) {
        this.f3954a = c1309c;
    }

    /* renamed from: a */
    public void m5027a(BusLineResult busLineResult) {
        if (this.f3954a.f3953b != null) {
            this.f3954a.f3953b.onGetBusLineResult(busLineResult);
        }
    }
}
