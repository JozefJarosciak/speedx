package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.util.BitSet;

class CharMatcher$Or extends CharMatcher {
    final CharMatcher first;
    final CharMatcher second;

    public /* bridge */ /* synthetic */ boolean apply(Object obj) {
        return super.apply((Character) obj);
    }

    CharMatcher$Or(CharMatcher charMatcher, CharMatcher charMatcher2, String str) {
        super(str);
        this.first = (CharMatcher) Preconditions.checkNotNull(charMatcher);
        this.second = (CharMatcher) Preconditions.checkNotNull(charMatcher2);
    }

    CharMatcher$Or(CharMatcher charMatcher, CharMatcher charMatcher2) {
        String valueOf = String.valueOf(String.valueOf(charMatcher));
        String valueOf2 = String.valueOf(String.valueOf(charMatcher2));
        this(charMatcher, charMatcher2, new StringBuilder((valueOf.length() + 18) + valueOf2.length()).append("CharMatcher.or(").append(valueOf).append(", ").append(valueOf2).append(")").toString());
    }

    @GwtIncompatible("java.util.BitSet")
    void setBits(BitSet bitSet) {
        this.first.setBits(bitSet);
        this.second.setBits(bitSet);
    }

    public boolean matches(char c) {
        return this.first.matches(c) || this.second.matches(c);
    }

    CharMatcher withToString(String str) {
        return new CharMatcher$Or(this.first, this.second, str);
    }
}
