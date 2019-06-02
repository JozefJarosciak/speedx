package com.baidu.location.p041a;

import android.location.Location;
import android.os.Handler;
import android.os.Message;
import com.baidu.location.BDLocation;
import com.baidu.location.C1102f;
import com.baidu.location.Jni;
import com.baidu.location.p042d.C1041e;
import com.baidu.location.p042d.C1100j;
import com.baidu.location.p043b.C1072a;
import com.baidu.location.p043b.C1074b;
import com.baidu.location.p043b.C1079d;
import com.baidu.location.p043b.C1082g;
import com.baidu.location.p043b.C1085h;
import java.util.HashMap;
import java.util.Locale;

/* renamed from: com.baidu.location.a.h */
public abstract class C1054h {
    /* renamed from: c */
    public static String f2443c = null;
    /* renamed from: a */
    public C1082g f2444a = null;
    /* renamed from: b */
    public C1072a f2445b = null;
    /* renamed from: d */
    final Handler f2446d = new C1052a(this);
    /* renamed from: e */
    private boolean f2447e = true;
    /* renamed from: f */
    private boolean f2448f = false;
    /* renamed from: g */
    private String f2449g = null;
    /* renamed from: h */
    private String f2450h = null;

    /* renamed from: com.baidu.location.a.h$a */
    public class C1052a extends Handler {
        /* renamed from: a */
        final /* synthetic */ C1054h f2439a;

        public C1052a(C1054h c1054h) {
            this.f2439a = c1054h;
        }

        public void handleMessage(Message message) {
            if (C1102f.isServing) {
                switch (message.what) {
                    case 21:
                        this.f2439a.mo2601a(message);
                        return;
                    case 62:
                    case 63:
                        this.f2439a.mo2600a();
                        return;
                    default:
                        return;
                }
            }
        }
    }

    /* renamed from: com.baidu.location.a.h$b */
    class C1053b extends C1041e {
        /* renamed from: a */
        String f2440a;
        /* renamed from: b */
        String f2441b;
        /* renamed from: c */
        final /* synthetic */ C1054h f2442c;

        public C1053b(C1054h c1054h) {
            this.f2442c = c1054h;
            this.f2440a = null;
            this.f2441b = null;
            this.k = new HashMap();
        }

        /* renamed from: a */
        public void mo2597a() {
            this.h = C1100j.m4019c();
            if (!((!C1100j.f2737h && !C1100j.f2738i) || this.f2442c.f2449g == null || this.f2442c.f2450h == null)) {
                this.f2441b += String.format(Locale.CHINA, "&ki=%s&sn=%s", new Object[]{this.f2442c.f2449g, this.f2442c.f2450h});
            }
            String encodeTp4 = Jni.encodeTp4(this.f2441b);
            this.f2441b = null;
            if (this.f2440a == null) {
                this.f2440a = C1070t.m3844b();
            }
            this.k.put("bloc", encodeTp4);
            if (this.f2440a != null) {
                this.k.put("up", this.f2440a);
            }
            this.k.put("trtm", String.format(Locale.CHINA, "%d", new Object[]{Long.valueOf(System.currentTimeMillis())}));
        }

        /* renamed from: a */
        public void m3749a(String str) {
            this.f2441b = str;
            m3672e();
        }

        /* renamed from: a */
        public void mo2598a(boolean z) {
            Message obtainMessage;
            BDLocation bDLocation;
            if (!z || this.j == null) {
                obtainMessage = this.f2442c.f2446d.obtainMessage(63);
                obtainMessage.obj = "HttpStatus error";
                obtainMessage.sendToTarget();
            } else {
                try {
                    String str = this.j;
                    C1054h.f2443c = str;
                    try {
                        bDLocation = new BDLocation(str);
                        if (bDLocation.getLocType() == 161) {
                            C1051g.m3735a().m3743a(str);
                        }
                        bDLocation.setOperators(C1074b.m3866a().m3886h());
                        if (C1060k.m3789a().m3801g()) {
                            bDLocation.setDirection(C1060k.m3789a().m3803i());
                        }
                    } catch (Exception e) {
                        bDLocation = new BDLocation();
                        bDLocation.setLocType(0);
                    }
                    this.f2440a = null;
                    if (bDLocation.getLocType() == 0 && bDLocation.getLatitude() == Double.MIN_VALUE && bDLocation.getLongitude() == Double.MIN_VALUE) {
                        obtainMessage = this.f2442c.f2446d.obtainMessage(63);
                        obtainMessage.obj = "HttpStatus error";
                        obtainMessage.sendToTarget();
                    } else {
                        Message obtainMessage2 = this.f2442c.f2446d.obtainMessage(21);
                        obtainMessage2.obj = bDLocation;
                        obtainMessage2.sendToTarget();
                    }
                } catch (Exception e2) {
                    obtainMessage = this.f2442c.f2446d.obtainMessage(63);
                    obtainMessage.obj = "HttpStatus error";
                    obtainMessage.sendToTarget();
                }
            }
            if (this.k != null) {
                this.k.clear();
            }
        }
    }

    /* renamed from: a */
    public String m3753a(String str) {
        if (this.f2449g == null) {
            this.f2449g = C1055i.m3758b(C1102f.getServiceContext());
        }
        if (this.f2450h == null) {
            this.f2450h = C1055i.m3759c(C1102f.getServiceContext());
        }
        if (this.f2445b == null || !this.f2445b.m3853a()) {
            this.f2445b = C1074b.m3866a().m3884f();
        }
        if (this.f2444a == null || !this.f2444a.m3956f()) {
            this.f2444a = C1085h.m3959a().m3975m();
        }
        Location g = C1079d.m3897a().m3937i() ? C1079d.m3897a().m3935g() : null;
        if ((this.f2445b == null || this.f2445b.m3856c()) && ((this.f2444a == null || this.f2444a.m3945a() == 0) && g == null)) {
            return null;
        }
        String b = m3756b();
        if (C1051g.m3735a().m3746d() == -2) {
            b = b + "&imo=1";
        }
        if (this.f2444a == null || this.f2444a.m3945a() == 0) {
            String j = C1085h.m3959a().m3972j();
            if (j != null) {
                b = j + b;
            }
        }
        return C1100j.m4011a(this.f2445b, this.f2444a, g, b, 0);
    }

    /* renamed from: a */
    public abstract void mo2600a();

    /* renamed from: a */
    public abstract void mo2601a(Message message);

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: b */
    public java.lang.String m3756b() {
        /*
        r8 = this;
        r7 = 1;
        r6 = 0;
        r0 = com.baidu.location.p041a.C1038a.m3645a();
        r1 = r0.m3654c();
        r0 = com.baidu.location.p043b.C1085h.m3963h();
        if (r0 == 0) goto L_0x005d;
    L_0x0010:
        r0 = "&cn=32";
    L_0x0012:
        r2 = r8.f2447e;
        if (r2 == 0) goto L_0x0076;
    L_0x0016:
        r8.f2447e = r6;
        r2 = com.baidu.location.p043b.C1085h.m3959a();
        r2 = r2.m3977o();
        r3 = android.text.TextUtils.isEmpty(r2);
        if (r3 != 0) goto L_0x0045;
    L_0x0026:
        r3 = "02:00:00:00:00:00";
        r3 = r2.equals(r3);
        if (r3 != 0) goto L_0x0045;
    L_0x002e:
        r3 = ":";
        r4 = "";
        r2 = r2.replace(r3, r4);
        r3 = java.util.Locale.CHINA;
        r4 = "%s&mac=%s";
        r5 = 2;
        r5 = new java.lang.Object[r5];
        r5[r6] = r0;
        r5[r7] = r2;
        r0 = java.lang.String.format(r3, r4, r5);
    L_0x0045:
        r2 = android.os.Build.VERSION.SDK_INT;
        r3 = 17;
        if (r2 <= r3) goto L_0x004b;
    L_0x004b:
        r2 = new java.lang.StringBuilder;
        r2.<init>();
        r0 = r2.append(r0);
        r0 = r0.append(r1);
        r0 = r0.toString();
        return r0;
    L_0x005d:
        r0 = java.util.Locale.CHINA;
        r2 = "&cn=%d";
        r3 = new java.lang.Object[r7];
        r4 = com.baidu.location.p043b.C1074b.m3866a();
        r4 = r4.m3883e();
        r4 = java.lang.Integer.valueOf(r4);
        r3[r6] = r4;
        r0 = java.lang.String.format(r0, r2, r3);
        goto L_0x0012;
    L_0x0076:
        r2 = r8.f2448f;
        if (r2 != 0) goto L_0x004b;
    L_0x007a:
        r2 = com.baidu.location.p041a.C1070t.m3851f();
        if (r2 == 0) goto L_0x0091;
    L_0x0080:
        r3 = new java.lang.StringBuilder;
        r3.<init>();
        r0 = r3.append(r0);
        r0 = r0.append(r2);
        r0 = r0.toString();
    L_0x0091:
        r8.f2448f = r7;
        goto L_0x004b;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.location.a.h.b():java.lang.String");
    }
}
