package org.xclcharts.renderer.plot;

import android.graphics.Canvas;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.renderer.XEnum.LineStyle;
import org.xclcharts.renderer.XEnum.RectType;

public class BorderRender extends Border {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$LineStyle;
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$RectType;
    private Path mPath = null;
    private RectF mRect = new RectF();

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

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$RectType() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$RectType;
        if (iArr == null) {
            iArr = new int[RectType.values().length];
            try {
                iArr[RectType.RECT.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[RectType.ROUNDRECT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$RectType = iArr;
        }
        return iArr;
    }

    public int getBorderSpadding() {
        return 5;
    }

    private void setPaintLineStyle() {
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$LineStyle()[getBorderLineStyle().ordinal()]) {
            case 2:
                getLinePaint().setPathEffect(DrawHelper.getInstance().getDotLineStyle());
                return;
            case 3:
                getLinePaint().setPathEffect(DrawHelper.getInstance().getDashLineStyle());
                return;
            default:
                return;
        }
    }

    public void renderRect(Canvas canvas, RectF rectF, boolean z, boolean z2) {
        setPaintLineStyle();
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$RectType()[getBorderRectType().ordinal()]) {
            case 1:
                if (z2) {
                    canvas.drawRect(rectF, getBackgroundPaint());
                }
                if (z) {
                    canvas.drawRect(rectF, getLinePaint());
                    return;
                }
                return;
            case 2:
                if (z2) {
                    canvas.drawRoundRect(rectF, (float) getRoundRadius(), (float) getRoundRadius(), getBackgroundPaint());
                }
                if (z) {
                    canvas.drawRoundRect(rectF, (float) getRoundRadius(), (float) getRoundRadius(), getLinePaint());
                    return;
                }
                return;
            default:
                return;
        }
    }

    public void renderCapRect(Canvas canvas, RectF rectF, float f, boolean z, boolean z2) {
        setPaintLineStyle();
        if (this.mPath == null) {
            this.mPath = new Path();
        }
        float width = rectF.left + (rectF.width() * 0.5f);
        float f2 = rectF.bottom;
        this.mPath.moveTo(rectF.left, rectF.bottom);
        this.mPath.lineTo(rectF.left, rectF.top);
        this.mPath.lineTo(rectF.right, rectF.top);
        this.mPath.lineTo(rectF.right, rectF.bottom);
        this.mPath.lineTo(width + f, f2);
        this.mPath.lineTo(width, f2 + f);
        this.mPath.lineTo(width - f, f2);
        this.mPath.close();
        if (z2) {
            canvas.drawPath(this.mPath, getBackgroundPaint());
        }
        if (z) {
            canvas.drawPath(this.mPath, getLinePaint());
        }
        this.mPath.reset();
    }

    public void renderCapRound(Canvas canvas, RectF rectF, float f, boolean z, boolean z2) {
        if (z2) {
            setPaintLineStyle();
            float width = rectF.left + (rectF.width() * 0.5f);
            float f2 = rectF.bottom;
            if (this.mRect == null) {
                this.mRect = new RectF();
            }
            this.mRect.left = rectF.left + 5.0f;
            this.mRect.top = rectF.top + 5.0f;
            this.mRect.right = rectF.right - 5.0f;
            this.mRect.bottom = rectF.bottom - 5.0f;
            getBackgroundPaint().setStyle(Style.FILL);
            canvas.drawRoundRect(this.mRect, (float) getRoundRadius(), (float) getRoundRadius(), getBackgroundPaint());
            if (this.mPath == null) {
                this.mPath = new Path();
            }
            float paintFontHeight = DrawHelper.getInstance().getPaintFontHeight(getBackgroundPaint());
            this.mPath.moveTo(width + f, f2 - paintFontHeight);
            this.mPath.lineTo(width, f2 + f);
            this.mPath.lineTo(width - f, f2 - paintFontHeight);
            this.mPath.close();
            canvas.drawPath(this.mPath, getBackgroundPaint());
            this.mPath.reset();
        }
    }

    public void renderRound(Canvas canvas, RectF rectF, float f, boolean z, boolean z2) {
        setPaintLineStyle();
        if (this.mRect == null) {
            this.mRect = new RectF();
        }
        this.mRect.left = rectF.left + 5.0f;
        this.mRect.top = rectF.top + 5.0f;
        this.mRect.right = rectF.right - 5.0f;
        this.mRect.bottom = rectF.bottom - 5.0f;
        if (z2) {
            canvas.drawRoundRect(this.mRect, (float) getRoundRadius(), (float) getRoundRadius(), getBackgroundPaint());
        }
        if (z) {
            canvas.drawRoundRect(this.mRect, (float) getRoundRadius(), (float) getRoundRadius(), getLinePaint());
        }
    }

    public void renderBorder(String str, Canvas canvas, float f, float f2, float f3, float f4) {
        this.mRect.left = f + 5.0f;
        this.mRect.top = f2 + 5.0f;
        this.mRect.right = f3 - 5.0f;
        this.mRect.bottom = f4 - 5.0f;
        setPaintLineStyle();
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$RectType()[getBorderRectType().ordinal()]) {
            case 1:
                if (!str.equals("CHART")) {
                    canvas.drawRect(this.mRect, getLinePaint());
                    return;
                } else if (this.mPaintBackground != null) {
                    canvas.drawRect(this.mRect, this.mPaintBackground);
                    return;
                } else {
                    return;
                }
            case 2:
                if (!str.equals("CHART")) {
                    canvas.drawRoundRect(this.mRect, (float) getRoundRadius(), (float) getRoundRadius(), getLinePaint());
                    return;
                } else if (this.mPaintBackground != null) {
                    canvas.drawRoundRect(this.mRect, (float) getRoundRadius(), (float) getRoundRadius(), this.mPaintBackground);
                    return;
                } else {
                    return;
                }
            default:
                return;
        }
    }
}
