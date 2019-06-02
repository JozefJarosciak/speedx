package com.baidu.platform.core.p049d;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.BikingRoutePlanOption;
import com.baidu.platform.base.C1213f;
import com.baidu.platform.comapi.util.CoordTrans;
import com.baidu.platform.domain.C1361b;

/* renamed from: com.baidu.platform.core.d.b */
public class C1325b extends C1213f {
    public C1325b(BikingRoutePlanOption bikingRoutePlanOption) {
        m5077a(bikingRoutePlanOption);
    }

    /* renamed from: a */
    private void m5077a(BikingRoutePlanOption bikingRoutePlanOption) {
        this.a.m5234a("mode", "riding");
        LatLng location = bikingRoutePlanOption.mFrom.getLocation();
        if (location != null) {
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                location = CoordTrans.gcjToBaidu(location);
            }
            this.a.m5234a("origin", location.latitude + "," + location.longitude);
        } else {
            this.a.m5234a("origin", bikingRoutePlanOption.mFrom.getName());
        }
        location = bikingRoutePlanOption.mTo.getLocation();
        if (location != null) {
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                location = CoordTrans.gcjToBaidu(location);
            }
            this.a.m5234a("destination", location.latitude + "," + location.longitude);
        } else {
            this.a.m5234a("destination", bikingRoutePlanOption.mTo.getName());
        }
        this.a.m5234a("origin_region", bikingRoutePlanOption.mFrom.getCity());
        this.a.m5234a("destination_region", bikingRoutePlanOption.mTo.getCity());
        this.a.m5234a("output", "json");
        this.a.m5234a("from", "android_map_sdk");
    }

    /* renamed from: a */
    public String mo2682a(C1361b c1361b) {
        return c1361b.mo2721j();
    }
}
