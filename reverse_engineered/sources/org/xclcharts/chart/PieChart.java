package org.xclcharts.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.PointF;
import android.graphics.RadialGradient;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.MathHelper;
import org.xclcharts.event.click.ArcPosition;
import org.xclcharts.renderer.CirChart;
import org.xclcharts.renderer.XEnum.ChartType;
import org.xclcharts.renderer.XEnum.LabelSaveType;
import org.xclcharts.renderer.info.PlotArcLabelInfo;

public class PieChart extends CirChart {
    private static final String TAG = "PieChart";
    private List<PieData> mDataset;
    private boolean mGradient = true;
    private LabelSaveType mLabelSaveType = LabelSaveType.ONLYPOSITION;
    protected ArrayList<PlotArcLabelInfo> mLstLabels = null;
    private Paint mPaintArc = null;
    protected Paint mPaintArcBorder = null;
    protected RectF mRectF = null;
    private boolean mSaveLabelsPosition = false;
    private float mSelectedOffset = 10.0f;
    private float mTotalAngle = 360.0f;

    public PieChart() {
        if (this.mLstLabels == null) {
            this.mLstLabels = new ArrayList();
        }
    }

    public ChartType getType() {
        return ChartType.PIE;
    }

    public Paint geArcPaint() {
        if (this.mPaintArc == null) {
            this.mPaintArc = new Paint();
            this.mPaintArc.setAntiAlias(true);
        }
        return this.mPaintArc;
    }

    public void setDataSource(List<PieData> list) {
        this.mDataset = list;
    }

    public List<PieData> getDataSource() {
        return this.mDataset;
    }

    public void setTotalAngle(float f) {
        this.mTotalAngle = f;
    }

    public float getTotalAngle() {
        return this.mTotalAngle;
    }

    public void saveLabelsPosition(LabelSaveType labelSaveType) {
        this.mLabelSaveType = labelSaveType;
        if (LabelSaveType.NONE == labelSaveType) {
            this.mSaveLabelsPosition = false;
        } else {
            this.mSaveLabelsPosition = true;
        }
    }

    public ArrayList<PlotArcLabelInfo> getLabelsPosition() {
        return this.mLstLabels;
    }

    public Paint getArcBorderPaint() {
        if (this.mPaintArcBorder == null) {
            this.mPaintArcBorder = new Paint(1);
            this.mPaintArcBorder.setStyle(Style.STROKE);
            this.mPaintArcBorder.setColor(-1);
        }
        return this.mPaintArcBorder;
    }

    public void showGradient() {
        this.mGradient = true;
    }

    public void hideGradient() {
        this.mGradient = false;
    }

    public boolean getGradient() {
        return this.mGradient;
    }

    public void setSelectedOffset(float f) {
        this.mSelectedOffset = f;
    }

    public float getSelectedOffset() {
        return this.mSelectedOffset;
    }

    private RadialGradient renderRadialGradient(Paint paint, float f, float f2, float f3) {
        float f4 = f3 * 0.8f;
        int color = paint.getColor();
        return new RadialGradient(f, f2, f4, DrawHelper.getInstance().getDarkerColor(color), color, TileMode.MIRROR);
    }

    protected boolean validateAngle(float f) {
        if (Float.compare(f, 0.0f) == 0 || Float.compare(f, 0.0f) == -1) {
            return false;
        }
        return true;
    }

    protected void renderArcBorder(Canvas canvas, RectF rectF, float f, float f2) {
        if (this.mPaintArcBorder != null) {
            canvas.drawArc(rectF, f, f2, true, this.mPaintArcBorder);
        }
    }

    protected void initRectF(float f, float f2, float f3, float f4) {
        if (this.mRectF == null) {
            this.mRectF = new RectF(f, f2, f3, f4);
        } else {
            this.mRectF.set(f, f2, f3, f4);
        }
    }

    protected boolean renderLabels(Canvas canvas) {
        if (this.mLstLabels == null) {
            return false;
        }
        boolean z;
        if (this.mSaveLabelsPosition && LabelSaveType.ONLYPOSITION == this.mLabelSaveType) {
            z = false;
        } else {
            z = true;
        }
        int size = this.mLstLabels.size();
        for (int i = 0; i < size; i++) {
            PlotArcLabelInfo plotArcLabelInfo = (PlotArcLabelInfo) this.mLstLabels.get(i);
            renderLabel(canvas, (PieData) this.mDataset.get(plotArcLabelInfo.getID()), plotArcLabelInfo, this.mSaveLabelsPosition, z);
        }
        if (!this.mSaveLabelsPosition) {
            this.mLstLabels.clear();
        }
        return true;
    }

    protected boolean renderPlot(Canvas canvas) {
        try {
            if (this.mDataset == null) {
                return false;
            }
            float centerX = this.plotArea.getCenterX();
            float centerY = this.plotArea.getCenterY();
            float radius = getRadius();
            if (Float.compare(radius, 0.0f) == 0 || Float.compare(radius, 0.0f) == -1) {
                return false;
            }
            float f = this.mOffsetAngle;
            this.mLstLabels.clear();
            float sub = sub(centerX, radius);
            float sub2 = sub(centerY, radius);
            float add = add(centerX, radius);
            float add2 = add(centerY, radius);
            int size = this.mDataset.size();
            for (int i = 0; i < size; i++) {
                PieData pieData = (PieData) this.mDataset.get(i);
                float sliceAngle = MathHelper.getInstance().getSliceAngle(getTotalAngle(), (float) pieData.getPercentage());
                if (validateAngle(sliceAngle)) {
                    geArcPaint().setColor(pieData.getSliceColor());
                    if (getGradient()) {
                        geArcPaint().setShader(renderRadialGradient(geArcPaint(), centerX, centerY, radius));
                    }
                    if (pieData.getSelected()) {
                        float div = div(radius, this.mSelectedOffset);
                        PointF calcArcEndPointXY = MathHelper.getInstance().calcArcEndPointXY(centerX, centerY, div, add(f, sliceAngle / 2.0f));
                        initRectF(sub(calcArcEndPointXY.x, radius), sub(calcArcEndPointXY.y, radius), add(calcArcEndPointXY.x, radius), add(calcArcEndPointXY.y, radius));
                        this.mLstLabels.add(new PlotArcLabelInfo(i, calcArcEndPointXY.x, calcArcEndPointXY.y, radius, f, sliceAngle));
                    } else {
                        initRectF(sub, sub2, add, add2);
                        this.mLstLabels.add(new PlotArcLabelInfo(i, centerX, centerY, radius, f, sliceAngle));
                    }
                    canvas.drawArc(this.mRectF, f, sliceAngle, true, geArcPaint());
                    renderArcBorder(canvas, this.mRectF, f, sliceAngle);
                    saveArcRecord(i, centerX + this.mTranslateXY[0], centerY + this.mTranslateXY[1], radius, f, sliceAngle, this.mSelectedOffset, getOffsetAngle());
                    f = add(f, sliceAngle);
                }
            }
            renderLabels(canvas);
            this.plotLegend.renderPieKey(canvas, this.mDataset);
            return true;
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return false;
        }
    }

    protected boolean validateParams() {
        if (this.mDataset == null) {
            return false;
        }
        float f = 0.0f;
        for (PieData pieData : this.mDataset) {
            float sliceAngle = MathHelper.getInstance().getSliceAngle(getTotalAngle(), (float) pieData.getPercentage());
            f = add(f, sliceAngle);
            if (Float.compare(f, 0.0f) == -1) {
                Log.w(TAG, "传入参数不合理，圆心角总计小于等于0度. 现有圆心角合计:" + Float.toString(f) + " 当前圆心角:" + Float.toString(sliceAngle) + " 当前百分比:" + Double.toString(pieData.getPercentage()));
            } else if (Float.compare(f, getTotalAngle() + 0.5f) == 1) {
                Log.w(TAG, "传入参数不合理，圆心角总计大于总角度. 现有圆心角合计:" + Float.toString(f));
            }
        }
        return true;
    }

    public ArcPosition getPositionRecord(float f, float f2) {
        return getArcRecord(f, f2);
    }

    protected boolean postRender(Canvas canvas) throws Exception {
        try {
            super.postRender(canvas);
            if (!validateParams()) {
                return false;
            }
            renderPlot(canvas);
            renderFocusShape(canvas);
            renderToolTip(canvas);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }
}
