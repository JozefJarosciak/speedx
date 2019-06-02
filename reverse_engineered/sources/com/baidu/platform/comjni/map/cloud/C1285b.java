package com.baidu.platform.comjni.map.cloud;

import android.util.Log;
import com.baidu.mapapi.http.HttpClient.HttpStateError;
import com.baidu.mapapi.http.HttpClient.ProtoResultCallback;

/* renamed from: com.baidu.platform.comjni.map.cloud.b */
class C1285b extends ProtoResultCallback {
    /* renamed from: a */
    final /* synthetic */ C1284a f3935a;

    C1285b(C1284a c1284a) {
        this.f3935a = c1284a;
    }

    public void onFailed(HttpStateError httpStateError) {
        Log.d("baidumapsdk", "---network error: " + httpStateError);
    }

    public void onSuccess(String str) {
        this.f3935a.f3934g = str;
        if (this.f3935a.m4936a()) {
            this.f3935a.m4942f(str);
        } else {
            this.f3935a.f3933f.post(new C1286c(this, str));
        }
    }
}
