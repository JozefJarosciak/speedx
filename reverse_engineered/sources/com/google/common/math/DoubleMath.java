package com.google.common.math;

import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.primitives.Booleans;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.Iterator;

@GwtCompatible(emulated = true)
public final class DoubleMath {
    private static final double LN_2 = Math.log(2.0d);
    @VisibleForTesting
    static final int MAX_FACTORIAL = 170;
    private static final double MAX_INT_AS_DOUBLE = 2.147483647E9d;
    private static final double MAX_LONG_AS_DOUBLE_PLUS_ONE = 9.223372036854776E18d;
    private static final double MIN_INT_AS_DOUBLE = -2.147483648E9d;
    private static final double MIN_LONG_AS_DOUBLE = -9.223372036854776E18d;
    @VisibleForTesting
    static final double[] everySixteenthFactorial = new double[]{1.0d, 2.0922789888E13d, 2.631308369336935E35d, 1.2413915592536073E61d, 1.2688693218588417E89d, 7.156945704626381E118d, 9.916779348709496E149d, 1.974506857221074E182d, 3.856204823625804E215d, 5.5502938327393044E249d, 4.7147236359920616E284d};

    /* renamed from: com.google.common.math.DoubleMath$1 */
    static /* synthetic */ class C38041 {
        static final /* synthetic */ int[] $SwitchMap$java$math$RoundingMode = new int[RoundingMode.values().length];

        static {
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UNNECESSARY.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.FLOOR.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.CEILING.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.DOWN.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.UP.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_EVEN.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_UP.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            try {
                $SwitchMap$java$math$RoundingMode[RoundingMode.HALF_DOWN.ordinal()] = 8;
            } catch (NoSuchFieldError e8) {
            }
        }
    }

    @GwtIncompatible("com.google.common.math.DoubleUtils")
    private static final class MeanAccumulator {
        private long count;
        private double mean;

        private MeanAccumulator() {
            this.count = 0;
            this.mean = 0.0d;
        }

        void add(double d) {
            Preconditions.checkArgument(DoubleUtils.isFinite(d));
            this.count++;
            this.mean += (d - this.mean) / ((double) this.count);
        }

        double mean() {
            Preconditions.checkArgument(this.count > 0, "Cannot take mean of 0 values");
            return this.mean;
        }
    }

    @GwtIncompatible("#isMathematicalInteger, com.google.common.math.DoubleUtils")
    static double roundIntermediate(double d, RoundingMode roundingMode) {
        if (DoubleUtils.isFinite(d)) {
            double rint;
            switch (C38041.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
                case 1:
                    MathPreconditions.checkRoundingUnnecessary(isMathematicalInteger(d));
                    return d;
                case 2:
                    if (d >= 0.0d || isMathematicalInteger(d)) {
                        return d;
                    }
                    return d - 1.0d;
                case 3:
                    if (d <= 0.0d || isMathematicalInteger(d)) {
                        return d;
                    }
                    return d + 1.0d;
                case 4:
                    return d;
                case 5:
                    if (isMathematicalInteger(d)) {
                        return d;
                    }
                    return d + Math.copySign(1.0d, d);
                case 6:
                    return Math.rint(d);
                case 7:
                    rint = Math.rint(d);
                    return Math.abs(d - rint) == 0.5d ? d + Math.copySign(0.5d, d) : rint;
                case 8:
                    rint = Math.rint(d);
                    if (Math.abs(d - rint) != 0.5d) {
                        return rint;
                    }
                    return d;
                default:
                    throw new AssertionError();
            }
        }
        throw new ArithmeticException("input is infinite or NaN");
    }

    @GwtIncompatible("#roundIntermediate")
    public static int roundToInt(double d, RoundingMode roundingMode) {
        int i;
        int i2 = 1;
        double roundIntermediate = roundIntermediate(d, roundingMode);
        if (roundIntermediate > -2.147483649E9d) {
            i = 1;
        } else {
            i = 0;
        }
        if (roundIntermediate >= 2.147483648E9d) {
            i2 = 0;
        }
        MathPreconditions.checkInRange(i2 & i);
        return (int) roundIntermediate;
    }

    @GwtIncompatible("#roundIntermediate")
    public static long roundToLong(double d, RoundingMode roundingMode) {
        int i;
        int i2 = 1;
        double roundIntermediate = roundIntermediate(d, roundingMode);
        if (MIN_LONG_AS_DOUBLE - roundIntermediate < 1.0d) {
            i = 1;
        } else {
            i = 0;
        }
        if (roundIntermediate >= MAX_LONG_AS_DOUBLE_PLUS_ONE) {
            i2 = 0;
        }
        MathPreconditions.checkInRange(i2 & i);
        return (long) roundIntermediate;
    }

    @GwtIncompatible("#roundIntermediate, java.lang.Math.getExponent, com.google.common.math.DoubleUtils")
    public static BigInteger roundToBigInteger(double d, RoundingMode roundingMode) {
        int i = 1;
        double roundIntermediate = roundIntermediate(d, roundingMode);
        int i2 = MIN_LONG_AS_DOUBLE - roundIntermediate < 1.0d ? 1 : 0;
        if (roundIntermediate >= MAX_LONG_AS_DOUBLE_PLUS_ONE) {
            i = 0;
        }
        if ((i & i2) != 0) {
            return BigInteger.valueOf((long) roundIntermediate);
        }
        BigInteger shiftLeft = BigInteger.valueOf(DoubleUtils.getSignificand(roundIntermediate)).shiftLeft(Math.getExponent(roundIntermediate) - 52);
        return roundIntermediate < 0.0d ? shiftLeft.negate() : shiftLeft;
    }

    @GwtIncompatible("com.google.common.math.DoubleUtils")
    public static boolean isPowerOfTwo(double d) {
        return d > 0.0d && DoubleUtils.isFinite(d) && LongMath.isPowerOfTwo(DoubleUtils.getSignificand(d));
    }

    public static double log2(double d) {
        return Math.log(d) / LN_2;
    }

    @GwtIncompatible("java.lang.Math.getExponent, com.google.common.math.DoubleUtils")
    public static int log2(double d, RoundingMode roundingMode) {
        int i = 1;
        boolean z = d > 0.0d && DoubleUtils.isFinite(d);
        Preconditions.checkArgument(z, "x must be positive and finite");
        int exponent = Math.getExponent(d);
        if (!DoubleUtils.isNormal(d)) {
            return log2(4.503599627370496E15d * d, roundingMode) - 52;
        }
        int i2;
        switch (C38041.$SwitchMap$java$math$RoundingMode[roundingMode.ordinal()]) {
            case 1:
                MathPreconditions.checkRoundingUnnecessary(isPowerOfTwo(d));
                break;
            case 2:
                break;
            case 3:
                if (isPowerOfTwo(d)) {
                    i = 0;
                    break;
                }
                break;
            case 4:
                if (exponent < 0) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                if (isPowerOfTwo(d)) {
                    i = 0;
                }
                i &= i2;
                break;
            case 5:
                if (exponent >= 0) {
                    i2 = 1;
                } else {
                    i2 = 0;
                }
                if (isPowerOfTwo(d)) {
                    i = 0;
                }
                i &= i2;
                break;
            case 6:
            case 7:
            case 8:
                double scaleNormalize = DoubleUtils.scaleNormalize(d);
                if (scaleNormalize * scaleNormalize <= 2.0d) {
                    i = 0;
                    break;
                }
                break;
            default:
                throw new AssertionError();
        }
        i = 0;
        if (i != 0) {
            return exponent + 1;
        }
        return exponent;
    }

    @GwtIncompatible("java.lang.Math.getExponent, com.google.common.math.DoubleUtils")
    public static boolean isMathematicalInteger(double d) {
        return DoubleUtils.isFinite(d) && (d == 0.0d || 52 - Long.numberOfTrailingZeros(DoubleUtils.getSignificand(d)) <= Math.getExponent(d));
    }

    public static double factorial(int i) {
        MathPreconditions.checkNonNegative("n", i);
        if (i > MAX_FACTORIAL) {
            return Double.POSITIVE_INFINITY;
        }
        double d = 1.0d;
        for (int i2 = (i & -16) + 1; i2 <= i; i2++) {
            d *= (double) i2;
        }
        return everySixteenthFactorial[i >> 4] * d;
    }

    public static boolean fuzzyEquals(double d, double d2, double d3) {
        MathPreconditions.checkNonNegative("tolerance", d3);
        return Math.copySign(d - d2, 1.0d) <= d3 || d == d2 || (Double.isNaN(d) && Double.isNaN(d2));
    }

    public static int fuzzyCompare(double d, double d2, double d3) {
        if (fuzzyEquals(d, d2, d3)) {
            return 0;
        }
        if (d < d2) {
            return -1;
        }
        if (d > d2) {
            return 1;
        }
        return Booleans.compare(Double.isNaN(d), Double.isNaN(d2));
    }

    @GwtIncompatible("MeanAccumulator")
    public static double mean(double... dArr) {
        MeanAccumulator meanAccumulator = new MeanAccumulator();
        for (double add : dArr) {
            meanAccumulator.add(add);
        }
        return meanAccumulator.mean();
    }

    @GwtIncompatible("MeanAccumulator")
    public static double mean(int... iArr) {
        MeanAccumulator meanAccumulator = new MeanAccumulator();
        for (int i : iArr) {
            meanAccumulator.add((double) i);
        }
        return meanAccumulator.mean();
    }

    @GwtIncompatible("MeanAccumulator")
    public static double mean(long... jArr) {
        MeanAccumulator meanAccumulator = new MeanAccumulator();
        for (long j : jArr) {
            meanAccumulator.add((double) j);
        }
        return meanAccumulator.mean();
    }

    @GwtIncompatible("MeanAccumulator")
    public static double mean(Iterable<? extends Number> iterable) {
        MeanAccumulator meanAccumulator = new MeanAccumulator();
        for (Number doubleValue : iterable) {
            meanAccumulator.add(doubleValue.doubleValue());
        }
        return meanAccumulator.mean();
    }

    @GwtIncompatible("MeanAccumulator")
    public static double mean(Iterator<? extends Number> it) {
        MeanAccumulator meanAccumulator = new MeanAccumulator();
        while (it.hasNext()) {
            meanAccumulator.add(((Number) it.next()).doubleValue());
        }
        return meanAccumulator.mean();
    }

    private DoubleMath() {
    }
}
