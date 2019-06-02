package com.google.gson.jpush.internal;

import ch.qos.logback.classic.spi.CallerData;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;

/* renamed from: com.google.gson.jpush.internal.e */
final class C4021e implements Serializable, WildcardType {
    /* renamed from: z */
    private static final String[] f14514z;
    /* renamed from: a */
    private final Type f14515a;
    /* renamed from: b */
    private final Type f14516b;

    static {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1723278948.run(Unknown Source)
*/
        /*
        r4 = 1;
        r1 = 0;
        r0 = 2;
        r3 = new java.lang.String[r0];
        r2 = "9#d|3cmewg";
        r0 = -1;
        r5 = r3;
        r6 = r3;
        r3 = r1;
    L_0x000b:
        r2 = r2.toCharArray();
        r7 = r2.length;
        if (r7 > r4) goto L_0x0053;
    L_0x0012:
        r8 = r1;
    L_0x0013:
        r9 = r2;
        r10 = r8;
        r13 = r7;
        r7 = r2;
        r2 = r13;
    L_0x0018:
        r12 = r7[r8];
        r11 = r10 % 5;
        switch(r11) {
            case 0: goto L_0x004b;
            case 1: goto L_0x004d;
            case 2: goto L_0x004f;
            case 3: goto L_0x0051;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 71;
    L_0x0021:
        r11 = r11 ^ r12;
        r11 = (char) r11;
        r7[r8] = r11;
        r8 = r10 + 1;
        if (r2 != 0) goto L_0x002d;
    L_0x0029:
        r7 = r9;
        r10 = r8;
        r8 = r2;
        goto L_0x0018;
    L_0x002d:
        r7 = r2;
        r2 = r9;
    L_0x002f:
        if (r7 > r8) goto L_0x0013;
    L_0x0031:
        r7 = new java.lang.String;
        r7.<init>(r2);
        r2 = r7.intern();
        switch(r0) {
            case 0: goto L_0x0046;
            default: goto L_0x003d;
        };
    L_0x003d:
        r5[r3] = r2;
        r0 = "9#rq7cq!";
        r2 = r0;
        r3 = r4;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r3] = r2;
        f14514z = r6;
        return;
    L_0x004b:
        r11 = 6;
        goto L_0x0021;
    L_0x004d:
        r11 = 3;
        goto L_0x0021;
    L_0x004f:
        r11 = r4;
        goto L_0x0021;
    L_0x0051:
        r11 = 4;
        goto L_0x0021;
    L_0x0053:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.gson.jpush.internal.e.<clinit>():void");
    }

    public C4021e(Type[] typeArr, Type[] typeArr2) {
        boolean z = true;
        C4017a.m16320a(typeArr2.length <= 1);
        C4017a.m16320a(typeArr.length == 1);
        if (typeArr2.length == 1) {
            C4017a.m16319a(typeArr2[0]);
            C4018b.m16340e(typeArr2[0]);
            if (typeArr[0] != Object.class) {
                z = false;
            }
            C4017a.m16320a(z);
            this.f14516b = C4018b.m16330a(typeArr2[0]);
            this.f14515a = Object.class;
            return;
        }
        C4017a.m16319a(typeArr[0]);
        C4018b.m16340e(typeArr[0]);
        this.f14516b = null;
        this.f14515a = C4018b.m16330a(typeArr[0]);
    }

    public final boolean equals(Object obj) {
        return (obj instanceof WildcardType) && C4018b.m16334a((Type) this, (WildcardType) obj);
    }

    public final Type[] getLowerBounds() {
        if (this.f14516b == null) {
            return C4018b.f14506a;
        }
        return new Type[]{this.f14516b};
    }

    public final Type[] getUpperBounds() {
        return new Type[]{this.f14515a};
    }

    public final int hashCode() {
        return (this.f14516b != null ? this.f14516b.hashCode() + 31 : 1) ^ (this.f14515a.hashCode() + 31);
    }

    public final String toString() {
        return this.f14516b != null ? new StringBuilder(f14514z[1]).append(C4018b.m16338c(this.f14516b)).toString() : this.f14515a == Object.class ? CallerData.NA : new StringBuilder(f14514z[0]).append(C4018b.m16338c(this.f14515a)).toString();
    }
}
