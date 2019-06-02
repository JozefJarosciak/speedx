package com.google.common.base;

abstract class CharMatcher$FastMatcher extends CharMatcher {
    public /* bridge */ /* synthetic */ boolean apply(Object obj) {
        return super.apply((Character) obj);
    }

    CharMatcher$FastMatcher() {
    }

    CharMatcher$FastMatcher(String str) {
        super(str);
    }

    public final CharMatcher precomputed() {
        return this;
    }

    public CharMatcher negate() {
        return new CharMatcher$NegatedFastMatcher(this);
    }
}
