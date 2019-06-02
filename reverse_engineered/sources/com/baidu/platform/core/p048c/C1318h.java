package com.baidu.platform.core.p048c;

import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.platform.base.C1211d;

/* renamed from: com.baidu.platform.core.c.h */
class C1318h implements C1211d<PoiResult> {
    /* renamed from: a */
    final /* synthetic */ C1316f f3957a;

    C1318h(C1316f c1316f) {
        this.f3957a = c1316f;
    }

    /* renamed from: a */
    public void m5054a(PoiResult poiResult) {
        if (this.f3957a.f3955b != null) {
            this.f3957a.f3955b.onGetPoiResult(poiResult);
        }
    }
}
