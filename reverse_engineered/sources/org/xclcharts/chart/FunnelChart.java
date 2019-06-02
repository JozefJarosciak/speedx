package org.xclcharts.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.Log;
import java.util.Collections;
import java.util.List;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.renderer.EventChart;
import org.xclcharts.renderer.XEnum.ChartType;
import org.xclcharts.renderer.XEnum.HorizontalAlign;
import org.xclcharts.renderer.XEnum.SortType;

public class FunnelChart extends EventChart {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$HorizontalAlign = null;
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$SortType = null;
    private static final String TAG = "FunnelChart";
    private List<FunnelData> mDataSet;
    private boolean mFunnelLineVisible = true;
    private boolean mIsLabelLineSyncColor = false;
    private boolean mIsLabelSyncColor = false;
    private boolean mIsShowLabelLine = false;
    private HorizontalAlign mLabelAlign = HorizontalAlign.CENTER;
    private boolean mLabelVisible = true;
    private Paint mPaint = null;
    private Paint mPaintFunnelLine = null;
    private Paint mPaintLabel = null;
    private Paint mPaintLabelLine = null;
    private float mPlotWidthPercent = 100.0f;
    private SortType mSortType = SortType.DESC;

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

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$SortType() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$SortType;
        if (iArr == null) {
            iArr = new int[SortType.values().length];
            try {
                iArr[SortType.ASC.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[SortType.DESC.ordinal()] = 3;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[SortType.NORMAL.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$SortType = iArr;
        }
        return iArr;
    }

    public ChartType getType() {
        return ChartType.FUNNEL;
    }

    public Paint getPaint() {
        if (this.mPaint == null) {
            this.mPaint = new Paint(1);
        }
        return this.mPaint;
    }

    public Paint getFunnelLinePaint() {
        if (this.mPaintFunnelLine == null) {
            this.mPaintFunnelLine = new Paint(1);
        }
        this.mPaintFunnelLine.setStrokeWidth(5.0f);
        return this.mPaintFunnelLine;
    }

    public Paint getLabelPaint() {
        if (this.mPaintLabel == null) {
            this.mPaintLabel = new Paint(1);
        }
        return this.mPaintLabel;
    }

    public Paint getLabelLinePaint() {
        if (this.mPaintLabelLine == null) {
            this.mPaintLabelLine = new Paint(1);
        }
        return this.mPaintLabelLine;
    }

    public void setPlotWidthPercent(float f) {
        this.mPlotWidthPercent = f;
    }

    public void setSortType(SortType sortType) {
        this.mSortType = sortType;
    }

    public void hideLabelLine() {
        this.mIsShowLabelLine = false;
    }

    public void showLabelLine() {
        this.mIsShowLabelLine = true;
    }

    public boolean isShowLabelLine() {
        return this.mIsShowLabelLine;
    }

    public void syncLabelLineColor() {
        this.mIsLabelLineSyncColor = true;
    }

    public void syncLabelColor() {
        this.mIsLabelSyncColor = true;
    }

    public void setFunnelLineVisible(boolean z) {
        this.mFunnelLineVisible = z;
    }

    public boolean getFunnelLineVisible() {
        return this.mFunnelLineVisible;
    }

    public void setLabelVisible(boolean z) {
        this.mLabelVisible = z;
    }

    public boolean getLabelVisible() {
        return this.mLabelVisible;
    }

    public void setLabelAlign(HorizontalAlign horizontalAlign) {
        this.mLabelAlign = horizontalAlign;
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$HorizontalAlign()[this.mLabelAlign.ordinal()]) {
            case 1:
                getLabelPaint().setTextAlign(Align.LEFT);
                showLabelLine();
                return;
            case 2:
                getLabelPaint().setTextAlign(Align.CENTER);
                return;
            case 3:
                getLabelPaint().setTextAlign(Align.RIGHT);
                showLabelLine();
                return;
            default:
                getLabelPaint().setTextAlign(Align.CENTER);
                return;
        }
    }

    public HorizontalAlign getLabelAlign() {
        return this.mLabelAlign;
    }

    public List<FunnelData> getDataSource() {
        return this.mDataSet;
    }

    public void setDataSource(List<FunnelData> list) {
        this.mDataSet = list;
    }

    private boolean sortDataSet() {
        if (this.mDataSet == null) {
            Log.e(TAG, "数据源为空!");
            return false;
        }
        for (int size = this.mDataSet.size() - 1; size >= 0; size--) {
            FunnelData funnelData = (FunnelData) this.mDataSet.get(size);
            if (Float.compare(funnelData.getData(), 0.0f) == -1 || Float.compare(funnelData.getData(), 0.0f) == 0) {
                this.mDataSet.remove(size);
            }
        }
        if (this.mDataSet.size() == 0) {
            return false;
        }
        if (SortType.NORMAL != this.mSortType) {
            Collections.sort(this.mDataSet);
        }
        return true;
    }

    private void drawTriangle(Canvas canvas, float f, PointF pointF, PointF pointF2) {
        Path path = new Path();
        path.moveTo(pointF.x, pointF.y);
        path.lineTo(pointF2.x, pointF2.y);
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$SortType()[this.mSortType.ordinal()]) {
            case 3:
                path.lineTo(f, this.plotArea.getBottom());
                break;
            default:
                path.lineTo(f, this.plotArea.getTop());
                break;
        }
        path.close();
        getPaint().setColor(((FunnelData) this.mDataSet.get(0)).getColor());
        canvas.drawPath(path, getPaint());
    }

    private float getHalfWidth(float f, float f2) {
        return ((f2 / 100.0f) * f) / 2.0f;
    }

    protected void renderPlotDesc(Canvas canvas, float f, float f2, float f3) {
        int size = this.mDataSet.size();
        this.plotArea.getBottom();
        PointF pointF = new PointF();
        PointF pointF2 = new PointF();
        pointF.x = f - (this.plotArea.getPlotWidth() / 2.0f);
        pointF2.x = (this.plotArea.getPlotWidth() / 2.0f) + f;
        float bottom = this.plotArea.getBottom();
        pointF2.y = bottom;
        pointF.y = bottom;
        bottom = f2 / 2.0f;
        Path path = new Path();
        for (int i = 0; i < size; i++) {
            FunnelData funnelData = (FunnelData) this.mDataSet.get(i);
            path.reset();
            if (i == 0) {
                path.moveTo(f, this.plotArea.getBottom());
            } else {
                path.moveTo(pointF.x, pointF.y);
                path.lineTo(pointF2.x, pointF2.y);
            }
            bottom = getHalfWidth(f2, funnelData.getData());
            float sub = sub(this.plotArea.getBottom(), ((float) i) * f3);
            float f4 = sub - (f3 / 2.0f);
            pointF.x = f - bottom;
            pointF.y = sub - f3;
            pointF2.x = bottom + f;
            pointF2.y = sub - f3;
            path.lineTo(pointF2.x, pointF2.y);
            path.lineTo(pointF.x, pointF.y);
            getPaint().setColor(funnelData.getColor());
            path.close();
            if (funnelData.getAlpha() != -1) {
                getPaint().setAlpha(funnelData.getAlpha());
            }
            canvas.drawPath(path, getPaint());
            if (funnelData.getAlpha() != -1) {
                getPaint().setAlpha(255);
            }
            if (i != size - 1 && this.mFunnelLineVisible) {
                canvas.drawLine(pointF.x, pointF.y, pointF2.x, pointF2.y, getFunnelLinePaint());
            }
            renderLabels(canvas, funnelData.getLabel(), f, f4, funnelData.getColor());
        }
    }

    protected void renderPlotAsc(Canvas canvas, float f, float f2, float f3) {
        int size = this.mDataSet.size();
        this.plotArea.getBottom();
        PointF pointF = new PointF();
        PointF pointF2 = new PointF();
        pointF.x = f - (this.plotArea.getPlotWidth() / 2.0f);
        pointF2.x = (this.plotArea.getPlotWidth() / 2.0f) + f;
        float bottom = this.plotArea.getBottom();
        pointF2.y = bottom;
        pointF.y = bottom;
        bottom = f2 / 2.0f;
        Path path = new Path();
        for (int i = 0; i < size; i++) {
            FunnelData funnelData = (FunnelData) this.mDataSet.get(i);
            path.reset();
            if (i == 0) {
                path.moveTo(f, this.plotArea.getTop());
            } else {
                path.moveTo(pointF.x, pointF.y);
                path.lineTo(pointF2.x, pointF2.y);
            }
            bottom = getHalfWidth(f2, funnelData.getData());
            float add = add(this.plotArea.getTop(), ((float) i) * f3);
            float f4 = add + (f3 / 2.0f);
            pointF.x = f - bottom;
            pointF.y = add + f3;
            pointF2.x = bottom + f;
            pointF2.y = add + f3;
            path.lineTo(pointF2.x, pointF2.y);
            path.lineTo(pointF.x, pointF.y);
            path.close();
            getPaint().setColor(funnelData.getColor());
            if (funnelData.getAlpha() != -1) {
                getPaint().setAlpha(funnelData.getAlpha());
            }
            canvas.drawPath(path, getPaint());
            if (funnelData.getAlpha() != -1) {
                getPaint().setAlpha(255);
            }
            if (i != size - 1 && this.mFunnelLineVisible) {
                canvas.drawLine(pointF.x, pointF.y, pointF2.x, pointF2.y, getFunnelLinePaint());
            }
            renderLabels(canvas, funnelData.getLabel(), f, f4, funnelData.getColor());
        }
    }

    private void renderPlotOne(Canvas canvas, float f, float f2, float f3) {
        FunnelData funnelData = (FunnelData) this.mDataSet.get(0);
        float halfWidth = getHalfWidth(f2, funnelData.getData());
        PointF pointF = new PointF();
        PointF pointF2 = new PointF();
        pointF.x = f - halfWidth;
        pointF2.x = halfWidth + f;
        if (SortType.DESC == this.mSortType) {
            halfWidth = this.plotArea.getTop();
            pointF2.y = halfWidth;
            pointF.y = halfWidth;
        } else {
            halfWidth = this.plotArea.getBottom();
            pointF2.y = halfWidth;
            pointF.y = halfWidth;
        }
        if (funnelData.getAlpha() != -1) {
            getPaint().setAlpha(funnelData.getAlpha());
        }
        drawTriangle(canvas, f, pointF, pointF2);
        if (funnelData.getAlpha() != -1) {
            getPaint().setAlpha(255);
        }
        float bottom = this.plotArea.getBottom() - (this.plotArea.getHeight() / 2.0f);
        renderLabels(canvas, funnelData.getLabel(), f, bottom, funnelData.getColor());
    }

    protected void renderLabels(Canvas canvas, String str, float f, float f2, int i) {
        if (getLabelVisible() && "" != str) {
            if (this.mIsLabelLineSyncColor) {
                getLabelLinePaint().setColor(i);
                getLabelPaint().setColor(i);
            } else if (this.mIsLabelSyncColor) {
                getLabelPaint().setColor(i);
            }
            if (isShowLabelLine()) {
                float textWidth = DrawHelper.getInstance().getTextWidth(getLabelPaint(), str);
                switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$HorizontalAlign()[getLabelAlign().ordinal()]) {
                    case 1:
                        canvas.drawLine(f, f2, this.plotArea.getLeft() + textWidth, f2, getLabelLinePaint());
                        break;
                    case 3:
                        canvas.drawLine(f, f2, this.plotArea.getRight() - textWidth, f2, getLabelLinePaint());
                        break;
                }
            }
            switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$HorizontalAlign()[getLabelAlign().ordinal()]) {
                case 1:
                    f = this.plotArea.getLeft();
                    break;
                case 3:
                    f = this.plotArea.getRight();
                    break;
            }
            canvas.drawText(str, f, (DrawHelper.getInstance().getPaintFontHeight(getLabelPaint()) / 3.0f) + f2, getLabelPaint());
        }
    }

    protected void renderPlot(Canvas canvas) {
        if (sortDataSet()) {
            int size = this.mDataSet.size();
            float plotWidth = this.plotArea.getPlotWidth() * (this.mPlotWidthPercent / 100.0f);
            float height = this.plotArea.getHeight() / ((float) size);
            float centerX = this.plotArea.getCenterX();
            if (1 == size) {
                renderPlotOne(canvas, centerX, plotWidth, height);
            }
            if (SortType.DESC == this.mSortType) {
                renderPlotDesc(canvas, centerX, plotWidth, height);
            } else {
                renderPlotAsc(canvas, centerX, plotWidth, height);
            }
        }
    }

    protected boolean postRender(Canvas canvas) throws Exception {
        try {
            super.postRender(canvas);
            calcPlotRange();
            this.plotArea.render(canvas);
            renderTitle(canvas);
            renderPlot(canvas);
            renderFocusShape(canvas);
            renderToolTip(canvas);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }
}
