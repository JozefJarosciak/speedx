package com.baidu.platform.base;

import android.os.Handler;
import android.os.Looper;
import com.baidu.mapapi.http.AsyncHttpClient;

/* renamed from: com.baidu.platform.base.a */
public abstract class C1208a {
    /* renamed from: a */
    protected C1212e f3577a;
    /* renamed from: b */
    private AsyncHttpClient f3578b = new AsyncHttpClient();
    /* renamed from: c */
    private Handler f3579c = new Handler(Looper.getMainLooper());
    /* renamed from: d */
    private String f3580d;

    /* renamed from: a */
    private boolean mo2684a() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /* renamed from: a */
    protected boolean m4533a(C1213f c1213f) {
        String a = c1213f.m4540a();
        if (a == null) {
            return false;
        }
        this.f3578b.get(a, new C1209b(this));
        return true;
    }
}
