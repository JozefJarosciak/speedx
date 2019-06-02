package com.baidu.platform.core.p047b;

import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.platform.base.C1213f;
import com.baidu.platform.domain.C1361b;

/* renamed from: com.baidu.platform.core.b.e */
public class C1304e extends C1213f {
    public C1304e(GeoCodeOption geoCodeOption) {
        m5006a(geoCodeOption);
    }

    /* renamed from: a */
    private void m5006a(GeoCodeOption geoCodeOption) {
        this.a.m5234a("qt", "gc");
        this.a.m5234a("cn", geoCodeOption.mCity);
        this.a.m5234a("ie", "utf-8");
        this.a.m5234a("oue", "0");
        this.a.m5234a("wd", geoCodeOption.mAddress);
    }

    /* renamed from: a */
    public String mo2682a(C1361b c1361b) {
        return c1361b.mo2717f();
    }
}
