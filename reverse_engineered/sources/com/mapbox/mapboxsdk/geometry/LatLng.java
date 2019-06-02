package com.mapbox.mapboxsdk.geometry;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.mapbox.mapboxsdk.utils.MathUtils;

public class LatLng implements Parcelable, ILatLng {
    public static final Creator<LatLng> CREATOR = new LatLng$1();
    private double altitude;
    private double latitude;
    private double longitude;

    public LatLng() {
        this.altitude = 0.0d;
        this.latitude = 0.0d;
        this.longitude = 0.0d;
    }

    public LatLng(double d, double d2) {
        this.altitude = 0.0d;
        this.latitude = d;
        this.longitude = d2;
    }

    public LatLng(double d, double d2, double d3) {
        this.altitude = 0.0d;
        this.latitude = d;
        this.longitude = d2;
        this.altitude = d3;
    }

    public LatLng(Location location) {
        this(location.getLatitude(), location.getLongitude(), location.getAltitude());
    }

    public LatLng(LatLng latLng) {
        this.altitude = 0.0d;
        this.latitude = latLng.latitude;
        this.longitude = latLng.longitude;
        this.altitude = latLng.altitude;
    }

    protected LatLng(Parcel parcel) {
        this.altitude = 0.0d;
        this.latitude = parcel.readDouble();
        this.longitude = parcel.readDouble();
        this.altitude = parcel.readDouble();
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setAltitude(double d) {
        this.altitude = d;
    }

    public double getAltitude() {
        return this.altitude;
    }

    public LatLng wrap() {
        LatLng latLng = new LatLng(this);
        double longitude = latLng.getLongitude();
        if (longitude < -180.0d || longitude > 180.0d) {
            latLng.setLongitude(MathUtils.wrap(latLng.getLongitude(), -180.0d, 180.0d));
        }
        return latLng;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LatLng latLng = (LatLng) obj;
        if (Double.compare(latLng.altitude, this.altitude) == 0 && Double.compare(latLng.latitude, this.latitude) == 0 && Double.compare(latLng.longitude, this.longitude) == 0) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        long doubleToLongBits = Double.doubleToLongBits(this.latitude);
        int i = (int) (doubleToLongBits ^ (doubleToLongBits >>> 32));
        long doubleToLongBits2 = Double.doubleToLongBits(this.longitude);
        i = (i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
        doubleToLongBits2 = Double.doubleToLongBits(this.altitude);
        return (i * 31) + ((int) (doubleToLongBits2 ^ (doubleToLongBits2 >>> 32)));
    }

    public String toString() {
        return "LatLng [latitude=" + this.latitude + ", longitude=" + this.longitude + ", altitude=" + this.altitude + "]";
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeDouble(this.latitude);
        parcel.writeDouble(this.longitude);
        parcel.writeDouble(this.altitude);
    }

    public double distanceTo(LatLng latLng) {
        if (this.latitude == latLng.latitude && this.longitude == latLng.longitude) {
            return 0.0d;
        }
        double toRadians = Math.toRadians(this.latitude);
        double toRadians2 = Math.toRadians(this.longitude);
        double toRadians3 = Math.toRadians(latLng.getLatitude());
        double toRadians4 = Math.toRadians(latLng.getLongitude());
        double cos = Math.cos(toRadians);
        double cos2 = Math.cos(toRadians3);
        double cos3 = ((Math.cos(toRadians2) * cos) * cos2) * Math.cos(toRadians4);
        return Math.acos((Math.sin(toRadians) * Math.sin(toRadians3)) + ((((Math.sin(toRadians2) * cos) * cos2) * Math.sin(toRadians4)) + cos3)) * 6378137.0d;
    }
}
