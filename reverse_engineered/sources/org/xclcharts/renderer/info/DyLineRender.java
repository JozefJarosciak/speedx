package org.xclcharts.renderer.info;

import android.graphics.Canvas;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.renderer.XEnum.DyLineStyle;

public class DyLineRender extends DyLine {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$DyLineStyle;
    private float mBottom = 0.0f;
    private float mLeft = 0.0f;
    private float mRight = 0.0f;
    private float mTop = 0.0f;

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$DyLineStyle() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$DyLineStyle;
        if (iArr == null) {
            iArr = new int[DyLineStyle.values().length];
            try {
                iArr[DyLineStyle.BackwardDiagonal.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[DyLineStyle.Cross.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[DyLineStyle.Horizontal.ordinal()] = 4;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[DyLineStyle.Vertical.ordinal()] = 3;
            } catch (NoSuchFieldError e4) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$DyLineStyle = iArr;
        }
        return iArr;
    }

    private void drawCross(Canvas canvas) {
        drawVertical(canvas);
        drawHorizontal(canvas);
    }

    private void drawBackwardDiagonal(Canvas canvas) {
        DrawHelper.getInstance().drawLine(getLineDrawStyle(), this.mCenterXY.x, this.mCenterXY.y, this.mCenterXY.x, this.mBottom, canvas, getLinePaint());
        DrawHelper.getInstance().drawLine(getLineDrawStyle(), this.mLeft, this.mCenterXY.y, this.mCenterXY.x, this.mCenterXY.y, canvas, getLinePaint());
    }

    private void drawVertical(Canvas canvas) {
        DrawHelper.getInstance().drawLine(getLineDrawStyle(), this.mCenterXY.x, this.mTop, this.mCenterXY.x, this.mBottom, canvas, getLinePaint());
    }

    private void drawHorizontal(Canvas canvas) {
        DrawHelper.getInstance().drawLine(getLineDrawStyle(), this.mLeft, this.mCenterXY.y, this.mRight, this.mCenterXY.y, canvas, getLinePaint());
    }

    public void renderLine(Canvas canvas, float f, float f2, float f3, float f4) {
        if (this.mCenterXY != null && Float.compare(this.mCenterXY.x, f) != 0 && Float.compare(this.mCenterXY.x, f) != -1 && Float.compare(this.mCenterXY.x, f3) != 0 && Float.compare(this.mCenterXY.x, f3) != 1 && Float.compare(this.mCenterXY.y, f2) != 0 && Float.compare(this.mCenterXY.y, f2) != -1 && Float.compare(this.mCenterXY.y, f4) != 0 && Float.compare(this.mCenterXY.y, f4) != 1) {
            this.mLeft = f;
            this.mTop = f2;
            this.mRight = f3;
            this.mBottom = f4;
            switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$DyLineStyle()[getDyLineStyle().ordinal()]) {
                case 1:
                    drawCross(canvas);
                    return;
                case 2:
                    drawBackwardDiagonal(canvas);
                    return;
                case 3:
                    drawVertical(canvas);
                    return;
                case 4:
                    drawHorizontal(canvas);
                    return;
                default:
                    return;
            }
        }
    }
}
