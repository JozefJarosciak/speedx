package com.baidu.platform.core.p048c;

import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.platform.base.C1211d;

/* renamed from: com.baidu.platform.core.c.i */
class C1319i implements C1211d<PoiResult> {
    /* renamed from: a */
    final /* synthetic */ C1316f f3958a;

    C1319i(C1316f c1316f) {
        this.f3958a = c1316f;
    }

    /* renamed from: a */
    public void m5056a(PoiResult poiResult) {
        if (this.f3958a.f3955b != null) {
            this.f3958a.f3955b.onGetPoiResult(poiResult);
        }
    }
}
