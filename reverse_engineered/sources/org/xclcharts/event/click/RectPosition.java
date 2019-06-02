package org.xclcharts.event.click;

import android.graphics.RectF;

public class RectPosition extends PositionRecord {
    protected int mExtValue = 0;
    protected RectF mRectF = null;
    protected RectF mRectFRange = null;

    public void extPointClickRange(int i) {
        this.mExtValue = i;
    }

    protected void saveRectF(float f, float f2, float f3, float f4) {
        if (this.mRectF == null) {
            this.mRectF = new RectF();
        }
        this.mRectF.set(f, f2, f3, f4);
    }

    protected void saveRectF(RectF rectF) {
        this.mRectF = rectF;
    }

    public float getRadius() {
        return (this.mRectF.bottom - ((float) this.mExtValue)) - (this.mRectF.top + ((float) this.mExtValue));
    }

    public String getRectInfo() {
        if (this.mRectF == null) {
            return "";
        }
        return " left:" + Float.toString(this.mRectF.left + ((float) this.mExtValue)) + " top:" + Float.toString(this.mRectF.top + ((float) this.mExtValue)) + " right:" + Float.toString(this.mRectF.right - ((float) this.mExtValue)) + " bottom:" + Float.toString(this.mRectF.bottom - ((float) this.mExtValue));
    }

    public RectF getRectF() {
        return this.mRectF;
    }

    protected boolean compareRange(float f, float f2) {
        if (this.mRectF == null) {
            return false;
        }
        if (this.mRectFRange == null) {
            this.mRectFRange = new RectF();
        }
        this.mRectFRange.setEmpty();
        this.mRectFRange.set(this.mRectF);
        RectF rectF = this.mRectFRange;
        rectF.left -= (float) this.mExtValue;
        rectF = this.mRectFRange;
        rectF.top -= (float) this.mExtValue;
        rectF = this.mRectFRange;
        rectF.right += (float) this.mExtValue;
        rectF = this.mRectFRange;
        rectF.bottom += (float) this.mExtValue;
        if (this.mRectFRange.contains(f, f2)) {
            return true;
        }
        if (Float.compare(f, this.mRectFRange.left) != 1 && Float.compare(f, this.mRectFRange.left) != 0) {
            return false;
        }
        if (Float.compare(f, this.mRectFRange.right) != -1 && Float.compare(f, this.mRectFRange.right) != 0) {
            return false;
        }
        if (Float.compare(f2, this.mRectFRange.bottom) != 1 && Float.compare(f2, this.mRectFRange.bottom) != 0) {
            return false;
        }
        if (Float.compare(f2, this.mRectFRange.top) == -1 || Float.compare(f2, this.mRectFRange.top) == 0) {
            return true;
        }
        return false;
    }
}
