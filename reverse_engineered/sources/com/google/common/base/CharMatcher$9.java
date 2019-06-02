package com.google.common.base;

import com.google.common.annotations.GwtIncompatible;
import java.util.BitSet;

class CharMatcher$9 extends CharMatcher$FastMatcher {
    final /* synthetic */ char val$match;

    CharMatcher$9(String str, char c) {
        this.val$match = c;
        super(str);
    }

    public boolean matches(char c) {
        return c == this.val$match;
    }

    public String replaceFrom(CharSequence charSequence, char c) {
        return charSequence.toString().replace(this.val$match, c);
    }

    public CharMatcher and(CharMatcher charMatcher) {
        return charMatcher.matches(this.val$match) ? this : NONE;
    }

    public CharMatcher or(CharMatcher charMatcher) {
        return charMatcher.matches(this.val$match) ? charMatcher : super.or(charMatcher);
    }

    public CharMatcher negate() {
        return isNot(this.val$match);
    }

    @GwtIncompatible("java.util.BitSet")
    void setBits(BitSet bitSet) {
        bitSet.set(this.val$match);
    }
}
