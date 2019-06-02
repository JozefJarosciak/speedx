package org.xclcharts.chart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.v4.view.ViewCompat;
import java.util.ArrayList;
import java.util.List;
import org.xclcharts.common.PointHelper;
import org.xclcharts.renderer.EventChart;
import org.xclcharts.renderer.XEnum.ChartType;

public class FunnelChart2 extends EventChart {
    private int mBgColor = -1;
    private List<Funnel2Data> mDataSet;
    private Paint mPaint = null;
    private Paint mPaintLabel = null;

    public ChartType getType() {
        return ChartType.FUNNEL;
    }

    public List<Funnel2Data> getDataSource() {
        return this.mDataSet;
    }

    public void setDataSource(List<Funnel2Data> list) {
        this.mDataSet = list;
    }

    public Paint getPaint() {
        if (this.mPaint == null) {
            this.mPaint = new Paint(1);
        }
        return this.mPaint;
    }

    public Paint getPaintLabel() {
        if (this.mPaintLabel == null) {
            this.mPaintLabel = new Paint(1);
            this.mPaintLabel.setColor(ViewCompat.MEASURED_STATE_MASK);
        }
        return this.mPaintLabel;
    }

    public void setBgColor(int i) {
        this.mBgColor = i;
    }

    protected void renderPlot(Canvas canvas) {
        if (this.mDataSet.size() != 0) {
            int i;
            float div = div(this.plotArea.getPlotWidth(), 5.0f);
            float mul = mul(div, 2.0f);
            float sub = sub(this.plotArea.getCenterX(), div);
            float add = add(this.plotArea.getCenterX(), div);
            Path path = new Path();
            path.moveTo(this.plotArea.getLeft(), this.plotArea.getBottom());
            path.lineTo(this.plotArea.getRight(), this.plotArea.getBottom());
            path.lineTo(add, this.plotArea.getTop());
            path.lineTo(sub, this.plotArea.getTop());
            path.close();
            getPaint().setStyle(Style.FILL);
            getPaint().setColor(((Funnel2Data) this.mDataSet.get(0)).getColor());
            canvas.drawPath(path, getPaint());
            List arrayList = new ArrayList();
            List arrayList2 = new ArrayList();
            List arrayList3 = new ArrayList();
            int size = this.mDataSet.size();
            float top = this.plotArea.getTop();
            float f = sub;
            for (i = 0; i < size; i++) {
                f = add(f, mul(mul, ((Funnel2Data) this.mDataSet.get(i)).getPercentData()));
                arrayList.add(new PointF(f, top));
            }
            float bottom = this.plotArea.getBottom();
            mul = div(sub(this.plotArea.getPlotWidth(), mul), 2.0f);
            f = bottom;
            for (i = 0; i < size; i++) {
                f = sub(f, mul(this.plotArea.getPlotHeight(), ((Funnel2Data) this.mDataSet.get(i)).getBaseData()));
                div = mul(mul, sub(1.0f, div(f, this.plotArea.getPlotHeight())));
                top = this.plotArea.getLeft() + div;
                arrayList2.add(new PointF((((Funnel2Data) this.mDataSet.get(i)).getPercentData() * ((this.plotArea.getRight() - div) - top)) + top, f));
            }
            List arrayList4 = new ArrayList();
            mul = add(add, div(mul, 2.0f));
            f = this.plotArea.getBottom();
            for (i = 0; i < size; i++) {
                f = sub(f, mul(this.plotArea.getPlotHeight(), ((Funnel2Data) this.mDataSet.get(i)).getPercentData()));
                arrayList3.add(new PointF(this.plotArea.getRight(), f));
                if (i == 0) {
                    arrayList4.add(Float.valueOf(sub(this.plotArea.getBottom(), div(sub(this.plotArea.getBottom(), f), 2.0f))));
                } else {
                    arrayList4.add(Float.valueOf(sub(f, div(sub(f, ((PointF) arrayList3.get(i - 1)).y), 2.0f))));
                }
            }
            Path path2 = new Path();
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                if (i2 + 1 < arrayList.size()) {
                    getPaint().setColor(((Funnel2Data) this.mDataSet.get(i2 + 1)).getColor());
                    path2.moveTo(((PointF) arrayList.get(i2)).x, ((PointF) arrayList.get(i2)).y);
                    PointF percent = PointHelper.percent((PointF) arrayList.get(i2), 0.7f, (PointF) arrayList2.get(i2), 0.5f);
                    path2.quadTo(percent.x, percent.y, ((PointF) arrayList2.get(i2)).x, ((PointF) arrayList2.get(i2)).y);
                    percent = PointHelper.percent((PointF) arrayList2.get(i2), 0.1f, (PointF) arrayList3.get(i2), 0.9f);
                    path2.quadTo(percent.x, percent.y, ((PointF) arrayList3.get(i2)).x, ((PointF) arrayList3.get(i2)).y);
                    path2.lineTo(add, this.plotArea.getTop());
                    path2.close();
                    canvas.drawPath(path2, getPaint());
                    path2.reset();
                }
            }
            path.reset();
            path.moveTo(this.plotArea.getRight(), this.plotArea.getBottom());
            path.lineTo(this.plotArea.getRight(), this.plotArea.getTop());
            path.lineTo(add, this.plotArea.getTop());
            path.close();
            getPaint().setColor(this.mBgColor);
            canvas.drawPath(path, getPaint());
            path.reset();
            path.moveTo(this.plotArea.getLeft(), this.plotArea.getTop());
            path.lineTo(sub, this.plotArea.getTop());
            path.lineTo(this.plotArea.getLeft(), this.plotArea.getBottom());
            path.close();
            canvas.drawPath(path, getPaint());
            getPaintLabel();
            for (i = 0; i < arrayList4.size(); i++) {
                try {
                    canvas.drawText(((Funnel2Data) this.mDataSet.get(i)).getLabel(), mul, ((Float) arrayList4.get(i)).floatValue(), this.mPaintLabel);
                } catch (Exception e) {
                }
            }
        }
    }

    protected boolean postRender(Canvas canvas) throws Exception {
        try {
            super.postRender(canvas);
            calcPlotRange();
            canvas.drawColor(this.mBgColor);
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
