package com.alipay.sdk.app;

public class H5AuthActivity extends H5PayActivity {
    /* renamed from: a */
    public final void mo2423a() {
        Object obj = AuthTask.f1900a;
        synchronized (obj) {
            try {
                obj.notify();
            } catch (Exception e) {
            }
        }
    }
}
