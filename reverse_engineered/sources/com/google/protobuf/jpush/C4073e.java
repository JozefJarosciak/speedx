package com.google.protobuf.jpush;

import java.io.InputStream;
import java.io.OutputStream;

/* renamed from: com.google.protobuf.jpush.e */
public final class C4073e {
    /* renamed from: z */
    private static final String[] f14625z;
    /* renamed from: a */
    private final byte[] f14626a;
    /* renamed from: b */
    private final int f14627b;
    /* renamed from: c */
    private int f14628c;
    /* renamed from: d */
    private final OutputStream f14629d = null;

    static {
        /* JADX: method processing error */
/*
Error: jadx.core.utils.exceptions.JadxOverflowException: Regions stack size limit reached
	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:37)
	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:61)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:33)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:34)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:56)
	at jadx.core.ProcessClass.process(ProcessClass.java:39)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:282)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:200)
	at jadx.api.JadxDecompiler$$Lambda$8/1723278948.run(Unknown Source)
*/
        /*
        r2 = 1;
        r1 = 0;
        r0 = 3;
        r4 = new java.lang.String[r0];
        r3 = "\u00154~FH >~Z\r\"`7e\u0000)*{RH(:aS\u001af7vF\u0018#19";
        r0 = -1;
        r5 = r4;
        r6 = r4;
        r4 = r1;
    L_0x000b:
        r3 = r3.toCharArray();
        r7 = r3.length;
        if (r7 > r2) goto L_0x0061;
    L_0x0012:
        r8 = r1;
    L_0x0013:
        r9 = r3;
        r10 = r8;
        r13 = r7;
        r7 = r3;
        r3 = r13;
    L_0x0018:
        r12 = r7[r8];
        r11 = r10 % 5;
        switch(r11) {
            case 0: goto L_0x0055;
            case 1: goto L_0x0058;
            case 2: goto L_0x005b;
            case 3: goto L_0x005e;
            default: goto L_0x001f;
        };
    L_0x001f:
        r11 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
    L_0x0021:
        r11 = r11 ^ r12;
        r11 = (char) r11;
        r7[r8] = r11;
        r8 = r10 + 1;
        if (r3 != 0) goto L_0x002d;
    L_0x0029:
        r7 = r9;
        r10 = r8;
        r8 = r3;
        goto L_0x0018;
    L_0x002d:
        r7 = r3;
        r3 = r9;
    L_0x002f:
        if (r7 > r8) goto L_0x0013;
    L_0x0031:
        r7 = new java.lang.String;
        r7.<init>(r3);
        r3 = r7.intern();
        switch(r0) {
            case 0: goto L_0x0046;
            case 1: goto L_0x0050;
            default: goto L_0x003d;
        };
    L_0x003d:
        r5[r4] = r3;
        r0 = "\u0014:vRH >~Z\r\"`7e\u0000)*{RH(:aS\u001af7vF\u0018#1";
        r3 = r0;
        r4 = r2;
        r5 = r6;
        r0 = r1;
        goto L_0x000b;
    L_0x0046:
        r5[r4] = r3;
        r3 = 2;
        r0 = "5/vU\r\n:qB@otW\u0006f0yZ\u0011f=r\u0016\u000b'3{S\ff0y\u0016+);rR'3+gC\u001c\u0015+eS\t+,7B\u0000'+7W\u001a#`D\u000126yQH207WH 3vBH'-eW\u0011h";
        r4 = r3;
        r5 = r6;
        r3 = r0;
        r0 = r2;
        goto L_0x000b;
    L_0x0050:
        r5[r4] = r3;
        f14625z = r6;
        return;
    L_0x0055:
        r11 = 70;
        goto L_0x0021;
    L_0x0058:
        r11 = 95;
        goto L_0x0021;
    L_0x005b:
        r11 = 23;
        goto L_0x0021;
    L_0x005e:
        r11 = 54;
        goto L_0x0021;
    L_0x0061:
        r8 = r1;
        goto L_0x002f;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.protobuf.jpush.e.<clinit>():void");
    }

    private C4073e(byte[] bArr, int i, int i2) {
        this.f14626a = bArr;
        this.f14628c = i;
        this.f14627b = i + i2;
    }

    /* renamed from: a */
    public static int m16444a(int i) {
        return i >= 0 ? C4073e.m16457e(i) : 10;
    }

    /* renamed from: a */
    public static int m16445a(long j) {
        return (-128 & j) == 0 ? 1 : (-16384 & j) == 0 ? 2 : (-2097152 & j) == 0 ? 3 : (-268435456 & j) == 0 ? 4 : (-34359738368L & j) == 0 ? 5 : (-4398046511104L & j) == 0 ? 6 : (-562949953421312L & j) == 0 ? 7 : (-72057594037927936L & j) == 0 ? 8 : (Long.MIN_VALUE & j) == 0 ? 9 : 10;
    }

    /* renamed from: a */
    public static C4073e m16446a(byte[] bArr, int i, int i2) {
        return new C4073e(bArr, 0, i2);
    }

    /* renamed from: b */
    public static int m16447b(int i, long j) {
        return C4073e.m16453c(i) + C4073e.m16445a(j);
    }

    /* renamed from: b */
    public static int m16448b(int i, C4071c c4071c) {
        return C4073e.m16453c(i) + (C4073e.m16457e(c4071c.m16423a()) + c4071c.m16423a());
    }

    /* renamed from: b */
    public static int m16449b(int i, C4067k c4067k) {
        int c = C4073e.m16453c(i);
        int c2 = c4067k.m16412c();
        return c + (c2 + C4073e.m16457e(c2));
    }

    /* renamed from: b */
    private void m16450b() {
        if (this.f14629d == null) {
            throw new C4074f();
        }
        this.f14629d.write(this.f14626a, 0, this.f14628c);
        this.f14628c = 0;
    }

    /* renamed from: b */
    private void m16451b(int i) {
        byte b = (byte) i;
        if (this.f14628c == this.f14627b) {
            m16450b();
        }
        byte[] bArr = this.f14626a;
        int i2 = this.f14628c;
        this.f14628c = i2 + 1;
        bArr[i2] = b;
    }

    /* renamed from: b */
    private void m16452b(long j) {
        while ((-128 & j) != 0) {
            m16451b((((int) j) & 127) | 128);
            j >>>= 7;
        }
        m16451b((int) j);
    }

    /* renamed from: c */
    private static int m16453c(int i) {
        return C4073e.m16457e(C4080n.m16477a(i, 0));
    }

    /* renamed from: c */
    public static int m16454c(int i, int i2) {
        return C4073e.m16453c(i) + C4073e.m16444a(i2);
    }

    /* renamed from: d */
    public static int m16455d(int i, int i2) {
        return C4073e.m16453c(i) + C4073e.m16457e(i2);
    }

    /* renamed from: d */
    private void m16456d(int i) {
        while ((i & -128) != 0) {
            m16451b((i & 127) | 128);
            i >>>= 7;
        }
        m16451b(i);
    }

    /* renamed from: e */
    private static int m16457e(int i) {
        return (i & -128) == 0 ? 1 : (i & -16384) == 0 ? 2 : (-2097152 & i) == 0 ? 3 : (-268435456 & i) == 0 ? 4 : 5;
    }

    /* renamed from: e */
    private void m16458e(int i, int i2) {
        m16456d(C4080n.m16477a(i, i2));
    }

    /* renamed from: a */
    public final int m16459a() {
        if (this.f14629d == null) {
            return this.f14627b - this.f14628c;
        }
        throw new UnsupportedOperationException(f14625z[2]);
    }

    /* renamed from: a */
    public final void m16460a(int i, int i2) {
        m16458e(i, 0);
        if (i2 >= 0) {
            m16456d(i2);
        } else {
            m16452b((long) i2);
        }
    }

    /* renamed from: a */
    public final void m16461a(int i, long j) {
        m16458e(i, 0);
        m16452b(j);
    }

    /* renamed from: a */
    public final void m16462a(int i, C4071c c4071c) {
        m16458e(i, 2);
        m16456d(c4071c.m16423a());
        int a = c4071c.m16423a();
        if (this.f14627b - this.f14628c >= a) {
            c4071c.m16424a(this.f14626a, 0, this.f14628c, a);
            this.f14628c = a + this.f14628c;
            return;
        }
        int i2 = this.f14627b - this.f14628c;
        c4071c.m16424a(this.f14626a, 0, this.f14628c, i2);
        int i3 = i2 + 0;
        a -= i2;
        this.f14628c = this.f14627b;
        m16450b();
        if (a <= this.f14627b) {
            c4071c.m16424a(this.f14626a, i3, 0, a);
            this.f14628c = a;
            return;
        }
        InputStream c = c4071c.m16426c();
        if (((long) i3) != c.skip((long) i3)) {
            throw new IllegalStateException(f14625z[0]);
        }
        while (a > 0) {
            i3 = Math.min(a, this.f14627b);
            int read = c.read(this.f14626a, 0, i3);
            if (read != i3) {
                throw new IllegalStateException(f14625z[1]);
            }
            this.f14629d.write(this.f14626a, 0, read);
            a -= read;
        }
    }

    /* renamed from: a */
    public final void m16463a(int i, C4067k c4067k) {
        m16458e(i, 2);
        m16456d(c4067k.m16412c());
        c4067k.m16411a(this);
    }

    /* renamed from: b */
    public final void m16464b(int i, int i2) {
        m16458e(i, 0);
        m16456d(i2);
    }
}
