package com.baidu.platform.core.p049d;

import com.baidu.mapapi.search.route.TransitRoutePlanOption;
import com.baidu.platform.base.C1213f;
import com.baidu.platform.domain.C1361b;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;

/* renamed from: com.baidu.platform.core.d.s */
public class C1343s extends C1213f {
    public C1343s(TransitRoutePlanOption transitRoutePlanOption) {
        m5148a(transitRoutePlanOption);
    }

    /* renamed from: a */
    private void m5148a(TransitRoutePlanOption transitRoutePlanOption) {
        this.a.m5234a("qt", "bus");
        this.a.m5234a("sy", transitRoutePlanOption.mPolicy.getInt() + "");
        this.a.m5234a("ie", "utf-8");
        this.a.m5234a("lrn", "20");
        this.a.m5234a(MapboxEvent.ATTRIBUTE_VERSION, "3");
        this.a.m5234a("rp_format", "json");
        this.a.m5234a("rp_filter", "mobile");
        this.a.m5234a("ic_info", "2");
        this.a.m5234a("sn", m4541a(transitRoutePlanOption.mFrom));
        this.a.m5234a("en", m4541a(transitRoutePlanOption.mTo));
        if (transitRoutePlanOption.mCityName != null) {
            this.a.m5234a("c", transitRoutePlanOption.mCityName);
        }
    }

    /* renamed from: a */
    public String mo2682a(C1361b c1361b) {
        return c1361b.mo2719h();
    }
}
