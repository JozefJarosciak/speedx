package org.xclcharts.renderer.plot;

import android.graphics.Paint;
import java.util.ArrayList;
import java.util.List;
import org.xclcharts.renderer.XEnum.Location;

public class PlotAttrInfo {
    protected List<String> mAttrInfo = null;
    protected List<Location> mAttrInfoLocation = null;
    protected List<Paint> mAttrInfoPaint = null;
    protected List<Float> mAttrInfoPostion = null;

    public List<String> getPlotAttrInfo() {
        return this.mAttrInfo;
    }

    public List<Float> getPlotAttrInfoPostion() {
        return this.mAttrInfoPostion;
    }

    public List<Paint> getPlotAttrInfoPaint() {
        return this.mAttrInfoPaint;
    }

    public void clearPlotAttrInfo() {
        if (this.mAttrInfoLocation != null) {
            this.mAttrInfoLocation.clear();
        }
        if (this.mAttrInfo != null) {
            this.mAttrInfo.clear();
        }
        if (this.mAttrInfoPostion != null) {
            this.mAttrInfoPostion.clear();
        }
        if (this.mAttrInfoPaint != null) {
            this.mAttrInfoPaint.clear();
        }
    }

    public void addAttributeInfo(Location location, String str, float f, Paint paint) {
        if (this.mAttrInfoLocation == null) {
            this.mAttrInfoLocation = new ArrayList();
        }
        if (this.mAttrInfo == null) {
            this.mAttrInfo = new ArrayList();
        }
        if (this.mAttrInfoPostion == null) {
            this.mAttrInfoPostion = new ArrayList();
        }
        if (this.mAttrInfoPaint == null) {
            this.mAttrInfoPaint = new ArrayList();
        }
        this.mAttrInfoLocation.add(location);
        this.mAttrInfo.add(str);
        this.mAttrInfoPostion.add(Float.valueOf(f));
        this.mAttrInfoPaint.add(paint);
    }
}
