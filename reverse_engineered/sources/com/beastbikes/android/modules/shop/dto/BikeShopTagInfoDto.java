package com.beastbikes.android.modules.shop.dto;

import java.io.Serializable;
import org.json.JSONObject;

public class BikeShopTagInfoDto implements Serializable {
    private boolean isCare;
    private boolean isFix;
    private boolean isOfficeExperience;
    private boolean rent;

    public BikeShopTagInfoDto(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.isFix = jSONObject.optBoolean("isFix");
            this.rent = jSONObject.optBoolean("isRent");
            this.isCare = jSONObject.optBoolean("isCare");
            this.isOfficeExperience = jSONObject.optBoolean("offline_experience");
        }
    }

    public boolean isFix() {
        return this.isFix;
    }

    public void setFix(boolean z) {
        this.isFix = z;
    }

    public boolean isCare() {
        return this.isCare;
    }

    public void setCare(boolean z) {
        this.isCare = z;
    }

    public boolean isRent() {
        return this.rent;
    }

    public void setRent(boolean z) {
        this.rent = z;
    }

    public boolean isOfficeExperience() {
        return this.isOfficeExperience;
    }

    public void setOfficeExperience(boolean z) {
        this.isOfficeExperience = z;
    }
}
