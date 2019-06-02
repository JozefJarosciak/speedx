package cn.jpush.p005b.p006a.p007a;

import cn.jpush.p005b.p006a.p009c.C0551a;
import cn.jpush.p005b.p006a.p009c.C0552b;
import cn.jpush.p005b.p006a.p009c.C0553c;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

/* renamed from: cn.jpush.b.a.a.g */
public abstract class C0513g {
    /* renamed from: z */
    private static final String[] f1066z;
    /* renamed from: a */
    private boolean f1067a = true;
    /* renamed from: e */
    protected C0521f f1068e;
    /* renamed from: f */
    protected ByteBuffer f1069f;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 6;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "&\u001fX<.\f\tX*.H\u0000\u0019,2\r^";
        r0 = -1;
        r4 = r3;
    L_0x0008:
        r1 = r1.toCharArray();
        r5 = r1.length;
        r6 = 0;
        r7 = 1;
        if (r5 > r7) goto L_0x002d;
    L_0x0011:
        r7 = r1;
        r8 = r6;
        r11 = r5;
        r5 = r1;
        r1 = r11;
    L_0x0016:
        r10 = r5[r6];
        r9 = r8 % 5;
        switch(r9) {
            case 0: goto L_0x0068;
            case 1: goto L_0x006b;
            case 2: goto L_0x006e;
            case 3: goto L_0x0071;
            default: goto L_0x001d;
        };
    L_0x001d:
        r9 = 65;
    L_0x001f:
        r9 = r9 ^ r10;
        r9 = (char) r9;
        r5[r6] = r9;
        r6 = r8 + 1;
        if (r1 != 0) goto L_0x002b;
    L_0x0027:
        r5 = r7;
        r8 = r6;
        r6 = r1;
        goto L_0x0016;
    L_0x002b:
        r5 = r1;
        r1 = r7;
    L_0x002d:
        if (r5 > r6) goto L_0x0011;
    L_0x002f:
        r5 = new java.lang.String;
        r5.<init>(r1);
        r1 = r5.intern();
        switch(r0) {
            case 0: goto L_0x0043;
            case 1: goto L_0x004b;
            case 2: goto L_0x0053;
            case 3: goto L_0x005b;
            case 4: goto L_0x0063;
            default: goto L_0x003b;
        };
    L_0x003b:
        r3[r2] = r1;
        r2 = 1;
        r1 = "H]X";
        r0 = 0;
        r3 = r4;
        goto L_0x0008;
    L_0x0043:
        r3[r2] = r1;
        r2 = 2;
        r1 = "3\"\u001d/4\r\u0003\f\u0003";
        r0 = 1;
        r3 = r4;
        goto L_0x0008;
    L_0x004b:
        r3[r2] = r1;
        r2 = 3;
        r1 = "3\"\u001d-1\u0007\u001e\u000b;\u001c";
        r0 = 2;
        r3 = r4;
        goto L_0x0008;
    L_0x0053:
        r3[r2] = r1;
        r2 = 4;
        r1 = "DP\u001a'5\r\u0003B~";
        r0 = 3;
        r3 = r4;
        goto L_0x0008;
    L_0x005b:
        r3[r2] = r1;
        r2 = 5;
        r1 = ".\u0019\u0016?-H]X2$\u0006J";
        r0 = 4;
        r3 = r4;
        goto L_0x0008;
    L_0x0063:
        r3[r2] = r1;
        f1066z = r4;
        return;
    L_0x0068:
        r9 = 104; // 0x68 float:1.46E-43 double:5.14E-322;
        goto L_0x001f;
    L_0x006b:
        r9 = 112; // 0x70 float:1.57E-43 double:5.53E-322;
        goto L_0x001f;
    L_0x006e:
        r9 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        goto L_0x001f;
    L_0x0071:
        r9 = 94;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.b.a.a.g.<clinit>():void");
    }

    public C0513g(boolean z, int i, int i2, long j) {
        this.f1068e = new C0521f(true, i, i2, j);
        this.f1069f = ByteBuffer.allocate(7168);
    }

    public C0513g(boolean z, int i, int i2, long j, int i3, long j2) {
        this.f1068e = new C0521f(false, 0, i, i2, j, -1, j2);
        this.f1069f = ByteBuffer.allocate(7168);
    }

    public C0513g(boolean z, C0521f c0521f, ByteBuffer byteBuffer) {
        this.f1068e = c0521f;
        if (byteBuffer != null) {
            this.f1069f = byteBuffer;
            mo2232c();
            return;
        }
        C0552b.m1906a(f1066z[0]);
    }

    /* renamed from: a */
    private final byte[] mo2234a() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] b = C0551a.m1904b(this.f1069f);
        this.f1068e.f1089a = (this.f1067a ? 24 : 20) + b.length;
        try {
            byteArrayOutputStream.write(this.f1068e.m1850c());
            byteArrayOutputStream.write(b);
        } catch (Exception e) {
        }
        byte[] toByteArray = byteArrayOutputStream.toByteArray();
        new StringBuilder(f1066z[5]).append(toByteArray.length).append(f1066z[4]).append(C0553c.m1908a(toByteArray));
        return toByteArray;
    }

    /* renamed from: a */
    protected final void m1819a(int i) {
        this.f1069f.put((byte) i);
    }

    /* renamed from: a */
    protected final void m1820a(long j) {
        this.f1069f.putLong(j);
    }

    /* renamed from: a */
    protected final void m1821a(String str) {
        this.f1069f.put(C0551a.m1902a(str));
    }

    /* renamed from: a */
    protected final void m1822a(byte[] bArr) {
        this.f1069f.put(bArr);
    }

    /* renamed from: b */
    protected abstract void mo2231b();

    /* renamed from: b */
    protected final void m1824b(int i) {
        this.f1069f.putShort((short) i);
    }

    /* renamed from: c */
    protected abstract void mo2232c();

    /* renamed from: c */
    protected final void m1826c(int i) {
        this.f1069f.putInt(i);
    }

    /* renamed from: e */
    public final int m1827e() {
        return this.f1068e.f1091c;
    }

    /* renamed from: f */
    public final C0521f m1828f() {
        return this.f1068e;
    }

    /* renamed from: g */
    public final byte[] m1829g() {
        this.f1069f.clear();
        mo2231b();
        this.f1069f.flip();
        return mo2234a();
    }

    public String toString() {
        return (this.f1067a ? f1066z[2] : f1066z[3]) + f1066z[1] + this.f1068e.toString();
    }
}
