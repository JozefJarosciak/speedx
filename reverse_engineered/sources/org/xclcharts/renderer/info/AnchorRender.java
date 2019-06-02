package org.xclcharts.renderer.info;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.renderer.XEnum.AnchorStyle;
import org.xclcharts.renderer.XEnum.DataAreaStyle;

public class AnchorRender {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$AnchorStyle;
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$DataAreaStyle;
    private static AnchorRender instance = null;
    private Paint mPaintBg = null;
    private Paint mPaintText = null;
    private RectF mRect = null;

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$AnchorStyle() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$AnchorStyle;
        if (iArr == null) {
            iArr = new int[AnchorStyle.values().length];
            try {
                iArr[AnchorStyle.CAPRECT.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AnchorStyle.CAPROUNDRECT.ordinal()] = 4;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[AnchorStyle.CIRCLE.ordinal()] = 5;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[AnchorStyle.HLINE.ordinal()] = 7;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[AnchorStyle.RECT.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[AnchorStyle.ROUNDRECT.ordinal()] = 3;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[AnchorStyle.TOBOTTOM.ordinal()] = 8;
            } catch (NoSuchFieldError e7) {
            }
            try {
                iArr[AnchorStyle.TOLEFT.ordinal()] = 10;
            } catch (NoSuchFieldError e8) {
            }
            try {
                iArr[AnchorStyle.TORIGHT.ordinal()] = 11;
            } catch (NoSuchFieldError e9) {
            }
            try {
                iArr[AnchorStyle.TOTOP.ordinal()] = 9;
            } catch (NoSuchFieldError e10) {
            }
            try {
                iArr[AnchorStyle.VLINE.ordinal()] = 6;
            } catch (NoSuchFieldError e11) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$AnchorStyle = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$DataAreaStyle() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$DataAreaStyle;
        if (iArr == null) {
            iArr = new int[DataAreaStyle.values().length];
            try {
                iArr[DataAreaStyle.FILL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[DataAreaStyle.STROKE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$DataAreaStyle = iArr;
        }
        return iArr;
    }

    public static synchronized AnchorRender getInstance() {
        AnchorRender anchorRender;
        synchronized (AnchorRender.class) {
            if (instance == null) {
                instance = new AnchorRender();
            }
            anchorRender = instance;
        }
        return anchorRender;
    }

    public void renderAnchor(Canvas canvas, AnchorDataPoint anchorDataPoint, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        if (anchorDataPoint != null) {
            float radius = anchorDataPoint.getRadius();
            switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$DataAreaStyle()[anchorDataPoint.getAreaStyle().ordinal()]) {
                case 1:
                    getBgPaint().setStyle(Style.FILL);
                    break;
                case 2:
                    getBgPaint().setStyle(Style.STROKE);
                    break;
            }
            getBgPaint().setColor(anchorDataPoint.getBgColor());
            float strokeWidth = getBgPaint().getStrokeWidth();
            if (anchorDataPoint.getLineWidth() > -1) {
                getBgPaint().setStrokeWidth((float) anchorDataPoint.getLineWidth());
            }
            switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$AnchorStyle()[anchorDataPoint.getAnchorStyle().ordinal()]) {
                case 2:
                case 3:
                case 4:
                    renderRoundRect(canvas, anchorDataPoint, f, f2, radius);
                    break;
                default:
                    switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$AnchorStyle()[anchorDataPoint.getAnchorStyle().ordinal()]) {
                        case 1:
                            renderRect(canvas, getBgPaint(), radius, f, f2);
                            break;
                        case 5:
                            canvas.drawCircle(f, f2, radius, getBgPaint());
                            break;
                        case 6:
                            DrawHelper.getInstance().drawLine(anchorDataPoint.getLineStyle(), f, f5, f, f7, canvas, getBgPaint());
                            break;
                        case 7:
                            DrawHelper.getInstance().drawLine(anchorDataPoint.getLineStyle(), f4, f2, f6, f2, canvas, getBgPaint());
                            break;
                        case 8:
                            DrawHelper.getInstance().drawLine(anchorDataPoint.getLineStyle(), f, f2 + f3, f, f7, canvas, getBgPaint());
                            break;
                        case 9:
                            DrawHelper.getInstance().drawLine(anchorDataPoint.getLineStyle(), f, f2 - f3, f, f5, canvas, getBgPaint());
                            break;
                        case 10:
                            DrawHelper.getInstance().drawLine(anchorDataPoint.getLineStyle(), f - f3, f2, f4, f2, canvas, getBgPaint());
                            break;
                        case 11:
                            DrawHelper.getInstance().drawLine(anchorDataPoint.getLineStyle(), f + f3, f2, f6, f2, canvas, getBgPaint());
                            break;
                    }
                    if (anchorDataPoint.getAnchor().trim() != "") {
                        getTextPaint().setColor(anchorDataPoint.getTextColor());
                        getTextPaint().setTextSize(anchorDataPoint.getTextSize());
                        canvas.drawText(anchorDataPoint.getAnchor(), f, f2, getTextPaint());
                        break;
                    }
                    break;
            }
            getBgPaint().setStrokeWidth(strokeWidth);
        }
    }

    private void renderRoundRect(Canvas canvas, AnchorDataPoint anchorDataPoint, float f, float f2, float f3) {
        float textWidth;
        float f4;
        float capRectW = anchorDataPoint.getCapRectW() / 2.0f;
        float capRectH = anchorDataPoint.getCapRectH();
        float capRectHeight = anchorDataPoint.getCapRectHeight();
        float f5 = capRectW + f3;
        if (Float.compare(f3, capRectW) == -1 || Float.compare(f3, capRectW) == 0) {
            f5 = 30.0f + capRectW;
        }
        String trim = anchorDataPoint.getAnchor().trim();
        if (anchorDataPoint.getAnchor() != "") {
            float paintFontHeight = DrawHelper.getInstance().getPaintFontHeight(getTextPaint()) + 30.0f;
            if (Float.compare(paintFontHeight, capRectHeight) != 1) {
                paintFontHeight = capRectHeight;
            }
            textWidth = DrawHelper.getInstance().getTextWidth(getTextPaint(), trim);
            if (Float.compare(textWidth, f5) == 1) {
                f4 = paintFontHeight;
            } else {
                textWidth = f5;
                f4 = paintFontHeight;
            }
        } else {
            textWidth = f5;
            f4 = capRectHeight;
        }
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$AnchorStyle()[anchorDataPoint.getAnchorStyle().ordinal()]) {
            case 2:
                renderCapRect(canvas, anchorDataPoint, f, f2, f3, capRectW, capRectH, f4, textWidth);
                break;
            case 3:
                renderRound(canvas, anchorDataPoint, f, f2, f3, capRectW, capRectH, f4, textWidth);
                break;
            case 4:
                renderCapRound(canvas, anchorDataPoint, f, f2, f3, capRectW, capRectH, f4, textWidth);
                break;
        }
        if (anchorDataPoint.getAnchor() != "") {
            getTextPaint().setColor(anchorDataPoint.getTextColor());
            getTextPaint().setTextSize(anchorDataPoint.getTextSize());
            canvas.drawText(trim, f, (f2 - capRectH) - (f4 / 3.0f), getTextPaint());
        }
        this.mPaintText = null;
    }

    private void renderCapRound(Canvas canvas, AnchorDataPoint anchorDataPoint, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        getBgPaint().setStyle(Style.FILL);
        renderRound(canvas, anchorDataPoint, f, f2, f3, f4, f5, f6, f7);
        renderCap(canvas, anchorDataPoint, f, f2, f3, f4, f5, f6, f7);
    }

    private void renderCapRect(Canvas canvas, AnchorDataPoint anchorDataPoint, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        Path path = new Path();
        path.moveTo(f, f2);
        path.lineTo(f - f4, f2 - f5);
        path.lineTo(f - f7, f2 - f5);
        path.lineTo(f - f7, (f2 - f5) - f6);
        path.lineTo(f + f7, (f2 - f5) - f6);
        path.lineTo(f + f7, f2 - f5);
        path.lineTo(f + f4, f2 - f5);
        path.lineTo(f, f2);
        path.close();
        canvas.drawPath(path, getBgPaint());
        path.reset();
    }

    private void renderRound(Canvas canvas, AnchorDataPoint anchorDataPoint, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        if (this.mRect == null) {
            this.mRect = new RectF();
        }
        this.mRect.left = f - f7;
        this.mRect.top = (f2 - f5) - f6;
        this.mRect.right = f + f7;
        this.mRect.bottom = f2 - f5;
        getBgPaint().setStyle(Style.FILL);
        canvas.drawRoundRect(this.mRect, anchorDataPoint.getRoundRadius(), anchorDataPoint.getRoundRadius(), getBgPaint());
        this.mRect.setEmpty();
    }

    private void renderCap(Canvas canvas, AnchorDataPoint anchorDataPoint, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        Path path = new Path();
        path.moveTo(f, f2);
        path.lineTo(f - f4, f2 - f5);
        path.lineTo(f + f4, f2 - f5);
        path.close();
        canvas.drawPath(path, getBgPaint());
        path.reset();
    }

    private void renderRect(Canvas canvas, Paint paint, float f, float f2, float f3) {
        if (this.mRect == null) {
            this.mRect = new RectF();
        }
        this.mRect.left = f2 - f;
        this.mRect.top = f3 - f;
        this.mRect.right = f2 + f;
        this.mRect.bottom = f3 + f;
        canvas.drawRect(this.mRect, getBgPaint());
        this.mRect.setEmpty();
    }

    private Paint getTextPaint() {
        if (this.mPaintText == null) {
            this.mPaintText = new Paint(1);
            this.mPaintText.setTextAlign(Align.CENTER);
        }
        return this.mPaintText;
    }

    private Paint getBgPaint() {
        if (this.mPaintBg == null) {
            this.mPaintBg = new Paint(1);
        }
        this.mPaintBg.setStrokeWidth(2.0f);
        return this.mPaintBg;
    }
}
