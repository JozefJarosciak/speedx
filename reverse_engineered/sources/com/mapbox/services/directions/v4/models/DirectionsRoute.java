package com.mapbox.services.directions.v4.models;

import com.mapbox.services.commons.geojson.LineString;
import java.util.ArrayList;
import java.util.List;

public class DirectionsRoute {
    private int distance;
    private int duration;
    private String geometry;
    private List<RouteStep> steps = new ArrayList();
    private String summary;

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int i) {
        this.duration = i;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String str) {
        this.summary = str;
    }

    public String getGeometry() {
        return this.geometry;
    }

    public void setGeometry(String str) {
        this.geometry = str;
    }

    public List<RouteStep> getSteps() {
        return this.steps;
    }

    public void setSteps(List<RouteStep> list) {
        this.steps = list;
    }

    public LineString asLineString(int i) {
        return LineString.fromPolyline(getGeometry(), i);
    }
}
