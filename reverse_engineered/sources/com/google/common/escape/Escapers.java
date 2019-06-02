package com.google.common.escape;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import java.util.HashMap;
import java.util.Map;

@GwtCompatible
@Beta
public final class Escapers {
    private static final Escaper NULL_ESCAPER = new C37601();

    /* renamed from: com.google.common.escape.Escapers$1 */
    static class C37601 extends CharEscaper {
        C37601() {
        }

        public String escape(String str) {
            return (String) Preconditions.checkNotNull(str);
        }

        protected char[] escape(char c) {
            return null;
        }
    }

    @Beta
    public static final class Builder {
        private final Map<Character, String> replacementMap;
        private char safeMax;
        private char safeMin;
        private String unsafeReplacement;

        private Builder() {
            this.replacementMap = new HashMap();
            this.safeMin = '\u0000';
            this.safeMax = '￿';
            this.unsafeReplacement = null;
        }

        public Builder setSafeRange(char c, char c2) {
            this.safeMin = c;
            this.safeMax = c2;
            return this;
        }

        public Builder setUnsafeReplacement(String str) {
            this.unsafeReplacement = str;
            return this;
        }

        public Builder addEscape(char c, String str) {
            Preconditions.checkNotNull(str);
            this.replacementMap.put(Character.valueOf(c), str);
            return this;
        }

        public Escaper build() {
            return new ArrayBasedCharEscaper(this.replacementMap, this.safeMin, this.safeMax) {
                private final char[] replacementChars;

                protected char[] escapeUnsafe(char c) {
                    return this.replacementChars;
                }
            };
        }
    }

    private Escapers() {
    }

    public static Escaper nullEscaper() {
        return NULL_ESCAPER;
    }

    public static Builder builder() {
        return new Builder();
    }

    static UnicodeEscaper asUnicodeEscaper(Escaper escaper) {
        Preconditions.checkNotNull(escaper);
        if (escaper instanceof UnicodeEscaper) {
            return (UnicodeEscaper) escaper;
        }
        if (escaper instanceof CharEscaper) {
            return wrap((CharEscaper) escaper);
        }
        String str = "Cannot create a UnicodeEscaper from: ";
        String valueOf = String.valueOf(escaper.getClass().getName());
        throw new IllegalArgumentException(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
    }

    public static String computeReplacement(CharEscaper charEscaper, char c) {
        return stringOrNull(charEscaper.escape(c));
    }

    public static String computeReplacement(UnicodeEscaper unicodeEscaper, int i) {
        return stringOrNull(unicodeEscaper.escape(i));
    }

    private static String stringOrNull(char[] cArr) {
        return cArr == null ? null : new String(cArr);
    }

    private static UnicodeEscaper wrap(final CharEscaper charEscaper) {
        return new UnicodeEscaper() {
            protected char[] escape(int i) {
                int i2 = 0;
                if (i < 65536) {
                    return charEscaper.escape((char) i);
                }
                char[] cArr = new char[2];
                Character.toChars(i, cArr, 0);
                char[] escape = charEscaper.escape(cArr[0]);
                char[] escape2 = charEscaper.escape(cArr[1]);
                if (escape == null && escape2 == null) {
                    return null;
                }
                int length;
                int length2 = escape != null ? escape.length : 1;
                if (escape2 != null) {
                    length = escape2.length;
                } else {
                    length = 1;
                }
                char[] cArr2 = new char[(length + length2)];
                if (escape != null) {
                    for (length = 0; length < escape.length; length++) {
                        cArr2[length] = escape[length];
                    }
                } else {
                    cArr2[0] = cArr[0];
                }
                if (escape2 != null) {
                    while (i2 < escape2.length) {
                        cArr2[length2 + i2] = escape2[i2];
                        i2++;
                    }
                } else {
                    cArr2[length2] = cArr[1];
                }
                return cArr2;
            }
        };
    }
}
