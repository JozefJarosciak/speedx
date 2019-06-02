package com.mapbox.mapboxsdk.style.layers;

import android.support.annotation.NonNull;

public abstract class Layer {
    private boolean invalidated;
    private long nativePtr;

    protected native void finalize() throws Throwable;

    protected native String nativeGetId();

    protected native float nativeGetMaxZoom();

    protected native float nativeGetMinZoom();

    protected native Object nativeGetVisibility();

    protected native void nativeSetFilter(Object[] objArr);

    protected native void nativeSetLayoutProperty(String str, Object obj);

    protected native void nativeSetMaxZoom(float f);

    protected native void nativeSetMinZoom(float f);

    protected native void nativeSetPaintProperty(String str, Object obj);

    protected native void nativeSetSourceLayer(String str);

    public Layer(long j) {
        this.nativePtr = j;
    }

    public void setProperties(@NonNull Property<?>... propertyArr) {
        checkValidity();
        if (propertyArr.length != 0) {
            for (Property property : propertyArr) {
                Object convertValue = convertValue(property.value);
                if (property instanceof PaintProperty) {
                    nativeSetPaintProperty(property.name, convertValue);
                } else {
                    nativeSetLayoutProperty(property.name, convertValue);
                }
            }
        }
    }

    public String getId() {
        checkValidity();
        return nativeGetId();
    }

    public PropertyValue<String> getVisibility() {
        checkValidity();
        return new PropertyValue(nativeGetVisibility());
    }

    public float getMinZoom() {
        checkValidity();
        return nativeGetMinZoom();
    }

    public float getMaxZoom() {
        checkValidity();
        return nativeGetMaxZoom();
    }

    public void setMinZoom(float f) {
        checkValidity();
        nativeSetMinZoom(f);
    }

    public void setMaxZoom(float f) {
        checkValidity();
        nativeSetMaxZoom(f);
    }

    public long getNativePtr() {
        return this.nativePtr;
    }

    private Object convertValue(Object obj) {
        return (obj == null || !(obj instanceof Function)) ? obj : ((Function) obj).toValueObject();
    }

    protected void checkValidity() {
        if (this.invalidated) {
            throw new RuntimeException("Layer has been invalidated. Request a new reference after adding");
        }
    }

    public final void invalidate() {
        this.invalidated = true;
    }
}
