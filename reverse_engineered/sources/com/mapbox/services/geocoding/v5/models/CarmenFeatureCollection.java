package com.mapbox.services.geocoding.v5.models;

import com.mapbox.services.commons.geojson.BaseFeatureCollection;
import java.util.List;

public class CarmenFeatureCollection extends BaseFeatureCollection {
    private String attribution;
    private final List<CarmenFeature> features;
    private List<String> query;

    protected CarmenFeatureCollection(List<CarmenFeature> list) {
        this.features = list;
    }

    public List<String> getQuery() {
        return this.query;
    }

    public void setQuery(List<String> list) {
        this.query = list;
    }

    public String getAttribution() {
        return this.attribution;
    }

    public void setAttribution(String str) {
        this.attribution = str;
    }

    public List<CarmenFeature> getFeatures() {
        return this.features;
    }

    public static CarmenFeatureCollection fromFeatures(List<CarmenFeature> list) {
        return new CarmenFeatureCollection(list);
    }
}
