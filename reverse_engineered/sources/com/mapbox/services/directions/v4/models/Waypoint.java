package com.mapbox.services.directions.v4.models;

public class Waypoint {
    private double latitude;
    private double longitude;

    public Waypoint(double d, double d2) {
        this.longitude = d;
        this.latitude = d2;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }
}
