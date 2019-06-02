package com.baidu.platform.core.p047b;

import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.platform.base.C1211d;

/* renamed from: com.baidu.platform.core.b.c */
class C1302c implements C1211d<ReverseGeoCodeResult> {
    /* renamed from: a */
    final /* synthetic */ C1300a f3952a;

    C1302c(C1300a c1300a) {
        this.f3952a = c1300a;
    }

    /* renamed from: a */
    public void m5002a(ReverseGeoCodeResult reverseGeoCodeResult) {
        if (this.f3952a.f3950b != null) {
            this.f3952a.f3950b.onGetReverseGeoCodeResult(reverseGeoCodeResult);
        }
    }
}
