package org.xclcharts.renderer.info;

import android.graphics.Paint;
import android.graphics.Paint.Align;
import org.xclcharts.renderer.XEnum.DyInfoStyle;
import org.xclcharts.renderer.line.PlotDot;

public class ToolTip extends DyInfo {
    public void setInfoStyle(DyInfoStyle dyInfoStyle) {
        setStyle(dyInfoStyle);
    }

    public void setRoundRadius(float f, float f2) {
        setStyle(DyInfoStyle.ROUNDRECT);
        setRoundRectX(f);
        setRoundRectY(f2);
    }

    public void setAlign(Align align) {
        this.mPositionAlign = align;
    }

    public void setCurrentXY(float f, float f2) {
        setCenterXY(f, f2);
    }

    public void addToolTip(String str, Paint paint) {
        addInfo(str, paint);
    }

    public void addToolTip(PlotDot plotDot, String str, Paint paint) {
        addInfo(plotDot, str, paint);
    }
}
