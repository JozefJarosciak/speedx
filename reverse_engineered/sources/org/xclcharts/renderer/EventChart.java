package org.xclcharts.renderer;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.Log;
import java.util.ArrayList;
import java.util.Iterator;
import org.xclcharts.common.DrawHelper;
import org.xclcharts.common.MathHelper;
import org.xclcharts.event.click.ArcPosition;
import org.xclcharts.event.click.BarPosition;
import org.xclcharts.event.click.PlotArcPosition;
import org.xclcharts.event.click.PlotBarPosition;
import org.xclcharts.event.click.PlotPointPosition;
import org.xclcharts.event.click.PointPosition;
import org.xclcharts.event.click.PositionRecord;
import org.xclcharts.renderer.XEnum.ChartType;
import org.xclcharts.renderer.info.ToolTip;
import org.xclcharts.renderer.info.ToolTipRender;

public class EventChart extends XChart {
    private static final String TAG = "EventChart";
    private int mClickRangeExtValue = 0;
    private ArcPosition mFocusArcPosition = null;
    private boolean mFocusArcSelect = false;
    private Paint mFocusPaint = null;
    private PointF mFocusPoint = null;
    private float mFocusRadius = 0.0f;
    private RectF mFocusRect = null;
    private boolean mListenClick = false;
    private ArrayList mRecordset = null;
    private int mSelectDataChildID = -1;
    private int mSelectDataID = -1;
    private int mSelectID = -1;
    private boolean mShowClikedFocus = false;
    private ToolTipRender mToolTip = null;

    public EventChart() {
        initPositionRecord();
    }

    public void ActiveListenItemClick() {
        this.mListenClick = true;
    }

    public void DeactiveListenItemClick() {
        this.mListenClick = false;
    }

    public boolean getListenItemClickStatus() {
        return this.mListenClick;
    }

    public ArrayList<PositionRecord> getPositionRecordset() {
        return this.mRecordset;
    }

    public void showClikedFocus() {
        this.mShowClikedFocus = true;
    }

    private void clearSelected() {
        this.mSelectID = -1;
        this.mSelectDataID = -1;
        this.mSelectDataChildID = -1;
    }

    private void saveSelected(int i, int i2, int i3) {
        this.mSelectID = i;
        this.mSelectDataID = i2;
        this.mSelectDataChildID = i3;
    }

    protected int getSelected() {
        return this.mSelectID;
    }

    protected void savePointRecord(int i, int i2, float f, float f2, RectF rectF) {
        if (getListenItemClickStatus()) {
            savePointRecord(i, i2, f, f2, rectF.left, rectF.top, rectF.right, rectF.bottom);
        }
    }

    protected void savePointRecord(int i, int i2, float f, float f2, float f3, float f4, float f5, float f6) {
        if (getListenItemClickStatus()) {
            if (this.mRecordset == null) {
                this.mRecordset = new ArrayList();
            }
            PlotPointPosition plotPointPosition = new PlotPointPosition();
            plotPointPosition.savePlotDataID(i);
            plotPointPosition.savePlotDataChildID(i2);
            plotPointPosition.savePlotPosition(f, f2);
            plotPointPosition.savePlotRectF(f3, f4, f5, f6);
            plotPointPosition.extPointClickRange(this.mClickRangeExtValue);
            this.mRecordset.add(plotPointPosition);
        }
    }

    protected void saveBarRectFRecord(int i, int i2, float f, float f2, float f3, float f4) {
        if (getListenItemClickStatus()) {
            if (this.mRecordset == null) {
                this.mRecordset = new ArrayList();
            }
            PlotBarPosition plotBarPosition = new PlotBarPosition();
            plotBarPosition.savePlotDataID(i);
            plotBarPosition.savePlotDataChildID(i2);
            plotBarPosition.savePlotRectF(f, f2, f3, f4);
            plotBarPosition.extPointClickRange(this.mClickRangeExtValue);
            this.mRecordset.add(plotBarPosition);
        }
    }

    protected void saveBarRecord(int i, int i2, float f, float f2, RectF rectF) {
        if (getListenItemClickStatus()) {
            if (this.mRecordset == null) {
                this.mRecordset = new ArrayList();
            }
            PlotBarPosition plotBarPosition = new PlotBarPosition();
            plotBarPosition.savePlotDataID(i);
            plotBarPosition.savePlotDataChildID(i2);
            plotBarPosition.savePlotRectF(rectF);
            plotBarPosition.extPointClickRange(this.mClickRangeExtValue);
            this.mRecordset.add(plotBarPosition);
        }
    }

    protected void saveArcRecord(int i, float f, float f2, float f3, float f4, float f5, float f6, float f7) {
        if (getListenItemClickStatus()) {
            if (this.mRecordset == null) {
                this.mRecordset = new ArrayList();
            }
            PlotArcPosition plotArcPosition = new PlotArcPosition();
            plotArcPosition.savePlotDataID(i);
            plotArcPosition.savePlotCirXY(f, f2);
            plotArcPosition.saveAngle(f3, f4, f5, f6);
            plotArcPosition.saveInitialAngle(f7);
            this.mRecordset.add(plotArcPosition);
        }
    }

    public void extPointClickRange(int i) {
        this.mClickRangeExtValue = i;
    }

    public boolean isPlotClickArea(float f, float f2) {
        if (!getListenItemClickStatus() || Float.compare(f, getPlotArea().getLeft()) == -1 || Float.compare(f, getPlotArea().getRight()) == 1 || Float.compare(f2, getPlotArea().getTop()) == -1 || Float.compare(f2, getPlotArea().getBottom()) == 1) {
            return false;
        }
        return true;
    }

    protected ArcPosition getArcRecord(float f, float f2) {
        if (!getListenItemClickStatus()) {
            return null;
        }
        if (!isPlotClickArea(f, f2)) {
            return null;
        }
        if (!getClikedScaleStatus()) {
            return null;
        }
        if (this.mRecordset == null) {
            return null;
        }
        Iterator it = this.mRecordset.iterator();
        while (it.hasNext()) {
            PlotArcPosition plotArcPosition = (PlotArcPosition) it.next();
            if (plotArcPosition.compareF(f, f2)) {
                saveSelected(plotArcPosition.getRecordID(), plotArcPosition.getDataID(), plotArcPosition.getDataChildID());
                return plotArcPosition;
            }
        }
        clearSelected();
        return null;
    }

    protected BarPosition getBarRecord(float f, float f2) {
        if (!getListenItemClickStatus()) {
            return null;
        }
        if (!isPlotClickArea(f, f2)) {
            return null;
        }
        if (!getClikedScaleStatus()) {
            return null;
        }
        if (this.mRecordset == null) {
            return null;
        }
        Iterator it = this.mRecordset.iterator();
        while (it.hasNext()) {
            PlotBarPosition plotBarPosition = (PlotBarPosition) it.next();
            if (plotBarPosition.compareF(f, f2)) {
                saveSelected(plotBarPosition.getRecordID(), plotBarPosition.getDataID(), plotBarPosition.getDataChildID());
                return plotBarPosition;
            }
        }
        clearSelected();
        return null;
    }

    protected PointPosition getPointRecord(float f, float f2) {
        if (!getListenItemClickStatus()) {
            return null;
        }
        if (!isPlotClickArea(f, f2)) {
            return null;
        }
        if (!getClikedScaleStatus()) {
            return null;
        }
        if (this.mRecordset == null) {
            return null;
        }
        Iterator it = this.mRecordset.iterator();
        while (it.hasNext()) {
            PlotPointPosition plotPointPosition = (PlotPointPosition) it.next();
            if (plotPointPosition.compareF(f, f2)) {
                saveSelected(plotPointPosition.getRecordID(), plotPointPosition.getDataID(), plotPointPosition.getDataChildID());
                return plotPointPosition;
            }
        }
        clearSelected();
        return null;
    }

    protected void initPositionRecord() {
        if (this.mRecordset != null) {
            this.mRecordset.clear();
            this.mRecordset = null;
        }
    }

    public Paint getFocusPaint() {
        if (this.mFocusPaint == null) {
            this.mFocusPaint = new Paint(1);
        }
        return this.mFocusPaint;
    }

    public void showFocusPointF(PointF pointF, float f) {
        this.mFocusPoint = pointF;
        this.mFocusRadius = f;
    }

    public void showFocusRectF(RectF rectF) {
        this.mFocusRect = rectF;
    }

    public void showFocusArc(ArcPosition arcPosition) {
        showFocusArc(arcPosition, false);
    }

    public void showFocusArc(ArcPosition arcPosition, boolean z) {
        this.mFocusArcPosition = arcPosition;
        this.mFocusArcSelect = z;
    }

    public ToolTip getToolTip() {
        if (this.mToolTip == null) {
            this.mToolTip = new ToolTipRender();
        }
        return this.mToolTip;
    }

    protected void renderToolTip(Canvas canvas) {
        if (this.mToolTip != null) {
            this.mToolTip.renderInfo(canvas);
        }
    }

    protected boolean drawFocusRect(Canvas canvas, int i, int i2, float f, float f2, float f3, float f4) {
        if (!this.mShowClikedFocus) {
            return true;
        }
        if (-1 == this.mSelectID) {
            return false;
        }
        if (this.mFocusRect == null) {
            return false;
        }
        if (this.mSelectDataID != i || this.mSelectDataChildID != i2) {
            return true;
        }
        this.mFocusRect.left = f;
        this.mFocusRect.top = f2;
        this.mFocusRect.right = f3;
        this.mFocusRect.bottom = f4;
        canvas.drawRect(this.mFocusRect, getFocusPaint());
        this.mFocusRect.setEmpty();
        clearSelected();
        return true;
    }

    protected boolean renderFocusShape(Canvas canvas) {
        if (!this.mShowClikedFocus) {
            return true;
        }
        ChartType type = getType();
        if (ChartType.BAR == type || ChartType.BAR3D == type || ChartType.STACKBAR == type) {
            return true;
        }
        try {
            if (this.mFocusPoint != null) {
                canvas.drawCircle(this.mFocusPoint.x, this.mFocusPoint.y, this.mFocusRadius, getFocusPaint());
                this.mFocusPoint = null;
                this.mFocusRadius = 0.0f;
            } else if (this.mFocusRect == null) {
                if (this.mFocusArcPosition == null) {
                    return false;
                }
                PointF pointF = this.mFocusArcPosition.getPointF();
                float f = pointF.x;
                float f2 = pointF.y;
                float radius = this.mFocusArcPosition.getRadius();
                if (this.mFocusArcSelect) {
                    pointF = MathHelper.getInstance().calcArcEndPointXY(f, f2, div(radius, this.mFocusArcPosition.getSelectedOffset()), add(this.mFocusArcPosition.getStartAngle(), this.mFocusArcPosition.getSweepAngle() / 2.0f));
                    f = pointF.x;
                    f2 = pointF.y;
                }
                DrawHelper.getInstance().drawPercent(canvas, getFocusPaint(), f, f2, radius, this.mFocusArcPosition.getStartAngle(), this.mFocusArcPosition.getSweepAngle(), true);
                this.mFocusArcPosition = null;
            }
        } catch (Exception e) {
            Log.e(TAG, e.toString());
        }
        return true;
    }

    protected boolean postRender(Canvas canvas) throws Exception {
        try {
            super.postRender(canvas);
            initPositionRecord();
            return true;
        } catch (Exception e) {
            throw e;
        }
    }
}
