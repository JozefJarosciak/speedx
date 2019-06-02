package com.baidu.platform.core.busline;

import com.alipay.sdk.cons.C0844a;
import com.baidu.mapapi.search.busline.BusLineSearchOption;
import com.baidu.platform.base.C1213f;
import com.baidu.platform.domain.C1361b;

/* renamed from: com.baidu.platform.core.busline.b */
public class C1308b extends C1213f {
    public C1308b(BusLineSearchOption busLineSearchOption) {
        m5022a(busLineSearchOption);
    }

    /* renamed from: a */
    private void m5022a(BusLineSearchOption busLineSearchOption) {
        this.a.m5234a("qt", "bsl");
        this.a.m5234a("rt_info", C0844a.f2048d);
        this.a.m5234a("ie", "utf-8");
        this.a.m5234a("oue", "0");
        this.a.m5234a("c", busLineSearchOption.mCity);
        this.a.m5234a("uid", busLineSearchOption.mUid);
        this.a.m5234a("t", System.currentTimeMillis() + "");
    }

    /* renamed from: a */
    public String mo2682a(C1361b c1361b) {
        return c1361b.mo2724m();
    }
}
