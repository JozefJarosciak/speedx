package com.mapbox.services.geocoding.v5.models;

import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;
import com.mapbox.services.commons.geojson.Feature;
import com.mapbox.services.commons.geojson.Geometry;
import com.mapbox.services.commons.models.Position;
import java.util.List;

public class CarmenFeature extends Feature {
    private String address;
    private double[] bbox;
    private double[] center;
    private List<CarmenContext> context;
    @SerializedName("place_name")
    private String placeName;
    private double relevance;
    private String text;

    private CarmenFeature(Geometry geometry, JsonObject jsonObject, String str) {
        super(geometry, jsonObject, str);
    }

    public CarmenFeature() {
        super(null, null, null);
    }

    public String getText() {
        return this.text;
    }

    public String getPlaceName() {
        return this.placeName;
    }

    public double[] getBbox() {
        return this.bbox;
    }

    public String getAddress() {
        return this.address;
    }

    public double[] getCenter() {
        return this.center;
    }

    public List<CarmenContext> getContext() {
        return this.context;
    }

    public double getRelevance() {
        return this.relevance;
    }

    public void setText(String str) {
        this.text = str;
    }

    public void setPlaceName(String str) {
        this.placeName = str;
    }

    public void setBbox(double[] dArr) {
        this.bbox = dArr;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public void setCenter(double[] dArr) {
        this.center = dArr;
    }

    public void setContext(List<CarmenContext> list) {
        this.context = list;
    }

    public void setRelevance(double d) {
        this.relevance = d;
    }

    public Position asPosition() {
        return Position.fromCoordinates(this.center[0], this.center[1]);
    }

    public String toString() {
        return getPlaceName();
    }
}
