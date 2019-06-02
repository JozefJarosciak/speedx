package org.xclcharts.renderer.plot;

import android.graphics.Canvas;
import org.xclcharts.common.DrawHelper;

public class PlotGridRender extends PlotGrid {
    private final int BLOB_WIDTH = 2;
    private boolean mMajorTickLine = false;

    public void setPrimaryTickLine(boolean z) {
        this.mMajorTickLine = z;
    }

    public void renderOddRowsFill(Canvas canvas, float f, float f2, float f3, float f4) {
        if (canvas != null && isShowOddRowBgColor()) {
            canvas.drawRect(f, f4, f3, f2, getOddRowsBgColorPaint());
        }
    }

    public void renderEvenRowsFill(Canvas canvas, float f, float f2, float f3, float f4) {
        if (canvas != null && isShowEvenRowBgColor()) {
            canvas.drawRect(f, f4, f3, f2, getEvenRowsBgColorPaint());
        }
    }

    public void renderGridLinesHorizontal(Canvas canvas, float f, float f2, float f3, float f4) {
        if (canvas != null && isShowHorizontalLines()) {
            float f5 = 0.0f;
            if (this.mMajorTickLine) {
                f5 = getHorizontalLinePaint().getStrokeWidth();
                getHorizontalLinePaint().setStrokeWidth(2.0f + f5);
            }
            float f6 = f5;
            DrawHelper.getInstance().drawLine(getHorizontalLineStyle(), f, f2, f3, f4, canvas, getHorizontalLinePaint());
            if (this.mMajorTickLine) {
                getHorizontalLinePaint().setStrokeWidth(f6);
            }
        }
    }

    public void renderGridLinesVertical(Canvas canvas, float f, float f2, float f3, float f4) {
        if (canvas != null && isShowVerticalLines()) {
            float f5 = 0.0f;
            if (this.mMajorTickLine) {
                f5 = getVerticalLinePaint().getStrokeWidth();
                getVerticalLinePaint().setStrokeWidth(2.0f + f5);
            }
            float f6 = f5;
            DrawHelper.getInstance().drawLine(getVerticalLineStyle(), f, f2, f3, f4, canvas, getVerticalLinePaint());
            if (this.mMajorTickLine) {
                getVerticalLinePaint().setStrokeWidth(f6);
            }
        }
    }
}
