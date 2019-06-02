package com.beastbikes.android.modules.cycling.ranking.dto;

import io.rong.imlib.statistics.UserData;
import java.io.Serializable;
import org.json.JSONObject;

public class RankDTO implements Serializable {
    private String area;
    private String avatarUrl;
    private String city;
    private String clubName;
    private String district;
    private String email;
    private boolean isManager;
    private String joined;
    private long lastActivityTime;
    private int level;
    private double milestone;
    private double monthlyDistance;
    private String nickname;
    private int ordinal;
    private String province;
    private double rankDistance;
    private int rankType;
    private String remarks;
    private double score;
    private double totalDistance;
    private String userId;
    private int userIntId;
    private String username;
    private double weeklyDistance;
    private double yearlyDistance;

    public RankDTO(JSONObject jSONObject) {
        this.userId = jSONObject.optString("userId");
        this.username = jSONObject.optString(UserData.USERNAME_KEY);
        this.nickname = jSONObject.optString("nickname");
        this.email = jSONObject.optString("email");
        this.province = jSONObject.optString("province");
        this.city = jSONObject.optString("city");
        this.district = jSONObject.optString("area");
        this.weeklyDistance = jSONObject.optDouble("weeklyDistance", 0.0d);
        this.monthlyDistance = jSONObject.optDouble("monthlyDistance", 0.0d);
        this.totalDistance = jSONObject.optDouble("totalDistance", 0.0d);
        this.lastActivityTime = jSONObject.optLong("lastestCyclingTime", 0);
        this.avatarUrl = jSONObject.optString("avatar");
        this.area = jSONObject.optString("area");
        this.yearlyDistance = jSONObject.optDouble("yearlyDistance", 0.0d);
        this.userIntId = jSONObject.optInt("userIntId", 0);
        this.rankDistance = jSONObject.optDouble("rankDistance", 0.0d);
        this.clubName = jSONObject.optString("clubName");
        JSONObject optJSONObject = jSONObject.optJSONObject("user");
        if (optJSONObject != null) {
            this.avatarUrl = optJSONObject.optString("avatar");
        }
        this.ordinal = jSONObject.optInt("rank");
        this.rankType = jSONObject.optInt("rankType");
        this.milestone = jSONObject.optDouble("milestone");
        this.joined = jSONObject.optString("joined");
        this.isManager = jSONObject.optBoolean("ismanager");
        this.remarks = jSONObject.optString("remarks");
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String str) {
        this.username = str;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String str) {
        this.nickname = str;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String str) {
        this.email = str;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String str) {
        this.district = str;
    }

    public double getTotalDistance() {
        return this.totalDistance;
    }

    public void setTotalDistance(double d) {
        this.totalDistance = d;
    }

    public long getLastActivityTime() {
        return this.lastActivityTime;
    }

    public void setLastActivityTime(long j) {
        this.lastActivityTime = j;
    }

    public int getOrdinal() {
        return this.ordinal;
    }

    public void setOrdinal(int i) {
        this.ordinal = i;
    }

    public double getWeeklyDistance() {
        return this.weeklyDistance;
    }

    public void setWeeklyDistance(double d) {
        this.weeklyDistance = d;
    }

    public double getMonthlyDistance() {
        return this.monthlyDistance;
    }

    public void setMonthlyDistance(double d) {
        this.monthlyDistance = d;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String str) {
        this.avatarUrl = str;
    }

    public double getScore() {
        return this.score;
    }

    public void setScore(double d) {
        this.score = d;
    }

    public boolean isManager() {
        return this.isManager;
    }

    public void setManager(boolean z) {
        this.isManager = z;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public int getRankType() {
        return this.rankType;
    }

    public void setRankType(int i) {
        this.rankType = i;
    }

    public void setMilestone(double d) {
        this.milestone = d;
    }

    public double getMilestone() {
        return this.milestone;
    }

    public String getJoined() {
        return this.joined;
    }

    public void setJoined(String str) {
        this.joined = str;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String str) {
        this.area = str;
    }

    public double getYearlyDistance() {
        return this.yearlyDistance;
    }

    public void setYearlyDistance(double d) {
        this.yearlyDistance = d;
    }

    public int getUserIntId() {
        return this.userIntId;
    }

    public void setUserIntId(int i) {
        this.userIntId = i;
    }

    public double getRankDistance() {
        return this.rankDistance;
    }

    public void setRankDistance(double d) {
        this.rankDistance = d;
    }

    public String getClubName() {
        return this.clubName;
    }

    public void setClubName(String str) {
        this.clubName = str;
    }

    public void setIsManager(boolean z) {
        this.isManager = z;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String str) {
        this.remarks = str;
    }

    public String toString() {
        return "RankDTO [userId=" + this.userId + ", username=" + this.username + ", nickname=" + this.nickname + ", email=" + this.email + ", province=" + this.province + ", city=" + this.city + ", district=" + this.district + ", avatarUrl=" + this.avatarUrl + ", totalDistance=" + this.totalDistance + ", weeklyDistance=" + this.weeklyDistance + ", monthlyDistance=" + this.monthlyDistance + ", lastActivityTime=" + this.lastActivityTime + ", ordinal=" + this.ordinal + ", type=" + this.rankType + "]";
    }
}
