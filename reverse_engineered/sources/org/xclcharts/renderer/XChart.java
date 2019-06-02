package org.xclcharts.renderer;

import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Shader;
import android.graphics.Shader.TileMode;
import java.util.List;
import org.xclcharts.common.MathHelper;
import org.xclcharts.renderer.XEnum.ChartType;
import org.xclcharts.renderer.XEnum.Direction;
import org.xclcharts.renderer.XEnum.HorizontalAlign;
import org.xclcharts.renderer.XEnum.PanMode;
import org.xclcharts.renderer.XEnum.RectType;
import org.xclcharts.renderer.XEnum.VerticalAlign;
import org.xclcharts.renderer.info.AnchorDataPoint;
import org.xclcharts.renderer.info.AnchorRender;
import org.xclcharts.renderer.info.DyLine;
import org.xclcharts.renderer.info.DyLineRender;
import org.xclcharts.renderer.info.Legend;
import org.xclcharts.renderer.info.LegendRender;
import org.xclcharts.renderer.plot.Border;
import org.xclcharts.renderer.plot.BorderRender;
import org.xclcharts.renderer.plot.PlotArea;
import org.xclcharts.renderer.plot.PlotAreaRender;
import org.xclcharts.renderer.plot.PlotGrid;
import org.xclcharts.renderer.plot.PlotGridRender;
import org.xclcharts.renderer.plot.PlotLegend;
import org.xclcharts.renderer.plot.PlotLegendRender;
import org.xclcharts.renderer.plot.PlotTitle;
import org.xclcharts.renderer.plot.PlotTitleRender;

public class XChart implements IRender {
    private boolean mBackgroundColorVisible = false;
    private BorderRender mBorder = null;
    private float mBottom = 0.0f;
    private float mCenterX = 0.0f;
    private float mCenterY = 0.0f;
    private boolean mControlPanRange = true;
    private LegendRender mDyLegend = null;
    private DyLineRender mDyLine = null;
    private boolean mDyLineVisible = false;
    protected boolean mEnablePanMode = true;
    private boolean mEnableScale = true;
    private float mHeight = 0.0f;
    private float mLeft = 0.0f;
    private float mPaddingBottom = 0.0f;
    private float mPaddingLeft = 0.0f;
    private float mPaddingRight = 0.0f;
    private float mPaddingTop = 0.0f;
    private PanMode mPlotPanMode = PanMode.FREE;
    private float mRight = 0.0f;
    private boolean mShowBorder = false;
    private float mTop = 0.0f;
    protected float[] mTranslateXY = new float[2];
    private float mWidth = 0.0f;
    private float mXScale = 0.0f;
    private float mYScale = 0.0f;
    protected PlotAreaRender plotArea = null;
    protected PlotGridRender plotGrid = null;
    protected PlotLegendRender plotLegend = null;
    private PlotTitleRender plotTitle = null;

    public XChart() {
        initChart();
    }

    private void initChart() {
        this.mTranslateXY[0] = 0.0f;
        this.mTranslateXY[1] = 0.0f;
        if (this.plotLegend == null) {
            this.plotLegend = new PlotLegendRender(this);
        }
        if (this.plotArea == null) {
            this.plotArea = new PlotAreaRender();
        }
        if (this.plotGrid == null) {
            this.plotGrid = new PlotGridRender();
        }
        if (this.plotTitle == null) {
            this.plotTitle = new PlotTitleRender();
        }
    }

    public ChartType getType() {
        return ChartType.NONE;
    }

    public PlotLegend getPlotLegend() {
        if (this.plotLegend == null) {
            this.plotLegend = new PlotLegendRender(this);
        }
        return this.plotLegend;
    }

    public void setPadding(float f, float f2, float f3, float f4) {
        if (f2 > 0.0f) {
            this.mPaddingTop = f2;
        }
        if (f4 > 0.0f) {
            this.mPaddingBottom = f4;
        }
        if (f > 0.0f) {
            this.mPaddingLeft = f;
        }
        if (f3 > 0.0f) {
            this.mPaddingRight = f3;
        }
    }

    public PlotArea getPlotArea() {
        if (this.plotArea == null) {
            this.plotArea = new PlotAreaRender();
        }
        return this.plotArea;
    }

    public PlotGrid getPlotGrid() {
        if (this.plotGrid == null) {
            this.plotGrid = new PlotGridRender();
        }
        return this.plotGrid;
    }

    public PlotTitle getPlotTitle() {
        if (this.plotTitle == null) {
            this.plotTitle = new PlotTitleRender();
        }
        return this.plotTitle;
    }

    public void setChartRange(float f, float f2) {
        setChartRange(0.0f, 0.0f, f, f2);
    }

    public void setChartRange(float f, float f2, float f3, float f4) {
        if (f > 0.0f) {
            this.mLeft = f;
        }
        if (f2 > 0.0f) {
            this.mTop = f2;
        }
        this.mRight = add(f, f3);
        this.mBottom = add(f2, f4);
        if (Float.compare(f3, 0.0f) > 0) {
            this.mWidth = f3;
        }
        if (Float.compare(f4, 0.0f) > 0) {
            this.mHeight = f4;
        }
    }

    public void setTitle(String str) {
        if (this.plotTitle != null) {
            this.plotTitle.setTitle(str);
        }
    }

    public void addSubtitle(String str) {
        if (this.plotTitle != null) {
            this.plotTitle.setSubtitle(str);
        }
    }

    public void setTitleVerticalAlign(VerticalAlign verticalAlign) {
        if (this.plotTitle != null) {
            this.plotTitle.setVerticalAlign(verticalAlign);
        }
    }

    public void setTitleAlign(HorizontalAlign horizontalAlign) {
        if (this.plotTitle != null) {
            this.plotTitle.setTitleAlign(horizontalAlign);
        }
    }

    public float getLeft() {
        return this.mLeft;
    }

    public float getTop() {
        return this.mTop;
    }

    public float getRight() {
        return this.mRight;
    }

    public float getBottom() {
        return this.mBottom;
    }

    public float getWidth() {
        return this.mWidth;
    }

    public float getHeight() {
        return this.mHeight;
    }

    public float getPaddingTop() {
        return this.mPaddingTop;
    }

    public float getPaddingBottom() {
        return this.mPaddingBottom;
    }

    public float getPaddingLeft() {
        return this.mPaddingLeft;
    }

    public float getPaddingRight() {
        return this.mPaddingRight;
    }

    public PointF getCenterXY() {
        PointF pointF = new PointF();
        pointF.x = getLeft() + div(getWidth(), 2.0f);
        pointF.y = getTop() + div(getHeight(), 2.0f);
        return pointF;
    }

    public void setTranslateXY(float f, float f2) {
        if (this.mEnablePanMode) {
            if (this.mTranslateXY == null) {
                this.mTranslateXY = new float[2];
            }
            this.mTranslateXY[0] = f;
            this.mTranslateXY[1] = f2;
        }
    }

    public float[] getTranslateXY() {
        return this.mTranslateXY;
    }

    protected void calcPlotRange() {
        int borderWidth = getBorderWidth();
        if (this.plotArea != null) {
            this.plotArea.setBottom(sub(getBottom() - ((float) (borderWidth / 2)), this.mPaddingBottom));
            this.plotArea.setLeft(add(getLeft() + ((float) (borderWidth / 2)), this.mPaddingLeft));
            this.plotArea.setRight(sub(getRight() - ((float) (borderWidth / 2)), this.mPaddingRight));
            this.plotArea.setTop(add(((float) (borderWidth / 2)) + getTop(), this.mPaddingTop));
        }
    }

    protected void renderTitle(Canvas canvas) {
        int borderWidth = getBorderWidth();
        if (this.plotTitle != null) {
            this.plotTitle.renderTitle(this.mLeft + ((float) borderWidth), this.mRight - ((float) borderWidth), ((float) borderWidth) + this.mTop, this.mWidth, this.plotArea.getTop(), canvas);
        }
    }

    protected boolean drawAnchor(List<AnchorDataPoint> list, int i, int i2, Canvas canvas, float f, float f2, float f3) {
        if (list == null || -1 == i) {
            return false;
        }
        int size = list.size();
        float left = getPlotArea().getLeft();
        float right = getPlotArea().getRight();
        float top = getPlotArea().getTop();
        float bottom = getPlotArea().getBottom();
        for (int i3 = 0; i3 < size; i3++) {
            AnchorDataPoint anchorDataPoint = (AnchorDataPoint) list.get(i3);
            if (anchorDataPoint.getDataSeriesID() == i && (-1 == i2 || -1 == anchorDataPoint.getDataChildID() || (-1 != i2 && anchorDataPoint.getDataChildID() == i2))) {
                AnchorRender.getInstance().renderAnchor(canvas, anchorDataPoint, f, f2, f3, left, top, right, bottom);
                return true;
            }
        }
        return false;
    }

    public void setApplyBackgroundColor(boolean z) {
        this.mBackgroundColorVisible = z;
    }

    public void setBackgroundColor(int i) {
        getBackgroundPaint().setColor(i);
        getPlotArea().getBackgroundPaint().setColor(i);
        if (this.mBorder == null) {
            this.mBorder = new BorderRender();
        }
        this.mBorder.getBackgroundPaint().setColor(i);
    }

    public void setBackgroundColor(Direction direction, int i, int i2) {
        if (i == i2) {
            getBackgroundPaint().setColor(i);
        } else {
            Shader linearGradient;
            if (direction == Direction.VERTICAL) {
                linearGradient = new LinearGradient(0.0f, 0.0f, 0.0f, getBottom() - getTop(), i, i2, TileMode.MIRROR);
            } else {
                linearGradient = new LinearGradient(getLeft(), getBottom(), getRight(), getTop(), i, i2, TileMode.CLAMP);
            }
            getBackgroundPaint().setShader(linearGradient);
        }
        if (this.mBorder == null) {
            this.mBorder = new BorderRender();
        }
        this.mBorder.getBackgroundPaint().setColor(i2);
    }

    public Paint getBackgroundPaint() {
        if (this.mBorder == null) {
            this.mBorder = new BorderRender();
        }
        return this.mBorder.getBackgroundPaint();
    }

    public void showBorder() {
        this.mShowBorder = true;
        if (this.mBorder == null) {
            this.mBorder = new BorderRender();
        }
        this.mBorder.setBorderRectType(RectType.RECT);
    }

    public void showRoundBorder() {
        this.mShowBorder = true;
        if (this.mBorder == null) {
            this.mBorder = new BorderRender();
        }
        this.mBorder.setBorderRectType(RectType.ROUNDRECT);
    }

    public void hideBorder() {
        this.mShowBorder = false;
        if (this.mBorder != null) {
            this.mBorder = null;
        }
    }

    public Border getBorder() {
        if (this.mBorder == null) {
            this.mBorder = new BorderRender();
        }
        return this.mBorder;
    }

    public boolean isShowBorder() {
        return this.mShowBorder;
    }

    public int getBorderWidth() {
        if (!this.mShowBorder) {
            return 0;
        }
        if (this.mBorder == null) {
            this.mBorder = new BorderRender();
        }
        return this.mBorder.getBorderWidth();
    }

    public void setBorderWidth(int i) {
        if (i > 0) {
            if (this.mBorder == null) {
                this.mBorder = new BorderRender();
            }
            this.mBorder.setRoundRadius(i);
        }
    }

    protected void renderBorder(Canvas canvas) {
        if (this.mShowBorder) {
            if (this.mBorder == null) {
                this.mBorder = new BorderRender();
            }
            this.mBorder.renderBorder("BORDER", canvas, this.mLeft, this.mTop, this.mRight, this.mBottom);
        }
    }

    protected void renderChartBackground(Canvas canvas) {
        if (this.mBackgroundColorVisible) {
            if (this.mBorder == null) {
                this.mBorder = new BorderRender();
            }
            if (this.mShowBorder) {
                this.mBorder.renderBorder("CHART", canvas, this.mLeft, this.mTop, this.mRight, this.mBottom);
                return;
            }
            int borderSpadding = this.mBorder.getBorderSpadding();
            this.mBorder.renderBorder("CHART", canvas, this.mLeft - ((float) borderSpadding), this.mTop - ((float) borderSpadding), this.mRight + ((float) borderSpadding), this.mBottom + ((float) borderSpadding));
        }
    }

    public void setScale(float f, float f2, float f3, float f4) {
        this.mXScale = f;
        this.mYScale = f2;
        this.mCenterX = f3;
        this.mCenterY = f4;
    }

    protected boolean getClikedScaleStatus() {
        if (!this.mEnableScale || Float.compare(this.mXScale, 0.0f) == 0) {
            return true;
        }
        if (Float.compare(this.mXScale, 0.95f) == 1 && Float.compare(this.mXScale, 1.1f) == -1) {
            return true;
        }
        return false;
    }

    private void scaleChart(Canvas canvas) {
        if (!this.mEnableScale) {
            return;
        }
        if (Float.compare(this.mCenterX, 0.0f) == 1 || Float.compare(this.mCenterY, 0.0f) == 1) {
            canvas.scale(this.mXScale, this.mYScale, this.mCenterX, this.mCenterY);
        }
    }

    public void enableScale() {
        this.mEnableScale = true;
    }

    public void disableScale() {
        this.mEnableScale = false;
    }

    public boolean getScaleStatus() {
        return this.mEnableScale;
    }

    public void setPlotPanMode(PanMode panMode) {
        this.mPlotPanMode = panMode;
    }

    public PanMode getPlotPanMode() {
        return this.mPlotPanMode;
    }

    public void enablePanMode() {
        this.mEnablePanMode = true;
    }

    public void disablePanMode() {
        this.mEnablePanMode = false;
    }

    public void enabledCtlPanRange() {
        this.mControlPanRange = true;
    }

    public void disabledCtlPanRange() {
        this.mControlPanRange = false;
    }

    public boolean getCtlPanRangeStatus() {
        return this.mControlPanRange;
    }

    public boolean getPanModeStatus() {
        return this.mEnablePanMode;
    }

    public Legend getDyLegend() {
        if (this.mDyLegend == null) {
            this.mDyLegend = new LegendRender();
        }
        return this.mDyLegend;
    }

    public void showDyLine() {
        this.mDyLineVisible = true;
    }

    public void hideDyLine() {
        this.mDyLineVisible = false;
    }

    public boolean getDyLineVisible() {
        return this.mDyLineVisible;
    }

    public DyLine getDyLine() {
        if (this.mDyLine == null) {
            this.mDyLine = new DyLineRender();
        }
        return this.mDyLine;
    }

    private void drawDyLine(Canvas canvas) {
        if (this.mDyLineVisible) {
            if (this.mDyLine == null) {
                this.mDyLine = new DyLineRender();
            }
            this.mDyLine.renderLine(canvas, this.plotArea.getLeft(), this.plotArea.getTop(), this.plotArea.getRight(), this.plotArea.getBottom());
        }
    }

    private void drawDyLegend(Canvas canvas) {
        if (this.mDyLegend != null) {
            this.mDyLegend.setPlotWH(getWidth(), getHeight());
            this.mDyLegend.renderInfo(canvas);
        }
    }

    protected boolean postRender(Canvas canvas) throws Exception {
        try {
            renderChartBackground(canvas);
            return true;
        } catch (Exception e) {
            throw e;
        }
    }

    public boolean render(Canvas canvas) throws Exception {
        if (canvas == null) {
            return false;
        }
        try {
            canvas.save();
            scaleChart(canvas);
            boolean postRender = postRender(canvas);
            renderBorder(canvas);
            drawDyLegend(canvas);
            drawDyLine(canvas);
            canvas.restore();
            return postRender;
        } catch (Exception e) {
            throw e;
        }
    }

    public void disableHighPrecision() {
        MathHelper.getInstance().disableHighPrecision();
    }

    public void enabledHighPrecision() {
        MathHelper.getInstance().enabledHighPrecision();
    }

    protected float add(float f, float f2) {
        return MathHelper.getInstance().add(f, f2);
    }

    protected float sub(float f, float f2) {
        return MathHelper.getInstance().sub(f, f2);
    }

    protected float mul(float f, float f2) {
        return MathHelper.getInstance().mul(f, f2);
    }

    protected float div(float f, float f2) {
        return MathHelper.getInstance().div(f, f2);
    }
}
