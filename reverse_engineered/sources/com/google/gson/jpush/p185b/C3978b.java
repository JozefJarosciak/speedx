package com.google.gson.jpush.p185b;

import com.google.gson.jpush.internal.C3977u;
import com.google.gson.jpush.internal.p186a.C3998h;

/* renamed from: com.google.gson.jpush.b.b */
final class C3978b extends C3977u {
    /* renamed from: z */
    private static final String[] f14319z;

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
        r3 = 2;
        r2 = 1;
        r1 = 0;
        r0 = 4;
        r5 = new java.lang.String[r0];
        r4 = "tOz\u001f8\u0006un\u001f";
        r0 = -1;
        r6 = r5;
        r7 = r5;
        r5 = r1;
    L_0x000c:
        r4 = r4.toCharArray();
        r8 = r4.length;
        if (r8 > r2) goto L_0x006b;
    L_0x0013:
        r9 = r1;
    L_0x0014:
        r10 = r4;
        r11 = r9;
        r14 = r8;
        r8 = r4;
        r4 = r14;
    L_0x0019:
        r13 = r8[r9];
        r12 = r11 % 5;
        switch(r12) {
            case 0: goto L_0x005f;
            case 1: goto L_0x0062;
            case 2: goto L_0x0065;
            case 3: goto L_0x0068;
            default: goto L_0x0020;
        };
    L_0x0020:
        r12 = 63;
    L_0x0022:
        r12 = r12 ^ r13;
        r12 = (char) r12;
        r8[r9] = r12;
        r9 = r11 + 1;
        if (r4 != 0) goto L_0x002e;
    L_0x002a:
        r8 = r10;
        r11 = r9;
        r9 = r4;
        goto L_0x0019;
    L_0x002e:
        r8 = r4;
        r4 = r10;
    L_0x0030:
        if (r8 > r9) goto L_0x0014;
    L_0x0032:
        r8 = new java.lang.String;
        r8.<init>(r4);
        r4 = r8.intern();
        switch(r0) {
            case 0: goto L_0x0047;
            case 1: goto L_0x0050;
            case 2: goto L_0x005a;
            default: goto L_0x003e;
        };
    L_0x003e:
        r6[r5] = r4;
        r0 = "\u0011\u0017kn\\ \n+^t\u0001zfZt\rn\u001f#\u000eh+";
        r4 = r0;
        r5 = r2;
        r6 = r7;
        r0 = r1;
        goto L_0x000c;
    L_0x0047:
        r6[r5] = r4;
        r0 = "t\ftgJ9\u0001;";
        r4 = r0;
        r5 = r3;
        r6 = r7;
        r0 = r2;
        goto L_0x000c;
    L_0x0050:
        r6[r5] = r4;
        r4 = 3;
        r0 = "t\u001fzWt";
        r5 = r4;
        r6 = r7;
        r4 = r0;
        r0 = r3;
        goto L_0x000c;
    L_0x005a:
        r6[r5] = r4;
        f14319z = r7;
        return;
    L_0x005f:
        r12 = 84;
        goto L_0x0022;
    L_0x0062:
        r12 = 111; // 0x6f float:1.56E-43 double:5.5E-322;
        goto L_0x0022;
    L_0x0065:
        r12 = 27;
        goto L_0x0022;
    L_0x0068:
        r12 = 11;
        goto L_0x0022;
    L_0x006b:
        r9 = r1;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.b.b.<clinit>():void");
    }

    C3978b() {
    }

    /* renamed from: a */
    public final void mo5675a(C3976a c3976a) {
        if (c3976a instanceof C3998h) {
            ((C3998h) c3976a).mo5692o();
            return;
        }
        int a = c3976a.f14310i;
        if (a == 0) {
            a = c3976a.mo5692o();
        }
        if (a == 13) {
            c3976a.f14310i = 9;
        } else if (a == 12) {
            c3976a.f14310i = 8;
        } else if (a == 14) {
            c3976a.f14310i = 10;
        } else {
            throw new IllegalStateException(new StringBuilder(f14319z[1]).append(c3976a.mo5683f()).append(f14319z[0]).append((c3976a.f14308g + 1)).append(f14319z[2]).append(c3976a.m16113u()).append(f14319z[3]).append(c3976a.m16133q()).toString());
        }
    }
}
