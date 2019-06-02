package com.beastbikes.android.modules.shop.dto;

import android.text.TextUtils;
import android.webkit.URLUtil;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import java.io.Serializable;
import org.json.JSONArray;
import org.json.JSONObject;

public class BikeShopListDTO implements Serializable {
    public static final int STATUS_FAIL = -1;
    public static final int STATUS_PASS = 1;
    public static final int STATUS_UNTREATED = 0;
    private String address;
    private String allAddress;
    private String avatar;
    private String bikeShopImg;
    private String city;
    private double distance;
    private String district;
    private double latitude;
    private int level;
    private double longitude;
    private String name;
    private String officeExperience;
    private String ownerId;
    private String province;
    private String reason;
    private long shopId;
    private int status;
    private BikeShopTagInfoDto tagInfo;

    public BikeShopListDTO(JSONObject jSONObject) {
        JSONArray optJSONArray = jSONObject.optJSONArray(MapboxEvent.TYPE_LOCATION);
        if (optJSONArray != null) {
            this.latitude = optJSONArray.optDouble(1);
            this.longitude = optJSONArray.optDouble(0);
        }
        this.name = jSONObject.optString("shop_name");
        this.avatar = jSONObject.optString("logo");
        this.tagInfo = new BikeShopTagInfoDto();
        this.tagInfo.setRent(jSONObject.optBoolean("is_rent"));
        this.tagInfo.setFix(jSONObject.optBoolean("is_fix"));
        this.tagInfo.setCare(jSONObject.optBoolean("is_care"));
        this.tagInfo.setOfficeExperience(jSONObject.optBoolean("offline_experience"));
        this.shopId = jSONObject.optLong("shop_id");
        this.province = jSONObject.optString("province");
        this.city = jSONObject.optString("city");
        this.distance = jSONObject.optDouble("distance");
        this.bikeShopImg = jSONObject.optString("bike_shop_img");
        if (!TextUtils.isEmpty(this.bikeShopImg) && URLUtil.isHttpsUrl(this.bikeShopImg)) {
            this.bikeShopImg = this.bikeShopImg.replaceAll(this.bikeShopImg.substring(0, 8), "http://");
        }
        this.address = jSONObject.optString(GeocodingCriteria.TYPE_ADDRESS);
        this.district = jSONObject.optString("district");
        this.level = jSONObject.optInt("level");
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public double getLongitude() {
        return this.longitude;
    }

    public void setLongitude(double d) {
        this.longitude = d;
    }

    public double getLatitude() {
        return this.latitude;
    }

    public void setLatitude(double d) {
        this.latitude = d;
    }

    public long getShopId() {
        return this.shopId;
    }

    public void setShopId(long j) {
        this.shopId = j;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public BikeShopTagInfoDto getTagInfo() {
        return this.tagInfo;
    }

    public void setTagInfo(BikeShopTagInfoDto bikeShopTagInfoDto) {
        this.tagInfo = bikeShopTagInfoDto;
    }

    public String getAvatar() {
        return this.avatar;
    }

    public void setAvatar(String str) {
        this.avatar = str;
    }

    public String getOwnerId() {
        return this.ownerId;
    }

    public void setOwnerId(String str) {
        this.ownerId = str;
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int i) {
        this.status = i;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public double getDistance() {
        return this.distance;
    }

    public void setDistance(double d) {
        this.distance = d;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public String getBikeShopImg() {
        return this.bikeShopImg;
    }

    public void setBikeShopImg(String str) {
        this.bikeShopImg = str;
    }

    public String getAllAddress() {
        return this.province + this.city + this.district + this.address;
    }

    public void setAllAddress(String str) {
        this.allAddress = str;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String str) {
        this.district = str;
    }

    public String getOfficeExperience() {
        return this.officeExperience;
    }

    public void setOfficeExperience(String str) {
        this.officeExperience = str;
    }
}
