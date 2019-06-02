package com.baidu.platform.core.p048c;

import com.baidu.mapapi.search.poi.PoiIndoorOption;
import com.baidu.platform.base.C1213f;
import com.baidu.platform.domain.C1361b;

/* renamed from: com.baidu.platform.core.c.c */
public class C1313c extends C1213f {
    public C1313c(PoiIndoorOption poiIndoorOption) {
        m5038a(poiIndoorOption);
    }

    /* renamed from: a */
    private void m5038a(PoiIndoorOption poiIndoorOption) {
        this.a.m5234a("qt", "indoor_s");
        this.a.m5234a("x", "0");
        this.a.m5234a("y", "0");
        this.a.m5234a("from", "android_map_sdk");
        String str = poiIndoorOption.bid;
        if (!(str == null || str.equals(""))) {
            this.a.m5234a("bid", str);
        }
        str = poiIndoorOption.wd;
        if (!(str == null || str.equals(""))) {
            this.a.m5234a("wd", str);
        }
        str = poiIndoorOption.floor;
        if (!(str == null || str.equals(""))) {
            this.a.m5234a("floor", str);
        }
        this.a.m5234a("current", poiIndoorOption.currentPage + "");
        this.a.m5234a("pageSize", poiIndoorOption.pageSize + "");
    }

    /* renamed from: a */
    public String mo2682a(C1361b c1361b) {
        return c1361b.mo2714c();
    }
}
