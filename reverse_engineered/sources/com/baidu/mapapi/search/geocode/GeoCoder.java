package com.baidu.mapapi.search.geocode;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.search.core.C1146i;
import com.baidu.platform.core.p047b.C1299f;
import com.baidu.platform.core.p047b.C1300a;

public class GeoCoder extends C1146i {
    /* renamed from: a */
    private C1299f f3370a = new C1300a();
    /* renamed from: b */
    private boolean f3371b;

    private GeoCoder() {
    }

    public static GeoCoder newInstance() {
        BMapManager.init();
        return new GeoCoder();
    }

    public void destroy() {
        if (!this.f3371b) {
            this.f3371b = true;
            this.f3370a.mo2684a();
            BMapManager.destroy();
        }
    }

    public boolean geocode(GeoCodeOption geoCodeOption) {
        if (this.f3370a == null) {
            throw new IllegalStateException("GeoCoder is null, please call newInstance() first.");
        } else if (geoCodeOption != null && geoCodeOption.mAddress != null && geoCodeOption.mCity != null) {
            return this.f3370a.mo2689a(geoCodeOption);
        } else {
            throw new IllegalArgumentException("option or address or city can not be null");
        }
    }

    public boolean reverseGeoCode(ReverseGeoCodeOption reverseGeoCodeOption) {
        if (this.f3370a == null) {
            throw new IllegalStateException("GeoCoder is null, please call newInstance() first.");
        } else if (reverseGeoCodeOption != null && reverseGeoCodeOption.mLocation != null) {
            return this.f3370a.mo2690a(reverseGeoCodeOption);
        } else {
            throw new IllegalArgumentException("option or mLocation can not be null");
        }
    }

    public void setOnGetGeoCodeResultListener(OnGetGeoCoderResultListener onGetGeoCoderResultListener) {
        if (this.f3370a == null) {
            throw new IllegalStateException("GeoCoder is null, please call newInstance() first.");
        } else if (onGetGeoCoderResultListener == null) {
            throw new IllegalArgumentException("listener can not be null");
        } else {
            this.f3370a.mo2688a(onGetGeoCoderResultListener);
        }
    }
}
