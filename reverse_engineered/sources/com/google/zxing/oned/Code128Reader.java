package com.google.zxing.oned;

import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

public final class Code128Reader extends OneDReader {
    private static final int CODE_CODE_A = 101;
    private static final int CODE_CODE_B = 100;
    private static final int CODE_CODE_C = 99;
    private static final int CODE_FNC_1 = 102;
    private static final int CODE_FNC_2 = 97;
    private static final int CODE_FNC_3 = 96;
    private static final int CODE_FNC_4_A = 101;
    private static final int CODE_FNC_4_B = 100;
    static final int[][] CODE_PATTERNS = new int[][]{new int[]{2, 1, 2, 2, 2, 2}, new int[]{2, 2, 2, 1, 2, 2}, new int[]{2, 2, 2, 2, 2, 1}, new int[]{1, 2, 1, 2, 2, 3}, new int[]{1, 2, 1, 3, 2, 2}, new int[]{1, 3, 1, 2, 2, 2}, new int[]{1, 2, 2, 2, 1, 3}, new int[]{1, 2, 2, 3, 1, 2}, new int[]{1, 3, 2, 2, 1, 2}, new int[]{2, 2, 1, 2, 1, 3}, new int[]{2, 2, 1, 3, 1, 2}, new int[]{2, 3, 1, 2, 1, 2}, new int[]{1, 1, 2, 2, 3, 2}, new int[]{1, 2, 2, 1, 3, 2}, new int[]{1, 2, 2, 2, 3, 1}, new int[]{1, 1, 3, 2, 2, 2}, new int[]{1, 2, 3, 1, 2, 2}, new int[]{1, 2, 3, 2, 2, 1}, new int[]{2, 2, 3, 2, 1, 1}, new int[]{2, 2, 1, 1, 3, 2}, new int[]{2, 2, 1, 2, 3, 1}, new int[]{2, 1, 3, 2, 1, 2}, new int[]{2, 2, 3, 1, 1, 2}, new int[]{3, 1, 2, 1, 3, 1}, new int[]{3, 1, 1, 2, 2, 2}, new int[]{3, 2, 1, 1, 2, 2}, new int[]{3, 2, 1, 2, 2, 1}, new int[]{3, 1, 2, 2, 1, 2}, new int[]{3, 2, 2, 1, 1, 2}, new int[]{3, 2, 2, 2, 1, 1}, new int[]{2, 1, 2, 1, 2, 3}, new int[]{2, 1, 2, 3, 2, 1}, new int[]{2, 3, 2, 1, 2, 1}, new int[]{1, 1, 1, 3, 2, 3}, new int[]{1, 3, 1, 1, 2, 3}, new int[]{1, 3, 1, 3, 2, 1}, new int[]{1, 1, 2, 3, 1, 3}, new int[]{1, 3, 2, 1, 1, 3}, new int[]{1, 3, 2, 3, 1, 1}, new int[]{2, 1, 1, 3, 1, 3}, new int[]{2, 3, 1, 1, 1, 3}, new int[]{2, 3, 1, 3, 1, 1}, new int[]{1, 1, 2, 1, 3, 3}, new int[]{1, 1, 2, 3, 3, 1}, new int[]{1, 3, 2, 1, 3, 1}, new int[]{1, 1, 3, 1, 2, 3}, new int[]{1, 1, 3, 3, 2, 1}, new int[]{1, 3, 3, 1, 2, 1}, new int[]{3, 1, 3, 1, 2, 1}, new int[]{2, 1, 1, 3, 3, 1}, new int[]{2, 3, 1, 1, 3, 1}, new int[]{2, 1, 3, 1, 1, 3}, new int[]{2, 1, 3, 3, 1, 1}, new int[]{2, 1, 3, 1, 3, 1}, new int[]{3, 1, 1, 1, 2, 3}, new int[]{3, 1, 1, 3, 2, 1}, new int[]{3, 3, 1, 1, 2, 1}, new int[]{3, 1, 2, 1, 1, 3}, new int[]{3, 1, 2, 3, 1, 1}, new int[]{3, 3, 2, 1, 1, 1}, new int[]{3, 1, 4, 1, 1, 1}, new int[]{2, 2, 1, 4, 1, 1}, new int[]{4, 3, 1, 1, 1, 1}, new int[]{1, 1, 1, 2, 2, 4}, new int[]{1, 1, 1, 4, 2, 2}, new int[]{1, 2, 1, 1, 2, 4}, new int[]{1, 2, 1, 4, 2, 1}, new int[]{1, 4, 1, 1, 2, 2}, new int[]{1, 4, 1, 2, 2, 1}, new int[]{1, 1, 2, 2, 1, 4}, new int[]{1, 1, 2, 4, 1, 2}, new int[]{1, 2, 2, 1, 1, 4}, new int[]{1, 2, 2, 4, 1, 1}, new int[]{1, 4, 2, 1, 1, 2}, new int[]{1, 4, 2, 2, 1, 1}, new int[]{2, 4, 1, 2, 1, 1}, new int[]{2, 2, 1, 1, 1, 4}, new int[]{4, 1, 3, 1, 1, 1}, new int[]{2, 4, 1, 1, 1, 2}, new int[]{1, 3, 4, 1, 1, 1}, new int[]{1, 1, 1, 2, 4, 2}, new int[]{1, 2, 1, 1, 4, 2}, new int[]{1, 2, 1, 2, 4, 1}, new int[]{1, 1, 4, 2, 1, 2}, new int[]{1, 2, 4, 1, 1, 2}, new int[]{1, 2, 4, 2, 1, 1}, new int[]{4, 1, 1, 2, 1, 2}, new int[]{4, 2, 1, 1, 1, 2}, new int[]{4, 2, 1, 2, 1, 1}, new int[]{2, 1, 2, 1, 4, 1}, new int[]{2, 1, 4, 1, 2, 1}, new int[]{4, 1, 2, 1, 2, 1}, new int[]{1, 1, 1, 1, 4, 3}, new int[]{1, 1, 1, 3, 4, 1}, new int[]{1, 3, 1, 1, 4, 1}, new int[]{1, 1, 4, 1, 1, 3}, new int[]{1, 1, 4, 3, 1, 1}, new int[]{4, 1, 1, 1, 1, 3}, new int[]{4, 1, 1, 3, 1, 1}, new int[]{1, 1, 3, 1, 4, 1}, new int[]{1, 1, 4, 1, 3, 1}, new int[]{3, 1, 1, 1, 4, 1}, new int[]{4, 1, 1, 1, 3, 1}, new int[]{2, 1, 1, 4, 1, 2}, new int[]{2, 1, 1, 2, 1, 4}, new int[]{2, 1, 1, 2, 3, 2}, new int[]{2, 3, 3, 1, 1, 1, 2}};
    private static final int CODE_SHIFT = 98;
    private static final int CODE_START_A = 103;
    private static final int CODE_START_B = 104;
    private static final int CODE_START_C = 105;
    private static final int CODE_STOP = 106;
    private static final float MAX_AVG_VARIANCE = 0.25f;
    private static final float MAX_INDIVIDUAL_VARIANCE = 0.7f;

    private static int[] findStartPattern(BitArray bitArray) throws NotFoundException {
        int size = bitArray.getSize();
        int nextSet = bitArray.getNextSet(0);
        Object obj = new int[6];
        int length = obj.length;
        int i = nextSet;
        int i2 = 0;
        int i3 = nextSet;
        int i4 = 0;
        while (i < size) {
            int i5;
            int i6;
            if ((bitArray.get(i) ^ i2) != 0) {
                obj[i4] = obj[i4] + 1;
                i5 = i2;
                i6 = i4;
            } else {
                if (i4 == length - 1) {
                    float f = MAX_AVG_VARIANCE;
                    nextSet = -1;
                    i6 = 103;
                    while (i6 <= 105) {
                        float patternMatchVariance = OneDReader.patternMatchVariance(obj, CODE_PATTERNS[i6], MAX_INDIVIDUAL_VARIANCE);
                        if (patternMatchVariance < f) {
                            nextSet = i6;
                        } else {
                            patternMatchVariance = f;
                        }
                        i6++;
                        f = patternMatchVariance;
                    }
                    if (nextSet < 0 || !bitArray.isRange(Math.max(0, i3 - ((i - i3) / 2)), i3, false)) {
                        nextSet = (obj[0] + obj[1]) + i3;
                        System.arraycopy(obj, 2, obj, 0, length - 2);
                        obj[length - 2] = null;
                        obj[length - 1] = null;
                        i6 = i4 - 1;
                    } else {
                        return new int[]{i3, i, nextSet};
                    }
                }
                i6 = i4 + 1;
                nextSet = i3;
                obj[i6] = 1;
                if (i2 == 0) {
                    i5 = 1;
                } else {
                    boolean z = false;
                }
                i3 = nextSet;
            }
            i++;
            i2 = i5;
            i4 = i6;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private static int decodeCode(BitArray bitArray, int[] iArr, int i) throws NotFoundException {
        OneDReader.recordPattern(bitArray, i, iArr);
        float f = MAX_AVG_VARIANCE;
        int i2 = -1;
        for (int i3 = 0; i3 < CODE_PATTERNS.length; i3++) {
            float patternMatchVariance = OneDReader.patternMatchVariance(iArr, CODE_PATTERNS[i3], MAX_INDIVIDUAL_VARIANCE);
            if (patternMatchVariance < f) {
                i2 = i3;
                f = patternMatchVariance;
            }
        }
        if (i2 >= 0) {
            return i2;
        }
        throw NotFoundException.getNotFoundInstance();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.google.zxing.Result decodeRow(int r26, com.google.zxing.common.BitArray r27, java.util.Map<com.google.zxing.DecodeHintType, ?> r28) throws com.google.zxing.NotFoundException, com.google.zxing.FormatException, com.google.zxing.ChecksumException {
        /*
        r25 = this;
        if (r28 == 0) goto L_0x002f;
    L_0x0002:
        r2 = com.google.zxing.DecodeHintType.ASSUME_GS1;
        r0 = r28;
        r2 = r0.containsKey(r2);
        if (r2 == 0) goto L_0x002f;
    L_0x000c:
        r2 = 1;
    L_0x000d:
        r17 = findStartPattern(r27);
        r3 = 2;
        r7 = r17[r3];
        r18 = new java.util.ArrayList;
        r3 = 20;
        r0 = r18;
        r0.<init>(r3);
        r3 = (byte) r7;
        r3 = java.lang.Byte.valueOf(r3);
        r0 = r18;
        r0.add(r3);
        switch(r7) {
            case 103: goto L_0x0031;
            case 104: goto L_0x008d;
            case 105: goto L_0x0090;
            default: goto L_0x002a;
        };
    L_0x002a:
        r2 = com.google.zxing.FormatException.getFormatInstance();
        throw r2;
    L_0x002f:
        r2 = 0;
        goto L_0x000d;
    L_0x0031:
        r3 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
    L_0x0033:
        r12 = 0;
        r11 = 0;
        r19 = new java.lang.StringBuilder;
        r4 = 20;
        r0 = r19;
        r0.<init>(r4);
        r4 = 0;
        r9 = r17[r4];
        r4 = 1;
        r13 = r17[r4];
        r4 = 6;
        r0 = new int[r4];
        r20 = r0;
        r14 = 0;
        r8 = 0;
        r4 = 0;
        r10 = 1;
        r6 = 0;
        r5 = 0;
        r16 = r11;
        r11 = r9;
        r9 = r3;
        r3 = r4;
        r4 = r7;
        r7 = r14;
        r14 = r8;
        r8 = r12;
    L_0x0058:
        if (r8 != 0) goto L_0x0259;
    L_0x005a:
        r7 = 0;
        r0 = r27;
        r1 = r20;
        r15 = decodeCode(r0, r1, r13);
        r11 = (byte) r15;
        r11 = java.lang.Byte.valueOf(r11);
        r0 = r18;
        r0.add(r11);
        r11 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        if (r15 == r11) goto L_0x0072;
    L_0x0071:
        r10 = 1;
    L_0x0072:
        r11 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        if (r15 == r11) goto L_0x007b;
    L_0x0076:
        r3 = r3 + 1;
        r11 = r3 * r15;
        r4 = r4 + r11;
    L_0x007b:
        r0 = r20;
        r0 = r0.length;
        r21 = r0;
        r11 = 0;
        r12 = r13;
    L_0x0082:
        r0 = r21;
        if (r11 >= r0) goto L_0x0093;
    L_0x0086:
        r22 = r20[r11];
        r12 = r12 + r22;
        r11 = r11 + 1;
        goto L_0x0082;
    L_0x008d:
        r3 = 100;
        goto L_0x0033;
    L_0x0090:
        r3 = 99;
        goto L_0x0033;
    L_0x0093:
        switch(r15) {
            case 103: goto L_0x00bc;
            case 104: goto L_0x00bc;
            case 105: goto L_0x00bc;
            default: goto L_0x0096;
        };
    L_0x0096:
        switch(r9) {
            case 99: goto L_0x01c8;
            case 100: goto L_0x0156;
            case 101: goto L_0x00c1;
            default: goto L_0x0099;
        };
    L_0x0099:
        r23 = r5;
        r5 = r9;
        r9 = r7;
        r7 = r6;
        r6 = r23;
        r24 = r8;
        r8 = r10;
        r10 = r24;
    L_0x00a5:
        if (r16 == 0) goto L_0x00ad;
    L_0x00a7:
        r11 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        if (r5 != r11) goto L_0x0255;
    L_0x00ab:
        r5 = 100;
    L_0x00ad:
        r11 = r13;
        r16 = r9;
        r13 = r12;
        r9 = r5;
        r5 = r6;
        r6 = r7;
        r7 = r14;
        r14 = r15;
        r23 = r10;
        r10 = r8;
        r8 = r23;
        goto L_0x0058;
    L_0x00bc:
        r2 = com.google.zxing.FormatException.getFormatInstance();
        throw r2;
    L_0x00c1:
        r11 = 64;
        if (r15 >= r11) goto L_0x00e8;
    L_0x00c5:
        if (r5 != r6) goto L_0x00dd;
    L_0x00c7:
        r5 = r15 + 32;
        r5 = (char) r5;
        r0 = r19;
        r0.append(r5);
    L_0x00cf:
        r5 = 0;
        r23 = r5;
        r5 = r9;
        r9 = r7;
        r7 = r6;
        r6 = r23;
        r24 = r8;
        r8 = r10;
        r10 = r24;
        goto L_0x00a5;
    L_0x00dd:
        r5 = r15 + 32;
        r5 = r5 + 128;
        r5 = (char) r5;
        r0 = r19;
        r0.append(r5);
        goto L_0x00cf;
    L_0x00e8:
        r11 = 96;
        if (r15 >= r11) goto L_0x010d;
    L_0x00ec:
        if (r5 != r6) goto L_0x0104;
    L_0x00ee:
        r5 = r15 + -64;
        r5 = (char) r5;
        r0 = r19;
        r0.append(r5);
    L_0x00f6:
        r5 = 0;
        r23 = r5;
        r5 = r9;
        r9 = r7;
        r7 = r6;
        r6 = r23;
        r24 = r8;
        r8 = r10;
        r10 = r24;
        goto L_0x00a5;
    L_0x0104:
        r5 = r15 + 64;
        r5 = (char) r5;
        r0 = r19;
        r0.append(r5);
        goto L_0x00f6;
    L_0x010d:
        r11 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        if (r15 == r11) goto L_0x0112;
    L_0x0111:
        r10 = 0;
    L_0x0112:
        switch(r15) {
            case 96: goto L_0x0115;
            case 97: goto L_0x0115;
            case 98: goto L_0x014a;
            case 99: goto L_0x0151;
            case 100: goto L_0x014e;
            case 101: goto L_0x013a;
            case 102: goto L_0x0122;
            case 103: goto L_0x0115;
            case 104: goto L_0x0115;
            case 105: goto L_0x0115;
            case 106: goto L_0x0154;
            default: goto L_0x0115;
        };
    L_0x0115:
        r23 = r5;
        r5 = r9;
        r9 = r7;
        r7 = r6;
        r6 = r23;
        r24 = r8;
        r8 = r10;
        r10 = r24;
        goto L_0x00a5;
    L_0x0122:
        if (r2 == 0) goto L_0x0115;
    L_0x0124:
        r11 = r19.length();
        if (r11 != 0) goto L_0x0132;
    L_0x012a:
        r11 = "]C1";
        r0 = r19;
        r0.append(r11);
        goto L_0x0115;
    L_0x0132:
        r11 = 29;
        r0 = r19;
        r0.append(r11);
        goto L_0x0115;
    L_0x013a:
        if (r6 != 0) goto L_0x0141;
    L_0x013c:
        if (r5 == 0) goto L_0x0141;
    L_0x013e:
        r6 = 1;
        r5 = 0;
        goto L_0x0115;
    L_0x0141:
        if (r6 == 0) goto L_0x0148;
    L_0x0143:
        if (r5 == 0) goto L_0x0148;
    L_0x0145:
        r6 = 0;
        r5 = 0;
        goto L_0x0115;
    L_0x0148:
        r5 = 1;
        goto L_0x0115;
    L_0x014a:
        r7 = 1;
        r9 = 100;
        goto L_0x0115;
    L_0x014e:
        r9 = 100;
        goto L_0x0115;
    L_0x0151:
        r9 = 99;
        goto L_0x0115;
    L_0x0154:
        r8 = 1;
        goto L_0x0115;
    L_0x0156:
        r11 = 96;
        if (r15 >= r11) goto L_0x017e;
    L_0x015a:
        if (r5 != r6) goto L_0x0173;
    L_0x015c:
        r5 = r15 + 32;
        r5 = (char) r5;
        r0 = r19;
        r0.append(r5);
    L_0x0164:
        r5 = 0;
        r23 = r5;
        r5 = r9;
        r9 = r7;
        r7 = r6;
        r6 = r23;
        r24 = r8;
        r8 = r10;
        r10 = r24;
        goto L_0x00a5;
    L_0x0173:
        r5 = r15 + 32;
        r5 = r5 + 128;
        r5 = (char) r5;
        r0 = r19;
        r0.append(r5);
        goto L_0x0164;
    L_0x017e:
        r11 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        if (r15 == r11) goto L_0x0183;
    L_0x0182:
        r10 = 0;
    L_0x0183:
        switch(r15) {
            case 96: goto L_0x0186;
            case 97: goto L_0x0186;
            case 98: goto L_0x01bc;
            case 99: goto L_0x01c3;
            case 100: goto L_0x01ac;
            case 101: goto L_0x01c0;
            case 102: goto L_0x0194;
            case 103: goto L_0x0186;
            case 104: goto L_0x0186;
            case 105: goto L_0x0186;
            case 106: goto L_0x01c6;
            default: goto L_0x0186;
        };
    L_0x0186:
        r23 = r5;
        r5 = r9;
        r9 = r7;
        r7 = r6;
        r6 = r23;
        r24 = r8;
        r8 = r10;
        r10 = r24;
        goto L_0x00a5;
    L_0x0194:
        if (r2 == 0) goto L_0x0186;
    L_0x0196:
        r11 = r19.length();
        if (r11 != 0) goto L_0x01a4;
    L_0x019c:
        r11 = "]C1";
        r0 = r19;
        r0.append(r11);
        goto L_0x0186;
    L_0x01a4:
        r11 = 29;
        r0 = r19;
        r0.append(r11);
        goto L_0x0186;
    L_0x01ac:
        if (r6 != 0) goto L_0x01b3;
    L_0x01ae:
        if (r5 == 0) goto L_0x01b3;
    L_0x01b0:
        r6 = 1;
        r5 = 0;
        goto L_0x0186;
    L_0x01b3:
        if (r6 == 0) goto L_0x01ba;
    L_0x01b5:
        if (r5 == 0) goto L_0x01ba;
    L_0x01b7:
        r6 = 0;
        r5 = 0;
        goto L_0x0186;
    L_0x01ba:
        r5 = 1;
        goto L_0x0186;
    L_0x01bc:
        r7 = 1;
        r9 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x0186;
    L_0x01c0:
        r9 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x0186;
    L_0x01c3:
        r9 = 99;
        goto L_0x0186;
    L_0x01c6:
        r8 = 1;
        goto L_0x0186;
    L_0x01c8:
        r11 = 100;
        if (r15 >= r11) goto L_0x01ea;
    L_0x01cc:
        r11 = 10;
        if (r15 >= r11) goto L_0x01d7;
    L_0x01d0:
        r11 = 48;
        r0 = r19;
        r0.append(r11);
    L_0x01d7:
        r0 = r19;
        r0.append(r15);
        r23 = r5;
        r5 = r9;
        r9 = r7;
        r7 = r6;
        r6 = r23;
        r24 = r8;
        r8 = r10;
        r10 = r24;
        goto L_0x00a5;
    L_0x01ea:
        r11 = 106; // 0x6a float:1.49E-43 double:5.24E-322;
        if (r15 == r11) goto L_0x01ef;
    L_0x01ee:
        r10 = 0;
    L_0x01ef:
        switch(r15) {
            case 100: goto L_0x01f4;
            case 101: goto L_0x0236;
            case 102: goto L_0x0204;
            case 103: goto L_0x01f2;
            case 104: goto L_0x01f2;
            case 105: goto L_0x01f2;
            case 106: goto L_0x0246;
            default: goto L_0x01f2;
        };
    L_0x01f2:
        goto L_0x0099;
    L_0x01f4:
        r9 = 100;
        r23 = r5;
        r5 = r9;
        r9 = r7;
        r7 = r6;
        r6 = r23;
        r24 = r8;
        r8 = r10;
        r10 = r24;
        goto L_0x00a5;
    L_0x0204:
        if (r2 == 0) goto L_0x0099;
    L_0x0206:
        r11 = r19.length();
        if (r11 != 0) goto L_0x0221;
    L_0x020c:
        r11 = "]C1";
        r0 = r19;
        r0.append(r11);
        r23 = r5;
        r5 = r9;
        r9 = r7;
        r7 = r6;
        r6 = r23;
        r24 = r8;
        r8 = r10;
        r10 = r24;
        goto L_0x00a5;
    L_0x0221:
        r11 = 29;
        r0 = r19;
        r0.append(r11);
        r23 = r5;
        r5 = r9;
        r9 = r7;
        r7 = r6;
        r6 = r23;
        r24 = r8;
        r8 = r10;
        r10 = r24;
        goto L_0x00a5;
    L_0x0236:
        r9 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        r23 = r5;
        r5 = r9;
        r9 = r7;
        r7 = r6;
        r6 = r23;
        r24 = r8;
        r8 = r10;
        r10 = r24;
        goto L_0x00a5;
    L_0x0246:
        r8 = 1;
        r23 = r5;
        r5 = r9;
        r9 = r7;
        r7 = r6;
        r6 = r23;
        r24 = r8;
        r8 = r10;
        r10 = r24;
        goto L_0x00a5;
    L_0x0255:
        r5 = 101; // 0x65 float:1.42E-43 double:5.0E-322;
        goto L_0x00ad;
    L_0x0259:
        r2 = r13 - r11;
        r0 = r27;
        r5 = r0.getNextUnset(r13);
        r6 = r27.getSize();
        r8 = r5 - r11;
        r8 = r8 / 2;
        r8 = r8 + r5;
        r6 = java.lang.Math.min(r6, r8);
        r8 = 0;
        r0 = r27;
        r5 = r0.isRange(r5, r6, r8);
        if (r5 != 0) goto L_0x027c;
    L_0x0277:
        r2 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r2;
    L_0x027c:
        r3 = r3 * r7;
        r3 = r4 - r3;
        r3 = r3 % 103;
        if (r3 == r7) goto L_0x0288;
    L_0x0283:
        r2 = com.google.zxing.ChecksumException.getChecksumInstance();
        throw r2;
    L_0x0288:
        r3 = r19.length();
        if (r3 != 0) goto L_0x0293;
    L_0x028e:
        r2 = com.google.zxing.NotFoundException.getNotFoundInstance();
        throw r2;
    L_0x0293:
        if (r3 <= 0) goto L_0x02a2;
    L_0x0295:
        if (r10 == 0) goto L_0x02a2;
    L_0x0297:
        r4 = 99;
        if (r9 != r4) goto L_0x02d1;
    L_0x029b:
        r4 = r3 + -2;
        r0 = r19;
        r0.delete(r4, r3);
    L_0x02a2:
        r3 = 1;
        r3 = r17[r3];
        r4 = 0;
        r4 = r17[r4];
        r3 = r3 + r4;
        r3 = (float) r3;
        r4 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r4 = r3 / r4;
        r3 = (float) r11;
        r2 = (float) r2;
        r5 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = r2 / r5;
        r5 = r3 + r2;
        r6 = r18.size();
        r7 = new byte[r6];
        r2 = 0;
        r3 = r2;
    L_0x02bd:
        if (r3 >= r6) goto L_0x02d9;
    L_0x02bf:
        r0 = r18;
        r2 = r0.get(r3);
        r2 = (java.lang.Byte) r2;
        r2 = r2.byteValue();
        r7[r3] = r2;
        r2 = r3 + 1;
        r3 = r2;
        goto L_0x02bd;
    L_0x02d1:
        r4 = r3 + -1;
        r0 = r19;
        r0.delete(r4, r3);
        goto L_0x02a2;
    L_0x02d9:
        r2 = new com.google.zxing.Result;
        r3 = r19.toString();
        r6 = 2;
        r6 = new com.google.zxing.ResultPoint[r6];
        r8 = 0;
        r9 = new com.google.zxing.ResultPoint;
        r0 = r26;
        r10 = (float) r0;
        r9.<init>(r4, r10);
        r6[r8] = r9;
        r4 = 1;
        r8 = new com.google.zxing.ResultPoint;
        r0 = r26;
        r9 = (float) r0;
        r8.<init>(r5, r9);
        r6[r4] = r8;
        r4 = com.google.zxing.BarcodeFormat.CODE_128;
        r2.<init>(r3, r7, r6, r4);
        return r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.zxing.oned.Code128Reader.decodeRow(int, com.google.zxing.common.BitArray, java.util.Map):com.google.zxing.Result");
    }
}
