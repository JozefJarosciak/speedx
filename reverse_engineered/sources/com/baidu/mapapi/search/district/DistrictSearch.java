package com.baidu.mapapi.search.district;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.search.core.C1146i;
import com.baidu.platform.core.p046a.C1296f;
import com.baidu.platform.core.p046a.C1297d;

public class DistrictSearch extends C1146i {
    /* renamed from: a */
    private C1296f f3366a;
    /* renamed from: b */
    private boolean f3367b;

    DistrictSearch() {
        this.f3366a = null;
        this.f3367b = false;
        this.f3366a = new C1297d();
    }

    public static DistrictSearch newInstance() {
        BMapManager.init();
        return new DistrictSearch();
    }

    public void destroy() {
        if (!this.f3367b) {
            this.f3367b = true;
            this.f3366a.mo2684a();
            BMapManager.destroy();
        }
    }

    public boolean searchDistrict(DistrictSearchOption districtSearchOption) {
        if (this.f3366a == null) {
            throw new IllegalStateException("searcher is null, please call newInstance first.");
        } else if (districtSearchOption != null && districtSearchOption.mCityName != null && !districtSearchOption.mCityName.equals("")) {
            return this.f3366a.mo2686a(districtSearchOption);
        } else {
            throw new IllegalArgumentException("option or city name can not be null or empty.");
        }
    }

    public void setOnDistrictSearchListener(OnGetDistricSearchResultListener onGetDistricSearchResultListener) {
        if (this.f3366a == null) {
            throw new IllegalStateException("searcher is null, please call newInstance first.");
        } else if (onGetDistricSearchResultListener == null) {
            throw new IllegalArgumentException("listener can not be null");
        } else {
            this.f3366a.mo2685a(onGetDistricSearchResultListener);
        }
    }
}
