package com.beastbikes.android.ble.protocol.v1;

import ch.qos.logback.core.CoreConstants;

public class CyclingSampleCharacteristic {
    private int altitude;
    private int cadence;
    private int distance;
    private int heartRate;
    private double latitude;
    private double longitude;
    private int maxCadence;
    private int maxHeartRate;
    private int maxSpeed;
    private int speed;
    private int syncDataType;
    private int timestamp;

    public int getSyncDataType() {
        return this.syncDataType;
    }

    public void setSyncDataType(int i) {
        this.syncDataType = i;
    }

    public int getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(int i) {
        this.timestamp = i;
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

    public int getAltitude() {
        return this.altitude;
    }

    public void setAltitude(int i) {
        this.altitude = i;
    }

    public int getSpeed() {
        return this.speed;
    }

    public void setSpeed(int i) {
        this.speed = i;
    }

    public int getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setMaxSpeed(int i) {
        this.maxSpeed = i;
    }

    public int getDistance() {
        return this.distance;
    }

    public void setDistance(int i) {
        this.distance = i;
    }

    public int getCadence() {
        return this.cadence;
    }

    public void setCadence(int i) {
        this.cadence = i;
    }

    public int getMaxCadence() {
        return this.maxCadence;
    }

    public void setMaxCadence(int i) {
        this.maxCadence = i;
    }

    public int getHeartRate() {
        return this.heartRate;
    }

    public void setHeartRate(int i) {
        this.heartRate = i;
    }

    public int getMaxHeartRate() {
        return this.maxHeartRate;
    }

    public void setMaxHeartRate(int i) {
        this.maxHeartRate = i;
    }

    public String toString() {
        return "CyclingSampleCharacteristic{syncDataType=" + this.syncDataType + ", timestamp=" + this.timestamp + ", latitude=" + this.latitude + ", longitude=" + this.longitude + ", altitude=" + this.altitude + ", speed=" + this.speed + ", maxSpeed=" + this.maxSpeed + ", distance=" + this.distance + ", cadence=" + this.cadence + ", maxCadence=" + this.maxCadence + ", heartRate=" + this.heartRate + ", maxHeartRate=" + this.maxHeartRate + CoreConstants.CURLY_RIGHT;
    }
}
