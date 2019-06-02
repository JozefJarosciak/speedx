package com.baidu.platform.core.p050e;

import com.alipay.sdk.sys.C0869a;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.inner.Point;
import com.baidu.mapapi.search.share.RouteShareURLOption;
import com.baidu.platform.base.C1213f;
import com.baidu.platform.comjni.util.AppMD5;
import com.baidu.platform.domain.C1361b;
import com.baidu.platform.util.C1364a;

/* renamed from: com.baidu.platform.core.e.e */
public class C1350e extends C1213f {
    public C1350e(RouteShareURLOption routeShareURLOption) {
        m5169a(routeShareURLOption);
    }

    /* renamed from: a */
    private int m5168a(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    /* renamed from: a */
    private void m5169a(RouteShareURLOption routeShareURLOption) {
        C1364a c1364a = new C1364a();
        Point ll2point = CoordUtil.ll2point(routeShareURLOption.mFrom.getLocation());
        Point ll2point2 = CoordUtil.ll2point(routeShareURLOption.mTo.getLocation());
        String str = ll2point != null ? "1$$$$" + ll2point.f3293x + "," + ll2point.f3294y + "$$" : "2$$$$$$";
        String name = routeShareURLOption.mFrom.getName();
        if (name == null || name.equals("")) {
            name = "起点";
        }
        String str2 = str + name + "$$0$$$$";
        str = ll2point2 != null ? "1$$$$" + ll2point2.f3293x + "," + ll2point2.f3294y + "$$" : "2$$$$$$";
        String name2 = routeShareURLOption.mTo.getName();
        if (name2 == null || name2.equals("")) {
            name2 = "终点";
        }
        String str3 = str + name2 + "$$0$$$$";
        String str4 = "";
        str = "";
        switch (routeShareURLOption.mMode.ordinal()) {
            case 0:
                str = "&sharecallbackflag=carRoute";
                str4 = "nav";
                c1364a.m5234a("sc", m5168a(routeShareURLOption.mFrom.getCity()) + "");
                c1364a.m5234a("ec", m5168a(routeShareURLOption.mTo.getCity()) + "");
                break;
            case 1:
                str = "&sharecallbackflag=footRoute";
                str4 = "walk";
                c1364a.m5234a("sc", m5168a(routeShareURLOption.mFrom.getCity()) + "");
                c1364a.m5234a("ec", m5168a(routeShareURLOption.mTo.getCity()) + "");
                break;
            case 2:
                str = "&sharecallbackflag=cycleRoute";
                str4 = "cycle";
                c1364a.m5234a("sc", m5168a(routeShareURLOption.mFrom.getCity()) + "");
                c1364a.m5234a("ec", m5168a(routeShareURLOption.mTo.getCity()) + "");
                break;
            case 3:
                str = "&i=" + routeShareURLOption.mPn + ",1,1&sharecallbackflag=busRoute";
                c1364a.m5234a("c", routeShareURLOption.mCityCode + "");
                str4 = "bt";
                break;
        }
        c1364a.m5234a("sn", str2);
        c1364a.m5234a("en", str3);
        this.a.m5234a("url", "http://map.baidu.com/?newmap=1&s=" + str4 + (AppMD5.encodeUrlParamsValue(C0869a.f2161b + c1364a.m5235a() + ("&start=" + name + "&end=" + name2)) + str));
        this.a.m5234a("from", "android_map_sdk");
    }

    /* renamed from: a */
    public String mo2682a(C1361b c1361b) {
        return c1361b.mo2729r();
    }
}
