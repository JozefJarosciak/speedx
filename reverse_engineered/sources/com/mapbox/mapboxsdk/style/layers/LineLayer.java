package com.mapbox.mapboxsdk.style.layers;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import com.mapbox.mapboxsdk.style.layers.Filter.Statement;
import com.mapbox.mapboxsdk.utils.ColorUtils;

public class LineLayer extends Layer {
    private native Object nativeGetLineBlur();

    private native Object nativeGetLineCap();

    private native Object nativeGetLineColor();

    private native Object nativeGetLineDasharray();

    private native Object nativeGetLineGapWidth();

    private native Object nativeGetLineJoin();

    private native Object nativeGetLineMiterLimit();

    private native Object nativeGetLineOffset();

    private native Object nativeGetLineOpacity();

    private native Object nativeGetLinePattern();

    private native Object nativeGetLineRoundLimit();

    private native Object nativeGetLineTranslate();

    private native Object nativeGetLineTranslateAnchor();

    private native Object nativeGetLineWidth();

    protected native void finalize() throws Throwable;

    protected native void initialize(String str, String str2);

    public LineLayer(long j) {
        super(j);
    }

    public LineLayer(String str, String str2) {
        initialize(str, str2);
    }

    public void setSourceLayer(String str) {
        checkValidity();
        nativeSetSourceLayer(str);
    }

    public LineLayer withSourceLayer(String str) {
        setSourceLayer(str);
        return this;
    }

    public void setFilter(Statement statement) {
        checkValidity();
        setFilter(statement.toArray());
    }

    public void setFilter(Object[] objArr) {
        checkValidity();
        nativeSetFilter(objArr);
    }

    public LineLayer withFilter(Object[] objArr) {
        setFilter(objArr);
        return this;
    }

    public LineLayer withFilter(Statement statement) {
        setFilter(statement);
        return this;
    }

    public LineLayer withProperties(@NonNull Property<?>... propertyArr) {
        setProperties(propertyArr);
        return this;
    }

    public PropertyValue<String> getLineCap() {
        checkValidity();
        return new PropertyValue(nativeGetLineCap());
    }

    public PropertyValue<String> getLineJoin() {
        checkValidity();
        return new PropertyValue(nativeGetLineJoin());
    }

    public PropertyValue<Float> getLineMiterLimit() {
        checkValidity();
        return new PropertyValue(nativeGetLineMiterLimit());
    }

    public PropertyValue<Float> getLineRoundLimit() {
        checkValidity();
        return new PropertyValue(nativeGetLineRoundLimit());
    }

    public PropertyValue<Float> getLineOpacity() {
        checkValidity();
        return new PropertyValue(nativeGetLineOpacity());
    }

    public PropertyValue<String> getLineColor() {
        checkValidity();
        return new PropertyValue(nativeGetLineColor());
    }

    @ColorInt
    public int getLineColorAsInt() {
        checkValidity();
        PropertyValue lineColor = getLineColor();
        if (lineColor.isValue()) {
            return ColorUtils.rgbaToColor((String) lineColor.getValue());
        }
        throw new RuntimeException("line-color was set as a Function");
    }

    public PropertyValue<Float[]> getLineTranslate() {
        checkValidity();
        return new PropertyValue(nativeGetLineTranslate());
    }

    public PropertyValue<String> getLineTranslateAnchor() {
        checkValidity();
        return new PropertyValue(nativeGetLineTranslateAnchor());
    }

    public PropertyValue<Float> getLineWidth() {
        checkValidity();
        return new PropertyValue(nativeGetLineWidth());
    }

    public PropertyValue<Float> getLineGapWidth() {
        checkValidity();
        return new PropertyValue(nativeGetLineGapWidth());
    }

    public PropertyValue<Float> getLineOffset() {
        checkValidity();
        return new PropertyValue(nativeGetLineOffset());
    }

    public PropertyValue<Float> getLineBlur() {
        checkValidity();
        return new PropertyValue(nativeGetLineBlur());
    }

    public PropertyValue<Float[]> getLineDasharray() {
        checkValidity();
        return new PropertyValue(nativeGetLineDasharray());
    }

    public PropertyValue<String> getLinePattern() {
        checkValidity();
        return new PropertyValue(nativeGetLinePattern());
    }
}
