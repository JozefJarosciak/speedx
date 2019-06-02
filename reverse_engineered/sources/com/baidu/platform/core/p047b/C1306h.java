package com.baidu.platform.core.p047b;

import com.alipay.sdk.cons.C0844a;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeOption;
import com.baidu.platform.base.C1213f;
import com.baidu.platform.comapi.util.CoordTrans;
import com.baidu.platform.domain.C1361b;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;

/* renamed from: com.baidu.platform.core.b.h */
public class C1306h extends C1213f {
    public C1306h(ReverseGeoCodeOption reverseGeoCodeOption) {
        m5015a(reverseGeoCodeOption);
    }

    /* renamed from: a */
    private void m5015a(ReverseGeoCodeOption reverseGeoCodeOption) {
        if (reverseGeoCodeOption.mLocation != null) {
            LatLng latLng = new LatLng(reverseGeoCodeOption.mLocation.latitude, reverseGeoCodeOption.mLocation.longitude);
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                latLng = CoordTrans.gcjToBaidu(latLng);
            }
            this.a.m5234a(MapboxEvent.TYPE_LOCATION, latLng.latitude + "," + latLng.longitude);
        }
        this.a.m5234a("coordtype", "bd09ll");
        this.a.m5234a("pois", C0844a.f2048d);
        this.a.m5234a("output", "json");
        this.a.m5234a("from", "android_map_sdk");
    }

    /* renamed from: a */
    public String mo2682a(C1361b c1361b) {
        return c1361b.mo2716e();
    }
}
