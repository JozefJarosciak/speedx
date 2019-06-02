package com.beastbikes.android.modules.social.im.dto;

import com.avos.avoscloud.AVUtils;
import java.io.Serializable;
import org.json.JSONObject;

public class FriendDTO implements Serializable {
    public static final int FRIEND_STATUS_ADD = 0;
    public static final int FRIEND_STATUS_FANS = 1;
    public static final int FRIEND_STATUS_FOLLOW = 2;
    public static final int FRIEND_STATUS_FOLLOW_AND_FANS = 3;
    public static final int FRIEND_STATUS_PROCESSED = 1;
    public static final int FRIEND_STATUS_UNTREATED = 0;
    private static final long serialVersionUID = -5382704204584792514L;
    private String area;
    private String avatar;
    private String city;
    private String clubId;
    private String clubName;
    private long createTime;
    private String extra;
    private String friendId;
    private String nickname;
    private String objectId;
    private String province;
    private String remarks;
    private int requestId;
    private int status;
    private String thirdNick;

    public FriendDTO(JSONObject jSONObject) {
        this.objectId = jSONObject.optString(AVUtils.objectIdTag);
        this.friendId = jSONObject.optString("userId");
        this.avatar = jSONObject.optString("avatar");
        if (jSONObject.has("avatarImage")) {
            this.avatar = jSONObject.optString("avatarImage");
        }
        this.nickname = jSONObject.optString("nickname");
        this.extra = jSONObject.optString("extra");
        if (jSONObject.has("followStatu")) {
            this.status = jSONObject.optInt("followStatu");
        } else {
            this.status = jSONObject.optInt("status");
        }
        this.province = jSONObject.optString("province");
        this.city = jSONObject.optString("city");
        this.requestId = jSONObject.optInt("requestId");
        this.createTime = jSONObject.optLong("timestamp");
        this.remarks = jSONObject.optString("remarks");
        this.area = jSONObject.optString("area");
        this.clubId = jSONObject.optString("clubId");
        this.clubName = jSONObject.optString("clubName");
        this.thirdNick = jSONObject.optString("thirdNick");
    }

    public String getObjectId() {
        return this.objectId;
    }

    public void setObjectId(String str) {
        this.objectId = str;
    }

    public String getFriendId() {
        return this.friendId;
    }

    public void setFriendId(String str) {
        this.friendId = str;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setNickname(String str) {
        this.nickname = str;
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

    public int getRequestId() {
        return this.requestId;
    }

    public void setRequestId(int i) {
        this.requestId = i;
    }

    public String getRemarks() {
        return this.remarks;
    }

    public void setRemarks(String str) {
        this.remarks = str;
    }

    public long getCreateTime() {
        if (this.createTime <= 0) {
            this.createTime = System.currentTimeMillis() / 1000;
        }
        return this.createTime;
    }

    public void setCreateTime(long j) {
        this.createTime = j;
    }

    public String getClubName() {
        return this.clubName;
    }

    public void setClubName(String str) {
        this.clubName = str;
    }

    public String getClubId() {
        return this.clubId;
    }

    public void setClubId(String str) {
        this.clubId = str;
    }

    public String getArea() {
        return this.area;
    }

    public void setArea(String str) {
        this.area = str;
    }

    public String getThirdNick() {
        return this.thirdNick;
    }

    public void setThirdNick(String str) {
        this.thirdNick = str;
    }
}
