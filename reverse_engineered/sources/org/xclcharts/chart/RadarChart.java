package org.xclcharts.chart;

import android.graphics.Canvas;
import android.graphics.Paint.Align;
import android.graphics.Path;
import android.util.Log;
import java.lang.reflect.Array;
import java.util.List;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.RdChart;
import org.xclcharts.renderer.XEnum.ChartType;
import org.xclcharts.renderer.XEnum.DataAreaStyle;
import org.xclcharts.renderer.XEnum.DotStyle;
import org.xclcharts.renderer.XEnum.RadarChartType;
import org.xclcharts.renderer.axis.CategoryAxis;
import org.xclcharts.renderer.axis.CategoryAxisRender;
import org.xclcharts.renderer.axis.DataAxis;
import org.xclcharts.renderer.axis.DataAxisRender;
import org.xclcharts.renderer.line.PlotDot;
import org.xclcharts.renderer.line.PlotDotRender;
import org.xclcharts.renderer.line.PlotLine;

public class RadarChart extends RdChart {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$DataAreaStyle = null;
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$RadarChartType = null;
    private static final String TAG = "RadarChart";
    private CategoryAxisRender categoryAxis = null;
    private DataAxisRender dataAxis = null;
    private int mAreaAlpha = 100;
    private Float[][] mArrayDotX = null;
    private Float[][] mArrayDotY = null;
    private Float[] mArrayLabelAgent = null;
    private Float[][] mArrayLabelX = null;
    private Float[][] mArrayLabelY = null;
    private Float[] mArrayRadius = null;
    private List<RadarData> mDataSet;
    private int mLabelOffset = 0;
    private RadarChartType mRadarChartType = RadarChartType.RADAR;
    private Path mRdPath = new Path();

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$DataAreaStyle() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$DataAreaStyle;
        if (iArr == null) {
            iArr = new int[DataAreaStyle.values().length];
            try {
                iArr[DataAreaStyle.FILL.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[DataAreaStyle.STROKE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$DataAreaStyle = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$RadarChartType() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$RadarChartType;
        if (iArr == null) {
            iArr = new int[RadarChartType.values().length];
            try {
                iArr[RadarChartType.RADAR.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[RadarChartType.ROUND.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$RadarChartType = iArr;
        }
        return iArr;
    }

    public RadarChart() {
        initChart();
    }

    public ChartType getType() {
        return ChartType.RADAR;
    }

    private void initChart() {
        if (this.dataAxis == null) {
            this.dataAxis = new DataAxisRender();
        }
        if (this.dataAxis != null) {
            this.dataAxis.setHorizontalTickAlign(Align.LEFT);
            this.dataAxis.getTickLabelPaint().setTextAlign(Align.RIGHT);
            this.dataAxis.hideTickMarks();
        }
        if (this.categoryAxis == null) {
            this.categoryAxis = new CategoryAxisRender();
        }
        if (this.plotLegend == null) {
            this.plotLegend.show();
        }
    }

    private void clearArray() {
        if (this.mArrayDotX != null) {
            this.mArrayDotX = null;
        }
        if (this.mArrayDotY != null) {
            this.mArrayDotY = null;
        }
        if (this.mArrayLabelX != null) {
            this.mArrayLabelX = null;
        }
        if (this.mArrayLabelY != null) {
            this.mArrayLabelY = null;
        }
        if (this.mArrayLabelAgent != null) {
            this.mArrayLabelAgent = null;
        }
        if (this.mArrayRadius != null) {
            this.mArrayRadius = null;
        }
    }

    public void setChartType(RadarChartType radarChartType) {
        this.mRadarChartType = radarChartType;
    }

    public DataAxis getDataAxis() {
        return this.dataAxis;
    }

    public CategoryAxis getCategoryAxis() {
        return this.categoryAxis;
    }

    public void setCategories(List<String> list) {
        if (this.categoryAxis != null) {
            this.categoryAxis.setDataBuilding(list);
        }
    }

    public void setDataSource(List<RadarData> list) {
        this.mDataSet = list;
    }

    public List<RadarData> getDataSource() {
        return this.mDataSet;
    }

    public void setAreaAlpha(int i) {
        this.mAreaAlpha = i;
    }

    private boolean validateParams() {
        if (this.categoryAxis.getDataSet().size() <= 0) {
            Log.e(TAG, "标签数据源为空");
            return false;
        } else if (this.mDataSet.size() > 0) {
            return true;
        } else {
            Log.e(TAG, "数据源为空");
            return false;
        }
    }

    private void renderGridLines(Canvas canvas) {
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$RadarChartType()[this.mRadarChartType.ordinal()]) {
            case 1:
                renderGridLinesRadar(canvas);
                return;
            case 2:
                renderGridLinesRound(canvas);
                return;
            default:
                return;
        }
    }

    private void renderGridLinesRadar(Canvas canvas) {
        this.mRdPath.reset();
        for (int i = 0; i < getAxisTickCount(); i++) {
            for (int i2 = 0; i2 < getPlotAgentNumber(); i2++) {
                if (i2 == 0) {
                    this.mRdPath.moveTo(this.mArrayDotX[i][i2].floatValue(), this.mArrayDotY[i][i2].floatValue());
                } else {
                    this.mRdPath.lineTo(this.mArrayDotX[i][i2].floatValue(), this.mArrayDotY[i][i2].floatValue());
                }
            }
            this.mRdPath.close();
            canvas.drawPath(this.mRdPath, getLinePaint());
            this.mRdPath.reset();
        }
    }

    private void renderGridLinesRound(Canvas canvas) {
        for (Float floatValue : this.mArrayRadius) {
            canvas.drawCircle(this.plotArea.getCenterX(), this.plotArea.getCenterY(), floatValue.floatValue(), getLinePaint());
        }
    }

    private void renderAxisLines(Canvas canvas) {
        float centerX = this.plotArea.getCenterX();
        float centerY = this.plotArea.getCenterY();
        int plotAgentNumber = getPlotAgentNumber();
        int axisTickCount = getAxisTickCount() - 1;
        for (int i = 0; i < plotAgentNumber; i++) {
            canvas.drawLine(centerX, centerY, this.mArrayDotX[axisTickCount][i].floatValue(), this.mArrayDotY[axisTickCount][i].floatValue(), getLinePaint());
        }
    }

    private void renderAxisLabels(Canvas canvas) {
        int plotAgentNumber = getPlotAgentNumber();
        int axisTickCount = getAxisTickCount();
        for (int i = 0; i < axisTickCount; i++) {
            for (int i2 = 0; i2 < plotAgentNumber; i2++) {
                if (i == axisTickCount - 1) {
                    canvas.drawText((String) this.categoryAxis.getDataSet().get(i2), this.mArrayLabelX[i][i2].floatValue(), this.mArrayLabelY[i][i2].floatValue(), getLabelPaint());
                }
                if (i2 == 0) {
                    Canvas canvas2 = canvas;
                    this.dataAxis.renderAxisHorizontalTick(getLeft(), getPlotArea().getLeft(), canvas2, this.mArrayDotX[i][i2].floatValue(), this.mArrayDotY[i][i2].floatValue(), Double.toString((this.dataAxis.getAxisSteps() * ((double) i)) + ((double) this.dataAxis.getAxisMin())), true);
                }
            }
        }
    }

    private int getAxisTickCount() {
        if (this.dataAxis == null) {
            return 0;
        }
        return Math.round((float) (this.dataAxis.getAixTickCount() + 1));
    }

    private int getPlotAgentNumber() {
        if (this.categoryAxis == null) {
            return 0;
        }
        return this.categoryAxis.getDataSet().size();
    }

    public void setlabelOffset(int i) {
        this.mLabelOffset = i;
    }

    private void calcAllPoints() {
        float centerX = this.plotArea.getCenterX();
        float centerY = this.plotArea.getCenterY();
        int plotAgentNumber = getPlotAgentNumber();
        int axisTickCount = getAxisTickCount();
        float div = MathHelper.getInstance().div(360.0f, (float) plotAgentNumber);
        float sub = MathHelper.getInstance().sub(270.0f, div);
        float div2 = MathHelper.getInstance().div(getRadius(), (float) (axisTickCount - 1));
        this.mArrayDotX = (Float[][]) Array.newInstance(Float.class, new int[]{axisTickCount, plotAgentNumber});
        this.mArrayDotY = (Float[][]) Array.newInstance(Float.class, new int[]{axisTickCount, plotAgentNumber});
        this.mArrayLabelAgent = new Float[plotAgentNumber];
        this.mArrayLabelX = (Float[][]) Array.newInstance(Float.class, new int[]{axisTickCount, plotAgentNumber});
        this.mArrayLabelY = (Float[][]) Array.newInstance(Float.class, new int[]{axisTickCount, plotAgentNumber});
        this.mArrayRadius = new Float[axisTickCount];
        float paintFontHeight = (DrawHelper.getInstance().getPaintFontHeight(getLabelPaint()) + getRadius()) + ((float) this.mLabelOffset);
        for (int i = 0; i < axisTickCount; i++) {
            this.mArrayRadius[i] = Float.valueOf(((float) i) * div2);
            for (int i2 = 0; i2 < plotAgentNumber; i2++) {
                float add = MathHelper.getInstance().add(MathHelper.getInstance().add(sub, ((float) i2) * div), div);
                if (Float.compare(0.0f, this.mArrayRadius[i].floatValue()) == 0) {
                    this.mArrayDotX[i][i2] = Float.valueOf(centerX);
                    this.mArrayDotY[i][i2] = Float.valueOf(centerY);
                } else {
                    MathHelper.getInstance().calcArcEndPointXY(centerX, centerY, this.mArrayRadius[i].floatValue(), add);
                    this.mArrayDotX[i][i2] = Float.valueOf(MathHelper.getInstance().getPosX());
                    this.mArrayDotY[i][i2] = Float.valueOf(MathHelper.getInstance().getPosY());
                }
                if (i == 0) {
                    this.mArrayLabelAgent[i2] = Float.valueOf(add);
                }
                MathHelper.getInstance().calcArcEndPointXY(centerX, centerY, paintFontHeight, add);
                this.mArrayLabelX[i][i2] = Float.valueOf(MathHelper.getInstance().getPosX());
                this.mArrayLabelY[i][i2] = Float.valueOf(MathHelper.getInstance().getPosY());
            }
        }
    }

    private void renderDataArea(Canvas canvas) {
        float centerX = this.plotArea.getCenterX();
        float centerY = this.plotArea.getCenterY();
        int i = 0;
        for (RadarData radarData : this.mDataSet) {
            List<Double> linePoint = radarData.getLinePoint();
            int size = linePoint.size();
            if (size < 3) {
                Log.e(TAG, "这几个数据可不够，最少三个起步.");
            } else {
                Float[] fArr = new Float[size];
                Float[] fArr2 = new Float[size];
                int i2 = 0;
                for (Double d : linePoint) {
                    if (Double.compare(d.doubleValue(), 0.0d) == 0) {
                        fArr[i2] = Float.valueOf(this.plotArea.getCenterX());
                        fArr2[i2] = Float.valueOf(this.plotArea.getCenterY());
                        i2++;
                    } else {
                        MathHelper.getInstance().calcArcEndPointXY(centerX, centerY, (float) (((double) getRadius()) * Double.valueOf((d.doubleValue() - ((double) this.dataAxis.getAxisMin())) / ((double) this.dataAxis.getAxisRange())).doubleValue()), this.mArrayLabelAgent[i2].floatValue());
                        fArr[i2] = Float.valueOf(MathHelper.getInstance().getPosX());
                        fArr2[i2] = Float.valueOf(MathHelper.getInstance().getPosY());
                        i2++;
                    }
                }
                switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$DataAreaStyle()[radarData.getAreaStyle().ordinal()]) {
                    case 1:
                        drawDataPath(canvas, radarData, fArr, fArr2, i);
                        break;
                    case 2:
                        renderDataLine(canvas, radarData, fArr, fArr2, i);
                        break;
                    default:
                        Log.e(TAG, "这类型不认识.");
                        break;
                }
                i++;
            }
        }
    }

    private void renderDataLine(Canvas canvas, RadarData radarData, Float[] fArr, Float[] fArr2, int i) {
        float f = 0.0f;
        int i2 = 0;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        while (i2 < fArr.length) {
            float floatValue;
            float f5;
            if (i2 == 0) {
                f4 = fArr[i2].floatValue();
                floatValue = fArr2[i2].floatValue();
                f = floatValue;
                f5 = f4;
            } else {
                DrawHelper.getInstance().drawLine(radarData.getLineStyle(), f4, f, fArr[i2].floatValue(), fArr2[i2].floatValue(), canvas, radarData.getPlotLine().getLinePaint());
                f5 = fArr[i2].floatValue();
                f = fArr2[i2].floatValue();
                floatValue = f2;
                f4 = f3;
            }
            i2++;
            f2 = floatValue;
            f3 = f4;
            f4 = f5;
        }
        DrawHelper.getInstance().drawLine(radarData.getLineStyle(), f4, f, f3, f2, canvas, radarData.getPlotLine().getLinePaint());
        for (int i3 = 0; i3 < fArr.length; i3++) {
            renderDotAndLabel(canvas, radarData, fArr[i3].floatValue(), fArr2[i3].floatValue(), i, i3);
        }
    }

    private void drawDataPath(Canvas canvas, RadarData radarData, Float[] fArr, Float[] fArr2, int i) {
        int i2;
        this.mRdPath.reset();
        float f = 0.0f;
        float f2 = 0.0f;
        for (i2 = 0; i2 < fArr.length; i2++) {
            if (i2 == 0) {
                f2 = fArr[i2].floatValue();
                f = fArr2[i2].floatValue();
                this.mRdPath.moveTo(f2, f);
            } else {
                this.mRdPath.lineTo(fArr[i2].floatValue(), fArr2[i2].floatValue());
            }
        }
        this.mRdPath.lineTo(f2, f);
        this.mRdPath.close();
        i2 = radarData.getPlotLine().getLinePaint().getAlpha();
        radarData.getPlotLine().getLinePaint().setAlpha(this.mAreaAlpha);
        canvas.drawPath(this.mRdPath, radarData.getPlotLine().getLinePaint());
        radarData.getPlotLine().getLinePaint().setAlpha(i2);
        for (int i3 = 0; i3 < fArr.length; i3++) {
            renderDotAndLabel(canvas, radarData, fArr[i3].floatValue(), fArr2[i3].floatValue(), i, i3);
        }
    }

    private void renderDotAndLabel(Canvas canvas, RadarData radarData, float f, float f2, int i, int i2) {
        PlotLine plotLine = radarData.getPlotLine();
        float itemLabelRotateAngle = radarData.getItemLabelRotateAngle();
        if (!plotLine.getDotStyle().equals(DotStyle.HIDE)) {
            PlotDot plotDot = plotLine.getPlotDot();
            float dotRadius = plotDot.getDotRadius();
            PlotDotRender.getInstance().renderDot(canvas, plotDot, f, f2, radarData.getPlotLine().getDotPaint());
            savePointRecord(i, i2, f, f2, f - dotRadius, f2 - dotRadius, f + dotRadius, dotRadius + f2);
        }
        if (radarData.getLabelVisible()) {
            DrawHelper.getInstance().drawRotateText(getFormatterDotLabel(((Double) radarData.getLinePoint().get(i2)).doubleValue()), f, f2, itemLabelRotateAngle, canvas, radarData.getPlotLine().getDotLabelPaint());
        }
    }

    protected void renderPlot(Canvas canvas) {
        if (validateParams()) {
            calcAllPoints();
            renderGridLines(canvas);
            renderAxisLines(canvas);
            renderDataArea(canvas);
            renderAxisLabels(canvas);
            this.plotLegend.renderRdKey(canvas, this.mDataSet);
            clearArray();
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
