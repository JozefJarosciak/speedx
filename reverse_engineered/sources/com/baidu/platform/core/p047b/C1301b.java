package com.baidu.platform.core.p047b;

import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.platform.base.C1211d;

/* renamed from: com.baidu.platform.core.b.b */
class C1301b implements C1211d<GeoCodeResult> {
    /* renamed from: a */
    final /* synthetic */ C1300a f3951a;

    C1301b(C1300a c1300a) {
        this.f3951a = c1300a;
    }

    /* renamed from: a */
    public void m5000a(GeoCodeResult geoCodeResult) {
        if (this.f3951a.f3950b != null) {
            this.f3951a.f3950b.onGetGeoCodeResult(geoCodeResult);
        }
    }
}
