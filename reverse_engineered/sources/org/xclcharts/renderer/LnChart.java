package org.xclcharts.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PointF;
import android.util.Log;
import java.util.List;
import org.xclcharts.chart.CustomLineData;
import org.xclcharts.common.CurveHelper;
import org.xclcharts.common.MathHelper;
import org.xclcharts.common.PointHelper;
import org.xclcharts.event.click.PointPosition;
import org.xclcharts.renderer.XEnum.AxisLocation;
import org.xclcharts.renderer.XEnum.BarCenterStyle;
import org.xclcharts.renderer.XEnum.HorizontalAlign;
import org.xclcharts.renderer.XEnum.LegendType;
import org.xclcharts.renderer.XEnum.VerticalAlign;
import org.xclcharts.renderer.info.AnchorDataPoint;
import org.xclcharts.renderer.info.PlotAxisTick;
import org.xclcharts.renderer.line.PlotCustomLine;

public class LnChart extends AxesChart {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$AxisLocation = null;
    private static final String TAG = "LnChart";
    private PointF[] BezierControls;
    private List<AnchorDataPoint> mAnchorSet;
    protected BarCenterStyle mBarCenterStyle = BarCenterStyle.TICKMARKS;
    protected PlotCustomLine mCustomLine = null;
    protected boolean mXCoordFirstTickmarksBegin = false;

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$AxisLocation() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$AxisLocation;
        if (iArr == null) {
            iArr = new int[AxisLocation.values().length];
            try {
                iArr[AxisLocation.BOTTOM.ordinal()] = 2;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[AxisLocation.HORIZONTAL_CENTER.ordinal()] = 5;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[AxisLocation.LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[AxisLocation.RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[AxisLocation.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[AxisLocation.VERTICAL_CENTER.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$AxisLocation = iArr;
        }
        return iArr;
    }

    public LnChart() {
        if (this.plotLegend != null) {
            this.plotLegend.show();
            this.plotLegend.setType(LegendType.ROW);
            this.plotLegend.setHorizontalAlign(HorizontalAlign.LEFT);
            this.plotLegend.setVerticalAlign(VerticalAlign.TOP);
            this.plotLegend.hideBox();
        }
        categoryAxisDefaultSetting();
        dataAxisDefaultSetting();
    }

    public float getVPValPosition(double d) {
        return sub(this.plotArea.getBottom(), mul(getPlotScreenHeight(), div((float) MathHelper.getInstance().sub(d, (double) this.dataAxis.getAxisMin()), this.dataAxis.getAxisRange())));
    }

    protected float getLnXValPosition(double d, double d2, double d3) {
        return add(this.plotArea.getLeft(), mul(getPlotScreenWidth(), (float) MathHelper.getInstance().div(MathHelper.getInstance().sub(d, d3), MathHelper.getInstance().sub(d2, d3))));
    }

    private float getVPDataAxisStdY() {
        if (this.dataAxis.getAxisStdStatus()) {
            return getVPValPosition((double) this.dataAxis.getAxisStd());
        }
        return this.plotArea.getBottom();
    }

    protected float getAxisYPos(AxisLocation axisLocation) {
        if (this.dataAxis.getAxisStdStatus() && this.categoryAxis.getAxisBuildStdStatus()) {
            return getVPDataAxisStdY();
        }
        return super.getAxisYPos(axisLocation);
    }

    public void setCustomLines(List<CustomLineData> list) {
        if (this.mCustomLine == null) {
            this.mCustomLine = new PlotCustomLine();
        }
        this.mCustomLine.setCustomLines(list);
    }

    protected void drawClipDataAxisGridlines(Canvas canvas) {
        float f = 0.0f;
        float f2 = 0.0f;
        int aixTickCount = this.dataAxis.getAixTickCount();
        if (aixTickCount == 0) {
            Log.e(TAG, "数据源个数为0!");
            return;
        }
        int i;
        float axisYPos;
        float left;
        if (1 == aixTickCount) {
            i = aixTickCount - 1;
        } else {
            i = aixTickCount;
        }
        AxisLocation dataAxisLocation = getDataAxisLocation();
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$AxisLocation()[dataAxisLocation.ordinal()]) {
            case 1:
            case 2:
            case 5:
                f = getVerticalXSteps(i);
                axisYPos = getAxisYPos(dataAxisLocation);
                left = this.plotArea.getLeft();
                break;
            case 3:
            case 4:
            case 6:
                f2 = getVerticalYSteps(i);
                float axisXPos = getAxisXPos(dataAxisLocation);
                axisYPos = this.plotArea.getBottom();
                left = axisXPos;
                break;
            default:
                Log.e(TAG, "未知的枚举类型 .");
                axisYPos = 0.0f;
                left = 0.0f;
                break;
        }
        this.mLstDataTick.clear();
        for (int i2 = 0; i2 < aixTickCount + 1; i2++) {
            double add;
            switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$AxisLocation()[dataAxisLocation.ordinal()]) {
                case 1:
                case 2:
                case 5:
                    float add2 = add(this.plotArea.getLeft(), mul((float) i2, f));
                    drawVerticalGridLines(canvas, this.plotArea.getTop(), this.plotArea.getBottom(), i2, aixTickCount + 1, f, add2);
                    add = (double) MathHelper.getInstance().add(this.dataAxis.getAxisMin(), mul((float) i2, (float) this.dataAxis.getAxisSteps()));
                    this.mLstDataTick.add(new PlotAxisTick(i2, add2, axisYPos, Double.toString(add)));
                    break;
                case 3:
                case 4:
                case 6:
                    float sub = sub(this.plotArea.getBottom(), mul((float) i2, f2));
                    drawHorizontalGridLines(canvas, this.plotArea.getLeft(), this.plotArea.getRight(), i2, aixTickCount + 1, f2, sub);
                    add = (double) MathHelper.getInstance().add(this.dataAxis.getAxisMin(), mul((float) i2, (float) this.dataAxis.getAxisSteps()));
                    this.mLstDataTick.add(new PlotAxisTick(i2, left, sub, Double.toString(add)));
                    break;
                default:
                    break;
            }
        }
    }

    protected int getCategoryAxisCount() {
        int size = this.categoryAxis.getDataSet().size();
        if (size == 0) {
            Log.w(TAG, "分类轴数据源为0!");
            return 0;
        } else if (1 == size) {
            return size;
        } else {
            if (!this.mXCoordFirstTickmarksBegin) {
                return size - 1;
            }
            if (BarCenterStyle.SPACE != this.mBarCenterStyle) {
                return size + 1;
            }
            return size;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected void drawClipCategoryAxisGridlines(android.graphics.Canvas r31) {
        /*
        r30 = this;
        r0 = r30;
        r2 = r0.categoryAxis;
        r28 = r2.getDataSet();
        if (r28 != 0) goto L_0x000b;
    L_0x000a:
        return;
    L_0x000b:
        r18 = 0;
        r8 = 0;
        r2 = 0;
        r7 = r28.size();
        if (r7 != 0) goto L_0x001d;
    L_0x0015:
        r2 = "LnChart";
        r3 = "分类轴数据源为0!";
        android.util.Log.w(r2, r3);
        goto L_0x000a;
    L_0x001d:
        r3 = 1;
        if (r3 != r7) goto L_0x0021;
    L_0x0020:
        r2 = 1;
    L_0x0021:
        r3 = r30.getCategoryAxisCount();
        r29 = r30.getCategoryAxisLocation();
        r4 = org.xclcharts.renderer.XEnum.AxisLocation.LEFT;
        r0 = r29;
        if (r4 == r0) goto L_0x003b;
    L_0x002f:
        r4 = org.xclcharts.renderer.XEnum.AxisLocation.RIGHT;
        r0 = r29;
        if (r4 == r0) goto L_0x003b;
    L_0x0035:
        r4 = org.xclcharts.renderer.XEnum.AxisLocation.VERTICAL_CENTER;
        r0 = r29;
        if (r4 != r0) goto L_0x0076;
    L_0x003b:
        r0 = r30;
        r8 = r0.getVerticalYSteps(r3);
        r0 = r30;
        r1 = r29;
        r11 = r0.getAxisXPos(r1);
        r0 = r30;
        r3 = r0.plotArea;
        r22 = r3.getBottom();
    L_0x0051:
        r0 = r30;
        r3 = r0.mLstCateTick;
        r3.clear();
        r10 = 1;
        r6 = 0;
        r27 = r2;
    L_0x005c:
        if (r6 >= r7) goto L_0x000a;
    L_0x005e:
        r2 = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$AxisLocation();
        r3 = r29.ordinal();
        r2 = r2[r3];
        switch(r2) {
            case 1: goto L_0x0121;
            case 2: goto L_0x0121;
            case 3: goto L_0x008d;
            case 4: goto L_0x008d;
            case 5: goto L_0x0121;
            case 6: goto L_0x008d;
            default: goto L_0x006b;
        };
    L_0x006b:
        r16 = r10;
    L_0x006d:
        r2 = r27 + 1;
        r10 = r16;
    L_0x0071:
        r6 = r6 + 1;
        r27 = r2;
        goto L_0x005c;
    L_0x0076:
        r0 = r30;
        r18 = r0.getVerticalXSteps(r3);
        r0 = r30;
        r1 = r29;
        r22 = r0.getAxisYPos(r1);
        r0 = r30;
        r3 = r0.plotArea;
        r11 = r3.getLeft();
        goto L_0x0051;
    L_0x008d:
        r0 = r30;
        r2 = r0.mXCoordFirstTickmarksBegin;
        if (r2 == 0) goto L_0x00ce;
    L_0x0093:
        r0 = r30;
        r2 = r0.plotArea;
        r2 = r2.getBottom();
        r3 = r27 + 1;
        r3 = (float) r3;
        r0 = r30;
        r3 = r0.mul(r3, r8);
        r0 = r30;
        r9 = r0.sub(r2, r3);
    L_0x00aa:
        r0 = r30;
        r2 = r0.plotArea;
        r4 = r2.getLeft();
        r0 = r30;
        r2 = r0.plotArea;
        r5 = r2.getRight();
        r2 = r30;
        r3 = r31;
        r2.drawHorizontalGridLines(r3, r4, r5, r6, r7, r8, r9);
        r0 = r30;
        r2 = r0.categoryAxis;
        r2 = r2.isShowAxisLabels();
        if (r2 != 0) goto L_0x00e6;
    L_0x00cb:
        r2 = r27;
        goto L_0x0071;
    L_0x00ce:
        r0 = r30;
        r2 = r0.plotArea;
        r2 = r2.getBottom();
        r0 = r27;
        r3 = (float) r0;
        r0 = r30;
        r3 = r0.mul(r3, r8);
        r0 = r30;
        r9 = r0.sub(r2, r3);
        goto L_0x00aa;
    L_0x00e6:
        r0 = r30;
        r2 = r0.mXCoordFirstTickmarksBegin;
        if (r2 == 0) goto L_0x01d0;
    L_0x00ec:
        r2 = org.xclcharts.renderer.XEnum.BarCenterStyle.SPACE;
        r0 = r30;
        r3 = r0.mBarCenterStyle;
        if (r2 != r3) goto L_0x01d0;
    L_0x00f4:
        r2 = r7 + -1;
        if (r6 != r2) goto L_0x01cd;
    L_0x00f8:
        r2 = 0;
    L_0x00f9:
        r3 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r0 = r30;
        r3 = r0.div(r8, r3);
        r0 = r30;
        r15 = r0.add(r9, r3);
        r16 = r2;
    L_0x0109:
        r0 = r30;
        r2 = r0.mLstCateTick;
        r10 = new org.xclcharts.renderer.info.PlotAxisTick;
        r0 = r28;
        r13 = r0.get(r6);
        r13 = (java.lang.String) r13;
        r12 = r9;
        r14 = r11;
        r10.<init>(r11, r12, r13, r14, r15, r16);
        r2.add(r10);
        goto L_0x006d;
    L_0x0121:
        r0 = r30;
        r2 = r0.mXCoordFirstTickmarksBegin;
        if (r2 == 0) goto L_0x0169;
    L_0x0127:
        r0 = r30;
        r2 = r0.plotArea;
        r2 = r2.getLeft();
        r3 = r27 + 1;
        r3 = (float) r3;
        r0 = r30;
        r1 = r18;
        r3 = r0.mul(r3, r1);
        r0 = r30;
        r19 = r0.add(r2, r3);
    L_0x0140:
        r0 = r30;
        r2 = r0.plotArea;
        r14 = r2.getTop();
        r0 = r30;
        r2 = r0.plotArea;
        r15 = r2.getBottom();
        r12 = r30;
        r13 = r31;
        r16 = r6;
        r17 = r7;
        r12.drawVerticalGridLines(r13, r14, r15, r16, r17, r18, r19);
        r0 = r30;
        r2 = r0.categoryAxis;
        r2 = r2.isShowAxisLabels();
        if (r2 != 0) goto L_0x0183;
    L_0x0165:
        r2 = r27;
        goto L_0x0071;
    L_0x0169:
        r0 = r30;
        r2 = r0.plotArea;
        r2 = r2.getLeft();
        r0 = r27;
        r3 = (float) r0;
        r0 = r30;
        r1 = r18;
        r3 = r0.mul(r3, r1);
        r0 = r30;
        r19 = r0.add(r2, r3);
        goto L_0x0140;
    L_0x0183:
        r0 = r30;
        r2 = r0.mXCoordFirstTickmarksBegin;
        if (r2 == 0) goto L_0x01c8;
    L_0x0189:
        r2 = org.xclcharts.renderer.XEnum.BarCenterStyle.SPACE;
        r0 = r30;
        r3 = r0.mBarCenterStyle;
        if (r2 != r3) goto L_0x01c8;
    L_0x0191:
        r2 = r7 + -1;
        if (r6 != r2) goto L_0x0196;
    L_0x0195:
        r10 = 0;
    L_0x0196:
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r0 = r30;
        r1 = r18;
        r2 = r0.div(r1, r2);
        r0 = r30;
        r1 = r19;
        r24 = r0.sub(r1, r2);
        r26 = r10;
    L_0x01aa:
        r0 = r30;
        r2 = r0.mLstCateTick;
        r20 = new org.xclcharts.renderer.info.PlotAxisTick;
        r0 = r28;
        r23 = r0.get(r6);
        r23 = (java.lang.String) r23;
        r21 = r19;
        r25 = r22;
        r20.<init>(r21, r22, r23, r24, r25, r26);
        r0 = r20;
        r2.add(r0);
        r16 = r26;
        goto L_0x006d;
    L_0x01c8:
        r26 = r10;
        r24 = r19;
        goto L_0x01aa;
    L_0x01cd:
        r2 = r10;
        goto L_0x00f9;
    L_0x01d0:
        r16 = r10;
        r15 = r9;
        goto L_0x0109;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xclcharts.renderer.LnChart.drawClipCategoryAxisGridlines(android.graphics.Canvas):void");
    }

    public boolean isPlotClickArea(float f, float f2) {
        if (!getListenItemClickStatus() || Float.compare(f, getLeft()) == -1 || Float.compare(f, getRight()) == 1 || Float.compare(f2, getPlotArea().getTop()) == -1 || Float.compare(f2, getPlotArea().getBottom()) == 1) {
            return false;
        }
        return true;
    }

    public PointPosition getPositionRecord(float f, float f2) {
        return getPointRecord(f, f2);
    }

    protected void renderBezierCurveLine(Canvas canvas, Paint paint, Path path, List<PointF> list) {
        if (this.BezierControls == null) {
            this.BezierControls = new PointF[2];
        }
        paint.setStyle(Style.STROKE);
        int size = list.size();
        if (size > 2) {
            PointF percent;
            if (size == 3) {
                if (path == null) {
                    path = new Path();
                }
                path.moveTo(((PointF) list.get(0)).x, ((PointF) list.get(0)).y);
                percent = PointHelper.percent((PointF) list.get(1), 0.5f, (PointF) list.get(2), 0.8f);
                path.quadTo(percent.x, percent.y, ((PointF) list.get(2)).x, ((PointF) list.get(2)).y);
                canvas.drawPath(path, paint);
                path.reset();
                return;
            }
            float bottom = this.plotArea.getBottom();
            int i = 0;
            Path path2 = path;
            while (i < size) {
                if (i >= 3) {
                    if (((PointF) list.get(i - 1)).y < bottom || ((PointF) list.get(i)).y < bottom) {
                        CurveHelper.curve3((PointF) list.get(i - 2), (PointF) list.get(i - 1), (PointF) list.get(i - 3), (PointF) list.get(i), this.BezierControls);
                        renderBezierCurvePath(canvas, paint, path2, (PointF) list.get(i - 2), (PointF) list.get(i - 1), this.BezierControls);
                    } else {
                        Path path3;
                        if (path2 == null) {
                            path3 = new Path();
                        } else {
                            path3 = path2;
                        }
                        path3.reset();
                        path3.moveTo(((PointF) list.get(i - 2)).x, ((PointF) list.get(i - 2)).y);
                        if (((PointF) list.get(i - 2)).y >= bottom) {
                            path3.lineTo(((PointF) list.get(i - 1)).x, ((PointF) list.get(i - 1)).y);
                        } else {
                            CurveHelper.curve3((PointF) list.get(i - 2), (PointF) list.get(i - 1), (PointF) list.get(i - 3), (PointF) list.get(i), this.BezierControls);
                            path3.quadTo(this.BezierControls[0].x, this.BezierControls[0].y, ((PointF) list.get(i - 1)).x, ((PointF) list.get(i - 1)).y);
                            canvas.drawPath(path3, paint);
                            path3.reset();
                        }
                        canvas.drawLine(((PointF) list.get(i - 1)).x, ((PointF) list.get(i - 1)).y, ((PointF) list.get(i)).x, ((PointF) list.get(i)).y, paint);
                        path2 = path3;
                    }
                }
                i++;
            }
            if (size > 3) {
                percent = (PointF) list.get(size - 1);
                CurveHelper.curve3((PointF) list.get(size - 2), percent, (PointF) list.get(size - 3), percent, this.BezierControls);
                renderBezierCurvePath(canvas, paint, path2, (PointF) list.get(size - 2), (PointF) list.get(size - 1), this.BezierControls);
            }
        }
    }

    private void renderBezierCurvePath(Canvas canvas, Paint paint, Path path, PointF pointF, PointF pointF2, PointF[] pointFArr) {
        if (path == null) {
            path = new Path();
        }
        path.reset();
        path.moveTo(pointF.x, pointF.y);
        bezierCurvePathAxisMinValue(path, pointF, pointF2, pointFArr);
        canvas.drawPath(path, paint);
        path.reset();
    }

    protected void bezierCurvePathAxisMinValue(Path path, PointF pointF, PointF pointF2, PointF[] pointFArr) {
        float bottom = this.plotArea.getBottom();
        if (pointF.y >= bottom && pointF2.y >= bottom) {
            path.lineTo(pointF2.x, pointF2.y);
        } else if (pointFArr[0].y >= bottom && pointFArr[1].y >= bottom) {
            path.lineTo(pointF2.x, pointF2.y);
        } else if (pointFArr[0].y >= bottom && pointFArr[1].y < bottom) {
            path.cubicTo(pointFArr[0].x, bottom, pointFArr[1].x, pointFArr[1].y, pointF2.x, pointF2.y);
        } else if (pointFArr[0].y >= bottom || pointFArr[1].y < bottom) {
            path.cubicTo(pointFArr[0].x, pointFArr[0].y, pointFArr[1].x, pointFArr[1].y, pointF2.x, pointF2.y);
        } else {
            path.cubicTo(pointFArr[0].x, pointFArr[0].y, pointFArr[1].x, bottom, pointF2.x, pointF2.y);
        }
    }

    public void setAnchorDataPoint(List<AnchorDataPoint> list) {
        this.mAnchorSet = list;
    }

    public List<AnchorDataPoint> getAnchorDataPoint() {
        return this.mAnchorSet;
    }
}
