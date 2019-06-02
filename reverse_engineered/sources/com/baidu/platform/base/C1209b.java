package com.baidu.platform.base;

import com.baidu.mapapi.http.HttpClient.HttpStateError;
import com.baidu.mapapi.http.HttpClient.ProtoResultCallback;

/* renamed from: com.baidu.platform.base.b */
class C1209b extends ProtoResultCallback {
    /* renamed from: a */
    final /* synthetic */ C1208a f3581a;

    C1209b(C1208a c1208a) {
        this.f3581a = c1208a;
    }

    public void onFailed(HttpStateError httpStateError) {
    }

    public void onSuccess(String str) {
        this.f3581a.f3580d = str;
        if (!this.f3581a.mo2684a()) {
            this.f3581a.f3579c.post(new C1210c(this));
        } else if (this.f3581a.f3577a != null) {
            this.f3581a.f3577a.mo2683a(this.f3581a.f3580d);
        }
    }
}
