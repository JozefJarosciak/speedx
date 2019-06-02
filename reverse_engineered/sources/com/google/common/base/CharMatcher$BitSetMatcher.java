package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.util.BitSet;

@GwtIncompatible("java.util.BitSet")
class CharMatcher$BitSetMatcher extends CharMatcher$FastMatcher {
    private final BitSet table;

    private CharMatcher$BitSetMatcher(BitSet bitSet, String str) {
        BitSet bitSet2;
        super(str);
        if (bitSet.length() + 64 < bitSet.size()) {
            bitSet2 = (BitSet) bitSet.clone();
        } else {
            bitSet2 = bitSet;
        }
        this.table = bitSet2;
    }

    public boolean matches(char c) {
        return this.table.get(c);
    }

    void setBits(BitSet bitSet) {
        bitSet.or(this.table);
    }
}
