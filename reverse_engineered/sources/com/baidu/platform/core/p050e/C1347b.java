package com.baidu.platform.core.p050e;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.inner.Point;
import com.baidu.mapapi.search.share.LocationShareURLOption;
import com.baidu.platform.base.C1213f;
import com.baidu.platform.domain.C1361b;

/* renamed from: com.baidu.platform.core.e.b */
public class C1347b extends C1213f {
    public C1347b(LocationShareURLOption locationShareURLOption) {
        m5163a(locationShareURLOption);
    }

    /* renamed from: a */
    private void m5163a(LocationShareURLOption locationShareURLOption) {
        this.a.m5234a("qt", "cs");
        Point ll2point = CoordUtil.ll2point(locationShareURLOption.mLocation);
        this.a.m5234a("geo", ll2point.f3293x + "|" + ll2point.f3294y);
        this.a.m5234a("t", locationShareURLOption.mName);
        this.a.m5234a("cnt", locationShareURLOption.mSnippet);
        m4544b(false);
        m4543a(false);
    }

    /* renamed from: a */
    public String mo2682a(C1361b c1361b) {
        return c1361b.mo2728q();
    }
}
