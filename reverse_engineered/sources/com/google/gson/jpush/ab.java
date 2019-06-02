package com.google.gson.jpush;

import com.google.gson.jpush.internal.ag;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3979c;
import java.io.Reader;
import java.io.StringReader;

public final class ab {
    /* renamed from: z */
    private static final String[] f14282z;

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
        r12 = 32;
        r2 = 1;
        r1 = 0;
        r0 = 3;
        r4 = new java.lang.String[r0];
        r3 = "f#rL\bDbkA\u001fS+uGMj\u0011TnMS-nR\u000eEx;";
        r0 = -1;
        r5 = r4;
        r6 = r4;
        r4 = r1;
    L_0x000d:
        r3 = r3.toCharArray();
        r7 = r3.length;
        if (r7 > r2) goto L_0x0061;
    L_0x0014:
        r8 = r1;
    L_0x0015:
        r9 = r3;
        r10 = r8;
        r14 = r7;
        r7 = r3;
        r3 = r14;
    L_0x001a:
        r13 = r7[r8];
        r11 = r10 % 5;
        switch(r11) {
            case 0: goto L_0x0057;
            case 1: goto L_0x0059;
            case 2: goto L_0x005c;
            case 3: goto L_0x005f;
            default: goto L_0x0021;
        };
    L_0x0021:
        r11 = 109; // 0x6d float:1.53E-43 double:5.4E-322;
    L_0x0023:
        r11 = r11 ^ r13;
        r11 = (char) r11;
        r7[r8] = r11;
        r8 = r10 + 1;
        if (r3 != 0) goto L_0x002f;
    L_0x002b:
        r7 = r9;
        r10 = r8;
        r8 = r3;
        goto L_0x001a;
    L_0x002f:
        r7 = r3;
        r3 = r9;
    L_0x0031:
        if (r7 > r8) goto L_0x0015;
    L_0x0033:
        r7 = new java.lang.String;
        r7.<init>(r3);
        r3 = r7.intern();
        switch(r0) {
            case 0: goto L_0x0048;
            case 1: goto L_0x0052;
            default: goto L_0x003f;
        };
    L_0x003f:
        r5[r4] = r3;
        r0 = "\u00006t\u0000'S-u";
        r3 = r0;
        r4 = r2;
        r5 = r6;
        r0 = r1;
        goto L_0x000d;
    L_0x0048:
        r5[r4] = r3;
        r3 = 2;
        r0 = "d+\u0000\u0003O6;C\u0002N1nM\b\u00006sEME,oI\u001fEbO\u000eU/~N\u0019\u000e";
        r4 = r3;
        r5 = r6;
        r3 = r0;
        r0 = r2;
        goto L_0x000d;
    L_0x0052:
        r5[r4] = r3;
        f14282z = r6;
        return;
    L_0x0057:
        r11 = r12;
        goto L_0x0023;
    L_0x0059:
        r11 = 66;
        goto L_0x0023;
    L_0x005c:
        r11 = 27;
        goto L_0x0023;
    L_0x005f:
        r11 = r12;
        goto L_0x0023;
    L_0x0061:
        r8 = r1;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.ab.<clinit>():void");
    }

    /* renamed from: a */
    private static C3975w m16065a(C3976a c3976a) {
        boolean p = c3976a.m16132p();
        c3976a.m16118a(true);
        try {
            C3975w a = ag.m16326a(c3976a);
            c3976a.m16118a(p);
            return a;
        } catch (Throwable e) {
            throw new aa(new StringBuilder(f14282z[0]).append(c3976a).append(f14282z[1]).toString(), e);
        } catch (Throwable e2) {
            throw new aa(new StringBuilder(f14282z[0]).append(c3976a).append(f14282z[1]).toString(), e2);
        } catch (Throwable th) {
            c3976a.m16118a(p);
        }
    }

    /* renamed from: a */
    private C3975w m16066a(Reader reader) {
        try {
            C3976a c3976a = new C3976a(reader);
            C3975w a = m16065a(c3976a);
            if ((a instanceof C1483y) || c3976a.mo5683f() == C3979c.f14329j) {
                return a;
            }
            throw new af(f14282z[2]);
        } catch (Throwable e) {
            throw new af(e);
        } catch (Throwable e2) {
            throw new C4053x(e2);
        } catch (Throwable e22) {
            throw new af(e22);
        }
    }

    /* renamed from: a */
    public final C3975w m16067a(String str) {
        return m16066a(new StringReader(str));
    }
}
