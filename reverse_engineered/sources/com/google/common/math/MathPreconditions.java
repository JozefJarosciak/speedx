package com.google.common.math;

import com.google.common.annotations.GwtCompatible;
import java.math.BigInteger;

@GwtCompatible
final class MathPreconditions {
    static int checkPositive(String str, int i) {
        if (i > 0) {
            return i;
        }
        String valueOf = String.valueOf(String.valueOf(str));
        throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 26).append(valueOf).append(" (").append(i).append(") must be > 0").toString());
    }

    static long checkPositive(String str, long j) {
        if (j > 0) {
            return j;
        }
        String valueOf = String.valueOf(String.valueOf(str));
        throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 35).append(valueOf).append(" (").append(j).append(") must be > 0").toString());
    }

    static BigInteger checkPositive(String str, BigInteger bigInteger) {
        if (bigInteger.signum() > 0) {
            return bigInteger;
        }
        String valueOf = String.valueOf(String.valueOf(str));
        String valueOf2 = String.valueOf(String.valueOf(bigInteger));
        throw new IllegalArgumentException(new StringBuilder((valueOf.length() + 15) + valueOf2.length()).append(valueOf).append(" (").append(valueOf2).append(") must be > 0").toString());
    }

    static int checkNonNegative(String str, int i) {
        if (i >= 0) {
            return i;
        }
        String valueOf = String.valueOf(String.valueOf(str));
        throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 27).append(valueOf).append(" (").append(i).append(") must be >= 0").toString());
    }

    static long checkNonNegative(String str, long j) {
        if (j >= 0) {
            return j;
        }
        String valueOf = String.valueOf(String.valueOf(str));
        throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 36).append(valueOf).append(" (").append(j).append(") must be >= 0").toString());
    }

    static BigInteger checkNonNegative(String str, BigInteger bigInteger) {
        if (bigInteger.signum() >= 0) {
            return bigInteger;
        }
        String valueOf = String.valueOf(String.valueOf(str));
        String valueOf2 = String.valueOf(String.valueOf(bigInteger));
        throw new IllegalArgumentException(new StringBuilder((valueOf.length() + 16) + valueOf2.length()).append(valueOf).append(" (").append(valueOf2).append(") must be >= 0").toString());
    }

    static double checkNonNegative(String str, double d) {
        if (d >= 0.0d) {
            return d;
        }
        String valueOf = String.valueOf(String.valueOf(str));
        throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 40).append(valueOf).append(" (").append(d).append(") must be >= 0").toString());
    }

    static void checkRoundingUnnecessary(boolean z) {
        if (!z) {
            throw new ArithmeticException("mode was UNNECESSARY, but rounding was necessary");
        }
    }

    static void checkInRange(boolean z) {
        if (!z) {
            throw new ArithmeticException("not in range");
        }
    }

    static void checkNoOverflow(boolean z) {
        if (!z) {
            throw new ArithmeticException("overflow");
        }
    }

    private MathPreconditions() {
    }
}
