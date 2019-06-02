package com.baidu.platform.core.p046a;

import com.baidu.mapapi.search.district.DistrictResult;
import com.baidu.mapapi.search.district.DistrictSearchOption;
import com.baidu.mapapi.search.district.OnGetDistricSearchResultListener;
import com.baidu.platform.base.C1208a;
import com.baidu.platform.base.SearchType;

/* renamed from: com.baidu.platform.core.a.d */
public class C1297d extends C1208a implements C1296f {
    /* renamed from: b */
    boolean f3946b = true;
    /* renamed from: c */
    DistrictResult f3947c = null;
    /* renamed from: d */
    private OnGetDistricSearchResultListener f3948d;

    /* renamed from: a */
    private boolean m4986a(String str) {
        ((C1294b) this.a).m4978b();
        return m4533a(new C1295c(str));
    }

    /* renamed from: a */
    public void mo2684a() {
        this.f3948d = null;
    }

    /* renamed from: a */
    public void mo2685a(OnGetDistricSearchResultListener onGetDistricSearchResultListener) {
        this.f3948d = onGetDistricSearchResultListener;
    }

    /* renamed from: a */
    public boolean mo2686a(DistrictSearchOption districtSearchOption) {
        this.a = new C1294b();
        this.a.m4536a(SearchType.DISTRICT_SEARCH);
        this.a.m4537a(new C1298e(this));
        return m4533a(new C1293a(districtSearchOption));
    }
}
