package cn.jpush.p005b.p006a.p007a;

import cn.jpush.android.util.C0497j;
import cn.jpush.p005b.p006a.p009c.C0551a;
import cn.jpush.p005b.p006a.p009c.C0552b;
import java.nio.ByteBuffer;

/* renamed from: cn.jpush.b.a.a.l */
public final class C0524l extends C0514i {
    /* renamed from: z */
    private static final String[] f1105z;
    /* renamed from: a */
    long f1106a;
    /* renamed from: b */
    String f1107b;
    /* renamed from: c */
    String f1108c;
    /* renamed from: d */
    String f1109d;
    /* renamed from: i */
    String f1110i;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 7;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "\u0005Y\u00106X@\u001a\u0011\u001aJ\u0013";
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
        r9 = 46;
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
        r1 = "\u0005Y\u00042]Z\u000e\u001b!J\u0013";
        r0 = 0;
        r3 = r4;
        goto L_0x0008;
    L_0x0043:
        r3[r2] = r1;
        r2 = 2;
        r1 = "\tTT";
        r0 = 1;
        r3 = r4;
        goto L_0x0008;
    L_0x004b:
        r3[r2] = r1;
        r2 = 3;
        r1 = "r+\u00114GZ\r\u0011!|L\n\u0004<@Z\u001c)s\u0003\t\u0013\u0001:J\u0013";
        r0 = 2;
        r3 = r4;
        goto L_0x0008;
    L_0x0053:
        r3[r2] = r1;
        r2 = 4;
        r1 = "\u0005Y\u00066I`\u001dN";
        r0 = 3;
        r3 = r4;
        goto L_0x0008;
    L_0x005b:
        r3[r2] = r1;
        r2 = 5;
        r1 = "\u0005Y\u00196]Z\u0018\u00136\u0014";
        r0 = 4;
        r3 = r4;
        goto L_0x0008;
    L_0x0063:
        r3[r2] = r1;
        r2 = 6;
        r1 = "{\u001c\u0007#AG\n\u0011sK[\u000b\u001b!\u000e\u0004Y\u0017<JLC";
        r0 = 5;
        r3 = r4;
        goto L_0x0008;
    L_0x006b:
        r3[r2] = r1;
        f1105z = r4;
        return;
    L_0x0070:
        r9 = 41;
        goto L_0x001f;
    L_0x0073:
        r9 = 121; // 0x79 float:1.7E-43 double:6.0E-322;
        goto L_0x001f;
    L_0x0076:
        r9 = 116; // 0x74 float:1.63E-43 double:5.73E-322;
        goto L_0x001f;
    L_0x0079:
        r9 = 83;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.b.a.a.l.<clinit>():void");
    }

    public C0524l(C0521f c0521f, ByteBuffer byteBuffer) {
        super(c0521f, byteBuffer);
    }

    /* renamed from: a */
    public final long mo2234a() {
        return this.f1106a;
    }

    /* renamed from: b */
    protected final void mo2231b() {
        super.mo2231b();
        m1820a(this.f1106a);
        m1821a(this.f1107b);
        m1821a(this.f1108c);
        m1821a(this.f1109d);
    }

    /* renamed from: c */
    protected final void mo2232c() {
        super.mo2232c();
        if (this.g > 0) {
            C0552b.m1907b(new StringBuilder(f1105z[6]).append(this.g).append(f1105z[5]).append(this.h).toString());
            return;
        }
        ByteBuffer byteBuffer = this.f;
        if (this.g == 0) {
            this.f1106a = C0497j.m1758d(byteBuffer, this);
            this.f1107b = C0551a.m1899a(byteBuffer, this);
            this.f1108c = C0551a.m1899a(byteBuffer, this);
            this.f1109d = C0551a.m1899a(byteBuffer, this);
        } else if (this.g == 1007) {
            this.f1110i = C0551a.m1899a(byteBuffer, this);
        }
    }

    /* renamed from: d */
    public final String m1863d() {
        return this.f1107b;
    }

    /* renamed from: h */
    public final String m1864h() {
        return this.f1108c;
    }

    /* renamed from: i */
    public final String m1865i() {
        return this.f1109d;
    }

    public final String toString() {
        return new StringBuilder(f1105z[3]).append(this.f1106a).append(f1105z[1]).append(this.f1107b).append(f1105z[4]).append(this.f1108c).append(f1105z[0]).append(this.f1109d).append(f1105z[2]).append(super.toString()).toString();
    }
}
