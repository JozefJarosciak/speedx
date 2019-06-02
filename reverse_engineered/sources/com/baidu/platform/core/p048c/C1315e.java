package com.baidu.platform.core.p048c;

import ch.qos.logback.core.joran.action.Action;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.platform.base.C1213f;
import com.baidu.platform.domain.C1361b;

/* renamed from: com.baidu.platform.core.c.e */
public class C1315e extends C1213f {
    C1315e(PoiDetailSearchOption poiDetailSearchOption) {
        m5043a(poiDetailSearchOption);
    }

    /* renamed from: a */
    public String mo2682a(C1361b c1361b) {
        return c1361b.mo2713b();
    }

    /* renamed from: a */
    void m5043a(PoiDetailSearchOption poiDetailSearchOption) {
        this.a.m5234a("uid", poiDetailSearchOption.mUid);
        this.a.m5234a("output", "json");
        this.a.m5234a(Action.SCOPE_ATTRIBUTE, "2");
    }
}
