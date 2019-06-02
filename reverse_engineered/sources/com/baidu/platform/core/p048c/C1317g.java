package com.baidu.platform.core.p048c;

import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.platform.base.C1211d;

/* renamed from: com.baidu.platform.core.c.g */
class C1317g implements C1211d<PoiResult> {
    /* renamed from: a */
    final /* synthetic */ C1316f f3956a;

    C1317g(C1316f c1316f) {
        this.f3956a = c1316f;
    }

    /* renamed from: a */
    public void m5052a(PoiResult poiResult) {
        if (this.f3956a.f3955b != null) {
            this.f3956a.f3955b.onGetPoiResult(poiResult);
        }
    }
}
