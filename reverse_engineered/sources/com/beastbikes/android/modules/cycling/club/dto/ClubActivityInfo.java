package com.beastbikes.android.modules.cycling.club.dto;

import ch.qos.logback.core.CoreConstants;
import com.beastbikes.framework.ui.android.WebActivity;
import java.io.Serializable;
import java.util.Arrays;
import org.json.JSONObject;

public class ClubActivityInfo implements Serializable {
    private String actId;
    private String applyEndDate;
    private String clubId;
    private String cover;
    private String decstiption;
    private String desc;
    private String endDate;
    private String id;
    private boolean isClubPrivate;
    private boolean isOriginator;
    private boolean isRegistered;
    private int maxMembers;
    private int members;
    private String mobPlace;
    private double[] mobPoint;
    private String mobilephone;
    private String objectId;
    private String originator;
    private String routeId;
    private String routeImage;
    private String routeName;
    private String startDate;
    private int status;
    private String title;
    private String userId;

    public String getEndDate() {
        return this.endDate;
    }

    public void setEndDate(String str) {
        this.endDate = str;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public boolean isClubPrivate() {
        return this.isClubPrivate;
    }

    public void setIsClubPrivate(boolean z) {
        this.isClubPrivate = z;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public String getMobPlace() {
        return this.mobPlace;
    }

    public void setMobPlace(String str) {
        this.mobPlace = str;
    }

    public boolean isRegistered() {
        return this.isRegistered;
    }

    public void setIsRegistered(boolean z) {
        this.isRegistered = z;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }

    public String getActId() {
        return this.actId;
    }

    public void setActId(String str) {
        this.actId = str;
    }

    public int getMaxMembers() {
        return this.maxMembers;
    }

    public void setMaxMembers(int i) {
        this.maxMembers = i;
    }

    public String getClubId() {
        return this.clubId;
    }

    public void setClubId(String str) {
        this.clubId = str;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public void setObjectId(String str) {
        this.objectId = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getRouteId() {
        return this.routeId;
    }

    public void setRouteId(String str) {
        this.routeId = str;
    }

    public String getOriginator() {
        return this.originator;
    }

    public void setOriginator(String str) {
        this.originator = str;
    }

    public String getRouteImage() {
        return this.routeImage;
    }

    public void setRouteImage(String str) {
        this.routeImage = str;
    }

    public boolean isOriginator() {
        return this.isOriginator;
    }

    public void setIsOriginator(boolean z) {
        this.isOriginator = z;
    }

    public String getRouteName() {
        return this.routeName;
    }

    public void setRouteName(String str) {
        this.routeName = str;
    }

    public int getMembers() {
        return this.members;
    }

    public void setMembers(int i) {
        this.members = i;
    }

    public double[] getMobPoint() {
        return this.mobPoint;
    }

    public void setMobPoint(double[] dArr) {
        this.mobPoint = dArr;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String str) {
        this.desc = str;
    }

    public String getMobilephone() {
        return this.mobilephone;
    }

    public void setMobilephone(String str) {
        this.mobilephone = str;
    }

    public String getApplyEndDate() {
        return this.applyEndDate;
    }

    public void setApplyEndDate(String str) {
        this.applyEndDate = str;
    }

    public String getCover() {
        return this.cover;
    }

    public void setCover(String str) {
        this.cover = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getDecstiption() {
        return this.decstiption;
    }

    public void setDecstiption(String str) {
        this.decstiption = str;
    }

    public ClubActivityInfo() {
        this.isClubPrivate = true;
        this.mobPoint = new double[2];
    }

    public ClubActivityInfo(JSONObject jSONObject) {
        this.isClubPrivate = true;
        this.mobPoint = new double[2];
        setStartDate(jSONObject.optString("startDate"));
        setRouteName(jSONObject.optString("routeName"));
        setEndDate(jSONObject.optString("endDate"));
        setClubId(jSONObject.optString("clubId"));
        setTitle(jSONObject.optString(WebActivity.EXTRA_TITLE));
        setRouteImage(jSONObject.optString("routeImage"));
        setUserId(jSONObject.optString("userId"));
        setRouteId(jSONObject.optString("routeId"));
        setStatus(jSONObject.optInt("status"));
        setMobPlace(jSONObject.optString("mobPlace"));
        setMembers(jSONObject.optInt("members"));
        setMobilephone(jSONObject.optString("mobilephone"));
        setActId(jSONObject.optString("actId"));
        setDesc(jSONObject.optString("desc"));
        setIsRegistered(jSONObject.optBoolean("isRegistered"));
        setIsOriginator(jSONObject.optBoolean("isOriginator"));
        setIsClubPrivate(jSONObject.optBoolean("isClubPrivate"));
        setMaxMembers(jSONObject.optInt("maxMembers"));
        setOriginator(jSONObject.optString("originator"));
        setApplyEndDate(jSONObject.optString("applyEndDate"));
        setCover(jSONObject.optString("cover"));
        setDecstiption(jSONObject.optString("decstiption"));
        if (jSONObject.optJSONArray("mobPoint") != null) {
            for (int i = 0; i < jSONObject.optJSONArray("mobPoint").length(); i++) {
                this.mobPoint[i] = jSONObject.optJSONArray("mobPoint").optDouble(i);
            }
        }
    }

    public String toString() {
        return "ClubActivityInfo{startDate='" + this.startDate + CoreConstants.SINGLE_QUOTE_CHAR + ", endDate='" + this.endDate + CoreConstants.SINGLE_QUOTE_CHAR + ", userId='" + this.userId + CoreConstants.SINGLE_QUOTE_CHAR + ", isClubPrivate=" + this.isClubPrivate + ", mobPlace='" + this.mobPlace + CoreConstants.SINGLE_QUOTE_CHAR + ", isRegistered=" + this.isRegistered + ", id='" + this.id + CoreConstants.SINGLE_QUOTE_CHAR + ", actId='" + this.actId + CoreConstants.SINGLE_QUOTE_CHAR + ", maxMembers=" + this.maxMembers + ", clubId='" + this.clubId + CoreConstants.SINGLE_QUOTE_CHAR + ", objectId='" + this.objectId + CoreConstants.SINGLE_QUOTE_CHAR + ", title='" + this.title + CoreConstants.SINGLE_QUOTE_CHAR + ", routeId='" + this.routeId + CoreConstants.SINGLE_QUOTE_CHAR + ", originator='" + this.originator + CoreConstants.SINGLE_QUOTE_CHAR + ", routeImage='" + this.routeImage + CoreConstants.SINGLE_QUOTE_CHAR + ", isOriginator=" + this.isOriginator + ", decstiption='" + this.decstiption + CoreConstants.SINGLE_QUOTE_CHAR + ", routeName='" + this.routeName + CoreConstants.SINGLE_QUOTE_CHAR + ", members=" + this.members + ", mobPoint=" + Arrays.toString(this.mobPoint) + ", desc='" + this.desc + CoreConstants.SINGLE_QUOTE_CHAR + ", mobilephone='" + this.mobilephone + CoreConstants.SINGLE_QUOTE_CHAR + ", applyEndDate='" + this.applyEndDate + CoreConstants.SINGLE_QUOTE_CHAR + ", cover='" + this.cover + CoreConstants.SINGLE_QUOTE_CHAR + ", status=" + this.status + CoreConstants.CURLY_RIGHT;
    }
}
