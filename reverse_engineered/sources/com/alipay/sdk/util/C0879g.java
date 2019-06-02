package com.alipay.sdk.util;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import com.alipay.android.app.IRemoteServiceCallback.Stub;

/* renamed from: com.alipay.sdk.util.g */
final class C0879g extends Stub {
    /* renamed from: a */
    final /* synthetic */ C0877e f2218a;

    C0879g(C0877e c0877e) {
        this.f2218a = c0877e;
    }

    public final boolean isHideLoadingScreen() throws RemoteException {
        return false;
    }

    public final void payEnd(boolean z, String str) throws RemoteException {
    }

    public final void startActivity(String str, String str2, int i, Bundle bundle) throws RemoteException {
        Intent intent = new Intent("android.intent.action.MAIN", null);
        if (bundle == null) {
            bundle = new Bundle();
        }
        try {
            bundle.putInt("CallingPid", i);
            intent.putExtras(bundle);
        } catch (Exception e) {
        }
        intent.setClassName(str, str2);
        if (this.f2218a.f2210a != null) {
            this.f2218a.f2210a.startActivity(intent);
        }
    }
}
