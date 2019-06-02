package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.util.BitSet;

class CharMatcher$And extends CharMatcher {
    final CharMatcher first;
    final CharMatcher second;

    public /* bridge */ /* synthetic */ boolean apply(Object obj) {
        return super.apply((Character) obj);
    }

    CharMatcher$And(CharMatcher charMatcher, CharMatcher charMatcher2) {
        String valueOf = String.valueOf(String.valueOf(charMatcher));
        String valueOf2 = String.valueOf(String.valueOf(charMatcher2));
        this(charMatcher, charMatcher2, new StringBuilder((valueOf.length() + 19) + valueOf2.length()).append("CharMatcher.and(").append(valueOf).append(", ").append(valueOf2).append(")").toString());
    }

    CharMatcher$And(CharMatcher charMatcher, CharMatcher charMatcher2, String str) {
        super(str);
        this.first = (CharMatcher) Preconditions.checkNotNull(charMatcher);
        this.second = (CharMatcher) Preconditions.checkNotNull(charMatcher2);
    }

    public boolean matches(char c) {
        return this.first.matches(c) && this.second.matches(c);
    }

    @GwtIncompatible("java.util.BitSet")
    void setBits(BitSet bitSet) {
        BitSet bitSet2 = new BitSet();
        this.first.setBits(bitSet2);
        BitSet bitSet3 = new BitSet();
        this.second.setBits(bitSet3);
        bitSet2.and(bitSet3);
        bitSet.or(bitSet2);
    }

    CharMatcher withToString(String str) {
        return new CharMatcher$And(this.first, this.second, str);
    }
}
