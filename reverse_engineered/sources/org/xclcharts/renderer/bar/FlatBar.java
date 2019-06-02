package org.xclcharts.renderer.bar;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.util.Log;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.renderer.XEnum.BarStyle;
import org.xclcharts.renderer.XEnum.Direction;

public class FlatBar extends Bar {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$BarStyle = null;
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$Direction = null;
    private static final String TAG = "FlatBar";
    private LinearGradient linearGradient = null;
    private int mFillAlpha = 255;
    private Path mPath = null;
    private final int radius = 5;

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$BarStyle() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$BarStyle;
        if (iArr == null) {
            iArr = new int[BarStyle.values().length];
            try {
                iArr[BarStyle.FILL.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[BarStyle.GRADIENT.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[BarStyle.OUTLINE.ordinal()] = 4;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[BarStyle.ROUNDBAR.ordinal()] = 6;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[BarStyle.STROKE.ordinal()] = 3;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[BarStyle.TRIANGLE.ordinal()] = 5;
            } catch (NoSuchFieldError e6) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$BarStyle = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$Direction() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$Direction;
        if (iArr == null) {
            iArr = new int[Direction.values().length];
            try {
                iArr[Direction.HORIZONTAL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Direction.VERTICAL.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$Direction = iArr;
        }
        return iArr;
    }

    public int getFillAlpha() {
        return this.mFillAlpha;
    }

    public void setFillAlpha(int i) {
        this.mFillAlpha = i;
    }

    public float[] getBarHeightAndMargin(float f, int i) {
        return calcBarHeightAndMargin(f, i);
    }

    public float[] getBarWidthAndMargin(float f, int i) {
        return calcBarWidthAndMargin(f, i);
    }

    private void setBarGradient(float f, float f2, float f3, float f4) {
        int lightColor = DrawHelper.getInstance().getLightColor(getBarPaint().getColor(), 150);
        float abs = Math.abs(f3 - f);
        float abs2 = Math.abs(f4 - f2);
        TileMode tileMode = TileMode.MIRROR;
        if (abs > abs2) {
            this.linearGradient = new LinearGradient(f3, f4, f3, f2, new int[]{lightColor, r1}, null, tileMode);
        } else {
            this.linearGradient = new LinearGradient(f, f4, f3, f4, new int[]{lightColor, r1}, null, tileMode);
        }
        getBarPaint().setShader(this.linearGradient);
    }

    public boolean renderBar(float f, float f2, float f3, float f4, Canvas canvas) {
        BarStyle barStyle = getBarStyle();
        if (Float.compare(f2, f4) == 0) {
            return true;
        }
        if (BarStyle.ROUNDBAR == barStyle) {
            canvas.drawRoundRect(new RectF(f, f4, f3, f2), getBarRoundRadius(), getBarRoundRadius(), getBarPaint());
            return true;
        }
        if (this.mPath == null) {
            this.mPath = new Path();
        }
        if (BarStyle.OUTLINE == barStyle) {
            getBarOutlinePaint().setColor(DrawHelper.getInstance().getLightColor(getBarPaint().getColor(), this.mOutlineAlpha));
            canvas.drawRect(f, f4, f3, f2, getBarOutlinePaint());
            getBarPaint().setStyle(Style.STROKE);
            getBarPaint().setStrokeWidth((float) this.mBorderWidth);
            drawPathBar(f, f2, f3, f4, canvas);
            getBarPaint().setStrokeWidth((float) this.mBorderWidth);
            return true;
        } else if (BarStyle.TRIANGLE == barStyle) {
            float f5;
            switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$Direction()[getBarDirection().ordinal()]) {
                case 1:
                    f5 = ((f4 - f2) / 2.0f) + f2;
                    this.mPath.moveTo(f, f2);
                    this.mPath.lineTo(f3, f5);
                    this.mPath.lineTo(f, f4);
                    this.mPath.close();
                    canvas.drawPath(this.mPath, getBarPaint());
                    canvas.drawCircle(f3, f5, 5.0f, getBarPaint());
                    break;
                default:
                    f5 = ((f3 - f) / 2.0f) + f;
                    this.mPath.moveTo(f, f4);
                    this.mPath.lineTo(f5, f2);
                    this.mPath.lineTo(f3, f4);
                    this.mPath.close();
                    canvas.drawPath(this.mPath, getBarPaint());
                    canvas.drawCircle(f5, f2, 5.0f, getBarPaint());
                    break;
            }
            this.mPath.reset();
            return true;
        } else {
            switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$BarStyle()[barStyle.ordinal()]) {
                case 1:
                    setBarGradient(f, f2, f3, f4);
                    break;
                case 2:
                    getBarPaint().setStyle(Style.FILL);
                    break;
                case 3:
                    if (Float.compare(1.0f, getBarPaint().getStrokeWidth()) == 0) {
                        getBarPaint().setStrokeWidth(3.0f);
                    }
                    getBarPaint().setStyle(Style.STROKE);
                    break;
                case 4:
                case 5:
                    break;
                default:
                    Log.e(TAG, "不认识的柱形风格参数.");
                    return false;
            }
            if (getBarStyle() != BarStyle.FILL) {
                setBarGradient(f, f2, f3, f4);
            }
            drawPathBar(f, f2, f3, f4, canvas);
            return true;
        }
    }

    public void renderBarItemLabel(String str, float f, float f2, Canvas canvas) {
        drawBarItemLabel(str, f, f2, canvas);
    }

    private void drawPathBar(float f, float f2, float f3, float f4, Canvas canvas) {
        this.mPath.moveTo(f, f4);
        this.mPath.lineTo(f, f2);
        this.mPath.lineTo(f3, f2);
        this.mPath.lineTo(f3, f4);
        this.mPath.close();
        canvas.drawPath(this.mPath, getBarPaint());
        this.mPath.reset();
    }
}
