package org.xclcharts.renderer;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import com.alibaba.fastjson.asm.Opcodes;
import org.xclcharts.common.IFormatterDoubleCallBack;
import org.xclcharts.event.click.PointPosition;
import org.xclcharts.renderer.XEnum.HorizontalAlign;
import org.xclcharts.renderer.XEnum.LegendType;
import org.xclcharts.renderer.XEnum.PanMode;
import org.xclcharts.renderer.XEnum.VerticalAlign;

public class RdChart extends EventChart {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$PanMode;
    private String TAG = "RdChart";
    private IFormatterDoubleCallBack mDotLabelFormatter;
    private int mOffsetAngle = 0;
    private Paint mPaintLabel = null;
    private Paint mPaintLine = null;
    private float mRadius = 0.0f;

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$PanMode() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$PanMode;
        if (iArr == null) {
            iArr = new int[PanMode.values().length];
            try {
                iArr[PanMode.FREE.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[PanMode.HORIZONTAL.ordinal()] = 1;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[PanMode.VERTICAL.ordinal()] = 2;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$PanMode = iArr;
        }
        return iArr;
    }

    public RdChart() {
        if (this.plotLegend != null) {
            this.plotLegend.show();
            this.plotLegend.setType(LegendType.ROW);
            this.plotLegend.setHorizontalAlign(HorizontalAlign.CENTER);
            this.plotLegend.setVerticalAlign(VerticalAlign.BOTTOM);
            this.plotLegend.showBox();
            this.plotLegend.hideBackground();
        }
    }

    protected void calcPlotRange() {
        super.calcPlotRange();
        this.mRadius = Math.min(div(this.plotArea.getWidth(), 2.0f), div(this.plotArea.getHeight(), 2.0f));
    }

    public PointPosition getPositionRecord(float f, float f2) {
        return getPointRecord(f, f2);
    }

    public float getRadius() {
        return this.mRadius;
    }

    public void setInitialAngle(int i) {
        if (i < 0 || i > 360) {
            Log.e(this.TAG, "起始偏移角度不能小于0或大于360");
        } else {
            this.mOffsetAngle = i;
        }
    }

    public int getInitialAngle() {
        return this.mOffsetAngle;
    }

    public void setDotLabelFormatter(IFormatterDoubleCallBack iFormatterDoubleCallBack) {
        this.mDotLabelFormatter = iFormatterDoubleCallBack;
    }

    protected String getFormatterDotLabel(double d) {
        String str = "";
        try {
            return this.mDotLabelFormatter.doubleFormatter(Double.valueOf(d));
        } catch (Exception e) {
            return Double.toString(d);
        }
    }

    public Paint getLabelPaint() {
        if (this.mPaintLabel == null) {
            this.mPaintLabel = new Paint();
            this.mPaintLabel.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mPaintLabel.setTextSize(18.0f);
            this.mPaintLabel.setAntiAlias(true);
            this.mPaintLabel.setTextAlign(Align.CENTER);
        }
        return this.mPaintLabel;
    }

    public Paint getLinePaint() {
        if (this.mPaintLine == null) {
            this.mPaintLine = new Paint(1);
            this.mPaintLine.setColor(Color.rgb(Opcodes.GETFIELD, 205, 230));
            this.mPaintLine.setStyle(Style.STROKE);
            this.mPaintLine.setStrokeWidth(3.0f);
        }
        return this.mPaintLine;
    }

    public boolean render(Canvas canvas) throws Exception {
        if (canvas == null) {
            return false;
        }
        try {
            if (getPanModeStatus()) {
                canvas.save();
                switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$PanMode()[getPlotPanMode().ordinal()]) {
                    case 1:
                        canvas.translate(this.mTranslateXY[0], 0.0f);
                        break;
                    case 2:
                        canvas.translate(0.0f, this.mTranslateXY[1]);
                        break;
                    default:
                        canvas.translate(this.mTranslateXY[0], this.mTranslateXY[1]);
                        break;
                }
                super.render(canvas);
                canvas.restore();
            } else {
                super.render(canvas);
            }
            return true;
        } catch (Exception e) {
            throw e;
        }
    }
}
