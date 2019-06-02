package com.google.common.base;

class CharMatcher$8 extends CharMatcher$FastMatcher {
    CharMatcher$8(String str) {
        super(str);
    }

    public boolean matches(char c) {
        return false;
    }

    public int indexIn(CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        return -1;
    }

    public int indexIn(CharSequence charSequence, int i) {
        Preconditions.checkPositionIndex(i, charSequence.length());
        return -1;
    }

    public int lastIndexIn(CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        return -1;
    }

    public boolean matchesAllOf(CharSequence charSequence) {
        return charSequence.length() == 0;
    }

    public boolean matchesNoneOf(CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        return true;
    }

    public String removeFrom(CharSequence charSequence) {
        return charSequence.toString();
    }

    public String replaceFrom(CharSequence charSequence, char c) {
        return charSequence.toString();
    }

    public String replaceFrom(CharSequence charSequence, CharSequence charSequence2) {
        Preconditions.checkNotNull(charSequence2);
        return charSequence.toString();
    }

    public String collapseFrom(CharSequence charSequence, char c) {
        return charSequence.toString();
    }

    public String trimFrom(CharSequence charSequence) {
        return charSequence.toString();
    }

    public String trimLeadingFrom(CharSequence charSequence) {
        return charSequence.toString();
    }

    public String trimTrailingFrom(CharSequence charSequence) {
        return charSequence.toString();
    }

    public int countIn(CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        return 0;
    }

    public CharMatcher and(CharMatcher charMatcher) {
        Preconditions.checkNotNull(charMatcher);
        return this;
    }

    public CharMatcher or(CharMatcher charMatcher) {
        return (CharMatcher) Preconditions.checkNotNull(charMatcher);
    }

    public CharMatcher negate() {
        return ANY;
    }
}
