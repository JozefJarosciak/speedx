package com.baidu.platform.core.p048c;

import com.alipay.sdk.cons.C0844a;
import com.alipay.sdk.util.C0880h;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.inner.Point;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiSortType;
import com.baidu.platform.base.C1213f;
import com.baidu.platform.domain.C1361b;

/* renamed from: com.baidu.platform.core.c.m */
public class C1323m extends C1213f {
    C1323m(PoiBoundSearchOption poiBoundSearchOption) {
        m5065a(poiBoundSearchOption);
    }

    C1323m(PoiCitySearchOption poiCitySearchOption) {
        m5066a(poiCitySearchOption);
    }

    C1323m(PoiNearbySearchOption poiNearbySearchOption) {
        m5067a(poiNearbySearchOption);
    }

    /* renamed from: a */
    private void m5065a(PoiBoundSearchOption poiBoundSearchOption) {
        this.a.m5234a("qt", "bd2");
        this.a.m5234a("rp_format", "json");
        this.a.m5234a("rp_filter", "mobile");
        this.a.m5234a("ie", "utf-8");
        this.a.m5234a("pn", poiBoundSearchOption.mPageNum + "");
        this.a.m5234a("rn", poiBoundSearchOption.mPageCapacity + "");
        this.a.m5234a("wd", poiBoundSearchOption.mKeyword);
        Point ll2point = CoordUtil.ll2point(poiBoundSearchOption.mBound.northeast);
        Point ll2point2 = CoordUtil.ll2point(poiBoundSearchOption.mBound.southwest);
        this.a.m5234a("ar", "(" + ll2point.f3293x + "," + ll2point.f3294y + C0880h.f2220b + ll2point2.f3293x + "," + ll2point2.f3294y + ")");
        this.a.m5234a("l", "12");
        this.a.m5234a("b", "(" + ll2point.f3293x + "," + ll2point.f3294y + C0880h.f2220b + ll2point2.f3293x + "," + ll2point2.f3294y + ")");
    }

    /* renamed from: a */
    private void m5066a(PoiCitySearchOption poiCitySearchOption) {
        this.a.m5234a("qt", "con");
        this.a.m5234a("rp_format", "json");
        this.a.m5234a("rp_filter", "mobile");
        this.a.m5234a("ie", "utf-8");
        if (poiCitySearchOption.mIsReturnAddr) {
            this.a.m5234a("addr_identify", C0844a.f2048d);
        } else {
            this.a.m5234a("addr_identify", "0");
        }
        this.a.m5234a("c", poiCitySearchOption.mCity);
        this.a.m5234a("pn", poiCitySearchOption.mPageNum + "");
        this.a.m5234a("rn", poiCitySearchOption.mPageCapacity + "");
        this.a.m5234a("l", "12");
        this.a.m5234a("b", "(0,0;0,0)");
        this.a.m5234a("wd", poiCitySearchOption.mKeyword);
    }

    /* renamed from: a */
    private void m5067a(PoiNearbySearchOption poiNearbySearchOption) {
        this.a.m5234a("qt", "bd2");
        this.a.m5234a("rp_format", "json");
        this.a.m5234a("rp_filter", "mobile");
        this.a.m5234a("ie", "utf-8");
        this.a.m5234a("pn", poiNearbySearchOption.mPageNum + "");
        this.a.m5234a("rn", poiNearbySearchOption.mPageCapacity + "");
        this.a.m5234a("wd", poiNearbySearchOption.mKeyword);
        if (poiNearbySearchOption.sortType == PoiSortType.distance_from_near_to_far) {
            this.a.m5234a("pl_sort_type", "distance");
        }
        Point ll2point = CoordUtil.ll2point(poiNearbySearchOption.mLocation);
        Point point = new Point(ll2point.f3293x - poiNearbySearchOption.mRadius, ll2point.f3294y - poiNearbySearchOption.mRadius);
        Point point2 = new Point(ll2point.f3293x + poiNearbySearchOption.mRadius, ll2point.f3294y + poiNearbySearchOption.mRadius);
        this.a.m5234a("ar", "(" + point.f3293x + "," + point.f3294y + C0880h.f2220b + point2.f3293x + "," + point2.f3294y + ")");
        this.a.m5234a("l", "12");
        this.a.m5234a("b", "(" + point.f3293x + "," + point.f3294y + C0880h.f2220b + point2.f3293x + "," + point2.f3294y + ")");
        this.a.m5234a("distance", poiNearbySearchOption.mRadius + "");
        this.a.m5234a("center_rank", C0844a.f2048d);
        this.a.m5234a("loc", "(" + ll2point.f3293x + "," + ll2point.f3294y + ")");
    }

    /* renamed from: a */
    public String mo2682a(C1361b c1361b) {
        return c1361b.mo2712a();
    }
}
