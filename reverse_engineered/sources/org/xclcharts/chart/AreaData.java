package org.xclcharts.chart;

import android.graphics.Shader.TileMode;
import java.util.List;
import org.xclcharts.renderer.XEnum.Direction;
import org.xclcharts.renderer.XEnum.DotStyle;

public class AreaData extends LineData {
    private boolean mApplayGradient = false;
    private int mAreaBeginColor = -1;
    private int mAreaEndColor = -1;
    private int mAreaFillColor = -999;
    private Direction mDirection = Direction.VERTICAL;
    private TileMode mTileMode = TileMode.MIRROR;

    public AreaData(String str, int i, List<Double> list) {
    }

    public AreaData(String str, List<Double> list, int i, int i2) {
        setLabel(str);
        setDataSet(list);
        setLineColor(i);
        setAreaFillColor(i2);
        setAreaBeginColor(i2);
        setAreaEndColor(-1);
    }

    public AreaData(String str, List<Double> list, int i, int i2, int i3) {
        setLabel(str);
        setDataSet(list);
        setLineColor(i);
        setAreaFillColor(i2);
        setApplayGradient(true);
        setAreaBeginColor(i2);
        setAreaEndColor(i3);
    }

    public AreaData(String str, List<Double> list, int i, DotStyle dotStyle) {
        setLabel(str);
        setLineColor(i);
        setDataSet(list);
        setDotStyle(dotStyle);
        setAreaFillColor(i);
        setAreaBeginColor(i);
        setAreaEndColor(-1);
    }

    public void setAreaFillColor(int i) {
        this.mAreaFillColor = i;
    }

    public int getAreaFillColor() {
        return this.mAreaFillColor;
    }

    public void setApplayGradient(boolean z) {
        this.mApplayGradient = z;
    }

    public boolean getApplayGradient() {
        return this.mApplayGradient;
    }

    public void setGradientDirection(Direction direction) {
        this.mDirection = direction;
    }

    public Direction getGradientDirection() {
        return this.mDirection;
    }

    public void setGradientMode(TileMode tileMode) {
        this.mTileMode = tileMode;
    }

    public TileMode getGradientMode() {
        return this.mTileMode;
    }

    public void setAreaBeginColor(int i) {
        this.mAreaBeginColor = i;
    }

    public void setAreaEndColor(int i) {
        this.mAreaEndColor = i;
    }

    public int getAreaBeginColor() {
        return this.mAreaBeginColor;
    }

    public int getAreaEndColor() {
        return this.mAreaEndColor;
    }
}
