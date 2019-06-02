package org.xclcharts.renderer.plot;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.renderer.XEnum.LabelBoxStyle;

public class PlotLabelRender extends PlotLabel {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$LabelBoxStyle;
    private int mBorderColor = -1;
    private RectF mRectBox = null;

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$LabelBoxStyle() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$LabelBoxStyle;
        if (iArr == null) {
            iArr = new int[LabelBoxStyle.values().length];
            try {
                iArr[LabelBoxStyle.CAPRECT.ordinal()] = 4;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[LabelBoxStyle.CAPROUNDRECT.ordinal()] = 6;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[LabelBoxStyle.CIRCLE.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[LabelBoxStyle.RECT.ordinal()] = 2;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[LabelBoxStyle.ROUNDRECT.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[LabelBoxStyle.TEXT.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$LabelBoxStyle = iArr;
        }
        return iArr;
    }

    public boolean drawLabel(Canvas canvas, Paint paint, String str, float f, float f2, float f3, int i) {
        this.mBorderColor = i;
        return drawLabel(canvas, paint, str, f, f2, f3);
    }

    public boolean drawLabel(Canvas canvas, Paint paint, String str, float f, float f2, float f3) {
        if ("" == str || str.length() == 0) {
            return false;
        }
        if (canvas == null || paint == null) {
            return false;
        }
        float labelWidth = getLabelWidth(paint, str);
        float labelHeight = getLabelHeight(paint);
        float f4 = f + this.mOffsetX;
        float f5 = f2 - this.mOffsetY;
        if (LabelBoxStyle.TEXT == this.mLabelBoxStyle) {
            DrawHelper.getInstance().drawRotateText(str, f4, f5 - this.mMargin, f3, canvas, paint);
            return true;
        } else if (LabelBoxStyle.CIRCLE == this.mLabelBoxStyle) {
            initBox();
            r0 = this.mRadius;
            if (Float.compare(this.mRadius, 0.0f) == 0 || Float.compare(this.mRadius, 0.0f) == -1) {
                try {
                    r0 = (Math.max(labelWidth, labelHeight) / 2.0f) + 5.0f;
                } catch (Exception e) {
                    r0 = 25.0f;
                }
            }
            labelHeight = (f5 - this.mMargin) - r0;
            canvas.drawCircle(f4, labelHeight, r0, this.mBorder.getBackgroundPaint());
            if (this.mShowBoxBorder) {
                canvas.drawCircle(f4, labelHeight, r0, this.mBorder.getLinePaint());
            }
            DrawHelper.getInstance().drawRotateText(str, f4, labelHeight, f3, canvas, paint);
            return true;
        } else {
            r0 = (f4 - (labelWidth / 2.0f)) - this.mMargin;
            labelWidth = ((labelWidth / 2.0f) + f4) + this.mMargin;
            labelHeight = (f5 - labelHeight) - this.mMargin;
            if (this.mRectBox == null) {
                this.mRectBox = new RectF();
            }
            this.mRectBox.left = r0;
            this.mRectBox.right = labelWidth;
            this.mRectBox.top = labelHeight;
            this.mRectBox.bottom = f5;
            if (LabelBoxStyle.RECT == this.mLabelBoxStyle) {
                drawBox(canvas);
                DrawHelper.getInstance().drawRotateText(str, f4, f5 - this.mMargin, f3, canvas, paint);
            } else {
                float width = this.mRectBox.width() * this.mScale;
                RectF rectF = this.mRectBox;
                rectF.top -= width;
                rectF = this.mRectBox;
                rectF.bottom -= width;
                initBox();
                if (this.mBorderColor != -1) {
                    this.mBorder.setBorderLineColor(this.mBorderColor);
                }
                switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$LabelBoxStyle()[this.mLabelBoxStyle.ordinal()]) {
                    case 4:
                        this.mBorder.renderCapRect(canvas, this.mRectBox, width, this.mShowBoxBorder, this.mShowBackground);
                        break;
                    case 5:
                        this.mBorder.renderRound(canvas, this.mRectBox, width, this.mShowBoxBorder, this.mShowBackground);
                        break;
                    case 6:
                        this.mBorder.renderCapRound(canvas, this.mRectBox, width, this.mShowBoxBorder, this.mShowBackground);
                        break;
                }
                DrawHelper.getInstance().drawRotateText(str, f4, (f5 - this.mMargin) - width, f3, canvas, paint);
            }
            this.mRectBox.setEmpty();
            return true;
        }
    }

    private float getLabelWidth(Paint paint, String str) {
        return DrawHelper.getInstance().getTextWidth(paint, str);
    }

    private float getLabelHeight(Paint paint) {
        return DrawHelper.getInstance().getPaintFontHeight(paint);
    }

    private void drawBox(Canvas canvas) {
        initBox();
        if (this.mBorderColor != -1) {
            this.mBorder.setBorderLineColor(this.mBorderColor);
        }
        this.mBorder.renderRect(canvas, this.mRectBox, this.mShowBoxBorder, this.mShowBackground);
    }
}
