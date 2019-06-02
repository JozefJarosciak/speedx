package com.mapbox.mapboxsdk.style.sources;

public abstract class Source {
    private boolean invalidated;
    private long nativePtr;

    protected native String nativeGetId();

    public Source(long j) {
        this.nativePtr = j;
    }

    public String getId() {
        checkValidity();
        return nativeGetId();
    }

    public long getNativePtr() {
        return this.nativePtr;
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
