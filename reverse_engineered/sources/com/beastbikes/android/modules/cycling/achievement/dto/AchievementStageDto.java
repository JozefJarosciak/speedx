package com.beastbikes.android.modules.cycling.achievement.dto;

import java.io.Serializable;

public class AchievementStageDto implements Serializable {
    private String activityId;
    private long date;
    private String mName;
    private double mTimeCost;
    private int rank;

    public int getRank() {
        return this.rank;
    }

    public void setRank(int i) {
        this.rank = i;
    }

    public String getmName() {
        return this.mName;
    }

    public void setmName(String str) {
        this.mName = str;
    }

    public double getmTimeCost() {
        return this.mTimeCost;
    }

    public void setmTimeCost(double d) {
        this.mTimeCost = d;
    }

    public long getDate() {
        return this.date;
    }

    public void setDate(long j) {
        this.date = j;
    }

    public String getActivityId() {
        return this.activityId;
    }

    public void setActivityId(String str) {
        this.activityId = str;
    }
}
