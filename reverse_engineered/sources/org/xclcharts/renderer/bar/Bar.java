package org.xclcharts.renderer.bar;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import com.avos.avoscloud.AVException;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.XEnum.BarStyle;
import org.xclcharts.renderer.XEnum.Direction;
import org.xclcharts.renderer.XEnum.ItemLabelStyle;

public class Bar {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$Direction = null;
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$ItemLabelStyle = null;
    private static final String TAG = "Bar";
    private Direction mBarDirection = Direction.VERTICAL;
    private double mBarInnerMargin = 0.20000000298023224d;
    private float mBarMaxPxHeight = 0.0f;
    private float mBarMaxPxWidth = 0.0f;
    protected float mBarRoundRaidus = 15.0f;
    private BarStyle mBarStyle = BarStyle.GRADIENT;
    private float mBarTickSpacePercent = 0.7f;
    protected int mBorderWidth = 0;
    private int mItemLabelAnchorOffset = 5;
    private float mItemLabelRotateAngle = 0.0f;
    private ItemLabelStyle mItemLabelStyle = ItemLabelStyle.NORMAL;
    protected int mOutlineAlpha = 150;
    private Paint mPaintBar = null;
    private Paint mPaintItemLabel = null;
    private Paint mPaintOutlineBar = null;
    private boolean mShowItemLabel = false;

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

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$ItemLabelStyle() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$ItemLabelStyle;
        if (iArr == null) {
            iArr = new int[ItemLabelStyle.values().length];
            try {
                iArr[ItemLabelStyle.BOTTOM.ordinal()] = 4;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[ItemLabelStyle.INNER.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[ItemLabelStyle.NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[ItemLabelStyle.OUTER.ordinal()] = 3;
            } catch (NoSuchFieldError e4) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$ItemLabelStyle = iArr;
        }
        return iArr;
    }

    public void setBarRoundRadius(int i) {
        this.mBarRoundRaidus = (float) i;
    }

    public float getBarRoundRadius() {
        return this.mBarRoundRaidus;
    }

    public Direction getBarDirection() {
        return this.mBarDirection;
    }

    public void setBarDirection(Direction direction) {
        this.mBarDirection = direction;
    }

    public void setItemLabelStyle(ItemLabelStyle itemLabelStyle) {
        this.mItemLabelStyle = itemLabelStyle;
    }

    public ItemLabelStyle getItemLabelStyle() {
        return this.mItemLabelStyle;
    }

    public Paint getBarPaint() {
        if (this.mPaintBar == null) {
            this.mPaintBar = new Paint(1);
            this.mPaintBar.setColor(Color.rgb(AVException.UNSUPPORTED_SERVICE, AVException.USERNAME_PASSWORD_MISMATCH, 9));
            this.mPaintBar.setStyle(Style.FILL);
        }
        return this.mPaintBar;
    }

    public Paint getBarOutlinePaint() {
        if (this.mPaintOutlineBar == null) {
            this.mPaintOutlineBar = new Paint(1);
            this.mPaintOutlineBar.setStyle(Style.FILL);
        }
        return this.mPaintOutlineBar;
    }

    public void setOutlineAlpha(int i) {
        this.mOutlineAlpha = i;
    }

    public void setBorderWidth(int i) {
        this.mBorderWidth = i;
    }

    public Paint getItemLabelPaint() {
        if (this.mPaintItemLabel == null) {
            this.mPaintItemLabel = new Paint(1);
            this.mPaintItemLabel.setTextSize(12.0f);
            this.mPaintItemLabel.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mPaintItemLabel.setTextAlign(Align.CENTER);
        }
        return this.mPaintItemLabel;
    }

    public int getItemLabelAnchorOffset() {
        return this.mItemLabelAnchorOffset;
    }

    public void setItemLabelAnchorOffset(int i) {
        this.mItemLabelAnchorOffset = i;
    }

    public float getItemLabelRotateAngle() {
        return this.mItemLabelRotateAngle;
    }

    public void setItemLabelRotateAngle(float f) {
        this.mItemLabelRotateAngle = f;
    }

    public void setItemLabelVisible(boolean z) {
        this.mShowItemLabel = z;
    }

    public boolean setBarTickSpacePercent(float f) {
        if (Float.compare(f, 0.0f) == -1) {
            Log.e(TAG, "此比例不能为负数噢!");
            return false;
        } else if (Float.compare(f, 0.0f) == 0) {
            Log.e(TAG, "此比例不能等于0!");
            return false;
        } else {
            this.mBarTickSpacePercent = f;
            return true;
        }
    }

    public boolean setBarInnerMargin(float f) {
        if (Float.compare(f, 0.0f) == -1) {
            Log.e(TAG, "此比例不能为负数噢!");
            return false;
        } else if (Float.compare(f, 0.9f) == 1 || Float.compare(f, 0.9f) == 0) {
            Log.e(TAG, "此比例不能大于等于0.9,要给柱形留下点显示空间!");
            return false;
        } else {
            this.mBarInnerMargin = (double) f;
            return true;
        }
    }

    public double getInnerMargin() {
        return this.mBarInnerMargin;
    }

    public boolean getItemLabelsVisible() {
        return this.mShowItemLabel;
    }

    public void setBarMaxPxWidth(float f) {
        this.mBarMaxPxWidth = f;
    }

    public float getBarMaxPxWidth() {
        return this.mBarMaxPxWidth;
    }

    public void setBarMaxPxHeight(float f) {
        this.mBarMaxPxHeight = f;
    }

    public float getBarMaxPxHeight() {
        return this.mBarMaxPxHeight;
    }

    protected float[] calcBarHeightAndMargin(float f, int i) {
        if (i == 0) {
            Log.e(TAG, "柱形个数为零.");
            return null;
        }
        float mul = MathHelper.getInstance().mul(f, this.mBarTickSpacePercent);
        float mul2 = MathHelper.getInstance().mul(mul, (float) this.mBarInnerMargin);
        float div = MathHelper.getInstance().div(mul2, (float) i);
        mul = MathHelper.getInstance().div(MathHelper.getInstance().sub(mul, mul2), (float) i);
        float[] fArr = new float[2];
        if (Float.compare(this.mBarMaxPxHeight, 0.0f) == 1 && Float.compare(mul, this.mBarMaxPxHeight) == 1) {
            mul = this.mBarMaxPxHeight;
        }
        fArr[0] = mul;
        fArr[1] = div;
        return fArr;
    }

    protected float[] calcBarWidthAndMargin(float f, int i) {
        if (i == 0) {
            Log.e(TAG, "柱形个数为零.");
            return null;
        }
        float mul = MathHelper.getInstance().mul(f, this.mBarTickSpacePercent);
        float mul2 = MathHelper.getInstance().mul(mul, (float) this.mBarInnerMargin);
        mul = MathHelper.getInstance().sub(mul, mul2);
        float div = MathHelper.getInstance().div(mul2, (float) i);
        mul = MathHelper.getInstance().div(mul, (float) i);
        float[] fArr = new float[2];
        if (Float.compare(this.mBarMaxPxWidth, 0.0f) == 1 && Float.compare(mul, this.mBarMaxPxWidth) == 1) {
            mul = this.mBarMaxPxWidth;
        }
        fArr[0] = mul;
        fArr[1] = div;
        return fArr;
    }

    protected void drawBarItemLabel(String str, float f, float f2, Canvas canvas) {
        if (getItemLabelsVisible() && str.length() > 0) {
            float f3;
            float f4;
            float textWidth;
            switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$Direction()[this.mBarDirection.ordinal()]) {
                case 1:
                    textWidth = DrawHelper.getInstance().getTextWidth(getItemLabelPaint(), str);
                    switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$ItemLabelStyle()[this.mItemLabelStyle.ordinal()]) {
                        case 2:
                            f3 = f2;
                            f4 = (f - ((float) this.mItemLabelAnchorOffset)) - textWidth;
                            break;
                        case 3:
                            f3 = f2;
                            f4 = (((float) this.mItemLabelAnchorOffset) + f) + textWidth;
                            break;
                        default:
                            f3 = f2;
                            f4 = f + ((float) this.mItemLabelAnchorOffset);
                            break;
                    }
                case 2:
                    textWidth = DrawHelper.getInstance().getPaintFontHeight(getItemLabelPaint());
                    switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$ItemLabelStyle()[this.mItemLabelStyle.ordinal()]) {
                        case 2:
                            f3 = (((float) this.mItemLabelAnchorOffset) + f2) + textWidth;
                            f4 = f;
                            break;
                        case 3:
                            f3 = (f2 - ((float) this.mItemLabelAnchorOffset)) - textWidth;
                            f4 = f;
                            break;
                        default:
                            f3 = f2 - ((float) this.mItemLabelAnchorOffset);
                            f4 = f;
                            break;
                    }
                default:
                    f3 = f2;
                    f4 = f;
                    break;
            }
            DrawHelper.getInstance().drawRotateText(str, f4, f3, getItemLabelRotateAngle(), canvas, getItemLabelPaint());
        }
    }

    public void setBarStyle(BarStyle barStyle) {
        this.mBarStyle = barStyle;
    }

    public BarStyle getBarStyle() {
        return this.mBarStyle;
    }
}
