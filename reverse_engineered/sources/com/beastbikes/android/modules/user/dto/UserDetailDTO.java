package com.beastbikes.android.modules.user.dto;

import android.text.TextUtils;
import com.beastbikes.android.utils.C2555d;
import java.io.Serializable;
import java.util.Date;
import org.json.JSONObject;

public class UserDetailDTO implements Serializable {
    private static final long serialVersionUID = 6374588791743399277L;
    private double averageSpeed;
    private boolean existRoute;
    private long latestActivityTime;
    private double longestDistance;
    private String longestDistanceId;
    private double monthlyDistance;
    private double monthlyGoal;
    private int totalActivities;
    private double totalCalories;
    private double totalDistance;
    private long totalElapsedTime;
    private String userId;

    public UserDetailDTO(JSONObject jSONObject) {
        this.totalCalories = jSONObject.optDouble("totalCalories", 0.0d);
        this.totalDistance = jSONObject.optDouble("totalDistance", 0.0d);
        this.monthlyDistance = jSONObject.optDouble("monthlyDistance", 0.0d);
        this.longestDistance = jSONObject.optDouble("longestDistance", 0.0d);
        this.totalElapsedTime = jSONObject.optLong("totalTime", 0);
        this.longestDistanceId = jSONObject.optString("longestDistanceIdentity");
        this.averageSpeed = jSONObject.optDouble("averageSpeed", 0.0d);
        String optString = jSONObject.optString("latestCyclingTime");
        C2555d.m12799a(new Date());
        C2555d.m12796a(System.currentTimeMillis());
        if (!TextUtils.isEmpty(optString)) {
            this.latestActivityTime = C2555d.m12800b(optString);
        }
        this.totalActivities = jSONObject.optInt("totalCount", 0);
    }

    public UserDetailDTO(JSONObject jSONObject, boolean z) {
        boolean z2 = true;
        this.totalDistance = jSONObject.optDouble("total_distance", 0.0d);
        this.monthlyDistance = jSONObject.optDouble("monthly_distance", 0.0d);
        this.totalElapsedTime = jSONObject.optLong("total_time", 0);
        this.averageSpeed = jSONObject.optDouble("avg_speed", 0.0d);
        this.totalActivities = jSONObject.optInt("total_count", 0);
        this.monthlyGoal = jSONObject.optDouble("monthly_goal", 0.0d);
        if (jSONObject.optInt("exist_route", 0) != 1) {
            z2 = false;
        }
        this.existRoute = z2;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public double getTotalCalories() {
        return this.totalCalories;
    }

    public void setTotalCalories(double d) {
        this.totalCalories = d;
    }

    public double getTotalDistance() {
        return this.totalDistance;
    }

    public void setTotalDistance(double d) {
        this.totalDistance = d;
    }

    public double getMonthlyDistance() {
        return this.monthlyDistance;
    }

    public void setMonthlyDistance(double d) {
        this.monthlyDistance = d;
    }

    public double getLongestDistance() {
        return this.longestDistance;
    }

    public void setLongestDistance(double d) {
        this.longestDistance = d;
    }

    public String getLongestDistanceIdentity() {
        return this.longestDistanceId;
    }

    public void setLongestDistanceIdentity(String str) {
        this.longestDistanceId = str;
    }

    public long getTotalElapsedTime() {
        return this.totalElapsedTime;
    }

    public void setTotalElapsedTime(long j) {
        this.totalElapsedTime = j;
    }

    public double getAverageSpeed() {
        return this.averageSpeed;
    }

    public void setAverageSpeed(double d) {
        this.averageSpeed = d;
    }

    public long getLatestActivityTime() {
        return this.latestActivityTime;
    }

    public void setLatestActivityTime(long j) {
        this.latestActivityTime = j;
    }

    public int getTotalCount() {
        return this.totalActivities;
    }

    public void setTotalCount(int i) {
        this.totalActivities = i;
    }

    public boolean isExistRoute() {
        return this.existRoute;
    }

    public void setExistRoute(boolean z) {
        this.existRoute = z;
    }

    public double getMonthlyGoal() {
        return this.monthlyGoal;
    }

    public void setMonthlyGoal(double d) {
        this.monthlyGoal = d;
    }
}
