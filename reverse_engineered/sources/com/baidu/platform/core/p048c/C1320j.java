package com.baidu.platform.core.p048c;

import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.platform.base.C1211d;

/* renamed from: com.baidu.platform.core.c.j */
class C1320j implements C1211d<PoiDetailResult> {
    /* renamed from: a */
    final /* synthetic */ C1316f f3959a;

    C1320j(C1316f c1316f) {
        this.f3959a = c1316f;
    }

    /* renamed from: a */
    public void m5058a(PoiDetailResult poiDetailResult) {
        if (this.f3959a.f3955b != null) {
            this.f3959a.f3955b.onGetPoiDetailResult(poiDetailResult);
        }
    }
}
