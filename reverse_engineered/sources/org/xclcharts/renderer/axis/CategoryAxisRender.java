package org.xclcharts.renderer.axis;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import java.util.List;
import org.xclcharts.renderer.XEnum.ODD_EVEN;

public class CategoryAxisRender extends CategoryAxis {
    public CategoryAxisRender() {
        getTickLabelPaint().setTextAlign(Align.CENTER);
    }

    public List<String> getDataSet() {
        return this.mDataSet;
    }

    public void renderAxisHorizontalTick(float f, float f2, Canvas canvas, float f3, float f4, String str, float f5, float f6, boolean z) {
        renderHorizontalTick(f, f2, canvas, f3, f4, str, f5, f6, z);
    }

    public void renderAxisVerticalTick(Canvas canvas, float f, float f2, String str, float f3, float f4, boolean z, ODD_EVEN odd_even) {
        renderVerticalTick(canvas, f, f2, str, f3, f4, z, odd_even);
    }

    public void renderAxis(Canvas canvas, float f, float f2, float f3, float f4) {
        if (isShow() && isShowAxisLine()) {
            drawAxisLine(canvas, f, f2, f3, f4);
        }
    }

    public void renderAxisLine(Canvas canvas, float f, float f2, float f3, float f4) {
        drawAxisLine(canvas, f, f2, f3, f4);
    }

    public void setDataBuilding(List<String> list) {
        this.mDataSet = list;
    }
}
