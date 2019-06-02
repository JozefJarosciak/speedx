package com.baidu.platform.core.p049d;

import com.alipay.sdk.cons.C0844a;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.search.route.IndoorRoutePlanOption;
import com.baidu.platform.base.C1213f;
import com.baidu.platform.domain.C1361b;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;

/* renamed from: com.baidu.platform.core.d.g */
public class C1331g extends C1213f {
    C1331g(IndoorRoutePlanOption indoorRoutePlanOption) {
        m5108a(indoorRoutePlanOption);
    }

    /* renamed from: a */
    private void m5108a(IndoorRoutePlanOption indoorRoutePlanOption) {
        this.a.m5234a("qt", "indoornavi");
        this.a.m5234a("rp_format", "json");
        this.a.m5234a(MapboxEvent.ATTRIBUTE_VERSION, C0844a.f2048d);
        String str = "";
        if (CoordUtil.ll2mc(indoorRoutePlanOption.mFrom.getLocation()) != null) {
            this.a.m5234a("sn", (String.format("%f,%f", new Object[]{Double.valueOf(CoordUtil.ll2mc(indoorRoutePlanOption.mFrom.getLocation()).getLongitudeE6()), Double.valueOf(CoordUtil.ll2mc(indoorRoutePlanOption.mFrom.getLocation()).getLatitudeE6())}) + "|" + indoorRoutePlanOption.mFrom.getFloor()).replaceAll(" ", ""));
        }
        str = "";
        if (CoordUtil.ll2mc(indoorRoutePlanOption.mTo.getLocation()) != null) {
            this.a.m5234a("en", (String.format("%f,%f", new Object[]{Double.valueOf(CoordUtil.ll2mc(indoorRoutePlanOption.mTo.getLocation()).getLongitudeE6()), Double.valueOf(CoordUtil.ll2mc(indoorRoutePlanOption.mTo.getLocation()).getLatitudeE6())}) + "|" + indoorRoutePlanOption.mTo.getFloor()).replaceAll(" ", ""));
        }
    }

    /* renamed from: a */
    public String mo2682a(C1361b c1361b) {
        return c1361b.mo2723l();
    }
}
