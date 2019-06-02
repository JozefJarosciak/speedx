package com.baidu.platform.core.p048c;

import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.platform.base.C1211d;

/* renamed from: com.baidu.platform.core.c.k */
class C1321k implements C1211d<PoiIndoorResult> {
    /* renamed from: a */
    final /* synthetic */ C1316f f3960a;

    C1321k(C1316f c1316f) {
        this.f3960a = c1316f;
    }

    /* renamed from: a */
    public void m5060a(PoiIndoorResult poiIndoorResult) {
        if (this.f3960a.f3955b != null) {
            this.f3960a.f3955b.onGetPoiIndoorResult(poiIndoorResult);
        }
    }
}
