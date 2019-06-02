package com.baidu.platform.core.p049d;

import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.platform.base.C1213f;
import com.baidu.platform.domain.C1361b;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;

/* renamed from: com.baidu.platform.core.d.u */
public class C1345u extends C1213f {
    public C1345u(WalkingRoutePlanOption walkingRoutePlanOption) {
        m5156a(walkingRoutePlanOption);
    }

    /* renamed from: a */
    private void m5156a(WalkingRoutePlanOption walkingRoutePlanOption) {
        this.a.m5234a("qt", "walk2");
        this.a.m5234a("sn", m4541a(walkingRoutePlanOption.mFrom));
        this.a.m5234a("en", m4541a(walkingRoutePlanOption.mTo));
        if (walkingRoutePlanOption.mFrom != null) {
            this.a.m5234a("sc", walkingRoutePlanOption.mFrom.getCity());
        }
        if (walkingRoutePlanOption.mTo != null) {
            this.a.m5234a("ec", walkingRoutePlanOption.mTo.getCity());
        }
        this.a.m5234a("ie", "utf-8");
        this.a.m5234a("lrn", "20");
        this.a.m5234a(MapboxEvent.ATTRIBUTE_VERSION, "3");
        this.a.m5234a("rp_format", "json");
        this.a.m5234a("rp_filter", "mobile");
    }

    /* renamed from: a */
    public String mo2682a(C1361b c1361b) {
        return c1361b.mo2722k();
    }
}
