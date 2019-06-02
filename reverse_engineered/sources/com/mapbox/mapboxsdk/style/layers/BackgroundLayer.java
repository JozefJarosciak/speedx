package com.mapbox.mapboxsdk.style.layers;

import android.support.annotation.ColorInt;
import android.support.annotation.NonNull;
import com.mapbox.mapboxsdk.utils.ColorUtils;

public class BackgroundLayer extends Layer {
    private native Object nativeGetBackgroundColor();

    private native Object nativeGetBackgroundOpacity();

    private native Object nativeGetBackgroundPattern();

    protected native void finalize() throws Throwable;

    protected native void initialize(String str);

    public BackgroundLayer(long j) {
        super(j);
    }

    public BackgroundLayer(String str) {
        initialize(str);
    }

    public BackgroundLayer withProperties(@NonNull Property<?>... propertyArr) {
        setProperties(propertyArr);
        return this;
    }

    public PropertyValue<String> getBackgroundColor() {
        checkValidity();
        return new PropertyValue(nativeGetBackgroundColor());
    }

    @ColorInt
    public int getBackgroundColorAsInt() {
        checkValidity();
        PropertyValue backgroundColor = getBackgroundColor();
        if (backgroundColor.isValue()) {
            return ColorUtils.rgbaToColor((String) backgroundColor.getValue());
        }
        throw new RuntimeException("background-color was set as a Function");
    }

    public PropertyValue<String> getBackgroundPattern() {
        checkValidity();
        return new PropertyValue(nativeGetBackgroundPattern());
    }

    public PropertyValue<Float> getBackgroundOpacity() {
        checkValidity();
        return new PropertyValue(nativeGetBackgroundOpacity());
    }
}
