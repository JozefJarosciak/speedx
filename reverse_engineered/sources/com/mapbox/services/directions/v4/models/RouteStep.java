package com.mapbox.services.directions.v4.models;

import com.google.gson.annotations.SerializedName;

public class RouteStep {
    private String direction;
    private int distance;
    private int duration;
    private double heading;
    private StepManeuver maneuver;
    @SerializedName("wayName")
    private String wayName;

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

    public String getWayName() {
        return this.wayName;
    }

    public void setWayName(String str) {
        this.wayName = str;
    }

    public String getDirection() {
        return this.direction;
    }

    public void setDirection(String str) {
        this.direction = str;
    }

    public double getHeading() {
        return this.heading;
    }

    public void setHeading(double d) {
        this.heading = d;
    }

    public StepManeuver getManeuver() {
        return this.maneuver;
    }

    public void setManeuver(StepManeuver stepManeuver) {
        this.maneuver = stepManeuver;
    }
}
