package com.google.common.math;

import ch.qos.logback.core.spi.AbstractComponentTracker;
import com.alibaba.fastjson.asm.Opcodes;
import com.avos.avoscloud.AVException;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;
import java.math.RoundingMode;
import org.apache.http.HttpStatus;

@GwtCompatible(emulated = true)
public final class LongMath {
    @VisibleForTesting
    static final long FLOOR_SQRT_MAX_LONG = 3037000499L;
    @VisibleForTesting
    static final long MAX_POWER_OF_SQRT2_UNSIGNED = -5402926248376769404L;
    static final int[] biggestBinomials = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 3810779, 121977, 16175, 4337, 1733, 887, 534, 361, 265, 206, Opcodes.RET, 143, AVException.INVALID_EMAIL_ADDRESS, 111, 101, 94, 88, 83, 79, 76, 74, 72, 70, 69, 68, 67, 67, 66, 66, 66, 66};
    @VisibleForTesting
    static final int[] biggestSimpleBinomials = new int[]{Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE, 2642246, 86251, 11724, 3218, 1313, 684, HttpStatus.SC_INSUFFICIENT_SPACE_ON_RESOURCE, 287, AVException.USER_MOBILE_PHONENUMBER_TAKEN, Opcodes.RET, AVException.INVALID_ROLE_NAME, AVException.OPERATION_FORBIDDEN, 105, 95, 87, 81, 76, 73, 70, 68, 66, 64, 63, 62, 62, 61, 61, 61};
    static final long[] factorials = new long[]{1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880, 3628800, 39916800, 479001600, 6227020800L, 87178291200L, 1307674368000L, 20922789888000L, 355687428096000L, 6402373705728000L, 121645100408832000L, 2432902008176640000L};
    @GwtIncompatible("TODO")
    @VisibleForTesting
    static final long[] halfPowersOf10 = new long[]{3, 31, 316, 3162, 31622, 316227, 3162277, 31622776, 316227766, 3162277660L, 31622776601L, 316227766016L, 3162277660168L, 31622776601683L, 316227766016837L, 3162277660168379L, 31622776601683793L, 316227766016837933L, 3162277660168379331L};
    @VisibleForTesting
    static final byte[] maxLog10ForLeadingZeros = new byte[]{(byte) 19, Ascii.DC2, Ascii.DC2, Ascii.DC2, Ascii.DC2, (byte) 17, (byte) 17, (byte) 17, Ascii.DLE, Ascii.DLE, Ascii.DLE, Ascii.SI, Ascii.SI, Ascii.SI, Ascii.SI, Ascii.SO, Ascii.SO, Ascii.SO, (byte) 13, (byte) 13, (byte) 13, Ascii.FF, Ascii.FF, Ascii.FF, Ascii.FF, Ascii.VT, Ascii.VT, Ascii.VT, (byte) 10, (byte) 10, (byte) 10, (byte) 9, (byte) 9, (byte) 9, (byte) 9, (byte) 8, (byte) 8, (byte) 8, (byte) 7, (byte) 7, (byte) 7, (byte) 6, (byte) 6, (byte) 6, (byte) 6, (byte) 5, (byte) 5, (byte) 5, (byte) 4, (byte) 4, (byte) 4, (byte) 3, (byte) 3, (byte) 3, (byte) 3, (byte) 2, (byte) 2, (byte) 2, (byte) 1, (byte) 1, (byte) 1, (byte) 0, (byte) 0, (byte) 0};
    @GwtIncompatible("TODO")
    @VisibleForTesting
    static final long[] powersOf10 = new long[]{1, 10, 100, 1000, AbstractComponentTracker.LINGERING_TIMEOUT, 100000, 1000000, 10000000, 100000000, 1000000000, 10000000000L, 100000000000L, 1000000000000L, 10000000000000L, 100000000000000L, 1000000000000000L, 10000000000000000L, 100000000000000000L, 1000000000000000000L};

    /* renamed from: com.google.common.math.LongMath$1 */
    static /* synthetic */ class C38061 {
        static final /* synthetic */ int[] $SwitchMap$java$math$RoundingMode = new int[RoundingMode.values().length];

        static {
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UNNECESSARY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    public static boolean isPowerOfTwo(long j) {
        int i = 1;
        int i2 = j > 0 ? 1 : 0;
        if (((j - 1) & j) != 0) {
            i = 0;
        }
        return i & i2;
    }

    @VisibleForTesting
    static int lessThanBranchFree(long j, long j2) {
        return (int) ((((j - j2) ^ -1) ^ -1) >>> 63);
    }

    public static int log2(long j, RoundingMode roundingMode) {
        MathPreconditions.checkPositive("x", j);
        switch (C38061.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(j));
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return 64 - Long.numberOfLeadingZeros(j - 1);
            case 6:
            case 7:
            case 8:
                int numberOfLeadingZeros = Long.numberOfLeadingZeros(j);
                return (63 - numberOfLeadingZeros) + lessThanBranchFree(MAX_POWER_OF_SQRT2_UNSIGNED >>> numberOfLeadingZeros, j);
            default:
                throw new AssertionError("impossible");
        }
        return 63 - Long.numberOfLeadingZeros(j);
    }

    @GwtIncompatible("TODO")
    public static int log10(long j, RoundingMode roundingMode) {
        MathPreconditions.checkPositive("x", j);
        int log10Floor = log10Floor(j);
        long j2 = powersOf10[log10Floor];
        switch (C38061.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                boolean z;
                if (j == j2) {
                    z = true;
                } else {
                    z = false;
                }
                MathPreconditions.checkRoundingUnnecessary(z);
                break;
            case 2:
            case 3:
                break;
            case 4:
            case 5:
                return lessThanBranchFree(j2, j) + log10Floor;
            case 6:
            case 7:
            case 8:
                return lessThanBranchFree(halfPowersOf10[log10Floor], j) + log10Floor;
            default:
                throw new AssertionError();
        }
        return log10Floor;
    }

    @GwtIncompatible("TODO")
    static int log10Floor(long j) {
        byte b = maxLog10ForLeadingZeros[Long.numberOfLeadingZeros(j)];
        return b - lessThanBranchFree(j, powersOf10[b]);
    }

    @GwtIncompatible("TODO")
    public static long pow(long j, int i) {
        long j2 = 0;
        MathPreconditions.checkNonNegative("exponent", i);
        if (-2 > j || j > 2) {
            long j3 = 1;
            long j4 = j;
            while (true) {
                switch (i) {
                    case 0:
                        return j3;
                    case 1:
                        return j3 * j4;
                    default:
                        if ((i & 1) == 0) {
                            j2 = 1;
                        } else {
                            j2 = j4;
                        }
                        j3 *= j2;
                        j4 *= j4;
                        i >>= 1;
                }
            }
        } else {
            switch ((int) j) {
                case -2:
                    if (i < 64) {
                        return (i & 1) == 0 ? 1 << i : -(1 << i);
                    } else {
                        return 0;
                    }
                case -1:
                    if ((i & 1) != 0) {
                        return -1;
                    }
                    return 1;
                case 0:
                    if (i == 0) {
                        return 1;
                    }
                    return 0;
                case 1:
                    return 1;
                case 2:
                    if (i < 64) {
                        j2 = 1 << i;
                    }
                    return j2;
                default:
                    throw new AssertionError();
            }
        }
    }

    @GwtIncompatible("TODO")
    public static long sqrt(long j, RoundingMode roundingMode) {
        int i = 1;
        MathPreconditions.checkNonNegative("x", j);
        if (fitsInInt(j)) {
            return (long) IntMath.sqrt((int) j, roundingMode);
        }
        long sqrt = (long) Math.sqrt((double) j);
        long j2 = sqrt * sqrt;
        switch (C38061.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                boolean z;
                if (j2 != j) {
                    z = false;
                }
                MathPreconditions.checkRoundingUnnecessary(z);
                return sqrt;
            case 2:
            case 3:
                return j < j2 ? sqrt - 1 : sqrt;
            case 4:
            case 5:
                return j > j2 ? sqrt + 1 : sqrt;
            case 6:
            case 7:
            case 8:
                if (j >= j2) {
                    i = 0;
                }
                long j3 = sqrt - ((long) i);
                return j3 + ((long) lessThanBranchFree((j3 * j3) + j3, j));
            default:
                throw new AssertionError();
        }
    }

    @GwtIncompatible("TODO")
    public static long divide(long j, long j2, RoundingMode roundingMode) {
        Preconditions.checkNotNull(roundingMode);
        long j3 = j / j2;
        long j4 = j - (j2 * j3);
        if (j4 == 0) {
            return j3;
        }
        int i;
        int i2 = ((int) ((j ^ j2) >> 63)) | 1;
        switch (C38061.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(j4 == 0);
                break;
            case 2:
                break;
            case 3:
                if (i2 >= 0) {
                    i = 0;
                    break;
                }
                i = 1;
                break;
            case 4:
                i = 1;
                break;
            case 5:
                if (i2 <= 0) {
                    i = 0;
                    break;
                }
                i = 1;
                break;
            case 6:
            case 7:
            case 8:
                j4 = Math.abs(j4);
                j4 -= Math.abs(j2) - j4;
                if (j4 != 0) {
                    if (j4 <= 0) {
                        i = 0;
                        break;
                    }
                    i = 1;
                    break;
                }
                i = (roundingMode == RoundingMode.HALF_UP ? 1 : 0) | (((1 & j3) != 0 ? 1 : 0) & (roundingMode == RoundingMode.HALF_EVEN ? 1 : 0));
                break;
            default:
                throw new AssertionError();
        }
        i = 0;
        if (i != 0) {
            j4 = ((long) i2) + j3;
        } else {
            j4 = j3;
        }
        return j4;
    }

    @GwtIncompatible("TODO")
    public static int mod(long j, int i) {
        return (int) mod(j, (long) i);
    }

    @GwtIncompatible("TODO")
    public static long mod(long j, long j2) {
        if (j2 <= 0) {
            throw new ArithmeticException("Modulus must be positive");
        }
        long j3 = j % j2;
        return j3 >= 0 ? j3 : j3 + j2;
    }

    public static long gcd(long j, long j2) {
        MathPreconditions.checkNonNegative("a", j);
        MathPreconditions.checkNonNegative("b", j2);
        if (j == 0) {
            return j2;
        }
        if (j2 == 0) {
            return j;
        }
        int numberOfTrailingZeros = Long.numberOfTrailingZeros(j);
        long j3 = j >> numberOfTrailingZeros;
        int numberOfTrailingZeros2 = Long.numberOfTrailingZeros(j2);
        long j4 = j2 >> numberOfTrailingZeros2;
        while (j3 != j4) {
            j3 -= j4;
            long j5 = (j3 >> 63) & j3;
            j3 = (j3 - j5) - j5;
            j4 += j5;
            j3 >>= Long.numberOfTrailingZeros(j3);
        }
        return j3 << Math.min(numberOfTrailingZeros, numberOfTrailingZeros2);
    }

    @GwtIncompatible("TODO")
    public static long checkedAdd(long j, long j2) {
        int i;
        int i2 = 1;
        long j3 = j + j2;
        if ((j ^ j2) < 0) {
            i = 1;
        } else {
            i = 0;
        }
        if ((j ^ j3) < 0) {
            i2 = 0;
        }
        MathPreconditions.checkNoOverflow(i2 | i);
        return j3;
    }

    @GwtIncompatible("TODO")
    public static long checkedSubtract(long j, long j2) {
        int i;
        int i2 = 1;
        long j3 = j - j2;
        if ((j ^ j2) >= 0) {
            i = 1;
        } else {
            i = 0;
        }
        if ((j ^ j3) < 0) {
            i2 = 0;
        }
        MathPreconditions.checkNoOverflow(i2 | i);
        return j3;
    }

    @GwtIncompatible("TODO")
    public static long checkedMultiply(long j, long j2) {
        boolean z = false;
        int numberOfLeadingZeros = ((Long.numberOfLeadingZeros(j) + Long.numberOfLeadingZeros(j ^ -1)) + Long.numberOfLeadingZeros(j2)) + Long.numberOfLeadingZeros(j2 ^ -1);
        if (numberOfLeadingZeros > 65) {
            return j * j2;
        }
        boolean z2;
        int i;
        if (numberOfLeadingZeros >= 64) {
            z2 = true;
        } else {
            z2 = false;
        }
        MathPreconditions.checkNoOverflow(z2);
        if (j >= 0) {
            i = 1;
        } else {
            i = 0;
        }
        MathPreconditions.checkNoOverflow((j2 != Long.MIN_VALUE ? 1 : 0) | i);
        long j3 = j * j2;
        if (j == 0 || j3 / j == j2) {
            z = true;
        }
        MathPreconditions.checkNoOverflow(z);
        return j3;
    }

    @GwtIncompatible("TODO")
    public static long checkedPow(long j, int i) {
        boolean z = true;
        long j2 = 1;
        MathPreconditions.checkNonNegative("exponent", i);
        if (((j <= 2 ? 1 : 0) & (j >= -2 ? 1 : 0)) != 0) {
            switch ((int) j) {
                case -2:
                    if (i >= 64) {
                        z = false;
                    }
                    MathPreconditions.checkNoOverflow(z);
                    return (i & 1) == 0 ? 1 << i : -1 << i;
                case -1:
                    if ((i & 1) != 0) {
                        return -1;
                    }
                    return 1;
                case 0:
                    if (i == 0) {
                        return 1;
                    }
                    return 0;
                case 1:
                    return 1;
                case 2:
                    boolean z2;
                    if (i < 63) {
                        z2 = true;
                    } else {
                        z2 = false;
                    }
                    MathPreconditions.checkNoOverflow(z2);
                    return 1 << i;
                default:
                    throw new AssertionError();
            }
        }
        while (true) {
            switch (i) {
                case 0:
                    return j2;
                case 1:
                    return checkedMultiply(j2, j);
                default:
                    long checkedMultiply;
                    if ((i & 1) != 0) {
                        checkedMultiply = checkedMultiply(j2, j);
                    } else {
                        checkedMultiply = j2;
                    }
                    i >>= 1;
                    if (i > 0) {
                        boolean z3;
                        if (j <= FLOOR_SQRT_MAX_LONG) {
                            z3 = true;
                        } else {
                            z3 = false;
                        }
                        MathPreconditions.checkNoOverflow(z3);
                        j *= j;
                        j2 = checkedMultiply;
                    } else {
                        j2 = checkedMultiply;
                    }
            }
        }
    }

    @GwtIncompatible("TODO")
    public static long factorial(int i) {
        MathPreconditions.checkNonNegative("n", i);
        return i < factorials.length ? factorials[i] : Long.MAX_VALUE;
    }

    public static long binomial(int i, int i2) {
        long j = 1;
        int i3 = 2;
        MathPreconditions.checkNonNegative("n", i);
        MathPreconditions.checkNonNegative("k", i2);
        Preconditions.checkArgument(i2 <= i, "k (%s) > n (%s)", Integer.valueOf(i2), Integer.valueOf(i));
        if (i2 > (i >> 1)) {
            i2 = i - i2;
        }
        switch (i2) {
            case 0:
                return 1;
            case 1:
                return (long) i;
            default:
                if (i < factorials.length) {
                    return factorials[i] / (factorials[i2] * factorials[i - i2]);
                }
                if (i2 >= biggestBinomials.length || i > biggestBinomials[i2]) {
                    return Long.MAX_VALUE;
                }
                long j2;
                if (i2 >= biggestSimpleBinomials.length || i > biggestSimpleBinomials[i2]) {
                    int log2 = log2((long) i, RoundingMode.CEILING);
                    int i4 = 2;
                    int i5 = log2;
                    int i6 = i - 1;
                    long j3 = (long) i;
                    j2 = 1;
                    while (i4 <= i2) {
                        long j4;
                        int i7;
                        if (i5 + log2 < 63) {
                            j4 = ((long) i6) * j3;
                            j3 = j * ((long) i4);
                            j = j4;
                            j4 = j2;
                            i7 = i5 + log2;
                        } else {
                            j4 = multiplyFraction(j2, j3, j);
                            j = (long) i6;
                            j3 = (long) i4;
                            i7 = log2;
                        }
                        i5 = i7;
                        i6--;
                        i4++;
                        j2 = j4;
                        long j5 = j;
                        j = j3;
                        j3 = j5;
                    }
                    return multiplyFraction(j2, j3, j);
                }
                int i8 = i - 1;
                j2 = (long) i;
                while (i3 <= i2) {
                    j2 = (j2 * ((long) i8)) / ((long) i3);
                    i8--;
                    i3++;
                }
                return j2;
        }
    }

    static long multiplyFraction(long j, long j2, long j3) {
        if (j == 1) {
            return j2 / j3;
        }
        long gcd = gcd(j, j3);
        return (j2 / (j3 / gcd)) * (j / gcd);
    }

    static boolean fitsInInt(long j) {
        return ((long) ((int) j)) == j;
    }

    public static long mean(long j, long j2) {
        return (j & j2) + ((j ^ j2) >> 1);
    }

    private LongMath() {
    }
}
