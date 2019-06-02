package com.google.common.base;

class CharMatcher$5 extends CharMatcher {
    CharMatcher$5(String str) {
        super(str);
    }

    public /* bridge */ /* synthetic */ boolean apply(Object obj) {
        return super.apply((Character) obj);
    }

    public boolean matches(char c) {
        return Character.isUpperCase(c);
    }
}
