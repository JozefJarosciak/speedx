package com.baidu.platform.core.p048c;

import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiIndoorOption;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.platform.base.C1208a;
import com.baidu.platform.base.SearchType;

/* renamed from: com.baidu.platform.core.c.f */
public class C1316f extends C1208a implements C1311a {
    /* renamed from: b */
    private OnGetPoiSearchResultListener f3955b = null;

    /* renamed from: a */
    public void mo2684a() {
        this.f3955b = null;
    }

    /* renamed from: a */
    public void mo2693a(OnGetPoiSearchResultListener onGetPoiSearchResultListener) {
        this.f3955b = onGetPoiSearchResultListener;
    }

    /* renamed from: a */
    public boolean mo2694a(PoiBoundSearchOption poiBoundSearchOption) {
        this.a = new C1322l(poiBoundSearchOption.mPageNum, poiBoundSearchOption.mPageCapacity);
        this.a.m4537a(new C1319i(this));
        this.a.m4536a(SearchType.POI_IN_BOUND_SEARCH);
        return m4533a(new C1323m(poiBoundSearchOption));
    }

    /* renamed from: a */
    public boolean mo2695a(PoiCitySearchOption poiCitySearchOption) {
        this.a = new C1322l(poiCitySearchOption.mPageNum, poiCitySearchOption.mPageCapacity);
        this.a.m4537a(new C1318h(this));
        this.a.m4536a(SearchType.POI_IN_CITY_SEARCH);
        return m4533a(new C1323m(poiCitySearchOption));
    }

    /* renamed from: a */
    public boolean mo2696a(PoiDetailSearchOption poiDetailSearchOption) {
        this.a = new C1314d();
        this.a.m4537a(new C1320j(this));
        this.a.m4536a(SearchType.POI_DETAIL_SEARCH);
        return m4533a(new C1315e(poiDetailSearchOption));
    }

    /* renamed from: a */
    public boolean mo2697a(PoiIndoorOption poiIndoorOption) {
        this.a = new C1312b();
        this.a.m4537a(new C1321k(this));
        this.a.m4536a(SearchType.INDOOR_POI_SEARCH);
        return m4533a(new C1313c(poiIndoorOption));
    }

    /* renamed from: a */
    public boolean mo2698a(PoiNearbySearchOption poiNearbySearchOption) {
        this.a = new C1322l(poiNearbySearchOption.mPageNum, poiNearbySearchOption.mPageCapacity);
        this.a.m4537a(new C1317g(this));
        this.a.m4536a(SearchType.POI_NEAR_BY_SEARCH);
        return m4533a(new C1323m(poiNearbySearchOption));
    }
}
