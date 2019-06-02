package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.af;
import com.google.gson.jpush.al;
import com.google.gson.jpush.internal.C4037v;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3979c;
import com.google.gson.jpush.p185b.C3980d;

/* renamed from: com.google.gson.jpush.internal.a.ac */
final class ac extends al<Number> {
    /* renamed from: z */
    private static final String f14356z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = "8\u0002trs\t\u0013jp0\u0013\u000fiuu\u000fV$p\t@$";
        r0 = r0.toCharArray();
        r1 = r0.length;
        r2 = 0;
        r3 = 1;
        if (r1 > r3) goto L_0x0027;
    L_0x000b:
        r3 = r0;
        r4 = r2;
        r7 = r1;
        r1 = r0;
        r0 = r7;
    L_0x0010:
        r6 = r1[r2];
        r5 = r4 % 5;
        switch(r5) {
            case 0: goto L_0x0035;
            case 1: goto L_0x0038;
            case 2: goto L_0x003b;
            case 3: goto L_0x003d;
            default: goto L_0x0017;
        };
    L_0x0017:
        r5 = 16;
    L_0x0019:
        r5 = r5 ^ r6;
        r5 = (char) r5;
        r1[r2] = r5;
        r2 = r4 + 1;
        if (r0 != 0) goto L_0x0025;
    L_0x0021:
        r1 = r3;
        r4 = r2;
        r2 = r0;
        goto L_0x0010;
    L_0x0025:
        r1 = r0;
        r0 = r3;
    L_0x0027:
        if (r1 > r2) goto L_0x000b;
    L_0x0029:
        r1 = new java.lang.String;
        r1.<init>(r0);
        r0 = r1.intern();
        f14356z = r0;
        return;
    L_0x0035:
        r5 = 125; // 0x7d float:1.75E-43 double:6.2E-322;
        goto L_0x0019;
    L_0x0038:
        r5 = 122; // 0x7a float:1.71E-43 double:6.03E-322;
        goto L_0x0019;
    L_0x003b:
        r5 = 4;
        goto L_0x0019;
    L_0x003d:
        r5 = 23;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.internal.a.ac.<clinit>():void");
    }

    ac() {
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo5672a(C3976a c3976a) {
        C3979c f = c3976a.mo5683f();
        switch (az.f14379a[f.ordinal()]) {
            case 1:
                return new C4037v(c3976a.mo5685h());
            case 4:
                c3976a.mo5687j();
                return null;
            default:
                throw new af(new StringBuilder(f14356z).append(f).toString());
        }
    }

    /* renamed from: a */
    public final /* bridge */ /* synthetic */ void mo5673a(C3980d c3980d, Object obj) {
        c3980d.mo5695a((Number) obj);
    }
}
