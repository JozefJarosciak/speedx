package com.baidu.location.p041a;

import android.location.Location;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import ch.qos.logback.classic.turbo.ReconfigureOnChangeFilter;
import com.baidu.location.Address;
import com.baidu.location.BDLocation;
import com.baidu.location.C1102f;
import com.baidu.location.Poi;
import com.baidu.location.p041a.C1054h.C1052a;
import com.baidu.location.p041a.C1054h.C1053b;
import com.baidu.location.p042d.C1091b;
import com.baidu.location.p042d.C1100j;
import com.baidu.location.p043b.C1072a;
import com.baidu.location.p043b.C1074b;
import com.baidu.location.p043b.C1079d;
import com.baidu.location.p043b.C1082g;
import com.baidu.location.p043b.C1085h;
import java.util.List;

/* renamed from: com.baidu.location.a.j */
public class C1059j extends C1054h {
    /* renamed from: h */
    public static boolean f2458h = false;
    /* renamed from: i */
    private static C1059j f2459i = null;
    /* renamed from: A */
    private double f2460A;
    /* renamed from: B */
    private boolean f2461B;
    /* renamed from: C */
    private long f2462C;
    /* renamed from: D */
    private long f2463D;
    /* renamed from: E */
    private C1057a f2464E;
    /* renamed from: F */
    private boolean f2465F;
    /* renamed from: G */
    private boolean f2466G;
    /* renamed from: H */
    private boolean f2467H;
    /* renamed from: I */
    private boolean f2468I;
    /* renamed from: J */
    private C1058b f2469J;
    /* renamed from: K */
    private boolean f2470K;
    /* renamed from: e */
    final int f2471e;
    /* renamed from: f */
    public C1053b f2472f;
    /* renamed from: g */
    public final Handler f2473g;
    /* renamed from: j */
    private boolean f2474j;
    /* renamed from: k */
    private String f2475k;
    /* renamed from: l */
    private BDLocation f2476l;
    /* renamed from: m */
    private BDLocation f2477m;
    /* renamed from: n */
    private C1082g f2478n;
    /* renamed from: o */
    private C1072a f2479o;
    /* renamed from: p */
    private C1082g f2480p;
    /* renamed from: q */
    private C1072a f2481q;
    /* renamed from: r */
    private boolean f2482r;
    /* renamed from: s */
    private volatile boolean f2483s;
    /* renamed from: t */
    private boolean f2484t;
    /* renamed from: u */
    private long f2485u;
    /* renamed from: v */
    private long f2486v;
    /* renamed from: w */
    private Address f2487w;
    /* renamed from: x */
    private String f2488x;
    /* renamed from: y */
    private List<Poi> f2489y;
    /* renamed from: z */
    private double f2490z;

    /* renamed from: com.baidu.location.a.j$a */
    private class C1057a implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C1059j f2456a;

        public void run() {
            if (this.f2456a.f2465F) {
                this.f2456a.f2465F = false;
                if (!this.f2456a.f2466G) {
                }
            }
        }
    }

    /* renamed from: com.baidu.location.a.j$b */
    private class C1058b implements Runnable {
        /* renamed from: a */
        final /* synthetic */ C1059j f2457a;

        private C1058b(C1059j c1059j) {
            this.f2457a = c1059j;
        }

        public void run() {
            if (this.f2457a.f2470K) {
                this.f2457a.f2470K = false;
            }
            if (this.f2457a.f2484t) {
                this.f2457a.f2484t = false;
                this.f2457a.m3777g(null);
            }
        }
    }

    private C1059j() {
        this.f2471e = 1000;
        this.f2474j = true;
        this.f2472f = null;
        this.f2475k = null;
        this.f2476l = null;
        this.f2477m = null;
        this.f2478n = null;
        this.f2479o = null;
        this.f2480p = null;
        this.f2481q = null;
        this.f2482r = true;
        this.f2483s = false;
        this.f2484t = false;
        this.f2485u = 0;
        this.f2486v = 0;
        this.f2487w = null;
        this.f2488x = null;
        this.f2489y = null;
        this.f2461B = false;
        this.f2462C = 0;
        this.f2463D = 0;
        this.f2464E = null;
        this.f2465F = false;
        this.f2466G = false;
        this.f2467H = true;
        this.f2473g = new C1052a(this);
        this.f2468I = false;
        this.f2469J = null;
        this.f2470K = false;
        this.f2472f = new C1053b(this);
    }

    /* renamed from: a */
    private boolean m3765a(C1072a c1072a) {
        this.b = C1074b.m3866a().m3884f();
        return this.b == c1072a ? false : this.b == null || c1072a == null || !c1072a.m3854a(this.b);
    }

    /* renamed from: a */
    private boolean m3766a(C1082g c1082g) {
        this.a = C1085h.m3959a().m3975m();
        return c1082g == this.a ? false : this.a == null || c1082g == null || !c1082g.m3953c(this.a);
    }

    /* renamed from: c */
    public static synchronized C1059j m3769c() {
        C1059j c1059j;
        synchronized (C1059j.class) {
            if (f2459i == null) {
                f2459i = new C1059j();
            }
            c1059j = f2459i;
        }
        return c1059j;
    }

    /* renamed from: c */
    private void m3770c(Message message) {
        int d;
        boolean z = message.getData().getBoolean("isWaitingLocTag", false);
        if (z) {
            f2458h = true;
        }
        if (z) {
            d = C1038a.m3645a().m3656d(message);
            C1060k.m3789a().m3798d();
        } else {
            d = C1038a.m3645a().m3656d(message);
            C1060k.m3789a().m3798d();
        }
        switch (d) {
            case 1:
                m3773d(message);
                return;
            case 2:
                m3776f(message);
                return;
            case 3:
                if (C1079d.m3897a().m3937i()) {
                    m3775e(message);
                    return;
                }
                return;
            default:
                throw new IllegalArgumentException(String.format("this type %d is illegal", new Object[]{Integer.valueOf(d)}));
        }
    }

    /* renamed from: d */
    private void m3773d(Message message) {
        if (C1079d.m3897a().m3937i()) {
            m3775e(message);
            C1060k.m3789a().m3797c();
            return;
        }
        m3776f(message);
        C1060k.m3789a().m3795b();
    }

    /* renamed from: e */
    private void m3775e(Message message) {
        BDLocation bDLocation = new BDLocation(C1079d.m3897a().m3934f());
        if (C1100j.f2736g.equals("all") || C1100j.f2737h || C1100j.f2738i) {
            float[] fArr = new float[2];
            Location.distanceBetween(this.f2460A, this.f2490z, bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
            if (fArr[0] < 100.0f) {
                if (this.f2487w != null) {
                    bDLocation.setAddr(this.f2487w);
                }
                if (this.f2488x != null) {
                    bDLocation.setLocationDescribe(this.f2488x);
                }
                if (this.f2489y != null) {
                    bDLocation.setPoiList(this.f2489y);
                }
            } else {
                this.f2461B = true;
                m3776f(null);
            }
        }
        this.f2476l = bDLocation;
        this.f2477m = null;
        C1038a.m3645a().m3651a(bDLocation);
    }

    /* renamed from: f */
    private void m3776f(Message message) {
        if (this.f2482r) {
            this.f2463D = SystemClock.uptimeMillis();
            m3777g(message);
        } else if (!this.f2483s) {
            this.f2463D = SystemClock.uptimeMillis();
            if (C1085h.m3959a().m3968e()) {
                this.f2484t = true;
                if (this.f2469J == null) {
                    this.f2469J = new C1058b();
                }
                if (this.f2470K && this.f2469J != null) {
                    this.f2473g.removeCallbacks(this.f2469J);
                }
                this.f2473g.postDelayed(this.f2469J, 3500);
                this.f2470K = true;
                return;
            }
            m3777g(message);
        }
    }

    /* renamed from: g */
    private void m3777g(Message message) {
        if (!this.f2483s) {
            if (System.currentTimeMillis() - this.f2485u <= 0 || System.currentTimeMillis() - this.f2485u >= 1000 || this.f2476l == null) {
                this.f2483s = true;
                this.f2474j = m3765a(this.f2479o);
                if (m3766a(this.f2478n) || this.f2474j || this.f2476l == null || this.f2461B) {
                    this.f2485u = System.currentTimeMillis();
                    String a = m3753a(null);
                    if (a == null) {
                        String[] h = m3778h();
                        long currentTimeMillis = System.currentTimeMillis();
                        if (currentTimeMillis - this.f2462C > ReconfigureOnChangeFilter.DEFAULT_REFRESH_PERIOD) {
                            this.f2462C = currentTimeMillis;
                        }
                        String j = C1085h.m3959a().m3972j();
                        a = j != null ? j + m3756b() + h[0] : "" + m3756b() + h[0];
                        j = C1091b.m3989a().m3990a(true);
                        if (j != null) {
                            a = a + j;
                        }
                    }
                    if (this.f2475k != null) {
                        a = a + this.f2475k;
                        this.f2475k = null;
                    }
                    this.f2472f.m3749a(a);
                    this.f2479o = this.b;
                    this.f2478n = this.a;
                    if (this.f2482r) {
                        this.f2482r = false;
                        if (C1085h.m3963h() && message != null && C1038a.m3645a().m3658e(message) < 1000) {
                            return;
                        }
                        return;
                    }
                    return;
                }
                if (this.f2477m != null && System.currentTimeMillis() - this.f2486v > 30000) {
                    this.f2476l = this.f2477m;
                    this.f2477m = null;
                }
                if (C1060k.m3789a().m3801g()) {
                    this.f2476l.setDirection(C1060k.m3789a().m3803i());
                }
                C1038a.m3645a().m3651a(this.f2476l);
                m3779i();
                return;
            }
            C1038a.m3645a().m3651a(this.f2476l);
            m3779i();
        }
    }

    /* renamed from: h */
    private String[] m3778h() {
        String[] strArr = new String[]{"", "Location failed beacuse we can not get any loc information!"};
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("&apl=");
        int a = C1100j.m4006a(C1102f.getServiceContext());
        if (a == 1) {
            strArr[1] = "Location failed beacuse we can not get any loc information in airplane mode, you can turn it off and try again!!";
        }
        stringBuffer.append(a);
        String c = C1100j.m4020c(C1102f.getServiceContext());
        if (c.contains("0|0|")) {
            strArr[1] = "Location failed beacuse we can not get any loc information without any location permission!";
        }
        stringBuffer.append(c);
        if (VERSION.SDK_INT >= 23) {
            stringBuffer.append("&loc=");
            if (C1100j.m4014b(C1102f.getServiceContext()) == 0) {
                strArr[1] = "Location failed beacuse we can not get any loc information with the phone loc mode is off, you can turn it on and try again!";
            }
            stringBuffer.append(C1100j.m4014b(C1102f.getServiceContext()));
        }
        stringBuffer.append(C1085h.m3959a().m3969f());
        stringBuffer.append(C1074b.m3866a().m3885g());
        stringBuffer.append(C1100j.m4022d(C1102f.getServiceContext()));
        strArr[0] = stringBuffer.toString();
        return strArr;
    }

    /* renamed from: i */
    private void m3779i() {
        this.f2483s = false;
        this.f2466G = false;
        this.f2467H = false;
        this.f2461B = false;
        m3780j();
    }

    /* renamed from: j */
    private void m3780j() {
        if (this.f2476l != null) {
            C1070t.m3834a().m3852c();
        }
    }

    /* renamed from: a */
    public void mo2600a() {
        if (this.f2464E != null && this.f2465F) {
            this.f2465F = false;
            this.f2473g.removeCallbacks(this.f2464E);
        }
        if (C1079d.m3897a().m3937i()) {
            BDLocation bDLocation = new BDLocation(C1079d.m3897a().m3934f());
            if (C1100j.f2736g.equals("all") || C1100j.f2737h || C1100j.f2738i) {
                float[] fArr = new float[2];
                Location.distanceBetween(this.f2460A, this.f2490z, bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
                if (fArr[0] < 100.0f) {
                    if (this.f2487w != null) {
                        bDLocation.setAddr(this.f2487w);
                    }
                    if (this.f2488x != null) {
                        bDLocation.setLocationDescribe(this.f2488x);
                    }
                    if (this.f2489y != null) {
                        bDLocation.setPoiList(this.f2489y);
                    }
                }
            }
            C1038a.m3645a().m3651a(bDLocation);
            m3779i();
        } else if (this.f2466G) {
            m3779i();
        } else {
            if (this.f2474j || this.f2476l == null) {
                BDLocation bDLocation2 = new BDLocation();
                bDLocation2.setLocType(63);
                this.f2476l = null;
                C1038a.m3645a().m3651a(bDLocation2);
            } else {
                C1038a.m3645a().m3651a(this.f2476l);
            }
            this.f2477m = null;
            m3779i();
        }
    }

    /* renamed from: a */
    public void mo2601a(Message message) {
        if (this.f2464E != null && this.f2465F) {
            this.f2465F = false;
            this.f2473g.removeCallbacks(this.f2464E);
        }
        BDLocation bDLocation = (BDLocation) message.obj;
        BDLocation bDLocation2 = new BDLocation(bDLocation);
        if (bDLocation.hasAddr()) {
            this.f2487w = bDLocation.getAddress();
            this.f2490z = bDLocation.getLongitude();
            this.f2460A = bDLocation.getLatitude();
        }
        if (bDLocation.getLocationDescribe() != null) {
            this.f2488x = bDLocation.getLocationDescribe();
            this.f2490z = bDLocation.getLongitude();
            this.f2460A = bDLocation.getLatitude();
        }
        if (bDLocation.getPoiList() != null) {
            this.f2489y = bDLocation.getPoiList();
            this.f2490z = bDLocation.getLongitude();
            this.f2460A = bDLocation.getLatitude();
        }
        float[] fArr;
        if (C1079d.m3897a().m3937i()) {
            bDLocation = new BDLocation(C1079d.m3897a().m3934f());
            if (C1100j.f2736g.equals("all") || C1100j.f2737h || C1100j.f2738i) {
                fArr = new float[2];
                Location.distanceBetween(this.f2460A, this.f2490z, bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
                if (fArr[0] < 100.0f) {
                    if (this.f2487w != null) {
                        bDLocation.setAddr(this.f2487w);
                    }
                    if (this.f2488x != null) {
                        bDLocation.setLocationDescribe(this.f2488x);
                    }
                    if (this.f2489y != null) {
                        bDLocation.setPoiList(this.f2489y);
                    }
                }
            }
            C1038a.m3645a().m3651a(bDLocation);
            m3779i();
        } else if (this.f2466G) {
            fArr = new float[2];
            if (this.f2476l != null) {
                Location.distanceBetween(this.f2476l.getLatitude(), this.f2476l.getLongitude(), bDLocation.getLatitude(), bDLocation.getLongitude(), fArr);
            }
            if (fArr[0] > 10.0f) {
                this.f2476l = bDLocation;
                if (!this.f2467H) {
                    this.f2467H = false;
                    C1038a.m3645a().m3651a(bDLocation);
                }
            } else if (bDLocation.getUserIndoorState() > -1) {
                this.f2476l = bDLocation;
                C1038a.m3645a().m3651a(bDLocation);
            }
            m3779i();
        } else {
            boolean z;
            this.f2477m = null;
            if (bDLocation.getLocType() == 161 && "cl".equals(bDLocation.getNetworkLocationType()) && this.f2476l != null && this.f2476l.getLocType() == 161 && "wf".equals(this.f2476l.getNetworkLocationType()) && System.currentTimeMillis() - this.f2486v < 30000) {
                this.f2477m = bDLocation;
                z = true;
            } else {
                z = false;
            }
            if (z) {
                C1038a.m3645a().m3651a(this.f2476l);
            } else {
                C1038a.m3645a().m3651a(bDLocation);
                this.f2486v = System.currentTimeMillis();
            }
            if (!C1100j.m4013a(bDLocation)) {
                this.f2476l = null;
            } else if (!z) {
                this.f2476l = bDLocation;
            }
            int a = C1100j.m4007a(c, "ssid\":\"", "\"");
            if (a == Integer.MIN_VALUE || this.f2478n == null) {
                this.f2475k = null;
            } else {
                this.f2475k = this.f2478n.m3950b(a);
            }
            if (C1085h.m3963h()) {
                m3779i();
            } else {
                m3779i();
            }
        }
    }

    /* renamed from: b */
    public void m3783b(Message message) {
        if (this.f2468I) {
            m3770c(message);
        }
    }

    /* renamed from: d */
    public void m3784d() {
        this.f2482r = true;
        this.f2483s = false;
        this.f2468I = true;
    }

    /* renamed from: e */
    public void m3785e() {
        this.f2483s = false;
        this.f2484t = false;
        this.f2466G = false;
        this.f2467H = true;
        m3787g();
        this.f2468I = false;
    }

    /* renamed from: f */
    public void m3786f() {
        if (this.f2484t) {
            m3777g(null);
            this.f2484t = false;
        }
    }

    /* renamed from: g */
    public void m3787g() {
        this.f2476l = null;
    }
}
