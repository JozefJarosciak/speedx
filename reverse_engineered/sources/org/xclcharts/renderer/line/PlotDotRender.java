package org.xclcharts.renderer.line;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.XEnum.DotStyle;

public class PlotDotRender {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$DotStyle;
    private static PlotDotRender instance = null;
    protected Paint mPaintFill = null;
    private Path mPath = null;
    private RectF mRect = new RectF();

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$DotStyle() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$DotStyle;
        if (iArr == null) {
            iArr = new int[DotStyle.values().length];
            try {
                iArr[DotStyle.CROSS.ordinal()] = 9;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[DotStyle.DOT.ordinal()] = 4;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[DotStyle.HIDE.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[DotStyle.PRISMATIC.ordinal()] = 7;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[DotStyle.RECT.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[DotStyle.RING.ordinal()] = 5;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[DotStyle.RING2.ordinal()] = 6;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[DotStyle.TRIANGLE.ordinal()] = 2;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[DotStyle.X.ordinal()] = 8;
            } catch (NoSuchFieldError e9) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$DotStyle = iArr;
        }
        return iArr;
    }

    public static synchronized PlotDotRender getInstance() {
        PlotDotRender plotDotRender;
        synchronized (PlotDotRender.class) {
            if (instance == null) {
                instance = new PlotDotRender();
            }
            plotDotRender = instance;
        }
        return plotDotRender;
    }

    private void initPath() {
        if (this.mPath == null) {
            this.mPath = new Path();
        } else {
            this.mPath.reset();
        }
    }

    public Paint getInnerFillPaint() {
        if (this.mPaintFill == null) {
            this.mPaintFill = new Paint();
            this.mPaintFill.setColor(-1);
            this.mPaintFill.setStyle(Style.FILL);
            this.mPaintFill.setAntiAlias(true);
        }
        return this.mPaintFill;
    }

    public RectF renderDot(Canvas canvas, PlotDot plotDot, float f, float f2, Paint paint) {
        float dotRadius = plotDot.getDotRadius();
        if (Float.compare(dotRadius, 0.0f) == 0 || Float.compare(dotRadius, 0.0f) == -1) {
            return new RectF(0.0f, 0.0f, 0.0f, 0.0f);
        }
        float f3 = f - dotRadius;
        float f4 = f2 - dotRadius;
        float f5 = f + dotRadius;
        float f6 = f2 + dotRadius;
        if (DotStyle.DOT == plotDot.getDotStyle() || DotStyle.RING == plotDot.getDotStyle() || DotStyle.X == plotDot.getDotStyle()) {
            this.mRect.left = f3;
            this.mRect.top = f4;
            this.mRect.right = f5;
            this.mRect.bottom = f6;
        }
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$DotStyle()[plotDot.getDotStyle().ordinal()]) {
            case 2:
                renderTriangle(canvas, paint, dotRadius, f, f2);
                break;
            case 3:
                renderRect(canvas, paint, dotRadius, f, f2);
                break;
            case 4:
                canvas.drawCircle(f, f2, dotRadius, paint);
                break;
            case 5:
                renderRing(canvas, paint, dotRadius, plotDot, f, f2);
                break;
            case 6:
                renderRing2(canvas, paint, dotRadius, plotDot, f, f2);
                break;
            case 7:
                renderPrismatic(canvas, paint, dotRadius, f, f2);
                break;
            case 8:
                renderX(canvas, paint);
                break;
            case 9:
                renderCross(canvas, paint, dotRadius, f, f2);
                break;
        }
        return this.mRect;
    }

    private void renderRing(Canvas canvas, Paint paint, float f, PlotDot plotDot, float f2, float f3) {
        float f4 = 0.7f * f;
        canvas.drawCircle(f2, f3, f, paint);
        getInnerFillPaint().setColor(plotDot.getRingInnerColor());
        canvas.drawCircle(f2, f3, f4, getInnerFillPaint());
    }

    private void renderRing2(Canvas canvas, Paint paint, float f, PlotDot plotDot, float f2, float f3) {
        float f4 = 0.7f * f;
        canvas.drawCircle(f2, f3, f, paint);
        getInnerFillPaint().setColor(plotDot.getRingInnerColor());
        canvas.drawCircle(f2, f3, f4, getInnerFillPaint());
        f4 = 0.3f * f;
        getInnerFillPaint().setColor(plotDot.getRing2InnerColor());
        canvas.drawCircle(f2, f3, f4, getInnerFillPaint());
    }

    private void renderTriangle(Canvas canvas, Paint paint, float f, float f2, float f3) {
        float div = MathHelper.getInstance().div(f, 2.0f);
        float f4 = (f / 2.0f) + f;
        initPath();
        this.mPath.moveTo(f2 - f, f3 + div);
        this.mPath.lineTo(f2, f3 - f4);
        this.mPath.lineTo(f2 + f, f3 + div);
        this.mPath.close();
        canvas.drawPath(this.mPath, paint);
        this.mPath.reset();
        this.mRect.left = f2 - f;
        this.mRect.top = f3 - f4;
        this.mRect.right = f2 + f;
        this.mRect.bottom = div + f3;
    }

    private void renderPrismatic(Canvas canvas, Paint paint, float f, float f2, float f3) {
        initPath();
        float f4 = f2 - f;
        float f5 = f2 + f;
        float f6 = ((f5 - f4) / 2.0f) + f4;
        float f7 = f3 - f;
        float f8 = f3 + f;
        this.mPath.moveTo(f6, f7);
        this.mPath.lineTo(f4, f3);
        this.mPath.lineTo(f6, f8);
        this.mPath.lineTo(f5, f3);
        this.mPath.lineTo(f6, f7);
        this.mPath.close();
        canvas.drawPath(this.mPath, paint);
        this.mPath.reset();
        this.mRect.left = f4;
        this.mRect.top = f7;
        this.mRect.right = f5;
        this.mRect.bottom = f8;
    }

    private void renderRect(Canvas canvas, Paint paint, float f, float f2, float f3) {
        paint.setStyle(Style.FILL);
        this.mRect.left = f2 - f;
        this.mRect.top = f3 - f;
        this.mRect.right = f2 + f;
        this.mRect.bottom = f3 + f;
        canvas.drawRect(this.mRect, paint);
    }

    private void renderX(Canvas canvas, Paint paint) {
        canvas.drawLine(this.mRect.left, this.mRect.top, this.mRect.right, this.mRect.bottom, paint);
        canvas.drawLine(this.mRect.left, this.mRect.bottom, this.mRect.right, this.mRect.top, paint);
    }

    private void renderCross(Canvas canvas, Paint paint, float f, float f2, float f3) {
        canvas.drawLine(f2 - f, f3, f2 + f, f3, paint);
        canvas.drawLine(f2, f3 - f, f2, f3 + f, paint);
    }
}
