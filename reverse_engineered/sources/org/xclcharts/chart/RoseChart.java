package org.xclcharts.chart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.XEnum.ChartType;

public class RoseChart extends PieChart {
    private static final String TAG = "PieChart";
    private int mBgLines = 0;
    private int mIntervalAngle = 0;
    private Map<Float, Integer> mListBgSeg = null;
    private Paint mPaintBg = null;
    private Paint mPaintInner = null;
    private boolean mShowBgCircle = false;
    private int mShowBgLineColor = ViewCompat.MEASURED_STATE_MASK;
    private boolean mShowBgLines = false;
    private boolean mShowInner = true;
    private boolean mShowOuterLabels = false;

    public RoseChart() {
        initChart();
    }

    public ChartType getType() {
        return ChartType.ROSE;
    }

    private void initChart() {
        if (getLabelPaint() != null) {
            getLabelPaint().setColor(-1);
            getLabelPaint().setTextSize(22.0f);
            getLabelPaint().setTextAlign(Align.CENTER);
        }
    }

    public Paint getInnerPaint() {
        if (this.mPaintInner == null) {
            this.mPaintInner = new Paint();
            this.mPaintInner.setColor(Color.rgb(68, 68, 68));
            this.mPaintInner.setStyle(Style.FILL);
            this.mPaintInner.setAntiAlias(true);
        }
        return this.mPaintInner;
    }

    public void setIntervalAngle(int i) {
        this.mIntervalAngle = i;
    }

    public void showInner() {
        this.mShowInner = true;
    }

    public void hideInner() {
        this.mShowInner = false;
    }

    public void showOuterLabels() {
        this.mShowOuterLabels = true;
    }

    public void hideOuterLabels() {
        this.mShowOuterLabels = false;
    }

    public Paint getBgPaint() {
        if (this.mPaintBg == null) {
            this.mPaintBg = new Paint(1);
            this.mPaintBg.setStyle(Style.STROKE);
            this.mPaintBg.setAntiAlias(true);
        }
        return this.mPaintBg;
    }

    public void showBgLines(int i) {
        this.mShowBgLines = true;
        this.mShowBgLineColor = i;
    }

    public void showBgCircle(Map<Float, Integer> map) {
        this.mShowBgCircle = true;
        this.mListBgSeg = map;
    }

    public void hideBgLines() {
        this.mShowBgLines = false;
    }

    public void hideBgCircle() {
        this.mShowBgCircle = true;
    }

    public void setBgLines(int i) {
        this.mBgLines = i;
    }

    protected boolean validateParams() {
        return true;
    }

    private void drawBGCircle(Canvas canvas) {
        if (this.mShowBgCircle && this.mListBgSeg != null) {
            float radius = getRadius();
            for (Entry entry : this.mListBgSeg.entrySet()) {
                float mul = mul(radius, ((Float) entry.getKey()).floatValue());
                if (!(Float.compare(mul, 0.0f) == 0 || Float.compare(mul, 0.0f) == -1)) {
                    getBgPaint().setColor(((Integer) entry.getValue()).intValue());
                    canvas.drawCircle(this.plotArea.getCenterX(), this.plotArea.getCenterY(), mul, getBgPaint());
                }
            }
        }
    }

    private void drawBGLines(Canvas canvas) {
        if (this.mShowBgLines && this.mBgLines != 0) {
            float f = (float) ((360 - (this.mIntervalAngle * this.mBgLines)) / this.mBgLines);
            float radius = getRadius();
            int i = 0;
            float f2 = this.mOffsetAngle;
            while (i < this.mBgLines) {
                PointF calcArcEndPointXY = MathHelper.getInstance().calcArcEndPointXY(this.plotArea.getCenterX(), this.plotArea.getCenterY(), radius, (((float) this.mIntervalAngle) + f2) + (f / 2.0f));
                getBgPaint().setColor(this.mShowBgLineColor);
                canvas.drawLine(this.plotArea.getCenterX(), this.plotArea.getCenterY(), calcArcEndPointXY.x, calcArcEndPointXY.y, getBgPaint());
                i++;
                f2 = add(add(f2, f), (float) this.mIntervalAngle);
            }
        }
    }

    private float getLabelRadius() {
        float radius = getRadius();
        if (this.mShowOuterLabels) {
            return radius + DrawHelper.getInstance().getPaintFontHeight(getLabelPaint());
        }
        return radius - ((radius / 2.0f) / 2.0f);
    }

    protected boolean renderPlot(Canvas canvas) {
        float centerX = this.plotArea.getCenterX();
        float centerY = this.plotArea.getCenterY();
        float radius = getRadius();
        List<PieData> dataSource = getDataSource();
        if (dataSource == null || dataSource.size() == 0) {
            Log.e(TAG, "数据源为空.");
            return false;
        }
        if (this.mShowInner) {
            canvas.drawCircle(centerX, centerY, radius, getInnerPaint());
        }
        drawBGCircle(canvas);
        drawBGLines(canvas);
        float div = div(mul((float) ((360 - (this.mIntervalAngle * dataSource.size())) / dataSource.size()), 100.0f), 100.0f);
        if (validateAngle(div)) {
            float labelRadius = getLabelRadius();
            for (PieData pieData : dataSource) {
                geArcPaint().setColor(pieData.getSliceColor());
                float div2 = div(mul(mul(radius, (float) (pieData.getPercentage() / 100.0d)), 100.0f), 100.0f);
                Canvas canvas2 = canvas;
                canvas2.drawArc(new RectF(sub(centerX, div2), sub(centerY, div2), add(centerX, div2), add(centerY, div2)), ((float) this.mIntervalAngle) + this.mOffsetAngle, div, true, geArcPaint());
                String label = pieData.getLabel();
                if ("" != label) {
                    PointF calcArcEndPointXY = MathHelper.getInstance().calcArcEndPointXY(centerX, centerY, labelRadius, (this.mOffsetAngle + ((float) this.mIntervalAngle)) + (div / 2.0f));
                    DrawHelper.getInstance().drawRotateText(label, calcArcEndPointXY.x, calcArcEndPointXY.y, pieData.getItemLabelRotateAngle(), canvas, getLabelPaint());
                }
                this.mOffsetAngle = add(add(this.mOffsetAngle, div), (float) this.mIntervalAngle);
            }
            return true;
        }
        Log.e(TAG, "计算出来的扇区角度小于等于0度,不能绘制.");
        return false;
    }
}
