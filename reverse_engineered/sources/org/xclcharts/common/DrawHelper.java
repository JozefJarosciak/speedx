package org.xclcharts.common;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.FontMetrics;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.RectF;
import java.util.Random;
import org.xclcharts.renderer.XEnum.LineStyle;
import org.xclcharts.renderer.XEnum.TriangleDirection;
import org.xclcharts.renderer.XEnum.TriangleStyle;

public class DrawHelper {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$LineStyle;
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$TriangleDirection;
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$TriangleStyle;
    private static DrawHelper instance = null;
    private Paint mPaint = null;
    private Path mPath = null;
    private RectF mRectF = null;

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$LineStyle() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$LineStyle;
        if (iArr == null) {
            iArr = new int[LineStyle.values().length];
            try {
                iArr[LineStyle.DASH.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[LineStyle.DOT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[LineStyle.SOLID.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$LineStyle = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$TriangleDirection() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$TriangleDirection;
        if (iArr == null) {
            iArr = new int[TriangleDirection.values().length];
            try {
                iArr[TriangleDirection.DOWN.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[TriangleDirection.LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[TriangleDirection.RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[TriangleDirection.UP.ordinal()] = 1;
            } catch (NoSuchFieldError e4) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$TriangleDirection = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$TriangleStyle() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$TriangleStyle;
        if (iArr == null) {
            iArr = new int[TriangleStyle.values().length];
            try {
                iArr[TriangleStyle.FILL.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[TriangleStyle.OUTLINE.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$TriangleStyle = iArr;
        }
        return iArr;
    }

    public static synchronized DrawHelper getInstance() {
        DrawHelper drawHelper;
        synchronized (DrawHelper.class) {
            if (instance == null) {
                instance = new DrawHelper();
            }
            drawHelper = instance;
        }
        return drawHelper;
    }

    private void initRectF() {
        if (this.mRectF == null) {
            this.mRectF = new RectF();
        }
    }

    private void initPath() {
        if (this.mPath == null) {
            this.mPath = new Path();
        } else {
            this.mPath.reset();
        }
    }

    private void initPaint() {
        if (this.mPaint == null) {
            this.mPaint = new Paint();
        } else {
            this.mPaint.reset();
        }
    }

    public int randomColor() {
        Random random = new Random();
        return Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
    }

    public int getLightColor(int i, int i2) {
        initPaint();
        this.mPaint.setColor(i);
        this.mPaint.setAlpha(i2);
        return this.mPaint.getColor();
    }

    public int getDarkerColor(int i) {
        r0 = new float[3];
        Color.colorToHSV(i, r0);
        r0[1] = r0[1] + 0.1f;
        r0[2] = r0[2] - 0.1f;
        return Color.HSVToColor(r0);
    }

    public float getPaintFontHeight(Paint paint) {
        FontMetrics fontMetrics = paint.getFontMetrics();
        return (float) Math.ceil((double) (fontMetrics.descent - fontMetrics.ascent));
    }

    public float getTextWidth(Paint paint, String str) {
        if (str.length() == 0) {
            return 0.0f;
        }
        return paint.measureText(str, 0, str.length());
    }

    public float calcTextHeight(Paint paint, String str) {
        if (str.length() == 0) {
            return 0.0f;
        }
        return getPaintFontHeight(paint) * ((float) str.length());
    }

    public void drawRotateText(String str, float f, float f2, float f3, Canvas canvas, Paint paint) {
        if ("" != str && str.length() != 0) {
            if (f3 != 0.0f) {
                canvas.rotate(f3, f, f2);
                drawText(canvas, paint, str, f, f2);
                canvas.rotate(-1.0f * f3, f, f2);
                return;
            }
            drawText(canvas, paint, str, f, f2);
        }
    }

    public void drawTrigangle(float f, float f2, float f3, TriangleDirection triangleDirection, TriangleStyle triangleStyle, Canvas canvas, Paint paint) {
        int tan = (int) (((double) (f / 2.0f)) * Math.tan(1.0471975511965976d));
        initPath();
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$TriangleDirection()[triangleDirection.ordinal()]) {
            case 1:
                this.mPath.moveTo(f2 - (f / 2.0f), f3);
                this.mPath.lineTo((f / 2.0f) + f2, f3);
                this.mPath.lineTo(f2, f3 - ((float) tan));
                this.mPath.close();
                break;
            case 2:
                this.mPath.moveTo(f2 - (f / 2.0f), f3);
                this.mPath.lineTo((f / 2.0f) + f2, f3);
                this.mPath.lineTo(f2, ((float) tan) + f3);
                this.mPath.close();
                break;
            case 3:
                this.mPath.moveTo(f2, f3 - (f / 2.0f));
                this.mPath.lineTo(f2, (f / 2.0f) + f3);
                this.mPath.lineTo(f2 - ((float) tan), f3);
                this.mPath.close();
                break;
            case 4:
                this.mPath.moveTo(f2, f3 - (f / 2.0f));
                this.mPath.lineTo(f2, (f / 2.0f) + f3);
                this.mPath.lineTo(((float) tan) + f2, f3);
                this.mPath.close();
                break;
        }
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$TriangleStyle()[triangleStyle.ordinal()]) {
            case 1:
                paint.setStyle(Style.STROKE);
                break;
            case 2:
                paint.setStyle(Style.FILL);
                break;
        }
        canvas.drawPath(this.mPath, paint);
        this.mPath.reset();
    }

    public PathEffect getDotLineStyle() {
        return new DashPathEffect(new float[]{2.0f, 2.0f, 2.0f, 2.0f}, 1.0f);
    }

    public PathEffect getDashLineStyle() {
        return new DashPathEffect(new float[]{4.0f, 8.0f, 5.0f, 10.0f}, 1.0f);
    }

    public void drawDotLine(float f, float f2, float f3, float f4, Canvas canvas, Paint paint) {
        paint.setPathEffect(getDotLineStyle());
        canvas.drawLine(f, f2, f3, f4, paint);
        paint.setPathEffect(null);
    }

    public void drawDashLine(float f, float f2, float f3, float f4, Canvas canvas, Paint paint) {
        paint.setPathEffect(getDashLineStyle());
        canvas.drawLine(f, f2, f3, f4, paint);
        paint.setPathEffect(null);
    }

    public void drawLine(LineStyle lineStyle, float f, float f2, float f3, float f4, Canvas canvas, Paint paint) {
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$LineStyle()[lineStyle.ordinal()]) {
            case 1:
                canvas.drawLine(f, f2, f3, f4, paint);
                return;
            case 2:
                drawDotLine(f, f2, f3, f4, canvas, paint);
                return;
            case 3:
                drawDashLine(f, f2, f3, f4, canvas, paint);
                return;
            default:
                return;
        }
    }

    public void drawPercent(Canvas canvas, Paint paint, float f, float f2, float f3, float f4, float f5, boolean z) throws Exception {
        try {
            initRectF();
            this.mRectF.left = f - f3;
            this.mRectF.top = f2 - f3;
            this.mRectF.right = f + f3;
            this.mRectF.bottom = f2 + f3;
            canvas.drawArc(this.mRectF, f4, f5, z, paint);
            this.mRectF.setEmpty();
        } catch (Exception e) {
            throw e;
        }
    }

    public void drawPathArc(Canvas canvas, Paint paint, float f, float f2, float f3, float f4, float f5) throws Exception {
        try {
            initRectF();
            this.mRectF.left = f - f3;
            this.mRectF.top = f2 - f3;
            this.mRectF.right = f + f3;
            this.mRectF.bottom = f2 + f3;
            initPath();
            this.mPath.addArc(this.mRectF, f4, f5);
            canvas.drawPath(this.mPath, paint);
            this.mRectF.setEmpty();
            this.mPath.reset();
        } catch (Exception e) {
            throw e;
        }
    }

    public float drawText(Canvas canvas, Paint paint, String str, float f, float f2) {
        if (str.length() > 0) {
            if (str.indexOf("\n") > 0) {
                float paintFontHeight = getPaintFontHeight(paint);
                String[] split = str.split("\n");
                for (String drawText : split) {
                    canvas.drawText(drawText, f, f2, paint);
                    f2 += paintFontHeight;
                }
            } else {
                canvas.drawText(str, f, f2, paint);
            }
        }
        return f2;
    }
}
