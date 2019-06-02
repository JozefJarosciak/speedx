package com.baidu.mapapi.search.busline;

import com.baidu.mapapi.BMapManager;
import com.baidu.mapapi.search.core.C1146i;
import com.baidu.platform.core.busline.C1309c;
import com.baidu.platform.core.busline.IBusLineSearch;

public class BusLineSearch extends C1146i {
    /* renamed from: a */
    IBusLineSearch f3320a = new C1309c();
    /* renamed from: b */
    private boolean f3321b = false;

    BusLineSearch() {
    }

    public static BusLineSearch newInstance() {
        BMapManager.init();
        return new BusLineSearch();
    }

    public void destroy() {
        if (!this.f3321b) {
            this.f3321b = true;
            this.f3320a.mo2684a();
            BMapManager.destroy();
        }
    }

    public boolean searchBusLine(BusLineSearchOption busLineSearchOption) {
        if (this.f3320a == null) {
            throw new IllegalStateException("searcher is null, please call newInstance first.");
        } else if (busLineSearchOption != null && busLineSearchOption.mCity != null && busLineSearchOption.mUid != null) {
            return this.f3320a.mo2692a(busLineSearchOption);
        } else {
            throw new IllegalArgumentException("option or city or uid can not be null");
        }
    }

    public void setOnGetBusLineSearchResultListener(OnGetBusLineSearchResultListener onGetBusLineSearchResultListener) {
        if (this.f3320a == null) {
            throw new IllegalStateException("searcher is null, please call newInstance first.");
        } else if (onGetBusLineSearchResultListener == null) {
            throw new IllegalArgumentException("listener can not be null");
        } else {
            this.f3320a.mo2691a(onGetBusLineSearchResultListener);
        }
    }
}
