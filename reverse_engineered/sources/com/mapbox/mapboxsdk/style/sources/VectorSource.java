package com.mapbox.mapboxsdk.style.sources;

import java.net.URL;

public class VectorSource extends Source {
    protected native void finalize() throws Throwable;

    protected native void initialize(String str, Object obj);

    public VectorSource(long j) {
        super(j);
    }

    public VectorSource(String str, URL url) {
        this(str, url.toExternalForm());
    }

    public VectorSource(String str, String str2) {
        initialize(str, str2);
    }

    public VectorSource(String str, TileSet tileSet) {
        initialize(str, tileSet.toValueObject());
    }
}
