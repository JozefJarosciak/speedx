package com.google.common.base;

final class CharMatcher$NegatedFastMatcher extends CharMatcher$NegatedMatcher {
    CharMatcher$NegatedFastMatcher(CharMatcher charMatcher) {
        super(charMatcher);
    }

    CharMatcher$NegatedFastMatcher(String str, CharMatcher charMatcher) {
        super(str, charMatcher);
    }

    public final CharMatcher precomputed() {
        return this;
    }

    CharMatcher withToString(String str) {
        return new CharMatcher$NegatedFastMatcher(str, this.original);
    }
}
