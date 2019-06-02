package com.google.zxing.pdf417;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.Writer;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.pdf417.encoder.Compaction;
import com.google.zxing.pdf417.encoder.Dimensions;
import com.google.zxing.pdf417.encoder.PDF417;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.Map;

public final class PDF417Writer implements Writer {
    static final int DEFAULT_ERROR_CORRECTION_LEVEL = 2;
    static final int WHITE_SPACE = 30;

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2, Map<EncodeHintType, ?> map) throws WriterException {
        if (barcodeFormat != BarcodeFormat.PDF_417) {
            throw new IllegalArgumentException("Can only encode PDF_417, but got " + barcodeFormat);
        }
        PDF417 pdf417 = new PDF417();
        int i3 = 30;
        int i4 = 2;
        if (map != null) {
            if (map.containsKey(EncodeHintType.PDF417_COMPACT)) {
                pdf417.setCompact(((Boolean) map.get(EncodeHintType.PDF417_COMPACT)).booleanValue());
            }
            if (map.containsKey(EncodeHintType.PDF417_COMPACTION)) {
                pdf417.setCompaction((Compaction) map.get(EncodeHintType.PDF417_COMPACTION));
            }
            if (map.containsKey(EncodeHintType.PDF417_DIMENSIONS)) {
                Dimensions dimensions = (Dimensions) map.get(EncodeHintType.PDF417_DIMENSIONS);
                pdf417.setDimensions(dimensions.getMaxCols(), dimensions.getMinCols(), dimensions.getMaxRows(), dimensions.getMinRows());
            }
            if (map.containsKey(EncodeHintType.MARGIN)) {
                i3 = ((Number) map.get(EncodeHintType.MARGIN)).intValue();
            }
            if (map.containsKey(EncodeHintType.ERROR_CORRECTION)) {
                i4 = ((Number) map.get(EncodeHintType.ERROR_CORRECTION)).intValue();
            }
            if (map.containsKey(EncodeHintType.CHARACTER_SET)) {
                pdf417.setEncoding(Charset.forName((String) map.get(EncodeHintType.CHARACTER_SET)));
            }
        }
        return bitMatrixFromEncoder(pdf417, str, i4, i, i2, i3);
    }

    public BitMatrix encode(String str, BarcodeFormat barcodeFormat, int i, int i2) throws WriterException {
        return encode(str, barcodeFormat, i, i2, null);
    }

    private static BitMatrix bitMatrixFromEncoder(PDF417 pdf417, String str, int i, int i2, int i3, int i4) throws WriterException {
        int i5;
        pdf417.generateBarcodeLogic(str, i);
        byte[][] scaledMatrix = pdf417.getBarcodeMatrix().getScaledMatrix(1, 4);
        int i6 = i3 > i2 ? 1 : 0;
        if (scaledMatrix[0].length < scaledMatrix.length) {
            i5 = 1;
        } else {
            i5 = 0;
        }
        if ((i6 ^ i5) != 0) {
            scaledMatrix = rotateArray(scaledMatrix);
            i6 = 1;
        } else {
            i6 = 0;
        }
        int length = i2 / scaledMatrix[0].length;
        i5 = i3 / scaledMatrix.length;
        if (length >= i5) {
            length = i5;
        }
        if (length <= 1) {
            return bitMatrixFrombitArray(scaledMatrix, i4);
        }
        byte[][] rotateArray;
        byte[][] scaledMatrix2 = pdf417.getBarcodeMatrix().getScaledMatrix(length, length * 4);
        if (i6 != 0) {
            rotateArray = rotateArray(scaledMatrix2);
        } else {
            rotateArray = scaledMatrix2;
        }
        return bitMatrixFrombitArray(rotateArray, i4);
    }

    private static BitMatrix bitMatrixFrombitArray(byte[][] bArr, int i) {
        BitMatrix bitMatrix = new BitMatrix(bArr[0].length + (i * 2), bArr.length + (i * 2));
        bitMatrix.clear();
        int height = (bitMatrix.getHeight() - i) - 1;
        int i2 = 0;
        while (i2 < bArr.length) {
            for (int i3 = 0; i3 < bArr[0].length; i3++) {
                if (bArr[i2][i3] == (byte) 1) {
                    bitMatrix.set(i3 + i, height);
                }
            }
            i2++;
            height--;
        }
        return bitMatrix;
    }

    private static byte[][] rotateArray(byte[][] bArr) {
        byte[][] bArr2 = (byte[][]) Array.newInstance(Byte.TYPE, new int[]{bArr[0].length, bArr.length});
        for (int i = 0; i < bArr.length; i++) {
            int length = (bArr.length - i) - 1;
            for (int i2 = 0; i2 < bArr[0].length; i2++) {
                bArr2[i2][length] = bArr[i][i2];
            }
        }
        return bArr2;
    }
}
