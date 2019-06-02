package com.google.protobuf.jpush;

import java.util.Collection;

/* renamed from: com.google.protobuf.jpush.b */
public abstract class C4070b<BuilderType extends C4070b> implements C4069l {
    /* renamed from: z */
    private static final String f14608z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = "#b\u0014$u\u001f`U&n\u001ejU!<\u0013~\u0001%<\u0010u\u0007!eQs\u001d2y\u0006'\u0014.<8H08\u0014w\u0001)s\u001f']3t\u001er\u0019$<\u001fb\u0003%nQo\u00140l\u0014i\\n";
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
            case 2: goto L_0x003a;
            case 3: goto L_0x003d;
            default: goto L_0x0017;
        };
    L_0x0017:
        r5 = 28;
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
        f14608z = r0;
        return;
    L_0x0035:
        r5 = 113; // 0x71 float:1.58E-43 double:5.6E-322;
        goto L_0x0019;
    L_0x0038:
        r5 = 7;
        goto L_0x0019;
    L_0x003a:
        r5 = 117; // 0x75 float:1.64E-43 double:5.8E-322;
        goto L_0x0019;
    L_0x003d:
        r5 = 64;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.jpush.b.<clinit>():void");
    }

    /* renamed from: a */
    protected static <T> void m16414a(Iterable<T> iterable, Collection<? super T> collection) {
        for (T t : iterable) {
            if (t == null) {
                throw new NullPointerException();
            }
        }
        if (iterable instanceof Collection) {
            collection.addAll((Collection) iterable);
            return;
        }
        for (T t2 : iterable) {
            collection.add(t2);
        }
    }

    /* renamed from: a */
    public abstract BuilderType m16415a(C4072d c4072d, C4075g c4075g);

    /* renamed from: a */
    public final BuilderType m16416a(byte[] bArr, int i, int i2) {
        try {
            C4072d a = C4072d.m16427a(bArr, 0, i2);
            m16415a(a, C4075g.m16465a());
            a.m16433a(0);
            return this;
        } catch (C4078j e) {
            throw e;
        } catch (Throwable e2) {
            throw new RuntimeException(f14608z, e2);
        }
    }

    /* renamed from: b */
    public /* synthetic */ C4069l mo5719b(C4072d c4072d, C4075g c4075g) {
        return m16415a(c4072d, c4075g);
    }

    public /* synthetic */ Object clone() {
        return mo5721d();
    }

    /* renamed from: d */
    public abstract BuilderType mo5721d();
}
