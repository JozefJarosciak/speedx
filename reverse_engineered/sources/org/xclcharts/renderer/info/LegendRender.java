package org.xclcharts.renderer.info;

import android.graphics.Canvas;

public class LegendRender extends Legend {
    public void setPlotWH(float f, float f2) {
        setCenterXY(this.mXPercentage * f, this.mYPercentage * f2);
    }

    public void renderInfo(Canvas canvas) {
        drawInfo(canvas);
    }
}
