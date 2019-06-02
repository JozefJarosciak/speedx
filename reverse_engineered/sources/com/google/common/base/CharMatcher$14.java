package com.google.common.base;

class CharMatcher$14 extends CharMatcher {
    final /* synthetic */ Predicate val$predicate;

    CharMatcher$14(String str, Predicate predicate) {
        this.val$predicate = predicate;
        super(str);
    }

    public boolean matches(char c) {
        return this.val$predicate.apply(Character.valueOf(c));
    }

    public boolean apply(Character ch) {
        return this.val$predicate.apply(Preconditions.checkNotNull(ch));
    }
}
