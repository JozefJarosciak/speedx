package com.beastbikes.android.modules.cycling.club.dto;

import com.beastbikes.android.utils.C2563k;
import java.io.Serializable;
import org.json.JSONObject;

public class ClubUser implements Serializable {
    private String avatar;
    private String nickName;
    private String remarks;
    private String userId;

    public ClubUser(String str, String str2, String str3) {
        this.userId = str;
        this.nickName = str2;
        this.avatar = str3;
    }

    public ClubUser(JSONObject jSONObject) {
        if (!C2563k.m12869a(jSONObject)) {
            this.userId = jSONObject.optString("userId");
            this.nickName = jSONObject.optString("nickname");
            this.avatar = jSONObject.optString("avatar");
            this.remarks = jSONObject.optString("remarks");
        }
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getNickName() {
        return this.nickName;
    }

    public void setNickName(String str) {
        this.nickName = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String str) {
        this.remarks = str;
    }
}
