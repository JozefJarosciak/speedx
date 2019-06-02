package com.mapbox.services.directions.v4.models;

import java.util.ArrayList;
import java.util.List;

public class FeatureGeometry {
    private List<Double> coordinates = new ArrayList();
    private String type;

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }

    public List<Double> getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinates(List<Double> list) {
        this.coordinates = list;
    }
}
