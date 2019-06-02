package com.beastbikes.android.modules.cycling.club.dto;

import com.avos.avoscloud.AVUtils;
import java.io.Serializable;
import org.json.JSONObject;

public class ApplyDTO implements Serializable {
    private static final long serialVersionUID = -3530755736139133762L;
    private String avatarUrl;
    private String extra;
    private String nickname;
    private String objectId;
    private String remarks;
    private int status;
    private String userId;

    public ApplyDTO(JSONObject jSONObject) {
        this.objectId = jSONObject.optString(AVUtils.objectIdTag);
        this.extra = jSONObject.optString("extra");
        this.nickname = jSONObject.optString("nickname");
        this.status = jSONObject.optInt("status");
        this.userId = jSONObject.optString("userId");
        this.avatarUrl = jSONObject.optString("avatarImage");
        this.remarks = jSONObject.optString("remarks");
    }

    public String getObjectId() {
        return this.objectId;
    }

    public void setObjectId(String str) {
        this.objectId = str;
    }

    public String getExtra() {
        return this.extra;
    }

    public void setExtra(String str) {
        this.extra = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getAvatarUrl() {
        return this.avatarUrl;
    }

    public void setAvatarUrl(String str) {
        this.avatarUrl = str;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String str) {
        this.nickname = str;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String str) {
        this.remarks = str;
    }
}
