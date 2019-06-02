package com.beastbikes.android.modules.shop.dto;

import android.text.TextUtils;
import android.webkit.URLUtil;
import com.avos.avoscloud.AVUtils;
import com.beastbikes.android.utils.C2563k;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import io.rong.imlib.statistics.UserData;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

public class BikeShopInfoDTO implements Serializable {
    private String address;
    private String city;
    private int closeHour;
    private String clubId;
    private String clubLogo;
    private String clubName;
    private String description;
    private String district;
    private double latitude;
    private int level;
    private String logo;
    private double longitude;
    private String mainProducts = null;
    private String name;
    private String officeActivity;
    private int openHour;
    private String ownerId;
    private List<String> pictures = null;
    private String province;
    private double range;
    private int shopId;
    private int status;
    private BikeShopTagInfoDto tagInfo;
    private String telephone;

    public BikeShopInfoDTO(JSONObject jSONObject) {
        this.logo = jSONObject.optString("logo");
        if (!TextUtils.isEmpty(this.logo) && URLUtil.isHttpsUrl(this.logo)) {
            this.logo = this.logo.replaceAll(this.logo.substring(0, 8), "http://");
        }
        this.range = jSONObject.optDouble("range");
        JSONArray optJSONArray = jSONObject.optJSONArray(MapboxEvent.TYPE_LOCATION);
        if (optJSONArray != null) {
            this.longitude = optJSONArray.optDouble(0);
            this.latitude = optJSONArray.optDouble(1);
        }
        this.closeHour = jSONObject.optInt("closeHour");
        this.city = jSONObject.optString("city");
        this.openHour = jSONObject.optInt("openHour");
        this.level = jSONObject.optInt("level");
        this.address = jSONObject.optString(GeocodingCriteria.TYPE_ADDRESS);
        this.description = jSONObject.optString("description");
        this.shopId = jSONObject.optInt("shopId");
        this.name = jSONObject.optString("name");
        this.province = jSONObject.optString("province");
        this.district = jSONObject.optString("district");
        this.telephone = jSONObject.optString(UserData.PHONE_KEY);
        this.officeActivity = jSONObject.optString("officeActivity");
        this.status = jSONObject.optInt("status");
        this.ownerId = jSONObject.optString("ownerId");
        this.mainProducts = jSONObject.optString("mainProducts");
        JSONObject optJSONObject = jSONObject.optJSONObject("club");
        if (optJSONObject != null) {
            this.clubId = optJSONObject.optString(AVUtils.objectIdTag);
            this.clubName = optJSONObject.optString("name");
            this.clubLogo = optJSONObject.optString("logo");
        }
        optJSONObject = jSONObject.optJSONObject("tagInfo");
        if (!C2563k.m12869a(optJSONObject)) {
            this.tagInfo = new BikeShopTagInfoDto(optJSONObject);
        }
        JSONArray optJSONArray2 = jSONObject.optJSONArray("shopPictures");
        if (optJSONArray2 != null) {
            this.pictures = new ArrayList();
            for (int i = 0; i < optJSONArray2.length(); i++) {
                Object optString = optJSONArray2.optString(i);
                if (!TextUtils.isEmpty(optString) && URLUtil.isHttpsUrl(optString)) {
                    optString = optString.replaceAll(this.logo.substring(0, 8), "http://");
                }
                this.pictures.add(optString);
            }
        }
    }

    public String getLogo() {
        return this.logo;
    }

    public void setLogo(String str) {
        this.logo = str;
    }

    public double getRange() {
        return this.range;
    }

    public void setRange(double d) {
        this.range = d;
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

    public int getCloseHour() {
        return this.closeHour;
    }

    public void setCloseHour(int i) {
        this.closeHour = i;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public int getOpenHour() {
        return this.openHour;
    }

    public void setOpenHour(int i) {
        this.openHour = i;
    }

    public String getClubId() {
        return this.clubId;
    }

    public void setClubId(String str) {
        this.clubId = str;
    }

    public int getLevel() {
        return this.level;
    }

    public void setLevel(int i) {
        this.level = i;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String str) {
        this.address = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public int getShopId() {
        return this.shopId;
    }

    public void setShopId(int i) {
        this.shopId = i;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String str) {
        this.name = str;
    }

    public String getCitygetProvince() {
        return this.province;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public String getClubName() {
        return this.clubName;
    }

    public void setClubName(String str) {
        this.clubName = str;
    }

    public String getDistrict() {
        return this.district;
    }

    public void setDistrict(String str) {
        this.district = str;
    }

    public String getTelephone() {
        return this.telephone;
    }

    public void setTelephone(String str) {
        this.telephone = str;
    }

    public BikeShopTagInfoDto getTagInfo() {
        return this.tagInfo;
    }

    public void setTagInfo(BikeShopTagInfoDto bikeShopTagInfoDto) {
        this.tagInfo = bikeShopTagInfoDto;
    }

    public String getClubLogo() {
        return this.clubLogo;
    }

    public void setClubLogo(String str) {
        this.clubLogo = str;
    }

    public String getOfficeActivity() {
        return this.officeActivity;
    }

    public void setOfficeActivity(String str) {
        this.officeActivity = str;
    }

    public List<String> getPictures() {
        return this.pictures;
    }

    public void setPictures(List<String> list) {
        this.pictures = list;
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

    public String getMainProducts() {
        return this.mainProducts;
    }

    public void setMainProducts(String str) {
        this.mainProducts = str;
    }
}
