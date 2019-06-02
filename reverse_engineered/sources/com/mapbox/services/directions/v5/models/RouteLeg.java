package com.mapbox.services.directions.v5.models;

import java.util.List;

public class RouteLeg {
    private double distance;
    private double duration;
    private List<LegStep> steps;
    private String summary;

    public double getDistance() {
        return this.distance;
    }

    public double getDuration() {
        return this.duration;
    }

    public String getSummary() {
        return this.summary;
    }

    public List<LegStep> getSteps() {
        return this.steps;
    }
}
