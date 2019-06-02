package com.baidu.platform.core.p049d;

import ch.qos.logback.core.pattern.color.ANSIConstants;
import com.alipay.sdk.cons.C0844a;
import com.baidu.mapapi.search.route.DrivingRoutePlanOption;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.platform.base.C1213f;
import com.baidu.platform.domain.C1361b;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.util.List;

/* renamed from: com.baidu.platform.core.d.d */
public class C1328d extends C1213f {
    C1328d(DrivingRoutePlanOption drivingRoutePlanOption) {
        m5095a(drivingRoutePlanOption);
    }

    /* renamed from: a */
    private void m5095a(DrivingRoutePlanOption drivingRoutePlanOption) {
        this.a.m5234a("qt", "cars");
        this.a.m5234a("sy", drivingRoutePlanOption.mPolicy.getInt() + "");
        this.a.m5234a("ie", "utf-8");
        this.a.m5234a("lrn", "20");
        this.a.m5234a(MapboxEvent.ATTRIBUTE_VERSION, "6");
        this.a.m5234a("extinfo", ANSIConstants.GREEN_FG);
        this.a.m5234a("mrs", C0844a.f2048d);
        this.a.m5234a("rp_format", "json");
        this.a.m5234a("rp_filter", "mobile");
        this.a.m5234a("sn", m4541a(drivingRoutePlanOption.mFrom));
        this.a.m5234a("en", m4541a(drivingRoutePlanOption.mTo));
        if (drivingRoutePlanOption.mCityName != null) {
            this.a.m5234a("c", drivingRoutePlanOption.mCityName);
        }
        if (drivingRoutePlanOption.mFrom != null) {
            this.a.m5234a("sc", drivingRoutePlanOption.mFrom.getCity());
        }
        if (drivingRoutePlanOption.mTo != null) {
            this.a.m5234a("ec", drivingRoutePlanOption.mTo.getCity());
        }
        List list = drivingRoutePlanOption.mWayPoints;
        String str = new String();
        String str2 = new String();
        if (list != null) {
            String str3 = str;
            str = str2;
            for (int i = 0; i < list.size(); i++) {
                PlanNode planNode = (PlanNode) list.get(i);
                if (planNode != null) {
                    str3 = str3 + m4541a(planNode);
                    str = str + planNode.getCity();
                    if (i != list.size() - 1) {
                        str3 = str3 + "|";
                        str = str + "|";
                    }
                }
            }
            this.a.m5234a("wp", str3);
            this.a.m5234a("wpc", str);
        }
    }

    /* renamed from: a */
    public String mo2682a(C1361b c1361b) {
        return c1361b.mo2720i();
    }
}
