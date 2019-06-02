package com.mapbox.services.directions.v4.models;

public class DirectionsFeature {
    private FeatureGeometry geometry;
    private FeatureProperties properties;
    private String type;

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public FeatureGeometry getGeometry() {
        return this.geometry;
    }

    public void setGeometry(FeatureGeometry featureGeometry) {
        this.geometry = featureGeometry;
    }

    public FeatureProperties getProperties() {
        return this.properties;
    }

    public void setProperties(FeatureProperties featureProperties) {
        this.properties = featureProperties;
    }
}
