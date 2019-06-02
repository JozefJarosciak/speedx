package com.baidu.platform.core.p051f;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.sug.SuggestionSearchOption;
import com.baidu.platform.base.C1213f;
import com.baidu.platform.comapi.util.CoordTrans;
import com.baidu.platform.domain.C1361b;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;

/* renamed from: com.baidu.platform.core.f.e */
public class C1360e extends C1213f {
    public C1360e(SuggestionSearchOption suggestionSearchOption) {
        m5195a(suggestionSearchOption);
    }

    /* renamed from: a */
    private void m5195a(SuggestionSearchOption suggestionSearchOption) {
        this.a.m5234a("q", suggestionSearchOption.mKeyword);
        this.a.m5234a(GeocodingCriteria.TYPE_REGION, suggestionSearchOption.mCity);
        if (suggestionSearchOption.mLocation != null) {
            LatLng latLng = new LatLng(suggestionSearchOption.mLocation.latitude, suggestionSearchOption.mLocation.longitude);
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                latLng = CoordTrans.gcjToBaidu(latLng);
            }
            this.a.m5234a(MapboxEvent.TYPE_LOCATION, latLng.latitude + "," + latLng.longitude);
        }
        if (suggestionSearchOption.mCityLimit.booleanValue()) {
            this.a.m5234a("city_limit", "true");
        } else {
            this.a.m5234a("city_limit", "false");
        }
        this.a.m5234a("from", "android_map_sdk");
        this.a.m5234a("output", "json");
    }

    /* renamed from: a */
    public String mo2682a(C1361b c1361b) {
        return c1361b.mo2715d();
    }
}
