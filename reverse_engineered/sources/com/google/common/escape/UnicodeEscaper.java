package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;

@GwtCompatible
@Beta
public abstract class UnicodeEscaper extends Escaper {
    private static final int DEST_PAD = 32;

    protected abstract char[] escape(int i);

    protected UnicodeEscaper() {
    }

    protected int nextEscapeIndex(CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            int codePointAt = codePointAt(charSequence, i, i2);
            if (codePointAt < 0 || escape(codePointAt) != null) {
                break;
            }
            i += Character.isSupplementaryCodePoint(codePointAt) ? 2 : 1;
        }
        return i;
    }

    public String escape(String str) {
        Preconditions.checkNotNull(str);
        int length = str.length();
        int nextEscapeIndex = nextEscapeIndex(str, 0, length);
        return nextEscapeIndex == length ? str : escapeSlow(str, nextEscapeIndex);
    }

    protected final String escapeSlow(String str, int i) {
        int codePointAt;
        int length = str.length();
        char[] charBufferFromThreadLocal = Platform.charBufferFromThreadLocal();
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            codePointAt = codePointAt(str, i, length);
            if (codePointAt < 0) {
                throw new IllegalArgumentException("Trailing high surrogate at end of input");
            }
            int i4;
            Object escape = escape(codePointAt);
            codePointAt = (Character.isSupplementaryCodePoint(codePointAt) ? 2 : 1) + i;
            if (escape != null) {
                int i5 = i - i2;
                int length2 = (i3 + i5) + escape.length;
                if (charBufferFromThreadLocal.length < length2) {
                    charBufferFromThreadLocal = growBuffer(charBufferFromThreadLocal, i3, (length2 + (length - i)) + 32);
                }
                if (i5 > 0) {
                    str.getChars(i2, i, charBufferFromThreadLocal, i3);
                    i3 += i5;
                }
                if (escape.length > 0) {
                    System.arraycopy(escape, 0, charBufferFromThreadLocal, i3, escape.length);
                    i3 += escape.length;
                }
                i2 = i3;
                i3 = codePointAt;
            } else {
                i4 = i2;
                i2 = i3;
                i3 = i4;
            }
            i = nextEscapeIndex(str, codePointAt, length);
            i4 = i3;
            i3 = i2;
            i2 = i4;
        }
        codePointAt = length - i2;
        if (codePointAt > 0) {
            codePointAt += i3;
            if (charBufferFromThreadLocal.length < codePointAt) {
                charBufferFromThreadLocal = growBuffer(charBufferFromThreadLocal, i3, codePointAt);
            }
            str.getChars(i2, length, charBufferFromThreadLocal, i3);
            i3 = codePointAt;
        }
        return new String(charBufferFromThreadLocal, 0, i3);
    }

    protected static int codePointAt(CharSequence charSequence, int i, int i2) {
        Preconditions.checkNotNull(charSequence);
        if (i < i2) {
            int i3 = i + 1;
            int charAt = charSequence.charAt(i);
            if (charAt < 55296 || charAt > 57343) {
                return charAt;
            }
            String valueOf;
            if (charAt > 56319) {
                i3--;
                valueOf = String.valueOf(String.valueOf(charSequence));
                throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 88).append("Unexpected low surrogate character '").append(charAt).append("' with value ").append(charAt).append(" at index ").append(i3).append(" in '").append(valueOf).append("'").toString());
            } else if (i3 == i2) {
                return -charAt;
            } else {
                char charAt2 = charSequence.charAt(i3);
                if (Character.isLowSurrogate(charAt2)) {
                    return Character.toCodePoint(charAt, charAt2);
                }
                valueOf = String.valueOf(String.valueOf(charSequence));
                throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 89).append("Expected low surrogate but got char '").append(charAt2).append("' with value ").append(charAt2).append(" at index ").append(i3).append(" in '").append(valueOf).append("'").toString());
            }
        }
        throw new IndexOutOfBoundsException("Index exceeds specified range");
    }

    private static char[] growBuffer(char[] cArr, int i, int i2) {
        Object obj = new char[i2];
        if (i > 0) {
            System.arraycopy(cArr, 0, obj, 0, i);
        }
        return obj;
    }
}
