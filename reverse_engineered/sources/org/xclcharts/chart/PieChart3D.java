package org.xclcharts.chart;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.util.Log;
import java.util.List;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.XEnum.ChartType;
import org.xclcharts.renderer.info.PlotArcLabelInfo;

public class PieChart3D extends PieChart {
    private static final String TAG = "PieChart3D";
    private final int mRender3DLevel = 15;

    public ChartType getType() {
        return ChartType.PIE3D;
    }

    private boolean render3D(Canvas canvas, float f, List<PieData> list, float f2, float f3, float f4) {
        if (list == null) {
            return false;
        }
        int i = 0;
        float f5 = f;
        while (i < 15) {
            canvas.save(1);
            canvas.translate(0.0f, (float) (15 - i));
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                PieData pieData = (PieData) list.get(i2);
                if (pieData != null) {
                    float sliceAngle = MathHelper.getInstance().getSliceAngle(getTotalAngle(), (float) pieData.getPercentage());
                    if (validateAngle(sliceAngle)) {
                        geArcPaint().setColor(pieData.getSliceColor());
                        if (pieData.getSelected()) {
                            PointF calcArcEndPointXY = MathHelper.getInstance().calcArcEndPointXY(f2, f3, div(f4, getSelectedOffset()), add(f5, div(sliceAngle, 2.0f)));
                            initRectF(sub(calcArcEndPointXY.x, f4), sub(calcArcEndPointXY.y, f4), add(calcArcEndPointXY.x, f4), add(calcArcEndPointXY.y, f4));
                            canvas.drawArc(this.mRectF, f5, sliceAngle, true, geArcPaint());
                        } else {
                            initRectF(sub(f2, f4), sub(f3, f4), add(f2, f4), add(f3, f4));
                            canvas.drawArc(this.mRectF, f5, sliceAngle, true, geArcPaint());
                        }
                        f5 = add(f5, sliceAngle);
                    }
                }
            }
            canvas.restore();
            i++;
            f5 = f;
        }
        return true;
    }

    private boolean renderFlatArcAndLegend(Canvas canvas, float f, List<PieData> list, float f2, float f3, float f4) {
        if (list == null) {
            return false;
        }
        int size = list.size();
        this.mLstLabels.clear();
        float sub = sub(f2, f4);
        float sub2 = sub(f3, f4);
        float add = add(f2, f4);
        float add2 = add(f3, f4);
        float f5 = f;
        for (int i = 0; i < size; i++) {
            PieData pieData = (PieData) list.get(i);
            if (pieData != null) {
                float sliceAngle = MathHelper.getInstance().getSliceAngle(getTotalAngle(), (float) pieData.getPercentage());
                if (validateAngle(sliceAngle)) {
                    geArcPaint().setColor(DrawHelper.getInstance().getDarkerColor(pieData.getSliceColor()));
                    if (pieData.getSelected()) {
                        float div = div(f4, getSelectedOffset());
                        PointF calcArcEndPointXY = MathHelper.getInstance().calcArcEndPointXY(f2, f3, div, add(f5, div(sliceAngle, 2.0f)));
                        initRectF(sub(calcArcEndPointXY.x, f4), sub(calcArcEndPointXY.y, f4), add(calcArcEndPointXY.x, f4), add(calcArcEndPointXY.y, f4));
                        canvas.drawArc(this.mRectF, f5, sliceAngle, true, geArcPaint());
                        this.mLstLabels.add(new PlotArcLabelInfo(i, calcArcEndPointXY.x, calcArcEndPointXY.y, f4, f5, sliceAngle));
                    } else {
                        initRectF(sub, sub2, add, add2);
                        canvas.drawArc(this.mRectF, f5, sliceAngle, true, geArcPaint());
                        this.mLstLabels.add(new PlotArcLabelInfo(i, f2, f3, f4, f5, sliceAngle));
                    }
                    saveArcRecord(i, f2 + this.mTranslateXY[0], f3 + this.mTranslateXY[1], f4, f5, sliceAngle, getSelectedOffset(), getOffsetAngle());
                    f5 = add(f5, sliceAngle);
                }
            }
        }
        renderLabels(canvas);
        this.plotLegend.renderPieKey(canvas, list);
        return true;
    }

    protected boolean renderPlot(Canvas canvas) {
        List dataSource = getDataSource();
        if (dataSource == null) {
            Log.e(TAG, "数据源为空.");
            return false;
        }
        float centerX = this.plotArea.getCenterX();
        float centerY = this.plotArea.getCenterY();
        float radius = getRadius();
        if (!render3D(canvas, this.mOffsetAngle, dataSource, centerX, centerY, radius)) {
            return false;
        }
        return renderFlatArcAndLegend(canvas, this.mOffsetAngle, dataSource, centerX, centerY, radius);
    }
}
