package com.google.common.math;

import com.google.android.gms.location.places.Place;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@GwtCompatible(emulated = true)
public final class BigIntegerMath {
    private static final double LN_10 = Math.log(10.0d);
    private static final double LN_2 = Math.log(2.0d);
    @VisibleForTesting
    static final BigInteger SQRT2_PRECOMPUTED_BITS = new BigInteger("16a09e667f3bcc908b2fb1366ea957d3e3adec17512775099da2f590b0667322a", 16);
    @VisibleForTesting
    static final int SQRT2_PRECOMPUTE_THRESHOLD = 256;

    /* renamed from: com.google.common.math.BigIntegerMath$1 */
    static /* synthetic */ class C38031 {
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

    public static boolean isPowerOfTwo(BigInteger bigInteger) {
        Preconditions.checkNotNull(bigInteger);
        return bigInteger.signum() > 0 && bigInteger.getLowestSetBit() == bigInteger.bitLength() - 1;
    }

    public static int log2(BigInteger bigInteger, RoundingMode roundingMode) {
        MathPreconditions.checkPositive("x", (BigInteger) Preconditions.checkNotNull(bigInteger));
        int bitLength = bigInteger.bitLength() - 1;
        switch (C38031.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(bigInteger));
                return bitLength;
            case 2:
            case 3:
                return bitLength;
            case 4:
            case 5:
                if (isPowerOfTwo(bigInteger)) {
                    return bitLength;
                }
                return bitLength + 1;
            case 6:
            case 7:
            case 8:
                if (bitLength < 256) {
                    if (bigInteger.compareTo(SQRT2_PRECOMPUTED_BITS.shiftRight(256 - bitLength)) > 0) {
                        return bitLength + 1;
                    }
                    return bitLength;
                } else if (bigInteger.pow(2).bitLength() - 1 >= (bitLength * 2) + 1) {
                    return bitLength + 1;
                } else {
                    return bitLength;
                }
            default:
                throw new AssertionError();
        }
    }

    @GwtIncompatible("TODO")
    public static int log10(BigInteger bigInteger, RoundingMode roundingMode) {
        MathPreconditions.checkPositive("x", bigInteger);
        if (fitsInLong(bigInteger)) {
            return LongMath.log10(bigInteger.longValue(), roundingMode);
        }
        int compareTo;
        BigInteger bigInteger2;
        int i;
        int log2 = (int) ((((double) log2(bigInteger, RoundingMode.FLOOR)) * LN_2) / LN_10);
        BigInteger pow = BigInteger.TEN.pow(log2);
        int compareTo2 = pow.compareTo(bigInteger);
        if (compareTo2 > 0) {
            do {
                log2--;
                pow = pow.divide(BigInteger.TEN);
                compareTo = pow.compareTo(bigInteger);
            } while (compareTo > 0);
            int i2 = compareTo;
            compareTo = log2;
            bigInteger2 = pow;
            i = i2;
        } else {
            BigInteger multiply = BigInteger.TEN.multiply(pow);
            compareTo = log2;
            bigInteger2 = pow;
            i = compareTo2;
            BigInteger bigInteger3 = multiply;
            int compareTo3 = multiply.compareTo(bigInteger);
            while (compareTo3 <= 0) {
                compareTo++;
                bigInteger2 = BigInteger.TEN.multiply(bigInteger3);
                i = compareTo3;
                compareTo3 = bigInteger2.compareTo(bigInteger);
                BigInteger bigInteger4 = bigInteger2;
                bigInteger2 = bigInteger3;
                bigInteger3 = bigInteger4;
            }
        }
        switch (C38031.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(i == 0);
                return compareTo;
            case 2:
            case 3:
                return compareTo;
            case 4:
            case 5:
                if (bigInteger2.equals(bigInteger)) {
                    return compareTo;
                }
                return compareTo + 1;
            case 6:
            case 7:
            case 8:
                if (bigInteger.pow(2).compareTo(bigInteger2.pow(2).multiply(BigInteger.TEN)) > 0) {
                    return compareTo + 1;
                }
                return compareTo;
            default:
                throw new AssertionError();
        }
    }

    @GwtIncompatible("TODO")
    public static BigInteger sqrt(BigInteger bigInteger, RoundingMode roundingMode) {
        MathPreconditions.checkNonNegative("x", bigInteger);
        if (fitsInLong(bigInteger)) {
            return BigInteger.valueOf(LongMath.sqrt(bigInteger.longValue(), roundingMode));
        }
        BigInteger sqrtFloor = sqrtFloor(bigInteger);
        switch (C38031.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(sqrtFloor.pow(2).equals(bigInteger));
                return sqrtFloor;
            case 2:
            case 3:
                return sqrtFloor;
            case 4:
            case 5:
                int intValue = sqrtFloor.intValue();
                Object obj = (intValue * intValue == bigInteger.intValue() && sqrtFloor.pow(2).equals(bigInteger)) ? 1 : null;
                if (obj == null) {
                    return sqrtFloor.add(BigInteger.ONE);
                }
                return sqrtFloor;
            case 6:
            case 7:
            case 8:
                if (sqrtFloor.pow(2).add(sqrtFloor).compareTo(bigInteger) < 0) {
                    return sqrtFloor.add(BigInteger.ONE);
                }
                return sqrtFloor;
            default:
                throw new AssertionError();
        }
    }

    @GwtIncompatible("TODO")
    private static BigInteger sqrtFloor(BigInteger bigInteger) {
        BigInteger sqrtApproxWithDoubles;
        int log2 = log2(bigInteger, RoundingMode.FLOOR);
        if (log2 < Place.TYPE_SUBLOCALITY_LEVEL_1) {
            sqrtApproxWithDoubles = sqrtApproxWithDoubles(bigInteger);
        } else {
            log2 = (log2 - 52) & -2;
            sqrtApproxWithDoubles = sqrtApproxWithDoubles(bigInteger.shiftRight(log2)).shiftLeft(log2 >> 1);
        }
        BigInteger shiftRight = sqrtApproxWithDoubles.add(bigInteger.divide(sqrtApproxWithDoubles)).shiftRight(1);
        if (!sqrtApproxWithDoubles.equals(shiftRight)) {
            do {
                sqrtApproxWithDoubles = shiftRight;
                shiftRight = sqrtApproxWithDoubles.add(bigInteger.divide(sqrtApproxWithDoubles)).shiftRight(1);
            } while (shiftRight.compareTo(sqrtApproxWithDoubles) < 0);
        }
        return sqrtApproxWithDoubles;
    }

    @GwtIncompatible("TODO")
    private static BigInteger sqrtApproxWithDoubles(BigInteger bigInteger) {
        return DoubleMath.roundToBigInteger(Math.sqrt(DoubleUtils.bigToDouble(bigInteger)), RoundingMode.HALF_EVEN);
    }

    @GwtIncompatible("TODO")
    public static BigInteger divide(BigInteger bigInteger, BigInteger bigInteger2, RoundingMode roundingMode) {
        return new BigDecimal(bigInteger).divide(new BigDecimal(bigInteger2), 0, roundingMode).toBigIntegerExact();
    }

    public static BigInteger factorial(int i) {
        MathPreconditions.checkNonNegative("n", i);
        if (i < LongMath.factorials.length) {
            return BigInteger.valueOf(LongMath.factorials[i]);
        }
        List arrayList = new ArrayList(IntMath.divide(IntMath.log2(i, RoundingMode.CEILING) * i, 64, RoundingMode.CEILING));
        int length = LongMath.factorials.length;
        long j = LongMath.factorials[length - 1];
        int numberOfTrailingZeros = Long.numberOfTrailingZeros(j);
        long j2 = j >> numberOfTrailingZeros;
        int log2 = LongMath.log2((long) length, RoundingMode.FLOOR) + 1;
        long j3 = (long) length;
        length = 1 << (log2 - 1);
        int i2 = log2;
        j = j2;
        int log22 = LongMath.log2(j2, RoundingMode.FLOOR) + 1;
        int i3 = numberOfTrailingZeros;
        long j4 = j3;
        while (j4 <= ((long) i)) {
            if ((((long) length) & j4) != 0) {
                length <<= 1;
                i2++;
            }
            int numberOfTrailingZeros2 = Long.numberOfTrailingZeros(j4);
            long j5 = j4 >> numberOfTrailingZeros2;
            i3 += numberOfTrailingZeros2;
            if (log22 + (i2 - numberOfTrailingZeros2) >= 64) {
                arrayList.add(BigInteger.valueOf(j));
                j = 1;
            }
            long j6 = j * j5;
            log22 = LongMath.log2(j6, RoundingMode.FLOOR) + 1;
            j4 = 1 + j4;
            j = j6;
        }
        if (j > 1) {
            arrayList.add(BigInteger.valueOf(j));
        }
        return listProduct(arrayList).shiftLeft(i3);
    }

    static BigInteger listProduct(List<BigInteger> list) {
        return listProduct(list, 0, list.size());
    }

    static BigInteger listProduct(List<BigInteger> list, int i, int i2) {
        switch (i2 - i) {
            case 0:
                return BigInteger.ONE;
            case 1:
                return (BigInteger) list.get(i);
            case 2:
                return ((BigInteger) list.get(i)).multiply((BigInteger) list.get(i + 1));
            case 3:
                return ((BigInteger) list.get(i)).multiply((BigInteger) list.get(i + 1)).multiply((BigInteger) list.get(i + 2));
            default:
                int i3 = (i2 + i) >>> 1;
                return listProduct(list, i, i3).multiply(listProduct(list, i3, i2));
        }
    }

    public static BigInteger binomial(int i, int i2) {
        MathPreconditions.checkNonNegative("n", i);
        MathPreconditions.checkNonNegative("k", i2);
        Preconditions.checkArgument(i2 <= i, "k (%s) > n (%s)", Integer.valueOf(i2), Integer.valueOf(i));
        if (i2 > (i >> 1)) {
            i2 = i - i2;
        }
        if (i2 < LongMath.biggestBinomials.length && i <= LongMath.biggestBinomials[i2]) {
            return BigInteger.valueOf(LongMath.binomial(i, i2));
        }
        BigInteger bigInteger = BigInteger.ONE;
        long j = (long) i;
        long j2 = 1;
        int log2 = LongMath.log2((long) i, RoundingMode.CEILING);
        BigInteger bigInteger2 = bigInteger;
        int i3 = log2;
        for (int i4 = 1; i4 < i2; i4++) {
            int i5 = i - i4;
            int i6 = i4 + 1;
            if (i3 + log2 >= 63) {
                BigInteger divide = bigInteger2.multiply(BigInteger.valueOf(j)).divide(BigInteger.valueOf(j2));
                j = (long) i5;
                j2 = (long) i6;
                i3 = log2;
                bigInteger2 = divide;
            } else {
                j *= (long) i5;
                j2 *= (long) i6;
                i3 += log2;
            }
        }
        return bigInteger2.multiply(BigInteger.valueOf(j)).divide(BigInteger.valueOf(j2));
    }

    @GwtIncompatible("TODO")
    static boolean fitsInLong(BigInteger bigInteger) {
        return bigInteger.bitLength() <= 63;
    }

    private BigIntegerMath() {
    }
}
