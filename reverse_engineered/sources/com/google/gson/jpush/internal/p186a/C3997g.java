package com.google.gson.jpush.internal.p186a;

import com.google.gson.jpush.C4042k;
import com.google.gson.jpush.al;
import com.google.gson.jpush.am;
import com.google.gson.jpush.annotations.C1480b;
import com.google.gson.jpush.internal.C4022f;
import com.google.gson.jpush.p184a.C3972a;

/* renamed from: com.google.gson.jpush.internal.a.g */
public final class C3997g implements am {
    /* renamed from: z */
    private static final String f14391z;
    /* renamed from: a */
    private final C4022f f14392a;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = "s0\u0001\u0001\u001fr\u001e\u0013\u001e\u0005V\bR\u0018\u0010_\u000f\u0017N\u001cF\t\u0006N\u0013VZ&\u0017\u0001V;\u0016\u000f\u0001G\u001f\u0000N\u001eAZ&\u0017\u0001V;\u0016\u000f\u0001G\u001f\u0000(\u0010P\u000e\u001d\u001c\b\u0013\b\u0017\b\u0014A\u001f\u001c\r\u0014\u001d";
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
        r5 = 113; // 0x71 float:1.58E-43 double:5.6E-322;
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
        f14391z = r0;
        return;
    L_0x0035:
        r5 = 51;
        goto L_0x0019;
    L_0x0038:
        r5 = 122; // 0x7a float:1.71E-43 double:6.03E-322;
        goto L_0x0019;
    L_0x003b:
        r5 = 114; // 0x72 float:1.6E-43 double:5.63E-322;
        goto L_0x0019;
    L_0x003e:
        r5 = 110; // 0x6e float:1.54E-43 double:5.43E-322;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.internal.a.g.<clinit>():void");
    }

    public C3997g(C4022f c4022f) {
        this.f14392a = c4022f;
    }

    /* renamed from: a */
    static al<?> m16251a(C4022f c4022f, C4042k c4042k, C3972a<?> c3972a, C1480b c1480b) {
        Class a = c1480b.a();
        if (al.class.isAssignableFrom(a)) {
            return (al) c4022f.m16343a(C3972a.m16055a(a)).mo5711a();
        }
        if (am.class.isAssignableFrom(a)) {
            return ((am) c4022f.m16343a(C3972a.m16055a(a)).mo5711a()).mo5674a(c4042k, c3972a);
        }
        throw new IllegalArgumentException(f14391z);
    }

    /* renamed from: a */
    public final <T> al<T> mo5674a(C4042k c4042k, C3972a<T> c3972a) {
        C1480b c1480b = (C1480b) c3972a.m16057a().getAnnotation(C1480b.class);
        return c1480b == null ? null : C3997g.m16251a(this.f14392a, c4042k, c3972a, c1480b);
    }
}
