package com.beastbikes.android.ble.protocol.v1;

import ch.qos.logback.core.CoreConstants;

public class SampleCharacteristic extends AbstractCharacteristicValue {
    private int antPlusSpeed;
    private int cadence;
    private int gpsSpeed;
    private int heartBeatRate;
    private long timestamp;
    private int totalDistance;
    private int totalTime;

    public int getTotalDistance() {
        return this.totalDistance;
    }

    public void setTotalDistance(int i) {
        this.totalDistance = i;
    }

    public long getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(long j) {
        this.timestamp = j;
    }

    public int getGpsSpeed() {
        return this.gpsSpeed;
    }

    public void setGpsSpeed(int i) {
        this.gpsSpeed = i;
    }

    public int getAntPlusSpeed() {
        return this.antPlusSpeed;
    }

    public void setAntPlusSpeed(int i) {
        this.antPlusSpeed = i;
    }

    public int getCadence() {
        return this.cadence;
    }

    public void setCadence(int i) {
        this.cadence = i;
    }

    public int getHeartBeatRate() {
        return this.heartBeatRate;
    }

    public void setHeartBeatRate(int i) {
        this.heartBeatRate = i;
    }

    public int getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(int i) {
        this.totalTime = i;
    }

    public String toString() {
        return "SampleCharacteristic{totalDistance=" + this.totalDistance + ", timestamp=" + this.timestamp + ", gpsSpeed=" + this.gpsSpeed + ", antPlusSpeed=" + this.antPlusSpeed + ", cadence=" + this.cadence + ", heartBeatRate=" + this.heartBeatRate + ", totalTime=" + this.totalTime + CoreConstants.CURLY_RIGHT;
    }
}
