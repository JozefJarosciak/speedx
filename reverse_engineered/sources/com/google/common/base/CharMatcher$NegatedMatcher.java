package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.util.BitSet;

class CharMatcher$NegatedMatcher extends CharMatcher {
    final CharMatcher original;

    public /* bridge */ /* synthetic */ boolean apply(Object obj) {
        return super.apply((Character) obj);
    }

    CharMatcher$NegatedMatcher(String str, CharMatcher charMatcher) {
        super(str);
        this.original = charMatcher;
    }

    CharMatcher$NegatedMatcher(CharMatcher charMatcher) {
        String valueOf = String.valueOf(String.valueOf(charMatcher));
        this(new StringBuilder(valueOf.length() + 9).append(valueOf).append(".negate()").toString(), charMatcher);
    }

    public boolean matches(char c) {
        return !this.original.matches(c);
    }

    public boolean matchesAllOf(CharSequence charSequence) {
        return this.original.matchesNoneOf(charSequence);
    }

    public boolean matchesNoneOf(CharSequence charSequence) {
        return this.original.matchesAllOf(charSequence);
    }

    public int countIn(CharSequence charSequence) {
        return charSequence.length() - this.original.countIn(charSequence);
    }

    @GwtIncompatible("java.util.BitSet")
    void setBits(BitSet bitSet) {
        BitSet bitSet2 = new BitSet();
        this.original.setBits(bitSet2);
        bitSet2.flip(0, 65536);
        bitSet.or(bitSet2);
    }

    public CharMatcher negate() {
        return this.original;
    }

    CharMatcher withToString(String str) {
        return new CharMatcher$NegatedMatcher(str, this.original);
    }
}
