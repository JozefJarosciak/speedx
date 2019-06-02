package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.util.Arrays;
import java.util.BitSet;

class CharMatcher$11 extends CharMatcher {
    final /* synthetic */ char[] val$chars;

    CharMatcher$11(String str, char[] cArr) {
        this.val$chars = cArr;
        super(str);
    }

    public /* bridge */ /* synthetic */ boolean apply(Object obj) {
        return super.apply((Character) obj);
    }

    public boolean matches(char c) {
        return Arrays.binarySearch(this.val$chars, c) >= 0;
    }

    @GwtIncompatible("java.util.BitSet")
    void setBits(BitSet bitSet) {
        for (char c : this.val$chars) {
            bitSet.set(c);
        }
    }
}
