package com.mapbox.mapboxsdk.style.layers;

import android.support.annotation.NonNull;

public class RasterLayer extends Layer {
    private native Object nativeGetRasterBrightnessMax();

    private native Object nativeGetRasterBrightnessMin();

    private native Object nativeGetRasterContrast();

    private native Object nativeGetRasterFadeDuration();

    private native Object nativeGetRasterHueRotate();

    private native Object nativeGetRasterOpacity();

    private native Object nativeGetRasterSaturation();

    protected native void finalize() throws Throwable;

    protected native void initialize(String str, String str2);

    public RasterLayer(long j) {
        super(j);
    }

    public RasterLayer(String str, String str2) {
        initialize(str, str2);
    }

    public void setSourceLayer(String str) {
        checkValidity();
        nativeSetSourceLayer(str);
    }

    public RasterLayer withSourceLayer(String str) {
        setSourceLayer(str);
        return this;
    }

    public RasterLayer withProperties(@NonNull Property<?>... propertyArr) {
        setProperties(propertyArr);
        return this;
    }

    public PropertyValue<Float> getRasterOpacity() {
        checkValidity();
        return new PropertyValue(nativeGetRasterOpacity());
    }

    public PropertyValue<Float> getRasterHueRotate() {
        checkValidity();
        return new PropertyValue(nativeGetRasterHueRotate());
    }

    public PropertyValue<Float> getRasterBrightnessMin() {
        checkValidity();
        return new PropertyValue(nativeGetRasterBrightnessMin());
    }

    public PropertyValue<Float> getRasterBrightnessMax() {
        checkValidity();
        return new PropertyValue(nativeGetRasterBrightnessMax());
    }

    public PropertyValue<Float> getRasterSaturation() {
        checkValidity();
        return new PropertyValue(nativeGetRasterSaturation());
    }

    public PropertyValue<Float> getRasterContrast() {
        checkValidity();
        return new PropertyValue(nativeGetRasterContrast());
    }

    public PropertyValue<Float> getRasterFadeDuration() {
        checkValidity();
        return new PropertyValue(nativeGetRasterFadeDuration());
    }
}
