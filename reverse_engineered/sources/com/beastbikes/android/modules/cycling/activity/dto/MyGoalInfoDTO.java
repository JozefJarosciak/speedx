package com.beastbikes.android.modules.cycling.activity.dto;

import ch.qos.logback.core.CoreConstants;
import java.io.Serializable;
import org.json.JSONObject;

public class MyGoalInfoDTO implements Serializable {
    private double curGoal;
    private double monthAvgSpeed;
    private int monthCount;
    private double monthTime;
    private double myGoal;

    public MyGoalInfoDTO(JSONObject jSONObject) {
        this.curGoal = jSONObject.optDouble("curGoal");
        this.myGoal = jSONObject.optDouble("myGoal");
        this.monthAvgSpeed = jSONObject.optDouble("monthAvgSpeed");
        this.monthTime = jSONObject.optDouble("monthTime");
        this.monthCount = jSONObject.optInt("monthCount");
    }

    public double getCurGoal() {
        return this.curGoal;
    }

    public void setCurGoal(double d) {
        this.curGoal = d;
    }

    public double getMyGoal() {
        return this.myGoal;
    }

    public void setMyGoal(double d) {
        this.myGoal = d;
    }

    public double getMonthAvgSpeed() {
        return this.monthAvgSpeed;
    }

    public void setMonthAvgSpeed(double d) {
        this.monthAvgSpeed = d;
    }

    public double getMonthTime() {
        return this.monthTime;
    }

    public void setMonthTime(double d) {
        this.monthTime = d;
    }

    public int getMonthCount() {
        return this.monthCount;
    }

    public void setMonthCount(int i) {
        this.monthCount = i;
    }

    public String toString() {
        return "MyGoalInfoDTO{curGoal=" + this.curGoal + ", myGoal=" + this.myGoal + ", monthAvgSpeed=" + this.monthAvgSpeed + ", monthTime=" + this.monthTime + ", monthCount=" + this.monthCount + CoreConstants.CURLY_RIGHT;
    }
}
