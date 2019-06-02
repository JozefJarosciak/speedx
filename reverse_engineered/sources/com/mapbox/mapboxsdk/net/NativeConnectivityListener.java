package com.mapbox.mapboxsdk.net;

class NativeConnectivityListener implements ConnectivityListener {
    private boolean invalidated;
    private long nativePtr;

    protected native void finalize() throws Throwable;

    protected native void initialize();

    protected native void nativeOnConnectivityStateChanged(boolean z);

    static {
        System.loadLibrary("mapbox-gl");
    }

    NativeConnectivityListener(long j) {
        this.nativePtr = j;
    }

    NativeConnectivityListener() {
        initialize();
    }

    public void onNetworkStateChanged(boolean z) {
        nativeOnConnectivityStateChanged(z);
    }
}
