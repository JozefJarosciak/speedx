package com.baidu.platform.core.p050e;

import com.baidu.mapapi.search.share.ShareUrlResult;
import com.baidu.platform.base.C1211d;

/* renamed from: com.baidu.platform.core.e.i */
class C1354i implements C1211d<ShareUrlResult> {
    /* renamed from: a */
    final /* synthetic */ C1352g f3975a;

    C1354i(C1352g c1352g) {
        this.f3975a = c1352g;
    }

    /* renamed from: a */
    public void m5179a(ShareUrlResult shareUrlResult) {
        if (this.f3975a.f3973b != null) {
            this.f3975a.f3973b.onGetLocationShareUrlResult(shareUrlResult);
        }
    }
}
