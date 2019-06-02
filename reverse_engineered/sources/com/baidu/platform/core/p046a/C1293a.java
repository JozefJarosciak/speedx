package com.baidu.platform.core.p046a;

import com.alipay.sdk.cons.C0844a;
import com.baidu.mapapi.search.district.DistrictSearchOption;
import com.baidu.platform.base.C1213f;
import com.baidu.platform.domain.C1361b;

/* renamed from: com.baidu.platform.core.a.a */
public class C1293a extends C1213f {
    C1293a(DistrictSearchOption districtSearchOption) {
        m4972a(districtSearchOption);
    }

    /* renamed from: a */
    private void m4972a(DistrictSearchOption districtSearchOption) {
        if (districtSearchOption != null) {
            this.a.m5234a("qt", "con");
            this.a.m5234a("rp_format", "json");
            this.a.m5234a("rp_filter", "mobile");
            this.a.m5234a("area_res", "true");
            this.a.m5234a("addr_identify", C0844a.f2048d);
            this.a.m5234a("ie", "utf-8");
            this.a.m5234a("pn", "0");
            this.a.m5234a("rn", "10");
            this.a.m5234a("c", districtSearchOption.mCityName);
            if (districtSearchOption.mDistrictName == null || districtSearchOption.mDistrictName.equals("")) {
                this.a.m5234a("wd", districtSearchOption.mCityName);
            } else {
                this.a.m5234a("wd", districtSearchOption.mDistrictName);
            }
        }
    }

    /* renamed from: a */
    public String mo2682a(C1361b c1361b) {
        return c1361b.mo2725n();
    }
}
