package com.baidu.platform.core.p050e;

import com.baidu.mapapi.search.share.ShareUrlResult;
import com.baidu.platform.base.C1211d;

/* renamed from: com.baidu.platform.core.e.h */
class C1353h implements C1211d<ShareUrlResult> {
    /* renamed from: a */
    final /* synthetic */ C1352g f3974a;

    C1353h(C1352g c1352g) {
        this.f3974a = c1352g;
    }

    /* renamed from: a */
    public void m5177a(ShareUrlResult shareUrlResult) {
        if (this.f3974a.f3973b != null) {
            this.f3974a.f3973b.onGetPoiDetailShareUrlResult(shareUrlResult);
        }
    }
}
