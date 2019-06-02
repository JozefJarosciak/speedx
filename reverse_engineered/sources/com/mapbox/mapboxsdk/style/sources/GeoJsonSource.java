package com.mapbox.mapboxsdk.style.sources;

import com.alipay.sdk.packet.C0861d;
import com.mapbox.services.commons.geojson.FeatureCollection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import org.apache.http.HttpHost;

public class GeoJsonSource extends Source {
    private native void nativeSetGeoJson(Object obj);

    protected native void finalize() throws Throwable;

    protected native void initialize(String str, Object obj);

    protected native void nativeSetUrl(String str);

    public GeoJsonSource(long j) {
        super(j);
    }

    public GeoJsonSource(String str) {
        initialize(str, null);
        setGeoJson(FeatureCollection.fromFeatures(new ArrayList()));
    }

    public GeoJsonSource(String str, GeoJsonOptions geoJsonOptions) {
        initialize(str, geoJsonOptions);
        setGeoJson(FeatureCollection.fromFeatures(new ArrayList()));
    }

    public GeoJsonSource(String str, String str2) {
        if (str2 == null || str2.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
            throw new IllegalArgumentException("Expected a raw json body");
        }
        initialize(str, null);
        setGeoJson(str2);
    }

    public GeoJsonSource(String str, String str2, GeoJsonOptions geoJsonOptions) {
        if (str2 == null || str2.startsWith(HttpHost.DEFAULT_SCHEME_NAME)) {
            throw new IllegalArgumentException("Expected a raw json body");
        }
        initialize(str, geoJsonOptions);
        setGeoJson(str2);
    }

    public GeoJsonSource(String str, URL url) {
        initialize(str, null);
        nativeSetUrl(url.toExternalForm());
    }

    public GeoJsonSource(String str, URL url, GeoJsonOptions geoJsonOptions) {
        initialize(str, geoJsonOptions);
        nativeSetUrl(url.toExternalForm());
    }

    public GeoJsonSource(String str, FeatureCollection featureCollection) {
        initialize(str, null);
        setGeoJson(featureCollection);
    }

    public GeoJsonSource(String str, FeatureCollection featureCollection, GeoJsonOptions geoJsonOptions) {
        initialize(str, geoJsonOptions);
        setGeoJson(featureCollection);
    }

    public void setGeoJson(FeatureCollection featureCollection) {
        checkValidity();
        setGeoJson(featureCollection.toJson());
    }

    public void setGeoJson(String str) {
        checkValidity();
        setRawJson(str);
    }

    public void setUrl(URL url) {
        checkValidity();
        setUrl(url.toExternalForm());
    }

    public void setUrl(String str) {
        checkValidity();
        nativeSetUrl(str);
    }

    protected void setRawJson(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put(C0861d.f2139k, str);
        nativeSetGeoJson(hashMap);
    }
}
