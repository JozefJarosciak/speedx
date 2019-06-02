package com.google.gson.jpush.p184a;

import com.google.gson.jpush.internal.C4017a;
import com.google.gson.jpush.internal.C4018b;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/* renamed from: com.google.gson.jpush.a.a */
public final class C3972a<T> {
    /* renamed from: z */
    private static final String f14274z;
    /* renamed from: a */
    final Class<? super T> f14275a;
    /* renamed from: b */
    final Type f14276b;
    /* renamed from: c */
    final int f14277c;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = "XB zS{Ls}CeNsy[gJ>lNpY}";
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
        r5 = 58;
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
        f14274z = r0;
        return;
    L_0x0035:
        r5 = 21;
        goto L_0x0019;
    L_0x0038:
        r5 = 43;
        goto L_0x0019;
    L_0x003b:
        r5 = 83;
        goto L_0x0019;
    L_0x003e:
        r5 = 9;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.a.a.<clinit>():void");
    }

    protected C3972a() {
        Type genericSuperclass = getClass().getGenericSuperclass();
        if (genericSuperclass instanceof Class) {
            throw new RuntimeException(f14274z);
        }
        this.f14276b = C4018b.m16330a(((ParameterizedType) genericSuperclass).getActualTypeArguments()[0]);
        this.f14275a = C4018b.m16335b(this.f14276b);
        this.f14277c = this.f14276b.hashCode();
    }

    private C3972a(Type type) {
        this.f14276b = C4018b.m16330a((Type) C4017a.m16319a((Object) type));
        this.f14275a = C4018b.m16335b(this.f14276b);
        this.f14277c = this.f14276b.hashCode();
    }

    /* renamed from: a */
    public static <T> C3972a<T> m16055a(Class<T> cls) {
        return new C3972a(cls);
    }

    /* renamed from: a */
    public static C3972a<?> m16056a(Type type) {
        return new C3972a(type);
    }

    /* renamed from: a */
    public final Class<? super T> m16057a() {
        return this.f14275a;
    }

    /* renamed from: b */
    public final Type m16058b() {
        return this.f14276b;
    }

    public final boolean equals(Object obj) {
        return (obj instanceof C3972a) && C4018b.m16334a(this.f14276b, ((C3972a) obj).f14276b);
    }

    public final int hashCode() {
        return this.f14277c;
    }

    public final String toString() {
        return C4018b.m16338c(this.f14276b);
    }
}
