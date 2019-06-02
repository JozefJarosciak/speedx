package org.xclcharts.chart;

import android.graphics.Canvas;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;
import org.xclcharts.renderer.CirChart;
import org.xclcharts.renderer.XEnum.ChartType;
import org.xclcharts.renderer.XEnum.Location;
import org.xclcharts.renderer.XEnum.RoundAxisType;
import org.xclcharts.renderer.XEnum.RoundTickAxisType;
import org.xclcharts.renderer.axis.RoundAxis;
import org.xclcharts.renderer.axis.RoundAxisRender;
import org.xclcharts.renderer.plot.PlotAttrInfo;
import org.xclcharts.renderer.plot.PlotAttrInfoRender;
import org.xclcharts.renderer.plot.Pointer;
import org.xclcharts.renderer.plot.PointerRender;

public class DialChart extends CirChart {
    private static final int FIX_TOTAL_ANGLE = 270;
    private static final int INIT_ANGLE = 135;
    private static final String TAG = "DialChart";
    private PointerRender mPointer;
    List<Pointer> mPointerSet;
    private List<RoundAxis> mRoundAxis;
    private float mStartAngle;
    private float mTotalAngle;
    private PlotAttrInfoRender plotAttrInfoRender;

    public DialChart() {
        this.mStartAngle = 0.0f;
        this.mTotalAngle = 0.0f;
        this.mRoundAxis = new ArrayList();
        this.mPointer = null;
        this.mPointerSet = new ArrayList();
        this.plotAttrInfoRender = null;
        this.mStartAngle = 135.0f;
        this.mTotalAngle = 270.0f;
        if (this.plotAttrInfoRender == null) {
            this.plotAttrInfoRender = new PlotAttrInfoRender();
        }
    }

    public ChartType getType() {
        return ChartType.DIAL;
    }

    public void setStartAngle(float f) {
        this.mStartAngle = f;
    }

    public void setTotalAngle(float f) {
        this.mTotalAngle = f;
    }

    public List<RoundAxis> getPlotAxis() {
        return this.mRoundAxis;
    }

    public List<Pointer> getPlotPointer() {
        return this.mPointerSet;
    }

    public PlotAttrInfo getPlotAttrInfo() {
        return this.plotAttrInfoRender;
    }

    public void addPointer() {
        this.mPointerSet.add(new PointerRender());
    }

    public void clearPlotPointer() {
        if (this.mPointerSet != null) {
            this.mPointerSet.clear();
        }
    }

    public void clearPlotAxis() {
        if (this.mRoundAxis != null) {
            this.mRoundAxis.clear();
        }
    }

    public void clearAll() {
        clearPlotPointer();
        clearPlotAxis();
        this.plotAttrInfoRender.clearPlotAttrInfo();
    }

    public Pointer getPointer() {
        if (this.mPointer == null) {
            this.mPointer = new PointerRender();
        }
        return this.mPointer;
    }

    private void renderPointerLine(Canvas canvas) {
        if (this.mPointerSet != null) {
            float radius = getRadius();
            for (int i = 0; i < this.mPointerSet.size(); i++) {
                PointerRender pointerRender = (PointerRender) this.mPointerSet.get(i);
                pointerRender.setParentRadius(radius);
                pointerRender.setStartXY(this.plotArea.getCenterX(), this.plotArea.getCenterY());
                pointerRender.setTotalAngle(this.mTotalAngle);
                pointerRender.setStartAngle(this.mStartAngle);
                pointerRender.render(canvas);
            }
            if (this.mPointer == null) {
                this.mPointer = new PointerRender();
            }
            this.mPointer.setStartXY(this.plotArea.getCenterX(), this.plotArea.getCenterY());
            this.mPointer.setTotalAngle(this.mTotalAngle);
            this.mPointer.setStartAngle(this.mStartAngle);
            this.mPointer.setParentRadius(getRadius());
            this.mPointer.render(canvas);
        }
    }

    public void addInnerTicksAxis(float f, List<String> list) {
        addTicksAxis(f, list, RoundTickAxisType.INNER_TICKAXIS);
    }

    public void addOuterTicksAxis(float f, List<String> list) {
        addTicksAxis(f, list, RoundTickAxisType.OUTER_TICKAXIS);
    }

    private void addTicksAxis(float f, List<String> list, RoundTickAxisType roundTickAxisType) {
        RoundAxisRender roundAxisRender = new RoundAxisRender();
        roundAxisRender.setRoundAxisType(RoundAxisType.TICKAXIS);
        roundAxisRender.setRadiusPercentage(f);
        roundAxisRender.setAxisLabels(list);
        roundAxisRender.setRoundTickAxisType(roundTickAxisType);
        this.mRoundAxis.add(roundAxisRender);
    }

    public void addStrokeRingAxis(float f, float f2, List<Float> list, List<Integer> list2) {
        addRingAxis(f, f2, list, list2, null);
    }

    public void addStrokeRingAxis(float f, float f2, List<Float> list, List<Integer> list2, List<String> list3) {
        addRingAxis(f, f2, list, list2, list3);
    }

    public void addFillRingAxis(float f, List<Float> list, List<Integer> list2) {
        addRingAxis(f, 0.0f, list, list2, null);
    }

    public void addFillRingAxis(float f, List<Float> list, List<Integer> list2, List<String> list3) {
        addRingAxis(f, 0.0f, list, list2, list3);
    }

    public void addRingAxis(float f, float f2, List<Float> list, List<Integer> list2, List<String> list3) {
        RoundAxisRender roundAxisRender = new RoundAxisRender();
        roundAxisRender.setRoundAxisType(RoundAxisType.RINGAXIS);
        roundAxisRender.setRadiusPercentage(f);
        roundAxisRender.setRingInnerRadiusPercentage(f2);
        roundAxisRender.setAxisPercentage(list);
        roundAxisRender.setAxisColor(list2);
        roundAxisRender.setAxisLabels(list3);
        this.mRoundAxis.add(roundAxisRender);
    }

    public void addArcLineAxis(float f) {
        RoundAxisRender roundAxisRender = new RoundAxisRender();
        roundAxisRender.setRoundAxisType(RoundAxisType.ARCLINEAXIS);
        roundAxisRender.setRadiusPercentage(f);
        this.mRoundAxis.add(roundAxisRender);
    }

    public void addFillAxis(float f, int i) {
        RoundAxisRender roundAxisRender = new RoundAxisRender();
        roundAxisRender.setRoundAxisType(RoundAxisType.FILLAXIS);
        roundAxisRender.setRadiusPercentage(f);
        List arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(i));
        roundAxisRender.setAxisColor(arrayList);
        this.mRoundAxis.add(roundAxisRender);
    }

    public void addCircleAxis(float f, int i) {
        RoundAxisRender roundAxisRender = new RoundAxisRender();
        roundAxisRender.setRoundAxisType(RoundAxisType.CIRCLEAXIS);
        roundAxisRender.setRadiusPercentage(f);
        List arrayList = new ArrayList();
        arrayList.add(Integer.valueOf(i));
        roundAxisRender.setAxisColor(arrayList);
        this.mRoundAxis.add(roundAxisRender);
    }

    public void addLineAxis(Location location, float f) {
        RoundAxisRender roundAxisRender = new RoundAxisRender();
        roundAxisRender.setRoundAxisType(RoundAxisType.LINEAXIS);
        roundAxisRender.setRadiusPercentage(f);
        roundAxisRender.setLineAxisLocation(location);
        this.mRoundAxis.add(roundAxisRender);
    }

    protected void renderPlot(Canvas canvas) {
        try {
            float radius = getRadius();
            for (int i = 0; i < this.mRoundAxis.size(); i++) {
                RoundAxisRender roundAxisRender = (RoundAxisRender) this.mRoundAxis.get(i);
                roundAxisRender.setCenterXY(this.plotArea.getCenterX(), this.plotArea.getCenterY());
                roundAxisRender.setAngleInfo(this.mTotalAngle, this.mStartAngle);
                roundAxisRender.setOrgRadius(radius);
                roundAxisRender.render(canvas);
            }
            this.plotAttrInfoRender.renderAttrInfo(canvas, this.plotArea.getCenterX(), this.plotArea.getCenterY(), getRadius());
            renderPointerLine(canvas);
            this.mRoundAxis.clear();
            this.mPointerSet.clear();
            this.plotAttrInfoRender.clearPlotAttrInfo();
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
