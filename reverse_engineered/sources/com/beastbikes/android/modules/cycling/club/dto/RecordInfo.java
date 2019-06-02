package com.beastbikes.android.modules.cycling.club.dto;

import com.beastbikes.android.modules.user.dto.ActivityDTO;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.framework.ui.android.WebActivity;
import java.io.Serializable;
import java.util.Date;
import org.json.JSONObject;

public class RecordInfo implements Serializable {
    private String cyclingImage;
    private double distance;
    private Date endDate;
    private String sportIdentify;
    private Date startDate;
    private long time;
    private String title;

    public RecordInfo(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.sportIdentify = jSONObject.optString("sportIdentify");
            this.title = jSONObject.optString(WebActivity.EXTRA_TITLE);
            this.cyclingImage = jSONObject.optString("cyclingImage");
            this.startDate = C2555d.m12820h(jSONObject.optString("startDate"));
            this.endDate = C2555d.m12820h(jSONObject.optString("endDate"));
            this.distance = jSONObject.optDouble("distance");
            this.time = jSONObject.optLong("time") * 1000;
        }
    }

    public RecordInfo(ActivityDTO activityDTO) {
        if (activityDTO != null) {
            this.sportIdentify = activityDTO.getActivityIdentifier();
            this.title = activityDTO.getTitle();
            this.cyclingImage = activityDTO.getActivityUrl();
            this.startDate = C2555d.m12820h(C2555d.m12796a(activityDTO.getStartTime()));
            this.endDate = C2555d.m12820h(C2555d.m12796a(activityDTO.getStopTime()));
            this.distance = activityDTO.getTotalDistance();
            this.time = (long) ((int) activityDTO.getElapsedTime());
        }
    }

    public String getSportIdentify() {
        return this.sportIdentify;
    }

    public void setSportIdentify(String str) {
        this.sportIdentify = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getCyclingImage() {
        return this.cyclingImage;
    }

    public void setCyclingImage(String str) {
        this.cyclingImage = str;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double d) {
        this.distance = d;
    }

    public Date getStartDate() {
        return this.startDate;
    }

    public void setStartDate(Date date) {
        this.startDate = date;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date date) {
        this.endDate = date;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long j) {
        this.time = j;
    }
}
