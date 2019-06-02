package com.alibaba.fastjson.util;

import ch.qos.logback.core.CoreConstants;
import com.alibaba.fastjson.JSONException;
import com.avos.avoscloud.AVException;
import java.io.Closeable;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;

public class IOUtils {
    static final char[] DigitOnes = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    static final char[] DigitTens = new char[]{'0', '0', '0', '0', '0', '0', '0', '0', '0', '0', '1', '1', '1', '1', '1', '1', '1', '1', '1', '1', '2', '2', '2', '2', '2', '2', '2', '2', '2', '2', '3', '3', '3', '3', '3', '3', '3', '3', '3', '3', '4', '4', '4', '4', '4', '4', '4', '4', '4', '4', '5', '5', '5', '5', '5', '5', '5', '5', '5', '5', '6', '6', '6', '6', '6', '6', '6', '6', '6', '6', '7', '7', '7', '7', '7', '7', '7', '7', '7', '7', '8', '8', '8', '8', '8', '8', '8', '8', '8', '8', '9', '9', '9', '9', '9', '9', '9', '9', '9', '9'};
    static final char[] digits = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
    static final int[] sizeTable = new int[]{9, 99, AVException.UNKNOWN, 9999, 99999, 999999, 9999999, 99999999, 999999999, Integer.MAX_VALUE};

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
            }
        }
    }

    public static int stringSize(long j) {
        long j2 = 10;
        for (int i = 1; i < 19; i++) {
            if (j < j2) {
                return i;
            }
            j2 *= 10;
        }
        return 19;
    }

    public static void getChars(long j, int i, char[] cArr) {
        char c;
        int i2;
        int i3;
        int i4;
        if (j < 0) {
            j = -j;
            c = CoreConstants.DASH_CHAR;
            i2 = i;
        } else {
            c = '\u0000';
            i2 = i;
        }
        while (j > 2147483647L) {
            long j2 = j / 100;
            i3 = (int) (j - (((j2 << 6) + (j2 << 5)) + (j2 << 2)));
            i2--;
            cArr[i2] = DigitOnes[i3];
            i = i2 - 1;
            cArr[i] = DigitTens[i3];
            i2 = i;
            j = j2;
        }
        i3 = (int) j;
        while (i3 >= 65536) {
            i4 = i3 / 100;
            i3 -= ((i4 << 6) + (i4 << 5)) + (i4 << 2);
            i2--;
            cArr[i2] = DigitOnes[i3];
            i2--;
            cArr[i2] = DigitTens[i3];
            i3 = i4;
        }
        i4 = i2;
        while (true) {
            i2 = (52429 * i3) >>> 19;
            int i5 = i3 - ((i2 << 3) + (i2 << 1));
            i3 = i4 - 1;
            cArr[i3] = digits[i5];
            if (i2 == 0) {
                break;
            }
            i4 = i3;
            i3 = i2;
        }
        if (c != '\u0000') {
            cArr[i3 - 1] = c;
        }
    }

    public static void getChars(int i, int i2, char[] cArr) {
        int i3;
        char c;
        int i4;
        if (i < 0) {
            i3 = -i;
            c = CoreConstants.DASH_CHAR;
            i4 = i2;
        } else {
            c = '\u0000';
            i3 = i;
            i4 = i2;
        }
        while (i3 >= 65536) {
            i = i3 / 100;
            i3 -= ((i << 6) + (i << 5)) + (i << 2);
            i4--;
            cArr[i4] = DigitOnes[i3];
            i2 = i4 - 1;
            cArr[i2] = DigitTens[i3];
            i4 = i2;
            i3 = i;
        }
        while (true) {
            int i5 = (52429 * i3) >>> 19;
            i4--;
            cArr[i4] = digits[i3 - ((i5 << 3) + (i5 << 1))];
            if (i5 == 0) {
                break;
            }
            i3 = i5;
        }
        if (c != '\u0000') {
            cArr[i4 - 1] = c;
        }
    }

    public static void getChars(byte b, int i, char[] cArr) {
        char c = '\u0000';
        if (b < (byte) 0) {
            c = CoreConstants.DASH_CHAR;
            b = -b;
        }
        while (true) {
            byte b2 = (52429 * b) >>> 19;
            i--;
            cArr[i] = digits[b - ((b2 << 3) + (b2 << 1))];
            if (b2 == (byte) 0) {
                break;
            }
            b = b2;
        }
        if (c != '\u0000') {
            cArr[i - 1] = c;
        }
    }

    public static int stringSize(int i) {
        int i2 = 0;
        while (i > sizeTable[i2]) {
            i2++;
        }
        return i2 + 1;
    }

    public static void decode(CharsetDecoder charsetDecoder, ByteBuffer byteBuffer, CharBuffer charBuffer) {
        try {
            CoderResult decode = charsetDecoder.decode(byteBuffer, charBuffer, true);
            if (!decode.isUnderflow()) {
                decode.throwException();
            }
            decode = charsetDecoder.flush(charBuffer);
            if (!decode.isUnderflow()) {
                decode.throwException();
            }
        } catch (Throwable e) {
            throw new JSONException(e.getMessage(), e);
        }
    }
}
