package com.mapbox.mapboxsdk.style.layers;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import com.mapbox.mapboxsdk.style.layers.Filter.Statement;
import com.mapbox.mapboxsdk.utils.ColorUtils;

public class FillLayer extends Layer {
    private native Object nativeGetFillAntialias();

    private native Object nativeGetFillColor();

    private native Object nativeGetFillOpacity();

    private native Object nativeGetFillOutlineColor();

    private native Object nativeGetFillPattern();

    private native Object nativeGetFillTranslate();

    private native Object nativeGetFillTranslateAnchor();

    protected native void finalize() throws Throwable;

    protected native void initialize(String str, String str2);

    public FillLayer(long j) {
        super(j);
    }

    public FillLayer(String str, String str2) {
        initialize(str, str2);
    }

    public void setSourceLayer(String str) {
        checkValidity();
        nativeSetSourceLayer(str);
    }

    public FillLayer withSourceLayer(String str) {
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

    public FillLayer withFilter(Object[] objArr) {
        setFilter(objArr);
        return this;
    }

    public FillLayer withFilter(Statement statement) {
        setFilter(statement);
        return this;
    }

    public FillLayer withProperties(@NonNull Property<?>... propertyArr) {
        setProperties(propertyArr);
        return this;
    }

    public PropertyValue<Boolean> getFillAntialias() {
        checkValidity();
        return new PropertyValue(nativeGetFillAntialias());
    }

    public PropertyValue<Float> getFillOpacity() {
        checkValidity();
        return new PropertyValue(nativeGetFillOpacity());
    }

    public PropertyValue<String> getFillColor() {
        checkValidity();
        return new PropertyValue(nativeGetFillColor());
    }

    @ColorInt
    public int getFillColorAsInt() {
        checkValidity();
        PropertyValue fillColor = getFillColor();
        if (fillColor.isValue()) {
            return ColorUtils.rgbaToColor((String) fillColor.getValue());
        }
        throw new RuntimeException("fill-color was set as a Function");
    }

    public PropertyValue<String> getFillOutlineColor() {
        checkValidity();
        return new PropertyValue(nativeGetFillOutlineColor());
    }

    @ColorInt
    public int getFillOutlineColorAsInt() {
        checkValidity();
        PropertyValue fillOutlineColor = getFillOutlineColor();
        if (fillOutlineColor.isValue()) {
            return ColorUtils.rgbaToColor((String) fillOutlineColor.getValue());
        }
        throw new RuntimeException("fill-outline-color was set as a Function");
    }

    public PropertyValue<Float[]> getFillTranslate() {
        checkValidity();
        return new PropertyValue(nativeGetFillTranslate());
    }

    public PropertyValue<String> getFillTranslateAnchor() {
        checkValidity();
        return new PropertyValue(nativeGetFillTranslateAnchor());
    }

    public PropertyValue<String> getFillPattern() {
        checkValidity();
        return new PropertyValue(nativeGetFillPattern());
    }
}
