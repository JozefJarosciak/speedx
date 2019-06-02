package com.beastbikes.android.modules.cycling.club.dto;

import android.content.Context;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.utils.C2555d;
import com.beastbikes.framework.ui.android.WebActivity;
import java.util.Date;
import org.json.JSONObject;

public class ClubFeedActivity extends ClubFeedBase {
    private String actId;
    private String actUrl;
    private Date endDate;
    private String mobPlace;
    private String routeImage;
    private String routeName;
    private String startDate;
    private int status;
    private String title;

    public ClubFeedActivity(JSONObject jSONObject) {
        if (jSONObject != null) {
            Context applicationContext = BeastBikes.j().getApplicationContext();
            this.clubId = jSONObject.optString("clubId");
            this.actId = jSONObject.optString("actId");
            this.title = jSONObject.optString(WebActivity.EXTRA_TITLE);
            this.mobPlace = jSONObject.optString("mobPlace");
            this.startDate = applicationContext.getResources().getString(C1373R.string.str_time) + ": " + C2555d.m12814e(C2555d.m12820h(jSONObject.optString("startDate")));
            this.endDate = C2555d.m12820h(jSONObject.optString("endDate"));
            this.routeImage = jSONObject.optString("routeImage");
            this.status = jSONObject.optInt("status");
            this.actUrl = jSONObject.optString("actUrl");
            this.routeName = jSONObject.optString("routeName");
            this.routeName += applicationContext.getResources().getString(C1373R.string.route);
        }
    }

    public String getActId() {
        return this.actId;
    }

    public void setActId(String str) {
        this.actId = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getMobPlace() {
        return this.mobPlace;
    }

    public void setMobPlace(String str) {
        this.mobPlace = str;
    }

    public String getStartDate() {
        return this.startDate;
    }

    public void setStartDate(String str) {
        this.startDate = str;
    }

    public Date getEndDate() {
        return this.endDate;
    }

    public void setEndDate(Date date) {
        this.endDate = date;
    }

    public String getRouteImage() {
        return this.routeImage;
    }

    public void setRouteImage(String str) {
        this.routeImage = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getActUrl() {
        return this.actUrl;
    }

    public void setActUrl(String str) {
        this.actUrl = str;
    }

    public String getRouteName() {
        return this.routeName;
    }

    public void setRouteName(String str) {
        this.routeName = str;
    }
}
