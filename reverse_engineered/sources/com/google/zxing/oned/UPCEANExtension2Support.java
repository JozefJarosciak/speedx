package com.google.zxing.oned;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.BitArray;
import java.util.EnumMap;
import java.util.Map;

final class UPCEANExtension2Support {
    private final int[] decodeMiddleCounters = new int[4];
    private final StringBuilder decodeRowStringBuffer = new StringBuilder();

    UPCEANExtension2Support() {
    }

    Result decodeRow(int i, BitArray bitArray, int[] iArr) throws NotFoundException {
        StringBuilder stringBuilder = this.decodeRowStringBuffer;
        stringBuilder.setLength(0);
        int decodeMiddle = decodeMiddle(bitArray, iArr, stringBuilder);
        String stringBuilder2 = stringBuilder.toString();
        Map parseExtensionString = parseExtensionString(stringBuilder2);
        Result result = new Result(stringBuilder2, null, new ResultPoint[]{new ResultPoint(((float) (iArr[0] + iArr[1])) / 2.0f, (float) i), new ResultPoint((float) decodeMiddle, (float) i)}, BarcodeFormat.UPC_EAN_EXTENSION);
        if (parseExtensionString != null) {
            result.putAllMetadata(parseExtensionString);
        }
        return result;
    }

    int decodeMiddle(BitArray bitArray, int[] iArr, StringBuilder stringBuilder) throws NotFoundException {
        int[] iArr2 = this.decodeMiddleCounters;
        iArr2[0] = 0;
        iArr2[1] = 0;
        iArr2[2] = 0;
        iArr2[3] = 0;
        int size = bitArray.getSize();
        int i = iArr[1];
        int i2 = 0;
        for (int i3 = 0; i3 < 2 && i < size; i3++) {
            int decodeDigit = UPCEANReader.decodeDigit(bitArray, iArr2, i, UPCEANReader.L_AND_G_PATTERNS);
            stringBuilder.append((char) ((decodeDigit % 10) + 48));
            int i4 = 0;
            while (i4 < iArr2.length) {
                int i5 = iArr2[i4] + i;
                i4++;
                i = i5;
            }
            if (decodeDigit >= 10) {
                i2 |= 1 << (1 - i3);
            }
            if (i3 != 1) {
                i = bitArray.getNextUnset(bitArray.getNextSet(i));
            }
        }
        if (stringBuilder.length() != 2) {
            throw NotFoundException.getNotFoundInstance();
        } else if (Integer.parseInt(stringBuilder.toString()) % 4 == i2) {
            return i;
        } else {
            throw NotFoundException.getNotFoundInstance();
        }
    }

    private static Map<ResultMetadataType, Object> parseExtensionString(String str) {
        if (str.length() != 2) {
            return null;
        }
        Map<ResultMetadataType, Object> enumMap = new EnumMap(ResultMetadataType.class);
        enumMap.put(ResultMetadataType.ISSUE_NUMBER, Integer.valueOf(str));
        return enumMap;
    }
}
