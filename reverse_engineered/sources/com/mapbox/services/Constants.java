package com.mapbox.services;

import java.util.Locale;

public class Constants {
    public static final String BASE_API_URL = "https://api.mapbox.com";
    public static final int GOOGLE_PRECISION = 5;
    public static final String HEADER_USER_AGENT = String.format(Locale.US, "MapboxJava/%s", new Object[]{"1.3.1"});
    public static final String MAPBOX_JAVA_VERSION = "1.3.1";
    public static final String MAPBOX_STYLE_DARK = "dark-v9";
    public static final String MAPBOX_STYLE_LIGHT = "light-v9";
    public static final String MAPBOX_STYLE_OUTDOORS = "outdoors-v9";
    public static final String MAPBOX_STYLE_SATELLITE = "satellite-v9";
    public static final String MAPBOX_STYLE_SATELLITE_HYBRID = "satellite-streets-v9";
    public static final String MAPBOX_STYLE_STREETS = "streets-v9";
    public static final String MAPBOX_USER = "mapbox";
    public static final int OSRM_PRECISION_V4 = 6;
    public static final int OSRM_PRECISION_V5 = 5;
}
