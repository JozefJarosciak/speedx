package cn.jpush.android.p002a;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.telephony.CellLocation;
import android.widget.Toast;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.ac;
import com.avos.avoscloud.AVOSCloud;
import java.util.Date;
import org.json.JSONArray;

/* renamed from: cn.jpush.android.a.d */
public abstract class C0395d {
    /* renamed from: B */
    private static final String[] f431B;
    /* renamed from: b */
    public static int f432b = AVOSCloud.DEFAULT_NETWORK_TIMEOUT;
    /* renamed from: c */
    public static boolean f433c = true;
    /* renamed from: f */
    private static boolean f434f = false;
    /* renamed from: A */
    private JSONArray f435A;
    /* renamed from: a */
    public String f436a;
    /* renamed from: d */
    protected boolean f437d;
    /* renamed from: e */
    private boolean f438e;
    /* renamed from: g */
    private int f439g;
    /* renamed from: h */
    private C0393b f440h;
    /* renamed from: i */
    private C0398g f441i;
    /* renamed from: j */
    private Context f442j;
    /* renamed from: k */
    private int[] f443k;
    /* renamed from: l */
    private C0397f f444l;
    /* renamed from: m */
    private boolean f445m;
    /* renamed from: n */
    private final BroadcastReceiver f446n;
    /* renamed from: o */
    private long f447o;
    /* renamed from: p */
    private int f448p;
    /* renamed from: q */
    private boolean f449q;
    /* renamed from: r */
    private boolean f450r;
    /* renamed from: s */
    private C0401j f451s;
    /* renamed from: t */
    private int f452t;
    /* renamed from: u */
    private final Date f453u;
    /* renamed from: v */
    private JSONArray f454v;
    /* renamed from: w */
    private boolean f455w;
    /* renamed from: x */
    private boolean f456x;
    /* renamed from: y */
    private boolean f457y;
    /* renamed from: z */
    private String f458z;

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
        r5 = "L*Z";
        r0 = -1;
        r7 = r6;
        r8 = r6;
        r6 = r1;
    L_0x000d:
        r5 = r5.toCharArray();
        r9 = r5.length;
        if (r9 > r2) goto L_0x007d;
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
            case 0: goto L_0x0071;
            case 1: goto L_0x0074;
            case 2: goto L_0x0077;
            case 3: goto L_0x007a;
            default: goto L_0x0021;
        };
    L_0x0021:
        r13 = 14;
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
        r0 = "L(RaD\"\u0018}k_+_~}D)X#On\u0005s^]r\u0011KGr\u0015bLZh";
        r5 = r0;
        r6 = r2;
        r7 = r8;
        r0 = r1;
        goto L_0x000d;
    L_0x0048:
        r7[r6] = r5;
        r0 = "L(RaD\"\u0018}k_+_~}D)X#Me\u0007xJKr\u0011KGr\u0015bLZh";
        r5 = r0;
        r6 = r3;
        r7 = r8;
        r0 = r2;
        goto L_0x000d;
    L_0x0051:
        r7[r6] = r5;
        r0 = "L(RaD\"\u0018}k_+_~}D)X#On\u0005s^]r\u0005yL\\~\u0003iAAn\u0007bDAc";
        r5 = r0;
        r6 = r4;
        r7 = r8;
        r0 = r3;
        goto L_0x000d;
    L_0x005a:
        r7[r6] = r5;
        r5 = 4;
        r0 = "L(RaD\"\u0018}k_+_~}D)X#On\u0005s^]r\u0000CKr\nyNOy\u000fyC";
        r6 = r5;
        r7 = r8;
        r5 = r0;
        r0 = r4;
        goto L_0x000d;
    L_0x0064:
        r7[r6] = r5;
        f431B = r8;
        r0 = 15000; // 0x3a98 float:2.102E-41 double:7.411E-320;
        f432b = r0;
        f433c = r2;
        f434f = r1;
        return;
    L_0x0071:
        r13 = 45;
        goto L_0x0023;
    L_0x0074:
        r13 = 70;
        goto L_0x0023;
    L_0x0077:
        r13 = 54;
        goto L_0x0023;
    L_0x007a:
        r13 = 13;
        goto L_0x0023;
    L_0x007d:
        r10 = r1;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.a.d.<clinit>():void");
    }

    private C0395d(Context context) {
        this.f438e = false;
        this.f449q = false;
        this.f453u = new Date();
        this.f456x = false;
        this.f457y = false;
        this.f446n = new C0396e();
        this.f442j = context.getApplicationContext();
        this.f440h = new C0393b(context);
        this.f451s = new C0401j(context);
        this.f441i = new C0398g(context);
    }

    private C0395d(Context context, String str) {
        this(context);
        if (str == null || "".equals(str)) {
            this.f436a = f431B[0];
        } else {
            this.f436a = str;
        }
    }

    private C0395d(Context context, String str, boolean z) {
        this(context, str);
        this.f456x = z;
    }

    public C0395d(Context context, String str, boolean z, boolean z2) {
        this(context, str, z);
        this.f438e = z2;
    }

    /* renamed from: a */
    static /* synthetic */ void m994a(C0395d c0395d, Object obj) {
        if (f434f) {
            obj.toString();
            ac.m1581b();
            Toast.makeText(c0395d.f442j, String.valueOf(obj), 0).show();
        }
    }

    /* renamed from: a */
    private static boolean m995a(Context context) {
        return C0490b.m1697c(context, f431B[3]) && C0490b.m1697c(context, f431B[1]) && C0490b.m1697c(context, f431B[2]) && C0490b.m1697c(context, f431B[4]);
    }

    /* renamed from: a */
    public final String m1009a() {
        String d = this.f441i.m1023d();
        return d == null ? "" : d;
    }

    /* renamed from: a */
    public final void m1010a(JSONArray jSONArray) {
        this.f454v = jSONArray;
    }

    /* renamed from: b */
    public final JSONArray m1011b() {
        return !C0490b.m1697c(this.f442j, f431B[3]) ? null : this.f440h.m988e() ? this.f440h.m985b() : this.f454v;
    }

    /* renamed from: b */
    public final void m1012b(JSONArray jSONArray) {
        this.f435A = jSONArray;
    }

    /* renamed from: c */
    public final JSONArray m1013c() {
        return this.f435A != null ? this.f435A : null;
    }

    /* renamed from: d */
    public abstract void mo2194d();

    /* renamed from: e */
    public final void m1015e() {
        if (this.f448p == 1) {
            CellLocation.requestLocationUpdate();
            this.f448p = 2;
            this.f444l.sendEmptyMessage(1);
            if (this.f451s.m1029b().isWifiEnabled()) {
                this.f451s.m1029b().startScan();
                this.f450r = false;
            } else if (this.f438e) {
                this.f447o = System.currentTimeMillis();
                if (f433c) {
                    this.f450r = true;
                } else {
                    this.f444l.sendEmptyMessageDelayed(5, 8000);
                }
            } else {
                this.f444l.sendEmptyMessageDelayed(5, 0);
            }
        }
    }

    /* renamed from: f */
    public final void m1016f() {
        if (!this.f457y) {
            this.f457y = true;
            this.f438e = false;
            this.f456x = false;
            if (C0490b.m1697c(this.f442j, f431B[3])) {
                this.f454v = this.f440h.m986c();
            } else {
                ac.m1586d();
                this.f454v = null;
            }
            if (!C0490b.m1697c(this.f442j, f431B[1])) {
                ac.m1586d();
                this.f435A = null;
            } else if (C0395d.m995a(this.f442j) || C0490b.m1738u(this.f442j)) {
                this.f455w = this.f451s.m1028a();
                if (this.f455w) {
                    this.f435A = this.f451s.m1030c();
                } else {
                    this.f435A = null;
                }
            } else {
                this.f435A = null;
            }
            if (!C0490b.m1697c(this.f442j, f431B[4])) {
                ac.m1586d();
            } else if (this.f441i.m1020a()) {
                this.f441i.m1021b();
                if (!("" == this.f441i.m1023d() || this.f441i.m1023d() == null || System.currentTimeMillis() - this.f441i.m1024e() >= 30000)) {
                    this.f452t = 0;
                    this.f458z = this.f441i.m1023d();
                }
                if (!this.f449q && !this.f456x) {
                    mo2194d();
                    return;
                }
            }
            this.f458z = "";
            if (!this.f449q) {
            }
        }
    }

    /* renamed from: g */
    public final void m1017g() {
        this.f457y = false;
        if (C0490b.m1697c(this.f442j, f431B[4]) && this.f441i.m1020a()) {
            this.f441i.m1022c();
        }
    }
}
