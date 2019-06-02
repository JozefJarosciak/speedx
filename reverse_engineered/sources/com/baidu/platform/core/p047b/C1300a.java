package com.baidu.platform.core.p047b;

import com.baidu.mapapi.search.geocode.GeoCodeOption;
import com.baidu.mapapi.search.geocode.OnGetGeoCoderResultListener;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.platform.base.C1208a;
import com.baidu.platform.base.C1213f;
import com.baidu.platform.base.SearchType;

/* renamed from: com.baidu.platform.core.b.a */
public class C1300a extends C1208a implements C1299f {
    /* renamed from: b */
    OnGetGeoCoderResultListener f3950b;

    /* renamed from: a */
    public void mo2684a() {
        this.f3950b = null;
    }

    /* renamed from: a */
    public void mo2688a(OnGetGeoCoderResultListener onGetGeoCoderResultListener) {
        this.f3950b = onGetGeoCoderResultListener;
    }

    /* renamed from: a */
    public boolean mo2689a(GeoCodeOption geoCodeOption) {
        this.a = new C1303d();
        C1213f c1304e = new C1304e(geoCodeOption);
        this.a.m4537a(new C1301b(this));
        this.a.m4536a(SearchType.GEO_CODER);
        return m4533a(c1304e);
    }

    /* renamed from: a */
    public boolean mo2690a(ReverseGeoCodeOption reverseGeoCodeOption) {
        this.a = new C1305g();
        C1213f c1306h = new C1306h(reverseGeoCodeOption);
        this.a.m4537a(new C1302c(this));
        this.a.m4536a(SearchType.REVERSE_GEO_CODER);
        return m4533a(c1306h);
    }
}
