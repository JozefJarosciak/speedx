package com.avos.avoscloud;

import android.location.Location;

public class AVGeoPoint {
    static double EARTH_MEAN_RADIUS_KM = 6378.14d;
    static double ONE_KM_TO_MILES = 1.609344d;
    private double latitude;
    private double longitude;

    public AVGeoPoint() {
        this.latitude = 0.0d;
        this.longitude = 0.0d;
    }

    public double distanceInKilometersTo(AVGeoPoint aVGeoPoint) {
        Location location = new Location("");
        location.setLatitude(this.latitude);
        location.setLongitude(this.longitude);
        Location location2 = new Location("");
        location2.setLatitude(aVGeoPoint.latitude);
        location2.setLongitude(aVGeoPoint.longitude);
        return (double) (location.distanceTo(location2) / 1000.0f);
    }

    public double distanceInMilesTo(AVGeoPoint aVGeoPoint) {
        return distanceInKilometersTo(aVGeoPoint) / ONE_KM_TO_MILES;
    }

    public double distanceInRadiansTo(AVGeoPoint aVGeoPoint) {
        return distanceInKilometersTo(aVGeoPoint) / EARTH_MEAN_RADIUS_KM;
    }

    public AVGeoPoint(double d, double d2) {
        this.latitude = d;
        this.longitude = d2;
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
