package com.google.zxing.oned.rss.expanded.decoders;

import com.google.zxing.common.BitArray;

abstract class AI01weightDecoder extends AI01decoder {
    protected abstract void addWeightCode(StringBuilder stringBuilder, int i);

    protected abstract int checkWeight(int i);

    AI01weightDecoder(BitArray bitArray) {
        super(bitArray);
    }

    protected final void encodeCompressedWeight(StringBuilder stringBuilder, int i, int i2) {
        int extractNumericValueFromBitArray = getGeneralDecoder().extractNumericValueFromBitArray(i, i2);
        addWeightCode(stringBuilder, extractNumericValueFromBitArray);
        int checkWeight = checkWeight(extractNumericValueFromBitArray);
        int i3 = 100000;
        for (extractNumericValueFromBitArray = 0; extractNumericValueFromBitArray < 5; extractNumericValueFromBitArray++) {
            if (checkWeight / i3 == 0) {
                stringBuilder.append('0');
            }
            i3 /= 10;
        }
        stringBuilder.append(checkWeight);
    }
}
