package com.mapbox.services.directions.v5.models;

import java.util.List;

public class LegStep {
    private double distance;
    private double duration;
    private String geometry;
    private List<StepIntersection> intersections;
    private StepManeuver maneuver;
    private String mode;
    private String name;

    public double getDistance() {
        return this.distance;
    }

    public double getDuration() {
        return this.duration;
    }

    public String getGeometry() {
        return this.geometry;
    }

    public String getName() {
        return this.name;
    }

    public String getMode() {
        return this.mode;
    }

    public StepManeuver getManeuver() {
        return this.maneuver;
    }

    public List<StepIntersection> getIntersections() {
        return this.intersections;
    }
}
