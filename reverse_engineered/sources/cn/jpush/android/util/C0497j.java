package cn.jpush.android.util;

import cn.jpush.android.api.C0407c;
import cn.jpush.p005b.p006a.p007a.C0514i;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;

/* renamed from: cn.jpush.android.util.j */
public final class C0497j {
    /* renamed from: z */
    private static final String[] f1038z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = 6;
        r3 = new java.lang.String[r0];
        r2 = 0;
        r1 = "mI[B7=LHE3=M[C=o\bZE3~C}C3~M\u0013";
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
        r9 = 82;
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
        r1 = "_Q]T\u0010hNOT H\\@]!";
        r0 = 0;
        r3 = r4;
        goto L_0x0008;
    L_0x0043:
        r3[r2] = r1;
        r2 = 2;
        r1 = "Q]T\u0010hNOT =AZ\u0011<hDE";
        r0 = 1;
        r3 = r4;
        goto L_0x0008;
    L_0x004b:
        r3[r2] = r1;
        r2 = 3;
        r1 = "aJPE7]OW7o\u0012";
        r0 = 2;
        r3 = r4;
        goto L_0x0008;
    L_0x0053:
        r3[r2] = r1;
        r2 = 4;
        r1 = "Q]T\u0010hNOT =AGW='";
        r0 = 3;
        r3 = r4;
        goto L_0x0008;
    L_0x005b:
        r3[r2] = r1;
        r2 = 5;
        r1 = "wzLB\"rFZTrt[\t_'qD";
        r0 = 4;
        r3 = r4;
        goto L_0x0008;
    L_0x0063:
        r3[r2] = r1;
        f1038z = r4;
        return;
    L_0x0068:
        r9 = 29;
        goto L_0x001f;
    L_0x006b:
        r9 = 40;
        goto L_0x001f;
    L_0x006e:
        r9 = 41;
        goto L_0x001f;
    L_0x0071:
        r9 = 49;
        goto L_0x001f;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.android.util.j.<clinit>():void");
    }

    /* renamed from: a */
    public static int m1753a(ByteBuffer byteBuffer, C0514i c0514i) {
        try {
            return byteBuffer.getInt();
        } catch (BufferUnderflowException e) {
            C0497j.m1755a(e.fillInStackTrace(), c0514i, byteBuffer);
            if (c0514i != null) {
                c0514i.f1071g = 10000;
            }
            return -1;
        } catch (BufferOverflowException e2) {
            C0497j.m1755a(e2.fillInStackTrace(), c0514i, byteBuffer);
            if (c0514i != null) {
                c0514i.f1071g = 10000;
            }
            return -1;
        } catch (Exception e3) {
            C0497j.m1755a(e3.fillInStackTrace(), c0514i, byteBuffer);
            if (c0514i != null) {
                c0514i.f1071g = 10000;
            }
            return -1;
        }
    }

    /* renamed from: a */
    public static ByteBuffer m1754a(ByteBuffer byteBuffer, byte[] bArr, C0514i c0514i) {
        try {
            return byteBuffer.get(bArr);
        } catch (BufferUnderflowException e) {
            C0497j.m1755a(e.fillInStackTrace(), c0514i, byteBuffer);
            if (c0514i != null) {
                c0514i.f1071g = 10000;
            }
            return null;
        } catch (BufferOverflowException e2) {
            C0497j.m1755a(e2.fillInStackTrace(), c0514i, byteBuffer);
            if (c0514i != null) {
                c0514i.f1071g = 10000;
            }
            return null;
        } catch (Exception e3) {
            C0497j.m1755a(e3.fillInStackTrace(), c0514i, byteBuffer);
            if (c0514i != null) {
                c0514i.f1071g = 10000;
            }
            return null;
        }
    }

    /* renamed from: a */
    private static void m1755a(Throwable th, C0514i c0514i, ByteBuffer byteBuffer) {
        C0407c a = C0407c.m1169a();
        StringBuilder stringBuilder = new StringBuilder();
        if (c0514i != null) {
            stringBuilder.append(c0514i == null ? f1038z[5] : c0514i.toString());
            stringBuilder.append(new StringBuilder(f1038z[3]).append(byteBuffer == null ? f1038z[2] : byteBuffer.toString()).toString());
        }
        ac.m1589e(f1038z[1], new StringBuilder(f1038z[4]).append(stringBuilder.toString()).toString());
        ac.m1589e(f1038z[1], new StringBuilder(f1038z[0]).append(th.getStackTrace().toString()).toString());
        a.m1177a(th, stringBuilder.toString());
    }

    /* renamed from: b */
    public static short m1756b(ByteBuffer byteBuffer, C0514i c0514i) {
        try {
            return byteBuffer.getShort();
        } catch (BufferUnderflowException e) {
            C0497j.m1755a(e.fillInStackTrace(), c0514i, byteBuffer);
            if (c0514i != null) {
                c0514i.f1071g = 10000;
            }
            return (short) -1;
        } catch (BufferOverflowException e2) {
            C0497j.m1755a(e2.fillInStackTrace(), c0514i, byteBuffer);
            if (c0514i != null) {
                c0514i.f1071g = 10000;
            }
            return (short) -1;
        } catch (Exception e3) {
            C0497j.m1755a(e3.fillInStackTrace(), c0514i, byteBuffer);
            if (c0514i != null) {
                c0514i.f1071g = 10000;
            }
            return (short) -1;
        }
    }

    /* renamed from: c */
    public static Byte m1757c(ByteBuffer byteBuffer, C0514i c0514i) {
        try {
            return Byte.valueOf(byteBuffer.get());
        } catch (BufferUnderflowException e) {
            C0497j.m1755a(e.fillInStackTrace(), c0514i, byteBuffer);
            if (c0514i != null) {
                c0514i.f1071g = 10000;
            }
            return null;
        } catch (BufferOverflowException e2) {
            C0497j.m1755a(e2.fillInStackTrace(), c0514i, byteBuffer);
            if (c0514i != null) {
                c0514i.f1071g = 10000;
            }
            return null;
        } catch (Exception e3) {
            C0497j.m1755a(e3.fillInStackTrace(), c0514i, byteBuffer);
            if (c0514i != null) {
                c0514i.f1071g = 10000;
            }
            return null;
        }
    }

    /* renamed from: d */
    public static long m1758d(ByteBuffer byteBuffer, C0514i c0514i) {
        try {
            return byteBuffer.getLong();
        } catch (BufferUnderflowException e) {
            C0497j.m1755a(e.fillInStackTrace(), c0514i, byteBuffer);
            if (c0514i != null) {
                c0514i.f1071g = 10000;
            }
            return 0;
        } catch (BufferOverflowException e2) {
            C0497j.m1755a(e2.fillInStackTrace(), c0514i, byteBuffer);
            if (c0514i != null) {
                c0514i.f1071g = 10000;
            }
            return 0;
        } catch (Exception e3) {
            C0497j.m1755a(e3.fillInStackTrace(), c0514i, byteBuffer);
            if (c0514i != null) {
                c0514i.f1071g = 10000;
            }
            return 0;
        }
    }
}
