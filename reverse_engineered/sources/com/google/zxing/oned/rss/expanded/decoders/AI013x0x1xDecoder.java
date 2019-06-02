package com.google.zxing.oned.rss.expanded.decoders;

import ch.qos.logback.core.CoreConstants;
import com.google.zxing.NotFoundException;
import com.google.zxing.common.BitArray;

final class AI013x0x1xDecoder extends AI01weightDecoder {
    private static final int DATE_SIZE = 16;
    private static final int HEADER_SIZE = 8;
    private static final int WEIGHT_SIZE = 20;
    private final String dateCode;
    private final String firstAIdigits;

    AI013x0x1xDecoder(BitArray bitArray, String str, String str2) {
        super(bitArray);
        this.dateCode = str2;
        this.firstAIdigits = str;
    }

    public String parseInformation() throws NotFoundException {
        if (getInformation().getSize() != 84) {
            throw NotFoundException.getNotFoundInstance();
        }
        StringBuilder stringBuilder = new StringBuilder();
        encodeCompressedGtin(stringBuilder, 8);
        encodeCompressedWeight(stringBuilder, 48, 20);
        encodeCompressedDate(stringBuilder, 68);
        return stringBuilder.toString();
    }

    private void encodeCompressedDate(StringBuilder stringBuilder, int i) {
        int extractNumericValueFromBitArray = getGeneralDecoder().extractNumericValueFromBitArray(i, 16);
        if (extractNumericValueFromBitArray != 38400) {
            stringBuilder.append(CoreConstants.LEFT_PARENTHESIS_CHAR);
            stringBuilder.append(this.dateCode);
            stringBuilder.append(CoreConstants.RIGHT_PARENTHESIS_CHAR);
            int i2 = extractNumericValueFromBitArray % 32;
            extractNumericValueFromBitArray /= 32;
            int i3 = (extractNumericValueFromBitArray % 12) + 1;
            extractNumericValueFromBitArray /= 12;
            if (extractNumericValueFromBitArray / 10 == 0) {
                stringBuilder.append('0');
            }
            stringBuilder.append(extractNumericValueFromBitArray);
            if (i3 / 10 == 0) {
                stringBuilder.append('0');
            }
            stringBuilder.append(i3);
            if (i2 / 10 == 0) {
                stringBuilder.append('0');
            }
            stringBuilder.append(i2);
        }
    }

    protected void addWeightCode(StringBuilder stringBuilder, int i) {
        int i2 = i / 100000;
        stringBuilder.append(CoreConstants.LEFT_PARENTHESIS_CHAR);
        stringBuilder.append(this.firstAIdigits);
        stringBuilder.append(i2);
        stringBuilder.append(CoreConstants.RIGHT_PARENTHESIS_CHAR);
    }

    protected int checkWeight(int i) {
        return i % 100000;
    }
}
