package cn.jpush.p005b.p006a.p009c;

import cn.jpush.android.util.C0497j;
import cn.jpush.android.util.ac;
import cn.jpush.p005b.p006a.p007a.C0514i;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;

/* renamed from: cn.jpush.b.a.c.a */
public final class C0551a {
    /* renamed from: z */
    private static final String f1168z;

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static {
        /*
        r0 = "\u001dDo\u0019n";
        r0 = r0.toCharArray();
        r1 = r0.length;
        r2 = 0;
        r3 = 1;
        if (r1 > r3) goto L_0x0027;
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
            case 0: goto L_0x0035;
            case 1: goto L_0x0038;
            case 2: goto L_0x003b;
            case 3: goto L_0x003e;
            default: goto L_0x0017;
        };
    L_0x0017:
        r5 = 86;
    L_0x0019:
        r5 = r5 ^ r6;
        r5 = (char) r5;
        r1[r2] = r5;
        r2 = r4 + 1;
        if (r0 != 0) goto L_0x0025;
    L_0x0021:
        r1 = r3;
        r4 = r2;
        r2 = r0;
        goto L_0x0010;
    L_0x0025:
        r1 = r0;
        r0 = r3;
    L_0x0027:
        if (r1 > r2) goto L_0x000b;
    L_0x0029:
        r1 = new java.lang.String;
        r1.<init>(r0);
        r0 = r1.intern();
        f1168z = r0;
        return;
    L_0x0035:
        r5 = 72;
        goto L_0x0019;
    L_0x0038:
        r5 = 16;
        goto L_0x0019;
    L_0x003b:
        r5 = 41;
        goto L_0x0019;
    L_0x003e:
        r5 = 52;
        goto L_0x0019;
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.jpush.b.a.c.a.<clinit>():void");
    }

    /* renamed from: a */
    public static String m1899a(ByteBuffer byteBuffer, C0514i c0514i) {
        short b = C0497j.m1756b(byteBuffer, c0514i);
        if (b < (short) 0) {
            ac.m1586d();
            return null;
        }
        byte[] bArr = new byte[b];
        C0497j.m1754a(byteBuffer, bArr, c0514i);
        try {
            return new String(bArr, f1168z);
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /* renamed from: a */
    public static void m1900a(byte[] bArr, int i, int i2) {
        System.arraycopy(new byte[]{(byte) (i >>> 24), (byte) (i >>> 16), (byte) (i >>> 8), (byte) i}, 0, bArr, i2, 4);
    }

    /* renamed from: a */
    public static void m1901a(byte[] bArr, String str, int i) {
        Object bytes = str.getBytes();
        System.arraycopy(bytes, 0, bArr, i, bytes.length);
    }

    /* renamed from: a */
    public static byte[] m1902a(String str) {
        if (str == null || "".equals(str)) {
            return new byte[]{(byte) 0, (byte) 0};
        }
        Object obj = null;
        try {
            obj = str.getBytes(f1168z);
        } catch (UnsupportedEncodingException e) {
        }
        short length = (short) obj.length;
        Object obj2 = new byte[(length + 2)];
        System.arraycopy(new byte[]{(byte) (length >>> 8), (byte) length}, 0, obj2, 0, 2);
        System.arraycopy(obj, 0, obj2, 2, length);
        return obj2;
    }

    /* renamed from: a */
    public static byte[] m1903a(ByteBuffer byteBuffer) {
        try {
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.get(bArr);
            return bArr;
        } catch (NegativeArraySizeException e) {
            ac.m1586d();
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    /* renamed from: b */
    public static byte[] m1904b(ByteBuffer byteBuffer) {
        try {
            byte[] bArr = new byte[byteBuffer.remaining()];
            byteBuffer.asReadOnlyBuffer().flip();
            byteBuffer.get(bArr);
            return bArr;
        } catch (NegativeArraySizeException e) {
            ac.m1586d();
            return null;
        } catch (Exception e2) {
            return null;
        }
    }

    /* renamed from: c */
    public static String m1905c(ByteBuffer byteBuffer) {
        try {
            byte[] bArr = new byte[byteBuffer.getShort()];
            byteBuffer.get(bArr);
            return new String(bArr, f1168z);
        } catch (UnsupportedEncodingException e) {
        } catch (Exception e2) {
        }
        return null;
    }
}
