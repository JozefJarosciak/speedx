package com.google.common.base;

import java.util.Arrays;

class CharMatcher$7 extends CharMatcher$FastMatcher {
    CharMatcher$7(String str) {
        super(str);
    }

    public boolean matches(char c) {
        return true;
    }

    public int indexIn(CharSequence charSequence) {
        return charSequence.length() == 0 ? -1 : 0;
    }

    public int indexIn(CharSequence charSequence, int i) {
        int length = charSequence.length();
        Preconditions.checkPositionIndex(i, length);
        return i == length ? -1 : i;
    }

    public int lastIndexIn(CharSequence charSequence) {
        return charSequence.length() - 1;
    }

    public boolean matchesAllOf(CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        return true;
    }

    public boolean matchesNoneOf(CharSequence charSequence) {
        return charSequence.length() == 0;
    }

    public String removeFrom(CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        return "";
    }

    public String replaceFrom(CharSequence charSequence, char c) {
        char[] cArr = new char[charSequence.length()];
        Arrays.fill(cArr, c);
        return new String(cArr);
    }

    public String replaceFrom(CharSequence charSequence, CharSequence charSequence2) {
        StringBuilder stringBuilder = new StringBuilder(charSequence.length() * charSequence2.length());
        for (int i = 0; i < charSequence.length(); i++) {
            stringBuilder.append(charSequence2);
        }
        return stringBuilder.toString();
    }

    public String collapseFrom(CharSequence charSequence, char c) {
        return charSequence.length() == 0 ? "" : String.valueOf(c);
    }

    public String trimFrom(CharSequence charSequence) {
        Preconditions.checkNotNull(charSequence);
        return "";
    }

    public int countIn(CharSequence charSequence) {
        return charSequence.length();
    }

    public CharMatcher and(CharMatcher charMatcher) {
        return (CharMatcher) Preconditions.checkNotNull(charMatcher);
    }

    public CharMatcher or(CharMatcher charMatcher) {
        Preconditions.checkNotNull(charMatcher);
        return this;
    }

    public CharMatcher negate() {
        return NONE;
    }
}
