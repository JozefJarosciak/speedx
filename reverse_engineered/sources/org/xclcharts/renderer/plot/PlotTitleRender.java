package org.xclcharts.renderer.plot;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.renderer.XEnum.HorizontalAlign;
import org.xclcharts.renderer.XEnum.VerticalAlign;

public class PlotTitleRender extends PlotTitle {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$HorizontalAlign;
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$VerticalAlign;

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$HorizontalAlign() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$HorizontalAlign;
        if (iArr == null) {
            iArr = new int[HorizontalAlign.values().length];
            try {
                iArr[HorizontalAlign.CENTER.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[HorizontalAlign.LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[HorizontalAlign.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$HorizontalAlign = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$VerticalAlign() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$VerticalAlign;
        if (iArr == null) {
            iArr = new int[VerticalAlign.values().length];
            try {
                iArr[VerticalAlign.BOTTOM.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[VerticalAlign.MIDDLE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[VerticalAlign.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$VerticalAlign = iArr;
        }
        return iArr;
    }

    public void renderTitle(float f, float f2, float f3, float f4, float f5, Canvas canvas) {
        String title = getTitle();
        String subtitle = getSubtitle();
        float f6 = 0.0f;
        float f7 = 0.0f;
        float f8 = 0.0f;
        if (title.length() != 0 || subtitle.length() != 0) {
            float paintFontHeight;
            if (title.length() > 0) {
                f6 = DrawHelper.getInstance().getPaintFontHeight(getTitlePaint());
            }
            if (title.length() > 0) {
                paintFontHeight = DrawHelper.getInstance().getPaintFontHeight(getSubtitlePaint());
            } else {
                paintFontHeight = 0.0f;
            }
            float f9 = f6 + paintFontHeight;
            float abs = Math.abs(f5 - f3);
            switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$VerticalAlign()[getVerticalAlign().ordinal()]) {
                case 1:
                    f6 += f3;
                    break;
                case 2:
                    f6 = (float) Math.round(((abs / 2.0f) + f3) - (f9 / 2.0f));
                    break;
                case 3:
                    f6 = f5 - f6;
                    break;
                default:
                    f6 = 0.0f;
                    break;
            }
            switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$HorizontalAlign()[getTitleAlign().ordinal()]) {
                case 1:
                    f9 = f6 + paintFontHeight;
                    getTitlePaint().setTextAlign(Align.LEFT);
                    getSubtitlePaint().setTextAlign(Align.LEFT);
                    f8 = f6;
                    f7 = f;
                    break;
                case 2:
                    f7 = (float) Math.round((f4 / 2.0f) + f);
                    getTitlePaint().setTextAlign(Align.CENTER);
                    getSubtitlePaint().setTextAlign(Align.CENTER);
                    f8 = f6;
                    break;
                case 3:
                    getTitlePaint().setTextAlign(Align.RIGHT);
                    getSubtitlePaint().setTextAlign(Align.RIGHT);
                    f8 = f6;
                    f7 = f2;
                    break;
            }
            DrawHelper.getInstance().drawText(canvas, getTitlePaint(), title, f7, f8);
            Canvas canvas2 = canvas;
            DrawHelper.getInstance().drawText(canvas2, getSubtitlePaint(), subtitle, f7, f8 + paintFontHeight);
        }
    }
}
