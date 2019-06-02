package com.beastbikes.android.modules.cycling.club.dto;

import com.avos.avoscloud.AVUtils;
import java.io.Serializable;
import org.json.JSONObject;

public class ClubRankBean implements Serializable {
    private String city;
    private boolean isPrivate = false;
    private String logo;
    private int members;
    private double milestone;
    private String name;
    private String objectId;
    private int ordinal;
    private long score;

    public ClubRankBean(JSONObject jSONObject) {
        this.city = jSONObject.optString("city");
        this.name = jSONObject.optString("name");
        this.objectId = jSONObject.optString(AVUtils.objectIdTag);
        this.score = jSONObject.optLong("score");
        this.members = jSONObject.optInt("members");
        this.milestone = jSONObject.optDouble("milestone");
        this.logo = jSONObject.optString("logo");
        this.isPrivate = jSONObject.optBoolean("isPrivate");
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public void setObjectId(String str) {
        this.objectId = str;
    }

    public long getScore() {
        return this.score;
    }

    public void setScore(long j) {
        this.score = j;
    }

    public int getMembers() {
        return this.members;
    }

    public void setMembers(int i) {
        this.members = i;
    }

    public double getMilestone() {
        return this.milestone;
    }

    public void setMilestone(double d) {
        this.milestone = d;
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String str) {
        this.logo = str;
    }

    public int getOrdinal() {
        return this.ordinal;
    }

    public void setOrdinal(int i) {
        this.ordinal = i;
    }

    public boolean isPrivate() {
        return this.isPrivate;
    }

    public void setIsPrivate(boolean z) {
        this.isPrivate = z;
    }
}
