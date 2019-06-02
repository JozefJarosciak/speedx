package com.baidu.location.p041a;

import android.content.Context;
import android.util.Log;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import com.baidu.lbsapi.auth.LBSAuthManager;
import com.baidu.lbsapi.auth.LBSAuthManagerListener;
import com.baidu.location.p042d.C1090a;

/* renamed from: com.baidu.location.a.i */
public class C1055i implements LBSAuthManagerListener {
    /* renamed from: a */
    private static Object f2451a = new Object();
    /* renamed from: b */
    private static C1055i f2452b = null;
    /* renamed from: c */
    private int f2453c = 0;
    /* renamed from: d */
    private Context f2454d = null;
    /* renamed from: e */
    private long f2455e = 0;

    /* renamed from: a */
    public static C1055i m3757a() {
        C1055i c1055i;
        synchronized (f2451a) {
            if (f2452b == null) {
                f2452b = new C1055i();
            }
            c1055i = f2452b;
        }
        return c1055i;
    }

    /* renamed from: b */
    public static String m3758b(Context context) {
        try {
            return LBSAuthManager.getInstance(context).getPublicKey(context);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: c */
    public static String m3759c(Context context) {
        try {
            return LBSAuthManager.getInstance(context).getMCode();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public void m3760a(Context context) {
        this.f2454d = context;
        LBSAuthManager.getInstance(this.f2454d).authenticate(false, "lbs_locsdk", null, this);
        this.f2455e = System.currentTimeMillis();
    }

    /* renamed from: b */
    public boolean m3761b() {
        boolean z = true;
        if (!(this.f2453c == 0 || this.f2453c == LBSAuthManager.CODE_AUTHENTICATING || this.f2453c == LBSAuthManager.CODE_UNAUTHENTICATE || this.f2453c == -10 || this.f2453c == -11)) {
            z = false;
        }
        if (this.f2454d != null) {
            long currentTimeMillis = System.currentTimeMillis() - this.f2455e;
            if (z) {
                if (currentTimeMillis > 86400000) {
                    LBSAuthManager.getInstance(this.f2454d).authenticate(false, "lbs_locsdk", null, this);
                    this.f2455e = System.currentTimeMillis();
                }
            } else if (currentTimeMillis < 0 || currentTimeMillis > AbstractComponentTracker.LINGERING_TIMEOUT) {
                LBSAuthManager.getInstance(this.f2454d).authenticate(false, "lbs_locsdk", null, this);
                this.f2455e = System.currentTimeMillis();
            }
        }
        return z;
    }

    public void onAuthResult(int i, String str) {
        this.f2453c = i;
        Log.i(C1090a.f2659a, "LocationAuthManager Authentication Error errorcode = " + i + " , msg = " + str);
    }
}
