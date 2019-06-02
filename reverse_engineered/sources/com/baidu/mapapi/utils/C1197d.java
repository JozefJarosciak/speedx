package com.baidu.mapapi.utils;

import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.baidu.mapframework.open.aidl.C1193b.C1194a;
import com.baidu.mapframework.open.aidl.IComOpenClient.C1203a;

/* renamed from: com.baidu.mapapi.utils.d */
class C1197d extends C1194a {
    /* renamed from: a */
    final /* synthetic */ C1196c f3535a;

    C1197d(C1196c c1196c) {
        this.f3535a = c1196c;
    }

    /* renamed from: a */
    public void mo2661a(IBinder iBinder) throws RemoteException {
        Log.d(C1192a.f3514c, "onClientReady");
        if (C1192a.f3516e != null) {
            C1192a.f3516e = null;
        }
        C1192a.f3516e = C1203a.m4523a(iBinder);
        if (!C1192a.f3531t) {
            C1192a.m4473a(C1192a.f3512a);
        }
        C1192a.f3531t = true;
    }
}
