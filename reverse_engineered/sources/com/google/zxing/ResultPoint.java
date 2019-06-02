package com.google.zxing;

import ch.qos.logback.core.CoreConstants;
import com.google.zxing.common.detector.MathUtils;

public class ResultPoint {
    /* renamed from: x */
    private final float f14641x;
    /* renamed from: y */
    private final float f14642y;

    public ResultPoint(float f, float f2) {
        this.f14641x = f;
        this.f14642y = f2;
    }

    public final float getX() {
        return this.f14641x;
    }

    public final float getY() {
        return this.f14642y;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof ResultPoint)) {
            return false;
        }
        ResultPoint resultPoint = (ResultPoint) obj;
        if (this.f14641x == resultPoint.f14641x && this.f14642y == resultPoint.f14642y) {
            return true;
        }
        return false;
    }

    public final int hashCode() {
        return (Float.floatToIntBits(this.f14641x) * 31) + Float.floatToIntBits(this.f14642y);
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder(25);
        stringBuilder.append(CoreConstants.LEFT_PARENTHESIS_CHAR);
        stringBuilder.append(this.f14641x);
        stringBuilder.append(CoreConstants.COMMA_CHAR);
        stringBuilder.append(this.f14642y);
        stringBuilder.append(CoreConstants.RIGHT_PARENTHESIS_CHAR);
        return stringBuilder.toString();
    }

    public static void orderBestPatterns(ResultPoint[] resultPointArr) {
        ResultPoint resultPoint;
        ResultPoint resultPoint2;
        ResultPoint resultPoint3;
        float distance = distance(resultPointArr[0], resultPointArr[1]);
        float distance2 = distance(resultPointArr[1], resultPointArr[2]);
        float distance3 = distance(resultPointArr[0], resultPointArr[2]);
        if (distance2 >= distance && distance2 >= distance3) {
            resultPoint = resultPointArr[0];
            resultPoint2 = resultPointArr[1];
            resultPoint3 = resultPointArr[2];
        } else if (distance3 < distance2 || distance3 < distance) {
            resultPoint = resultPointArr[2];
            resultPoint2 = resultPointArr[0];
            resultPoint3 = resultPointArr[1];
        } else {
            resultPoint = resultPointArr[1];
            resultPoint2 = resultPointArr[0];
            resultPoint3 = resultPointArr[2];
        }
        if (crossProductZ(resultPoint2, resultPoint, resultPoint3) >= 0.0f) {
            ResultPoint resultPoint4 = resultPoint3;
            resultPoint3 = resultPoint2;
            resultPoint2 = resultPoint4;
        }
        resultPointArr[0] = resultPoint3;
        resultPointArr[1] = resultPoint;
        resultPointArr[2] = resultPoint2;
    }

    public static float distance(ResultPoint resultPoint, ResultPoint resultPoint2) {
        return MathUtils.distance(resultPoint.f14641x, resultPoint.f14642y, resultPoint2.f14641x, resultPoint2.f14642y);
    }

    private static float crossProductZ(ResultPoint resultPoint, ResultPoint resultPoint2, ResultPoint resultPoint3) {
        float f = resultPoint2.f14641x;
        float f2 = resultPoint2.f14642y;
        return ((resultPoint3.f14641x - f) * (resultPoint.f14642y - f2)) - ((resultPoint.f14641x - f) * (resultPoint3.f14642y - f2));
    }
}
