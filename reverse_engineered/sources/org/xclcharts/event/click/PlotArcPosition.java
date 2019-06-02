package org.xclcharts.event.click;

import android.graphics.PointF;

public class PlotArcPosition extends ArcPosition {
    public void saveAngle(float f, float f2, float f3, float f4) {
        this.mRadius = f;
        this.mOffsetAngle = f2;
        this.mCurrentAngle = f3;
        this.mSelectedOffset = f4;
    }

    public void savePlotDataID(int i) {
        saveDataID(i);
    }

    public void savePlotDataChildID(int i) {
        saveDataChildID(i);
    }

    public void savePlotCirXY(float f, float f2) {
        if (this.mCirXY == null) {
            this.mCirXY = new PointF();
        }
        this.mCirXY.x = f;
        this.mCirXY.y = f2;
    }

    public boolean compareF(float f, float f2) {
        return compareRange(f, f2);
    }
}
