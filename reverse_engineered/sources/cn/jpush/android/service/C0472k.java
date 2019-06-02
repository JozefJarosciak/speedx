package cn.jpush.android.service;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import cn.jpush.android.helpers.ConnectingHelper;
import cn.jpush.android.util.ac;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicLong;

/* renamed from: cn.jpush.android.service.k */
public final class C0472k implements Runnable {
    /* renamed from: a */
    public static AtomicLong f865a = new AtomicLong(0);
    /* renamed from: b */
    public static AtomicBoolean f866b = new AtomicBoolean(false);
    /* renamed from: z */
    private static final String[] f867z;
    /* renamed from: c */
    private Context f868c;
    /* renamed from: d */
    private Handler f869d;
    /* renamed from: e */
    private boolean f870e;
    /* renamed from: f */
    private volatile boolean f871f = false;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 20;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "5i=\u0017w\u0004x1\u001c'\u0002t=\u0017n\u0006t:\\'>~)Re\u0002t?\u0019']1,\u0017sJ";
        r0 = -1;
        r4 = r3;
    L_0x0009:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002d;
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
            case 0: goto L_0x010a;
            case 1: goto L_0x010e;
            case 2: goto L_0x0112;
            case 3: goto L_0x0116;
            default: goto L_0x001e;
        };
    L_0x001e:
        r9 = 7;
    L_0x001f:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002b;
    L_0x0027:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0017;
    L_0x002b:
        r5 = r1;
        r1 = r7;
    L_0x002d:
        if (r5 > r6) goto L_0x0012;
    L_0x002f:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0043;
            case 1: goto L_0x004b;
            case 2: goto L_0x0053;
            case 3: goto L_0x005b;
            case 4: goto L_0x0063;
            case 5: goto L_0x006b;
            case 6: goto L_0x0073;
            case 7: goto L_0x007c;
            case 8: goto L_0x0086;
            case 9: goto L_0x0091;
            case 10: goto L_0x009c;
            case 11: goto L_0x00a7;
            case 12: goto L_0x00b2;
            case 13: goto L_0x00bd;
            case 14: goto L_0x00c8;
            case 15: goto L_0x00d3;
            case 16: goto L_0x00de;
            case 17: goto L_0x00e9;
            case 18: goto L_0x00f4;
            default: goto L_0x003b;
        };
    L_0x003b:
        r3[r2] = r1;
        r2 = 1;
        r1 = ">t*\u0005h\u0002z7\u001c`3}7\u0017i\u0004";
        r0 = 0;
        r3 = r4;
        goto L_0x0009;
    L_0x0043:
        r3[r2] = r1;
        r2 = 2;
        r1 = "1r*\u001bh\u001e1sRh\u001e]1\u0015n\u001eW?\u001bk\u0015u~_'\u0002t-\u0002D\u001fu;H";
        r0 = 1;
        r3 = r4;
        goto L_0x0009;
    L_0x004b:
        r3[r2] = r1;
        r2 = 3;
        r1 = "%5\u001ch\u0007~3d\u001b1,\u0017v\u0005t-\u0006']1=\u001fcJ";
        r0 = 2;
        r3 = r4;
        goto L_0x0009;
    L_0x0053:
        r3[r2] = r1;
        r2 = 4;
        r1 = "2t9\u001biPe1Ru\u0005~\u001biPR1\u001ci\u0015r*\u001bi\u0017E6\u0000b\u0011u~_'\u0019ud";
        r0 = 3;
        r3 = r4;
        goto L_0x0009;
    L_0x005b:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\"t=\u0017n\u0006t:Rb\u0002c1\u0000'\u0002t-\u0002h\u001eb;R*Pr1\u0016bJ";
        r0 = 4;
        r3 = r4;
        goto L_0x0009;
    L_0x0063:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\"t=\u0017n\u0006t:Re\te;\u0001']12\u0017iJ";
        r0 = 5;
        r3 = r4;
        goto L_0x0009;
    L_0x006b:
        r3[r2] = r1;
        r2 = 7;
        r1 = "2c;\u0013lPc;\u0011b\u0019g7\u001c`Ps'Rp\u0011*!s\u001fa~_'\u0013~0\u001cb\u0013e7\u001diJ";
        r0 = 6;
        r3 = r4;
        goto L_0x0009;
    L_0x0073:
        r3[r2] = r1;
        r2 = 8;
        r1 = "1r*\u001bh\u001e1sRh\u001e]1\u0015`\u0015u\u0017\u001c']1=\u001di\u001et=\u0006n\u001fd";
        r0 = 7;
        r3 = r4;
        goto L_0x0009;
    L_0x007c:
        r3[r2] = r1;
        r2 = 9;
        r1 = "1r*\u001bh\u001e1sRu\u0015r;\u001bq\u0015u\u001d\u001dj\u001dp0\u0016']1=\u001fcJ";
        r0 = 8;
        r3 = r4;
        goto L_0x0009;
    L_0x0086:
        r3[r2] = r1;
        r2 = 10;
        r1 = "%6\u0013i\u0014};\u0016'\u0002t-\u0002h\u001eb;Rd\u001f|3\u0013i\u00141sR";
        r0 = 9;
        r3 = r4;
        goto L_0x0009;
    L_0x0091:
        r3[r2] = r1;
        r2 = 11;
        r1 = "\\1=\u001di\u001et=\u0006n\u001fd";
        r0 = 10;
        r3 = r4;
        goto L_0x0009;
    L_0x009c:
        r3[r2] = r1;
        r2 = 12;
        r1 = "\\1.\u0019`J";
        r0 = 11;
        r3 = r4;
        goto L_0x0009;
    L_0x00a7:
        r3[r2] = r1;
        r2 = 13;
        r1 = ">t*\u0005h\u0002z~\u001en\u0003e;\u001cn\u001evp\\)";
        r0 = 12;
        r3 = r4;
        goto L_0x0009;
    L_0x00b2:
        r3[r2] = r1;
        r2 = 14;
        r1 = "\u001dR1\u001ci\u0015r*\u001bh\u001e17\u0001'\u0002t-\u0017sPe1R7Pf6\u0017iP;\u0006p\u001fc5Rk\u0019b*\u0017i\u00199\\'2c;\u0013lP1\u0005)";
        r0 = 13;
        r3 = r4;
        goto L_0x0009;
    L_0x00bd:
        r3[r2] = r1;
        r2 = 15;
        r1 = "3c;\u0013s\u0015u~\u0011h\u001e;\u0011s\u0019~0R*P";
        r0 = 14;
        r3 = r4;
        goto L_0x0009;
    L_0x00c8:
        r3[r2] = r1;
        r2 = 16;
        r1 = "\\1;\u0000u\u001fcd";
        r0 = 15;
        r3 = r4;
        goto L_0x0009;
    L_0x00d3:
        r3[r2] = r1;
        r2 = 17;
        r1 = "\"t*\u0007u\u001e11\u0014'3}1\u0001bP{0\u001b'\u0013~0\u001cb\u0013e7\u001diP<~";
        r0 = 16;
        r3 = r4;
        goto L_0x0009;
    L_0x00de:
        r3[r2] = r1;
        r2 = 18;
        r1 = "1r*\u001bh\u001e1sRd\u001c~-\u0017D\u001f0\u0017d\u0004x1\u001c']1=\u001di\u001et=\u0006n\u001fd";
        r0 = 17;
        r3 = r4;
        goto L_0x0009;
    L_0x00e9:
        r3[r2] = r1;
        r2 = 19;
        r1 = "1r*\u001bh\u001e1sRs\u0002h\r\u0006h\u00001sRd\u001f0\u0017d\u0004x1\u001c=";
        r0 = 18;
        r3 = r4;
        goto L_0x0009;
    L_0x00f4:
        r3[r2] = r1;
        f867z = r4;
        r0 = new java.util.concurrent.atomic.AtomicLong;
        r2 = 0;
        r0.<init>(r2);
        f865a = r0;
        r0 = new java.util.concurrent.atomic.AtomicBoolean;
        r1 = 0;
        r0.<init>(r1);
        f866b = r0;
        return;
    L_0x010a:
        r9 = 112; // 0x70 float:1.57E-43 double:5.53E-322;
        goto L_0x001f;
    L_0x010e:
        r9 = 17;
        goto L_0x001f;
    L_0x0112:
        r9 = 94;
        goto L_0x001f;
    L_0x0116:
        r9 = 114; // 0x72 float:1.6E-43 double:5.63E-322;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.k.<clinit>():void");
    }

    public C0472k(Context context, Handler handler, boolean z) {
        this.f868c = context;
        this.f869d = handler;
        this.f870e = false;
    }

    /* renamed from: c */
    private void m1511c() {
        ac.m1582b(f867z[1], new StringBuilder(f867z[18]).append(f865a.get()).toString());
        if (0 != f865a.get()) {
            try {
                f866b.set(true);
                f865a.set((long) PushProtocol.Close(f865a.get()));
                ac.m1582b(f867z[1], new StringBuilder(f867z[17]).append(f865a.get()).toString());
                f866b.set(false);
            } catch (Exception e) {
                ac.m1592h();
            }
            ConnectingHelper.sendConnectionToHandler(Message.obtain(this.f869d, 7301), f865a.get());
            return;
        }
        ac.m1581b();
    }

    /* renamed from: a */
    public final void m1512a() {
        ac.m1582b(f867z[1], new StringBuilder(f867z[19]).append(f865a.get()).toString());
        this.f871f = true;
        PushProtocol.Stop(f865a.get());
    }

    /* renamed from: b */
    public final boolean m1513b() {
        return this.f871f;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r13 = this;
        r12 = 86400; // 0x15180 float:1.21072E-40 double:4.26873E-319;
        r11 = 10;
        r10 = 2;
        r3 = 0;
        r2 = 1;
        r0 = f867z;
        r0 = r0[r2];
        r1 = new java.lang.StringBuilder;
        r4 = f867z;
        r5 = 4;
        r4 = r4[r5];
        r1.<init>(r4);
        r4 = java.lang.Thread.currentThread();
        r4 = r4.getId();
        r1 = r1.append(r4);
        r1 = r1.toString();
        cn.jpush.android.util.ac.m1585c(r0, r1);
        r0 = f865a;
        r4 = cn.jpush.android.service.PushProtocol.InitConn();
        r0.set(r4);
        r0 = new java.lang.StringBuilder;
        r1 = f867z;
        r4 = 15;
        r1 = r1[r4];
        r0.<init>(r1);
        r1 = f865a;
        r4 = r1.get();
        r0.append(r4);
        cn.jpush.android.util.ac.m1581b();
        r0 = r13.f868c;
        r0 = cn.jpush.android.helpers.ConnectingHelper.sendSis(r0);
        if (r0 == 0) goto L_0x0054;
    L_0x0051:
        r0.configure();
    L_0x0054:
        r1 = r13.f871f;
        if (r1 == 0) goto L_0x005f;
    L_0x0058:
        cn.jpush.android.util.ac.m1586d();
        r13.m1511c();
    L_0x005e:
        return;
    L_0x005f:
        r1 = r13.f868c;
        r4 = f865a;
        r4 = r4.get();
        r0 = cn.jpush.android.helpers.ConnectingHelper.openConnection(r13, r1, r4, r0);
        if (r0 != 0) goto L_0x0071;
    L_0x006d:
        r13.m1511c();
        goto L_0x005e;
    L_0x0071:
        r0 = cn.jpush.android.C0404a.m1145y();
        if (r0 != 0) goto L_0x00a1;
    L_0x0077:
        r0 = r13.f868c;
        r1 = f865a;
        r4 = r1.get();
        r1 = r13.f870e;
        r0 = cn.jpush.android.helpers.ConnectingHelper.register(r0, r4, r1);
        if (r0 != 0) goto L_0x00a1;
    L_0x0087:
        r0 = cn.jpush.android.C0404a.m1136q();
        if (r12 != r0) goto L_0x009d;
    L_0x008d:
        cn.jpush.android.util.ac.m1576a();
        r0 = r13.f869d;
        r1 = 1001; // 0x3e9 float:1.403E-42 double:4.946E-321;
        r2 = 100;
        r0.sendEmptyMessageDelayed(r1, r2);
    L_0x0099:
        r13.m1511c();
        goto L_0x005e;
    L_0x009d:
        cn.jpush.android.util.ac.m1576a();
        goto L_0x0099;
    L_0x00a1:
        r0 = r13.f868c;
        r1 = f865a;
        r4 = r1.get();
        r0 = cn.jpush.android.helpers.ConnectingHelper.login(r0, r4);
        if (r0 >= 0) goto L_0x00b3;
    L_0x00af:
        r13.m1511c();
        goto L_0x005e;
    L_0x00b3:
        if (r0 <= 0) goto L_0x00d9;
    L_0x00b5:
        r1 = f865a;
        r2 = r1.get();
        r1 = new java.lang.StringBuilder;
        r4 = f867z;
        r4 = r4[r10];
        r1.<init>(r4);
        r1.append(r0);
        cn.jpush.android.util.ac.m1576a();
        r0 = r13.f869d;
        r1 = 7306; // 0x1c8a float:1.0238E-41 double:3.6096E-320;
        r0 = android.os.Message.obtain(r0, r1);
        cn.jpush.android.helpers.ConnectingHelper.sendConnectionToHandler(r0, r2);
        r13.m1511c();
        goto L_0x005e;
    L_0x00d9:
        r0 = 0;
        r4 = f865a;
        r4 = r4.get();
        r0 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1));
        if (r0 == 0) goto L_0x0187;
    L_0x00e5:
        r0 = f865a;
        r0 = r0.get();
        r4 = f867z;
        r4 = r4[r2];
        r5 = new java.lang.StringBuilder;
        r6 = f867z;
        r7 = 8;
        r6 = r6[r7];
        r5.<init>(r6);
        r5 = r5.append(r0);
        r5 = r5.toString();
        cn.jpush.android.util.ac.m1578a(r4, r5);
        r4 = r13.f869d;
        r5 = 7304; // 0x1c88 float:1.0235E-41 double:3.6087E-320;
        r4 = android.os.Message.obtain(r4, r5);
        cn.jpush.android.helpers.ConnectingHelper.sendConnectionToHandler(r4, r0);
        r0 = 8192; // 0x2000 float:1.14794E-41 double:4.0474E-320;
        r4 = new byte[r0];
    L_0x0114:
        r0 = r13.f871f;
        if (r0 != 0) goto L_0x02dc;
    L_0x0118:
        r0 = f867z;
        r0 = r0[r2];
        r1 = f867z;
        r5 = 13;
        r1 = r1[r5];
        cn.jpush.android.util.ac.m1582b(r0, r1);
        r0 = f865a;
        r0 = r0.get();
        r0 = cn.jpush.android.service.PushProtocol.RecvPush(r0, r4, r12);
        r1 = f867z;
        r1 = r1[r2];
        r5 = new java.lang.StringBuilder;
        r6 = f867z;
        r7 = 6;
        r6 = r6[r7];
        r5.<init>(r6);
        r5 = r5.append(r0);
        r6 = f867z;
        r7 = 11;
        r6 = r6[r7];
        r5 = r5.append(r6);
        r6 = f865a;
        r6 = r6.get();
        r5 = r5.append(r6);
        r6 = f867z;
        r7 = 12;
        r6 = r6[r7];
        r5 = r5.append(r6);
        r6 = cn.jpush.android.C0448e.f751c;
        r5 = r5.append(r6);
        r5 = r5.toString();
        cn.jpush.android.util.ac.m1582b(r1, r5);
        r6 = 0;
        r1 = f865a;
        r8 = r1.get();
        r1 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1));
        if (r1 != 0) goto L_0x018c;
    L_0x0178:
        r0 = f867z;
        r0 = r0[r2];
        r1 = f867z;
        r2 = 14;
        r1 = r1[r2];
        cn.jpush.android.util.ac.m1587d(r0, r1);
        goto L_0x005e;
    L_0x0187:
        cn.jpush.android.util.ac.m1586d();
        goto L_0x005e;
    L_0x018c:
        if (r0 <= 0) goto L_0x02bb;
    L_0x018e:
        r5 = r13.f868c;
        r6 = new byte[r0];
        java.lang.System.arraycopy(r4, r3, r6, r3, r0);
        r1 = cn.jpush.p005b.p006a.p007a.C0520e.m1847a(r6);
        if (r1 != 0) goto L_0x01a0;
    L_0x019b:
        cn.jpush.android.util.ac.m1588e();
        goto L_0x0114;
    L_0x01a0:
        r0 = f867z;
        r0 = r0[r2];
        r7 = new java.lang.StringBuilder;
        r8 = f867z;
        r9 = 9;
        r8 = r8[r9];
        r7.<init>(r8);
        r8 = r1.m1827e();
        r7 = r7.append(r8);
        r7 = r7.toString();
        cn.jpush.android.util.ac.m1582b(r0, r7);
        r0 = f867z;
        r0 = r0[r2];
        r7 = r1.toString();
        cn.jpush.android.util.ac.m1578a(r0, r7);
        r0 = r1.m1827e();
        switch(r0) {
            case 3: goto L_0x0215;
            case 19: goto L_0x022d;
            case 25: goto L_0x023e;
            case 100: goto L_0x0217;
            default: goto L_0x01d0;
        };
    L_0x01d0:
        r0 = r3;
    L_0x01d1:
        if (r0 != 0) goto L_0x01e4;
    L_0x01d3:
        r0 = r13.f869d;
        r7 = 7302; // 0x1c86 float:1.0232E-41 double:3.6077E-320;
        r0 = android.os.Message.obtain(r0, r7, r1);
        r7 = f865a;
        r8 = r7.get();
        cn.jpush.android.helpers.ConnectingHelper.sendConnectionToHandler(r0, r8);
    L_0x01e4:
        r0 = r1.f1071g;
        if (r0 == 0) goto L_0x0240;
    L_0x01e8:
        r0 = f867z;
        r0 = r0[r2];
        r5 = new java.lang.StringBuilder;
        r6 = f867z;
        r7 = 5;
        r6 = r6[r7];
        r5.<init>(r6);
        r6 = r1.f1071g;
        r5 = r5.append(r6);
        r6 = f867z;
        r7 = 16;
        r6 = r6[r7];
        r5 = r5.append(r6);
        r1 = r1.f1072h;
        r1 = r5.append(r1);
        r1 = r1.toString();
        cn.jpush.android.util.ac.m1587d(r0, r1);
        goto L_0x0114;
    L_0x0215:
        r0 = r2;
        goto L_0x01d1;
    L_0x0217:
        r0 = r1;
        r0 = (cn.jpush.p005b.p006a.p007a.C0519d) r0;
        r0 = r0.mo2234a();
        if (r0 != 0) goto L_0x0224;
    L_0x0220:
        cn.jpush.android.util.ac.m1588e();
        goto L_0x01d0;
    L_0x0224:
        r0 = r0.m1890b();
        r0 = cn.jpush.android.helpers.C0460j.m1419a(r0);
        goto L_0x01d1;
    L_0x022d:
        r0 = r1;
        r0 = (cn.jpush.p005b.p006a.p007a.C0515a) r0;
        r7 = r0.mo2234a();
        if (r7 == r11) goto L_0x023c;
    L_0x0236:
        r0 = r0.mo2234a();
        if (r0 != r10) goto L_0x01d0;
    L_0x023c:
        r0 = r2;
        goto L_0x01d1;
    L_0x023e:
        r0 = r2;
        goto L_0x01d1;
    L_0x0240:
        r0 = r1.m1827e();
        switch(r0) {
            case 3: goto L_0x025c;
            case 10: goto L_0x02b6;
            case 19: goto L_0x0276;
            case 25: goto L_0x0269;
            case 100: goto L_0x02ad;
            default: goto L_0x0247;
        };
    L_0x0247:
        r0 = new java.lang.StringBuilder;
        r5 = f867z;
        r5 = r5[r11];
        r0.<init>(r5);
        r1 = r1.m1827e();
        r0.append(r1);
        cn.jpush.android.util.ac.m1586d();
        goto L_0x0114;
    L_0x025c:
        r0 = r13.f869d;
        r6 = f865a;
        r6 = r6.get();
        cn.jpush.android.helpers.C0458h.m1406a(r5, r0, r6, r1);
        goto L_0x0114;
    L_0x0269:
        r0 = r13.f869d;
        r6 = f865a;
        r6 = r6.get();
        cn.jpush.android.helpers.C0458h.m1409b(r5, r0, r6, r1);
        goto L_0x0114;
    L_0x0276:
        r0 = f865a;
        r6 = r0.get();
        r1 = (cn.jpush.p005b.p006a.p007a.C0515a) r1;
        r0 = r1.mo2234a();
        if (r0 != r10) goto L_0x0294;
    L_0x0284:
        cn.jpush.android.util.ac.m1581b();
        r0 = r13.f869d;
        r1 = 7303; // 0x1c87 float:1.0234E-41 double:3.608E-320;
        r0 = android.os.Message.obtain(r0, r1);
        cn.jpush.android.helpers.ConnectingHelper.sendConnectionToHandler(r0, r6);
        goto L_0x0114;
    L_0x0294:
        if (r0 != r11) goto L_0x029b;
    L_0x0296:
        cn.jpush.android.util.ac.m1581b();
        goto L_0x0114;
    L_0x029b:
        r1 = new java.lang.StringBuilder;
        r5 = f867z;
        r6 = 3;
        r5 = r5[r6];
        r1.<init>(r5);
        r1.append(r0);
        cn.jpush.android.util.ac.m1586d();
        goto L_0x0114;
    L_0x02ad:
        r0 = r13.f868c;
        r5 = r13.f869d;
        cn.jpush.p005b.p006a.p008b.C0546t.m1893a(r0, r5, r1, r6);
        goto L_0x0114;
    L_0x02b6:
        cn.jpush.android.util.ac.m1586d();
        goto L_0x0114;
    L_0x02bb:
        r1 = -994; // 0xfffffffffffffc1e float:NaN double:NaN;
        if (r0 != r1) goto L_0x02c4;
    L_0x02bf:
        cn.jpush.android.util.ac.m1581b();
        goto L_0x0114;
    L_0x02c4:
        r1 = f867z;
        r1 = r1[r2];
        r4 = new java.lang.StringBuilder;
        r5 = f867z;
        r3 = r5[r3];
        r4.<init>(r3);
        r0 = r4.append(r0);
        r0 = r0.toString();
        cn.jpush.android.util.ac.m1582b(r1, r0);
    L_0x02dc:
        r0 = r13.f871f;
        if (r0 == 0) goto L_0x02ff;
    L_0x02e0:
        r0 = f867z;
        r0 = r0[r2];
        r1 = new java.lang.StringBuilder;
        r2 = f867z;
        r3 = 7;
        r2 = r2[r3];
        r1.<init>(r2);
        r2 = f865a;
        r2 = r2.get();
        r1 = r1.append(r2);
        r1 = r1.toString();
        cn.jpush.android.util.ac.m1582b(r0, r1);
    L_0x02ff:
        r13.m1511c();
        goto L_0x005e;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.k.run():void");
    }
}
