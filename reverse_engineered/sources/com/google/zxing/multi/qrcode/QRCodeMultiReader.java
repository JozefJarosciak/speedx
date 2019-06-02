package com.google.zxing.multi.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultMetadataType;
import com.google.zxing.ResultPoint;
import com.google.zxing.common.DecoderResult;
import com.google.zxing.common.DetectorResult;
import com.google.zxing.multi.MultipleBarcodeReader;
import com.google.zxing.multi.qrcode.detector.MultiDetector;
import com.google.zxing.qrcode.QRCodeReader;
import com.google.zxing.qrcode.decoder.QRCodeDecoderMetaData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public final class QRCodeMultiReader extends QRCodeReader implements MultipleBarcodeReader {
    private static final Result[] EMPTY_RESULT_ARRAY = new Result[0];
    private static final ResultPoint[] NO_POINTS = new ResultPoint[0];

    private static final class SAComparator implements Serializable, Comparator<Result> {
        private SAComparator() {
        }

        public int compare(Result result, Result result2) {
            int intValue = ((Integer) result.getResultMetadata().get(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)).intValue();
            int intValue2 = ((Integer) result2.getResultMetadata().get(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)).intValue();
            if (intValue < intValue2) {
                return -1;
            }
            if (intValue > intValue2) {
                return 1;
            }
            return 0;
        }
    }

    public Result[] decodeMultiple(BinaryBitmap binaryBitmap) throws NotFoundException {
        return decodeMultiple(binaryBitmap, null);
    }

    public Result[] decodeMultiple(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException {
        List arrayList = new ArrayList();
        for (DetectorResult detectorResult : new MultiDetector(binaryBitmap.getBlackMatrix()).detectMulti(map)) {
            try {
                DecoderResult decode = getDecoder().decode(detectorResult.getBits(), (Map) map);
                ResultPoint[] points = detectorResult.getPoints();
                if (decode.getOther() instanceof QRCodeDecoderMetaData) {
                    ((QRCodeDecoderMetaData) decode.getOther()).applyMirroredCorrection(points);
                }
                Result result = new Result(decode.getText(), decode.getRawBytes(), points, BarcodeFormat.QR_CODE);
                List byteSegments = decode.getByteSegments();
                if (byteSegments != null) {
                    result.putMetadata(ResultMetadataType.BYTE_SEGMENTS, byteSegments);
                }
                String eCLevel = decode.getECLevel();
                if (eCLevel != null) {
                    result.putMetadata(ResultMetadataType.ERROR_CORRECTION_LEVEL, eCLevel);
                }
                if (decode.hasStructuredAppend()) {
                    result.putMetadata(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE, Integer.valueOf(decode.getStructuredAppendSequenceNumber()));
                    result.putMetadata(ResultMetadataType.STRUCTURED_APPEND_PARITY, Integer.valueOf(decode.getStructuredAppendParity()));
                }
                arrayList.add(result);
            } catch (ReaderException e) {
            }
        }
        if (arrayList.isEmpty()) {
            return EMPTY_RESULT_ARRAY;
        }
        List processStructuredAppend = processStructuredAppend(arrayList);
        return (Result[]) processStructuredAppend.toArray(new Result[processStructuredAppend.size()]);
    }

    private static List<Result> processStructuredAppend(List<Result> list) {
        Result result;
        int i;
        for (Result result2 : list) {
            if (result2.getResultMetadata().containsKey(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)) {
                i = 1;
                break;
            }
        }
        i = 0;
        if (i == 0) {
            return list;
        }
        List<Result> arrayList = new ArrayList();
        List<Result> arrayList2 = new ArrayList();
        for (Result result22 : list) {
            arrayList.add(result22);
            if (result22.getResultMetadata().containsKey(ResultMetadataType.STRUCTURED_APPEND_SEQUENCE)) {
                arrayList2.add(result22);
            }
        }
        Collections.sort(arrayList2, new SAComparator());
        StringBuilder stringBuilder = new StringBuilder();
        int i2 = 0;
        int i3 = 0;
        for (Result result222 : arrayList2) {
            stringBuilder.append(result222.getText());
            i3 += result222.getRawBytes().length;
            if (result222.getResultMetadata().containsKey(ResultMetadataType.BYTE_SEGMENTS)) {
                for (byte[] length : (Iterable) result222.getResultMetadata().get(ResultMetadataType.BYTE_SEGMENTS)) {
                    i2 += length.length;
                }
            }
            i2 = i2;
        }
        Object obj = new byte[i3];
        Object obj2 = new byte[i2];
        i3 = 0;
        int i4 = 0;
        for (Result result2222 : arrayList2) {
            System.arraycopy(result2222.getRawBytes(), 0, obj, i4, result2222.getRawBytes().length);
            i4 += result2222.getRawBytes().length;
            if (result2222.getResultMetadata().containsKey(ResultMetadataType.BYTE_SEGMENTS)) {
                for (byte[] length2 : (Iterable) result2222.getResultMetadata().get(ResultMetadataType.BYTE_SEGMENTS)) {
                    System.arraycopy(length2, 0, obj2, i3, length2.length);
                    i3 += length2.length;
                }
            }
            i3 = i3;
        }
        result2222 = new Result(stringBuilder.toString(), obj, NO_POINTS, BarcodeFormat.QR_CODE);
        if (i2 > 0) {
            Collection arrayList3 = new ArrayList();
            arrayList3.add(obj2);
            result2222.putMetadata(ResultMetadataType.BYTE_SEGMENTS, arrayList3);
        }
        arrayList.add(result2222);
        return arrayList;
    }
}
