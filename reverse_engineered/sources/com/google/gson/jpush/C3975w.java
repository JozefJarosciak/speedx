package com.google.gson.jpush;

import com.google.gson.jpush.internal.ag;
import com.google.gson.jpush.p185b.C3980d;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

/* renamed from: com.google.gson.jpush.w */
public abstract class C3975w {
    /* renamed from: z */
    private static final String[] f14283z;

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
        r2 = 1;
        r1 = 0;
        r0 = 3;
        r4 = new java.lang.String[r0];
        r3 = "\u000e|N^?3g\u0007Cp.4F\rU\t[i\r^(fFT1";
        r0 = -1;
        r5 = r4;
        r6 = r4;
        r4 = r1;
    L_0x000b:
        r3 = r3.toCharArray();
        r7 = r3.length;
        if (r7 > r2) goto L_0x0061;
    L_0x0012:
        r8 = r1;
    L_0x0013:
        r9 = r3;
        r10 = r8;
        r13 = r7;
        r7 = r3;
        r3 = r13;
    L_0x0018:
        r12 = r7[r8];
        r11 = r10 % 5;
        switch(r11) {
            case 0: goto L_0x0055;
            case 1: goto L_0x0058;
            case 2: goto L_0x005b;
            case 3: goto L_0x005e;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 31;
    L_0x0021:
        r11 = r11 ^ r12;
        r11 = (char) r11;
        r7[r8] = r11;
        r8 = r10 + 1;
        if (r3 != 0) goto L_0x002d;
    L_0x0029:
        r7 = r9;
        r10 = r8;
        r8 = r3;
        goto L_0x0018;
    L_0x002d:
        r7 = r3;
        r3 = r9;
    L_0x002f:
        if (r7 > r8) goto L_0x0013;
    L_0x0031:
        r7 = new java.lang.String;
        r7.<init>(r3);
        r3 = r7.intern();
        switch(r0) {
            case 0: goto L_0x0046;
            case 1: goto L_0x0050;
            default: goto L_0x003d;
        };
    L_0x003d:
        r5[r4] = r3;
        r0 = "\u000e|N^?3g\u0007Cp.4F\rU\t[i\rO(}JDk3bB\u0003";
        r3 = r0;
        r4 = r2;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r4] = r3;
        r3 = 2;
        r0 = "\u0014{S\r~z^tbQz[EGz9`\u001d\r";
        r4 = r3;
        r5 = r6;
        r3 = r0;
        r0 = r2;
        goto L_0x000b;
    L_0x0050:
        r5[r4] = r3;
        f14283z = r6;
        return;
    L_0x0055:
        r11 = 90;
        goto L_0x0021;
    L_0x0058:
        r11 = 20;
        goto L_0x0021;
    L_0x005b:
        r11 = 39;
        goto L_0x0021;
    L_0x005e:
        r11 = 45;
        goto L_0x0021;
    L_0x0061:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.w.<clinit>():void");
    }

    /* renamed from: b */
    public Number mo5666b() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: c */
    public String mo5667c() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: d */
    public double mo5668d() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: e */
    public long mo5669e() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: f */
    public int mo5670f() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: g */
    public boolean mo5671g() {
        throw new UnsupportedOperationException(getClass().getSimpleName());
    }

    /* renamed from: h */
    public final C4054z m16074h() {
        if (this instanceof C4054z) {
            return (C4054z) this;
        }
        throw new IllegalStateException(new StringBuilder(f14283z[2]).append(this).toString());
    }

    /* renamed from: i */
    public final C4052t m16075i() {
        if (this instanceof C4052t) {
            return (C4052t) this;
        }
        throw new IllegalStateException(f14283z[0]);
    }

    /* renamed from: j */
    public final ac m16076j() {
        if (this instanceof ac) {
            return (ac) this;
        }
        throw new IllegalStateException(f14283z[1]);
    }

    public String toString() {
        try {
            Writer stringWriter = new StringWriter();
            C3980d c3980d = new C3980d(stringWriter);
            c3980d.m16151b(true);
            ag.m16328a(this, c3980d);
            return stringWriter.toString();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }
}
