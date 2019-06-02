package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
@Beta
public final class Utf8 {
    public static int encodedLength(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && charSequence.charAt(i) < '') {
            i++;
        }
        int i2 = i;
        i = length;
        while (i2 < length) {
            char charAt = charSequence.charAt(i2);
            if (charAt >= 'ࠀ') {
                i += encodedLengthGeneral(charSequence, i2);
                break;
            }
            i2++;
            i = ((127 - charAt) >>> 31) + i;
        }
        if (i >= length) {
            return i;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (((long) i) + 4294967296L));
    }

    private static int encodedLengthGeneral(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = 0;
        int i3 = i;
        while (i3 < length) {
            char charAt = charSequence.charAt(i3);
            if (charAt < 'ࠀ') {
                i2 += (127 - charAt) >>> 31;
            } else {
                i2 += 2;
                if ('?' <= charAt && charAt <= '?') {
                    if (Character.codePointAt(charSequence, i3) < 65536) {
                        throw new IllegalArgumentException("Unpaired surrogate at index " + i3);
                    }
                    i3++;
                }
            }
            i3++;
        }
        return i2;
    }

    public static boolean isWellFormed(byte[] bArr) {
        return isWellFormed(bArr, 0, bArr.length);
    }

    public static boolean isWellFormed(byte[] bArr, int i, int i2) {
        int i3 = i + i2;
        Preconditions.checkPositionIndexes(i, i3, bArr.length);
        while (i < i3) {
            if (bArr[i] < (byte) 0) {
                return isWellFormedSlowPath(bArr, i, i3);
            }
            i++;
        }
        return true;
    }

    private static boolean isWellFormedSlowPath(byte[] bArr, int i, int i2) {
        while (i < i2) {
            int i3 = i + 1;
            byte b = bArr[i];
            if (b < (byte) 0) {
                int i4;
                if (b < (byte) -32) {
                    if (i3 == i2) {
                        return false;
                    }
                    if (b >= (byte) -62) {
                        i4 = i3 + 1;
                        if (bArr[i3] > (byte) -65) {
                        }
                    }
                    return false;
                } else if (b < (byte) -16) {
                    if (i3 + 1 >= i2) {
                        return false;
                    }
                    r3 = i3 + 1;
                    r2 = bArr[i3];
                    if (r2 <= (byte) -65 && ((b != (byte) -32 || r2 >= (byte) -96) && (b != (byte) -19 || (byte) -96 > r2))) {
                        i4 = r3 + 1;
                        if (bArr[r3] > (byte) -65) {
                        }
                    }
                    return false;
                } else if (i3 + 2 >= i2) {
                    return false;
                } else {
                    r3 = i3 + 1;
                    r2 = bArr[i3];
                    if (r2 <= (byte) -65 && (((b << 28) + (r2 + 112)) >> 30) == 0) {
                        i3 = r3 + 1;
                        if (bArr[r3] <= (byte) -65) {
                            i4 = i3 + 1;
                            if (bArr[i3] > (byte) -65) {
                            }
                        }
                    }
                    return false;
                }
                i = i4;
            } else {
                i = i3;
            }
        }
        return true;
    }

    private Utf8() {
    }
}
