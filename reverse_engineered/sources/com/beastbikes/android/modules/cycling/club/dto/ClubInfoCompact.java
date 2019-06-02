package com.beastbikes.android.modules.cycling.club.dto;

import com.avos.avoscloud.AVUtils;
import java.io.Serializable;
import org.json.JSONObject;

public class ClubInfoCompact implements Serializable {
    public static final int CLUB_STATUS_APPLY = 2;
    public static final int CLUB_STATUS_APPLY_REFUSED = 6;
    public static final int CLUB_STATUS_CREATE = 3;
    public static final int CLUB_STATUS_ESTABLISHED = 4;
    public static final int CLUB_STATUS_JOINED = 1;
    public static final int CLUB_STATUS_NONE = 0;
    public static final int CLUB_STATUS_QUIT = 5;
    private static final long serialVersionUID = 6214355107311561136L;
    private int activities;
    private String city;
    private int clubLevel;
    private int curPhotoNum;
    private String desc;
    private boolean isPrivate;
    private int level;
    private long linkTo;
    private String logo;
    private String managerId;
    private int maxMembers = 0;
    private int maxPhotoNum;
    private int members;
    private double milestone;
    private String name;
    private String notice;
    private String objectId;
    private int ordinal;
    private String province;
    private int rank;
    private double score;
    private int status;
    private int type;

    public ClubInfoCompact(String str, String str2, int i, double d, String str3, String str4) {
        this.name = str;
        this.logo = str2;
        this.members = i;
        this.milestone = d;
        this.city = str3;
        this.objectId = str4;
    }

    public ClubInfoCompact(JSONObject jSONObject) {
        this.objectId = jSONObject.optString(AVUtils.objectIdTag);
        this.name = jSONObject.optString("name");
        this.desc = jSONObject.optString("desc");
        this.managerId = jSONObject.optString("managerId");
        this.milestone = jSONObject.optDouble("milestone", 0.0d);
        this.score = jSONObject.optDouble("score", 0.0d);
        this.maxMembers = jSONObject.optInt("maxMembers", 50);
        this.members = jSONObject.optInt("members", 1);
        this.logo = jSONObject.optString("logo");
        this.province = jSONObject.optString("province");
        this.city = jSONObject.optString("city");
        this.activities = jSONObject.optInt("activities", 0);
        this.notice = jSONObject.optString("notice");
        this.rank = jSONObject.optInt("rank");
        this.status = jSONObject.optInt("status");
        this.maxPhotoNum = jSONObject.optInt("maxPhotoNum");
        this.curPhotoNum = jSONObject.optInt("curPhotoNum");
        this.isPrivate = jSONObject.optBoolean("isPrivate");
        this.clubLevel = jSONObject.optInt("level");
        this.type = jSONObject.optInt("type");
        this.linkTo = jSONObject.optLong("linkTo");
    }

    public int getActivities() {
        return this.activities;
    }

    public void setActivities(int i) {
        this.activities = i;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public void setObjectId(String str) {
        this.objectId = str;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public double getMilestone() {
        return this.milestone;
    }

    public void setMilestone(double d) {
        this.milestone = d;
    }

    public double getScore() {
        return this.score;
    }

    public void setScore(double d) {
        this.score = d;
    }

    public int getMembers() {
        return this.members;
    }

    public void setMembers(int i) {
        if (i >= 0) {
            this.members = i;
        }
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String str) {
        this.logo = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public String getManagerId() {
        return this.managerId;
    }

    public void setManagerId(String str) {
        this.managerId = str;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public int getOrdinal() {
        return this.ordinal;
    }

    public void setOrdinal(int i) {
        this.ordinal = i;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getNotice() {
        return this.notice;
    }

    public void setNotice(String str) {
        this.notice = str;
    }

    public int getMaxMembers() {
        return this.maxMembers;
    }

    public void setMaxMembers(int i) {
        this.maxMembers = i;
    }

    public int getRank() {
        return this.rank;
    }

    public void setRank(int i) {
        this.rank = i;
    }

    public int getMaxPhotoNum() {
        return this.maxPhotoNum;
    }

    public void setMaxPhotoNum(int i) {
        this.maxPhotoNum = i;
    }

    public int getCurPhotoNum() {
        return this.curPhotoNum;
    }

    public void setCurPhotoNum(int i) {
        this.curPhotoNum = i;
    }

    public boolean getIsPrivate() {
        return this.isPrivate;
    }

    public void setIsPrivate(boolean z) {
        this.isPrivate = z;
    }

    public int getClubLevel() {
        return this.clubLevel;
    }

    public void setClubLevel(int i) {
        this.clubLevel = i;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public long getLinkTo() {
        return this.linkTo;
    }

    public void setLinkTo(long j) {
        this.linkTo = j;
    }
}
