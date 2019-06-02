package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.C1483y;
import com.google.gson.jpush.C3975w;
import com.google.gson.jpush.C4052t;
import com.google.gson.jpush.C4054z;
import com.google.gson.jpush.ac;
import com.google.gson.jpush.p185b.C3980d;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/* renamed from: com.google.gson.jpush.internal.a.j */
public final class C4000j extends C3980d {
    /* renamed from: a */
    private static final Writer f14397a = new C4001k();
    /* renamed from: b */
    private static final ac f14398b = new ac(f14399z[3]);
    /* renamed from: z */
    private static final String[] f14399z;
    /* renamed from: c */
    private final List<C3975w> f14400c = new ArrayList();
    /* renamed from: d */
    private String f14401d;
    /* renamed from: e */
    private C3975w f14402e = C1483y.f7007a;

    static {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1723278948.run(Unknown Source)
*/
        /*
        r6 = 3;
        r3 = 2;
        r2 = 1;
        r1 = 0;
        r0 = 4;
        r5 = new java.lang.String[r0];
        r4 = "?x\fUz\u0013D1y3\u0011XcU;;\u000b\"u>UB-}3\u001bB7r?\u0006\u0011c";
        r0 = -1;
        r7 = r5;
        r8 = r5;
        r5 = r1;
    L_0x000d:
        r4 = r4.toCharArray();
        r9 = r4.length;
        if (r9 > r2) goto L_0x007d;
    L_0x0014:
        r10 = r1;
    L_0x0015:
        r11 = r4;
        r12 = r10;
        r15 = r9;
        r9 = r4;
        r4 = r15;
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
        r13 = 90;
    L_0x0023:
        r13 = r13 ^ r14;
        r13 = (char) r13;
        r9[r10] = r13;
        r10 = r12 + 1;
        if (r4 != 0) goto L_0x002f;
    L_0x002b:
        r9 = r11;
        r12 = r10;
        r10 = r4;
        goto L_0x001a;
    L_0x002f:
        r9 = r4;
        r4 = r11;
    L_0x0031:
        if (r9 > r10) goto L_0x0015;
    L_0x0033:
        r9 = new java.lang.String;
        r9.<init>(r4);
        r4 = r9.intern();
        switch(r0) {
            case 0: goto L_0x0048;
            case 1: goto L_0x0051;
            case 2: goto L_0x005a;
            default: goto L_0x003f;
        };
    L_0x003f:
        r7[r5] = r4;
        r0 = "0S3~9\u0001N';5\u001bNcQ\t:ec~6\u0010F&u.UI6oz\u0002J0;";
        r4 = r0;
        r5 = r2;
        r7 = r8;
        r0 = r1;
        goto L_0x000d;
    L_0x0048:
        r7[r5] = r4;
        r0 = "<E t7\u0005G&o?UO,x/\u0018N-o";
        r4 = r0;
        r5 = r3;
        r7 = r8;
        r0 = r2;
        goto L_0x000d;
    L_0x0051:
        r7[r5] = r4;
        r0 = "\u0016G,h?\u0011";
        r4 = r0;
        r5 = r6;
        r7 = r8;
        r0 = r3;
        goto L_0x000d;
    L_0x005a:
        r7[r5] = r4;
        f14399z = r8;
        r0 = new com.google.gson.jpush.internal.a.k;
        r0.<init>();
        f14397a = r0;
        r0 = new com.google.gson.jpush.ac;
        r1 = f14399z;
        r1 = r1[r6];
        r0.<init>(r1);
        f14398b = r0;
        return;
    L_0x0071:
        r13 = 117; // 0x75 float:1.64E-43 double:5.8E-322;
        goto L_0x0023;
    L_0x0074:
        r13 = 43;
        goto L_0x0023;
    L_0x0077:
        r13 = 67;
        goto L_0x0023;
    L_0x007a:
        r13 = 27;
        goto L_0x0023;
    L_0x007d:
        r10 = r1;
        goto L_0x0031;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.internal.a.j.<clinit>():void");
    }

    public C4000j() {
        super(f14397a);
    }

    /* renamed from: a */
    private void m16271a(C3975w c3975w) {
        if (this.f14401d != null) {
            if (!(c3975w instanceof C1483y) || m16161i()) {
                ((C4054z) m16272j()).m16408a(this.f14401d, c3975w);
            }
            this.f14401d = null;
        } else if (this.f14400c.isEmpty()) {
            this.f14402e = c3975w;
        } else {
            C3975w j = m16272j();
            if (j instanceof C4052t) {
                ((C4052t) j).m16400a(c3975w);
                return;
            }
            throw new IllegalStateException();
        }
    }

    /* renamed from: j */
    private C3975w m16272j() {
        return (C3975w) this.f14400c.get(this.f14400c.size() - 1);
    }

    /* renamed from: a */
    public final C3980d mo5694a(long j) {
        m16271a(new ac(Long.valueOf(j)));
        return this;
    }

    /* renamed from: a */
    public final C3980d mo5695a(Number number) {
        if (number == null) {
            return mo5705f();
        }
        if (!m16159g()) {
            double doubleValue = number.doubleValue();
            if (Double.isNaN(doubleValue) || Double.isInfinite(doubleValue)) {
                throw new IllegalArgumentException(new StringBuilder(f14399z[0]).append(number).toString());
            }
        }
        m16271a(new ac(number));
        return this;
    }

    /* renamed from: a */
    public final C3980d mo5696a(String str) {
        if (this.f14400c.isEmpty() || this.f14401d != null) {
            throw new IllegalStateException();
        } else if (m16272j() instanceof C4054z) {
            this.f14401d = str;
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    /* renamed from: a */
    public final C3980d mo5697a(boolean z) {
        m16271a(new ac(Boolean.valueOf(z)));
        return this;
    }

    /* renamed from: a */
    public final C3975w mo5698a() {
        if (this.f14400c.isEmpty()) {
            return this.f14402e;
        }
        throw new IllegalStateException(new StringBuilder(f14399z[1]).append(this.f14400c).toString());
    }

    /* renamed from: b */
    public final C3980d mo5699b() {
        C3975w c4052t = new C4052t();
        m16271a(c4052t);
        this.f14400c.add(c4052t);
        return this;
    }

    /* renamed from: b */
    public final C3980d mo5700b(String str) {
        if (str == null) {
            return mo5705f();
        }
        m16271a(new ac(str));
        return this;
    }

    /* renamed from: c */
    public final C3980d mo5701c() {
        if (this.f14400c.isEmpty() || this.f14401d != null) {
            throw new IllegalStateException();
        } else if (m16272j() instanceof C4052t) {
            this.f14400c.remove(this.f14400c.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    public final void close() {
        if (this.f14400c.isEmpty()) {
            this.f14400c.add(f14398b);
            return;
        }
        throw new IOException(f14399z[2]);
    }

    /* renamed from: d */
    public final C3980d mo5703d() {
        C3975w c4054z = new C4054z();
        m16271a(c4054z);
        this.f14400c.add(c4054z);
        return this;
    }

    /* renamed from: e */
    public final C3980d mo5704e() {
        if (this.f14400c.isEmpty() || this.f14401d != null) {
            throw new IllegalStateException();
        } else if (m16272j() instanceof C4054z) {
            this.f14400c.remove(this.f14400c.size() - 1);
            return this;
        } else {
            throw new IllegalStateException();
        }
    }

    /* renamed from: f */
    public final C3980d mo5705f() {
        m16271a(C1483y.f7007a);
        return this;
    }

    public final void flush() {
    }
}
