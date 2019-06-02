package com.google.thirdparty.publicsuffix;

import ch.qos.logback.core.CoreConstants;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap$Builder;
import com.google.common.collect.Lists;
import java.util.List;

@GwtCompatible
class TrieParser {
    private static final Joiner PREFIX_JOINER = Joiner.on("");

    TrieParser() {
    }

    static ImmutableMap<String, PublicSuffixType> parseTrie(CharSequence charSequence) {
        ImmutableMap$Builder builder = ImmutableMap.builder();
        int length = charSequence.length();
        int i = 0;
        while (i < length) {
            i += doParseTrieToBuilder(Lists.newLinkedList(), charSequence.subSequence(i, length), builder);
        }
        return builder.build();
    }

    private static int doParseTrieToBuilder(List<CharSequence> list, CharSequence charSequence, ImmutableMap$Builder<String, PublicSuffixType> immutableMap$Builder) {
        int i;
        int length = charSequence.length();
        char c = '\u0000';
        int i2 = 0;
        while (i2 < length) {
            c = charSequence.charAt(i2);
            if (c == '&' || c == '?' || c == '!' || c == CoreConstants.COLON_CHAR || c == CoreConstants.COMMA_CHAR) {
                break;
            }
            i2++;
        }
        list.add(0, reverse(charSequence.subSequence(0, i2)));
        if (c == '!' || c == '?' || c == CoreConstants.COLON_CHAR || c == CoreConstants.COMMA_CHAR) {
            String join = PREFIX_JOINER.join((Iterable) list);
            if (join.length() > 0) {
                immutableMap$Builder.put(join, PublicSuffixType.fromCode(c));
            }
        }
        i2++;
        if (c != '?' && c != CoreConstants.COMMA_CHAR) {
            i = i2;
            while (i < length) {
                i += doParseTrieToBuilder(list, charSequence.subSequence(i, length), immutableMap$Builder);
                if (charSequence.charAt(i) != '?') {
                    if (charSequence.charAt(i) == CoreConstants.COMMA_CHAR) {
                    }
                }
                i++;
                break;
            }
        }
        i = i2;
        list.remove(0);
        return i;
    }

    private static CharSequence reverse(CharSequence charSequence) {
        int i = 1;
        int length = charSequence.length();
        if (length <= 1) {
            return charSequence;
        }
        char[] cArr = new char[length];
        cArr[0] = charSequence.charAt(length - 1);
        while (i < length) {
            cArr[i] = charSequence.charAt((length - 1) - i);
            if (Character.isSurrogatePair(cArr[i], cArr[i - 1])) {
                swap(cArr, i - 1, i);
            }
            i++;
        }
        return new String(cArr);
    }

    private static void swap(char[] cArr, int i, int i2) {
        char c = cArr[i];
        cArr[i] = cArr[i2];
        cArr[i2] = c;
    }
}
