package com.beastbikes.android.ble.dto;

import java.io.Serializable;
import org.json.JSONObject;

public class BleCyclingDTO implements Serializable {
    private double totalAvgSpeed;
    private int totalCount;
    private double totalDistance;
    private long totalTime;

    public BleCyclingDTO(JSONObject jSONObject) {
        this.totalDistance = jSONObject.optDouble("total_distance");
        this.totalAvgSpeed = jSONObject.optDouble("total_avgspeed");
        this.totalTime = jSONObject.optLong("total_time");
        this.totalCount = jSONObject.optInt("total_count");
    }

    public double getTotalDistance() {
        return this.totalDistance;
    }

    public void setTotalDistance(double d) {
        this.totalDistance = d;
    }

    public long getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(long j) {
        this.totalTime = j;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public void setTotalCount(int i) {
        this.totalCount = i;
    }

    public double getTotalAvgSpeed() {
        return this.totalAvgSpeed;
    }

    public void setTotalAvgSpeed(double d) {
        this.totalAvgSpeed = d;
    }
}
