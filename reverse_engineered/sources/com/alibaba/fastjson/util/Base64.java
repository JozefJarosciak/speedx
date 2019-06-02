package com.alibaba.fastjson.util;

import java.util.Arrays;

public class Base64 {
    public static final char[] CA = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/".toCharArray();
    public static final int[] IA = new int[256];

    static {
        Arrays.fill(IA, -1);
        int length = CA.length;
        for (int i = 0; i < length; i++) {
            IA[CA[i]] = i;
        }
        IA[61] = 0;
    }

    public static final byte[] decodeFast(char[] cArr, int i, int i2) {
        int i3 = 0;
        if (i2 == 0) {
            return new byte[0];
        }
        int i4 = (i + i2) - 1;
        int i5 = i;
        while (i5 < i4 && IA[cArr[i5]] < 0) {
            i5++;
        }
        int i6 = i4;
        while (i6 > 0 && IA[cArr[i6]] < 0) {
            i6--;
        }
        int i7 = cArr[i6] == '=' ? cArr[i6 + -1] == '=' ? 2 : 1 : 0;
        int i8 = (i6 - i5) + 1;
        if (i2 > 76) {
            if (cArr[76] == '\r') {
                i4 = i8 / 78;
            } else {
                i4 = 0;
            }
            i4 <<= 1;
        } else {
            i4 = 0;
        }
        int i9 = (((i8 - i4) * 6) >> 3) - i7;
        byte[] bArr = new byte[i9];
        int i10 = (i9 / 3) * 3;
        i8 = 0;
        int i11 = 0;
        while (i11 < i10) {
            int i12 = i5 + 1;
            int i13 = i12 + 1;
            i5 = (IA[cArr[i5]] << 18) | (IA[cArr[i12]] << 12);
            i12 = i13 + 1;
            int i14 = (IA[cArr[i13]] << 6) | i5;
            i5 = i12 + 1;
            i14 |= IA[cArr[i12]];
            i12 = i11 + 1;
            bArr[i11] = (byte) (i14 >> 16);
            i13 = i12 + 1;
            bArr[i12] = (byte) (i14 >> 8);
            i11 = i13 + 1;
            bArr[i13] = (byte) i14;
            if (i4 > 0) {
                i8++;
                if (i8 == 19) {
                    i5 += 2;
                    i8 = 0;
                }
            }
        }
        if (i11 < i9) {
            i5 = 0;
            for (i4 = i5; i4 <= i6 - i7; i4++) {
                i3++;
                i5 = (IA[cArr[i4]] << (18 - (i3 * 6))) | i5;
            }
            i4 = 16;
            i3 = i11;
            while (i3 < i9) {
                i8 = i3 + 1;
                bArr[i3] = (byte) (i5 >> i4);
                i4 -= 8;
                i3 = i8;
            }
        }
        return bArr;
    }

    public static final byte[] decodeFast(String str, int i, int i2) {
        int i3 = 0;
        if (i2 == 0) {
            return new byte[0];
        }
        int i4 = (i + i2) - 1;
        int i5 = i;
        while (i5 < i4 && IA[str.charAt(i5)] < 0) {
            i5++;
        }
        int i6 = i4;
        while (i6 > 0 && IA[str.charAt(i6)] < 0) {
            i6--;
        }
        int i7 = str.charAt(i6) == '=' ? str.charAt(i6 + -1) == '=' ? 2 : 1 : 0;
        int i8 = (i6 - i5) + 1;
        if (i2 > 76) {
            if (str.charAt(76) == '\r') {
                i4 = i8 / 78;
            } else {
                i4 = 0;
            }
            i4 <<= 1;
        } else {
            i4 = 0;
        }
        int i9 = (((i8 - i4) * 6) >> 3) - i7;
        byte[] bArr = new byte[i9];
        int i10 = (i9 / 3) * 3;
        i8 = 0;
        int i11 = 0;
        while (i11 < i10) {
            int i12 = i5 + 1;
            int i13 = i12 + 1;
            i5 = (IA[str.charAt(i5)] << 18) | (IA[str.charAt(i12)] << 12);
            i12 = i13 + 1;
            int i14 = (IA[str.charAt(i13)] << 6) | i5;
            i5 = i12 + 1;
            i14 |= IA[str.charAt(i12)];
            i12 = i11 + 1;
            bArr[i11] = (byte) (i14 >> 16);
            i13 = i12 + 1;
            bArr[i12] = (byte) (i14 >> 8);
            i11 = i13 + 1;
            bArr[i13] = (byte) i14;
            if (i4 > 0) {
                i8++;
                if (i8 == 19) {
                    i5 += 2;
                    i8 = 0;
                }
            }
        }
        if (i11 < i9) {
            i5 = 0;
            for (i4 = i5; i4 <= i6 - i7; i4++) {
                i3++;
                i5 = (IA[str.charAt(i4)] << (18 - (i3 * 6))) | i5;
            }
            i4 = 16;
            i3 = i11;
            while (i3 < i9) {
                i8 = i3 + 1;
                bArr[i3] = (byte) (i5 >> i4);
                i4 -= 8;
                i3 = i8;
            }
        }
        return bArr;
    }

    public static final byte[] decodeFast(String str) {
        int i = 0;
        int length = str.length();
        if (length == 0) {
            return new byte[0];
        }
        int i2 = length - 1;
        int i3 = 0;
        while (i3 < i2 && IA[str.charAt(i3) & 255] < 0) {
            i3++;
        }
        int i4 = i2;
        while (i4 > 0 && IA[str.charAt(i4) & 255] < 0) {
            i4--;
        }
        int i5 = str.charAt(i4) == '=' ? str.charAt(i4 + -1) == '=' ? 2 : 1 : 0;
        int i6 = (i4 - i3) + 1;
        if (length > 76) {
            if (str.charAt(76) == '\r') {
                i2 = i6 / 78;
            } else {
                i2 = 0;
            }
            i2 <<= 1;
        } else {
            i2 = 0;
        }
        int i7 = (((i6 - i2) * 6) >> 3) - i5;
        byte[] bArr = new byte[i7];
        int i8 = (i7 / 3) * 3;
        length = 0;
        int i9 = 0;
        while (i9 < i8) {
            int i10 = i3 + 1;
            int i11 = i10 + 1;
            i3 = (IA[str.charAt(i3)] << 18) | (IA[str.charAt(i10)] << 12);
            i10 = i11 + 1;
            int i12 = (IA[str.charAt(i11)] << 6) | i3;
            i3 = i10 + 1;
            i12 |= IA[str.charAt(i10)];
            i10 = i9 + 1;
            bArr[i9] = (byte) (i12 >> 16);
            i11 = i10 + 1;
            bArr[i10] = (byte) (i12 >> 8);
            i9 = i11 + 1;
            bArr[i11] = (byte) i12;
            if (i2 > 0) {
                length++;
                if (length == 19) {
                    i3 += 2;
                    length = 0;
                }
            }
        }
        if (i9 < i7) {
            i3 = 0;
            for (i2 = i3; i2 <= i4 - i5; i2++) {
                i++;
                i3 = (IA[str.charAt(i2)] << (18 - (i * 6))) | i3;
            }
            i2 = 16;
            i = i9;
            while (i < i7) {
                length = i + 1;
                bArr[i] = (byte) (i3 >> i2);
                i2 -= 8;
                i = length;
            }
        }
        return bArr;
    }
}
