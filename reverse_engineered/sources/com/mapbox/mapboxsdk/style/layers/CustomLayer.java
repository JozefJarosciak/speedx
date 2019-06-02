package com.mapbox.mapboxsdk.style.layers;

public class CustomLayer extends Layer {
    protected native void finalize() throws Throwable;

    protected native void initialize(String str, long j, long j2, long j3, long j4);

    protected native void nativeUpdate();

    public CustomLayer(String str, long j, long j2, long j3, long j4) {
        initialize(str, j2, j3, j4, j);
    }

    public CustomLayer(long j) {
        super(j);
    }

    public void update() {
        nativeUpdate();
    }
}
