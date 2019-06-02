package com.baidu.mapapi.search.poi;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.search.core.C1146i;
import com.baidu.platform.core.p048c.C1311a;
import com.baidu.platform.core.p048c.C1316f;

public class PoiSearch extends C1146i {
    /* renamed from: a */
    private C1311a f3390a = new C1316f();
    /* renamed from: b */
    private boolean f3391b = false;

    PoiSearch() {
    }

    public static PoiSearch newInstance() {
        BMapManager.init();
        return new PoiSearch();
    }

    public void destroy() {
        if (!this.f3391b) {
            this.f3391b = true;
            this.f3390a.mo2684a();
            BMapManager.destroy();
        }
    }

    public boolean searchInBound(PoiBoundSearchOption poiBoundSearchOption) {
        if (this.f3390a == null) {
            throw new IllegalStateException("searcher is null, please call newInstance first.");
        } else if (poiBoundSearchOption != null && poiBoundSearchOption.mBound != null && poiBoundSearchOption.mKeyword != null) {
            return this.f3390a.mo2694a(poiBoundSearchOption);
        } else {
            throw new IllegalArgumentException("option or bound or keyworld can not be null");
        }
    }

    public boolean searchInCity(PoiCitySearchOption poiCitySearchOption) {
        if (this.f3390a == null) {
            throw new IllegalStateException("searcher is null, please call newInstance first.");
        } else if (poiCitySearchOption != null && poiCitySearchOption.mCity != null && poiCitySearchOption.mKeyword != null) {
            return this.f3390a.mo2695a(poiCitySearchOption);
        } else {
            throw new IllegalArgumentException("option or city or keyworld can not be null");
        }
    }

    public boolean searchNearby(PoiNearbySearchOption poiNearbySearchOption) {
        if (this.f3390a == null) {
            throw new IllegalStateException("searcher is null, please call newInstance first.");
        } else if (poiNearbySearchOption != null && poiNearbySearchOption.mLocation != null && poiNearbySearchOption.mKeyword != null) {
            return poiNearbySearchOption.mRadius <= 0 ? false : this.f3390a.mo2698a(poiNearbySearchOption);
        } else {
            throw new IllegalArgumentException("option or location or keyworld can not be null");
        }
    }

    public boolean searchPoiDetail(PoiDetailSearchOption poiDetailSearchOption) {
        if (this.f3390a == null) {
            throw new IllegalStateException("searcher is null, please call newInstance first.");
        } else if (poiDetailSearchOption != null && poiDetailSearchOption.mUid != null) {
            return this.f3390a.mo2696a(poiDetailSearchOption);
        } else {
            throw new IllegalArgumentException("option or uid can not be null");
        }
    }

    public boolean searchPoiIndoor(PoiIndoorOption poiIndoorOption) {
        if (this.f3390a == null) {
            throw new IllegalStateException("searcher is null, please call newInstance first.");
        } else if (poiIndoorOption != null && poiIndoorOption.bid != null && poiIndoorOption.wd != null) {
            return this.f3390a.mo2697a(poiIndoorOption);
        } else {
            throw new IllegalArgumentException("option or indoor bid or keyword can not be null");
        }
    }

    public void setOnGetPoiSearchResultListener(OnGetPoiSearchResultListener onGetPoiSearchResultListener) {
        if (this.f3390a == null) {
            throw new IllegalStateException("searcher is null, please call newInstance first.");
        } else if (onGetPoiSearchResultListener == null) {
            throw new IllegalArgumentException("listener can not be null");
        } else {
            this.f3390a.mo2693a(onGetPoiSearchResultListener);
        }
    }
}
