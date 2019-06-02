package com.google.gson.jpush.internal;

import com.google.gson.jpush.C4053x;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.EnumSet;

/* renamed from: com.google.gson.jpush.internal.n */
final class C4030n implements ae<T> {
    /* renamed from: z */
    private static final String f14535z;
    /* renamed from: a */
    final /* synthetic */ Type f14536a;
    /* renamed from: b */
    final /* synthetic */ C4022f f14537b;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = "YjYWjy`\u000fshei|Sr0pVFc*$";
        r0 = r0.toCharArray();
        r1 = r0.length;
        r2 = 0;
        r3 = 1;
        if (r1 > r3) goto L_0x0026;
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
            case 0: goto L_0x0034;
            case 1: goto L_0x0037;
            case 2: goto L_0x0039;
            case 3: goto L_0x003c;
            default: goto L_0x0017;
        };
    L_0x0017:
        r5 = 6;
    L_0x0018:
        r5 = r5 ^ r6;
        r5 = (char) r5;
        r1[r2] = r5;
        r2 = r4 + 1;
        if (r0 != 0) goto L_0x0024;
    L_0x0020:
        r1 = r3;
        r4 = r2;
        r2 = r0;
        goto L_0x0010;
    L_0x0024:
        r1 = r0;
        r0 = r3;
    L_0x0026:
        if (r1 > r2) goto L_0x000b;
    L_0x0028:
        r1 = new java.lang.String;
        r1.<init>(r0);
        r0 = r1.intern();
        f14535z = r0;
        return;
    L_0x0034:
        r5 = 16;
        goto L_0x0018;
    L_0x0037:
        r5 = 4;
        goto L_0x0018;
    L_0x0039:
        r5 = 47;
        goto L_0x0018;
    L_0x003c:
        r5 = 54;
        goto L_0x0018;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.internal.n.<clinit>():void");
    }

    C4030n(C4022f c4022f, Type type) {
        this.f14537b = c4022f;
        this.f14536a = type;
    }

    /* renamed from: a */
    public final T mo5711a() {
        if (this.f14536a instanceof ParameterizedType) {
            Type type = ((ParameterizedType) this.f14536a).getActualTypeArguments()[0];
            if (type instanceof Class) {
                return EnumSet.noneOf((Class) type);
            }
            throw new C4053x(new StringBuilder(f14535z).append(this.f14536a.toString()).toString());
        }
        throw new C4053x(new StringBuilder(f14535z).append(this.f14536a.toString()).toString());
    }
}
