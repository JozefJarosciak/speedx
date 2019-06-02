package org.xclcharts.chart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import com.alibaba.fastjson.asm.Opcodes;
import java.util.Iterator;
import java.util.List;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.CirChart;
import org.xclcharts.renderer.XEnum.ChartType;
import org.xclcharts.renderer.XEnum.CircleType;

public class CircleChart extends CirChart {
    private static final String TAG = "CircleChart";
    private String mDataInfo = "";
    protected List<PieData> mDataSet;
    private CircleType mDisplayType = CircleType.FULL;
    private Paint mPaintBgCircle = null;
    private Paint mPaintDataInfo = null;
    private Paint mPaintFillCircle = null;
    private boolean mShowCap = false;
    private boolean mShowInnerBG = true;
    private boolean mShowInnerFill = true;
    private float miRadius = 0.8f;
    private float moRadius = 0.9f;

    public CircleChart() {
        initChart();
    }

    public ChartType getType() {
        return ChartType.CIRCLE;
    }

    private void initChart() {
        if (getLabelPaint() != null) {
            getLabelPaint().setColor(-1);
            getLabelPaint().setTextSize(36.0f);
            getLabelPaint().setTextAlign(Align.CENTER);
        }
        setInitialAngle(180.0f);
    }

    public void setDataSource(List<PieData> list) {
        this.mDataSet = list;
    }

    public void setAttributeInfo(String str) {
        this.mDataInfo = str;
    }

    public void setCircleType(CircleType circleType) {
        this.mDisplayType = circleType;
    }

    public Paint getFillCirclePaint() {
        if (this.mPaintFillCircle == null) {
            this.mPaintFillCircle = new Paint();
            this.mPaintFillCircle.setColor(Color.rgb(77, 83, 97));
            this.mPaintFillCircle.setAntiAlias(true);
        }
        return this.mPaintFillCircle;
    }

    public Paint getBgCirclePaint() {
        if (this.mPaintBgCircle == null) {
            this.mPaintBgCircle = new Paint();
            this.mPaintBgCircle.setColor(Color.rgb(Opcodes.LCMP, Opcodes.IF_ICMPEQ, Opcodes.PUTFIELD));
            this.mPaintBgCircle.setAntiAlias(true);
        }
        return this.mPaintBgCircle;
    }

    public Paint getDataInfoPaint() {
        if (this.mPaintDataInfo == null) {
            this.mPaintDataInfo = new Paint();
            this.mPaintDataInfo.setTextSize(22.0f);
            this.mPaintDataInfo.setColor(-1);
            this.mPaintDataInfo.setTextAlign(Align.CENTER);
            this.mPaintDataInfo.setAntiAlias(true);
        }
        return this.mPaintDataInfo;
    }

    public void hideInnerFill() {
        this.mShowInnerFill = false;
    }

    public void hideInnerBG() {
        this.mShowInnerBG = false;
    }

    public void showInnerFill() {
        this.mShowInnerFill = true;
    }

    public boolean isShowInnerFill() {
        return this.mShowInnerFill;
    }

    public void showInnerBG() {
        this.mShowInnerBG = true;
    }

    public boolean isShowInnerBG() {
        return this.mShowInnerBG;
    }

    public void setORadius(float f) {
        this.moRadius = f;
    }

    public void setIRadius(float f) {
        this.miRadius = f;
    }

    public boolean isShowCap() {
        return this.mShowCap;
    }

    public void ShowCap() {
        this.mShowCap = true;
    }

    public void HideCap() {
        this.mShowCap = false;
    }

    protected void drawPercent(Canvas canvas, Paint paint, float f, float f2, float f3, float f4, float f5) throws Exception {
        try {
            canvas.drawArc(new RectF(sub(f, f3), sub(f2, f3), add(f, f3), add(f2, f3)), f4, f5, true, paint);
        } catch (Exception e) {
            throw e;
        }
    }

    private float getCirY(float f, float f2) {
        if ("" == this.mDataInfo) {
            return f + (f2 / 3.0f);
        }
        return f;
    }

    protected boolean renderPlot(Canvas canvas) {
        try {
            float centerX = this.plotArea.getCenterX();
            float centerY = this.plotArea.getCenterY();
            float radius = getRadius();
            RectF rectF = new RectF(sub(centerX, radius), sub(centerY, radius), add(centerX, radius), add(centerY, radius));
            Paint paint = new Paint();
            paint.setAntiAlias(true);
            float paintFontHeight = DrawHelper.getInstance().getPaintFontHeight(getDataInfoPaint());
            float paintFontHeight2 = DrawHelper.getInstance().getPaintFontHeight(getLabelPaint());
            float f = paintFontHeight2 + paintFontHeight;
            Iterator it = this.mDataSet.iterator();
            if (it.hasNext()) {
                PieData pieData = (PieData) it.next();
                paint.setColor(pieData.getSliceColor());
                if (CircleType.HALF == this.mDisplayType) {
                    setInitialAngle(180.0f);
                    float width = getWidth() / 2.0f;
                    float bottom = getBottom();
                    if (isShowBorder()) {
                        width -= (float) getBorderWidth();
                        bottom -= (float) (getBorderWidth() / 2);
                    }
                    float round = MathHelper.getInstance().round(mul(width, this.moRadius), 2);
                    float round2 = MathHelper.getInstance().round(mul(width, this.miRadius), 2);
                    if (isShowInnerBG()) {
                        drawPercent(canvas, getBgCirclePaint(), centerX, bottom, width, 180.0f, 180.0f);
                        paintFontHeight2 = round2;
                    } else {
                        paintFontHeight2 = width;
                        round = width;
                    }
                    if (isShowInnerFill()) {
                        drawPercent(canvas, getFillCirclePaint(), centerX, bottom, round, 180.0f, 180.0f);
                    }
                    drawPercent(canvas, paint, centerX, bottom, width, 180.0f, MathHelper.getInstance().getSliceAngle(180.0f, (float) pieData.getPercentage()));
                    if (isShowInnerFill()) {
                        drawPercent(canvas, getFillCirclePaint(), centerX, bottom, paintFontHeight2, 180.0f, 180.0f);
                    }
                    if ("" != pieData.getLabel()) {
                        canvas.drawText(pieData.getLabel(), centerX, sub(bottom, f), getLabelPaint());
                    }
                    if ("" != this.mDataInfo) {
                        canvas.drawText(this.mDataInfo, centerX, bottom - paintFontHeight, getDataInfoPaint());
                    }
                } else {
                    Canvas canvas2;
                    float sliceAngle = MathHelper.getInstance().getSliceAngle(360.0f, (float) pieData.getPercentage());
                    if (isShowInnerBG()) {
                        canvas.drawCircle(centerX, centerY, radius, getBgCirclePaint());
                    }
                    if (isShowInnerFill()) {
                        canvas2 = canvas;
                        canvas2.drawCircle(centerX, centerY, MathHelper.getInstance().round(mul(radius, this.moRadius), 2), getFillCirclePaint());
                    }
                    canvas.drawArc(rectF, this.mOffsetAngle, sliceAngle, true, paint);
                    if (isShowCap() && (isShowInnerBG() || isShowInnerFill())) {
                        float round3 = MathHelper.getInstance().round(mul(radius, this.miRadius), 2);
                        float f2 = ((radius - round3) / 2.0f) + round3;
                        if (isShowInnerBG()) {
                            paint.setColor(getBgCirclePaint().getColor());
                        } else {
                            paint.setColor(getFillCirclePaint().getColor());
                        }
                        PointF calcArcEndPointXY = MathHelper.getInstance().calcArcEndPointXY(centerX, centerY, f2, getInitialAngle());
                        canvas.drawLine(centerX, centerY, calcArcEndPointXY.x, calcArcEndPointXY.y, paint);
                        canvas.drawCircle(calcArcEndPointXY.x, calcArcEndPointXY.y, (radius - round3) / 2.0f, paint);
                        PointF calcArcEndPointXY2 = MathHelper.getInstance().calcArcEndPointXY(centerX, centerY, f2, add(this.mOffsetAngle, sliceAngle));
                        paint.setColor(pieData.getSliceColor());
                        canvas.drawLine(centerX, centerY, calcArcEndPointXY2.x, calcArcEndPointXY2.y, paint);
                        canvas.drawCircle(calcArcEndPointXY2.x, calcArcEndPointXY2.y, (radius - round3) / 2.0f, paint);
                    }
                    if (isShowInnerFill()) {
                        canvas2 = canvas;
                        canvas2.drawCircle(centerX, centerY, MathHelper.getInstance().round(mul(radius, this.miRadius), 2), getFillCirclePaint());
                    }
                    if ("" != pieData.getLabel()) {
                        canvas.drawText(pieData.getLabel(), centerX, getCirY(centerY, paintFontHeight2), getLabelPaint());
                    }
                    if ("" != this.mDataInfo) {
                        canvas.drawText(this.mDataInfo, centerX, add(centerY, paintFontHeight), getDataInfoPaint());
                    }
                }
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return true;
    }

    protected boolean postRender(Canvas canvas) throws Exception {
        try {
            super.postRender(canvas);
            return renderPlot(canvas);
        } catch (Exception e) {
            throw e;
        }
    }
}
