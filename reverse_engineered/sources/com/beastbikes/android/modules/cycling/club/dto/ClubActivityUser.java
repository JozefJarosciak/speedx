package com.beastbikes.android.modules.cycling.club.dto;

import ch.qos.logback.core.CoreConstants;
import java.io.Serializable;
import org.json.JSONObject;

public class ClubActivityUser implements Serializable {
    private String area;
    private String avatar;
    private String city;
    private String nickname;
    private String province;
    private String remarks;
    private String userId;
    private int userIntId;

    public ClubActivityUser(JSONObject jSONObject) {
        setUserIntId(jSONObject.optInt("userIntId"));
        setUserId(jSONObject.optString("userId"));
        setNickname(jSONObject.optString("nickname"));
        setCity(jSONObject.optString("city"));
        setArea(jSONObject.optString("area"));
        setAvatar(jSONObject.optString("avatar"));
        setRemarks(jSONObject.optString("remarks"));
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public String getProvince() {
        return this.province;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setArea(String str) {
        this.area = str;
    }

    public String getArea() {
        return this.area;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserIntId(int i) {
        this.userIntId = i;
    }

    public int getUserIntId() {
        return this.userIntId;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setNickname(String str) {
        this.nickname = str;
    }

    public String getNickname() {
        return this.nickname;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String str) {
        this.remarks = str;
    }

    public String toString() {
        return "ClubActivityUser{province='" + this.province + CoreConstants.SINGLE_QUOTE_CHAR + ", city='" + this.city + CoreConstants.SINGLE_QUOTE_CHAR + ", area='" + this.area + CoreConstants.SINGLE_QUOTE_CHAR + ", userId='" + this.userId + CoreConstants.SINGLE_QUOTE_CHAR + ", userIntId=" + this.userIntId + ", avatar='" + this.avatar + CoreConstants.SINGLE_QUOTE_CHAR + ", nickname='" + this.nickname + CoreConstants.SINGLE_QUOTE_CHAR + CoreConstants.CURLY_RIGHT;
    }
}
