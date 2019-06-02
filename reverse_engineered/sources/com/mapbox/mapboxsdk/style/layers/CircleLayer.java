package com.mapbox.mapboxsdk.style.layers;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import com.mapbox.mapboxsdk.style.layers.Filter.Statement;
import com.mapbox.mapboxsdk.utils.ColorUtils;

public class CircleLayer extends Layer {
    private native Object nativeGetCircleBlur();

    private native Object nativeGetCircleColor();

    private native Object nativeGetCircleOpacity();

    private native Object nativeGetCirclePitchScale();

    private native Object nativeGetCircleRadius();

    private native Object nativeGetCircleTranslate();

    private native Object nativeGetCircleTranslateAnchor();

    protected native void finalize() throws Throwable;

    protected native void initialize(String str, String str2);

    public CircleLayer(long j) {
        super(j);
    }

    public CircleLayer(String str, String str2) {
        initialize(str, str2);
    }

    public void setSourceLayer(String str) {
        checkValidity();
        nativeSetSourceLayer(str);
    }

    public CircleLayer withSourceLayer(String str) {
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

    public CircleLayer withFilter(Object[] objArr) {
        setFilter(objArr);
        return this;
    }

    public CircleLayer withFilter(Statement statement) {
        setFilter(statement);
        return this;
    }

    public CircleLayer withProperties(@NonNull Property<?>... propertyArr) {
        setProperties(propertyArr);
        return this;
    }

    public PropertyValue<Float> getCircleRadius() {
        checkValidity();
        return new PropertyValue(nativeGetCircleRadius());
    }

    public PropertyValue<String> getCircleColor() {
        checkValidity();
        return new PropertyValue(nativeGetCircleColor());
    }

    @ColorInt
    public int getCircleColorAsInt() {
        checkValidity();
        PropertyValue circleColor = getCircleColor();
        if (circleColor.isValue()) {
            return ColorUtils.rgbaToColor((String) circleColor.getValue());
        }
        throw new RuntimeException("circle-color was set as a Function");
    }

    public PropertyValue<Float> getCircleBlur() {
        checkValidity();
        return new PropertyValue(nativeGetCircleBlur());
    }

    public PropertyValue<Float> getCircleOpacity() {
        checkValidity();
        return new PropertyValue(nativeGetCircleOpacity());
    }

    public PropertyValue<Float[]> getCircleTranslate() {
        checkValidity();
        return new PropertyValue(nativeGetCircleTranslate());
    }

    public PropertyValue<String> getCircleTranslateAnchor() {
        checkValidity();
        return new PropertyValue(nativeGetCircleTranslateAnchor());
    }

    public PropertyValue<String> getCirclePitchScale() {
        checkValidity();
        return new PropertyValue(nativeGetCirclePitchScale());
    }
}
