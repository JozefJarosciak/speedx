package com.tencent.wxop.stat.common;

/* renamed from: com.tencent.wxop.stat.common.g */
public class C4535g {
    /* renamed from: a */
    static final /* synthetic */ boolean f16073a = (!C4535g.class.desiredAssertionStatus());

    private C4535g() {
    }

    /* renamed from: a */
    public static byte[] m18030a(byte[] bArr, int i) {
        return C4535g.m18031a(bArr, 0, bArr.length, i);
    }

    /* renamed from: a */
    public static byte[] m18031a(byte[] bArr, int i, int i2, int i3) {
        C4537i c4537i = new C4537i(i3, new byte[((i2 * 3) / 4)]);
        if (!c4537i.m18034a(bArr, i, i2, true)) {
            throw new IllegalArgumentException("bad base-64");
        } else if (c4537i.b == c4537i.a.length) {
            return c4537i.a;
        } else {
            Object obj = new byte[c4537i.b];
            System.arraycopy(c4537i.a, 0, obj, 0, c4537i.b);
            return obj;
        }
    }

    /* renamed from: b */
    public static byte[] m18032b(byte[] bArr, int i) {
        return C4535g.m18033b(bArr, 0, bArr.length, i);
    }

    /* renamed from: b */
    public static byte[] m18033b(byte[] bArr, int i, int i2, int i3) {
        C4538j c4538j = new C4538j(i3, null);
        int i4 = (i2 / 3) * 4;
        if (!c4538j.f16085d) {
            switch (i2 % 3) {
                case 0:
                    break;
                case 1:
                    i4 += 2;
                    break;
                case 2:
                    i4 += 3;
                    break;
                default:
                    break;
            }
        } else if (i2 % 3 > 0) {
            i4 += 4;
        }
        if (c4538j.f16086e && i2 > 0) {
            i4 += (c4538j.f16087f ? 2 : 1) * (((i2 - 1) / 57) + 1);
        }
        c4538j.a = new byte[i4];
        c4538j.m18035a(bArr, i, i2, true);
        if (f16073a || c4538j.b == i4) {
            return c4538j.a;
        }
        throw new AssertionError();
    }
}
