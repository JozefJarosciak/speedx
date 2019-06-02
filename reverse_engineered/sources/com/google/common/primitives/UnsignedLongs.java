package com.google.common.primitives;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.math.BigInteger;
import java.util.Comparator;

@GwtCompatible
@Beta
public final class UnsignedLongs {
    public static final long MAX_VALUE = -1;
    private static final int[] maxSafeDigits = new int[37];
    private static final long[] maxValueDivs = new long[37];
    private static final int[] maxValueMods = new int[37];

    enum LexicographicalComparator implements Comparator<long[]> {
        INSTANCE;

        public int compare(long[] jArr, long[] jArr2) {
            int min = Math.min(jArr.length, jArr2.length);
            for (int i = 0; i < min; i++) {
                if (jArr[i] != jArr2[i]) {
                    return UnsignedLongs.compare(jArr[i], jArr2[i]);
                }
            }
            return jArr.length - jArr2.length;
        }
    }

    private UnsignedLongs() {
    }

    private static long flip(long j) {
        return Long.MIN_VALUE ^ j;
    }

    public static int compare(long j, long j2) {
        return Longs.compare(flip(j), flip(j2));
    }

    public static long min(long... jArr) {
        boolean z;
        int i = 1;
        if (jArr.length > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        long flip = flip(jArr[0]);
        while (i < jArr.length) {
            long flip2 = flip(jArr[i]);
            if (flip2 < flip) {
                flip = flip2;
            }
            i++;
        }
        return flip(flip);
    }

    public static long max(long... jArr) {
        boolean z;
        int i = 1;
        if (jArr.length > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        long flip = flip(jArr[0]);
        while (i < jArr.length) {
            long flip2 = flip(jArr[i]);
            if (flip2 > flip) {
                flip = flip2;
            }
            i++;
        }
        return flip(flip);
    }

    public static String join(String str, long... jArr) {
        Preconditions.checkNotNull(str);
        if (jArr.length == 0) {
            return "";
        }
        StringBuilder stringBuilder = new StringBuilder(jArr.length * 5);
        stringBuilder.append(toString(jArr[0]));
        for (int i = 1; i < jArr.length; i++) {
            stringBuilder.append(str).append(toString(jArr[i]));
        }
        return stringBuilder.toString();
    }

    public static Comparator<long[]> lexicographicalComparator() {
        return LexicographicalComparator.INSTANCE;
    }

    public static long divide(long j, long j2) {
        int i = 1;
        if (j2 < 0) {
            if (compare(j, j2) < 0) {
                return 0;
            }
            return 1;
        } else if (j >= 0) {
            return j / j2;
        } else {
            long j3 = ((j >>> 1) / j2) << 1;
            if (compare(j - (j3 * j2), j2) < 0) {
                i = 0;
            }
            return ((long) i) + j3;
        }
    }

    public static long remainder(long j, long j2) {
        if (j2 < 0) {
            if (compare(j, j2) < 0) {
                return j;
            }
            return j - j2;
        } else if (j >= 0) {
            return j % j2;
        } else {
            long j3 = j - ((((j >>> 1) / j2) << 1) * j2);
            if (compare(j3, j2) < 0) {
                j2 = 0;
            }
            return j3 - j2;
        }
    }

    public static long parseUnsignedLong(String str) {
        return parseUnsignedLong(str, 10);
    }

    public static long decode(String str) {
        ParseRequest fromString = ParseRequest.fromString(str);
        try {
            return parseUnsignedLong(fromString.rawValue, fromString.radix);
        } catch (Throwable e) {
            Throwable th = e;
            String str2 = "Error parsing value: ";
            String valueOf = String.valueOf(str);
            NumberFormatException numberFormatException = new NumberFormatException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            numberFormatException.initCause(th);
            throw numberFormatException;
        }
    }

    public static long parseUnsignedLong(String str, int i) {
        Preconditions.checkNotNull(str);
        if (str.length() == 0) {
            throw new NumberFormatException("empty string");
        } else if (i < 2 || i > 36) {
            throw new NumberFormatException("illegal radix: " + i);
        } else {
            int i2 = maxSafeDigits[i] - 1;
            long j = 0;
            int i3 = 0;
            while (i3 < str.length()) {
                int digit = Character.digit(str.charAt(i3), i);
                if (digit == -1) {
                    throw new NumberFormatException(str);
                } else if (i3 <= i2 || !overflowInParse(j, digit, i)) {
                    j = (j * ((long) i)) + ((long) digit);
                    i3++;
                } else {
                    String str2 = "Too large for unsigned long: ";
                    String valueOf = String.valueOf(str);
                    throw new NumberFormatException(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
                }
            }
            return j;
        }
    }

    private static boolean overflowInParse(long j, int i, int i2) {
        if (j < 0) {
            return true;
        }
        if (j < maxValueDivs[i2]) {
            return false;
        }
        if (j > maxValueDivs[i2] || i > maxValueMods[i2]) {
            return true;
        }
        return false;
    }

    public static String toString(long j) {
        return toString(j, 10);
    }

    public static String toString(long j, int i) {
        boolean z = i >= 2 && i <= 36;
        Preconditions.checkArgument(z, "radix (%s) must be between Character.MIN_RADIX and Character.MAX_RADIX", Integer.valueOf(i));
        if (j == 0) {
            return "0";
        }
        int i2;
        char[] cArr = new char[64];
        int length = cArr.length;
        if (j < 0) {
            long divide = divide(j, (long) i);
            length--;
            cArr[length] = Character.forDigit((int) (j - (((long) i) * divide)), i);
            i2 = length;
            j = divide;
        } else {
            i2 = length;
        }
        while (j > 0) {
            length = i2 - 1;
            cArr[length] = Character.forDigit((int) (j % ((long) i)), i);
            j /= (long) i;
            i2 = length;
        }
        return new String(cArr, i2, cArr.length - i2);
    }

    static {
        BigInteger bigInteger = new BigInteger("10000000000000000", 16);
        for (int i = 2; i <= 36; i++) {
            maxValueDivs[i] = divide(-1, (long) i);
            maxValueMods[i] = (int) remainder(-1, (long) i);
            maxSafeDigits[i] = bigInteger.toString(i).length() - 1;
        }
    }
}
