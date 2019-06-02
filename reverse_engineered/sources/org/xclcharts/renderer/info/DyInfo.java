package org.xclcharts.renderer.info;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.v4.view.InputDeviceCompat;
import android.util.Log;
import java.util.ArrayList;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.renderer.XEnum.DotStyle;
import org.xclcharts.renderer.XEnum.DyInfoStyle;
import org.xclcharts.renderer.line.PlotDot;
import org.xclcharts.renderer.line.PlotDotRender;

public class DyInfo {
    private static /* synthetic */ int[] $SWITCH_TABLE$android$graphics$Paint$Align = null;
    private static final String TAG = "DyInfo";
    protected PointF mCenterXY = null;
    private ArrayList<PlotDot> mClickedDotStyle = null;
    private ArrayList<Paint> mClickedPaint = null;
    private ArrayList<String> mClickedText = null;
    private float mColSpan = 10.0f;
    private float mMargin = 5.0f;
    private Paint mPaintBackground = null;
    private Paint mPaintBorder = null;
    protected Align mPositionAlign = Align.RIGHT;
    protected float mRadius = 0.0f;
    private RectF mRect = new RectF();
    private float mRectHeight = 0.0f;
    private float mRectWidth = 0.0f;
    private float mRoundRectX = 5.0f;
    private float mRoundRectY = 5.0f;
    private float mRowSpan = 5.0f;
    protected float mScale = 0.2f;
    protected boolean mShowBackground = true;
    protected boolean mShowBoxBorder = true;
    private DyInfoStyle mStyle = DyInfoStyle.ROUNDRECT;

    static /* synthetic */ int[] $SWITCH_TABLE$android$graphics$Paint$Align() {
        int[] iArr = $SWITCH_TABLE$android$graphics$Paint$Align;
        if (iArr == null) {
            iArr = new int[Align.values().length];
            try {
                iArr[Align.CENTER.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[Align.LEFT.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[Align.RIGHT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$android$graphics$Paint$Align = iArr;
        }
        return iArr;
    }

    public Paint getBorderPaint() {
        if (this.mPaintBorder == null) {
            this.mPaintBorder = new Paint(1);
            this.mPaintBorder.setStyle(Style.STROKE);
        }
        return this.mPaintBorder;
    }

    public Paint getBackgroundPaint() {
        if (this.mPaintBackground == null) {
            this.mPaintBackground = new Paint(1);
            this.mPaintBackground.setAlpha(100);
            this.mPaintBackground.setColor(InputDeviceCompat.SOURCE_ANY);
        }
        return this.mPaintBackground;
    }

    private boolean validateParams() {
        if (this.mCenterXY == null) {
            Log.e(TAG, "没有传入点击坐标.");
            return false;
        } else if (this.mClickedPaint != null) {
            return true;
        } else {
            Log.e(TAG, "没有传入画笔.");
            return false;
        }
    }

    private void getContentRect() {
        int size;
        int size2;
        float f;
        float f2 = 0.0f;
        int size3 = this.mClickedDotStyle != null ? this.mClickedDotStyle.size() : 0;
        if (this.mClickedPaint != null) {
            size = this.mClickedPaint.size();
        } else {
            size = 0;
        }
        if (this.mClickedPaint != null) {
            size2 = this.mClickedPaint.size();
        } else {
            size2 = 0;
        }
        Paint paint = null;
        String str = "";
        int i = 0;
        float f3 = 0.0f;
        while (i < size2) {
            Paint paint2;
            if (size > i) {
                paint2 = (Paint) this.mClickedPaint.get(i);
            } else {
                paint2 = paint;
            }
            if (paint2 == null) {
                break;
            }
            String str2 = (String) this.mClickedText.get(i);
            float paintFontHeight = DrawHelper.getInstance().getPaintFontHeight(paint2);
            float textWidth = DrawHelper.getInstance().getTextWidth(paint2, str2);
            if (size3 <= i || ((PlotDot) this.mClickedDotStyle.get(i)).getDotStyle() == DotStyle.HIDE) {
                f = textWidth;
            } else {
                f = (this.mColSpan + paintFontHeight) + textWidth;
            }
            if (Float.compare(f, f2) != 1) {
                f = f2;
            }
            i++;
            f3 += paintFontHeight;
            f2 = f;
            paint = paint2;
        }
        f = ((this.mMargin * 2.0f) + (((float) size2) * this.mRowSpan)) + f3;
        this.mRectWidth = (this.mMargin * 2.0f) + f2;
        this.mRectHeight = f;
        getInfoRect();
    }

    public void setRowSpan(float f) {
        this.mRowSpan = f;
    }

    public void setColSpan(float f) {
        this.mColSpan = f;
    }

    public void setMargin(float f) {
        this.mMargin = f;
    }

    public void setRoundRectX(float f) {
        this.mRoundRectX = f;
    }

    public void setRoundRectY(float f) {
        this.mRoundRectY = f;
    }

    protected void setCenterXY(float f, float f2) {
        if (this.mCenterXY == null) {
            this.mCenterXY = new PointF();
        }
        this.mCenterXY.x = f;
        this.mCenterXY.y = f2;
    }

    public void setStyle(DyInfoStyle dyInfoStyle) {
        this.mStyle = dyInfoStyle;
    }

    protected void addInfo(String str, Paint paint) {
        PlotDot plotDot = new PlotDot();
        plotDot.setDotStyle(DotStyle.HIDE);
        addInfo(plotDot, str, paint);
    }

    protected void addInfo(PlotDot plotDot, String str, Paint paint) {
        if (this.mClickedDotStyle == null) {
            this.mClickedDotStyle = new ArrayList();
        }
        if (this.mClickedText == null) {
            this.mClickedText = new ArrayList();
        }
        if (this.mClickedPaint == null) {
            this.mClickedPaint = new ArrayList();
        }
        this.mClickedDotStyle.add(plotDot);
        this.mClickedText.add(str);
        this.mClickedPaint.add(paint);
    }

    protected void drawInfo(Canvas canvas) {
        if (validateParams()) {
            int size = this.mClickedDotStyle != null ? this.mClickedDotStyle.size() : 0;
            int size2 = this.mClickedPaint != null ? this.mClickedPaint.size() : 0;
            int size3 = this.mClickedPaint != null ? this.mClickedPaint.size() : 0;
            if (size3 != 0 || size != 0) {
                getContentRect();
                if (this.mRect != null) {
                    if (DyInfoStyle.RECT == this.mStyle) {
                        if (this.mShowBackground) {
                            canvas.drawRect(this.mRect, getBackgroundPaint());
                        }
                        if (this.mShowBoxBorder) {
                            canvas.drawRect(this.mRect, getBorderPaint());
                        }
                    } else if (DyInfoStyle.CAPRECT == this.mStyle) {
                        renderCapRect(canvas, this.mRect);
                    } else if (DyInfoStyle.CAPROUNDRECT == this.mStyle) {
                        renderCapRound(canvas, this.mRect);
                    } else if (DyInfoStyle.CIRCLE == this.mStyle) {
                        renderCircle(canvas, this.mRect);
                    } else {
                        if (this.mShowBackground) {
                            canvas.drawRoundRect(this.mRect, this.mRoundRectX, this.mRoundRectY, getBackgroundPaint());
                        }
                        if (this.mShowBoxBorder) {
                            canvas.drawRoundRect(this.mRect, this.mRoundRectX, this.mRoundRectY, getBorderPaint());
                        }
                    }
                    float f = this.mRect.left + this.mMargin;
                    int i = 0;
                    int i2 = 0;
                    float f2 = f;
                    float f3 = this.mMargin + this.mRect.top;
                    while (i2 < size3) {
                        int i3;
                        if (size2 > i2) {
                            i3 = i2;
                        } else {
                            i3 = i;
                        }
                        if (this.mClickedPaint.get(i3) != null) {
                            float paintFontHeight = DrawHelper.getInstance().getPaintFontHeight((Paint) this.mClickedPaint.get(i3));
                            if (size > i2) {
                                PlotDot plotDot = (PlotDot) this.mClickedDotStyle.get(i2);
                                if (plotDot.getDotStyle() != DotStyle.HIDE) {
                                    PlotDotRender.getInstance().renderDot(canvas, plotDot, f + (paintFontHeight / 2.0f), f3 + (paintFontHeight / 2.0f), (Paint) this.mClickedPaint.get(i3));
                                    f2 = (f + paintFontHeight) + this.mColSpan;
                                }
                            }
                            if (size3 > i2) {
                                DrawHelper.getInstance().drawText(canvas, (Paint) this.mClickedPaint.get(i3), (String) this.mClickedText.get(i2), f2, f3 + paintFontHeight);
                            }
                            i2++;
                            f2 = f;
                            f3 = (this.mRowSpan + paintFontHeight) + f3;
                            i = i3;
                        } else {
                            return;
                        }
                    }
                }
            }
        }
    }

    public void setCapBoxAngleHeight(float f) {
        this.mScale = f;
    }

    private void renderCapRect(Canvas canvas, RectF rectF) {
        if (this.mShowBackground || this.mShowBoxBorder) {
            float width = rectF.width() * this.mScale;
            rectF.top -= width;
            rectF.bottom -= width;
            float width2 = rectF.left + (rectF.width() * 0.5f);
            float f = rectF.bottom;
            Path path = new Path();
            path.moveTo(rectF.left, rectF.bottom);
            path.lineTo(rectF.left, rectF.top);
            path.lineTo(rectF.right, rectF.top);
            path.lineTo(rectF.right, rectF.bottom);
            path.lineTo(width2 + width, f);
            path.lineTo(width2, f + width);
            path.lineTo(width2 - width, f);
            path.close();
            if (this.mShowBackground) {
                canvas.drawPath(path, getBackgroundPaint());
            }
            if (this.mShowBoxBorder) {
                canvas.drawPath(path, getBorderPaint());
            }
        }
    }

    private void renderCapRound(Canvas canvas, RectF rectF) {
        if (this.mShowBackground) {
            float width = rectF.width() * this.mScale;
            rectF.top -= width;
            rectF.bottom -= width;
            float width2 = rectF.left + (rectF.width() * 0.5f);
            float f = rectF.bottom;
            float paintFontHeight = DrawHelper.getInstance().getPaintFontHeight(getBackgroundPaint());
            Path path = new Path();
            path.moveTo(width2 + width, f - paintFontHeight);
            path.lineTo(width2, f + width);
            path.lineTo(width2 - width, f - paintFontHeight);
            path.close();
            canvas.drawRoundRect(this.mRect, this.mRoundRectX, this.mRoundRectY, getBackgroundPaint());
            canvas.drawPath(path, getBackgroundPaint());
            path.reset();
        }
    }

    public void setCircleBoxRadius(float f) {
        this.mRadius = f;
    }

    private void renderCircle(Canvas canvas, RectF rectF) {
        float max = (Math.max(rectF.width(), rectF.height()) / 2.0f) + 5.0f;
        if (Float.compare(this.mRadius, 0.0f) != 0) {
            max = this.mRadius;
        }
        if (this.mShowBackground) {
            canvas.drawCircle(rectF.centerX(), rectF.centerY(), max, getBackgroundPaint());
        }
        if (this.mShowBoxBorder) {
            canvas.drawCircle(rectF.centerX(), rectF.centerY(), max, getBorderPaint());
        }
    }

    protected void clear() {
        if (this.mClickedDotStyle != null) {
            this.mClickedDotStyle.clear();
        }
        if (this.mClickedText != null) {
            this.mClickedText.clear();
        }
        if (this.mClickedPaint != null) {
            this.mClickedPaint.clear();
        }
    }

    private void getInfoRect() {
        switch ($SWITCH_TABLE$android$graphics$Paint$Align()[this.mPositionAlign.ordinal()]) {
            case 1:
                float f = this.mRectWidth / 2.0f;
                this.mRect.left = this.mCenterXY.x - f;
                this.mRect.right = f + this.mCenterXY.x;
                this.mRect.top = this.mCenterXY.y - this.mRectHeight;
                this.mRect.bottom = this.mCenterXY.y;
                return;
            case 2:
                this.mRect.left = this.mCenterXY.x - this.mRectWidth;
                this.mRect.right = this.mCenterXY.x;
                this.mRect.top = this.mCenterXY.y - this.mRectHeight;
                this.mRect.bottom = this.mCenterXY.y;
                return;
            case 3:
                this.mRect.left = this.mCenterXY.x;
                this.mRect.right = this.mCenterXY.x + this.mRectWidth;
                this.mRect.top = this.mCenterXY.y - this.mRectHeight;
                this.mRect.bottom = this.mCenterXY.y;
                return;
            default:
                return;
        }
    }

    public void hideBorder() {
        this.mShowBoxBorder = false;
    }

    public void hideBackground() {
        this.mShowBackground = false;
    }

    public void showBorder() {
        this.mShowBoxBorder = true;
    }

    public void showBackground() {
        this.mShowBackground = true;
    }
}
