package org.xclcharts.event.click;

import android.graphics.RectF;

public class PlotBarPosition extends BarPosition {
    public void savePlotDataID(int i) {
        saveDataID(i);
    }

    public void savePlotDataChildID(int i) {
        saveDataChildID(i);
    }

    public void savePlotRectF(float f, float f2, float f3, float f4) {
        saveRectF(f, f2, f3, f4);
    }

    public void savePlotRectF(RectF rectF) {
        saveRectF(rectF);
    }

    public boolean compareF(float f, float f2) {
        return compareRange(f, f2);
    }
}
