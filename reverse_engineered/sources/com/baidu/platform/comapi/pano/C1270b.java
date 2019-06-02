package com.baidu.platform.comapi.pano;

import com.baidu.mapapi.http.HttpClient.HttpStateError;
import com.baidu.mapapi.http.HttpClient.ProtoResultCallback;
import com.baidu.platform.comapi.pano.C1269a.C1200a;

/* renamed from: com.baidu.platform.comapi.pano.b */
class C1270b extends ProtoResultCallback {
    /* renamed from: a */
    final /* synthetic */ C1200a f3850a;
    /* renamed from: b */
    final /* synthetic */ C1269a f3851b;

    C1270b(C1269a c1269a, C1200a c1200a) {
        this.f3851b = c1269a;
        this.f3850a = c1200a;
    }

    public void onFailed(HttpStateError httpStateError) {
        this.f3850a.mo2662a(httpStateError);
    }

    public void onSuccess(String str) {
        this.f3850a.mo2663a(this.f3851b.m4807a(str));
    }
}
