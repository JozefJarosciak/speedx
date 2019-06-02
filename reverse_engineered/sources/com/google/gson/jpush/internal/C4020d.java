package com.google.gson.jpush.internal;

import com.j256.ormlite.stmt.query.SimpleComparison;
import java.io.Serializable;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;

/* renamed from: com.google.gson.jpush.internal.d */
final class C4020d implements Serializable, ParameterizedType {
    /* renamed from: z */
    private static final String f14510z;
    /* renamed from: a */
    private final Type f14511a;
    /* renamed from: b */
    private final Type f14512b;
    /* renamed from: c */
    private final Type[] f14513c;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = "^\u0003";
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
        r5 = 34;
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
        f14510z = r0;
        return;
    L_0x0035:
        r5 = 114; // 0x72 float:1.6E-43 double:5.63E-322;
        goto L_0x0019;
    L_0x0038:
        r5 = 35;
        goto L_0x0019;
    L_0x003b:
        r5 = 37;
        goto L_0x0019;
    L_0x003e:
        r5 = 77;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.internal.d.<clinit>():void");
    }

    public C4020d(Type type, Type type2, Type... typeArr) {
        int i = 0;
        if (type2 instanceof Class) {
            Class cls = (Class) type2;
            int i2 = (Modifier.isStatic(cls.getModifiers()) || cls.getEnclosingClass() == null) ? 1 : 0;
            boolean z = (type == null && i2 == 0) ? false : true;
            C4017a.m16320a(z);
        }
        this.f14511a = type == null ? null : C4018b.m16330a(type);
        this.f14512b = C4018b.m16330a(type2);
        this.f14513c = (Type[]) typeArr.clone();
        while (i < this.f14513c.length) {
            C4017a.m16319a(this.f14513c[i]);
            C4018b.m16340e(this.f14513c[i]);
            this.f14513c[i] = C4018b.m16330a(this.f14513c[i]);
            i++;
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof ParameterizedType) && C4018b.m16334a((Type) this, (ParameterizedType) obj);
    }

    public final Type[] getActualTypeArguments() {
        return (Type[]) this.f14513c.clone();
    }

    public final Type getOwnerType() {
        return this.f14511a;
    }

    public final Type getRawType() {
        return this.f14512b;
    }

    public final int hashCode() {
        return (Arrays.hashCode(this.f14513c) ^ this.f14512b.hashCode()) ^ C4018b.m16329a(this.f14511a);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder((this.f14513c.length + 1) * 30);
        stringBuilder.append(C4018b.m16338c(this.f14512b));
        if (this.f14513c.length == 0) {
            return stringBuilder.toString();
        }
        stringBuilder.append(SimpleComparison.LESS_THAN_OPERATION).append(C4018b.m16338c(this.f14513c[0]));
        for (int i = 1; i < this.f14513c.length; i++) {
            stringBuilder.append(f14510z).append(C4018b.m16338c(this.f14513c[i]));
        }
        return stringBuilder.append(SimpleComparison.GREATER_THAN_OPERATION).toString();
    }
}
