package cn.jpush.android.p002a;

import android.content.Context;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.cdma.CdmaCellLocation;
import android.telephony.gsm.GsmCellLocation;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.ac;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: cn.jpush.android.a.b */
public final class C0393b {
    /* renamed from: z */
    private static final String[] f409z;
    /* renamed from: a */
    private int f410a = 0;
    /* renamed from: b */
    private int f411b = 0;
    /* renamed from: c */
    private int f412c = 0;
    /* renamed from: d */
    private boolean f413d = false;
    /* renamed from: e */
    private boolean f414e = false;
    /* renamed from: f */
    private int f415f = 0;
    /* renamed from: g */
    private double f416g = 0.0d;
    /* renamed from: h */
    private PhoneStateListener f417h;
    /* renamed from: i */
    private double f418i = 0.0d;
    /* renamed from: j */
    private int f419j = 0;
    /* renamed from: k */
    private int f420k = 0;
    /* renamed from: l */
    private int f421l = 0;
    /* renamed from: m */
    private int f422m = 0;
    /* renamed from: n */
    private String f423n = "";
    /* renamed from: o */
    private String f424o = "";
    /* renamed from: p */
    private String f425p = "";
    /* renamed from: q */
    private TelephonyManager f426q;
    /* renamed from: r */
    private String f427r;
    /* renamed from: s */
    private ArrayList<C0392a> f428s = new ArrayList();
    /* renamed from: t */
    private Context f429t = null;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 26;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u0016w!Y8\u001e}k[2\u0005t,X$\u001ev+\u0005\u00164Z\u0000x\u0004(Z\nj\u0005$\\\u001ag\u00184X\u0011b\u00189";
        r0 = -1;
        r4 = r3;
    L_0x0009:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002e;
    L_0x0012:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0017:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x013c;
            case 1: goto L_0x0140;
            case 2: goto L_0x0144;
            case 3: goto L_0x0148;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 87;
    L_0x0020:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002c;
    L_0x0028:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0017;
    L_0x002c:
        r5 = r1;
        r1 = r7;
    L_0x002e:
        if (r5 > r6) goto L_0x0012;
    L_0x0030:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0044;
            case 1: goto L_0x004c;
            case 2: goto L_0x0054;
            case 3: goto L_0x005c;
            case 4: goto L_0x0064;
            case 5: goto L_0x006c;
            case 6: goto L_0x0074;
            case 7: goto L_0x007d;
            case 8: goto L_0x0087;
            case 9: goto L_0x0092;
            case 10: goto L_0x009d;
            case 11: goto L_0x00a8;
            case 12: goto L_0x00b3;
            case 13: goto L_0x00be;
            case 14: goto L_0x00c9;
            case 15: goto L_0x00d4;
            case 16: goto L_0x00df;
            case 17: goto L_0x00ea;
            case 18: goto L_0x00f5;
            case 19: goto L_0x0100;
            case 20: goto L_0x010b;
            case 21: goto L_0x0116;
            case 22: goto L_0x0121;
            case 23: goto L_0x012c;
            case 24: goto L_0x0137;
            default: goto L_0x003c;
        };
    L_0x003c:
        r3[r2] = r1;
        r2 = 1;
        r1 = "\u0007q*E2";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0044:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u0004p\"E6\u001bF6_%\u0012w\"_?";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004c:
        r3[r2] = r1;
        r2 = 3;
        r1 = "\u0010|1i6\u0004|\u0016_6\u0003p*E\u001b\u0018w\"B#\u0002} ";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0054:
        r3[r2] = r1;
        r2 = 4;
        r1 = "(w,Om";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005c:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u0010|+N%\u0016m,D9";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0064:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\u0010|1x.\u0004m F\u001e\u0013";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006c:
        r3[r2] = r1;
        r2 = 7;
        r1 = "\u001av'B;\u0012F&D\"\u0019m7R\b\u0014v!N";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0074:
        r3[r2] = r1;
        r2 = 8;
        r1 = "\u0005x!B8(m<[2";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007d:
        r3[r2] = r1;
        r2 = 9;
        r1 = "\u0004p!\u0011";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0087:
        r3[r2] = r1;
        r2 = 10;
        r1 = "\u0010|1i6\u0004|\u0016_6\u0003p*E\u001b\u0016m,_\"\u0013|";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0092:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\u0014|)G\u001e\u0013#";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009d:
        r3[r2] = r1;
        r2 = 12;
        r1 = "\u001av'B;\u0012F+N#\u0000v7@\b\u0014v!N";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a8:
        r3[r2] = r1;
        r2 = 13;
        r1 = "\u0014}(J";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b3:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\u0014x7Y>\u0012k";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00be:
        r3[r2] = r1;
        r2 = 15;
        r1 = "[9\"N9\u0012k$_>\u0018w";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c9:
        r3[r2] = r1;
        r2 = 16;
        r1 = "[9&J%\u0005p Ym";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d4:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\u0014|)G\b\u001e}";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00df:
        r3[r2] = r1;
        r2 = 18;
        r1 = "\"w S'\u0012z1N3V9&N;\u001bU*H6\u0003p*Ew\u001ejeE\"\u001bui\u000b0\u001eo \u000b\"\u000797N'\u0018k1\u000b4\u0012u)\u0006>\u0019*";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00ea:
        r3[r2] = r1;
        r2 = 19;
        r1 = "\u0010|1i6\u0004|\u0016_6\u0003p*E\u001e\u0013";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f5:
        r3[r2] = r1;
        r2 = 20;
        r1 = "4|)G\u001e\u0019*f6\u0019x\"N%";
        r0 = 19;
        r3 = r4;
        goto L_0x0009;
    L_0x0100:
        r3[r2] = r1;
        r2 = 21;
        r1 = "\u0005x!B8#`5Nm";
        r0 = 20;
        r3 = r4;
        goto L_0x0009;
    L_0x010b:
        r3[r2] = r1;
        r2 = 22;
        r1 = "\u0010|1e2\u0003n*Y<>}";
        r0 = 21;
        r3 = r4;
        goto L_0x0009;
    L_0x0116:
        r3[r2] = r1;
        r2 = 23;
        r1 = "\u0016~ ";
        r0 = 22;
        r3 = r4;
        goto L_0x0009;
    L_0x0121:
        r3[r2] = r1;
        r2 = 24;
        r1 = "\u001bv&J#\u001ev+t6\u0005|$t4\u0018} ";
        r0 = 23;
        r3 = r4;
        goto L_0x0009;
    L_0x012c:
        r3[r2] = r1;
        r2 = 25;
        r1 = "({,Om";
        r0 = 24;
        r3 = r4;
        goto L_0x0009;
    L_0x0137:
        r3[r2] = r1;
        f409z = r4;
        return;
    L_0x013c:
        r9 = 119; // 0x77 float:1.67E-43 double:5.9E-322;
        goto L_0x0020;
    L_0x0140:
        r9 = 25;
        goto L_0x0020;
    L_0x0144:
        r9 = 69;
        goto L_0x0020;
    L_0x0148:
        r9 = 43;
        goto L_0x0020;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.a.b.<clinit>():void");
    }

    public C0393b(Context context) {
        this.f429t = context;
        if (C0490b.m1697c(context, f409z[0])) {
            try {
                this.f417h = new C0394c(this);
                this.f426q = (TelephonyManager) context.getSystemService(f409z[1]);
                this.f426q.listen(this.f417h, 18);
            } catch (Exception e) {
                ac.m1592h();
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: h */
    private java.lang.String m983h() {
        /*
        r8 = this;
        r1 = 0;
        r6 = 4669142098048450560; // 0x40cc200000000000 float:0.0 double:14400.0;
        r3 = 0;
        r0 = r8.f429t;
        if (r0 == 0) goto L_0x0019;
    L_0x000b:
        r0 = r8.f429t;
        r2 = f409z;
        r2 = r2[r3];
        r0 = cn.jpush.android.util.C0490b.m1697c(r0, r2);
        if (r0 != 0) goto L_0x0019;
    L_0x0017:
        r0 = r1;
    L_0x0018:
        return r0;
    L_0x0019:
        r0 = r8.f426q;	 Catch:{ Exception -> 0x00ea }
        r0 = r0.getCellLocation();	 Catch:{ Exception -> 0x00ea }
        r2 = r8.f426q;	 Catch:{ Exception -> 0x00ea }
        r2 = r2.getNetworkOperator();	 Catch:{ Exception -> 0x00ea }
        r3 = r2.length();	 Catch:{ Exception -> 0x00ea }
        r4 = 5;
        if (r3 == r4) goto L_0x00d1;
    L_0x002c:
        r4 = 6;
        if (r3 == r4) goto L_0x002f;
    L_0x002f:
        r3 = r8.f426q;	 Catch:{ Exception -> 0x00ea }
        r3 = r3.getPhoneType();	 Catch:{ Exception -> 0x00ea }
        r4 = 2;
        if (r3 != r4) goto L_0x00eb;
    L_0x0038:
        r3 = r0 instanceof android.telephony.cdma.CdmaCellLocation;	 Catch:{ Exception -> 0x00ea }
        if (r3 == 0) goto L_0x00eb;
    L_0x003c:
        if (r0 == 0) goto L_0x00eb;
    L_0x003e:
        r0 = (android.telephony.cdma.CdmaCellLocation) r0;	 Catch:{ Exception -> 0x00ea }
        r3 = r0.getBaseStationLatitude();	 Catch:{ Exception -> 0x00ea }
        r4 = (double) r3;	 Catch:{ Exception -> 0x00ea }
        r4 = r4 / r6;
        r8.f416g = r4;	 Catch:{ Exception -> 0x00ea }
        r3 = r0.getBaseStationLongitude();	 Catch:{ Exception -> 0x00ea }
        r4 = (double) r3;	 Catch:{ Exception -> 0x00ea }
        r4 = r4 / r6;
        r8.f418i = r4;	 Catch:{ Exception -> 0x00ea }
        r3 = r0.getSystemId();	 Catch:{ Exception -> 0x00ea }
        r8.f422m = r3;	 Catch:{ Exception -> 0x00ea }
        r3 = r0.getBaseStationId();	 Catch:{ Exception -> 0x00ea }
        r8.f411b = r3;	 Catch:{ Exception -> 0x00ea }
        r0 = r0.getNetworkId();	 Catch:{ Exception -> 0x00ea }
        r8.f421l = r0;	 Catch:{ Exception -> 0x00ea }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00ea }
        r0.<init>();	 Catch:{ Exception -> 0x00ea }
        r3 = r8.f422m;	 Catch:{ Exception -> 0x00ea }
        r0.append(r3);	 Catch:{ Exception -> 0x00ea }
        cn.jpush.android.util.ac.m1584c();	 Catch:{ Exception -> 0x00ea }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00ea }
        r0.<init>();	 Catch:{ Exception -> 0x00ea }
        r3 = r8.f411b;	 Catch:{ Exception -> 0x00ea }
        r0.append(r3);	 Catch:{ Exception -> 0x00ea }
        cn.jpush.android.util.ac.m1584c();	 Catch:{ Exception -> 0x00ea }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00ea }
        r0.<init>();	 Catch:{ Exception -> 0x00ea }
        r3 = r8.f421l;	 Catch:{ Exception -> 0x00ea }
        r0.append(r3);	 Catch:{ Exception -> 0x00ea }
        cn.jpush.android.util.ac.m1584c();	 Catch:{ Exception -> 0x00ea }
        r0 = new cn.jpush.android.a.a;	 Catch:{ Exception -> 0x00ea }
        r0.<init>();	 Catch:{ Exception -> 0x00ea }
        r3 = r8.f411b;	 Catch:{ Exception -> 0x00ea }
        r0.m976a(r3);	 Catch:{ Exception -> 0x00ea }
        r3 = r8.f421l;	 Catch:{ Exception -> 0x00ea }
        r0.m981d(r3);	 Catch:{ Exception -> 0x00ea }
        r3 = r8.f422m;	 Catch:{ Exception -> 0x00ea }
        r0.m980c(r3);	 Catch:{ Exception -> 0x00ea }
        r3 = 0;
        r4 = 3;
        r2 = r2.substring(r3, r4);	 Catch:{ Exception -> 0x00ea }
        r2 = java.lang.Integer.parseInt(r2);	 Catch:{ Exception -> 0x00ea }
        r0.m979b(r2);	 Catch:{ Exception -> 0x00ea }
        r2 = f409z;	 Catch:{ Exception -> 0x00ea }
        r3 = 13;
        r2 = r2[r3];	 Catch:{ Exception -> 0x00ea }
        r0.m977a(r2);	 Catch:{ Exception -> 0x00ea }
        r2 = r8.f428s;	 Catch:{ Exception -> 0x00ea }
        r2.add(r0);	 Catch:{ Exception -> 0x00ea }
        r2 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00ea }
        r2.<init>();	 Catch:{ Exception -> 0x00ea }
        r3 = r0.m975a();	 Catch:{ Exception -> 0x00ea }
        r2.append(r3);	 Catch:{ Exception -> 0x00ea }
        cn.jpush.android.util.ac.m1581b();	 Catch:{ Exception -> 0x00ea }
        r0 = r0.toString();	 Catch:{ Exception -> 0x00ea }
        r8.f427r = r0;	 Catch:{ Exception -> 0x00ea }
        r0 = r8.f427r;	 Catch:{ Exception -> 0x00ea }
        goto L_0x0018;
    L_0x00d1:
        r4 = 0;
        r5 = 3;
        r4 = r2.substring(r4, r5);	 Catch:{ Exception -> 0x00ea }
        r4 = java.lang.Integer.parseInt(r4);	 Catch:{ Exception -> 0x00ea }
        r8.f419j = r4;	 Catch:{ Exception -> 0x00ea }
        r4 = 3;
        r3 = r2.substring(r4, r3);	 Catch:{ Exception -> 0x00ea }
        r3 = java.lang.Integer.parseInt(r3);	 Catch:{ Exception -> 0x00ea }
        r8.f420k = r3;	 Catch:{ Exception -> 0x00ea }
        goto L_0x002f;
    L_0x00ea:
        r0 = move-exception;
    L_0x00eb:
        r0 = r1;
        goto L_0x0018;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.a.b.h():java.lang.String");
    }

    /* renamed from: a */
    public final int m984a() {
        return this.f411b;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: b */
    public final org.json.JSONArray m985b() {
        /*
        r8 = this;
        r1 = 0;
        r6 = 4669142098048450560; // 0x40cc200000000000 float:0.0 double:14400.0;
        r3 = 0;
        r0 = r8.f429t;
        if (r0 == 0) goto L_0x0019;
    L_0x000b:
        r0 = r8.f429t;
        r2 = f409z;
        r2 = r2[r3];
        r0 = cn.jpush.android.util.C0490b.m1697c(r0, r2);
        if (r0 != 0) goto L_0x0019;
    L_0x0017:
        r0 = r1;
    L_0x0018:
        return r0;
    L_0x0019:
        r0 = r8.f426q;	 Catch:{ Exception -> 0x00d7 }
        r0 = r0.getCellLocation();	 Catch:{ Exception -> 0x00d7 }
        r2 = r8.f426q;	 Catch:{ Exception -> 0x00d7 }
        r2 = r2.getNetworkOperator();	 Catch:{ Exception -> 0x00d7 }
        r3 = r2.length();	 Catch:{ Exception -> 0x00d7 }
        r4 = 5;
        if (r3 == r4) goto L_0x00be;
    L_0x002c:
        r4 = 6;
        if (r3 == r4) goto L_0x002f;
    L_0x002f:
        r3 = r8.f426q;	 Catch:{ Exception -> 0x00d7 }
        r3 = r3.getPhoneType();	 Catch:{ Exception -> 0x00d7 }
        r4 = 2;
        if (r3 != r4) goto L_0x00db;
    L_0x0038:
        r3 = r0 instanceof android.telephony.cdma.CdmaCellLocation;	 Catch:{ Exception -> 0x00d7 }
        if (r3 == 0) goto L_0x00db;
    L_0x003c:
        if (r0 == 0) goto L_0x00db;
    L_0x003e:
        r0 = (android.telephony.cdma.CdmaCellLocation) r0;	 Catch:{ Exception -> 0x00d7 }
        r3 = r0.getBaseStationLatitude();	 Catch:{ Exception -> 0x00d7 }
        r4 = (double) r3;	 Catch:{ Exception -> 0x00d7 }
        r4 = r4 / r6;
        r8.f416g = r4;	 Catch:{ Exception -> 0x00d7 }
        r3 = r0.getBaseStationLongitude();	 Catch:{ Exception -> 0x00d7 }
        r4 = (double) r3;	 Catch:{ Exception -> 0x00d7 }
        r4 = r4 / r6;
        r8.f418i = r4;	 Catch:{ Exception -> 0x00d7 }
        r3 = r0.getSystemId();	 Catch:{ Exception -> 0x00d7 }
        r8.f422m = r3;	 Catch:{ Exception -> 0x00d7 }
        r3 = r0.getBaseStationId();	 Catch:{ Exception -> 0x00d7 }
        r8.f411b = r3;	 Catch:{ Exception -> 0x00d7 }
        r0 = r0.getNetworkId();	 Catch:{ Exception -> 0x00d7 }
        r8.f421l = r0;	 Catch:{ Exception -> 0x00d7 }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00d7 }
        r0.<init>();	 Catch:{ Exception -> 0x00d7 }
        r3 = r8.f422m;	 Catch:{ Exception -> 0x00d7 }
        r0.append(r3);	 Catch:{ Exception -> 0x00d7 }
        cn.jpush.android.util.ac.m1584c();	 Catch:{ Exception -> 0x00d7 }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00d7 }
        r0.<init>();	 Catch:{ Exception -> 0x00d7 }
        r3 = r8.f411b;	 Catch:{ Exception -> 0x00d7 }
        r0.append(r3);	 Catch:{ Exception -> 0x00d7 }
        cn.jpush.android.util.ac.m1584c();	 Catch:{ Exception -> 0x00d7 }
        r0 = new java.lang.StringBuilder;	 Catch:{ Exception -> 0x00d7 }
        r0.<init>();	 Catch:{ Exception -> 0x00d7 }
        r3 = r8.f421l;	 Catch:{ Exception -> 0x00d7 }
        r0.append(r3);	 Catch:{ Exception -> 0x00d7 }
        cn.jpush.android.util.ac.m1584c();	 Catch:{ Exception -> 0x00d7 }
        r0 = new cn.jpush.android.a.a;	 Catch:{ Exception -> 0x00d7 }
        r0.<init>();	 Catch:{ Exception -> 0x00d7 }
        r3 = r8.f411b;	 Catch:{ Exception -> 0x00d7 }
        r0.m976a(r3);	 Catch:{ Exception -> 0x00d7 }
        r3 = r8.f421l;	 Catch:{ Exception -> 0x00d7 }
        r0.m981d(r3);	 Catch:{ Exception -> 0x00d7 }
        r3 = r8.f422m;	 Catch:{ Exception -> 0x00d7 }
        r0.m980c(r3);	 Catch:{ Exception -> 0x00d7 }
        r3 = 0;
        r4 = 3;
        r2 = r2.substring(r3, r4);	 Catch:{ Exception -> 0x00d7 }
        r2 = java.lang.Integer.parseInt(r2);	 Catch:{ Exception -> 0x00d7 }
        r0.m979b(r2);	 Catch:{ Exception -> 0x00d7 }
        r2 = f409z;	 Catch:{ Exception -> 0x00d7 }
        r3 = 13;
        r2 = r2[r3];	 Catch:{ Exception -> 0x00d7 }
        r0.m977a(r2);	 Catch:{ Exception -> 0x00d7 }
        r2 = r8.f428s;	 Catch:{ Exception -> 0x00d7 }
        r2.add(r0);	 Catch:{ Exception -> 0x00d7 }
        r0 = r0.m978b();	 Catch:{ Exception -> 0x00d7 }
        goto L_0x0018;
    L_0x00be:
        r4 = 0;
        r5 = 3;
        r4 = r2.substring(r4, r5);	 Catch:{ Exception -> 0x00d7 }
        r4 = java.lang.Integer.parseInt(r4);	 Catch:{ Exception -> 0x00d7 }
        r8.f419j = r4;	 Catch:{ Exception -> 0x00d7 }
        r4 = 3;
        r3 = r2.substring(r4, r3);	 Catch:{ Exception -> 0x00d7 }
        r3 = java.lang.Integer.parseInt(r3);	 Catch:{ Exception -> 0x00d7 }
        r8.f420k = r3;	 Catch:{ Exception -> 0x00d7 }
        goto L_0x002f;
    L_0x00d7:
        r0 = move-exception;
        r0.printStackTrace();
    L_0x00db:
        r0 = r1;
        goto L_0x0018;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.a.b.b():org.json.JSONArray");
    }

    /* renamed from: c */
    public final JSONArray m986c() {
        CellLocation cellLocation;
        Object cellLocation2;
        int phoneType;
        Exception e;
        String networkOperator;
        int length;
        Class cls;
        Class[] clsArr;
        Method method;
        Method method2;
        Method method3;
        Object[] objArr;
        Method method4;
        CdmaCellLocation cdmaCellLocation;
        C0392a c0392a;
        int[] d;
        int i;
        try {
            cellLocation = this.f426q.getCellLocation();
        } catch (Exception e2) {
            cellLocation = null;
            ac.m1593i();
        }
        if (cellLocation == null) {
            ac.m1587d(f409z[20], f409z[18]);
            return null;
        }
        JSONArray jSONArray;
        long j;
        int i2;
        int i3;
        JSONObject jSONObject;
        this.f414e = false;
        this.f413d = false;
        this.f412c = 0;
        this.f415f = 0;
        this.f419j = 0;
        this.f420k = 0;
        this.f423n = "";
        this.f424o = "";
        this.f425p = "";
        if (this.f429t == null || C0490b.m1697c(this.f429t, f409z[0])) {
            try {
                cellLocation2 = this.f426q.getCellLocation();
            } catch (Exception e3) {
                cellLocation2 = null;
                ac.m1593i();
            }
            if (cellLocation2 == null) {
                ac.m1576a();
            } else {
                this.f425p = this.f426q.getNetworkOperatorName();
                this.f423n = C0490b.m1668a(this.f426q.getNetworkType());
                this.f424o = C0490b.m1734r(this.f429t);
                new StringBuilder(f409z[21]).append(this.f423n).append(f409z[16]).append(this.f425p).append(f409z[15]).append(this.f424o);
                ac.m1576a();
                try {
                    phoneType = this.f426q.getPhoneType();
                    try {
                        if (cellLocation2 instanceof GsmCellLocation) {
                            this.f414e = true;
                            GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation2;
                            this.f412c = gsmCellLocation.getCid();
                            this.f415f = gsmCellLocation.getLac();
                        }
                    } catch (Exception e4) {
                        e = e4;
                        e.printStackTrace();
                        networkOperator = this.f426q.getNetworkOperator();
                        length = networkOperator.length();
                        if (length > 3) {
                            this.f419j = Integer.parseInt(networkOperator.substring(0, 3));
                            this.f420k = Integer.parseInt(networkOperator.substring(3, length));
                        }
                        if (phoneType == 2) {
                            if (cellLocation2 instanceof CdmaCellLocation) {
                                cls = cellLocation2.getClass();
                                clsArr = new Class[0];
                                method = cls.getMethod(f409z[19], clsArr);
                                method2 = cls.getMethod(f409z[6], clsArr);
                                method3 = cls.getMethod(f409z[22], clsArr);
                                objArr = new Object[0];
                                this.f411b = ((Integer) method.invoke(cellLocation2, objArr)).intValue();
                                this.f422m = ((Integer) method2.invoke(cellLocation2, objArr)).intValue();
                                this.f421l = ((Integer) method3.invoke(cellLocation2, objArr)).intValue();
                                method = cls.getMethod(f409z[10], clsArr);
                                method4 = cls.getMethod(f409z[3], clsArr);
                                this.f416g = (double) ((Integer) method.invoke(cellLocation2, objArr)).intValue();
                                this.f418i = (double) ((Integer) method4.invoke(cellLocation2, objArr)).intValue();
                                this.f413d = true;
                            } else {
                                cdmaCellLocation = (CdmaCellLocation) cellLocation2;
                                this.f416g = ((double) cdmaCellLocation.getBaseStationLatitude()) / 14400.0d;
                                this.f418i = ((double) cdmaCellLocation.getBaseStationLongitude()) / 14400.0d;
                                this.f422m = cdmaCellLocation.getSystemId();
                                this.f411b = cdmaCellLocation.getBaseStationId();
                                this.f421l = cdmaCellLocation.getNetworkId();
                                new StringBuilder(f409z[9]).append(this.f422m).append(f409z[25]).append(this.f411b).append(f409z[4]).append(this.f421l);
                                ac.m1584c();
                                c0392a = new C0392a();
                                c0392a.m976a(this.f411b);
                                c0392a.m981d(this.f421l);
                                c0392a.m980c(this.f422m);
                                c0392a.m979b(Integer.parseInt(networkOperator.substring(0, 3)));
                                c0392a.m977a(f409z[13]);
                                this.f428s.add(c0392a);
                                new StringBuilder(f409z[11]).append(c0392a.m975a());
                                ac.m1581b();
                                this.f427r = c0392a.toString();
                                this.f413d = true;
                            }
                        }
                        jSONArray = new JSONArray();
                        d = m987d();
                        j = (long) this.f415f;
                        i2 = this.f419j;
                        i3 = this.f420k;
                        if (d.length < 2) {
                            d = new int[]{this.f412c, -60};
                        }
                        i = 0;
                        while (i < d.length) {
                            length = i + 1;
                            if (length >= 0) {
                            }
                            try {
                                jSONObject = new JSONObject();
                                jSONObject.put(f409z[17], d[i]);
                                jSONObject.put(f409z[24], j);
                                jSONObject.put(f409z[7], i2);
                                jSONObject.put(f409z[12], i3);
                                jSONObject.put(f409z[2], length);
                                jSONObject.put(f409z[23], 0);
                                jSONObject.put(f409z[8], this.f423n);
                                jSONObject.put(f409z[5], this.f424o);
                                jSONObject.put(f409z[14], this.f425p);
                                jSONArray.put(jSONObject);
                            } catch (Exception e5) {
                                e5.getMessage();
                                ac.m1588e();
                            }
                            i += 2;
                        }
                        if (this.f413d) {
                            try {
                                return new JSONArray().put(new JSONObject(m983h()));
                            } catch (JSONException e6) {
                            } catch (NullPointerException e7) {
                            }
                        }
                        return jSONArray;
                    }
                } catch (Exception e8) {
                    e = e8;
                    phoneType = 0;
                    e.printStackTrace();
                    networkOperator = this.f426q.getNetworkOperator();
                    length = networkOperator.length();
                    if (length > 3) {
                        this.f419j = Integer.parseInt(networkOperator.substring(0, 3));
                        this.f420k = Integer.parseInt(networkOperator.substring(3, length));
                    }
                    if (phoneType == 2) {
                        if (cellLocation2 instanceof CdmaCellLocation) {
                            cdmaCellLocation = (CdmaCellLocation) cellLocation2;
                            this.f416g = ((double) cdmaCellLocation.getBaseStationLatitude()) / 14400.0d;
                            this.f418i = ((double) cdmaCellLocation.getBaseStationLongitude()) / 14400.0d;
                            this.f422m = cdmaCellLocation.getSystemId();
                            this.f411b = cdmaCellLocation.getBaseStationId();
                            this.f421l = cdmaCellLocation.getNetworkId();
                            new StringBuilder(f409z[9]).append(this.f422m).append(f409z[25]).append(this.f411b).append(f409z[4]).append(this.f421l);
                            ac.m1584c();
                            c0392a = new C0392a();
                            c0392a.m976a(this.f411b);
                            c0392a.m981d(this.f421l);
                            c0392a.m980c(this.f422m);
                            c0392a.m979b(Integer.parseInt(networkOperator.substring(0, 3)));
                            c0392a.m977a(f409z[13]);
                            this.f428s.add(c0392a);
                            new StringBuilder(f409z[11]).append(c0392a.m975a());
                            ac.m1581b();
                            this.f427r = c0392a.toString();
                            this.f413d = true;
                        } else {
                            cls = cellLocation2.getClass();
                            clsArr = new Class[0];
                            method = cls.getMethod(f409z[19], clsArr);
                            method2 = cls.getMethod(f409z[6], clsArr);
                            method3 = cls.getMethod(f409z[22], clsArr);
                            objArr = new Object[0];
                            this.f411b = ((Integer) method.invoke(cellLocation2, objArr)).intValue();
                            this.f422m = ((Integer) method2.invoke(cellLocation2, objArr)).intValue();
                            this.f421l = ((Integer) method3.invoke(cellLocation2, objArr)).intValue();
                            method = cls.getMethod(f409z[10], clsArr);
                            method4 = cls.getMethod(f409z[3], clsArr);
                            this.f416g = (double) ((Integer) method.invoke(cellLocation2, objArr)).intValue();
                            this.f418i = (double) ((Integer) method4.invoke(cellLocation2, objArr)).intValue();
                            this.f413d = true;
                        }
                    }
                    jSONArray = new JSONArray();
                    d = m987d();
                    j = (long) this.f415f;
                    i2 = this.f419j;
                    i3 = this.f420k;
                    if (d.length < 2) {
                        d = new int[]{this.f412c, -60};
                    }
                    i = 0;
                    while (i < d.length) {
                        length = i + 1;
                        if (length >= 0) {
                        }
                        jSONObject = new JSONObject();
                        jSONObject.put(f409z[17], d[i]);
                        jSONObject.put(f409z[24], j);
                        jSONObject.put(f409z[7], i2);
                        jSONObject.put(f409z[12], i3);
                        jSONObject.put(f409z[2], length);
                        jSONObject.put(f409z[23], 0);
                        jSONObject.put(f409z[8], this.f423n);
                        jSONObject.put(f409z[5], this.f424o);
                        jSONObject.put(f409z[14], this.f425p);
                        jSONArray.put(jSONObject);
                        i += 2;
                    }
                    if (this.f413d) {
                        return new JSONArray().put(new JSONObject(m983h()));
                    }
                    return jSONArray;
                }
                try {
                    networkOperator = this.f426q.getNetworkOperator();
                    length = networkOperator.length();
                    if (length > 3) {
                        this.f419j = Integer.parseInt(networkOperator.substring(0, 3));
                        this.f420k = Integer.parseInt(networkOperator.substring(3, length));
                    }
                    if (phoneType == 2) {
                        if (cellLocation2 instanceof CdmaCellLocation) {
                            cdmaCellLocation = (CdmaCellLocation) cellLocation2;
                            this.f416g = ((double) cdmaCellLocation.getBaseStationLatitude()) / 14400.0d;
                            this.f418i = ((double) cdmaCellLocation.getBaseStationLongitude()) / 14400.0d;
                            this.f422m = cdmaCellLocation.getSystemId();
                            this.f411b = cdmaCellLocation.getBaseStationId();
                            this.f421l = cdmaCellLocation.getNetworkId();
                            new StringBuilder(f409z[9]).append(this.f422m).append(f409z[25]).append(this.f411b).append(f409z[4]).append(this.f421l);
                            ac.m1584c();
                            c0392a = new C0392a();
                            c0392a.m976a(this.f411b);
                            c0392a.m981d(this.f421l);
                            c0392a.m980c(this.f422m);
                            if (networkOperator != null && networkOperator.length() >= 3) {
                                c0392a.m979b(Integer.parseInt(networkOperator.substring(0, 3)));
                            }
                            c0392a.m977a(f409z[13]);
                            this.f428s.add(c0392a);
                            new StringBuilder(f409z[11]).append(c0392a.m975a());
                            ac.m1581b();
                            this.f427r = c0392a.toString();
                            this.f413d = true;
                        } else {
                            cls = cellLocation2.getClass();
                            clsArr = new Class[0];
                            method = cls.getMethod(f409z[19], clsArr);
                            method2 = cls.getMethod(f409z[6], clsArr);
                            method3 = cls.getMethod(f409z[22], clsArr);
                            objArr = new Object[0];
                            this.f411b = ((Integer) method.invoke(cellLocation2, objArr)).intValue();
                            this.f422m = ((Integer) method2.invoke(cellLocation2, objArr)).intValue();
                            this.f421l = ((Integer) method3.invoke(cellLocation2, objArr)).intValue();
                            method = cls.getMethod(f409z[10], clsArr);
                            method4 = cls.getMethod(f409z[3], clsArr);
                            this.f416g = (double) ((Integer) method.invoke(cellLocation2, objArr)).intValue();
                            this.f418i = (double) ((Integer) method4.invoke(cellLocation2, objArr)).intValue();
                            this.f413d = true;
                        }
                    }
                } catch (Exception e9) {
                    ac.m1593i();
                }
            }
        } else {
            ac.m1586d();
        }
        jSONArray = new JSONArray();
        d = m987d();
        j = (long) this.f415f;
        i2 = this.f419j;
        i3 = this.f420k;
        if (d.length < 2) {
            d = new int[]{this.f412c, -60};
        }
        i = 0;
        while (i < d.length && i <= 4) {
            length = i + 1;
            length = (length >= 0 || length > 31) ? 0 : (length * 2) - 113;
            jSONObject = new JSONObject();
            jSONObject.put(f409z[17], d[i]);
            jSONObject.put(f409z[24], j);
            jSONObject.put(f409z[7], i2);
            jSONObject.put(f409z[12], i3);
            jSONObject.put(f409z[2], length);
            jSONObject.put(f409z[23], 0);
            jSONObject.put(f409z[8], this.f423n);
            jSONObject.put(f409z[5], this.f424o);
            jSONObject.put(f409z[14], this.f425p);
            jSONArray.put(jSONObject);
            i += 2;
        }
        if (this.f413d) {
            return new JSONArray().put(new JSONObject(m983h()));
        }
        return jSONArray;
    }

    /* renamed from: d */
    public final int[] m987d() {
        if (this.f412c == 0) {
            return new int[0];
        }
        List<NeighboringCellInfo> neighboringCellInfo = this.f426q.getNeighboringCellInfo();
        if (neighboringCellInfo == null || neighboringCellInfo.size() == 0) {
            return new int[]{this.f412c};
        }
        Object obj = new int[((neighboringCellInfo.size() * 2) + 2)];
        obj[0] = this.f412c;
        obj[1] = this.f410a;
        int i = 2;
        for (NeighboringCellInfo neighboringCellInfo2 : neighboringCellInfo) {
            int cid = neighboringCellInfo2.getCid();
            if (cid > 0 && cid != 65535) {
                int i2 = i + 1;
                obj[i] = cid;
                i = i2 + 1;
                obj[i2] = neighboringCellInfo2.getRssi();
            }
        }
        Object obj2 = new int[i];
        System.arraycopy(obj, 0, obj2, 0, i);
        return obj2;
    }

    /* renamed from: e */
    public final boolean m988e() {
        return this.f413d;
    }

    /* renamed from: f */
    public final boolean m989f() {
        return this.f414e;
    }

    /* renamed from: g */
    public final float m990g() {
        if (!this.f413d && this.f414e) {
            m987d();
        }
        return 1.06535322E9f;
    }
}
