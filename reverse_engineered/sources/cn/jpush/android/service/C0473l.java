package cn.jpush.android.service;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Build.VERSION;
import cn.jpush.android.C0404a;
import cn.jpush.android.util.C0490b;
import cn.jpush.android.util.ac;
import java.util.List;

/* renamed from: cn.jpush.android.service.l */
final class C0473l implements Runnable {
    /* renamed from: z */
    private static final String[] f872z;
    /* renamed from: a */
    final /* synthetic */ long f873a;
    /* renamed from: b */
    final /* synthetic */ PushService f874b;

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
        r5 = ".$Ho\u0016\"Fk3okGo`6\u0007J{}!#g~'k\u0016.";
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
        r13 = 19;
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
        r0 = "6\"Fk3,$\\4";
        r5 = r0;
        r6 = r2;
        r7 = r8;
        r0 = r1;
        goto L_0x000d;
    L_0x0048:
        r7[r6] = r5;
        r0 = "\u0012>Xf@'9]gp'";
        r5 = r0;
        r6 = r3;
        r7 = r8;
        r0 = r2;
        goto L_0x000d;
    L_0x0051:
        r7[r6] = r5;
        r0 = "\u0001*E)gb8_oa6kDz{'9\u000b~f1#\u000b}v0=Bmv1kO{|b?D.`'(^|z62\n";
        r5 = r0;
        r6 = r4;
        r7 = r8;
        r0 = r3;
        goto L_0x000d;
    L_0x005a:
        r7[r6] = r5;
        r5 = 4;
        r0 = "nkGo`6kGof,(Ckwb?Bcvx";
        r6 = r5;
        r7 = r8;
        r5 = r0;
        r0 = r4;
        goto L_0x000d;
    L_0x0064:
        r7[r6] = r5;
        f872z = r8;
        return;
    L_0x0069:
        r13 = 66;
        goto L_0x0023;
    L_0x006c:
        r13 = 75;
        goto L_0x0023;
    L_0x006f:
        r13 = 43;
        goto L_0x0023;
    L_0x0072:
        r13 = 14;
        goto L_0x0023;
    L_0x0075:
        r10 = r1;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.service.l.<clinit>():void");
    }

    C0473l(PushService pushService, long j) {
        this.f874b = pushService;
        this.f873a = j;
    }

    public final void run() {
        int i = 0;
        try {
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            long w = C0404a.m1143w();
            new StringBuilder(f872z[1]).append(currentTimeMillis).append(f872z[4]).append(w);
            ac.m1581b();
            if (-1 == w || Math.abs(currentTimeMillis - w) > this.f873a) {
                List t = C0490b.m1737t(this.f874b.getApplicationContext());
                C0404a.m1075b(currentTimeMillis);
                int size = t != null ? t.size() : 0;
                while (i < size) {
                    Intent intent = new Intent();
                    intent.setComponent((ComponentName) t.get(i));
                    if (VERSION.SDK_INT >= 12) {
                        intent.setFlags(32);
                    }
                    this.f874b.startService(intent);
                    i++;
                }
                return;
            }
            new StringBuilder(f872z[0]).append(currentTimeMillis - w);
            ac.m1576a();
        } catch (SecurityException e) {
            ac.m1587d(f872z[2], f872z[3]);
            e.printStackTrace();
        }
    }
}
