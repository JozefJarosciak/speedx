package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.af;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3979c;
import com.google.gson.jpush.p185b.C3980d;
import java.util.BitSet;

/* renamed from: com.google.gson.jpush.internal.a.al */
final class al extends com.google.gson.jpush.al<BitSet> {
    /* renamed from: z */
    private static final String[] f14360z;

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
        r4 = 1;
        r1 = 0;
        r0 = 2;
        r3 = new java.lang.String[r0];
        r2 = "p-AL2P'\u0017O7M0RY~O\"[X;\u00197N];\u0003c";
        r0 = -1;
        r5 = r3;
        r6 = r3;
        r3 = r1;
    L_0x000b:
        r2 = r2.toCharArray();
        r7 = r2.length;
        if (r7 > r4) goto L_0x0057;
    L_0x0012:
        r8 = r1;
    L_0x0013:
        r9 = r2;
        r10 = r8;
        r13 = r7;
        r7 = r2;
        r2 = r13;
    L_0x0018:
        r12 = r7[r8];
        r11 = r10 % 5;
        switch(r11) {
            case 0: goto L_0x004b;
            case 1: goto L_0x004e;
            case 2: goto L_0x0051;
            case 3: goto L_0x0054;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 94;
    L_0x0021:
        r11 = r11 ^ r12;
        r11 = (char) r11;
        r7[r8] = r11;
        r8 = r10 + 1;
        if (r2 != 0) goto L_0x002d;
    L_0x0029:
        r7 = r9;
        r10 = r8;
        r8 = r2;
        goto L_0x0018;
    L_0x002d:
        r7 = r2;
        r2 = r9;
    L_0x002f:
        if (r7 > r8) goto L_0x0013;
    L_0x0031:
        r7 = new java.lang.String;
        r7.<init>(r2);
        r2 = r7.intern();
        switch(r0) {
            case 0: goto L_0x0046;
            default: goto L_0x003d;
        };
    L_0x003d:
        r5[r3] = r2;
        r0 = "|1EB,\u0003crU.\\ CD0^y\u0017O7M0RY~W6ZO;KcAL2L&\u0017\u0005o\u0015c\u0007\u0004r\u0019\u0005XX0]y\u0017";
        r2 = r0;
        r3 = r4;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r3] = r2;
        f14360z = r6;
        return;
    L_0x004b:
        r11 = 57;
        goto L_0x0021;
    L_0x004e:
        r11 = 67;
        goto L_0x0021;
    L_0x0051:
        r11 = 55;
        goto L_0x0021;
    L_0x0054:
        r11 = 45;
        goto L_0x0021;
    L_0x0057:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.internal.a.al.<clinit>():void");
    }

    al() {
    }

    /* renamed from: b */
    private static BitSet m16199b(C3976a c3976a) {
        if (c3976a.mo5683f() == C3979c.f14328i) {
            c3976a.mo5687j();
            return null;
        }
        BitSet bitSet = new BitSet();
        c3976a.mo5677a();
        C3979c f = c3976a.mo5683f();
        int i = 0;
        while (f != C3979c.f14321b) {
            boolean z;
            switch (az.f14379a[f.ordinal()]) {
                case 1:
                    if (c3976a.mo5690m() == 0) {
                        z = false;
                        break;
                    }
                    z = true;
                    break;
                case 2:
                    z = c3976a.mo5686i();
                    break;
                case 3:
                    String h = c3976a.mo5685h();
                    try {
                        if (Integer.parseInt(h) == 0) {
                            z = false;
                            break;
                        }
                        z = true;
                        break;
                    } catch (NumberFormatException e) {
                        throw new af(new StringBuilder(f14360z[1]).append(h).toString());
                    }
                default:
                    throw new af(new StringBuilder(f14360z[0]).append(f).toString());
            }
            if (z) {
                bitSet.set(i);
            }
            i++;
            f = c3976a.mo5683f();
        }
        c3976a.mo5678b();
        return bitSet;
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo5672a(C3976a c3976a) {
        return al.m16199b(c3976a);
    }

    /* renamed from: a */
    public final /* synthetic */ void mo5673a(C3980d c3980d, Object obj) {
        BitSet bitSet = (BitSet) obj;
        if (bitSet == null) {
            c3980d.mo5705f();
            return;
        }
        c3980d.mo5699b();
        for (int i = 0; i < bitSet.length(); i++) {
            c3980d.mo5694a((long) (bitSet.get(i) ? 1 : 0));
        }
        c3980d.mo5701c();
    }
}
