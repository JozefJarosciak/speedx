package com.beastbikes.android.modules.cycling.stage.dto;

import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.io.Serializable;
import org.json.JSONObject;

public class StageRankDTO implements Serializable {
    private String avatar;
    private double avgSpeed;
    private long duration;
    private int id;
    private double maxSpeed;
    private String nickname;
    private String prize;
    private int ranking;
    private String routeId;
    private String time;
    private String userId;

    public StageRankDTO(JSONObject jSONObject) {
        this.id = jSONObject.optInt("id");
        this.userId = jSONObject.optString("user_id");
        this.nickname = jSONObject.optString("nickname");
        this.routeId = jSONObject.optString("sport_route_id");
        this.avatar = jSONObject.optString("user_avatar");
        this.duration = jSONObject.optLong("use_time");
        this.maxSpeed = jSONObject.optDouble("speed_max");
        this.avgSpeed = jSONObject.optDouble("speed_avg");
        this.prize = jSONObject.optString("prize");
        this.time = jSONObject.optString(MapboxEvent.ATTRIBUTE_CREATED);
        this.ranking = jSONObject.optInt("ranking");
    }

    public int getId() {
        return this.id;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String str) {
        this.nickname = str;
    }

    public String getRouteId() {
        return this.routeId;
    }

    public void setRouteId(String str) {
        this.routeId = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public long getDuration() {
        return this.duration;
    }

    public void setDuration(long j) {
        this.duration = j;
    }

    public double getMaxSpeed() {
        return this.maxSpeed;
    }

    public void setMaxSpeed(double d) {
        this.maxSpeed = d;
    }

    public double getAvgSpeed() {
        return this.avgSpeed;
    }

    public void setAvgSpeed(double d) {
        this.avgSpeed = d;
    }

    public String getPrize() {
        return this.prize;
    }

    public void setPrize(String str) {
        this.prize = str;
    }

    public String getTime() {
        return this.time;
    }

    public void setTime(String str) {
        this.time = str;
    }

    public int getRanking() {
        return this.ranking;
    }

    public void setRanking(int i) {
        this.ranking = i;
    }
}
