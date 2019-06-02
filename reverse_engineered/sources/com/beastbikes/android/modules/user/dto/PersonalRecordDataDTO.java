package com.beastbikes.android.modules.user.dto;

import java.io.Serializable;
import org.json.JSONObject;

public class PersonalRecordDataDTO implements Serializable {
    private int avgCadence;
    private int avgHeartRate;
    private int avgPower;
    private double avgSpeed;
    private int cyclingRank;
    private int cyclingTimes;
    private int maxCadence;
    private long maxCyclingDuration;
    private double maxDistance;
    private int maxHeartRate;
    private int maxPower;
    private double maxSpeed;
    private int outputPower;
    private String time;
    private double totalAscent;
    private double totalCalorie;
    private double totalMileage;
    private long totalTime;

    public PersonalRecordDataDTO(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.totalMileage = jSONObject.optDouble("total_distance");
            this.totalTime = (long) jSONObject.optInt("total_time");
            this.avgSpeed = jSONObject.optDouble("avg_speed");
            this.cyclingTimes = jSONObject.optInt("total_count");
            this.avgCadence = jSONObject.optInt("avg_cadence");
            this.avgPower = jSONObject.optInt("avg_watts");
            this.avgHeartRate = jSONObject.optInt("avg_cardiac_rate");
            this.maxDistance = jSONObject.optDouble("max_distance");
            this.maxSpeed = jSONObject.optDouble("max_speed");
            this.totalCalorie = (double) jSONObject.optInt("total_calories");
            this.totalAscent = jSONObject.optDouble("total_rise");
            this.cyclingRank = jSONObject.optInt("distance_rank");
            this.maxCyclingDuration = (long) jSONObject.optInt("max_time");
            this.maxCadence = jSONObject.optInt("max_cadence");
            this.maxHeartRate = jSONObject.optInt("max_cardiac_rate");
            this.maxPower = jSONObject.optInt("max_watts");
            this.outputPower = jSONObject.optInt("total_kilojoules");
        }
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public double getTotalMileage() {
        return this.totalMileage;
    }

    public void setTotalMileage(double d) {
        this.totalMileage = d;
    }

    public long getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(long j) {
        this.totalTime = j;
    }

    public double getAvgSpeed() {
        return this.avgSpeed;
    }

    public void setAvgSpeed(double d) {
        this.avgSpeed = d;
    }

    public int getCyclingTimes() {
        return this.cyclingTimes;
    }

    public void setCyclingTimes(int i) {
        this.cyclingTimes = i;
    }

    public int getAvgCadence() {
        return this.avgCadence;
    }

    public void setAvgCadence(int i) {
        this.avgCadence = i;
    }

    public int getAvgPower() {
        return this.avgPower;
    }

    public void setAvgPower(int i) {
        this.avgPower = i;
    }

    public int getAvgHeartRate() {
        return this.avgHeartRate;
    }

    public void setAvgHeartRate(int i) {
        this.avgHeartRate = i;
    }

    public double getMaxDistance() {
        return this.maxDistance;
    }

    public void setMaxDistance(double d) {
        this.maxDistance = d;
    }

    public double getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setMaxSpeed(double d) {
        this.maxSpeed = d;
    }

    public double getTotalCalorie() {
        return this.totalCalorie;
    }

    public void setTotalCalorie(double d) {
        this.totalCalorie = d;
    }

    public double getTotalAscent() {
        return this.totalAscent;
    }

    public void setTotalAscent(double d) {
        this.totalAscent = d;
    }

    public int getCyclingRank() {
        return this.cyclingRank;
    }

    public void setCyclingRank(int i) {
        this.cyclingRank = i;
    }

    public long getMaxCyclingDuration() {
        return this.maxCyclingDuration;
    }

    public void setMaxCyclingDuration(long j) {
        this.maxCyclingDuration = j;
    }

    public int getMaxCadence() {
        return this.maxCadence;
    }

    public void setMaxCadence(int i) {
        this.maxCadence = i;
    }

    public int getMaxHeartRate() {
        return this.maxHeartRate;
    }

    public void setMaxHeartRate(int i) {
        this.maxHeartRate = i;
    }

    public int getMaxPower() {
        return this.maxPower;
    }

    public void setMaxPower(int i) {
        this.maxPower = i;
    }

    public int getOutputPower() {
        return this.outputPower;
    }

    public void setOutputPower(int i) {
        this.outputPower = i;
    }
}
