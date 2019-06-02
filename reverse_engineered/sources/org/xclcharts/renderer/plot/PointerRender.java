package org.xclcharts.renderer.plot;

import android.graphics.Canvas;
import android.graphics.Path;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.XEnum.PointerStyle;

public class PointerRender extends Pointer {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$PointerStyle = null;
    private static final int FIX_ANGLE = 90;
    private float mEndX = 0.0f;
    private float mEndY = 0.0f;
    private float mParentRadius = 0.0f;
    private Path mPath = null;
    private float mPointerAngle = 0.0f;
    private float mPointerRadius = 0.0f;
    private float mPointerTailRadius = 0.0f;
    private float mStartAngle = 0.0f;
    private float mTailX = 0.0f;
    private float mTailY = 0.0f;
    private float mTotalAngle = 0.0f;

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$PointerStyle() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$PointerStyle;
        if (iArr == null) {
            iArr = new int[PointerStyle.values().length];
            try {
                iArr[PointerStyle.LINE.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[PointerStyle.TRIANGLE.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$PointerStyle = iArr;
        }
        return iArr;
    }

    public void setStartXY(float f, float f2) {
        this.mCenterX = f;
        this.mCenterY = f2;
    }

    public void setCurrentAngle(float f) {
        this.mPointerAngle = f;
    }

    public void setStartAngle(float f) {
        this.mStartAngle = f;
    }

    public void setTotalAngle(float f) {
        this.mTotalAngle = f;
    }

    public void setParentRadius(float f) {
        this.mParentRadius = f;
    }

    private void calcRadius() {
        if (Float.compare(this.mPointerRadiusPercentage, 0.0f) == 1) {
            this.mPointerRadius = MathHelper.getInstance().mul(this.mParentRadius, this.mPointerRadiusPercentage);
        }
        if (Float.compare(this.mPointerTailRadiusPercentage, 0.0f) == 1) {
            this.mPointerTailRadius = MathHelper.getInstance().mul(this.mParentRadius, this.mPointerTailRadiusPercentage);
        }
    }

    public void setPointEndXY(float f, float f2) {
        this.mEndX = f;
        this.mEndY = f2;
    }

    public float getCurrentPointerAngle() {
        this.mPointerAngle = MathHelper.getInstance().mul(this.mTotalAngle, this.mPercentage);
        return this.mPointerAngle;
    }

    private void calcEndXY() {
        MathHelper.getInstance().calcArcEndPointXY(this.mCenterX, this.mCenterY, this.mPointerRadius, MathHelper.getInstance().add(getCurrentPointerAngle(), this.mStartAngle));
        this.mEndX = MathHelper.getInstance().getPosX();
        this.mEndY = MathHelper.getInstance().getPosY();
        if (Float.compare(this.mPointerTailRadiusPercentage, 0.0f) == 1) {
            MathHelper.getInstance().calcArcEndPointXY(this.mCenterX, this.mCenterY, this.mPointerTailRadius, (this.mPointerAngle + this.mStartAngle) - 180.0f);
            this.mTailX = MathHelper.getInstance().getPosX();
            this.mTailY = MathHelper.getInstance().getPosY();
            return;
        }
        this.mTailX = this.mCenterX;
        this.mTailY = this.mCenterY;
    }

    public void renerLine(Canvas canvas) {
        canvas.drawLine(this.mCenterX, this.mCenterY, this.mEndX, this.mEndY, getPointerPaint());
    }

    public void renderTriangle(Canvas canvas) {
        float add = MathHelper.getInstance().add(this.mPointerAngle - 90.0f, this.mStartAngle);
        float add2 = MathHelper.getInstance().add(this.mPointerAngle + 90.0f, this.mStartAngle);
        MathHelper.getInstance().calcArcEndPointXY(this.mTailX, this.mTailY, this.mBaseRadius, add);
        add = MathHelper.getInstance().getPosX();
        float posY = MathHelper.getInstance().getPosY();
        MathHelper.getInstance().calcArcEndPointXY(this.mTailX, this.mTailY, this.mBaseRadius, add2);
        add2 = MathHelper.getInstance().getPosX();
        float posY2 = MathHelper.getInstance().getPosY();
        if (this.mPath == null) {
            this.mPath = new Path();
        } else {
            this.mPath.reset();
        }
        this.mPath.moveTo(this.mEndX, this.mEndY);
        this.mPath.lineTo(add, posY);
        this.mPath.lineTo(add2, posY2);
        this.mPath.close();
        canvas.drawPath(this.mPath, getPointerPaint());
    }

    public void renderCircle(Canvas canvas) {
        canvas.drawCircle(this.mCenterX, this.mCenterY, this.mBaseRadius, getBaseCirclePaint());
    }

    public void render(Canvas canvas) {
        calcRadius();
        calcEndXY();
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$PointerStyle()[getPointerStyle().ordinal()]) {
            case 1:
                renderTriangle(canvas);
                if (isShowBaseCircle()) {
                    renderCircle(canvas);
                    return;
                }
                return;
            case 2:
                renerLine(canvas);
                if (isShowBaseCircle()) {
                    renderCircle(canvas);
                    return;
                }
                return;
            default:
                return;
        }
    }
}
