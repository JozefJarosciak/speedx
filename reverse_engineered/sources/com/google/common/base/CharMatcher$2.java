package com.google.common.base;

class CharMatcher$2 extends CharMatcher {
    CharMatcher$2(String str) {
        super(str);
    }

    public /* bridge */ /* synthetic */ boolean apply(Object obj) {
        return super.apply((Character) obj);
    }

    public boolean matches(char c) {
        return Character.isDigit(c);
    }
}
