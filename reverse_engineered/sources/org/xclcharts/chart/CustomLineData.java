package org.xclcharts.chart;

import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.support.v4.view.ViewCompat;
import org.xclcharts.renderer.XEnum.DotStyle;
import org.xclcharts.renderer.XEnum.LineStyle;
import org.xclcharts.renderer.XEnum.VerticalAlign;

public class CustomLineData {
    private int mColor = ViewCompat.MEASURED_STATE_MASK;
    private Double mDesireValue = Double.valueOf(0.0d);
    private String mLabel = "";
    private Align mLabelAlign = Align.RIGHT;
    private int mLabelOffset = 0;
    private VerticalAlign mLabelPostion = VerticalAlign.TOP;
    private float mLabelRotateAngle = 0.0f;
    private DotStyle mLineCap = DotStyle.HIDE;
    private int mLineStroke = 0;
    private LineStyle mLineStyle = LineStyle.SOLID;
    private boolean mLineVisible = true;
    private Paint mPaintCustomLine = null;
    private Paint mPaintLineLabel = null;

    public CustomLineData(Double d, int i) {
        setValue(d);
        setColor(i);
    }

    public CustomLineData(String str, Double d, int i, int i2) {
        setLabel(str);
        setValue(d);
        setColor(i);
        setLineStroke(i2);
    }

    public String getLabel() {
        return this.mLabel;
    }

    public void setLabel(String str) {
        this.mLabel = str;
    }

    public Double getValue() {
        return this.mDesireValue;
    }

    public void setValue(Double d) {
        this.mDesireValue = d;
    }

    public int getColor() {
        return this.mColor;
    }

    public void setColor(int i) {
        this.mColor = i;
    }

    public int getLineStroke() {
        return this.mLineStroke;
    }

    public void setLineStroke(int i) {
        this.mLineStroke = i;
    }

    public boolean isSetLineStroke() {
        return this.mLineStroke != 0;
    }

    public void setCustomLineCap(DotStyle dotStyle) {
        this.mLineCap = dotStyle;
    }

    public DotStyle getCustomeLineCap() {
        return this.mLineCap;
    }

    public void setLabelHorizontalPostion(Align align) {
        this.mLabelAlign = align;
    }

    public Align getLabelHorizontalPostion() {
        return this.mLabelAlign;
    }

    public void setLabelVerticalAlign(VerticalAlign verticalAlign) {
        this.mLabelPostion = verticalAlign;
    }

    public VerticalAlign getLabelVerticalAlign() {
        return this.mLabelPostion;
    }

    public void setLineStyle(LineStyle lineStyle) {
        this.mLineStyle = lineStyle;
    }

    public LineStyle getLineStyle() {
        return this.mLineStyle;
    }

    public float getLabelRotateAngle() {
        return this.mLabelRotateAngle;
    }

    public void setLabelRotateAngle(float f) {
        this.mLabelRotateAngle = f;
    }

    public void setLabelOffset(int i) {
        this.mLabelOffset = i;
    }

    public int getLabelOffset() {
        return this.mLabelOffset;
    }

    public Paint getCustomLinePaint() {
        if (this.mPaintCustomLine == null) {
            this.mPaintCustomLine = new Paint();
            this.mPaintCustomLine.setAntiAlias(true);
            this.mPaintCustomLine.setStrokeWidth(3.0f);
            this.mPaintCustomLine.setTextSize(18.0f);
            this.mPaintCustomLine.setTextAlign(Align.LEFT);
        }
        return this.mPaintCustomLine;
    }

    public Paint getLineLabelPaint() {
        if (this.mPaintLineLabel == null) {
            this.mPaintLineLabel = new Paint();
            this.mPaintLineLabel.setAntiAlias(true);
            this.mPaintLineLabel.setStrokeWidth(3.0f);
            this.mPaintLineLabel.setTextSize(18.0f);
            this.mPaintLineLabel.setTextAlign(Align.LEFT);
        }
        return this.mPaintLineLabel;
    }

    public boolean isShowLine() {
        return this.mLineVisible;
    }

    public void hideLine() {
        this.mLineVisible = false;
    }

    public void showLine() {
        this.mLineVisible = true;
    }
}
