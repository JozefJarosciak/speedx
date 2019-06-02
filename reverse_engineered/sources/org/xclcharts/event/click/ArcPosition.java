package org.xclcharts.event.click;

import android.graphics.PointF;
import org.xclcharts.common.MathHelper;

public class ArcPosition extends PositionRecord {
    protected PointF mCirXY = null;
    protected float mCurrentAngle = 0.0f;
    protected float mInitAngle = 0.0f;
    protected float mOffsetAngle = 0.0f;
    protected float mRadius = 0.0f;
    protected float mSelectedOffset = 0.0f;

    public float getAngle() {
        return MathHelper.getInstance().add(this.mOffsetAngle, this.mCurrentAngle);
    }

    public void saveInitialAngle(float f) {
        this.mInitAngle = f;
    }

    public float getRadius() {
        return this.mRadius;
    }

    public PointF getPointF() {
        return this.mCirXY;
    }

    public float getStartAngle() {
        return MathHelper.getInstance().add(this.mOffsetAngle, this.mInitAngle);
    }

    public float getSweepAngle() {
        return this.mCurrentAngle;
    }

    public float getSelectedOffset() {
        return this.mSelectedOffset;
    }

    protected boolean compareRange(float f, float f2) {
        if (this.mCirXY == null) {
            return false;
        }
        return compareRadius(f, f2);
    }

    private boolean compareRadius(float f, float f2) {
        double distance = MathHelper.getInstance().getDistance(this.mCirXY.x, this.mCirXY.y, f, f2);
        if (Double.compare(distance, (double) this.mRadius) == 0 || Double.compare(distance, (double) this.mRadius) == -1) {
            float degree = (float) MathHelper.getInstance().getDegree(this.mCirXY.x, this.mCirXY.y, f, f2);
            float angle = getAngle();
            if (Float.compare(angle, degree) == 1 || Float.compare(angle, degree) == 0) {
                return true;
            }
        }
        return false;
    }
}
