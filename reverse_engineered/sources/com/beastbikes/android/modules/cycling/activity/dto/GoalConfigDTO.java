package com.beastbikes.android.modules.cycling.activity.dto;

import ch.qos.logback.core.joran.action.Action;
import com.beastbikes.framework.ui.android.WebActivity;
import java.io.Serializable;
import org.json.JSONObject;

public class GoalConfigDTO implements Serializable {
    private boolean checked;
    private double distance;
    private String key;
    private String subTitle;
    private String title;

    public GoalConfigDTO(JSONObject jSONObject) {
        this.title = jSONObject.optString(WebActivity.EXTRA_TITLE);
        this.subTitle = jSONObject.optString("subtitle");
        this.distance = jSONObject.optDouble("distance");
        this.key = jSONObject.optString(Action.KEY_ATTRIBUTE);
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getSubTitle() {
        return this.subTitle;
    }

    public void setSubTitle(String str) {
        this.subTitle = str;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double d) {
        this.distance = d;
    }

    public boolean isChecked() {
        return this.checked;
    }

    public void setChecked(boolean z) {
        this.checked = z;
    }

    public String getKey() {
        return this.key;
    }

    public void setKey(String str) {
        this.key = str;
    }
}
