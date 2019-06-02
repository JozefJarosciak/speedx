package com.beastbikes.android.modules.cycling.club.dto;

import com.beastbikes.framework.ui.android.WebActivity;
import java.io.Serializable;
import org.json.JSONObject;

public class ClubActivityListDTO implements Serializable {
    public static final int CLUB_ACTIVITY_STATUS_CANCEL = 3;
    public static final int CLUB_ACTIVITY_STATUS_ENDED = 2;
    public static final int CLUB_ACTIVITY_STATUS_ONGOING = 0;
    private String actId;
    private String actUrl;
    private int applyStatus;
    private String avatarImage;
    private String cover;
    private String endDate;
    private String id;
    private boolean isManager;
    private boolean joined;
    private int maxMembers;
    private int members;
    private String mobPlace;
    private String nickname;
    private String remarks;
    private String routeId;
    private String routeImage;
    private int signInCount;
    private String startDate;
    private String title;

    public ClubActivityListDTO(JSONObject jSONObject) {
        setStartDate(jSONObject.optString("startDate"));
        setEndDate(jSONObject.optString("endDate"));
        setTitle(jSONObject.optString(WebActivity.EXTRA_TITLE));
        setRouteImage(jSONObject.optString("routeImage"));
        setRouteId(jSONObject.optString("routeId"));
        setApplyStatus(jSONObject.optInt("applyStatus"));
        setMobPlace(jSONObject.optString("mobPlace"));
        setMembers(jSONObject.optInt("members"));
        setActId(jSONObject.optString("actId"));
        setJoined(jSONObject.optBoolean("joined"));
        this.id = jSONObject.optString("id");
        this.cover = jSONObject.optString("cover");
        this.actUrl = jSONObject.optString("actUrl");
        this.isManager = jSONObject.optBoolean("isManager");
        this.avatarImage = jSONObject.optString("avatarImage");
        this.nickname = jSONObject.optString("nickname");
        this.remarks = jSONObject.optString("remarks");
        this.signInCount = jSONObject.optInt("signInCount");
        this.maxMembers = jSONObject.optInt("maxMembers");
    }

    public boolean getJoined() {
        return this.joined;
    }

    public void setJoined(boolean z) {
        this.joined = z;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setRouteImage(String str) {
        this.routeImage = str;
    }

    public String getRouteImage() {
        return this.routeImage;
    }

    public void setRouteId(String str) {
        this.routeId = str;
    }

    public String getRouteId() {
        return this.routeId;
    }

    public void setApplyStatus(int i) {
        this.applyStatus = i;
    }

    public int getApplyStatus() {
        return this.applyStatus;
    }

    public void setMobPlace(String str) {
        this.mobPlace = str;
    }

    public String getMobPlace() {
        return this.mobPlace;
    }

    public void setMembers(int i) {
        this.members = i;
    }

    public int getMembers() {
        return this.members;
    }

    public void setActId(String str) {
        this.actId = str;
    }

    public String getActId() {
        return this.actId;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getCover() {
        return this.cover;
    }

    public void setCover(String str) {
        this.cover = str;
    }

    public String getActUrl() {
        return this.actUrl;
    }

    public void setActUrl(String str) {
        this.actUrl = str;
    }

    public boolean isManager() {
        return this.isManager;
    }

    public void setIsManager(boolean z) {
        this.isManager = z;
    }

    public boolean isJoined() {
        return this.joined;
    }

    public String getAvatarImage() {
        return this.avatarImage;
    }

    public void setAvatarImage(String str) {
        this.avatarImage = str;
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

    public int getSignInCount() {
        return this.signInCount;
    }

    public void setSignInCount(int i) {
        this.signInCount = i;
    }

    public int getMaxMembers() {
        return this.maxMembers;
    }

    public void setMaxMembers(int i) {
        this.maxMembers = i;
    }
}
