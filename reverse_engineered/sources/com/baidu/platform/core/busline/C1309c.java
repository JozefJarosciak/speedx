package com.baidu.platform.core.busline;

import com.baidu.mapapi.search.busline.BusLineSearchOption;
import com.baidu.mapapi.search.busline.OnGetBusLineSearchResultListener;
import com.baidu.platform.base.C1208a;
import com.baidu.platform.base.SearchType;

/* renamed from: com.baidu.platform.core.busline.c */
public class C1309c extends C1208a implements IBusLineSearch {
    /* renamed from: b */
    OnGetBusLineSearchResultListener f3953b = null;

    /* renamed from: a */
    public void mo2684a() {
        this.f3953b = null;
    }

    /* renamed from: a */
    public void mo2691a(OnGetBusLineSearchResultListener onGetBusLineSearchResultListener) {
        this.f3953b = onGetBusLineSearchResultListener;
    }

    /* renamed from: a */
    public boolean mo2692a(BusLineSearchOption busLineSearchOption) {
        this.a = new C1307a();
        this.a.m4536a(SearchType.BUS_LINE_DETAIL);
        this.a.m4537a(new C1310d(this));
        return m4533a(new C1308b(busLineSearchOption));
    }
}
