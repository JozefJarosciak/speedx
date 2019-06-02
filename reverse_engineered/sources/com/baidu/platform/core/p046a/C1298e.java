package com.baidu.platform.core.p046a;

import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.district.DistrictResult;
import com.baidu.platform.base.C1211d;

/* renamed from: com.baidu.platform.core.a.e */
class C1298e implements C1211d<DistrictResult> {
    /* renamed from: a */
    final /* synthetic */ C1297d f3949a;

    C1298e(C1297d c1297d) {
        this.f3949a = c1297d;
    }

    /* renamed from: a */
    public void m4990a(DistrictResult districtResult) {
        if ((districtResult == null || districtResult.error == ERRORNO.RESULT_NOT_FOUND) && this.f3949a.f3946b) {
            this.f3949a.f3946b = false;
            String cityName = districtResult.getCityName();
            if (!(cityName == null || cityName.equals(""))) {
                this.f3949a.m4986a(cityName);
                this.f3949a.f3947c = districtResult;
                return;
            }
        }
        if (!(this.f3949a.f3946b || this.f3949a.f3947c == null || districtResult.error != ERRORNO.NO_ERROR)) {
            districtResult.setCityCode(this.f3949a.f3947c.getCityCode());
            districtResult.setCenterPt(this.f3949a.f3947c.getCenterPt());
        }
        this.f3949a.f3946b = true;
        this.f3949a.f3947c = null;
        if (this.f3949a.f3948d != null) {
            this.f3949a.f3948d.onGetDistrictResult(districtResult);
        }
    }
}
