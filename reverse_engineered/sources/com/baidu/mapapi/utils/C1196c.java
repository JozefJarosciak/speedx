package com.baidu.mapapi.utils;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;
import com.baidu.mapframework.open.aidl.C1204a.C1206a;

/* renamed from: com.baidu.mapapi.utils.c */
final class C1196c implements ServiceConnection {
    C1196c() {
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (C1192a.f3533v != null) {
            C1192a.f3533v.interrupt();
        }
        Log.d(C1192a.f3514c, "onServiceConnected " + componentName);
        try {
            if (C1192a.f3515d != null) {
                C1192a.f3515d = null;
            }
            C1192a.f3515d = C1206a.m4526a(iBinder);
            C1192a.f3515d.mo2666a(new C1197d(this));
        } catch (Throwable e) {
            Log.d(C1192a.f3514c, "getComOpenClient ", e);
            if (C1192a.f3515d != null) {
                C1192a.f3515d = null;
            }
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        Log.d(C1192a.f3514c, "onServiceDisconnected " + componentName);
        if (C1192a.f3515d != null) {
            C1192a.f3515d = null;
            C1192a.f3532u = false;
        }
    }
}
