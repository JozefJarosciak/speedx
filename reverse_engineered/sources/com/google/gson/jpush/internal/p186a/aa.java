package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.al;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3979c;
import com.google.gson.jpush.p185b.C3980d;

/* renamed from: com.google.gson.jpush.internal.a.aa */
final class aa extends al<Class> {
    /* renamed from: z */
    private static final String[] f14355z;

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
        r5 = 2;
        r2 = 1;
        r1 = 0;
        r0 = 3;
        r4 = new java.lang.String[r0];
        r3 = ",A+G\u0005e\u000e\u0019\b\u0003mA\u001fM\u0010k\u0012\u0019M\u0005\"\u0000M\\\u000er\u0004MI\u0013c\u0011\u0019M\u0005=";
        r0 = -1;
        r6 = r4;
        r7 = r4;
        r4 = r1;
    L_0x000c:
        r3 = r3.toCharArray();
        r8 = r3.length;
        if (r8 > r2) goto L_0x0060;
    L_0x0013:
        r9 = r1;
    L_0x0014:
        r10 = r3;
        r11 = r9;
        r14 = r8;
        r8 = r3;
        r3 = r14;
    L_0x0019:
        r13 = r8[r9];
        r12 = r11 % 5;
        switch(r12) {
            case 0: goto L_0x0055;
            case 1: goto L_0x0057;
            case 2: goto L_0x005a;
            case 3: goto L_0x005d;
            default: goto L_0x0020;
        };
    L_0x0020:
        r12 = 119; // 0x77 float:1.67E-43 double:5.9E-322;
    L_0x0022:
        r12 = r12 ^ r13;
        r12 = (char) r12;
        r8[r9] = r12;
        r9 = r11 + 1;
        if (r3 != 0) goto L_0x002e;
    L_0x002a:
        r8 = r10;
        r11 = r9;
        r9 = r3;
        goto L_0x0019;
    L_0x002e:
        r8 = r3;
        r3 = r10;
    L_0x0030:
        if (r8 > r9) goto L_0x0014;
    L_0x0032:
        r8 = new java.lang.String;
        r8.<init>(r3);
        r3 = r8.intern();
        switch(r0) {
            case 0: goto L_0x0047;
            case 1: goto L_0x0050;
            default: goto L_0x003e;
        };
    L_0x003e:
        r6[r4] = r3;
        r0 = "C\u0015\u0019M\u001ar\u0015\bLWv\u000eM[\u0012p\b\fD\u001ex\u0004MB\u0016t\u0000CD\u0016l\u0006Ck\u001bc\u0012\u001e\u0012W";
        r3 = r0;
        r4 = r2;
        r6 = r7;
        r0 = r1;
        goto L_0x000c;
    L_0x0047:
        r6[r4] = r3;
        r0 = "C\u0015\u0019M\u001ar\u0015\bLWv\u000eML\u0012q\u0004\u001fA\u0016n\b\u0017MWcA\u0007I\u0001cO\u0001I\u0019eO.D\u0016q\u0012C\b1m\u0013\nG\u0003\"\u0015\u0002\b\u0005g\u0006\u0004[\u0003g\u0013MIWv\u0018\u001dMWc\u0005\fX\u0003g\u0013R";
        r3 = r0;
        r4 = r5;
        r6 = r7;
        r0 = r2;
        goto L_0x000c;
    L_0x0050:
        r6[r4] = r3;
        f14355z = r7;
        return;
    L_0x0055:
        r12 = r5;
        goto L_0x0022;
    L_0x0057:
        r12 = 97;
        goto L_0x0022;
    L_0x005a:
        r12 = 109; // 0x6d float:1.53E-43 double:5.4E-322;
        goto L_0x0022;
    L_0x005d:
        r12 = 40;
        goto L_0x0022;
    L_0x0060:
        r9 = r1;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.internal.a.aa.<clinit>():void");
    }

    aa() {
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo5672a(C3976a c3976a) {
        if (c3976a.mo5683f() == C3979c.f14328i) {
            c3976a.mo5687j();
            return null;
        }
        throw new UnsupportedOperationException(f14355z[2]);
    }

    /* renamed from: a */
    public final /* synthetic */ void mo5673a(C3980d c3980d, Object obj) {
        Class cls = (Class) obj;
        if (cls == null) {
            c3980d.mo5705f();
            return;
        }
        throw new UnsupportedOperationException(new StringBuilder(f14355z[1]).append(cls.getName()).append(f14355z[0]).toString());
    }
}
