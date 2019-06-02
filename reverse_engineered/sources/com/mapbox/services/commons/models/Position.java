package com.mapbox.services.commons.models;

public class Position {
    private final double altitude;
    private final double latitude;
    private final double longitude;

    private Position(double d, double d2, double d3) {
        this.longitude = d;
        this.latitude = d2;
        this.altitude = d3;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public double getAltitude() {
        return this.altitude;
    }

    public double[] getCoordinates() {
        if (hasAltitude()) {
            return new double[]{getLongitude(), getLatitude(), getAltitude()};
        }
        return new double[]{getLongitude(), getLatitude()};
    }

    public static Position fromCoordinates(double d, double d2, double d3) {
        return new Position(d, d2, d3);
    }

    public static Position fromCoordinates(double d, double d2) {
        return new Position(d, d2, Double.NaN);
    }

    public static Position fromCoordinates(double[] dArr) {
        if (dArr.length == 3) {
            return fromCoordinates(dArr[0], dArr[1], dArr[2]);
        }
        return fromCoordinates(dArr[0], dArr[1]);
    }

    public boolean hasAltitude() {
        return !Double.isNaN(this.altitude);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Position)) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        Position position = (Position) obj;
        if (position.getLatitude() != this.latitude || position.getLongitude() != this.longitude || Double.isNaN(position.getAltitude()) != Double.isNaN(this.altitude)) {
            return false;
        }
        if (Double.isNaN(this.altitude) || position.getAltitude() == this.altitude) {
            return true;
        }
        return false;
    }

    public String toString() {
        return "Position [longitude=" + this.longitude + ", latitude=" + this.latitude + ", altitude=" + this.altitude + "]";
    }
}
