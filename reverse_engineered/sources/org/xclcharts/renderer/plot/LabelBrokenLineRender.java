package org.xclcharts.renderer.plot;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.XEnum.LabelLinePoint;

public class LabelBrokenLineRender extends LabelBrokenLine {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$LabelLinePoint;
    private Path mBzLine = null;

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$LabelLinePoint() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$LabelLinePoint;
        if (iArr == null) {
            iArr = new int[LabelLinePoint.values().length];
            try {
                iArr[LabelLinePoint.ALL.ordinal()] = 4;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[LabelLinePoint.BEGIN.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[LabelLinePoint.END.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[LabelLinePoint.NONE.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$LabelLinePoint = iArr;
        }
        return iArr;
    }

    public PointF renderLabelLine(String str, float f, float f2, float f3, float f4, float f5, Canvas canvas, Paint paint, boolean z, PlotLabelRender plotLabelRender) {
        float radius;
        float f6;
        float f7;
        if (getLinePointStyle() == LabelLinePoint.END || getLinePointStyle() == LabelLinePoint.ALL) {
            radius = getRadius();
        } else {
            radius = 0.0f;
        }
        MathHelper.getInstance().calcArcEndPointXY(f2, f3, MathHelper.getInstance().sub(f4, f4 / this.mBrokenStartPoint), f5);
        float posX = MathHelper.getInstance().getPosX();
        float posY = MathHelper.getInstance().getPosY();
        MathHelper.getInstance().calcArcEndPointXY(posX, posY, f4 / 2.0f, f5);
        float posX2 = MathHelper.getInstance().getPosX();
        float posY2 = MathHelper.getInstance().getPosY();
        float brokenLine = getBrokenLine();
        if (Float.compare(posX2, f2) == 0) {
            if (Float.compare(posY2, f3) == 1) {
                paint.setTextAlign(Align.LEFT);
                f6 = posX2 + brokenLine;
                f7 = f6 + radius;
            } else {
                paint.setTextAlign(Align.RIGHT);
                f6 = posX2 - brokenLine;
                f7 = f6 - radius;
            }
        } else if (Float.compare(posY2, f3) == 0) {
            if (Float.compare(posX2, f2) == 0 || Float.compare(posX2, f2) == -1) {
                paint.setTextAlign(Align.RIGHT);
                f7 = posX2 - radius;
                f6 = posX2;
            } else {
                paint.setTextAlign(Align.LEFT);
                f7 = posX2 + radius;
                f6 = posX2;
            }
        } else if (Float.compare(posX2 + brokenLine, f2) == 1) {
            paint.setTextAlign(Align.LEFT);
            f6 = posX2 + brokenLine;
            f7 = f6 + radius;
        } else if (Float.compare(posX2 - brokenLine, f2) == -1) {
            paint.setTextAlign(Align.RIGHT);
            f6 = posX2 - brokenLine;
            f7 = f6 - radius;
        } else {
            paint.setTextAlign(Align.CENTER);
            f7 = posX2;
            f6 = posX2;
        }
        if (this.mIsBZLine) {
            drawBZLine(posX, posY, posX2, posY2, f6, canvas);
        } else {
            drawBrokenLine(posX, posY, posX2, posY2, f6, canvas);
        }
        drawPoint(posX, posY, posX2, posY2, f6, radius, canvas);
        if (z) {
            if (plotLabelRender == null) {
                DrawHelper.getInstance().drawRotateText(str, f7, posY2, f, canvas, paint);
            } else {
                plotLabelRender.drawLabel(canvas, paint, str, f7, posY2, f);
            }
        }
        return new PointF(f7, posY2);
    }

    private void drawBrokenLine(float f, float f2, float f3, float f4, float f5, Canvas canvas) {
        canvas.drawLine(f, f2, f3, f4, getLabelLinePaint());
        canvas.drawLine(f3, f4, f5, f4, getLabelLinePaint());
    }

    private void drawBZLine(float f, float f2, float f3, float f4, float f5, Canvas canvas) {
        if (this.mBzLine == null) {
            this.mBzLine = new Path();
        }
        getLabelLinePaint().setStyle(Style.STROKE);
        this.mBzLine.reset();
        this.mBzLine.moveTo(f, f2);
        this.mBzLine.quadTo(f3, f4, f5, f4);
        canvas.drawPath(this.mBzLine, getLabelLinePaint());
    }

    private void drawPoint(float f, float f2, float f3, float f4, float f5, float f6, Canvas canvas) {
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$LabelLinePoint()[getLinePointStyle().ordinal()]) {
            case 2:
                canvas.drawCircle(f, f2, f6, getPointPaint());
                return;
            case 3:
                canvas.drawCircle(f5, f4, f6, getPointPaint());
                return;
            case 4:
                canvas.drawCircle(f, f2, f6, getPointPaint());
                canvas.drawCircle(f5, f4, f6, getPointPaint());
                return;
            default:
                return;
        }
    }
}
