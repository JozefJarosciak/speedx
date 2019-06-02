package com.alipay.sdk.util;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.alipay.android.app.IAlixPay.Stub;

/* renamed from: com.alipay.sdk.util.f */
final class C0878f implements ServiceConnection {
    /* renamed from: a */
    final /* synthetic */ C0877e f2217a;

    C0878f(C0877e c0877e) {
        this.f2217a = c0877e;
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.f2217a.f2211c = null;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        synchronized (this.f2217a.f2212d) {
            this.f2217a.f2211c = Stub.asInterface(iBinder);
            this.f2217a.f2212d.notify();
        }
    }
}
