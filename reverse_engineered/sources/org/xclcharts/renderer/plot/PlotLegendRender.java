package org.xclcharts.renderer.plot;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.v4.view.ViewCompat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;
import org.xclcharts.chart.ArcLineData;
import org.xclcharts.chart.BarData;
import org.xclcharts.chart.BubbleData;
import org.xclcharts.chart.LnData;
import org.xclcharts.chart.PieData;
import org.xclcharts.chart.RadarData;
import org.xclcharts.chart.ScatterData;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.renderer.XChart;
import org.xclcharts.renderer.XEnum.DotStyle;
import org.xclcharts.renderer.XEnum.HorizontalAlign;
import org.xclcharts.renderer.XEnum.LegendType;
import org.xclcharts.renderer.XEnum.VerticalAlign;
import org.xclcharts.renderer.line.PlotDot;
import org.xclcharts.renderer.line.PlotDotRender;

public class PlotLegendRender extends PlotLegend {
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$HorizontalAlign;
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$LegendType;
    private static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$VerticalAlign;
    private final int BOX_LINE_SIZE = 5;
    private boolean mIsLnChart = false;
    private float mKeyLabelX = 0.0f;
    private float mKeyLabelY = 0.0f;
    private ArrayList<Integer> mLstColor = null;
    private ArrayList<PlotDot> mLstDotStyle = null;
    private ArrayList<String> mLstKey = null;
    LinkedHashMap<Integer, Integer> mMapID = new LinkedHashMap();
    private Paint mPaintLine = null;
    private PlotArea mPlotArea = null;
    private float mRectHeight = 0.0f;
    private float mRectWidth = 0.0f;
    EnumChartType mType = EnumChartType.AXIS;
    private XChart mXChart = null;

    enum EnumChartType {
        AXIS,
        CIR,
        LN,
        RD
    }

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

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$LegendType() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$LegendType;
        if (iArr == null) {
            iArr = new int[LegendType.values().length];
            try {
                iArr[LegendType.COLUMN.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[LegendType.ROW.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$LegendType = iArr;
        }
        return iArr;
    }

    static /* synthetic */ int[] $SWITCH_TABLE$org$xclcharts$renderer$XEnum$VerticalAlign() {
        int[] iArr = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$VerticalAlign;
        if (iArr == null) {
            iArr = new int[VerticalAlign.values().length];
            try {
                iArr[VerticalAlign.BOTTOM.ordinal()] = 3;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[VerticalAlign.MIDDLE.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[VerticalAlign.TOP.ordinal()] = 1;
            } catch (NoSuchFieldError e3) {
            }
            $SWITCH_TABLE$org$xclcharts$renderer$XEnum$VerticalAlign = iArr;
        }
        return iArr;
    }

    public PlotLegendRender(XChart xChart) {
        this.mXChart = xChart;
    }

    public void setXChart(XChart xChart) {
        this.mXChart = xChart;
    }

    private void initEnv() {
        this.mKeyLabelY = 0.0f;
        this.mKeyLabelX = 0.0f;
        this.mRectHeight = 0.0f;
        this.mRectWidth = 0.0f;
    }

    private float getLabelTextWidth(String str) {
        return DrawHelper.getInstance().getTextWidth(getPaint(), str);
    }

    private float getLabelTextHeight() {
        return DrawHelper.getInstance().getPaintFontHeight(getPaint());
    }

    public boolean renderBarKey(Canvas canvas, List<BarData> list) {
        if (!isShow()) {
            return false;
        }
        refreshLst();
        convertArrayBarKey(list);
        render(canvas);
        return true;
    }

    public void renderLineKey(Canvas canvas, List<LnData> list) {
        if (isShow()) {
            setLnChartStatus();
            refreshLst();
            convertArrayLineKey(list);
            render(canvas);
        }
    }

    public void renderPieKey(Canvas canvas, List<PieData> list) {
        if (isShow()) {
            refreshLst();
            this.mType = EnumChartType.CIR;
            convertArrayPieKey(list);
            render(canvas);
        }
    }

    public void renderRdKey(Canvas canvas, List<RadarData> list) {
        if (isShow()) {
            setLnChartStatus();
            refreshLst();
            convertArrayRadarKey(list);
            render(canvas);
        }
    }

    public void renderPointKey(Canvas canvas, List<ScatterData> list) {
        if (isShow()) {
            refreshLst();
            convertArrayPointKey(list);
            render(canvas);
        }
    }

    public void renderBubbleKey(Canvas canvas, List<BubbleData> list) {
        if (isShow()) {
            refreshLst();
            convertArrayBubbleKey(list);
            render(canvas);
        }
    }

    public void renderRoundBarKey(Canvas canvas, List<ArcLineData> list) {
        if (isShow()) {
            refreshLst();
            convertArrayArcLineKey(list);
            render(canvas);
        }
    }

    public void renderRangeBarKey(Canvas canvas, String str, int i) {
        if (isShow() && "" != str && str.length() != 0) {
            refreshLst();
            this.mLstKey.add(str);
            this.mLstColor.add(Integer.valueOf(i));
            PlotDot plotDot = new PlotDot();
            plotDot.setDotStyle(DotStyle.RECT);
            this.mLstDotStyle.add(plotDot);
            render(canvas);
        }
    }

    private void setLnChartStatus() {
        if (this.mPaintLine == null) {
            this.mPaintLine = new Paint(1);
        }
        this.mPaintLine.setStrokeWidth(2.0f);
        this.mIsLnChart = true;
    }

    private void render(Canvas canvas) {
        if (this.mXChart != null) {
            if (this.mPlotArea == null) {
                this.mPlotArea = this.mXChart.getPlotArea();
            }
            calcContentRect();
            getStartXY();
            drawLegend(canvas);
        }
    }

    private float getRectWidth() {
        float labelTextHeight = getLabelTextHeight();
        if (this.mIsLnChart) {
            return labelTextHeight * 2.0f;
        }
        return labelTextHeight + (labelTextHeight / 2.0f);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void calcContentRect() {
        /*
        r14 = this;
        r0 = r14.mLstDotStyle;
        if (r0 == 0) goto L_0x001b;
    L_0x0004:
        r0 = r14.mLstDotStyle;
        r0 = r0.size();
        r1 = r0;
    L_0x000b:
        r0 = r14.mLstKey;
        if (r0 == 0) goto L_0x001e;
    L_0x000f:
        r0 = r14.mLstKey;
        r0 = r0.size();
        r8 = r0;
    L_0x0016:
        if (r8 != 0) goto L_0x0021;
    L_0x0018:
        if (r1 != 0) goto L_0x0021;
    L_0x001a:
        return;
    L_0x001b:
        r0 = 0;
        r1 = r0;
        goto L_0x000b;
    L_0x001e:
        r0 = 0;
        r8 = r0;
        goto L_0x0016;
    L_0x0021:
        r0 = "";
        r7 = r14.getLabelTextHeight();
        r5 = 1;
        r0 = r14.mMapID;
        r0.clear();
        r0 = r14.mPlotArea;
        r0 = r0.getWidth();
        r2 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r3 = r14.mMargin;
        r2 = r2 * r3;
        r9 = r0 - r2;
        r10 = r14.getRectWidth();
        r4 = 0;
        r2 = 0;
        r0 = 0;
        r6 = r0;
        r3 = r7;
    L_0x0043:
        if (r6 < r8) goto L_0x0068;
    L_0x0045:
        r0 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r1 = r14.mMargin;
        r0 = r0 * r1;
        r0 = r0 + r2;
        r14.mRectWidth = r0;
        r0 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r1 = r14.mMargin;
        r0 = r0 * r1;
        r0 = r0 + r3;
        r14.mRectHeight = r0;
        r0 = org.xclcharts.renderer.XEnum.LegendType.COLUMN;
        r1 = r14.getType();
        if (r0 != r1) goto L_0x001a;
    L_0x005d:
        r0 = r14.mRectHeight;
        r1 = 1073741824; // 0x40000000 float:2.0 double:5.304989477E-315;
        r2 = r14.mRowSpan;
        r1 = r1 * r2;
        r0 = r0 - r1;
        r14.mRectHeight = r0;
        goto L_0x001a;
    L_0x0068:
        if (r1 <= r6) goto L_0x007b;
    L_0x006a:
        r0 = r14.mLstDotStyle;
        r0 = r0.get(r6);
        r0 = (org.xclcharts.renderer.line.PlotDot) r0;
        r11 = r14.mIsLnChart;
        if (r11 == 0) goto L_0x00b2;
    L_0x0076:
        r0 = r14.mColSpan;
        r0 = r0 + r10;
        r0 = r0 + r4;
        r4 = r0;
    L_0x007b:
        r0 = r14.mLstKey;
        r0 = r0.get(r6);
        r0 = (java.lang.String) r0;
        r11 = r14.getLabelTextWidth(r0);
        r0 = r4 + r11;
        r4 = $SWITCH_TABLE$org$xclcharts$renderer$XEnum$LegendType();
        r12 = r14.getType();
        r12 = r12.ordinal();
        r4 = r4[r12];
        switch(r4) {
            case 1: goto L_0x00e6;
            case 2: goto L_0x00c0;
            default: goto L_0x009a;
        };
    L_0x009a:
        r4 = r5;
        r13 = r0;
        r0 = r3;
        r3 = r13;
    L_0x009e:
        r5 = r14.mMapID;
        r11 = java.lang.Integer.valueOf(r6);
        r12 = java.lang.Integer.valueOf(r4);
        r5.put(r11, r12);
        r5 = r6 + 1;
        r6 = r5;
        r5 = r4;
        r4 = r3;
        r3 = r0;
        goto L_0x0043;
    L_0x00b2:
        r0 = r0.getDotStyle();
        r11 = org.xclcharts.renderer.XEnum.DotStyle.HIDE;
        if (r0 == r11) goto L_0x007b;
    L_0x00ba:
        r0 = r14.mColSpan;
        r0 = r0 + r10;
        r0 = r0 + r4;
        r4 = r0;
        goto L_0x007b;
    L_0x00c0:
        r4 = java.lang.Float.compare(r0, r9);
        r12 = 1;
        if (r4 != r12) goto L_0x00d6;
    L_0x00c7:
        r0 = r14.mColSpan;
        r0 = r0 + r10;
        r4 = r0 + r11;
        r0 = r14.mRowSpan;
        r0 = r0 + r7;
        r0 = r0 + r3;
        r3 = r5 + 1;
        r13 = r4;
        r4 = r3;
        r3 = r13;
        goto L_0x009e;
    L_0x00d6:
        r4 = r14.mColSpan;
        r0 = r0 + r4;
        r4 = java.lang.Float.compare(r0, r2);
        r11 = 1;
        if (r4 != r11) goto L_0x009a;
    L_0x00e0:
        r2 = r0;
        r4 = r5;
        r13 = r0;
        r0 = r3;
        r3 = r13;
        goto L_0x009e;
    L_0x00e6:
        r4 = java.lang.Float.compare(r0, r2);
        r11 = 1;
        if (r4 != r11) goto L_0x00f8;
    L_0x00ed:
        r2 = r14.mRowSpan;
        r2 = r2 + r7;
        r2 = r2 + r3;
        r3 = 0;
        r4 = r5 + 1;
        r13 = r0;
        r0 = r2;
        r2 = r13;
        goto L_0x009e;
    L_0x00f8:
        r0 = r2;
        goto L_0x00ed;
        */
        throw new UnsupportedOperationException("Method not decompiled: org.xclcharts.renderer.plot.PlotLegendRender.calcContentRect():void");
    }

    private void getStartXY() {
        float f = 5.0f;
        if (!this.mShowBox) {
            f = 0.0f;
        }
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$HorizontalAlign()[getHorizontalAlign().ordinal()]) {
            case 1:
                if (EnumChartType.CIR == this.mType) {
                    this.mKeyLabelX = this.mXChart.getLeft() + this.mOffsetX;
                } else {
                    this.mKeyLabelX = this.mPlotArea.getLeft() + this.mOffsetX;
                }
                this.mKeyLabelX += f;
                break;
            case 2:
                this.mKeyLabelX = (this.mXChart.getLeft() + ((this.mXChart.getWidth() - this.mRectWidth) / 2.0f)) + this.mOffsetX;
                break;
            case 3:
                if (EnumChartType.CIR == this.mType) {
                    this.mKeyLabelX = (this.mXChart.getRight() - this.mOffsetX) - this.mRectWidth;
                } else {
                    this.mKeyLabelX = (this.mPlotArea.getRight() - this.mOffsetX) - this.mRectWidth;
                }
                this.mKeyLabelX -= f;
                break;
        }
        switch ($SWITCH_TABLE$org$xclcharts$renderer$XEnum$VerticalAlign()[getVerticalAlign().ordinal()]) {
            case 1:
                if (LegendType.COLUMN == getType()) {
                    this.mKeyLabelY = this.mPlotArea.getTop() + this.mOffsetY;
                    this.mKeyLabelY = f + this.mKeyLabelY;
                    return;
                }
                this.mKeyLabelY = (this.mPlotArea.getTop() - this.mRectHeight) - this.mOffsetY;
                this.mKeyLabelY -= f;
                return;
            case 2:
                this.mKeyLabelY = this.mPlotArea.getTop() + ((this.mPlotArea.getHeight() - this.mRectHeight) / 2.0f);
                return;
            case 3:
                if (LegendType.COLUMN == getType()) {
                    this.mKeyLabelY = this.mXChart.getBottom() + this.mOffsetY;
                    this.mKeyLabelY += (float) this.mXChart.getBorderWidth();
                    this.mKeyLabelY = f + this.mKeyLabelY;
                    return;
                }
                this.mKeyLabelY = (this.mXChart.getBottom() - this.mRectHeight) - this.mOffsetY;
                this.mKeyLabelY -= (float) this.mXChart.getBorderWidth();
                this.mKeyLabelY -= f;
                return;
            default:
                return;
        }
    }

    private void drawLegend(Canvas canvas) {
        int size = this.mLstDotStyle != null ? this.mLstDotStyle.size() : 0;
        if ((this.mLstKey != null ? this.mLstKey.size() : 0) != 0 || size != 0) {
            int size2;
            if (this.mLstColor != null) {
                size2 = this.mLstColor.size();
            } else {
                size2 = 0;
            }
            float f = this.mKeyLabelX + this.mMargin;
            float f2 = this.mMargin + this.mKeyLabelY;
            float labelTextHeight = getLabelTextHeight();
            float rectWidth = getRectWidth();
            drawBox(canvas);
            float f3 = f2;
            float f4 = f;
            int i = 0;
            for (Entry entry : this.mMapID.entrySet()) {
                int intValue;
                float f5;
                Integer num = (Integer) entry.getKey();
                Integer num2 = (Integer) entry.getValue();
                if (num2.intValue() > i) {
                    if (i > 0) {
                        f3 += this.mRowSpan + labelTextHeight;
                    }
                    f2 = this.mKeyLabelX + this.mMargin;
                    intValue = num2.intValue();
                    f5 = f3;
                } else {
                    intValue = i;
                    f5 = f3;
                    f2 = f4;
                }
                if (size2 > num.intValue()) {
                    getPaint().setColor(((Integer) this.mLstColor.get(num.intValue())).intValue());
                    if (this.mIsLnChart) {
                        this.mPaintLine.setColor(((Integer) this.mLstColor.get(num.intValue())).intValue());
                    }
                } else {
                    getPaint().setColor(ViewCompat.MEASURED_STATE_MASK);
                    if (this.mIsLnChart) {
                        this.mPaintLine.setColor(ViewCompat.MEASURED_STATE_MASK);
                    }
                }
                if (size > num.intValue()) {
                    PlotDot plotDot = (PlotDot) this.mLstDotStyle.get(num.intValue());
                    if (this.mIsLnChart) {
                        canvas.drawLine(f2, f5 + (labelTextHeight / 2.0f), f2 + rectWidth, f5 + (labelTextHeight / 2.0f), this.mPaintLine);
                        PlotDotRender.getInstance().renderDot(canvas, plotDot, f2 + (rectWidth / 2.0f), f5 + (labelTextHeight / 2.0f), getPaint());
                        f2 += this.mColSpan + rectWidth;
                    } else if (plotDot.getDotStyle() != DotStyle.HIDE) {
                        PlotDotRender.getInstance().renderDot(canvas, plotDot, f2 + (rectWidth / 2.0f), f5 + (labelTextHeight / 2.0f), getPaint());
                        f2 += this.mColSpan + rectWidth;
                    }
                }
                String str = (String) this.mLstKey.get(num.intValue());
                if ("" != str) {
                    canvas.drawText(str, f2, f5 + labelTextHeight, getPaint());
                }
                i = intValue;
                f3 = f5;
                f4 = (getLabelTextWidth(str) + f2) + this.mColSpan;
            }
            this.mMapID.clear();
            clearLst();
        }
    }

    private void clearLst() {
        if (this.mLstDotStyle != null) {
            this.mLstDotStyle.clear();
            this.mLstDotStyle = null;
        }
        if (this.mLstKey != null) {
            this.mLstKey.clear();
            this.mLstKey = null;
        }
        if (this.mLstColor != null) {
            this.mLstColor.clear();
            this.mLstColor = null;
        }
    }

    private void drawBox(Canvas canvas) {
        if (this.mShowBox) {
            RectF rectF = new RectF();
            rectF.left = this.mKeyLabelX;
            rectF.right = this.mKeyLabelX + this.mRectWidth;
            rectF.top = this.mKeyLabelY;
            rectF.bottom = this.mKeyLabelY + this.mRectHeight;
            this.mBorder.renderRect(canvas, rectF, this.mShowBoxBorder, this.mShowBackground);
        }
    }

    private void refreshLst() {
        initEnv();
        if (this.mLstKey == null) {
            this.mLstKey = new ArrayList();
        } else {
            this.mLstKey.clear();
        }
        if (this.mLstDotStyle == null) {
            this.mLstDotStyle = new ArrayList();
        } else {
            this.mLstDotStyle.clear();
        }
        if (this.mLstColor == null) {
            this.mLstColor = new ArrayList();
        } else {
            this.mLstColor.clear();
        }
    }

    private void convertArrayLineKey(List<LnData> list) {
        if (list != null) {
            String str = "";
            for (LnData lnData : list) {
                String lineKey = lnData.getLineKey();
                if (isDrawKey(lineKey) && "" != lineKey) {
                    this.mLstKey.add(lineKey);
                    this.mLstColor.add(Integer.valueOf(lnData.getLineColor()));
                    this.mLstDotStyle.add(lnData.getPlotLine().getPlotDot());
                }
            }
        }
    }

    private void convertArrayBarKey(List<BarData> list) {
        if (list != null) {
            String str = "";
            for (BarData barData : list) {
                String key = barData.getKey();
                if (isDrawKey(key) && "" != key) {
                    this.mLstKey.add(key);
                    this.mLstColor.add(barData.getColor());
                    PlotDot plotDot = new PlotDot();
                    plotDot.setDotStyle(DotStyle.RECT);
                    this.mLstDotStyle.add(plotDot);
                }
            }
        }
    }

    private void convertArrayPieKey(List<PieData> list) {
        if (list != null) {
            String str = "";
            for (PieData pieData : list) {
                String key = pieData.getKey();
                if (isDrawKey(key) && "" != key) {
                    this.mLstKey.add(key);
                    this.mLstColor.add(Integer.valueOf(pieData.getSliceColor()));
                    PlotDot plotDot = new PlotDot();
                    plotDot.setDotStyle(DotStyle.RECT);
                    this.mLstDotStyle.add(plotDot);
                }
            }
        }
    }

    private void convertArrayRadarKey(List<RadarData> list) {
        if (list != null) {
            String str = "";
            for (RadarData radarData : list) {
                String lineKey = radarData.getLineKey();
                if (isDrawKey(lineKey) && "" != lineKey) {
                    this.mLstKey.add(lineKey);
                    this.mLstColor.add(Integer.valueOf(radarData.getLineColor()));
                    this.mLstDotStyle.add(radarData.getPlotLine().getPlotDot());
                }
            }
        }
    }

    private void convertArrayPointKey(List<ScatterData> list) {
        if (list != null) {
            String str = "";
            for (ScatterData scatterData : list) {
                String key = scatterData.getKey();
                if (isDrawKey(key) && "" != key) {
                    this.mLstKey.add(key);
                    this.mLstColor.add(Integer.valueOf(scatterData.getPlotDot().getColor()));
                    this.mLstDotStyle.add(scatterData.getPlotDot());
                }
            }
        }
    }

    private void convertArrayBubbleKey(List<BubbleData> list) {
        if (list != null) {
            String str = "";
            for (BubbleData bubbleData : list) {
                String key = bubbleData.getKey();
                if (isDrawKey(key) && "" != key) {
                    this.mLstKey.add(key);
                    this.mLstColor.add(Integer.valueOf(bubbleData.getColor()));
                    PlotDot plotDot = new PlotDot();
                    plotDot.setDotStyle(DotStyle.DOT);
                    this.mLstDotStyle.add(plotDot);
                }
            }
        }
    }

    private void convertArrayArcLineKey(List<ArcLineData> list) {
        if (list != null) {
            String str = "";
            for (ArcLineData arcLineData : list) {
                String key = arcLineData.getKey();
                if (isDrawKey(key) && "" != key) {
                    this.mLstKey.add(key);
                    this.mLstColor.add(Integer.valueOf(arcLineData.getBarColor()));
                    PlotDot plotDot = new PlotDot();
                    plotDot.setDotStyle(DotStyle.RECT);
                    this.mLstDotStyle.add(plotDot);
                }
            }
        }
    }

    private boolean isDrawKey(String str) {
        if ("" == str || str.length() == 0) {
            return false;
        }
        return true;
    }
}
