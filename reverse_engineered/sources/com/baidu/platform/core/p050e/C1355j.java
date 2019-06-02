package com.baidu.platform.core.p050e;

import com.baidu.mapapi.search.share.ShareUrlResult;
import com.baidu.platform.base.C1211d;

/* renamed from: com.baidu.platform.core.e.j */
class C1355j implements C1211d<ShareUrlResult> {
    /* renamed from: a */
    final /* synthetic */ C1352g f3976a;

    C1355j(C1352g c1352g) {
        this.f3976a = c1352g;
    }

    /* renamed from: a */
    public void m5181a(ShareUrlResult shareUrlResult) {
        if (this.f3976a.f3973b != null) {
            this.f3976a.f3973b.onGetRouteShareUrlResult(shareUrlResult);
        }
    }
}
