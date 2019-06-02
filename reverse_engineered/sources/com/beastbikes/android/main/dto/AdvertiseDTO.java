package com.beastbikes.android.main.dto;

import java.io.Serializable;
import org.json.JSONObject;

public class AdvertiseDTO implements Serializable {
    private int districtType;
    private boolean hasAdv;
    private String launchPhoto;
    private String photoSize;
    private int platform;
    private String redirectUrl;

    public AdvertiseDTO(int i, String str, int i2, String str2, String str3) {
        this.districtType = i;
        this.photoSize = str;
        this.platform = i2;
        this.redirectUrl = str2;
        this.launchPhoto = str3;
    }

    public AdvertiseDTO(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.districtType = jSONObject.optInt("district_type");
            this.photoSize = jSONObject.optString("launch_photo_size");
            this.platform = jSONObject.optInt("platform");
            this.redirectUrl = jSONObject.optString("redirect_url");
            this.launchPhoto = jSONObject.optString("launch_photo");
        }
    }

    public int getDistrictType() {
        return this.districtType;
    }

    public void setDistrictType(int i) {
        this.districtType = i;
    }

    public String getPhotoSize() {
        return this.photoSize;
    }

    public void setPhotoSize(String str) {
        this.photoSize = str;
    }

    public int getPlatform() {
        return this.platform;
    }

    public void setPlatform(int i) {
        this.platform = i;
    }

    public String getRedirectUrl() {
        return this.redirectUrl;
    }

    public void setRedirectUrl(String str) {
        this.redirectUrl = str;
    }

    public String getLaunchPhoto() {
        return this.launchPhoto;
    }

    public void setLaunchPhoto(String str) {
        this.launchPhoto = str;
    }

    public boolean isHasAdv() {
        return this.hasAdv;
    }

    public void setHasAdv(boolean z) {
        this.hasAdv = z;
    }
}
