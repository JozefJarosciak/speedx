package org.xclcharts.chart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Shader;
import android.util.Log;
import com.alibaba.fastjson.asm.Opcodes;
import java.util.ArrayList;
import java.util.List;
import org.xclcharts.common.CurveHelper;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.PointHelper;
import org.xclcharts.renderer.LnChart;
import org.xclcharts.renderer.XEnum.ChartType;
import org.xclcharts.renderer.XEnum.CrurveLineStyle;
import org.xclcharts.renderer.XEnum.Direction;
import org.xclcharts.renderer.XEnum.DotStyle;
import org.xclcharts.renderer.line.DotInfo;
import org.xclcharts.renderer.line.PlotDot;
import org.xclcharts.renderer.line.PlotDotRender;
import org.xclcharts.renderer.line.PlotLine;

public class AreaChart extends LnChart {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$CrurveLineStyle = null;
    private static final String TAG = "AreaChart";
    private final int Y_MAX = 1;
    private final int Y_MIN = 0;
    private int mAreaAlpha = 100;
    private PointF[] mBezierControls = new PointF[2];
    private CrurveLineStyle mCrurveLineStyle = CrurveLineStyle.BEZIERCURVE;
    protected List<AreaData> mDataSet;
    private List<DotInfo> mLstDotInfo = new ArrayList();
    private List<LnData> mLstKey = new ArrayList();
    private List<PointF> mLstPathPoints = new ArrayList();
    private List<PointF> mLstPoints = new ArrayList();
    protected Paint mPaintAreaFill = null;
    private Path mPathArea = null;

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$CrurveLineStyle() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$CrurveLineStyle;
        if (iArr == null) {
            iArr = new int[CrurveLineStyle.values().length];
            try {
                iArr[CrurveLineStyle.BEELINE.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[CrurveLineStyle.BEZIERCURVE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$CrurveLineStyle = iArr;
        }
        return iArr;
    }

    public AreaChart() {
        categoryAxisDefaultSetting();
        dataAxisDefaultSetting();
    }

    public ChartType getType() {
        return ChartType.AREA;
    }

    public Paint getAreaFillPaint() {
        if (this.mPaintAreaFill == null) {
            this.mPaintAreaFill = new Paint();
            this.mPaintAreaFill.setStyle(Style.FILL);
            this.mPaintAreaFill.setAntiAlias(true);
            this.mPaintAreaFill.setColor(Color.rgb(73, Opcodes.IRETURN, 72));
        }
        return this.mPaintAreaFill;
    }

    protected void categoryAxisDefaultSetting() {
        if (this.categoryAxis != null) {
            this.categoryAxis.setHorizontalTickAlign(Align.CENTER);
        }
    }

    protected void dataAxisDefaultSetting() {
        if (this.dataAxis != null) {
            this.dataAxis.setHorizontalTickAlign(Align.LEFT);
        }
    }

    public void setCategories(List<String> list) {
        if (this.categoryAxis != null) {
            this.categoryAxis.setDataBuilding(list);
        }
    }

    public void setDataSource(List<AreaData> list) {
        this.mDataSet = list;
    }

    public List<AreaData> getDataSource() {
        return this.mDataSet;
    }

    public void setAreaAlpha(int i) {
        this.mAreaAlpha = i;
    }

    public void setCrurveLineStyle(CrurveLineStyle crurveLineStyle) {
        this.mCrurveLineStyle = crurveLineStyle;
    }

    public CrurveLineStyle getCrurveLineStyle() {
        return this.mCrurveLineStyle;
    }

    private boolean calcAllPoints(AreaData areaData, List<DotInfo> list, List<PointF> list2, List<PointF> list3) {
        if (areaData == null) {
            Log.w(TAG, "传入的数据序列参数为空.");
            return false;
        }
        List linePoint = areaData.getLinePoint();
        if (linePoint == null) {
            Log.w(TAG, "线数据集合为空.");
            return false;
        }
        float left = this.plotArea.getLeft();
        float bottom = this.plotArea.getBottom();
        float div = div(getPlotScreenWidth(), (float) (this.categoryAxis.getDataSet().size() - 1));
        int size = linePoint.size();
        if (size <= 0) {
            return false;
        }
        float f = bottom;
        int i = 0;
        while (i < size) {
            double doubleValue = ((Double) linePoint.get(i)).doubleValue();
            float add = add(this.plotArea.getLeft(), mul((float) i, div));
            left = getVPValPosition(doubleValue);
            if (i == 0) {
                if (2 < size && Double.compare(doubleValue, (double) this.dataAxis.getAxisMin()) != 0) {
                    list3.add(new PointF(this.plotArea.getLeft(), this.plotArea.getBottom()));
                }
                list3.add(new PointF(add, left));
                list2.add(new PointF(add, left));
            }
            list3.add(new PointF(add, left));
            list2.add(new PointF(add, left));
            list.add(new DotInfo(Double.valueOf(doubleValue), add, left));
            i++;
            f = left;
            left = add;
        }
        if (size > 2) {
            list3.add(new PointF(left, f));
            if (Double.compare(((Double) linePoint.get(size - 1)).doubleValue(), (double) this.dataAxis.getAxisMin()) != 0) {
                list3.add(new PointF(left, this.plotArea.getBottom()));
            }
        }
        return true;
    }

    private boolean renderBezierArea(Canvas canvas, Paint paint, Path path, AreaData areaData, List<PointF> list) {
        int size = list.size();
        if (size < 3) {
            return false;
        }
        paint.setColor(areaData.getAreaFillColor());
        Shader linearGradient;
        if (size == 3) {
            if (path == null) {
                path = new Path();
            }
            path.moveTo(((PointF) list.get(0)).x, this.plotArea.getBottom());
            path.lineTo(((PointF) list.get(0)).x, ((PointF) list.get(0)).y);
            PointF percent = PointHelper.percent((PointF) list.get(1), 0.5f, (PointF) list.get(2), 0.8f);
            path.quadTo(percent.x, percent.y, ((PointF) list.get(2)).x, ((PointF) list.get(2)).y);
            path.lineTo(((PointF) list.get(2)).x, this.plotArea.getBottom());
            path.close();
            if (areaData.getApplayGradient()) {
                if (areaData.getGradientDirection() == Direction.VERTICAL) {
                    linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, this.plotArea.getBottom() - getLineMaxMinY(1, list), areaData.getAreaBeginColor(), areaData.getAreaEndColor(), areaData.getGradientMode());
                } else {
                    linearGradient = new LinearGradient(this.plotArea.getLeft(), this.plotArea.getBottom(), ((PointF) list.get(2)).x, getLineMaxMinY(0, list), areaData.getAreaBeginColor(), areaData.getAreaEndColor(), areaData.getGradientMode());
                }
                paint.setShader(linearGradient);
            } else {
                paint.setShader(null);
            }
            canvas.drawPath(path, paint);
            path.reset();
            return true;
        }
        paint.setAlpha(this.mAreaAlpha);
        path.moveTo(this.plotArea.getLeft(), this.plotArea.getBottom());
        float bottom = this.plotArea.getBottom();
        int i = 0;
        Path path2 = path;
        while (i < size) {
            if (i >= 3) {
                if (((PointF) list.get(i - 1)).y < bottom || ((PointF) list.get(i)).y < bottom) {
                    CurveHelper.curve3((PointF) list.get(i - 2), (PointF) list.get(i - 1), (PointF) list.get(i - 3), (PointF) list.get(i), this.mBezierControls);
                    bezierCurvePathAxisMinValue(path2, (PointF) list.get(i - 2), (PointF) list.get(i - 1), this.mBezierControls);
                } else if (i != size - 1) {
                    if (path2 == null) {
                        Path path3 = new Path();
                        path3.moveTo(((PointF) list.get(i - 2)).x, ((PointF) list.get(i - 2)).y);
                        path2 = path3;
                    } else {
                        path2.lineTo(((PointF) list.get(i - 2)).x, ((PointF) list.get(i - 2)).y);
                    }
                    if (((PointF) list.get(i - 2)).y >= bottom) {
                        path2.moveTo(((PointF) list.get(i - 1)).x, ((PointF) list.get(i - 1)).y);
                    } else {
                        CurveHelper.curve3((PointF) list.get(i - 2), (PointF) list.get(i - 1), (PointF) list.get(i - 3), (PointF) list.get(i), this.mBezierControls);
                        path2.quadTo(this.mBezierControls[0].x, this.mBezierControls[0].y, ((PointF) list.get(i - 1)).x, ((PointF) list.get(i - 1)).y);
                    }
                    path2.close();
                    if (areaData.getApplayGradient()) {
                        if (areaData.getGradientDirection() == Direction.VERTICAL) {
                            linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, getLineMaxMinY(1, list), areaData.getAreaBeginColor(), areaData.getAreaEndColor(), areaData.getGradientMode());
                        } else {
                            linearGradient = new LinearGradient(this.plotArea.getLeft(), this.plotArea.getBottom(), ((PointF) list.get(i - 1)).x, getLineMaxMinY(0, list), areaData.getAreaBeginColor(), areaData.getAreaEndColor(), areaData.getGradientMode());
                        }
                        paint.setShader(linearGradient);
                    } else {
                        paint.setShader(null);
                    }
                    canvas.drawPath(path2, paint);
                    path2.reset();
                    path2.moveTo(((PointF) list.get(i)).x, ((PointF) list.get(i)).y);
                }
            }
            i++;
        }
        PointF pointF = (PointF) list.get(size - 1);
        if (((PointF) list.get(size - 2)).y >= bottom) {
            CurveHelper.curve3((PointF) list.get(size - 3), pointF, (PointF) list.get(size - 4), pointF, this.mBezierControls);
            bezierCurvePathAxisMinValue(path2, (PointF) list.get(size - 3), pointF, this.mBezierControls);
        } else {
            CurveHelper.curve3((PointF) list.get(size - 2), pointF, (PointF) list.get(size - 3), pointF, this.mBezierControls);
            bezierCurvePathAxisMinValue(path2, (PointF) list.get(size - 2), (PointF) list.get(size - 1), this.mBezierControls);
        }
        path2.close();
        if (areaData.getApplayGradient()) {
            if (areaData.getGradientDirection() == Direction.VERTICAL) {
                linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, getLineMaxMinY(1, list), areaData.getAreaBeginColor(), areaData.getAreaEndColor(), areaData.getGradientMode());
            } else {
                linearGradient = new LinearGradient(this.plotArea.getLeft(), this.plotArea.getBottom(), pointF.x, getLineMaxMinY(0, list), areaData.getAreaBeginColor(), areaData.getAreaEndColor(), areaData.getGradientMode());
            }
            paint.setShader(linearGradient);
        } else {
            paint.setShader(null);
        }
        canvas.drawPath(path2, paint);
        path2.reset();
        return true;
    }

    private boolean renderArea(Canvas canvas, Paint paint, Path path, AreaData areaData, List<PointF> list) {
        int size = list.size();
        if (size < 3) {
            return false;
        }
        paint.setColor(areaData.getAreaFillColor());
        if (areaData.getApplayGradient()) {
            Shader linearGradient;
            if (areaData.getGradientDirection() == Direction.VERTICAL) {
                linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, getLineMaxMinY(1, list), areaData.getAreaBeginColor(), areaData.getAreaEndColor(), areaData.getGradientMode());
            } else {
                linearGradient = new LinearGradient(this.plotArea.getLeft(), this.plotArea.getBottom(), ((PointF) list.get(size - 1)).x, getLineMaxMinY(0, list), areaData.getAreaBeginColor(), areaData.getAreaEndColor(), areaData.getGradientMode());
            }
            paint.setShader(linearGradient);
        } else {
            paint.setShader(null);
        }
        paint.setAlpha(this.mAreaAlpha);
        if (size == 3) {
            if (path == null) {
                path = new Path();
            }
            path.moveTo(((PointF) list.get(0)).x, this.plotArea.getBottom());
            path.lineTo(((PointF) list.get(0)).x, ((PointF) list.get(0)).y);
            path.lineTo(((PointF) list.get(1)).x, ((PointF) list.get(1)).y);
            path.lineTo(((PointF) list.get(2)).x, ((PointF) list.get(2)).y);
            path.lineTo(((PointF) list.get(2)).x, this.plotArea.getBottom());
            path.close();
            canvas.drawPath(path, paint);
            path.reset();
            return true;
        }
        for (int i = 0; i < size; i++) {
            PointF pointF = (PointF) list.get(i);
            if (i == 0) {
                path.moveTo(pointF.x, pointF.y);
            } else {
                path.lineTo(pointF.x, pointF.y);
            }
        }
        path.close();
        canvas.drawPath(path, paint);
        path.reset();
        return true;
    }

    private boolean renderLine(Canvas canvas, AreaData areaData, List<PointF> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                PointF pointF = (PointF) list.get(i - 1);
                PointF pointF2 = (PointF) list.get(i);
                DrawHelper.getInstance().drawLine(areaData.getLineStyle(), pointF.x, pointF.y, pointF2.x, pointF2.y, canvas, areaData.getLinePaint());
            }
        }
        return true;
    }

    private boolean renderBezierCurveLine(Canvas canvas, Path path, AreaData areaData, List<PointF> list) {
        renderBezierCurveLine(canvas, areaData.getLinePaint(), path, list);
        return true;
    }

    private boolean renderDotAndLabel(Canvas canvas, AreaData areaData, int i, List<DotInfo> list) {
        float itemLabelRotateAngle = areaData.getItemLabelRotateAngle();
        PlotLine plotLine = areaData.getPlotLine();
        if (plotLine.getDotStyle().equals(DotStyle.HIDE) && !areaData.getLabelVisible()) {
            return true;
        }
        PlotDot plotDot = plotLine.getPlotDot();
        float dotRadius = plotDot.getDotRadius();
        int size = list.size();
        for (int i2 = 0; i2 < size; i2++) {
            DotInfo dotInfo = (DotInfo) list.get(i2);
            if (!plotLine.getDotStyle().equals(DotStyle.HIDE)) {
                PlotDotRender.getInstance().renderDot(canvas, plotDot, dotInfo.mX, dotInfo.mY, plotLine.getDotPaint());
                savePointRecord(i, i2, dotInfo.mX + this.mMoveX, dotInfo.mY + this.mMoveY, (dotInfo.mX - dotRadius) + this.mMoveX, (dotInfo.mY - dotRadius) + this.mMoveY, (dotInfo.mX + dotRadius) + this.mMoveX, (dotInfo.mY + dotRadius) + this.mMoveY);
            }
            drawAnchor(getAnchorDataPoint(), i, i2, canvas, dotInfo.mX, dotInfo.mY, dotRadius);
            if (areaData.getLabelVisible()) {
                areaData.getLabelOptions().drawLabel(canvas, plotLine.getDotLabelPaint(), getFormatterItemLabel(dotInfo.mValue.doubleValue()), dotInfo.mX, dotInfo.mY, itemLabelRotateAngle, areaData.getLineColor());
            }
        }
        return true;
    }

    private float getLineMaxMinY(int i, List<PointF> list) {
        float f = 0.0f;
        int size = list.size();
        int i2 = 0;
        float f2 = 0.0f;
        while (i2 < size) {
            float f3;
            if (1 == i) {
                if (f2 < ((PointF) list.get(i2)).y) {
                    float f4 = f;
                    f = ((PointF) list.get(i2)).y;
                    f3 = f4;
                }
                f3 = f;
                f = f2;
            } else {
                if (i == 0) {
                    if (i2 == 0) {
                        f3 = ((PointF) list.get(0)).y;
                        f = f2;
                    } else if (f > ((PointF) list.get(i2)).y) {
                        f3 = ((PointF) list.get(i2)).y;
                        f = f2;
                    }
                }
                f3 = f;
                f = f2;
            }
            i2++;
            f2 = f;
            f = f3;
        }
        if (1 == i) {
            return f2;
        }
        return f;
    }

    private boolean renderVerticalPlot(Canvas canvas) {
        if (this.mDataSet == null) {
            Log.e(TAG, "数据源为空.");
            return false;
        }
        if (this.mPathArea == null) {
            this.mPathArea = new Path();
        }
        int size = this.mDataSet.size();
        for (int i = 0; i < size; i++) {
            AreaData areaData = (AreaData) this.mDataSet.get(i);
            calcAllPoints(areaData, this.mLstDotInfo, this.mLstPoints, this.mLstPathPoints);
            switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$CrurveLineStyle()[getCrurveLineStyle().ordinal()]) {
                case 1:
                    renderArea(canvas, getAreaFillPaint(), this.mPathArea, areaData, this.mLstPathPoints);
                    renderLine(canvas, areaData, this.mLstPoints);
                    break;
                case 2:
                    renderBezierArea(canvas, getAreaFillPaint(), this.mPathArea, areaData, this.mLstPathPoints);
                    renderBezierCurveLine(canvas, this.mPathArea, areaData, this.mLstPoints);
                    break;
                default:
                    Log.e(TAG, "未知的枚举类型.");
                    continue;
            }
            renderDotAndLabel(canvas, areaData, i, this.mLstDotInfo);
            this.mLstKey.add(areaData);
            this.mLstDotInfo.clear();
            this.mLstPoints.clear();
            this.mLstPathPoints.clear();
        }
        return true;
    }

    protected void drawClipPlot(Canvas canvas) {
        if (renderVerticalPlot(canvas) && this.mCustomLine != null) {
            this.mCustomLine.setVerticalPlot(this.dataAxis, this.plotArea, getAxisScreenHeight());
            this.mCustomLine.renderVerticalCustomlinesDataAxis(canvas);
        }
    }

    protected void drawClipLegend(Canvas canvas) {
        this.plotLegend.renderLineKey(canvas, this.mLstKey);
        this.mLstKey.clear();
    }
}
