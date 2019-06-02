package com.baidu.platform.core.p050e;

import com.baidu.mapapi.search.share.LocationShareURLOption;
import com.baidu.mapapi.search.share.OnGetShareUrlResultListener;
import com.baidu.mapapi.search.share.PoiDetailShareURLOption;
import com.baidu.mapapi.search.share.RouteShareURLOption;
import com.baidu.platform.base.C1208a;
import com.baidu.platform.base.SearchType;

/* renamed from: com.baidu.platform.core.e.g */
public class C1352g extends C1208a implements C1346a {
    /* renamed from: b */
    OnGetShareUrlResultListener f3973b = null;

    /* renamed from: a */
    public void mo2684a() {
        this.f3973b = null;
    }

    /* renamed from: a */
    public void mo2706a(OnGetShareUrlResultListener onGetShareUrlResultListener) {
        this.f3973b = onGetShareUrlResultListener;
    }

    /* renamed from: a */
    public boolean mo2707a(LocationShareURLOption locationShareURLOption) {
        this.a = new C1351f();
        this.a.m4537a(new C1354i(this));
        this.a.m4536a(SearchType.LOCATION_SEARCH_SHARE);
        return m4533a(new C1347b(locationShareURLOption));
    }

    /* renamed from: a */
    public boolean mo2708a(PoiDetailShareURLOption poiDetailShareURLOption) {
        this.a = new C1351f();
        this.a.m4537a(new C1353h(this));
        this.a.m4536a(SearchType.POI_DETAIL_SHARE);
        return m4533a(new C1348c(poiDetailShareURLOption));
    }

    /* renamed from: a */
    public boolean mo2709a(RouteShareURLOption routeShareURLOption) {
        this.a = new C1349d();
        this.a.m4537a(new C1355j(this));
        this.a.m4536a(SearchType.ROUTE_PLAN_SHARE);
        return m4533a(new C1350e(routeShareURLOption));
    }
}
