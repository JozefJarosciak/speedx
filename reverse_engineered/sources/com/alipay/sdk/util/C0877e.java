package com.alipay.sdk.util;

import android.app.Activity;
import android.content.Intent;
import android.content.ServiceConnection;
import com.alipay.android.app.IAlixPay;
import com.alipay.android.app.IRemoteServiceCallback;
import com.alipay.sdk.app.statistic.C0823a;
import com.alipay.sdk.app.statistic.C0825c;
import com.alipay.sdk.data.C0847a;
import com.alipay.sdk.util.C0885l.C0884a;

/* renamed from: com.alipay.sdk.util.e */
public class C0877e {
    /* renamed from: b */
    public static final String f2209b = "failed";
    /* renamed from: a */
    public Activity f2210a;
    /* renamed from: c */
    private IAlixPay f2211c;
    /* renamed from: d */
    private final Object f2212d = IAlixPay.class;
    /* renamed from: e */
    private boolean f2213e;
    /* renamed from: f */
    private C0813a f2214f;
    /* renamed from: g */
    private ServiceConnection f2215g = new C0878f(this);
    /* renamed from: h */
    private IRemoteServiceCallback f2216h = new C0879g(this);

    /* renamed from: com.alipay.sdk.util.e$a */
    public interface C0813a {
        /* renamed from: a */
        void mo2424a();
    }

    public C0877e(Activity activity, C0813a c0813a) {
        this.f2210a = activity;
        this.f2214f = c0813a;
    }

    /* renamed from: a */
    public final String m3444a(String str) {
        try {
            C0884a a = C0885l.m3461a(this.f2210a, C0885l.f2234b);
            if (a.m3459a()) {
                return f2209b;
            }
            if (a != null && a.f2232b > 78) {
                Intent intent = new Intent();
                intent.setClassName(C0885l.f2234b, "com.alipay.android.app.TransProcessPayActivity");
                this.f2210a.startActivity(intent);
                Thread.sleep(200);
            }
            return m3443b(str);
        } catch (Throwable th) {
            C0823a.m3185a(C0825c.f1952b, C0825c.f1946C, th);
        }
    }

    /* renamed from: a */
    private void m3441a(C0884a c0884a) throws InterruptedException {
        if (c0884a != null && c0884a.f2232b > 78) {
            Intent intent = new Intent();
            intent.setClassName(C0885l.f2234b, "com.alipay.android.app.TransProcessPayActivity");
            this.f2210a.startActivity(intent);
            Thread.sleep(200);
        }
    }

    /* renamed from: b */
    private String m3443b(String str) {
        Intent intent = new Intent();
        intent.setPackage(C0885l.f2234b);
        intent.setAction("com.eg.android.AlipayGphone.IAlixPay");
        String g = C0885l.m3483g(this.f2210a);
        try {
            String g2;
            this.f2210a.getApplicationContext().bindService(intent, this.f2215g, 1);
            synchronized (this.f2212d) {
                if (this.f2211c == null) {
                    try {
                        this.f2212d.wait((long) C0847a.m3258b().m3263a());
                    } catch (Throwable e) {
                        C0823a.m3185a(C0825c.f1952b, C0825c.f1944A, e);
                    }
                }
            }
            try {
                if (this.f2211c == null) {
                    g2 = C0885l.m3483g(this.f2210a);
                    C0823a.m3184a(C0825c.f1952b, C0825c.f1971u, g + "|" + g2 + "|" + C0885l.m3484h(this.f2210a));
                    g2 = f2209b;
                    try {
                        this.f2211c.unregisterCallback(this.f2216h);
                    } catch (Throwable th) {
                    }
                    try {
                        this.f2210a.unbindService(this.f2215g);
                    } catch (Throwable th2) {
                    }
                    this.f2216h = null;
                    this.f2215g = null;
                    this.f2211c = null;
                    if (!this.f2213e) {
                        return g2;
                    }
                    this.f2210a.setRequestedOrientation(0);
                    this.f2213e = false;
                    return g2;
                }
                if (this.f2214f != null) {
                    this.f2214f.mo2424a();
                }
                if (this.f2210a.getRequestedOrientation() == 0) {
                    this.f2210a.setRequestedOrientation(1);
                    this.f2213e = true;
                }
                this.f2211c.registerCallback(this.f2216h);
                g2 = this.f2211c.Pay(str);
                try {
                    this.f2211c.unregisterCallback(this.f2216h);
                } catch (Throwable th3) {
                }
                try {
                    this.f2210a.unbindService(this.f2215g);
                } catch (Throwable th4) {
                }
                this.f2216h = null;
                this.f2215g = null;
                this.f2211c = null;
                if (!this.f2213e) {
                    return g2;
                }
                this.f2210a.setRequestedOrientation(0);
                this.f2213e = false;
                return g2;
            } catch (Throwable th5) {
            }
            this.f2210a.unbindService(this.f2215g);
            this.f2216h = null;
            this.f2215g = null;
            this.f2211c = null;
            if (this.f2213e) {
                return g2;
            }
            this.f2210a.setRequestedOrientation(0);
            this.f2213e = false;
            return g2;
            this.f2216h = null;
            this.f2215g = null;
            this.f2211c = null;
            if (this.f2213e) {
                return g2;
            }
            this.f2210a.setRequestedOrientation(0);
            this.f2213e = false;
            return g2;
        } catch (Throwable e2) {
            C0823a.m3185a(C0825c.f1952b, C0825c.f1976z, e2);
            return f2209b;
        }
    }

    /* renamed from: a */
    private void m3440a() {
        this.f2210a = null;
    }
}
