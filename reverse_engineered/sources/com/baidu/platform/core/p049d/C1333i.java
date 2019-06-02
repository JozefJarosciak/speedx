package com.baidu.platform.core.p049d;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.route.MassTransitRoutePlanOption;
import com.baidu.platform.base.C1213f;
import com.baidu.platform.comapi.util.CoordTrans;
import com.baidu.platform.domain.C1361b;

/* renamed from: com.baidu.platform.core.d.i */
public class C1333i extends C1213f {
    public C1333i(MassTransitRoutePlanOption massTransitRoutePlanOption) {
        m5120a(massTransitRoutePlanOption);
    }

    /* renamed from: a */
    private void m5120a(MassTransitRoutePlanOption massTransitRoutePlanOption) {
        LatLng location = massTransitRoutePlanOption.mFrom.getLocation();
        if (location != null) {
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                location = CoordTrans.gcjToBaidu(location);
            }
            this.a.m5234a("origin", location.latitude + "," + location.longitude);
        } else {
            this.a.m5234a("origin", massTransitRoutePlanOption.mFrom.getName());
        }
        if (massTransitRoutePlanOption.mFrom.getCity() != null) {
            this.a.m5234a("origin_region", massTransitRoutePlanOption.mFrom.getCity());
        }
        location = massTransitRoutePlanOption.mTo.getLocation();
        if (location != null) {
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                location = CoordTrans.gcjToBaidu(location);
            }
            this.a.m5234a("destination", location.latitude + "," + location.longitude);
        } else {
            this.a.m5234a("destination", massTransitRoutePlanOption.mTo.getName());
        }
        if (massTransitRoutePlanOption.mTo.getCity() != null) {
            this.a.m5234a("destination_region", massTransitRoutePlanOption.mTo.getCity());
        }
        this.a.m5234a("tactics_incity", massTransitRoutePlanOption.mTacticsIncity.getInt() + "");
        this.a.m5234a("tactics_intercity", massTransitRoutePlanOption.mTacticsIntercity.getInt() + "");
        this.a.m5234a("trans_type_intercity", massTransitRoutePlanOption.mTransTypeIntercity.getInt() + "");
        this.a.m5234a("page_index", massTransitRoutePlanOption.mPageIndex + "");
        this.a.m5234a("page_size", massTransitRoutePlanOption.mPageSize + "");
        this.a.m5234a("coord_type", massTransitRoutePlanOption.mCoordType);
        this.a.m5234a("output", "json");
        this.a.m5234a("from", "android_map_sdk");
    }

    /* renamed from: a */
    public String mo2682a(C1361b c1361b) {
        return c1361b.mo2718g();
    }
}
