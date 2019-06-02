package org.xclcharts.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.PointF;
import android.support.v4.view.ViewCompat;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.XEnum.ChartType;
import org.xclcharts.renderer.XEnum.SliceLabelStyle;
import org.xclcharts.renderer.plot.PlotAttrInfo;
import org.xclcharts.renderer.plot.PlotAttrInfoRender;

public class DountChart extends PieChart {
    private String mCenterText = "";
    private int mFillRadius = 0;
    private float mInnerRadius = 0.8f;
    private Paint mPaintCenterText = null;
    private Paint mPaintFill = null;
    private PlotAttrInfoRender plotAttrInfoRender = null;

    public DountChart() {
        initChart();
    }

    public ChartType getType() {
        return ChartType.DOUNT;
    }

    private void initChart() {
        int i = ViewCompat.MEASURED_STATE_MASK;
        if (this.plotArea != null) {
            i = this.plotArea.getBackgroundPaint().getColor();
        }
        if (this.mPaintFill == null) {
            this.mPaintFill = new Paint();
            this.mPaintFill.setColor(i);
            this.mPaintFill.setAntiAlias(true);
        }
        if (this.plotAttrInfoRender == null) {
            this.plotAttrInfoRender = new PlotAttrInfoRender();
        }
        setLabelStyle(SliceLabelStyle.OUTSIDE);
    }

    private void initCenterTextPaint() {
        if (this.mPaintCenterText == null) {
            this.mPaintCenterText = new Paint();
            this.mPaintCenterText.setAntiAlias(true);
            this.mPaintCenterText.setTextSize(28.0f);
            this.mPaintCenterText.setTextAlign(Align.CENTER);
        }
    }

    public PlotAttrInfo getPlotAttrInfo() {
        return this.plotAttrInfoRender;
    }

    public Paint getInnerPaint() {
        return this.mPaintFill;
    }

    public void setInnerRadius(float f) {
        this.mInnerRadius = f;
    }

    public float calcInnerRadius() {
        this.mFillRadius = (int) MathHelper.getInstance().round(mul(getRadius(), this.mInnerRadius), 2);
        return (float) this.mFillRadius;
    }

    public Paint getCenterTextPaint() {
        initCenterTextPaint();
        return this.mPaintCenterText;
    }

    public void setCenterText(String str) {
        this.mCenterText = str;
    }

    private void renderCenterText(Canvas canvas) {
        if (this.mCenterText.length() <= 0) {
            return;
        }
        if (this.mCenterText.indexOf("\n") > 0) {
            float centerY = this.plotArea.getCenterY();
            float paintFontHeight = DrawHelper.getInstance().getPaintFontHeight(getCenterTextPaint());
            String[] split = this.mCenterText.split("\n");
            for (String drawText : split) {
                canvas.drawText(drawText, this.plotArea.getCenterX(), centerY, getCenterTextPaint());
                centerY += paintFontHeight;
            }
            return;
        }
        canvas.drawText(this.mCenterText, this.plotArea.getCenterX(), this.plotArea.getCenterY(), getCenterTextPaint());
    }

    protected PointF renderLabelInside(Canvas canvas, String str, float f, float f2, float f3, float f4, float f5, boolean z) {
        if ("" == str) {
            return null;
        }
        PointF calcArcEndPointXY = MathHelper.getInstance().calcArcEndPointXY(f2, f3, ((float) this.mFillRadius) + ((f4 - ((float) this.mFillRadius)) / 2.0f), f5);
        if (z) {
            DrawHelper.getInstance().drawRotateText(str, calcArcEndPointXY.x, calcArcEndPointXY.y, f, canvas, getLabelPaint());
        }
        return new PointF(calcArcEndPointXY.x, calcArcEndPointXY.y);
    }

    protected void renderInnderCircle(Canvas canvas) {
        float centerX = this.plotArea.getCenterX();
        float centerY = this.plotArea.getCenterY();
        canvas.drawCircle(centerX, centerY, (float) this.mFillRadius, this.mPaintFill);
        if (this.mPaintArcBorder != null) {
            canvas.drawCircle(centerX, centerY, (float) this.mFillRadius, this.mPaintArcBorder);
        }
    }

    protected void renderDount(Canvas canvas) {
        renderInnderCircle(canvas);
        this.plotAttrInfoRender.renderAttrInfo(canvas, this.plotArea.getCenterX(), this.plotArea.getCenterY(), getRadius());
        renderCenterText(canvas);
    }

    protected boolean renderPlot(Canvas canvas) {
        calcInnerRadius();
        super.renderPlot(canvas);
        renderDount(canvas);
        return true;
    }
}
