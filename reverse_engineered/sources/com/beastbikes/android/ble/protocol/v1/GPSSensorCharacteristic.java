package com.beastbikes.android.ble.protocol.v1;

import ch.qos.logback.core.CoreConstants;

public class GPSSensorCharacteristic extends SensorCharacteristic {
    private int accuracy;
    private float altitude;
    private double latitude;
    private double longitude;

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

    public float getAltitude() {
        return this.altitude;
    }

    public void setAltitude(float f) {
        this.altitude = f;
    }

    public int getAccuracy() {
        return this.accuracy;
    }

    public void setAccuracy(int i) {
        this.accuracy = i;
    }

    public String toString() {
        return "GPSSensorCharacteristic{latitude=" + this.latitude + ", longitude=" + this.longitude + ", altitude=" + this.altitude + ", accuracy=" + this.accuracy + CoreConstants.CURLY_RIGHT;
    }
}
