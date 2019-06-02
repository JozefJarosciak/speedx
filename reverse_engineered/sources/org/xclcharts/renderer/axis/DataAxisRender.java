package org.xclcharts.renderer.axis;

import android.graphics.Canvas;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.XEnum.ODD_EVEN;

public class DataAxisRender extends DataAxis {
    private int mCurrentId = 0;

    public float getAxisRange() {
        return MathHelper.getInstance().sub(getAxisMax(), getAxisMin());
    }

    public int getAixTickCount() {
        return (int) Math.ceil(((double) getAxisRange()) / getAxisSteps());
    }

    public void setAxisTickCurrentID(int i) {
        this.mCurrentId = i;
    }

    public boolean isPrimaryTick() {
        return isPrimaryTick(this.mCurrentId);
    }

    public boolean isPrimaryTick(int i) {
        if (!isDetailMode()) {
            return true;
        }
        if (i == 0 && this.mShowFirstTick) {
            return true;
        }
        if (((double) i) < getDetailModeSteps() || ((double) i) % getDetailModeSteps() != 0.0d) {
            return false;
        }
        return true;
    }

    public int getTickMarksLength() {
        int tickMarksLength = super.getTickMarksLength();
        return isPrimaryTick() ? tickMarksLength : tickMarksLength / 2;
    }

    public boolean isShowAxisLabels() {
        return !isPrimaryTick() ? false : super.isShowAxisLabels();
    }

    public void renderAxisHorizontalTick(float f, float f2, Canvas canvas, float f3, float f4, String str, boolean z) {
        renderHorizontalTick(f, f2, canvas, f3, f4, str, f3, f4, z);
    }

    public void renderAxisVerticalTick(Canvas canvas, float f, float f2, String str, boolean z, ODD_EVEN odd_even) {
        renderVerticalTick(canvas, f, f2, str, f, f2, z, odd_even);
    }

    public void renderAxis(Canvas canvas, float f, float f2, float f3, float f4) {
        if (isShow() && isShowAxisLine()) {
            drawAxisLine(canvas, f, f2, f3, f4);
        }
    }

    public void renderAxisLine(Canvas canvas, float f, float f2, float f3, float f4) {
        drawAxisLine(canvas, f, f2, f3, f4);
    }
}
