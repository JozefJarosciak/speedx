package com.baidu.platform.comapi;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.VersionInfo;
import com.baidu.platform.comapi.util.C1281e;
import com.baidu.platform.comapi.util.PermissionCheck;
import com.baidu.platform.comapi.util.PermissionCheck.C1217c;
import com.baidu.platform.comapi.util.PermissionCheck.C1274b;
import com.baidu.platform.comapi.util.SysUpdateObservable;
import com.baidu.platform.comjni.tools.C1290a;

/* renamed from: com.baidu.platform.comapi.a */
public class C1218a implements C1217c {
    /* renamed from: a */
    private static final String f3602a = C1218a.class.getSimpleName();
    /* renamed from: f */
    private static C1218a f3603f;
    /* renamed from: g */
    private static int f3604g = -100;
    /* renamed from: b */
    private Context f3605b;
    /* renamed from: c */
    private Handler f3606c;
    /* renamed from: d */
    private C1222d f3607d;
    /* renamed from: e */
    private int f3608e;

    static {
        NativeLoader.getInstance().loadLibrary(VersionInfo.getKitName());
        C1290a.m4967b();
    }

    private C1218a() {
    }

    /* renamed from: a */
    public static C1218a m4559a() {
        if (f3603f == null) {
            f3603f = new C1218a();
        }
        return f3603f;
    }

    /* renamed from: f */
    private void m4560f() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        intentFilter.addAction("android.net.wifi.WIFI_STATE_CHANGED");
        if (this.f3605b != null && this.f3607d != null) {
            this.f3605b.registerReceiver(this.f3607d, intentFilter);
        }
    }

    /* renamed from: g */
    private void m4561g() {
        if (this.f3607d != null && this.f3605b != null) {
            this.f3605b.unregisterReceiver(this.f3607d);
        }
    }

    /* renamed from: a */
    public void m4562a(Context context) {
        this.f3605b = context;
    }

    /* renamed from: a */
    public void m4563a(Message message) {
        if (message.what == 2012) {
            Intent intent;
            if (message.arg1 == 0) {
                intent = new Intent(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_OK);
            } else {
                intent = new Intent(SDKInitializer.SDK_BROADTCAST_ACTION_STRING_PERMISSION_CHECK_ERROR);
                intent.putExtra(SDKInitializer.SDK_BROADTCAST_INTENT_EXTRA_INFO_KEY_ERROR_CODE, message.arg1);
            }
            this.f3605b.sendBroadcast(intent);
            return;
        }
        if (message.arg2 == 3) {
            this.f3605b.sendBroadcast(new Intent(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR));
        }
        if (message.arg2 == 2 || message.arg2 == 404 || message.arg2 == 5 || message.arg2 == 8) {
            this.f3605b.sendBroadcast(new Intent(SDKInitializer.SDK_BROADCAST_ACTION_STRING_NETWORK_ERROR));
        }
    }

    /* renamed from: a */
    public void mo2669a(C1274b c1274b) {
        if (c1274b != null) {
            if (c1274b.f3855a == 0) {
                C1281e.f3919z = c1274b.f3859e;
                C1281e.m4848a(c1274b.f3856b, c1274b.f3857c);
            } else {
                Log.e("baidumapsdk", "Authentication Error\n" + c1274b.toString());
            }
            if (this.f3606c != null && c1274b.f3855a != f3604g) {
                f3604g = c1274b.f3855a;
                Message.obtain(this.f3606c, 2012, c1274b.f3855a, c1274b.f3855a, null).sendToTarget();
            }
        }
    }

    /* renamed from: b */
    public void m4565b() {
        if (this.f3608e == 0) {
            if (this.f3605b == null) {
                throw new IllegalStateException("you have not supplyed the global app context info from SDKInitializer.initialize(Context) function.");
            }
            this.f3607d = new C1222d();
            m4560f();
            SysUpdateObservable.getInstance().updateNetworkInfo(this.f3605b);
        }
        this.f3608e++;
    }

    /* renamed from: c */
    public boolean m4566c() {
        if (this.f3605b == null) {
            throw new IllegalStateException("you have not supplyed the global app context info from SDKInitializer.initialize(Context) function.");
        }
        this.f3606c = new C1219b(this);
        C1281e.m4851b(this.f3605b);
        C1281e.m4858f();
        PermissionCheck.init(this.f3605b);
        PermissionCheck.setPermissionCheckResultListener(this);
        PermissionCheck.permissionCheck();
        return true;
    }

    /* renamed from: d */
    public void m4567d() {
        this.f3608e--;
        if (this.f3608e == 0) {
            m4561g();
            C1281e.m4846a();
        }
    }

    /* renamed from: e */
    public Context m4568e() {
        if (this.f3605b != null) {
            return this.f3605b;
        }
        throw new IllegalStateException("you have not supplyed the global app context info from SDKInitializer.initialize(Context) function.");
    }
}
