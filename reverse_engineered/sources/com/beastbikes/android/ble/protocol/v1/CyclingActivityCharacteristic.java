package com.beastbikes.android.ble.protocol.v1;

import ch.qos.logback.core.CoreConstants;

public class CyclingActivityCharacteristic {
    private int climbHeight;
    private int sampleCount;
    private int sampleRate;
    private int startTime;
    private int stopTime;
    private int syncDataType;
    private int totalDistance;
    private int totalTime;

    public int getSyncDataType() {
        return this.syncDataType;
    }

    public void setSyncDataType(int i) {
        this.syncDataType = i;
    }

    public int getStopTime() {
        return this.stopTime;
    }

    public void setStopTime(int i) {
        this.stopTime = i;
    }

    public int getStartTime() {
        return this.startTime;
    }

    public void setStartTime(int i) {
        this.startTime = i;
    }

    public int getSampleRate() {
        return this.sampleRate;
    }

    public void setSampleRate(int i) {
        this.sampleRate = i;
    }

    public int getTotalDistance() {
        return this.totalDistance;
    }

    public void setTotalDistance(int i) {
        this.totalDistance = i;
    }

    public int getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(int i) {
        this.totalTime = i;
    }

    public int getSampleCount() {
        return this.sampleCount;
    }

    public void setSampleCount(int i) {
        this.sampleCount = i;
    }

    public int getClimbHeight() {
        return this.climbHeight;
    }

    public void setClimbHeight(int i) {
        this.climbHeight = i;
    }

    public String toString() {
        return "CyclingActivityCharacteristic{syncDataType=" + this.syncDataType + ", stopTime=" + this.stopTime + ", startTime=" + this.startTime + ", sampleRate=" + this.sampleRate + ", totalDistance=" + this.totalDistance + ", totalTime=" + this.totalTime + ", sampleCount=" + this.sampleCount + ", climbHeight=" + this.climbHeight + CoreConstants.CURLY_RIGHT;
    }
}
