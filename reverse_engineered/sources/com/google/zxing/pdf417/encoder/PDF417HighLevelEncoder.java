package com.google.zxing.pdf417.encoder;

import ch.qos.logback.core.CoreConstants;
import com.google.common.primitives.SignedBytes;
import com.google.zxing.WriterException;
import com.google.zxing.common.CharacterSetECI;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.nio.charset.CharsetEncoder;
import java.util.Arrays;

final class PDF417HighLevelEncoder {
    private static final int BYTE_COMPACTION = 1;
    private static final Charset DEFAULT_ENCODING = Charset.forName("ISO-8859-1");
    private static final int ECI_CHARSET = 927;
    private static final int ECI_GENERAL_PURPOSE = 926;
    private static final int ECI_USER_DEFINED = 925;
    private static final int LATCH_TO_BYTE = 924;
    private static final int LATCH_TO_BYTE_PADDED = 901;
    private static final int LATCH_TO_NUMERIC = 902;
    private static final int LATCH_TO_TEXT = 900;
    private static final byte[] MIXED = new byte[128];
    private static final int NUMERIC_COMPACTION = 2;
    private static final byte[] PUNCTUATION = new byte[128];
    private static final int SHIFT_TO_BYTE = 913;
    private static final int SUBMODE_ALPHA = 0;
    private static final int SUBMODE_LOWER = 1;
    private static final int SUBMODE_MIXED = 2;
    private static final int SUBMODE_PUNCTUATION = 3;
    private static final int TEXT_COMPACTION = 0;
    private static final byte[] TEXT_MIXED_RAW = new byte[]{(byte) 48, Framer.STDOUT_FRAME_PREFIX, Framer.STDERR_FRAME_PREFIX, (byte) 51, (byte) 52, (byte) 53, (byte) 54, (byte) 55, (byte) 56, (byte) 57, (byte) 38, (byte) 13, (byte) 9, (byte) 44, (byte) 58, (byte) 35, Framer.STDIN_FRAME_PREFIX, (byte) 46, (byte) 36, (byte) 47, (byte) 43, (byte) 37, (byte) 42, (byte) 61, (byte) 94, (byte) 0, (byte) 32, (byte) 0, (byte) 0, (byte) 0};
    private static final byte[] TEXT_PUNCTUATION_RAW = new byte[]{(byte) 59, (byte) 60, (byte) 62, SignedBytes.MAX_POWER_OF_TWO, (byte) 91, (byte) 92, (byte) 93, Framer.STDIN_REQUEST_FRAME_PREFIX, (byte) 96, (byte) 126, Framer.ENTER_FRAME_PREFIX, (byte) 13, (byte) 9, (byte) 44, (byte) 58, (byte) 10, Framer.STDIN_FRAME_PREFIX, (byte) 46, (byte) 36, (byte) 47, (byte) 34, (byte) 124, (byte) 42, (byte) 40, (byte) 41, (byte) 63, (byte) 123, (byte) 125, (byte) 39, (byte) 0};

    static {
        byte b;
        byte b2 = (byte) 0;
        Arrays.fill(MIXED, (byte) -1);
        for (b = (byte) 0; b < TEXT_MIXED_RAW.length; b = (byte) (b + 1)) {
            byte b3 = TEXT_MIXED_RAW[b];
            if (b3 > (byte) 0) {
                MIXED[b3] = b;
            }
        }
        Arrays.fill(PUNCTUATION, (byte) -1);
        while (b2 < TEXT_PUNCTUATION_RAW.length) {
            b = TEXT_PUNCTUATION_RAW[b2];
            if (b > (byte) 0) {
                PUNCTUATION[b] = b2;
            }
            b2 = (byte) (b2 + 1);
        }
    }

    private PDF417HighLevelEncoder() {
    }

    static String encodeHighLevel(String str, Compaction compaction, Charset charset) throws WriterException {
        StringBuilder stringBuilder = new StringBuilder(str.length());
        if (charset == null) {
            charset = DEFAULT_ENCODING;
        } else if (!DEFAULT_ENCODING.equals(charset)) {
            CharacterSetECI characterSetECIByName = CharacterSetECI.getCharacterSetECIByName(charset.name());
            if (characterSetECIByName != null) {
                encodingECI(characterSetECIByName.getValue(), stringBuilder);
            }
        }
        int length = str.length();
        if (compaction == Compaction.TEXT) {
            encodeText(str, 0, length, stringBuilder, 0);
        } else if (compaction == Compaction.BYTE) {
            byte[] bytes = str.getBytes(charset);
            encodeBinary(bytes, 0, bytes.length, 1, stringBuilder);
        } else if (compaction == Compaction.NUMERIC) {
            stringBuilder.append('Ά');
            encodeNumeric(str, 0, length, stringBuilder);
        } else {
            int i = 0;
            int i2 = 0;
            int i3 = 0;
            while (i3 < length) {
                int determineConsecutiveDigitCount = determineConsecutiveDigitCount(str, i3);
                if (determineConsecutiveDigitCount >= 13) {
                    stringBuilder.append('Ά');
                    i = 2;
                    encodeNumeric(str, i3, determineConsecutiveDigitCount, stringBuilder);
                    determineConsecutiveDigitCount = i3 + determineConsecutiveDigitCount;
                    i2 = 0;
                } else {
                    int determineConsecutiveTextCount = determineConsecutiveTextCount(str, i3);
                    if (determineConsecutiveTextCount >= 5 || determineConsecutiveDigitCount == length) {
                        if (i != 0) {
                            stringBuilder.append('΄');
                            i = 0;
                            i2 = 0;
                        }
                        i2 = encodeText(str, i3, determineConsecutiveTextCount, stringBuilder, i2);
                        determineConsecutiveDigitCount = i3 + determineConsecutiveTextCount;
                    } else {
                        determineConsecutiveDigitCount = determineConsecutiveBinaryCount(str, i3, charset);
                        if (determineConsecutiveDigitCount == 0) {
                            determineConsecutiveDigitCount = 1;
                        }
                        byte[] bytes2 = str.substring(i3, i3 + determineConsecutiveDigitCount).getBytes(charset);
                        if (bytes2.length == 1 && i == 0) {
                            encodeBinary(bytes2, 0, 1, 0, stringBuilder);
                        } else {
                            encodeBinary(bytes2, 0, bytes2.length, i, stringBuilder);
                            i = 1;
                            i2 = 0;
                        }
                        determineConsecutiveDigitCount += i3;
                    }
                }
                i3 = determineConsecutiveDigitCount;
            }
        }
        return stringBuilder.toString();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int encodeText(java.lang.CharSequence r9, int r10, int r11, java.lang.StringBuilder r12, int r13) {
        /*
        r8 = 28;
        r7 = 27;
        r2 = 1;
        r6 = 29;
        r1 = 0;
        r5 = new java.lang.StringBuilder;
        r5.<init>(r11);
        r0 = r1;
    L_0x000e:
        r3 = r10 + r0;
        r3 = r9.charAt(r3);
        switch(r13) {
            case 0: goto L_0x0047;
            case 1: goto L_0x0080;
            case 2: goto L_0x00c1;
            default: goto L_0x0017;
        };
    L_0x0017:
        r4 = isPunctuation(r3);
        if (r4 == 0) goto L_0x0112;
    L_0x001d:
        r4 = PUNCTUATION;
        r3 = r4[r3];
        r3 = (char) r3;
        r5.append(r3);
    L_0x0025:
        r0 = r0 + 1;
        if (r0 < r11) goto L_0x000e;
    L_0x0029:
        r6 = r5.length();
        r3 = r1;
        r4 = r1;
    L_0x002f:
        if (r3 >= r6) goto L_0x0121;
    L_0x0031:
        r0 = r3 % 2;
        if (r0 == 0) goto L_0x0118;
    L_0x0035:
        r0 = r2;
    L_0x0036:
        if (r0 == 0) goto L_0x011b;
    L_0x0038:
        r0 = r4 * 30;
        r4 = r5.charAt(r3);
        r0 = r0 + r4;
        r0 = (char) r0;
        r12.append(r0);
    L_0x0043:
        r3 = r3 + 1;
        r4 = r0;
        goto L_0x002f;
    L_0x0047:
        r4 = isAlphaUpper(r3);
        if (r4 == 0) goto L_0x005e;
    L_0x004d:
        r4 = 32;
        if (r3 != r4) goto L_0x0057;
    L_0x0051:
        r3 = 26;
        r5.append(r3);
        goto L_0x0025;
    L_0x0057:
        r3 = r3 + -65;
        r3 = (char) r3;
        r5.append(r3);
        goto L_0x0025;
    L_0x005e:
        r4 = isAlphaLower(r3);
        if (r4 == 0) goto L_0x0069;
    L_0x0064:
        r5.append(r7);
        r13 = r2;
        goto L_0x000e;
    L_0x0069:
        r4 = isMixed(r3);
        if (r4 == 0) goto L_0x0074;
    L_0x006f:
        r13 = 2;
        r5.append(r8);
        goto L_0x000e;
    L_0x0074:
        r5.append(r6);
        r4 = PUNCTUATION;
        r3 = r4[r3];
        r3 = (char) r3;
        r5.append(r3);
        goto L_0x0025;
    L_0x0080:
        r4 = isAlphaLower(r3);
        if (r4 == 0) goto L_0x0097;
    L_0x0086:
        r4 = 32;
        if (r3 != r4) goto L_0x0090;
    L_0x008a:
        r3 = 26;
        r5.append(r3);
        goto L_0x0025;
    L_0x0090:
        r3 = r3 + -97;
        r3 = (char) r3;
        r5.append(r3);
        goto L_0x0025;
    L_0x0097:
        r4 = isAlphaUpper(r3);
        if (r4 == 0) goto L_0x00a8;
    L_0x009d:
        r5.append(r7);
        r3 = r3 + -65;
        r3 = (char) r3;
        r5.append(r3);
        goto L_0x0025;
    L_0x00a8:
        r4 = isMixed(r3);
        if (r4 == 0) goto L_0x00b4;
    L_0x00ae:
        r13 = 2;
        r5.append(r8);
        goto L_0x000e;
    L_0x00b4:
        r5.append(r6);
        r4 = PUNCTUATION;
        r3 = r4[r3];
        r3 = (char) r3;
        r5.append(r3);
        goto L_0x0025;
    L_0x00c1:
        r4 = isMixed(r3);
        if (r4 == 0) goto L_0x00d1;
    L_0x00c7:
        r4 = MIXED;
        r3 = r4[r3];
        r3 = (char) r3;
        r5.append(r3);
        goto L_0x0025;
    L_0x00d1:
        r4 = isAlphaUpper(r3);
        if (r4 == 0) goto L_0x00dd;
    L_0x00d7:
        r5.append(r8);
        r13 = r1;
        goto L_0x000e;
    L_0x00dd:
        r4 = isAlphaLower(r3);
        if (r4 == 0) goto L_0x00e9;
    L_0x00e3:
        r5.append(r7);
        r13 = r2;
        goto L_0x000e;
    L_0x00e9:
        r4 = r10 + r0;
        r4 = r4 + 1;
        if (r4 >= r11) goto L_0x0105;
    L_0x00ef:
        r4 = r10 + r0;
        r4 = r4 + 1;
        r4 = r9.charAt(r4);
        r4 = isPunctuation(r4);
        if (r4 == 0) goto L_0x0105;
    L_0x00fd:
        r13 = 3;
        r3 = 25;
        r5.append(r3);
        goto L_0x000e;
    L_0x0105:
        r5.append(r6);
        r4 = PUNCTUATION;
        r3 = r4[r3];
        r3 = (char) r3;
        r5.append(r3);
        goto L_0x0025;
    L_0x0112:
        r5.append(r6);
        r13 = r1;
        goto L_0x000e;
    L_0x0118:
        r0 = r1;
        goto L_0x0036;
    L_0x011b:
        r0 = r5.charAt(r3);
        goto L_0x0043;
    L_0x0121:
        r0 = r6 % 2;
        if (r0 == 0) goto L_0x012d;
    L_0x0125:
        r0 = r4 * 30;
        r0 = r0 + 29;
        r0 = (char) r0;
        r12.append(r0);
    L_0x012d:
        return r13;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.pdf417.encoder.PDF417HighLevelEncoder.encodeText(java.lang.CharSequence, int, int, java.lang.StringBuilder, int):int");
    }

    private static void encodeBinary(byte[] bArr, int i, int i2, int i3, StringBuilder stringBuilder) {
        int i4;
        if (i2 == 1 && i3 == 0) {
            stringBuilder.append('Α');
        } else {
            if ((i2 % 6 == 0 ? 1 : null) != null) {
                stringBuilder.append('Μ');
            } else {
                stringBuilder.append('΅');
            }
        }
        if (i2 >= 6) {
            char[] cArr = new char[5];
            i4 = i;
            while ((i + i2) - i4 >= 6) {
                int i5;
                long j = 0;
                for (i5 = 0; i5 < 6; i5++) {
                    j = (j << 8) + ((long) (bArr[i4 + i5] & 255));
                }
                for (i5 = 0; i5 < 5; i5++) {
                    cArr[i5] = (char) ((int) (j % 900));
                    j /= 900;
                }
                for (i5 = cArr.length - 1; i5 >= 0; i5--) {
                    stringBuilder.append(cArr[i5]);
                }
                i4 += 6;
            }
        } else {
            i4 = i;
        }
        while (i4 < i + i2) {
            stringBuilder.append((char) (bArr[i4] & 255));
            i4++;
        }
    }

    private static void encodeNumeric(String str, int i, int i2, StringBuilder stringBuilder) {
        StringBuilder stringBuilder2 = new StringBuilder((i2 / 3) + 1);
        BigInteger valueOf = BigInteger.valueOf(900);
        BigInteger valueOf2 = BigInteger.valueOf(0);
        int i3 = 0;
        while (i3 < i2) {
            stringBuilder2.setLength(0);
            int min = Math.min(44, i2 - i3);
            BigInteger bigInteger = new BigInteger('1' + str.substring(i + i3, (i + i3) + min));
            do {
                stringBuilder2.append((char) bigInteger.mod(valueOf).intValue());
                bigInteger = bigInteger.divide(valueOf);
            } while (!bigInteger.equals(valueOf2));
            for (int length = stringBuilder2.length() - 1; length >= 0; length--) {
                stringBuilder.append(stringBuilder2.charAt(length));
            }
            i3 += min;
        }
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private static boolean isAlphaUpper(char c) {
        return c == ' ' || (c >= 'A' && c <= 'Z');
    }

    private static boolean isAlphaLower(char c) {
        return c == ' ' || (c >= 'a' && c <= 'z');
    }

    private static boolean isMixed(char c) {
        return MIXED[c] != (byte) -1;
    }

    private static boolean isPunctuation(char c) {
        return PUNCTUATION[c] != (byte) -1;
    }

    private static boolean isText(char c) {
        return c == '\t' || c == '\n' || c == '\r' || (c >= ' ' && c <= '~');
    }

    private static int determineConsecutiveDigitCount(CharSequence charSequence, int i) {
        int i2 = 0;
        int length = charSequence.length();
        if (i < length) {
            char charAt = charSequence.charAt(i);
            while (isDigit(charAt) && i < length) {
                i2++;
                i++;
                if (i < length) {
                    charAt = charSequence.charAt(i);
                }
            }
        }
        return i2;
    }

    private static int determineConsecutiveTextCount(CharSequence charSequence, int i) {
        int length = charSequence.length();
        int i2 = i;
        while (i2 < length) {
            char charAt = charSequence.charAt(i2);
            int i3 = 0;
            while (i3 < 13 && isDigit(r2) && i2 < length) {
                i3++;
                int i4 = i2 + 1;
                if (i4 < length) {
                    charAt = charSequence.charAt(i4);
                    i2 = i4;
                } else {
                    i2 = i4;
                }
            }
            if (i3 >= 13) {
                return (i2 - i) - i3;
            }
            if (i3 <= 0) {
                if (!isText(charSequence.charAt(i2))) {
                    break;
                }
                i2++;
            }
        }
        return i2 - i;
    }

    private static int determineConsecutiveBinaryCount(String str, int i, Charset charset) throws WriterException {
        CharsetEncoder newEncoder = charset.newEncoder();
        int length = str.length();
        int i2 = i;
        while (i2 < length) {
            char charAt = str.charAt(i2);
            int i3 = 0;
            while (i3 < 13 && isDigit(r1)) {
                i3++;
                int i4 = i2 + i3;
                if (i4 >= length) {
                    break;
                }
                charAt = str.charAt(i4);
            }
            if (i3 >= 13) {
                return i2 - i;
            }
            char charAt2 = str.charAt(i2);
            if (newEncoder.canEncode(charAt2)) {
                i2++;
            } else {
                throw new WriterException("Non-encodable character detected: " + charAt2 + " (Unicode: " + charAt2 + CoreConstants.RIGHT_PARENTHESIS_CHAR);
            }
        }
        return i2 - i;
    }

    private static void encodingECI(int i, StringBuilder stringBuilder) throws WriterException {
        if (i >= 0 && i < LATCH_TO_TEXT) {
            stringBuilder.append('Ο');
            stringBuilder.append((char) i);
        } else if (i < 810900) {
            stringBuilder.append('Ξ');
            stringBuilder.append((char) ((i / LATCH_TO_TEXT) - 1));
            stringBuilder.append((char) (i % LATCH_TO_TEXT));
        } else if (i < 811800) {
            stringBuilder.append('Ν');
            stringBuilder.append((char) (810900 - i));
        } else {
            throw new WriterException("ECI number not in valid range from 0..811799, but was " + i);
        }
    }
}
