package com.google.common.net;

import ch.qos.logback.core.CoreConstants;
import com.alibaba.fastjson.util.UTF8Decoder.Surrogate;
import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Preconditions;
import com.google.common.escape.UnicodeEscaper;

@GwtCompatible
@Beta
public final class PercentEscaper extends UnicodeEscaper {
    private static final char[] PLUS_SIGN = new char[]{'+'};
    private static final char[] UPPER_HEX_DIGITS = "0123456789ABCDEF".toCharArray();
    private final boolean plusForSpace;
    private final boolean[] safeOctets;

    public PercentEscaper(String str, boolean z) {
        Preconditions.checkNotNull(str);
        if (str.matches(".*[0-9A-Za-z].*")) {
            throw new IllegalArgumentException("Alphanumeric characters are always 'safe' and should not be explicitly specified");
        }
        String concat = String.valueOf(str).concat("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
        if (z && concat.contains(" ")) {
            throw new IllegalArgumentException("plusForSpace cannot be specified when space is a 'safe' character");
        }
        this.plusForSpace = z;
        this.safeOctets = createSafeOctets(concat);
    }

    private static boolean[] createSafeOctets(String str) {
        int i = 0;
        char[] toCharArray = str.toCharArray();
        int i2 = -1;
        for (char max : toCharArray) {
            i2 = Math.max(max, i2);
        }
        boolean[] zArr = new boolean[(i2 + 1)];
        i2 = toCharArray.length;
        while (i < i2) {
            zArr[toCharArray[i]] = true;
            i++;
        }
        return zArr;
    }

    protected int nextEscapeIndex(CharSequence charSequence, int i, int i2) {
        Preconditions.checkNotNull(charSequence);
        while (i < i2) {
            char charAt = charSequence.charAt(i);
            if (charAt >= this.safeOctets.length || !this.safeOctets[charAt]) {
                break;
            }
            i++;
        }
        return i;
    }

    public String escape(String str) {
        Preconditions.checkNotNull(str);
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt >= this.safeOctets.length || !this.safeOctets[charAt]) {
                return escapeSlow(str, i);
            }
        }
        return str;
    }

    protected char[] escape(int i) {
        if (i < this.safeOctets.length && this.safeOctets[i]) {
            return null;
        }
        if (i == 32 && this.plusForSpace) {
            return PLUS_SIGN;
        }
        if (i <= 127) {
            return new char[]{CoreConstants.PERCENT_CHAR, UPPER_HEX_DIGITS[i & 15], UPPER_HEX_DIGITS[i >>> 4]};
        } else if (i <= 2047) {
            r0 = new char[6];
            r0[0] = CoreConstants.PERCENT_CHAR;
            r0[3] = CoreConstants.PERCENT_CHAR;
            r0[5] = UPPER_HEX_DIGITS[i & 15];
            r1 = i >>> 4;
            r0[4] = UPPER_HEX_DIGITS[(r1 & 3) | 8];
            r1 >>>= 2;
            r0[2] = UPPER_HEX_DIGITS[r1 & 15];
            r0[1] = UPPER_HEX_DIGITS[(r1 >>> 4) | 12];
            return r0;
        } else if (i <= 65535) {
            r0 = new char[9];
            r1 = i >>> 4;
            r0[7] = UPPER_HEX_DIGITS[(r1 & 3) | 8];
            r1 >>>= 2;
            r0[5] = UPPER_HEX_DIGITS[r1 & 15];
            r1 >>>= 4;
            r0[4] = UPPER_HEX_DIGITS[(r1 & 3) | 8];
            r0[2] = UPPER_HEX_DIGITS[r1 >>> 2];
            return r0;
        } else if (i <= Surrogate.UCS4_MAX) {
            r0 = new char[12];
            r1 = i >>> 4;
            r0[10] = UPPER_HEX_DIGITS[(r1 & 3) | 8];
            r1 >>>= 2;
            r0[8] = UPPER_HEX_DIGITS[r1 & 15];
            r1 >>>= 4;
            r0[7] = UPPER_HEX_DIGITS[(r1 & 3) | 8];
            r1 >>>= 2;
            r0[5] = UPPER_HEX_DIGITS[r1 & 15];
            r1 >>>= 4;
            r0[4] = UPPER_HEX_DIGITS[(r1 & 3) | 8];
            r0[2] = UPPER_HEX_DIGITS[(r1 >>> 2) & 7];
            return r0;
        } else {
            throw new IllegalArgumentException("Invalid unicode character value " + i);
        }
    }
}
