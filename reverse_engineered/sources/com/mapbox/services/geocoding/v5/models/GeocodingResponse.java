package com.mapbox.services.geocoding.v5.models;

import java.util.List;

public class GeocodingResponse extends CarmenFeatureCollection {
    public GeocodingResponse(List<CarmenFeature> list) {
        super(list);
    }
}
