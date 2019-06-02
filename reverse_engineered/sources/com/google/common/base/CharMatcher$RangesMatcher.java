package com.google.common.base;

import java.util.Arrays;

class CharMatcher$RangesMatcher extends CharMatcher {
    private final char[] rangeEnds;
    private final char[] rangeStarts;

    public /* bridge */ /* synthetic */ boolean apply(Object obj) {
        return super.apply((Character) obj);
    }

    CharMatcher$RangesMatcher(String str, char[] cArr, char[] cArr2) {
        boolean z;
        super(str);
        this.rangeStarts = cArr;
        this.rangeEnds = cArr2;
        if (cArr.length == cArr2.length) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        for (int i = 0; i < cArr.length; i++) {
            boolean z2;
            if (cArr[i] <= cArr2[i]) {
                z2 = true;
            } else {
                z2 = false;
            }
            Preconditions.checkArgument(z2);
            if (i + 1 < cArr.length) {
                if (cArr2[i] < cArr[i + 1]) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                Preconditions.checkArgument(z2);
            }
        }
    }

    public boolean matches(char c) {
        int binarySearch = Arrays.binarySearch(this.rangeStarts, c);
        if (binarySearch >= 0) {
            return true;
        }
        binarySearch = (binarySearch ^ -1) - 1;
        if (binarySearch < 0 || c > this.rangeEnds[binarySearch]) {
            return false;
        }
        return true;
    }
}
