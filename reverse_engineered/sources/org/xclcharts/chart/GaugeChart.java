package org.xclcharts.chart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.util.Pair;
import com.alibaba.fastjson.asm.Opcodes;
import java.util.List;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.CirChart;
import org.xclcharts.renderer.XEnum.ChartType;

public class GaugeChart extends CirChart {
    private static final String TAG = "GaugeChart";
    private static final int mStartAngle = 180;
    private List<String> mLabels = null;
    private Paint mPaintDount = null;
    private Paint mPaintPartitionFill = null;
    private Paint mPaintPinterCircle = null;
    private Paint mPaintPointerLine = null;
    private Paint mPaintTick = null;
    private List<Pair> mPartitionDataset = null;
    private float mPointerAngle = 20.0f;
    private double mTickSteps = 10.0d;

    public GaugeChart() {
        initPaint();
    }

    public ChartType getType() {
        return ChartType.GAUGE;
    }

    private void initPaint() {
        getLabelPaint().setTextSize(18.0f);
        getLabelPaint().setColor(-16776961);
        if (this.mPaintTick == null) {
            this.mPaintTick = new Paint();
            this.mPaintTick.setStyle(Style.FILL);
            this.mPaintTick.setAntiAlias(true);
            this.mPaintTick.setColor(Color.rgb(50, Opcodes.FCMPL, 222));
            this.mPaintTick.setStrokeWidth(1.0f);
        }
        if (this.mPaintPointerLine == null) {
            this.mPaintPointerLine = new Paint();
            this.mPaintPointerLine.setStyle(Style.FILL);
            this.mPaintPointerLine.setAntiAlias(true);
            this.mPaintPointerLine.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mPaintPointerLine.setStrokeWidth(3.0f);
        }
        if (this.mPaintPinterCircle == null) {
            this.mPaintPinterCircle = new Paint();
            this.mPaintPinterCircle.setStyle(Style.FILL);
            this.mPaintPinterCircle.setAntiAlias(true);
            this.mPaintPinterCircle.setColor(ViewCompat.MEASURED_STATE_MASK);
            this.mPaintPinterCircle.setStrokeWidth(8.0f);
        }
        if (this.mPaintPartitionFill == null) {
            this.mPaintPartitionFill = new Paint();
            this.mPaintPartitionFill.setStyle(Style.FILL);
            this.mPaintPartitionFill.setAntiAlias(true);
        }
        if (this.mPaintDount == null) {
            this.mPaintDount = new Paint();
            this.mPaintDount.setStyle(Style.STROKE);
            this.mPaintDount.setColor(Color.rgb(50, Opcodes.FCMPL, 222));
            this.mPaintDount.setAntiAlias(true);
            this.mPaintDount.setStrokeWidth(2.0f);
        }
    }

    public Paint getTickPaint() {
        return this.mPaintTick;
    }

    public Paint getPinterCirclePaint() {
        return this.mPaintPinterCircle;
    }

    public Paint getPointerLinePaint() {
        return this.mPaintPointerLine;
    }

    public Paint getPartitionFillPaint() {
        return this.mPaintPartitionFill;
    }

    public Paint getDountPaint() {
        return this.mPaintDount;
    }

    protected void drawPercent(Canvas canvas, Paint paint, float f, float f2, float f3, float f4, float f5) throws Exception {
        try {
            canvas.drawArc(new RectF(sub(f, f3), sub(f2, f3), add(f, f3), add(f2, f3)), f4, f5, true, paint);
        } catch (Exception e) {
            throw e;
        }
    }

    public void setTickSteps(double d) {
        this.mTickSteps = d;
    }

    public void setCategories(List<String> list) {
        this.mLabels = list;
    }

    public void setPartition(List<Pair> list) {
        this.mPartitionDataset = list;
    }

    public void setCurrentAngle(float f) {
        this.mPointerAngle = f;
    }

    private void renderLabels(Canvas canvas) {
        if (this.mLabels != null) {
            float round = (float) Math.round((float) (180 / (this.mLabels.size() - 1)));
            float radius = getRadius();
            float add = add(radius, div(radius, 10.0f));
            float centerX = this.plotArea.getCenterX();
            float cirY = getCirY();
            getLabelPaint().setTextAlign(Align.CENTER);
            int i = 0;
            for (String str : this.mLabels) {
                if (i == 0) {
                    canvas.drawText(str, centerX - add, cirY, getLabelPaint());
                } else if (i == this.mLabels.size() - 1) {
                    canvas.drawText(str, centerX + add, cirY, getLabelPaint());
                } else {
                    MathHelper.getInstance().calcArcEndPointXY(centerX, cirY, add, 180.0f + (((float) i) * round));
                    canvas.drawText(str, MathHelper.getInstance().getPosX(), MathHelper.getInstance().getPosY(), getLabelPaint());
                }
                i++;
            }
        }
    }

    private void renderTicks(Canvas canvas) {
        float div = div(180.0f, new Double(this.mTickSteps).floatValue());
        float centerX = this.plotArea.getCenterX();
        float cirY = getCirY();
        float mul = mul(getRadius(), 0.9f);
        for (int i = 0; ((double) i) < this.mTickSteps; i++) {
            if (i != 0) {
                float add = (float) MathHelper.getInstance().add(180.0d, (double) (((float) i) * div));
                MathHelper.getInstance().calcArcEndPointXY(centerX, cirY, getRadius(), add);
                float posX = MathHelper.getInstance().getPosX();
                float posY = MathHelper.getInstance().getPosY();
                MathHelper.getInstance().calcArcEndPointXY(centerX, cirY, mul, add);
                canvas.drawLine(posX, posY, MathHelper.getInstance().getPosX(), MathHelper.getInstance().getPosY(), this.mPaintTick);
            }
        }
    }

    private void renderPointerLine(Canvas canvas) {
        float mul = mul(getRadius(), 0.9f);
        float centerX = this.plotArea.getCenterX();
        float cirY = getCirY();
        if (Float.compare(this.mPointerAngle, 180.0f) == 0 || Float.compare(this.mPointerAngle, 180.0f) == 1) {
            canvas.drawLine(centerX, cirY, centerX + mul, cirY, this.mPaintPointerLine);
        } else if (Float.compare(this.mPointerAngle, 0.0f) == 0 || Float.compare(this.mPointerAngle, 0.0f) == -1) {
            canvas.drawLine(centerX, cirY, centerX - mul, cirY, this.mPaintPointerLine);
        } else {
            MathHelper.getInstance().calcArcEndPointXY(centerX, cirY, mul, add(this.mPointerAngle, 180.0f));
            float posX = MathHelper.getInstance().getPosX();
            float posY = MathHelper.getInstance().getPosY();
            if (Float.compare(posY, cirY) == 1) {
                posY = cirY;
            }
            canvas.drawLine(centerX, cirY, posX, posY, this.mPaintPointerLine);
        }
    }

    private void renderPinterCircle(Canvas canvas) {
        canvas.drawCircle(this.plotArea.getCenterX(), getCirY(), mul(getRadius(), 0.05f), this.mPaintPinterCircle);
    }

    private boolean renderPartitionFill(Canvas canvas) throws Exception {
        if (this.mPartitionDataset == null || this.mPartitionDataset.size() == 0) {
            Log.e(TAG, "数据源为空.");
        } else {
            float mul = mul(getRadius(), 0.8f);
            float cirY = getCirY();
            RectF rectF = new RectF();
            rectF.left = sub(this.plotArea.getCenterX(), mul);
            rectF.top = sub(cirY, mul);
            rectF.right = add(this.plotArea.getCenterX(), mul);
            rectF.bottom = add(cirY, mul);
            float f = 0.0f;
            for (Pair pair : this.mPartitionDataset) {
                Float f2 = (Float) pair.first;
                cirY = add(f, f2.floatValue());
                if (Float.compare(f2.floatValue(), 0.0f) < 0) {
                    Log.e(TAG, "负角度???!!!");
                } else if (Float.compare(cirY, 180.0f) == 1) {
                    Log.e(TAG, "输入的角度总计大于180度");
                    break;
                }
                this.mPaintPartitionFill.setColor(((Integer) pair.second).intValue());
                canvas.drawArc(rectF, add(f, 180.0f), f2.floatValue(), true, this.mPaintPartitionFill);
                f = add(f, f2.floatValue());
            }
        }
        return false;
    }

    private float getCirY() {
        float bottom = getBottom();
        if (isShowBorder()) {
            bottom -= (float) (getBorderWidth() / 2);
        }
        return bottom - mul(getRadius(), 0.05f);
    }

    public float getRadius() {
        float borderWidth;
        float width = getWidth() / 2.0f;
        if (isShowBorder()) {
            borderWidth = width - ((float) getBorderWidth());
        } else {
            borderWidth = width;
        }
        if (this.mLabels != null && this.mLabels.size() > 0) {
            borderWidth = sub(sub(borderWidth, Math.max(DrawHelper.getInstance().getTextWidth(getLabelPaint(), (String) this.mLabels.get(0)), DrawHelper.getInstance().getTextWidth(getLabelPaint(), (String) this.mLabels.get(this.mLabels.size() - 1)))), (float) (getBorderWidth() / 2));
        }
        return borderWidth - mul(borderWidth, 0.05f);
    }

    private void renderDount(Canvas canvas) throws Exception {
        drawPercent(canvas, this.mPaintDount, this.plotArea.getCenterX(), getCirY(), getRadius(), 180.0f, 180.0f);
    }

    protected void renderPlot(Canvas canvas) {
        try {
            renderDount(canvas);
            renderTicks(canvas);
            renderPartitionFill(canvas);
            renderLabels(canvas);
            renderPointerLine(canvas);
            renderPinterCircle(canvas);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
    }

    protected boolean postRender(Canvas canvas) throws Exception {
        try {
            super.postRender(canvas);
            renderPlot(canvas);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }
}
