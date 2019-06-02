package org.xclcharts.common;

import android.graphics.PointF;
import com.alipay.sdk.cons.C0844a;
import java.math.BigDecimal;

public class MathHelper {
    private static final int DEFAULT_DIV_SCALE = 10;
    private static MathHelper instance = null;
    private boolean mHighPrecision = true;
    private PointF mPointF = new PointF();
    private float mPosX = 0.0f;
    private float mPosY = 0.0f;

    public static synchronized MathHelper getInstance() {
        MathHelper mathHelper;
        synchronized (MathHelper.class) {
            if (instance == null) {
                instance = new MathHelper();
            }
            mathHelper = instance;
        }
        return mathHelper;
    }

    private void resetEndPointXY() {
        this.mPosY = 0.0f;
        this.mPosX = 0.0f;
        this.mPointF.x = this.mPosX;
        this.mPointF.y = this.mPosY;
    }

    public PointF calcArcEndPointXY(float f, float f2, float f3, float f4) {
        resetEndPointXY();
        if (Float.compare(f4, 0.0f) == 0 || Float.compare(f3, 0.0f) == 0) {
            return this.mPointF;
        }
        float div = (float) (((double) div(f4, 180.0f)) * 3.141592653589793d);
        if (Float.compare(div, 0.0f) == -1) {
            this.mPosY = 0.0f;
            this.mPosX = 0.0f;
        }
        if (Float.compare(f4, 90.0f) == -1) {
            this.mPosX = add(f, ((float) Math.cos((double) div)) * f3);
            this.mPosY = add(f2, ((float) Math.sin((double) div)) * f3);
        } else if (Float.compare(f4, 90.0f) == 0) {
            this.mPosX = f;
            this.mPosY = add(f2, f3);
        } else if (Float.compare(f4, 90.0f) == 1 && Float.compare(f4, 180.0f) == -1) {
            div = (float) ((((double) sub(180.0f, f4)) * 3.141592653589793d) / 180.0d);
            this.mPosX = sub(f, (float) (Math.cos((double) div) * ((double) f3)));
            this.mPosY = add(f2, (float) (Math.sin((double) div) * ((double) f3)));
        } else if (Float.compare(f4, 180.0f) == 0) {
            this.mPosX = f - f3;
            this.mPosY = f2;
        } else if (Float.compare(f4, 180.0f) == 1 && Float.compare(f4, 270.0f) == -1) {
            div = (float) ((((double) sub(f4, 180.0f)) * 3.141592653589793d) / 180.0d);
            this.mPosX = sub(f, (float) (Math.cos((double) div) * ((double) f3)));
            this.mPosY = sub(f2, (float) (Math.sin((double) div) * ((double) f3)));
        } else if (Float.compare(f4, 270.0f) == 0) {
            this.mPosX = f;
            this.mPosY = sub(f2, f3);
        } else {
            div = (float) ((((double) sub(360.0f, f4)) * 3.141592653589793d) / 180.0d);
            this.mPosX = add(f, (float) (Math.cos((double) div) * ((double) f3)));
            this.mPosY = sub(f2, (float) (Math.sin((double) div) * ((double) f3)));
        }
        this.mPointF.x = this.mPosX;
        this.mPointF.y = this.mPosY;
        return this.mPointF;
    }

    public PointF getArcEndPointF() {
        return this.mPointF;
    }

    public float getPosX() {
        return this.mPosX;
    }

    public float getPosY() {
        return this.mPosY;
    }

    public double getDegree(float f, float f2, float f3, float f4) {
        double atan;
        float f5 = f3 - f;
        float f6 = f4 - f2;
        if (Float.compare(f5, 0.0f) != 0) {
            atan = Math.atan((double) Math.abs(f6 / f5));
            if (Float.compare(f5, 0.0f) != 1) {
                atan = (Float.compare(f6, 0.0f) == 1 || Float.compare(f6, 0.0f) == 0) ? 3.141592653589793d - atan : atan + 3.141592653589793d;
            } else if (!(Float.compare(f6, 0.0f) == 1 || Float.compare(f6, 0.0f) == 0)) {
                atan = 6.283185307179586d - atan;
            }
        } else {
            atan = 1.5707963267948966d;
            if (Float.compare(f6, 0.0f) != 1) {
                atan = 1.5707963267948966d * -1.0d;
            }
        }
        return Math.toDegrees(atan);
    }

    public double getDistance(float f, float f2, float f3, float f4) {
        return Math.hypot((double) Math.abs(f3 - f), (double) Math.abs(f4 - f2));
    }

    public float getSliceAngle(float f, float f2) {
        if (f2 >= 101.0f || f2 < 0.0f) {
            return 0.0f;
        }
        try {
            return getInstance().round(getInstance().mul(f, getInstance().div(f2, 100.0f)), 2);
        } catch (Exception e) {
            return -1.0f;
        }
    }

    public float getLnPlotXValPosition(float f, float f2, double d, double d2, double d3) {
        return mul(f, (float) div(sub(d, d3), sub(d2, d3)));
    }

    public float getLnXValPosition(float f, float f2, double d, double d2, double d3) {
        return add(f2, getLnXValPosition(f, f2, d, d2, d3));
    }

    public void disableHighPrecision() {
        this.mHighPrecision = false;
    }

    public void enabledHighPrecision() {
        this.mHighPrecision = true;
    }

    public float add(float f, float f2) {
        if (this.mHighPrecision) {
            return new BigDecimal(Float.toString(f)).add(new BigDecimal(Float.toString(f2))).floatValue();
        }
        return f + f2;
    }

    public float sub(float f, float f2) {
        if (this.mHighPrecision) {
            return new BigDecimal(Float.toString(f)).subtract(new BigDecimal(Float.toString(f2))).floatValue();
        }
        return f - f2;
    }

    public float mul(float f, float f2) {
        if (this.mHighPrecision) {
            return new BigDecimal(Float.toString(f)).multiply(new BigDecimal(Float.toString(f2))).floatValue();
        }
        return f * f2;
    }

    public float div(float f, float f2) {
        return div(f, f2, 10);
    }

    public float div(float f, float f2, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        } else if (Float.compare(f2, 0.0f) == 0) {
            return 0.0f;
        } else {
            if (this.mHighPrecision) {
                return new BigDecimal(Float.toString(f)).divide(new BigDecimal(Float.toString(f2)), i, 4).floatValue();
            }
            return f / f2;
        }
    }

    public float round(float f, int i) {
        if (i >= 0) {
            return new BigDecimal(Float.toString(f)).divide(new BigDecimal(C0844a.f2048d), i, 4).floatValue();
        }
        throw new IllegalArgumentException("The scale must be a positive integer or zero");
    }

    public double add(double d, double d2) {
        if (this.mHighPrecision) {
            return new BigDecimal(Double.toString(d)).add(new BigDecimal(Double.toString(d2))).doubleValue();
        }
        return d + d2;
    }

    public double sub(double d, double d2) {
        if (this.mHighPrecision) {
            return new BigDecimal(Double.toString(d)).subtract(new BigDecimal(Double.toString(d2))).doubleValue();
        }
        return d - d2;
    }

    public double div(double d, double d2) {
        return div(d, d2, 10);
    }

    public double div(double d, double d2, int i) {
        if (i < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        } else if (Double.compare(d2, 0.0d) == 0) {
            return 0.0d;
        } else {
            if (this.mHighPrecision) {
                return new BigDecimal(Double.toString(d)).divide(new BigDecimal(Double.toString(d2)), i, 4).doubleValue();
            }
            return d / d2;
        }
    }

    public double mul(double d, double d2) {
        if (this.mHighPrecision) {
            return new BigDecimal(Double.toString(d)).multiply(new BigDecimal(Double.toString(d2))).doubleValue();
        }
        return d * d2;
    }

    public double round(double d, int i) {
        if (i >= 0) {
            return new BigDecimal(Double.toString(d)).divide(new BigDecimal(C0844a.f2048d), i, 4).doubleValue();
        }
        throw new IllegalArgumentException("The scale must be a positive integer or zero");
    }
}
