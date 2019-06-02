package com.google.zxing.oned.rss;

public final class RSSUtils {
    private RSSUtils() {
    }

    public static int getRSSvalue(int[] iArr, int i, boolean z) {
        int length = iArr.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < iArr.length) {
            int i4 = iArr[i2] + i3;
            i2++;
            i3 = i4;
        }
        int i5 = 0;
        i2 = 0;
        int i6 = i3;
        i3 = 0;
        while (i5 < length - 1) {
            i4 = i2;
            i2 = i3 | (1 << i5);
            i3 = 1;
            while (i3 < iArr[i5]) {
                int combins = combins((i6 - i3) - 1, (length - i5) - 2);
                if (z && i2 == 0 && (i6 - i3) - ((length - i5) - 1) >= (length - i5) - 1) {
                    combins -= combins((i6 - i3) - (length - i5), (length - i5) - 2);
                }
                if ((length - i5) - 1 > 1) {
                    int i7 = 0;
                    for (int i8 = (i6 - i3) - ((length - i5) - 2); i8 > i; i8--) {
                        i7 += combins(((i6 - i3) - i8) - 1, (length - i5) - 3);
                    }
                    combins -= ((length - 1) - i5) * i7;
                } else if (i6 - i3 > i) {
                    combins--;
                }
                i4 += combins;
                i3++;
                i2 &= (1 << i5) ^ -1;
            }
            i5++;
            i6 -= i3;
            i3 = i2;
            i2 = i4;
        }
        return i2;
    }

    private static int combins(int i, int i2) {
        int i3;
        int i4 = 1;
        if (i - i2 > i2) {
            i3 = i - i2;
        } else {
            i3 = i2;
            i2 = i - i2;
        }
        int i5 = 1;
        while (i > i3) {
            i5 *= i;
            if (i4 <= i2) {
                i5 /= i4;
                i4++;
            }
            i--;
        }
        i3 = i5;
        while (i4 <= i2) {
            i3 /= i4;
            i4++;
        }
        return i3;
    }
}
