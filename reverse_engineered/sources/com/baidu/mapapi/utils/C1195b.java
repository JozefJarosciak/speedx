package com.baidu.mapapi.utils;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.baidu.mapframework.open.aidl.C1193b.C1194a;
import com.baidu.mapframework.open.aidl.IComOpenClient.C1203a;

/* renamed from: com.baidu.mapapi.utils.b */
final class C1195b extends C1194a {
    /* renamed from: a */
    final /* synthetic */ int f3534a;

    C1195b(int i) {
        this.f3534a = i;
    }

    /* renamed from: a */
    public void mo2661a(IBinder iBinder) throws RemoteException {
        Log.d(C1192a.f3514c, "onClientReady");
        if (C1192a.f3516e != null) {
            C1192a.f3516e = null;
        }
        C1192a.f3516e = C1203a.m4523a(iBinder);
        C1192a.m4473a(this.f3534a);
        C1192a.f3531t = true;
    }
}
