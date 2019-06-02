package com.baidu.platform.core.p050e;

import com.baidu.mapapi.http.HttpClient;
import com.baidu.mapapi.search.share.PoiDetailShareURLOption;
import com.baidu.platform.base.C1213f;
import com.baidu.platform.domain.C1361b;

/* renamed from: com.baidu.platform.core.e.c */
public class C1348c extends C1213f {
    public C1348c(PoiDetailShareURLOption poiDetailShareURLOption) {
        m5165a(poiDetailShareURLOption);
    }

    /* renamed from: a */
    private void m5165a(PoiDetailShareURLOption poiDetailShareURLOption) {
        this.a.m5234a("url", ("http://wapmap.baidu.com/s?tn=Detail&pid=" + poiDetailShareURLOption.mUid + "&smsf=3") + HttpClient.getPhoneInfo());
        m4544b(false);
        m4543a(false);
    }

    /* renamed from: a */
    public String mo2682a(C1361b c1361b) {
        return c1361b.mo2727p();
    }
}
