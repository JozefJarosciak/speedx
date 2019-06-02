package org.xclcharts.renderer.axis;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.PointF;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.XEnum.Location;
import org.xclcharts.renderer.XEnum.RoundAxisType;
import org.xclcharts.renderer.XEnum.RoundTickAxisType;

public class RoundAxisRender extends RoundAxis {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$Location = null;
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$RoundAxisType = null;
    private static final String TAG = "RoundAxisRender";
    private Location mLocation = Location.BOTTOM;

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$Location() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$Location;
        if (iArr == null) {
            iArr = new int[Location.values().length];
            try {
                iArr[Location.BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Location.LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Location.RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[Location.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$Location = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$RoundAxisType() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$RoundAxisType;
        if (iArr == null) {
            iArr = new int[RoundAxisType.values().length];
            try {
                iArr[RoundAxisType.ARCLINEAXIS.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[RoundAxisType.CIRCLEAXIS.ordinal()] = 5;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[RoundAxisType.FILLAXIS.ordinal()] = 4;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[RoundAxisType.LINEAXIS.ordinal()] = 6;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[RoundAxisType.RINGAXIS.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[RoundAxisType.TICKAXIS.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$RoundAxisType = iArr;
        }
        return iArr;
    }

    public void setAxisPercentage(List<Float> list) {
        if (this.mPercentage != null) {
            this.mPercentage.clear();
        }
        if (this.mPercentage == null) {
            this.mPercentage = new ArrayList();
        }
        this.mPercentage = list;
    }

    public void setAxisColor(List<Integer> list) {
        if (this.mColor != null) {
            this.mColor.clear();
        }
        if (this.mColor == null) {
            this.mColor = new ArrayList();
        }
        this.mColor = list;
    }

    public void setAxisLabels(List<String> list) {
        if (this.mLabels != null) {
            this.mLabels.clear();
        }
        if (this.mLabels == null) {
            this.mLabels = new ArrayList();
        }
        this.mLabels = list;
    }

    public void setLineAxisLocation(Location location) {
        this.mLocation = location;
    }

    public boolean renderTicks(Canvas canvas, List<String> list) {
        float div;
        float f;
        float f2;
        float f3 = this.mCirX;
        float f4 = this.mCirY;
        int size = list.size();
        if (Float.compare(this.mTotalAngle, 360.0f) == 0) {
            div = MathHelper.getInstance().div(this.mTotalAngle, (float) size);
        } else {
            div = MathHelper.getInstance().div(this.mTotalAngle, (float) (size - 1));
        }
        float f5 = this.mRadius;
        float f6;
        if (RoundTickAxisType.INNER_TICKAXIS == this.mRoundTickAxisType) {
            f6 = this.mRadius * 0.95f;
            if (1 < this.mDetailModeSteps) {
                f = f6;
                f2 = f6 - (this.mRadius * 0.05f);
            }
            f = f6;
            f2 = f6;
        } else {
            f6 = this.mRadius + (this.mRadius * 0.05f);
            if (1 < this.mDetailModeSteps) {
                f = f6;
                f2 = this.mRadius + (this.mRadius * 0.08f);
            }
            f = f6;
            f2 = f6;
        }
        int i = this.mDetailModeSteps;
        float strokeWidth = getTickMarksPaint().getStrokeWidth();
        for (int i2 = 0; i2 < size; i2++) {
            float f7;
            float f8;
            float f9;
            if (i2 == 0) {
                f7 = this.mInitAngle;
            } else {
                f7 = MathHelper.getInstance().add(this.mInitAngle, ((float) i2) * div);
            }
            MathHelper.getInstance().calcArcEndPointXY(f3, f4, f5, f7);
            float posX = MathHelper.getInstance().getPosX();
            float posY = MathHelper.getInstance().getPosY();
            MathHelper.getInstance().calcArcEndPointXY(f3, f4, f2, f7);
            float posX2 = MathHelper.getInstance().getPosX();
            float posY2 = MathHelper.getInstance().getPosY();
            if (i == this.mDetailModeSteps) {
                f8 = posY2;
                f9 = posX2;
                i = 0;
            } else {
                MathHelper.getInstance().calcArcEndPointXY(f3, f4, f, f7);
                f9 = MathHelper.getInstance().getPosX();
                f8 = MathHelper.getInstance().getPosY();
                i++;
            }
            if (isShowTickMarks()) {
                if (i == 0 && this.mLongTickfakeBold) {
                    getTickMarksPaint().setStrokeWidth(1.0f + strokeWidth);
                } else if (this.mLongTickfakeBold) {
                    getTickMarksPaint().setStrokeWidth(strokeWidth);
                }
                canvas.drawLine(posX, posY, f9, f8, getTickMarksPaint());
            }
            if (isShowAxisLabels()) {
                String formatterLabel = getFormatterLabel((String) list.get(i2));
                PointF labelXY = getLabelXY(formatterLabel, posX2, posY2, f3, f4, this.mTotalAngle, f7);
                DrawHelper.getInstance().drawRotateText(formatterLabel, labelXY.x, labelXY.y, getTickLabelRotateAngle(), canvas, getTickLabelPaint());
            }
        }
        return true;
    }

    private PointF getLabelXY(String str, float f, float f2, float f3, float f4, float f5, float f6) {
        PointF pointF = new PointF(f, f2);
        float textWidth = DrawHelper.getInstance().getTextWidth(getTickLabelPaint(), str);
        float paintFontHeight = DrawHelper.getInstance().getPaintFontHeight(getTickLabelPaint());
        getTickLabelPaint().setTextAlign(Align.CENTER);
        if (RoundTickAxisType.INNER_TICKAXIS == this.mRoundTickAxisType) {
            if (Float.compare(pointF.y, f4) == 0) {
                if (Float.compare(pointF.x, f3) == -1) {
                    pointF.x = (textWidth / 2.0f) + pointF.x;
                } else {
                    pointF.x -= textWidth / 2.0f;
                }
            } else if (Float.compare(pointF.x, f3) == 0) {
                if (Float.compare(pointF.y, f4) == -1) {
                    pointF.y += paintFontHeight / 2.0f;
                } else {
                    pointF.y -= paintFontHeight / 2.0f;
                }
            } else if (Float.compare(f5, f6) == 0) {
                pointF.y += paintFontHeight;
            } else if (Float.compare(pointF.x, f3) == 1) {
                if (Float.compare(f5, 360.0f) == 0) {
                    getTickLabelPaint().setTextAlign(Align.RIGHT);
                } else {
                    pointF.x -= textWidth / 2.0f;
                }
            } else if (Float.compare(pointF.x, f3) == -1) {
                if (Float.compare(f5, 360.0f) == 0) {
                    getTickLabelPaint().setTextAlign(Align.LEFT);
                } else {
                    pointF.x = (textWidth / 2.0f) + pointF.x;
                }
            }
        } else if (Float.compare(pointF.y, f4) == 0) {
            if (Float.compare(pointF.x, f3) == -1) {
                pointF.x -= textWidth / 2.0f;
            } else {
                pointF.x = (textWidth / 2.0f) + pointF.x;
            }
        } else if (Float.compare(pointF.x, f3) == 0) {
            if (Float.compare(pointF.y, f4) == -1) {
                pointF.y -= paintFontHeight / 2.0f;
            } else {
                pointF.y += paintFontHeight / 2.0f;
            }
        } else if (Float.compare(f5, f6) == 0) {
            pointF.y -= paintFontHeight;
        } else if (Float.compare(pointF.x, f3) == 1) {
            if (Float.compare(f5, 360.0f) == 0) {
                getTickLabelPaint().setTextAlign(Align.LEFT);
            } else {
                pointF.x = (textWidth / 2.0f) + pointF.x;
            }
        } else if (Float.compare(pointF.x, f3) == -1) {
            if (Float.compare(f5, 360.0f) == 0) {
                getTickLabelPaint().setTextAlign(Align.RIGHT);
            } else {
                pointF.x -= textWidth / 2.0f;
            }
        }
        return pointF;
    }

    public boolean renderFillAxis(Canvas canvas) throws Exception {
        if (isShow() && isShowAxisLine()) {
            if (this.mColor != null) {
                getFillAxisPaint().setColor(((Integer) this.mColor.get(0)).intValue());
            }
            DrawHelper.getInstance().drawPercent(canvas, getFillAxisPaint(), this.mCirX, this.mCirY, this.mRadius, this.mInitAngle, this.mTotalAngle, true);
        }
        return true;
    }

    public boolean renderTickAxis(Canvas canvas) throws Exception {
        if (!isShow() || this.mLabels == null) {
            return false;
        }
        if (isShowAxisLine()) {
            DrawHelper.getInstance().drawPathArc(canvas, getAxisPaint(), this.mCirX, this.mCirY, this.mRadius, this.mInitAngle, this.mTotalAngle);
        }
        return renderTicks(canvas, this.mLabels);
    }

    public boolean renderArcLineAxis(Canvas canvas) throws Exception {
        if (isShow() && isShowAxisLine()) {
            DrawHelper.getInstance().drawPathArc(canvas, getAxisPaint(), this.mCirX, this.mCirY, this.mRadius, this.mInitAngle, this.mTotalAngle);
        }
        return true;
    }

    public boolean renderCircleAxis(Canvas canvas) throws Exception {
        if (isShow() && isShowAxisLine()) {
            if (this.mColor != null) {
                getAxisPaint().setColor(((Integer) this.mColor.get(0)).intValue());
            }
            canvas.drawCircle(this.mCirX, this.mCirY, this.mRadius, getAxisPaint());
        }
        return true;
    }

    public boolean renderRingAxis(Canvas canvas) throws Exception {
        if (!isShow() || !isShowAxisLine()) {
            return true;
        }
        if (this.mPercentage == null) {
            return false;
        }
        int size = this.mPercentage.size();
        if (this.mColor != null) {
            int size2 = this.mColor.size();
        } else {
            boolean z = false;
        }
        if (this.mLabels != null) {
            int size3 = this.mLabels.size();
        } else {
            boolean z2 = false;
        }
        float f = this.mInitAngle;
        String str = "";
        int i = 0;
        while (i < size) {
            int i2;
            if (this.mColor == null || r6 <= i) {
                i2 = -1;
            } else {
                i2 = ((Integer) this.mColor.get(i)).intValue();
            }
            if (this.mLabels != null && r7 > i) {
                str = (String) this.mLabels.get(i);
            }
            float mul = MathHelper.getInstance().mul(this.mTotalAngle, ((Float) this.mPercentage.get(i)).floatValue());
            renderPartitions(canvas, f, mul, i2, str);
            f = MathHelper.getInstance().add(f, mul);
            str = "";
            i++;
        }
        if (Float.compare(getRingInnerRadiusPercentage(), 0.0f) != 0 && Float.compare(getRingInnerRadiusPercentage(), 0.0f) == 1) {
            canvas.drawCircle(this.mCirX, this.mCirY, getRingInnerRadius(), getFillAxisPaint());
        }
        return true;
    }

    private boolean renderPartitions(Canvas canvas, float f, float f2, int i, String str) throws Exception {
        getAxisPaint().setColor(i);
        if (Float.compare(f2, 0.0f) < 0) {
            Log.e(TAG, "负角度???!!!");
            return false;
        } else if (Float.compare(f2, 0.0f) == 0) {
            Log.w(TAG, "零角度???!!!");
            return true;
        } else {
            DrawHelper.getInstance().drawPercent(canvas, getAxisPaint(), this.mCirX, this.mCirY, this.mRadius, f, f2, true);
            if (!isShowAxisLabels() || "" == str) {
                return true;
            }
            MathHelper.getInstance().calcArcEndPointXY(this.mCirX, this.mCirY, this.mRadius * 0.5f, MathHelper.getInstance().add(f, f2 / 2.0f));
            DrawHelper.getInstance().drawRotateText(getFormatterLabel(str), MathHelper.getInstance().getPosX(), MathHelper.getInstance().getPosY(), getTickLabelRotateAngle(), canvas, getTickLabelPaint());
            return true;
        }
    }

    public boolean renderLineAxis(Canvas canvas) throws Exception {
        if (!isShow() || !isShowAxisLine()) {
            return true;
        }
        Canvas canvas2;
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$Location()[this.mLocation.ordinal()]) {
            case 1:
                canvas.drawLine(this.mCirX, this.mCirY, this.mCirX, this.mCirY - this.mRadius, getAxisPaint());
                break;
            case 2:
                canvas2 = canvas;
                canvas2.drawLine(this.mCirX, this.mCirY, this.mCirX, this.mRadius + this.mCirY, getAxisPaint());
                break;
            case 3:
                canvas.drawLine(this.mCirX, this.mCirY, this.mCirX - this.mRadius, this.mCirY, getAxisPaint());
                break;
            case 4:
                canvas2 = canvas;
                canvas2.drawLine(this.mCirX, this.mCirY, this.mRadius + this.mCirX, this.mCirY, getAxisPaint());
                break;
            default:
                return false;
        }
        return true;
    }

    public void setCenterXY(float f, float f2) {
        this.mCirX = f;
        this.mCirY = f2;
    }

    public void setOrgRadius(float f) {
        this.mOrgRadius = f;
    }

    public void setAngleInfo(float f, float f2) {
        this.mTotalAngle = f;
        this.mInitAngle = f2;
    }

    public boolean render(Canvas canvas) throws Exception {
        this.mRadius = getOuterRadius();
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$RoundAxisType()[getAxisType().ordinal()]) {
            case 1:
                return renderTickAxis(canvas);
            case 2:
                return renderRingAxis(canvas);
            case 3:
                return renderArcLineAxis(canvas);
            case 4:
                return renderFillAxis(canvas);
            case 5:
                return renderCircleAxis(canvas);
            case 6:
                return renderLineAxis(canvas);
            default:
                return false;
        }
    }
}
