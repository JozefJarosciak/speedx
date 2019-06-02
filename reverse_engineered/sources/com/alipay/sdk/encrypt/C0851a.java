package com.alipay.sdk.encrypt;

import com.alibaba.fastjson.asm.Opcodes;
import com.avos.avoscloud.AVException;

/* renamed from: com.alipay.sdk.encrypt.a */
public final class C0851a {
    /* renamed from: a */
    private static final int f2107a = 128;
    /* renamed from: b */
    private static final int f2108b = 64;
    /* renamed from: c */
    private static final int f2109c = 24;
    /* renamed from: d */
    private static final int f2110d = 8;
    /* renamed from: e */
    private static final int f2111e = 16;
    /* renamed from: f */
    private static final int f2112f = 4;
    /* renamed from: g */
    private static final int f2113g = -128;
    /* renamed from: h */
    private static final char f2114h = '=';
    /* renamed from: i */
    private static final byte[] f2115i = new byte[128];
    /* renamed from: j */
    private static final char[] f2116j = new char[64];

    static {
        int i;
        int i2 = 0;
        for (i = 0; i < 128; i++) {
            f2115i[i] = (byte) -1;
        }
        for (i = 90; i >= 65; i--) {
            f2115i[i] = (byte) (i - 65);
        }
        for (i = AVException.INVALID_FILE_NAME; i >= 97; i--) {
            f2115i[i] = (byte) ((i - 97) + 26);
        }
        for (i = 57; i >= 48; i--) {
            f2115i[i] = (byte) ((i - 48) + 52);
        }
        f2115i[43] = (byte) 62;
        f2115i[47] = (byte) 63;
        for (i = 0; i <= 25; i++) {
            f2116j[i] = (char) (i + 65);
        }
        int i3 = 26;
        i = 0;
        while (i3 <= 51) {
            f2116j[i3] = (char) (i + 97);
            i3++;
            i++;
        }
        i = 52;
        while (i <= 61) {
            f2116j[i] = (char) (i2 + 48);
            i++;
            i2++;
        }
        f2116j[62] = '+';
        f2116j[63] = '/';
    }

    /* renamed from: a */
    private static boolean m3283a(char c) {
        return c == ' ' || c == '\r' || c == '\n' || c == '\t';
    }

    /* renamed from: b */
    private static boolean m3285b(char c) {
        return c == f2114h;
    }

    /* renamed from: c */
    private static boolean m3286c(char c) {
        return c < '' && f2115i[c] != (byte) -1;
    }

    /* renamed from: a */
    public static String m3282a(byte[] bArr) {
        int i = 0;
        if (bArr == null) {
            return null;
        }
        int length = bArr.length * 8;
        if (length == 0) {
            return "";
        }
        int i2 = length % 24;
        int i3 = length / 24;
        char[] cArr = new char[((i2 != 0 ? i3 + 1 : i3) * 4)];
        int i4 = 0;
        int i5 = 0;
        while (i4 < i3) {
            length = i + 1;
            byte b = bArr[i];
            int i6 = length + 1;
            byte b2 = bArr[length];
            int i7 = i6 + 1;
            byte b3 = bArr[i6];
            byte b4 = (byte) (b2 & 15);
            byte b5 = (byte) (b & 3);
            if ((b & f2113g) == 0) {
                i6 = (byte) (b >> 2);
            } else {
                byte b6 = (byte) ((b >> 2) ^ Opcodes.CHECKCAST);
            }
            if ((b2 & f2113g) == 0) {
                i = (byte) (b2 >> 4);
            } else {
                b = (byte) ((b2 >> 4) ^ 240);
            }
            if ((b3 & f2113g) == 0) {
                length = (byte) (b3 >> 6);
            } else {
                length = (byte) ((b3 >> 6) ^ AVException.UNSUPPORTED_SERVICE);
            }
            int i8 = i5 + 1;
            cArr[i5] = f2116j[i6];
            i6 = i8 + 1;
            cArr[i8] = f2116j[i | (b5 << 4)];
            i5 = i6 + 1;
            cArr[i6] = f2116j[length | (b4 << 2)];
            i = i5 + 1;
            cArr[i5] = f2116j[b3 & 63];
            i4++;
            i5 = i;
            i = i7;
        }
        byte b7;
        byte b8;
        if (i2 == 8) {
            b7 = bArr[i];
            b8 = (byte) (b7 & 3);
            i = i5 + 1;
            cArr[i5] = f2116j[(b7 & f2113g) == 0 ? (byte) (b7 >> 2) : (byte) ((b7 >> 2) ^ Opcodes.CHECKCAST)];
            length = i + 1;
            cArr[i] = f2116j[b8 << 4];
            i3 = length + 1;
            cArr[length] = f2114h;
            cArr[i3] = f2114h;
        } else if (i2 == 16) {
            b7 = bArr[i];
            b = bArr[i + 1];
            b6 = (byte) (b & 15);
            byte b9 = (byte) (b7 & 3);
            if ((b7 & f2113g) == 0) {
                i3 = (byte) (b7 >> 2);
            } else {
                b8 = (byte) ((b7 >> 2) ^ Opcodes.CHECKCAST);
            }
            length = (b & f2113g) == 0 ? (byte) (b >> 4) : (byte) ((b >> 4) ^ 240);
            i = i5 + 1;
            cArr[i5] = f2116j[i3];
            i3 = i + 1;
            cArr[i] = f2116j[length | (b9 << 4)];
            length = i3 + 1;
            cArr[i3] = f2116j[b6 << 2];
            cArr[length] = f2114h;
        }
        return new String(cArr);
    }

    /* renamed from: a */
    public static byte[] m3284a(String str) {
        if (str == null) {
            return null;
        }
        int i;
        int length;
        int i2;
        int i3;
        char[] toCharArray = str.toCharArray();
        if (toCharArray == null) {
            i = 0;
        } else {
            length = toCharArray.length;
            i2 = 0;
            i = 0;
            while (i2 < length) {
                char c = toCharArray[i2];
                i3 = (c == ' ' || c == '\r' || c == '\n' || c == '\t') ? 1 : 0;
                if (i3 == 0) {
                    i3 = i + 1;
                    toCharArray[i] = toCharArray[i2];
                } else {
                    i3 = i;
                }
                i2++;
                i = i3;
            }
        }
        if (i % 4 != 0) {
            return null;
        }
        int i4 = i / 4;
        if (i4 == 0) {
            return new byte[0];
        }
        char c2;
        byte[] bArr = new byte[(i4 * 3)];
        i = 0;
        i2 = 0;
        length = 0;
        while (length < i4 - 1) {
            int i5 = i + 1;
            char c3 = toCharArray[i];
            if (C0851a.m3286c(c3)) {
                i = i5 + 1;
                c2 = toCharArray[i5];
                if (C0851a.m3286c(c2)) {
                    int i6 = i + 1;
                    char c4 = toCharArray[i];
                    if (C0851a.m3286c(c4)) {
                        i = i6 + 1;
                        char c5 = toCharArray[i6];
                        if (C0851a.m3286c(c5)) {
                            byte b = f2115i[c3];
                            byte b2 = f2115i[c2];
                            byte b3 = f2115i[c4];
                            byte b4 = f2115i[c5];
                            int i7 = i2 + 1;
                            bArr[i2] = (byte) ((b << 2) | (b2 >> 4));
                            int i8 = i7 + 1;
                            bArr[i7] = (byte) (((b2 & 15) << 4) | ((b3 >> 2) & 15));
                            i2 = i8 + 1;
                            bArr[i8] = (byte) ((b3 << 6) | b4);
                            length++;
                        }
                    }
                }
            }
            return null;
        }
        i4 = i + 1;
        char c6 = toCharArray[i];
        if (C0851a.m3286c(c6)) {
            i5 = i4 + 1;
            char c7 = toCharArray[i4];
            if (C0851a.m3286c(c7)) {
                b = f2115i[c6];
                byte b5 = f2115i[c7];
                i = i5 + 1;
                c2 = toCharArray[i5];
                c6 = toCharArray[i];
                if (C0851a.m3286c(c2) && C0851a.m3286c(c6)) {
                    byte b6 = f2115i[c2];
                    byte b7 = f2115i[c6];
                    int i9 = i2 + 1;
                    bArr[i2] = (byte) ((b << 2) | (b5 >> 4));
                    i2 = i9 + 1;
                    bArr[i9] = (byte) (((b5 & 15) << 4) | ((b6 >> 2) & 15));
                    bArr[i2] = (byte) (b7 | (b6 << 6));
                    return bArr;
                } else if (C0851a.m3285b(c2) && C0851a.m3285b(c6)) {
                    if ((b5 & 15) != 0) {
                        return null;
                    }
                    r1 = new byte[((length * 3) + 1)];
                    System.arraycopy(bArr, 0, r1, 0, length * 3);
                    r1[i2] = (byte) ((b << 2) | (b5 >> 4));
                    return r1;
                } else if (C0851a.m3285b(c2) || !C0851a.m3285b(c6)) {
                    return null;
                } else {
                    byte b8 = f2115i[c2];
                    if ((b8 & 3) != 0) {
                        return null;
                    }
                    r1 = new byte[((length * 3) + 2)];
                    System.arraycopy(bArr, 0, r1, 0, length * 3);
                    i3 = i2 + 1;
                    r1[i2] = (byte) ((b << 2) | (b5 >> 4));
                    r1[i3] = (byte) (((b5 & 15) << 4) | ((b8 >> 2) & 15));
                    return r1;
                }
            }
        }
        return null;
    }

    /* renamed from: a */
    private static int m3281a(char[] cArr) {
        if (cArr == null) {
            return 0;
        }
        int length = cArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            Object obj;
            int i3;
            char c = cArr[i];
            if (c == ' ' || c == '\r' || c == '\n' || c == '\t') {
                obj = 1;
            } else {
                obj = null;
            }
            if (obj == null) {
                i3 = i2 + 1;
                cArr[i2] = cArr[i];
            } else {
                i3 = i2;
            }
            i++;
            i2 = i3;
        }
        return i2;
    }
}
