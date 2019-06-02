package com.beastbikes.android.modules.user.dto;

import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import java.io.Serializable;
import org.json.JSONObject;

public class SearchRegionDTO implements Serializable {
    private String city;
    private String country;
    private String placeId;
    private String province;

    public SearchRegionDTO(JSONObject jSONObject) {
        if (jSONObject != null) {
            this.placeId = jSONObject.optString("place_id");
            this.country = jSONObject.optString(GeocodingCriteria.TYPE_COUNTRY);
            this.province = jSONObject.optString("province");
            this.city = jSONObject.optString("city");
        }
    }

    public String getPlaceId() {
        return this.placeId;
    }

    public void setPlaceId(String str) {
        this.placeId = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getProvince() {
        return this.province;
    }

    public void setProvince(String str) {
        this.province = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }
}
