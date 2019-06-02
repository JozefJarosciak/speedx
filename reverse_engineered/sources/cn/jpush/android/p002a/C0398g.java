package cn.jpush.android.p002a;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import cn.jpush.android.util.ac;

/* renamed from: cn.jpush.android.a.g */
public final class C0398g {
    /* renamed from: z */
    private static final String[] f465z;
    /* renamed from: a */
    public double f466a;
    /* renamed from: b */
    public double f467b;
    /* renamed from: c */
    private Context f468c;
    /* renamed from: d */
    private LocationManager f469d;
    /* renamed from: e */
    private Location f470e;
    /* renamed from: f */
    private String f471f;
    /* renamed from: g */
    private long f472g;
    /* renamed from: h */
    private final LocationListener f473h = new C0399h(this);

    static {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1723278948.run(Unknown Source)
*/
        /*
        r4 = 3;
        r3 = 2;
        r2 = 1;
        r1 = 0;
        r0 = 5;
        r6 = new java.lang.String[r0];
        r5 = "c#CRH-";
        r0 = -1;
        r7 = r6;
        r8 = r6;
        r6 = r1;
    L_0x000d:
        r5 = r5.toCharArray();
        r9 = r5.length;
        if (r9 > r2) goto L_0x0075;
    L_0x0014:
        r10 = r1;
    L_0x0015:
        r11 = r5;
        r12 = r10;
        r15 = r9;
        r9 = r5;
        r5 = r15;
    L_0x001a:
        r14 = r9[r10];
        r13 = r12 % 5;
        switch(r13) {
            case 0: goto L_0x0069;
            case 1: goto L_0x006c;
            case 2: goto L_0x006f;
            case 3: goto L_0x0072;
            default: goto L_0x0021;
        };
    L_0x0021:
        r13 = 39;
    L_0x0023:
        r13 = r13 ^ r14;
        r13 = (char) r13;
        r9[r10] = r13;
        r10 = r12 + 1;
        if (r5 != 0) goto L_0x002f;
    L_0x002b:
        r9 = r11;
        r12 = r10;
        r10 = r5;
        goto L_0x001a;
    L_0x002f:
        r9 = r5;
        r5 = r11;
    L_0x0031:
        if (r9 > r10) goto L_0x0015;
    L_0x0033:
        r9 = new java.lang.String;
        r9.<init>(r5);
        r5 = r9.intern();
        switch(r0) {
            case 0: goto L_0x0048;
            case 1: goto L_0x0051;
            case 2: goto L_0x005a;
            case 3: goto L_0x0064;
            default: goto L_0x003f;
        };
    L_0x003f:
        r7[r6] = r5;
        r0 = "}'DVN{#";
        r5 = r0;
        r6 = r2;
        r7 = r8;
        r0 = r1;
        goto L_0x000d;
    L_0x0048:
        r7[r6] = r5;
        r0 = "j6D";
        r5 = r0;
        r6 = r3;
        r7 = r8;
        r0 = r2;
        goto L_0x000d;
    L_0x0051:
        r7[r6] = r5;
        r0 = "a)TDSd)Y";
        r5 = r0;
        r6 = r4;
        r7 = r8;
        r0 = r3;
        goto L_0x000d;
    L_0x005a:
        r7[r6] = r5;
        r5 = 4;
        r0 = "vd[DS/|\u0012C\u000b/*YB\u00057cQ\t\u0005l*C\u0007\u001d( \u001b\u0007Eh'E\u0007\u001d( \u001b\u0007Fn%\u0015\u001f\u0002k;";
        r6 = r5;
        r7 = r8;
        r5 = r0;
        r0 = r4;
        goto L_0x000d;
    L_0x0064:
        r7[r6] = r5;
        f465z = r8;
        return;
    L_0x0069:
        r13 = 13;
        goto L_0x0023;
    L_0x006c:
        r13 = 70;
        goto L_0x0023;
    L_0x006f:
        r13 = 55;
        goto L_0x0023;
    L_0x0072:
        r13 = 37;
        goto L_0x0023;
    L_0x0075:
        r10 = r1;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.a.g.<clinit>():void");
    }

    public C0398g(Context context) {
        this.f468c = context;
        this.f469d = (LocationManager) this.f468c.getSystemService(f465z[3]);
    }

    /* renamed from: a */
    private void m1018a(Location location) {
        if (location != null) {
            try {
                this.f466a = location.getLatitude();
                this.f467b = location.getLongitude();
                this.f472g = System.currentTimeMillis();
                this.f471f = String.format(f465z[4], new Object[]{Double.valueOf(this.f466a), Double.valueOf(this.f467b), Double.valueOf(location.getAltitude()), Float.valueOf(location.getBearing()), Float.valueOf(location.getAccuracy())});
                return;
            } catch (Exception e) {
                e.getMessage();
                ac.m1588e();
            }
        }
        this.f471f = "";
    }

    /* renamed from: a */
    public final boolean m1020a() {
        try {
            return this.f469d != null ? this.f469d.isProviderEnabled(f465z[2]) || this.f469d.isProviderEnabled(f465z[0]) || this.f469d.isProviderEnabled(f465z[1]) : false;
        } catch (SecurityException e) {
            ac.m1586d();
            return false;
        } catch (IllegalArgumentException e2) {
            ac.m1586d();
            return false;
        }
    }

    /* renamed from: b */
    public final void m1021b() {
        try {
            Criteria criteria = new Criteria();
            criteria.setAccuracy(1);
            criteria.setAltitudeRequired(false);
            criteria.setBearingRequired(false);
            criteria.setCostAllowed(true);
            criteria.setPowerRequirement(1);
            String bestProvider = this.f469d.getBestProvider(criteria, true);
            if (bestProvider != null) {
                this.f470e = this.f469d.getLastKnownLocation(bestProvider);
                if (this.f470e != null) {
                    m1018a(this.f470e);
                }
                this.f469d.requestLocationUpdates(bestProvider, 2000, 10.0f, this.f473h);
            }
        } catch (SecurityException e) {
            ac.m1586d();
        } catch (Exception e2) {
            ac.m1586d();
        }
    }

    /* renamed from: c */
    public final void m1022c() {
        try {
            if (this.f473h != null) {
                this.f469d.removeUpdates(this.f473h);
            } else {
                ac.m1586d();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: d */
    public final String m1023d() {
        return this.f471f;
    }

    /* renamed from: e */
    public final long m1024e() {
        return this.f472g;
    }
}
