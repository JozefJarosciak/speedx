package com.mapbox.mapboxsdk.constants;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Style {
    public static final String DARK = "mapbox://styles/mapbox/dark-v9";
    @Deprecated
    public static final String EMERALD = "mapbox://styles/mapbox/emerald-v8";
    public static final String LIGHT = "mapbox://styles/mapbox/light-v9";
    public static final String MAPBOX_STREETS = "mapbox://styles/mapbox/streets-v9";
    public static final String OUTDOORS = "mapbox://styles/mapbox/outdoors-v9";
    public static final String SATELLITE = "mapbox://styles/mapbox/satellite-v9";
    public static final String SATELLITE_STREETS = "mapbox://styles/mapbox/satellite-streets-v9";

    @Retention(RetentionPolicy.SOURCE)
    public @interface StyleUrl {
    }
}
