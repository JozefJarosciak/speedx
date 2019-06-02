package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.C1483y;
import com.google.gson.jpush.C3975w;
import com.google.gson.jpush.C4052t;
import com.google.gson.jpush.C4054z;
import com.google.gson.jpush.ac;
import com.google.gson.jpush.al;
import com.google.gson.jpush.internal.C4037v;
import com.google.gson.jpush.p185b.C3976a;
import com.google.gson.jpush.p185b.C3980d;
import java.util.Iterator;
import java.util.Map.Entry;

/* renamed from: com.google.gson.jpush.internal.a.as */
final class as extends al<C3975w> {
    /* renamed from: z */
    private static final String f14364z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = "I\n3N\u0006dB2\u0002\u0015x\f2GB";
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
            case 3: goto L_0x003e;
            default: goto L_0x0017;
        };
    L_0x0017:
        r5 = 98;
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
        f14364z = r0;
        return;
    L_0x0035:
        r5 = 10;
        goto L_0x0019;
    L_0x0038:
        r5 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x0019;
    L_0x003b:
        r5 = 70;
        goto L_0x0019;
    L_0x003e:
        r5 = 34;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.internal.a.as.<clinit>():void");
    }

    as() {
    }

    /* renamed from: a */
    private void m16213a(C3980d c3980d, C3975w c3975w) {
        if (c3975w == null || (c3975w instanceof C1483y)) {
            c3980d.mo5705f();
        } else if (c3975w instanceof ac) {
            ac j = c3975w.m16076j();
            if (j.m16086k()) {
                c3980d.mo5695a(j.mo5666b());
            } else if (j.m16079a()) {
                c3980d.mo5697a(j.mo5671g());
            } else {
                c3980d.mo5700b(j.mo5667c());
            }
        } else if (c3975w instanceof C4052t) {
            c3980d.mo5699b();
            Iterator it = c3975w.m16075i().iterator();
            while (it.hasNext()) {
                m16213a(c3980d, (C3975w) it.next());
            }
            c3980d.mo5701c();
        } else if (c3975w instanceof C4054z) {
            c3980d.mo5703d();
            for (Entry entry : c3975w.m16074h().m16407a()) {
                c3980d.mo5696a((String) entry.getKey());
                m16213a(c3980d, (C3975w) entry.getValue());
            }
            c3980d.mo5704e();
        } else {
            throw new IllegalArgumentException(new StringBuilder(f14364z).append(c3975w.getClass()).toString());
        }
    }

    /* renamed from: b */
    private C3975w m16214b(C3976a c3976a) {
        C3975w c4052t;
        switch (az.f14379a[c3976a.mo5683f().ordinal()]) {
            case 1:
                return new ac(new C4037v(c3976a.mo5685h()));
            case 2:
                return new ac(Boolean.valueOf(c3976a.mo5686i()));
            case 3:
                return new ac(c3976a.mo5685h());
            case 4:
                c3976a.mo5687j();
                return C1483y.f7007a;
            case 5:
                c4052t = new C4052t();
                c3976a.mo5677a();
                while (c3976a.mo5682e()) {
                    c4052t.m16400a(m16214b(c3976a));
                }
                c3976a.mo5678b();
                return c4052t;
            case 6:
                c4052t = new C4054z();
                c3976a.mo5679c();
                while (c3976a.mo5682e()) {
                    c4052t.m16408a(c3976a.mo5684g(), m16214b(c3976a));
                }
                c3976a.mo5681d();
                return c4052t;
            default:
                throw new IllegalArgumentException();
        }
    }

    /* renamed from: a */
    public final /* synthetic */ Object mo5672a(C3976a c3976a) {
        return m16214b(c3976a);
    }
}
