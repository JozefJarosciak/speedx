package com.baidu.mapapi.search.share;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.search.core.C1146i;
import com.baidu.mapapi.search.share.RouteShareURLOption.RouteShareMode;
import com.baidu.platform.core.p050e.C1346a;
import com.baidu.platform.core.p050e.C1352g;

public class ShareUrlSearch extends C1146i {
    /* renamed from: a */
    C1346a f3504a = new C1352g();
    /* renamed from: b */
    private boolean f3505b = false;

    ShareUrlSearch() {
    }

    /* renamed from: a */
    private boolean m4456a(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static ShareUrlSearch newInstance() {
        BMapManager.init();
        return new ShareUrlSearch();
    }

    public void destroy() {
        if (!this.f3505b) {
            this.f3505b = true;
            this.f3504a.mo2684a();
            BMapManager.destroy();
        }
    }

    public boolean requestLocationShareUrl(LocationShareURLOption locationShareURLOption) {
        if (this.f3504a == null) {
            throw new IllegalStateException("searcher has been destroyed");
        } else if (locationShareURLOption != null && locationShareURLOption.mLocation != null && locationShareURLOption.mName != null && locationShareURLOption.mSnippet != null) {
            return this.f3504a.mo2707a(locationShareURLOption);
        } else {
            throw new IllegalArgumentException("option or name or snippet  can not be null");
        }
    }

    public boolean requestPoiDetailShareUrl(PoiDetailShareURLOption poiDetailShareURLOption) {
        if (this.f3504a == null) {
            throw new IllegalStateException("searcher has been destroyed");
        } else if (poiDetailShareURLOption != null && poiDetailShareURLOption.mUid != null) {
            return this.f3504a.mo2708a(poiDetailShareURLOption);
        } else {
            throw new IllegalArgumentException("option or uid can not be null");
        }
    }

    public boolean requestRouteShareUrl(RouteShareURLOption routeShareURLOption) {
        if (this.f3504a == null) {
            throw new IllegalStateException("searcher has been destroyed");
        } else if (routeShareURLOption == null) {
            throw new IllegalArgumentException("option is null");
        } else if (routeShareURLOption.getmMode().ordinal() < 0) {
            return false;
        } else {
            if (routeShareURLOption.mFrom == null || routeShareURLOption.mTo == null) {
                throw new IllegalArgumentException("start or end point can not be null");
            }
            if (routeShareURLOption.mMode == RouteShareMode.BUS_ROUTE_SHARE_MODE) {
                if ((routeShareURLOption.mFrom.getLocation() == null || routeShareURLOption.mTo.getLocation() == null) && routeShareURLOption.mCityCode < 0) {
                    throw new IllegalArgumentException("city code can not be null if don't set start or end point");
                }
            } else if (routeShareURLOption.mFrom.getLocation() == null && !m4456a(routeShareURLOption.mFrom.getCity())) {
                throw new IllegalArgumentException("start cityCode must be set if not set start location");
            } else if (routeShareURLOption.mTo.getLocation() == null && !m4456a(routeShareURLOption.mTo.getCity())) {
                throw new IllegalArgumentException("end cityCode must be set if not set end location");
            }
            return this.f3504a.mo2709a(routeShareURLOption);
        }
    }

    public void setOnGetShareUrlResultListener(OnGetShareUrlResultListener onGetShareUrlResultListener) {
        if (this.f3504a == null) {
            throw new IllegalStateException("searcher has been destroyed");
        } else if (onGetShareUrlResultListener == null) {
            throw new IllegalArgumentException("listener can not be null");
        } else {
            this.f3504a.mo2706a(onGetShareUrlResultListener);
        }
    }
}
