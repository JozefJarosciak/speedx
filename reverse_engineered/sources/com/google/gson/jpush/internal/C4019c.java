package com.google.gson.jpush.internal;

import java.io.Serializable;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;

/* renamed from: com.google.gson.jpush.internal.c */
final class C4019c implements Serializable, GenericArrayType {
    /* renamed from: z */
    private static final String f14508z;
    /* renamed from: a */
    private final Type f14509a;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = "PO";
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
        r5 = 100;
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
        f14508z = r0;
        return;
    L_0x0035:
        r5 = 11;
        goto L_0x0019;
    L_0x0038:
        r5 = 18;
        goto L_0x0019;
    L_0x003b:
        r5 = 113; // 0x71 float:1.58E-43 double:5.6E-322;
        goto L_0x0019;
    L_0x003e:
        r5 = 123; // 0x7b float:1.72E-43 double:6.1E-322;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.internal.c.<clinit>():void");
    }

    public C4019c(Type type) {
        this.f14509a = C4018b.m16330a(type);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof GenericArrayType) && C4018b.m16334a((Type) this, (GenericArrayType) obj);
    }

    public final Type getGenericComponentType() {
        return this.f14509a;
    }

    public final int hashCode() {
        return this.f14509a.hashCode();
    }

    public final String toString() {
        return C4018b.m16338c(this.f14509a) + f14508z;
    }
}
