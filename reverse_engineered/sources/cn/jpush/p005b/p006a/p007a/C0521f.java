package cn.jpush.p005b.p006a.p007a;

import cn.jpush.p005b.p006a.p009c.C0551a;
import java.nio.ByteBuffer;

/* renamed from: cn.jpush.b.a.a.f */
public final class C0521f {
    /* renamed from: z */
    private static final String[] f1088z;
    /* renamed from: a */
    int f1089a;
    /* renamed from: b */
    int f1090b;
    /* renamed from: c */
    int f1091c;
    /* renamed from: d */
    Long f1092d;
    /* renamed from: e */
    int f1093e;
    /* renamed from: f */
    long f1094f;
    /* renamed from: g */
    private boolean f1095g;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 7;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "ti\u000b\\^b";
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
            case 0: goto L_0x0070;
            case 1: goto L_0x0073;
            case 2: goto L_0x0076;
            case 3: goto L_0x0079;
            default: goto L_0x001d;
        };
    L_0x001d:
        r9 = 58;
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
            case 5: goto L_0x006b;
            default: goto L_0x003b;
        };
    L_0x003b:
        r3[r2] = r1;
        r2 = 1;
        r1 = "ti\n\\^b";
        r0 = 0;
        r3 = r4;
        goto L_0x0008;
    L_0x0043:
        r3[r2] = r1;
        r2 = 2;
        r1 = "ti\u0012@S<s";
        r0 = 1;
        r3 = r4;
        goto L_0x0008;
    L_0x004b:
        r3[r2] = r1;
        r2 = 3;
        r1 = "ti\u001bZW5(\u0016Q\u0000";
        r0 = 2;
        r3 = r4;
        goto L_0x0008;
    L_0x0053:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0003\u00030P[<\u0014X\u0018\u001a4,\u0016\u000f";
        r0 = 3;
        r3 = r4;
        goto L_0x0008;
    L_0x005b:
        r3[r2] = r1;
        r2 = 5;
        r1 = "ti\u000ePH+ \u0017[\u0000";
        r0 = 4;
        r3 = r4;
        goto L_0x0008;
    L_0x0063:
        r3[r2] = r1;
        r2 = 6;
        r1 = "\f!\u001d\u0015R=(\u001c\u0015S+i\u0016ZNx \u0016\\N1(\u0014\\@=-XL_,g";
        r0 = 5;
        r3 = r4;
        goto L_0x0008;
    L_0x006b:
        r3[r2] = r1;
        f1088z = r4;
        return;
    L_0x0070:
        r9 = 88;
        goto L_0x001f;
    L_0x0073:
        r9 = 73;
        goto L_0x001f;
    L_0x0076:
        r9 = 120; // 0x78 float:1.68E-43 double:5.93E-322;
        goto L_0x001f;
    L_0x0079:
        r9 = 53;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.b.a.a.f.<clinit>():void");
    }

    public C0521f(boolean z, int i, int i2, int i3, long j, int i4, long j2) {
        this.f1095g = false;
        this.f1095g = z;
        this.f1089a = 0;
        this.f1090b = i2;
        this.f1091c = i3;
        this.f1092d = Long.valueOf(j);
        this.f1093e = i4;
        this.f1094f = j2;
    }

    public C0521f(boolean z, int i, int i2, long j) {
        this(z, 0, i, i2, j, 0, 0);
    }

    public C0521f(boolean z, byte[] bArr) {
        this.f1095g = false;
        this.f1095g = false;
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        this.f1089a = wrap.getShort();
        this.f1090b = wrap.get();
        this.f1091c = wrap.get();
        this.f1092d = Long.valueOf(wrap.getLong());
        this.f1094f = wrap.getLong();
    }

    /* renamed from: a */
    public final Long m1848a() {
        return this.f1092d;
    }

    /* renamed from: b */
    public final long m1849b() {
        return this.f1094f;
    }

    /* renamed from: c */
    public final byte[] m1850c() {
        if (this.f1089a == 0) {
            throw new IllegalStateException(f1088z[6]);
        }
        ByteBuffer allocate = ByteBuffer.allocate(24);
        allocate.putShort((short) this.f1089a);
        allocate.put((byte) this.f1090b);
        allocate.put((byte) this.f1091c);
        allocate.putLong(this.f1092d.longValue());
        if (this.f1095g) {
            allocate.putInt(this.f1093e);
        }
        allocate.putLong(this.f1094f);
        allocate.flip();
        return C0551a.m1903a(allocate);
    }

    public final String toString() {
        return new StringBuilder(f1088z[4]).append(this.f1089a).append(f1088z[5]).append(this.f1090b).append(f1088z[3]).append(this.f1091c).append(f1088z[1]).append(this.f1092d).append(this.f1095g ? new StringBuilder(f1088z[0]).append(this.f1093e).toString() : "").append(f1088z[2]).append(this.f1094f).toString();
    }
}
