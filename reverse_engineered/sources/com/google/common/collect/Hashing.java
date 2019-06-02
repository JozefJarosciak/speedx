package com.google.common.collect;

import com.google.common.annotations.GwtCompatible;
import com.google.common.primitives.Ints;

@GwtCompatible
final class Hashing {
    private static final int C1 = -862048943;
    private static final int C2 = 461845907;
    private static int MAX_TABLE_SIZE = Ints.MAX_POWER_OF_TWO;

    private Hashing() {
    }

    static int smear(int i) {
        return C2 * Integer.rotateLeft(C1 * i, 15);
    }

    static int smearedHash(Object obj) {
        return smear(obj == null ? 0 : obj.hashCode());
    }

    static int closedTableSize(int i, double d) {
        int max = Math.max(i, 2);
        int highestOneBit = Integer.highestOneBit(max);
        if (max <= ((int) (((double) highestOneBit) * d))) {
            return highestOneBit;
        }
        highestOneBit <<= 1;
        if (highestOneBit > 0) {
            return highestOneBit;
        }
        return MAX_TABLE_SIZE;
    }

    static boolean needsResizing(int i, int i2, double d) {
        return ((double) i) > ((double) i2) * d && i2 < MAX_TABLE_SIZE;
    }
}
