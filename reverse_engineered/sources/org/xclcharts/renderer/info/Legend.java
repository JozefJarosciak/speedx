package org.xclcharts.renderer.info;

import android.graphics.Paint;
import org.xclcharts.renderer.line.PlotDot;

public class Legend extends DyInfo {
    protected float mXPercentage = 0.0f;
    protected float mYPercentage = 0.0f;

    public void setPosition(float f, float f2) {
        this.mXPercentage = f;
        this.mYPercentage = f2;
    }

    public void addLegend(String str, Paint paint) {
        addInfo(str, paint);
    }

    public void addLegend(PlotDot plotDot, String str, Paint paint) {
        addInfo(plotDot, str, paint);
    }
}
