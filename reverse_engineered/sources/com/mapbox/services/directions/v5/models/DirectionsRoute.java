package com.mapbox.services.directions.v5.models;

import java.util.List;

public class DirectionsRoute {
    private double distance;
    private double duration;
    private String geometry;
    private List<RouteLeg> legs;

    public double getDistance() {
        return this.distance;
    }

    public double getDuration() {
        return this.duration;
    }

    public String getGeometry() {
        return this.geometry;
    }

    public List<RouteLeg> getLegs() {
        return this.legs;
    }
}
