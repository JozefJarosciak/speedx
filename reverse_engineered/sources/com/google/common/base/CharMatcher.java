package com.google.common.base;

import ch.qos.logback.core.CoreConstants;
import com.google.android.gms.location.places.Place;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.GwtIncompatible;
import java.util.Arrays;
import java.util.BitSet;

@GwtCompatible(emulated = true)
@Beta
public abstract class CharMatcher implements Predicate<Character> {
    public static final CharMatcher ANY = new CharMatcher$7("CharMatcher.ANY");
    public static final CharMatcher ASCII = inRange('\u0000', Ascii.MAX, "CharMatcher.ASCII");
    public static final CharMatcher BREAKING_WHITESPACE = new CharMatcher$1();
    public static final CharMatcher DIGIT = new CharMatcher$RangesMatcher("CharMatcher.DIGIT", ZEROES.toCharArray(), NINES.toCharArray());
    private static final int DISTINCT_CHARS = 65536;
    public static final CharMatcher INVISIBLE = new CharMatcher$RangesMatcher("CharMatcher.INVISIBLE", "\u0000­؀؜۝܏ ᠎   ⁦⁧⁨⁩⁪　?﻿￹￺".toCharArray(), "  ­؄؜۝܏ ᠎‏ ⁤⁦⁧⁨⁩⁯　﻿￹￻".toCharArray());
    public static final CharMatcher JAVA_DIGIT = new CharMatcher$2("CharMatcher.JAVA_DIGIT");
    public static final CharMatcher JAVA_ISO_CONTROL = inRange('\u0000', '\u001f').or(inRange(Ascii.MAX, '')).withToString("CharMatcher.JAVA_ISO_CONTROL");
    public static final CharMatcher JAVA_LETTER = new CharMatcher$3("CharMatcher.JAVA_LETTER");
    public static final CharMatcher JAVA_LETTER_OR_DIGIT = new CharMatcher$4("CharMatcher.JAVA_LETTER_OR_DIGIT");
    public static final CharMatcher JAVA_LOWER_CASE = new CharMatcher$6("CharMatcher.JAVA_LOWER_CASE");
    public static final CharMatcher JAVA_UPPER_CASE = new CharMatcher$5("CharMatcher.JAVA_UPPER_CASE");
    private static final String NINES;
    public static final CharMatcher NONE = new CharMatcher$8("CharMatcher.NONE");
    public static final CharMatcher SINGLE_WIDTH = new CharMatcher$RangesMatcher("CharMatcher.SINGLE_WIDTH", "\u0000־א׳؀ݐ฀Ḁ℀ﭐﹰ｡".toCharArray(), "ӹ־ת״ۿݿ๿₯℺﷿﻿ￜ".toCharArray());
    public static final CharMatcher WHITESPACE = new CharMatcher$15("WHITESPACE");
    static final int WHITESPACE_MULTIPLIER = 1682554634;
    static final int WHITESPACE_SHIFT = Integer.numberOfLeadingZeros(WHITESPACE_TABLE.length() - 1);
    static final String WHITESPACE_TABLE = " 　\r   　 \u000b　   　 \t     \f 　 　　 \n 　";
    private static final String ZEROES = "0٠۰߀०০੦૦୦௦౦೦൦๐໐༠၀႐០᠐᥆᧐᭐᮰᱀᱐꘠꣐꤀꩐０";
    final String description;

    public abstract boolean matches(char c);

    static {
        StringBuilder stringBuilder = new StringBuilder(ZEROES.length());
        for (int i = 0; i < ZEROES.length(); i++) {
            stringBuilder.append((char) (ZEROES.charAt(i) + 9));
        }
        NINES = stringBuilder.toString();
    }

    private static String showCharacter(char c) {
        String str = "0123456789ABCDEF";
        char[] cArr = new char[]{CoreConstants.ESCAPE_CHAR, 'u', '\u0000', '\u0000', '\u0000', '\u0000'};
        for (int i = 0; i < 4; i++) {
            cArr[5 - i] = str.charAt(c & 15);
            c = (char) (c >> 4);
        }
        return String.copyValueOf(cArr);
    }

    public static CharMatcher is(char c) {
        String valueOf = String.valueOf(String.valueOf(showCharacter(c)));
        return new CharMatcher$9(new StringBuilder(valueOf.length() + 18).append("CharMatcher.is('").append(valueOf).append("')").toString(), c);
    }

    public static CharMatcher isNot(char c) {
        String valueOf = String.valueOf(String.valueOf(showCharacter(c)));
        return new CharMatcher$10(new StringBuilder(valueOf.length() + 21).append("CharMatcher.isNot('").append(valueOf).append("')").toString(), c);
    }

    public static CharMatcher anyOf(CharSequence charSequence) {
        int i = 0;
        switch (charSequence.length()) {
            case 0:
                return NONE;
            case 1:
                return is(charSequence.charAt(0));
            case 2:
                return isEither(charSequence.charAt(0), charSequence.charAt(1));
            default:
                char[] toCharArray = charSequence.toString().toCharArray();
                Arrays.sort(toCharArray);
                StringBuilder stringBuilder = new StringBuilder("CharMatcher.anyOf(\"");
                int length = toCharArray.length;
                while (i < length) {
                    stringBuilder.append(showCharacter(toCharArray[i]));
                    i++;
                }
                stringBuilder.append("\")");
                return new CharMatcher$11(stringBuilder.toString(), toCharArray);
        }
    }

    private static CharMatcher isEither(char c, char c2) {
        String valueOf = String.valueOf(String.valueOf(showCharacter(c)));
        String valueOf2 = String.valueOf(String.valueOf(showCharacter(c2)));
        return new CharMatcher$12(new StringBuilder((valueOf.length() + 21) + valueOf2.length()).append("CharMatcher.anyOf(\"").append(valueOf).append(valueOf2).append("\")").toString(), c, c2);
    }

    public static CharMatcher noneOf(CharSequence charSequence) {
        return anyOf(charSequence).negate();
    }

    public static CharMatcher inRange(char c, char c2) {
        Preconditions.checkArgument(c2 >= c);
        String valueOf = String.valueOf(String.valueOf(showCharacter(c)));
        String valueOf2 = String.valueOf(String.valueOf(showCharacter(c2)));
        return inRange(c, c2, new StringBuilder((valueOf.length() + 27) + valueOf2.length()).append("CharMatcher.inRange('").append(valueOf).append("', '").append(valueOf2).append("')").toString());
    }

    static CharMatcher inRange(char c, char c2, String str) {
        return new CharMatcher$13(str, c, c2);
    }

    public static CharMatcher forPredicate(Predicate<? super Character> predicate) {
        Preconditions.checkNotNull(predicate);
        if (predicate instanceof CharMatcher) {
            return (CharMatcher) predicate;
        }
        String valueOf = String.valueOf(String.valueOf(predicate));
        return new CharMatcher$14(new StringBuilder(valueOf.length() + 26).append("CharMatcher.forPredicate(").append(valueOf).append(")").toString(), predicate);
    }

    CharMatcher(String str) {
        this.description = str;
    }

    protected CharMatcher() {
        this.description = super.toString();
    }

    public CharMatcher negate() {
        return new CharMatcher$NegatedMatcher(this);
    }

    public CharMatcher and(CharMatcher charMatcher) {
        return new CharMatcher$And(this, (CharMatcher) Preconditions.checkNotNull(charMatcher));
    }

    public CharMatcher or(CharMatcher charMatcher) {
        return new CharMatcher$Or(this, (CharMatcher) Preconditions.checkNotNull(charMatcher));
    }

    public CharMatcher precomputed() {
        return Platform.precomputeCharMatcher(this);
    }

    CharMatcher withToString(String str) {
        throw new UnsupportedOperationException();
    }

    @GwtIncompatible("java.util.BitSet")
    CharMatcher precomputedInternal() {
        BitSet bitSet = new BitSet();
        setBits(bitSet);
        int cardinality = bitSet.cardinality();
        if (cardinality * 2 <= 65536) {
            return precomputedPositive(cardinality, bitSet, this.description);
        }
        bitSet.flip(0, 65536);
        int i = 65536 - cardinality;
        String str = ".negate()";
        if (this.description.endsWith(str)) {
            str = this.description.substring(0, this.description.length() - str.length());
        } else {
            String valueOf = String.valueOf(this.description);
            str = String.valueOf(str);
            str = str.length() != 0 ? valueOf.concat(str) : new String(valueOf);
        }
        return new CharMatcher$NegatedFastMatcher(toString(), precomputedPositive(i, bitSet, str));
    }

    @GwtIncompatible("java.util.BitSet")
    private static CharMatcher precomputedPositive(int i, BitSet bitSet, String str) {
        switch (i) {
            case 0:
                return NONE;
            case 1:
                return is((char) bitSet.nextSetBit(0));
            case 2:
                char nextSetBit = (char) bitSet.nextSetBit(0);
                return isEither(nextSetBit, (char) bitSet.nextSetBit(nextSetBit + 1));
            default:
                if (isSmall(i, bitSet.length())) {
                    return SmallCharMatcher.from(bitSet, str);
                }
                return new CharMatcher$BitSetMatcher(bitSet, str, null);
        }
    }

    @GwtIncompatible("SmallCharMatcher")
    private static boolean isSmall(int i, int i2) {
        return i <= Place.TYPE_SUBLOCALITY_LEVEL_1 && i2 > (i * 4) * 16;
    }

    @GwtIncompatible("java.util.BitSet")
    void setBits(BitSet bitSet) {
        for (int i = 65535; i >= 0; i--) {
            if (matches((char) i)) {
                bitSet.set(i);
            }
        }
    }

    public boolean matchesAnyOf(CharSequence charSequence) {
        return !matchesNoneOf(charSequence);
    }

    public boolean matchesAllOf(CharSequence charSequence) {
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (!matches(charSequence.charAt(length))) {
                return false;
            }
        }
        return true;
    }

    public boolean matchesNoneOf(CharSequence charSequence) {
        return indexIn(charSequence) == -1;
    }

    public int indexIn(CharSequence charSequence) {
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (matches(charSequence.charAt(i))) {
                return i;
            }
        }
        return -1;
    }

    public int indexIn(CharSequence charSequence, int i) {
        int length = charSequence.length();
        Preconditions.checkPositionIndex(i, length);
        for (int i2 = i; i2 < length; i2++) {
            if (matches(charSequence.charAt(i2))) {
                return i2;
            }
        }
        return -1;
    }

    public int lastIndexIn(CharSequence charSequence) {
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (matches(charSequence.charAt(length))) {
                return length;
            }
        }
        return -1;
    }

    public int countIn(CharSequence charSequence) {
        int i = 0;
        int i2 = 0;
        while (i < charSequence.length()) {
            if (matches(charSequence.charAt(i))) {
                i2++;
            }
            i++;
        }
        return i2;
    }

    public String removeFrom(CharSequence charSequence) {
        String charSequence2 = charSequence.toString();
        int indexIn = indexIn(charSequence2);
        if (indexIn == -1) {
            return charSequence2;
        }
        char[] toCharArray = charSequence2.toCharArray();
        int i = 1;
        while (true) {
            indexIn++;
            while (indexIn != toCharArray.length) {
                if (matches(toCharArray[indexIn])) {
                    i++;
                } else {
                    toCharArray[indexIn - i] = toCharArray[indexIn];
                    indexIn++;
                }
            }
            return new String(toCharArray, 0, indexIn - i);
        }
    }

    public String retainFrom(CharSequence charSequence) {
        return negate().removeFrom(charSequence);
    }

    public String replaceFrom(CharSequence charSequence, char c) {
        String charSequence2 = charSequence.toString();
        int indexIn = indexIn(charSequence2);
        if (indexIn == -1) {
            return charSequence2;
        }
        char[] toCharArray = charSequence2.toCharArray();
        toCharArray[indexIn] = c;
        for (int i = indexIn + 1; i < toCharArray.length; i++) {
            if (matches(toCharArray[i])) {
                toCharArray[i] = c;
            }
        }
        return new String(toCharArray);
    }

    public String replaceFrom(CharSequence charSequence, CharSequence charSequence2) {
        int i = 0;
        int length = charSequence2.length();
        if (length == 0) {
            return removeFrom(charSequence);
        }
        if (length == 1) {
            return replaceFrom(charSequence, charSequence2.charAt(0));
        }
        CharSequence charSequence3 = charSequence.toString();
        length = indexIn(charSequence3);
        if (length == -1) {
            return charSequence3;
        }
        int length2 = charSequence3.length();
        StringBuilder stringBuilder = new StringBuilder(((length2 * 3) / 2) + 16);
        do {
            stringBuilder.append(charSequence3, i, length);
            stringBuilder.append(charSequence2);
            i = length + 1;
            length = indexIn(charSequence3, i);
        } while (length != -1);
        stringBuilder.append(charSequence3, i, length2);
        return stringBuilder.toString();
    }

    public String trimFrom(CharSequence charSequence) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && matches(charSequence.charAt(i))) {
            i++;
        }
        int i2 = length - 1;
        while (i2 > i && matches(charSequence.charAt(i2))) {
            i2--;
        }
        return charSequence.subSequence(i, i2 + 1).toString();
    }

    public String trimLeadingFrom(CharSequence charSequence) {
        int length = charSequence.length();
        for (int i = 0; i < length; i++) {
            if (!matches(charSequence.charAt(i))) {
                return charSequence.subSequence(i, length).toString();
            }
        }
        return "";
    }

    public String trimTrailingFrom(CharSequence charSequence) {
        for (int length = charSequence.length() - 1; length >= 0; length--) {
            if (!matches(charSequence.charAt(length))) {
                return charSequence.subSequence(0, length + 1).toString();
            }
        }
        return "";
    }

    public String collapseFrom(CharSequence charSequence, char c) {
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (matches(charAt)) {
                if (charAt != c || (i != length - 1 && matches(charSequence.charAt(i + 1)))) {
                    StringBuilder append = new StringBuilder(length).append(charSequence.subSequence(0, i)).append(c);
                    return finishCollapseFrom(charSequence, i + 1, length, c, append, true);
                }
                i++;
            }
            i++;
        }
        return charSequence.toString();
    }

    public String trimAndCollapseFrom(CharSequence charSequence, char c) {
        int length = charSequence.length();
        int i = 0;
        while (i < length && matches(charSequence.charAt(i))) {
            i++;
        }
        int i2 = length - 1;
        while (i2 > i && matches(charSequence.charAt(i2))) {
            i2--;
        }
        if (i == 0 && i2 == length - 1) {
            return collapseFrom(charSequence, c);
        }
        return finishCollapseFrom(charSequence, i, i2 + 1, c, new StringBuilder((i2 + 1) - i), false);
    }

    private String finishCollapseFrom(CharSequence charSequence, int i, int i2, char c, StringBuilder stringBuilder, boolean z) {
        boolean z2 = z;
        while (i < i2) {
            char charAt = charSequence.charAt(i);
            if (!matches(charAt)) {
                stringBuilder.append(charAt);
                z2 = false;
            } else if (!z2) {
                stringBuilder.append(c);
                z2 = true;
            }
            i++;
        }
        return stringBuilder.toString();
    }

    @Deprecated
    public boolean apply(Character ch) {
        return matches(ch.charValue());
    }

    public String toString() {
        return this.description;
    }
}
