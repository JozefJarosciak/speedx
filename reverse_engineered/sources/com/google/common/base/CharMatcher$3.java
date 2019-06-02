package com.google.common.base;

class CharMatcher$3 extends CharMatcher {
    CharMatcher$3(String str) {
        super(str);
    }

    public /* bridge */ /* synthetic */ boolean apply(Object obj) {
        return super.apply((Character) obj);
    }

    public boolean matches(char c) {
        return Character.isLetter(c);
    }
}
