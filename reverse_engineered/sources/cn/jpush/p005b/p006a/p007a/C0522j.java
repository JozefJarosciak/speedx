package cn.jpush.p005b.p006a.p007a;

import cn.jpush.android.util.C0497j;
import cn.jpush.p005b.p006a.p009c.C0551a;
import cn.jpush.p005b.p006a.p009c.C0552b;
import java.nio.ByteBuffer;

/* renamed from: cn.jpush.b.a.a.j */
public final class C0522j extends C0514i {
    /* renamed from: z */
    private static final String[] f1096z;
    /* renamed from: a */
    int f1097a;
    /* renamed from: b */
    int f1098b;
    /* renamed from: c */
    String f1099c;
    /* renamed from: d */
    int f1100d;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 7;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u0010F\u0005O\u0013J\u0003\u0004|\u0004N\u0015\u001fE\u000f\u0006";
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
        r9 = 97;
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
        r1 = "\u001cKV";
        r0 = 0;
        r3 = r4;
        goto L_0x0008;
    L_0x0043:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\u0010F\u0005O\u0012O\u000f\u0019D*Y\u001fL";
        r0 = 1;
        r3 = r4;
        goto L_0x0008;
    L_0x004b:
        r3[r2] = r1;
        r2 = 3;
        r1 = "g*\u0019M\bR4\u0013Y\u0011S\b\u0005O<\u001cKVY\bX\\";
        r0 = 2;
        r3 = r4;
        goto L_0x0008;
    L_0x0053:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0010F\u0005O\u0013J\u0003\u0004~\bQ\u0003L";
        r0 = 3;
        r3 = r4;
        goto L_0x0008;
    L_0x005b:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u0010F\u001bO\u0012O\u0007\u0011O[";
        r0 = 4;
        r3 = r4;
        goto L_0x0008;
    L_0x0063:
        r3[r2] = r1;
        r2 = 6;
        r1 = "n\u0003\u0005Z\u000eR\u0015\u0013\n\u0004N\u0014\u0019XA\u0011F\u0015E\u0005Y\\";
        r0 = 5;
        r3 = r4;
        goto L_0x0008;
    L_0x006b:
        r3[r2] = r1;
        f1096z = r4;
        return;
    L_0x0070:
        r9 = 60;
        goto L_0x001f;
    L_0x0073:
        r9 = 102; // 0x66 float:1.43E-43 double:5.04E-322;
        goto L_0x001f;
    L_0x0076:
        r9 = 118; // 0x76 float:1.65E-43 double:5.83E-322;
        goto L_0x001f;
    L_0x0079:
        r9 = 42;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.b.a.a.j.<clinit>():void");
    }

    public C0522j(C0521f c0521f, ByteBuffer byteBuffer) {
        super(c0521f, byteBuffer);
    }

    /* renamed from: a */
    public final int mo2234a() {
        return this.f1097a;
    }

    /* renamed from: b */
    public final void mo2231b() {
        super.mo2231b();
        m1826c(this.f1097a);
        m1824b(this.f1098b);
        m1821a(this.f1099c);
        m1826c(this.f1100d);
    }

    /* renamed from: c */
    public final void mo2232c() {
        super.mo2232c();
        if (this.g > 0) {
            C0552b.m1907b(new StringBuilder(f1096z[6]).append(this.g).append(f1096z[5]).append(this.h).toString());
            return;
        }
        ByteBuffer byteBuffer = this.f;
        this.f1097a = C0497j.m1753a(byteBuffer, this);
        this.f1098b = C0497j.m1756b(byteBuffer, this);
        this.f1099c = C0551a.m1899a(byteBuffer, this);
        this.f1100d = C0497j.m1753a(byteBuffer, this);
    }

    /* renamed from: d */
    public final int m1854d() {
        return this.f1100d;
    }

    public final String toString() {
        return new StringBuilder(f1096z[3]).append(this.f1097a).append(f1096z[0]).append(this.f1098b).append(f1096z[2]).append(this.f1099c).append(f1096z[4]).append(this.f1100d).append(f1096z[1]).append(super.toString()).toString();
    }
}
