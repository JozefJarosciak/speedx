package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.C1483y;
import com.google.gson.jpush.C4052t;
import com.google.gson.jpush.C4054z;
import com.google.gson.jpush.ac;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3979c;
import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

/* renamed from: com.google.gson.jpush.internal.a.h */
public final class C3998h extends C3976a {
    /* renamed from: a */
    private static final Reader f14393a = new C3999i();
    /* renamed from: b */
    private static final Object f14394b = new Object();
    /* renamed from: z */
    private static final String[] f14395z;
    /* renamed from: c */
    private final List<Object> f14396c;

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
        r3 = 2;
        r2 = 1;
        r1 = 0;
        r0 = 4;
        r5 = new java.lang.String[r0];
        r4 = "eDS6:Ixn\u001asKd<6{a7}\u0016~\u000f~r\u001esA~h\u0011\\-<";
        r0 = -1;
        r6 = r5;
        r7 = r5;
        r5 = r1;
    L_0x000c:
        r4 = r4.toCharArray();
        r8 = r4.length;
        if (r8 > r2) goto L_0x0079;
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
            case 0: goto L_0x006d;
            case 1: goto L_0x0070;
            case 2: goto L_0x0073;
            case 3: goto L_0x0076;
            default: goto L_0x0020;
        };
    L_0x0020:
        r12 = 26;
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
        r0 = "jol\u001dy[rxX";
        r4 = r0;
        r5 = r2;
        r6 = r7;
        r0 = r1;
        goto L_0x000c;
    L_0x0047:
        r6[r5] = r4;
        r0 = "\u000fui\f:XvoX";
        r4 = r0;
        r5 = r3;
        r6 = r7;
        r0 = r2;
        goto L_0x000c;
    L_0x0050:
        r6[r5] = r4;
        r4 = 3;
        r0 = "eds\u0016HJvx\u001dh\u000f~oXyCxo\u001d~";
        r5 = r4;
        r6 = r7;
        r4 = r0;
        r0 = r3;
        goto L_0x000c;
    L_0x005a:
        r6[r5] = r4;
        f14395z = r7;
        r0 = new com.google.gson.jpush.internal.a.i;
        r0.<init>();
        f14393a = r0;
        r0 = new java.lang.Object;
        r0.<init>();
        f14394b = r0;
        return;
    L_0x006d:
        r12 = 47;
        goto L_0x0022;
    L_0x0070:
        r12 = 23;
        goto L_0x0022;
    L_0x0073:
        r12 = 28;
        goto L_0x0022;
    L_0x0076:
        r12 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        goto L_0x0022;
    L_0x0079:
        r9 = r1;
        goto L_0x0030;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.internal.a.h.<clinit>():void");
    }

    /* renamed from: a */
    private void m16253a(C3979c c3979c) {
        if (mo5683f() != c3979c) {
            throw new IllegalStateException(new StringBuilder(f14395z[1]).append(c3979c).append(f14395z[2]).append(mo5683f()).toString());
        }
    }

    /* renamed from: r */
    private Object m16254r() {
        return this.f14396c.get(this.f14396c.size() - 1);
    }

    /* renamed from: s */
    private Object m16255s() {
        return this.f14396c.remove(this.f14396c.size() - 1);
    }

    /* renamed from: a */
    public final void mo5677a() {
        m16253a(C3979c.f14320a);
        this.f14396c.add(((C4052t) m16254r()).iterator());
    }

    /* renamed from: b */
    public final void mo5678b() {
        m16253a(C3979c.f14321b);
        m16255s();
        m16255s();
    }

    /* renamed from: c */
    public final void mo5679c() {
        m16253a(C3979c.f14322c);
        this.f14396c.add(((C4054z) m16254r()).m16407a().iterator());
    }

    public final void close() {
        this.f14396c.clear();
        this.f14396c.add(f14394b);
    }

    /* renamed from: d */
    public final void mo5681d() {
        m16253a(C3979c.f14323d);
        m16255s();
        m16255s();
    }

    /* renamed from: e */
    public final boolean mo5682e() {
        C3979c f = mo5683f();
        return (f == C3979c.f14323d || f == C3979c.f14321b) ? false : true;
    }

    /* renamed from: f */
    public final C3979c mo5683f() {
        while (!this.f14396c.isEmpty()) {
            Object r = m16254r();
            if (r instanceof Iterator) {
                boolean z = this.f14396c.get(this.f14396c.size() - 2) instanceof C4054z;
                Iterator it = (Iterator) r;
                if (!it.hasNext()) {
                    return z ? C3979c.f14323d : C3979c.f14321b;
                } else {
                    if (z) {
                        return C3979c.f14324e;
                    }
                    this.f14396c.add(it.next());
                }
            } else if (r instanceof C4054z) {
                return C3979c.f14322c;
            } else {
                if (r instanceof C4052t) {
                    return C3979c.f14320a;
                }
                if (r instanceof ac) {
                    ac acVar = (ac) r;
                    if (acVar.m16087l()) {
                        return C3979c.f14325f;
                    }
                    if (acVar.m16079a()) {
                        return C3979c.f14327h;
                    }
                    if (acVar.m16086k()) {
                        return C3979c.f14326g;
                    }
                    throw new AssertionError();
                } else if (r instanceof C1483y) {
                    return C3979c.f14328i;
                } else {
                    if (r == f14394b) {
                        throw new IllegalStateException(f14395z[3]);
                    }
                    throw new AssertionError();
                }
            }
        }
        return C3979c.f14329j;
    }

    /* renamed from: g */
    public final String mo5684g() {
        m16253a(C3979c.f14324e);
        Entry entry = (Entry) ((Iterator) m16254r()).next();
        this.f14396c.add(entry.getValue());
        return (String) entry.getKey();
    }

    /* renamed from: h */
    public final String mo5685h() {
        C3979c f = mo5683f();
        if (f == C3979c.f14325f || f == C3979c.f14326g) {
            return ((ac) m16255s()).mo5667c();
        }
        throw new IllegalStateException(new StringBuilder(f14395z[1]).append(C3979c.f14325f).append(f14395z[2]).append(f).toString());
    }

    /* renamed from: i */
    public final boolean mo5686i() {
        m16253a(C3979c.f14327h);
        return ((ac) m16255s()).mo5671g();
    }

    /* renamed from: j */
    public final void mo5687j() {
        m16253a(C3979c.f14328i);
        m16255s();
    }

    /* renamed from: k */
    public final double mo5688k() {
        C3979c f = mo5683f();
        if (f == C3979c.f14326g || f == C3979c.f14325f) {
            double d = ((ac) m16254r()).mo5668d();
            if (m16132p() || !(Double.isNaN(d) || Double.isInfinite(d))) {
                m16255s();
                return d;
            }
            throw new NumberFormatException(new StringBuilder(f14395z[0]).append(d).toString());
        }
        throw new IllegalStateException(new StringBuilder(f14395z[1]).append(C3979c.f14326g).append(f14395z[2]).append(f).toString());
    }

    /* renamed from: l */
    public final long mo5689l() {
        C3979c f = mo5683f();
        if (f == C3979c.f14326g || f == C3979c.f14325f) {
            long e = ((ac) m16254r()).mo5669e();
            m16255s();
            return e;
        }
        throw new IllegalStateException(new StringBuilder(f14395z[1]).append(C3979c.f14326g).append(f14395z[2]).append(f).toString());
    }

    /* renamed from: m */
    public final int mo5690m() {
        C3979c f = mo5683f();
        if (f == C3979c.f14326g || f == C3979c.f14325f) {
            int f2 = ((ac) m16254r()).mo5670f();
            m16255s();
            return f2;
        }
        throw new IllegalStateException(new StringBuilder(f14395z[1]).append(C3979c.f14326g).append(f14395z[2]).append(f).toString());
    }

    /* renamed from: n */
    public final void mo5691n() {
        if (mo5683f() == C3979c.f14324e) {
            mo5684g();
        } else {
            m16255s();
        }
    }

    /* renamed from: o */
    public final void mo5692o() {
        m16253a(C3979c.f14324e);
        Entry entry = (Entry) ((Iterator) m16254r()).next();
        this.f14396c.add(entry.getValue());
        this.f14396c.add(new ac((String) entry.getKey()));
    }

    public final String toString() {
        return getClass().getSimpleName();
    }
}
