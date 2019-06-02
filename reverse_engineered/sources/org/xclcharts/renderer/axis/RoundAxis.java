package org.xclcharts.renderer.axis;

import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import java.util.List;
import org.xclcharts.common.IFormatterTextCallBack;
import org.xclcharts.renderer.XEnum.RoundAxisType;
import org.xclcharts.renderer.XEnum.RoundTickAxisType;

public class RoundAxis extends Axis {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$RoundAxisType;
    private RoundAxisType mAxisType = RoundAxisType.ARCLINEAXIS;
    protected float mCirX = 0.0f;
    protected float mCirY = 0.0f;
    protected List<Integer> mColor = null;
    protected int mDetailModeSteps = 1;
    protected float mInitAngle = 0.0f;
    private float mInnerRadiusPercentage = 0.9f;
    private IFormatterTextCallBack mLabelFormatter;
    protected List<String> mLabels = null;
    protected boolean mLongTickfakeBold = true;
    protected float mOrgRadius = 0.0f;
    private Paint mPaintFillAxis = null;
    protected List<Float> mPercentage = null;
    protected float mRadius = 0.0f;
    private float mRadiusPercentage = 1.0f;
    protected RoundTickAxisType mRoundTickAxisType = RoundTickAxisType.INNER_TICKAXIS;
    protected float mTotalAngle = 0.0f;

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$RoundAxisType() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$RoundAxisType;
        if (iArr == null) {
            iArr = new int[RoundAxisType.values().length];
            try {
                iArr[RoundAxisType.ARCLINEAXIS.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[RoundAxisType.CIRCLEAXIS.ordinal()] = 5;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[RoundAxisType.FILLAXIS.ordinal()] = 4;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[RoundAxisType.LINEAXIS.ordinal()] = 6;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[RoundAxisType.RINGAXIS.ordinal()] = 2;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[RoundAxisType.TICKAXIS.ordinal()] = 1;
            } catch (NoSuchFieldError e6) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$RoundAxisType = iArr;
        }
        return iArr;
    }

    public void setRoundAxisType(RoundAxisType roundAxisType) {
        this.mAxisType = roundAxisType;
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$RoundAxisType()[this.mAxisType.ordinal()]) {
            case 1:
                getTickLabelPaint().setTextAlign(Align.CENTER);
                showAxisLabels();
                showTickMarks();
                showAxisLine();
                getAxisPaint().setStyle(Style.STROKE);
                return;
            case 2:
                showAxisLabels();
                hideTickMarks();
                getAxisPaint().setStyle(Style.FILL);
                getAxisPaint().setColor(-16776961);
                initFillAxisPaint();
                return;
            case 3:
                hideAxisLabels();
                hideTickMarks();
                getAxisPaint().setStyle(Style.STROKE);
                break;
            case 5:
                break;
            default:
                return;
        }
        hideAxisLabels();
        hideTickMarks();
    }

    public void setDetailModeSteps(int i) {
        this.mDetailModeSteps = i;
    }

    public void setDetailModeSteps(int i, boolean z) {
        this.mDetailModeSteps = i;
        this.mLongTickfakeBold = z;
    }

    public float getRadius() {
        return this.mRadius;
    }

    public void setRoundTickAxisType(RoundTickAxisType roundTickAxisType) {
        this.mRoundTickAxisType = roundTickAxisType;
    }

    public void setRadiusPercentage(float f) {
        this.mRadiusPercentage = f;
    }

    public void setRingInnerRadiusPercentage(float f) {
        this.mInnerRadiusPercentage = f;
    }

    public Paint getFillAxisPaint() {
        initFillAxisPaint();
        return this.mPaintFillAxis;
    }

    private void initFillAxisPaint() {
        if (this.mPaintFillAxis == null) {
            this.mPaintFillAxis = new Paint();
            this.mPaintFillAxis.setStyle(Style.FILL);
            this.mPaintFillAxis.setColor(-1);
            this.mPaintFillAxis.setAntiAlias(true);
        }
    }

    public void setLabelFormatter(IFormatterTextCallBack iFormatterTextCallBack) {
        this.mLabelFormatter = iFormatterTextCallBack;
    }

    protected String getFormatterLabel(String str) {
        String str2 = "";
        try {
            str = this.mLabelFormatter.textFormatter(str);
        } catch (Exception e) {
        }
        return str;
    }

    public RoundAxisType getAxisType() {
        return this.mAxisType;
    }

    public float getOuterRadiusPercentage() {
        return this.mRadiusPercentage;
    }

    public float getRingInnerRadiusPercentage() {
        return this.mInnerRadiusPercentage;
    }

    public float getOuterRadius() {
        return this.mOrgRadius * this.mRadiusPercentage;
    }

    public float getRingInnerRadius() {
        return this.mOrgRadius * this.mInnerRadiusPercentage;
    }

    public PointF getCenterXY() {
        return new PointF(this.mCirX, this.mCirY);
    }
}
