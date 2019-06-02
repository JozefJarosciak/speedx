package com.google.zxing.multi;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.NotFoundException;
import com.google.zxing.Reader;
import com.google.zxing.ReaderException;
import com.google.zxing.Result;
import com.google.zxing.ResultPoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public final class GenericMultipleBarcodeReader implements MultipleBarcodeReader {
    private static final int MAX_DEPTH = 4;
    private static final int MIN_DIMENSION_TO_RECUR = 100;
    private final Reader delegate;

    public GenericMultipleBarcodeReader(Reader reader) {
        this.delegate = reader;
    }

    public Result[] decodeMultiple(BinaryBitmap binaryBitmap) throws NotFoundException {
        return decodeMultiple(binaryBitmap, null);
    }

    public Result[] decodeMultiple(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map) throws NotFoundException {
        List arrayList = new ArrayList();
        doDecodeMultiple(binaryBitmap, map, arrayList, 0, 0, 0);
        if (!arrayList.isEmpty()) {
            return (Result[]) arrayList.toArray(new Result[arrayList.size()]);
        }
        throw NotFoundException.getNotFoundInstance();
    }

    private void doDecodeMultiple(BinaryBitmap binaryBitmap, Map<DecodeHintType, ?> map, List<Result> list, int i, int i2, int i3) {
        if (i3 <= 4) {
            try {
                Object obj;
                Result decode = this.delegate.decode(binaryBitmap, map);
                for (Result text : list) {
                    if (text.getText().equals(decode.getText())) {
                        obj = 1;
                        break;
                    }
                }
                obj = null;
                if (obj == null) {
                    list.add(translateResultPoints(decode, i, i2));
                }
                ResultPoint[] resultPoints = decode.getResultPoints();
                if (resultPoints != null && resultPoints.length != 0) {
                    Map<DecodeHintType, ?> map2;
                    List<Result> list2;
                    int i4;
                    int width = binaryBitmap.getWidth();
                    int height = binaryBitmap.getHeight();
                    float f = (float) width;
                    float f2 = (float) height;
                    float f3 = 0.0f;
                    float f4 = 0.0f;
                    int length = resultPoints.length;
                    int i5 = 0;
                    while (i5 < length) {
                        float f5;
                        float f6;
                        float f7;
                        ResultPoint resultPoint = resultPoints[i5];
                        if (resultPoint == null) {
                            f5 = f4;
                            f6 = f2;
                            f7 = f;
                            f = f3;
                        } else {
                            float x = resultPoint.getX();
                            f5 = resultPoint.getY();
                            if (x < f) {
                                f7 = x;
                            } else {
                                f7 = f;
                            }
                            if (f5 < f2) {
                                f6 = f5;
                            } else {
                                f6 = f2;
                            }
                            if (x > f3) {
                                f = x;
                            } else {
                                f = f3;
                            }
                            if (f5 <= f4) {
                                f5 = f4;
                            }
                        }
                        i5++;
                        f4 = f5;
                        f3 = f;
                        f2 = f6;
                        f = f7;
                    }
                    if (f > 100.0f) {
                        map2 = map;
                        list2 = list;
                        i4 = i;
                        i5 = i2;
                        doDecodeMultiple(binaryBitmap.crop(0, 0, (int) f, height), map2, list2, i4, i5, i3 + 1);
                    }
                    if (f2 > 100.0f) {
                        map2 = map;
                        list2 = list;
                        i4 = i;
                        i5 = i2;
                        doDecodeMultiple(binaryBitmap.crop(0, 0, width, (int) f2), map2, list2, i4, i5, i3 + 1);
                    }
                    if (f3 < ((float) (width - 100))) {
                        map2 = map;
                        list2 = list;
                        i5 = i2;
                        doDecodeMultiple(binaryBitmap.crop((int) f3, 0, width - ((int) f3), height), map2, list2, i + ((int) f3), i5, i3 + 1);
                    }
                    if (f4 < ((float) (height - 100))) {
                        map2 = map;
                        list2 = list;
                        i4 = i;
                        doDecodeMultiple(binaryBitmap.crop(0, (int) f4, width, height - ((int) f4)), map2, list2, i4, i2 + ((int) f4), i3 + 1);
                    }
                }
            } catch (ReaderException e) {
            }
        }
    }

    private static Result translateResultPoints(Result result, int i, int i2) {
        ResultPoint[] resultPoints = result.getResultPoints();
        if (resultPoints == null) {
            return result;
        }
        ResultPoint[] resultPointArr = new ResultPoint[resultPoints.length];
        for (int i3 = 0; i3 < resultPoints.length; i3++) {
            ResultPoint resultPoint = resultPoints[i3];
            if (resultPoint != null) {
                resultPointArr[i3] = new ResultPoint(resultPoint.getX() + ((float) i), resultPoint.getY() + ((float) i2));
            }
        }
        Result result2 = new Result(result.getText(), result.getRawBytes(), resultPointArr, result.getBarcodeFormat());
        result2.putAllMetadata(result.getResultMetadata());
        return result2;
    }
}
