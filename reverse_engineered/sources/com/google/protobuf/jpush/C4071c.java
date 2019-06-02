package com.google.protobuf.jpush;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

/* renamed from: com.google.protobuf.jpush.c */
public final class C4071c {
    /* renamed from: a */
    public static final C4071c f14609a = new C4071c(new byte[0]);
    /* renamed from: z */
    private static final String[] f14610z;
    /* renamed from: b */
    private final byte[] f14611b;
    /* renamed from: c */
    private volatile int f14612c = 0;

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
        r2 = "oEj\u001d|\u001aCDdId\\@+HeIT{";
        r0 = -1;
        r5 = r3;
        r6 = r3;
        r3 = r1;
    L_0x000b:
        r2 = r2.toCharArray();
        r7 = r2.length;
        if (r7 > r4) goto L_0x0060;
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
            case 0: goto L_0x0054;
            case 1: goto L_0x0057;
            case 2: goto L_0x005a;
            case 3: goto L_0x005d;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 68;
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
        r0 = "oEj\u001d|";
        r2 = r0;
        r3 = r4;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r3] = r2;
        f14610z = r6;
        r0 = new com.google.protobuf.jpush.c;
        r1 = new byte[r1];
        r0.<init>(r1);
        f14609a = r0;
        return;
    L_0x0054:
        r11 = 58;
        goto L_0x0021;
    L_0x0057:
        r11 = 17;
        goto L_0x0021;
    L_0x005a:
        r11 = 44;
        goto L_0x0021;
    L_0x005d:
        r11 = 48;
        goto L_0x0021;
    L_0x0060:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.jpush.c.<clinit>():void");
    }

    private C4071c(byte[] bArr) {
        this.f14611b = bArr;
    }

    /* renamed from: a */
    public static C4071c m16419a(String str) {
        try {
            return new C4071c(str.getBytes(f14610z[1]));
        } catch (Throwable e) {
            throw new RuntimeException(f14610z[0], e);
        }
    }

    /* renamed from: a */
    public static C4071c m16420a(byte[] bArr) {
        return C4071c.m16421a(bArr, 0, bArr.length);
    }

    /* renamed from: a */
    public static C4071c m16421a(byte[] bArr, int i, int i2) {
        Object obj = new byte[i2];
        System.arraycopy(bArr, i, obj, 0, i2);
        return new C4071c(obj);
    }

    /* renamed from: a */
    public final byte m16422a(int i) {
        return this.f14611b[i];
    }

    /* renamed from: a */
    public final int m16423a() {
        return this.f14611b.length;
    }

    /* renamed from: a */
    public final void m16424a(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.f14611b, i, bArr, i2, i3);
    }

    /* renamed from: b */
    public final String m16425b() {
        try {
            return new String(this.f14611b, f14610z[1]);
        } catch (Throwable e) {
            throw new RuntimeException(f14610z[0], e);
        }
    }

    /* renamed from: c */
    public final InputStream m16426c() {
        return new ByteArrayInputStream(this.f14611b);
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof C4071c)) {
            return false;
        }
        C4071c c4071c = (C4071c) obj;
        int length = this.f14611b.length;
        if (length != c4071c.f14611b.length) {
            return false;
        }
        byte[] bArr = this.f14611b;
        byte[] bArr2 = c4071c.f14611b;
        for (int i = 0; i < length; i++) {
            if (bArr[i] != bArr2[i]) {
                return false;
            }
        }
        return true;
    }

    public final int hashCode() {
        int i = this.f14612c;
        if (i == 0) {
            byte[] bArr = this.f14611b;
            int length = this.f14611b.length;
            int i2 = 0;
            i = length;
            while (i2 < length) {
                int i3 = bArr[i2] + (i * 31);
                i2++;
                i = i3;
            }
            if (i == 0) {
                i = 1;
            }
            this.f14612c = i;
        }
        return i;
    }
}
