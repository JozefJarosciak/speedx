package com.baidu.mapapi.cloud;

import com.avos.avoscloud.AVStatus;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.platform.comapi.util.CoordTrans;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CloudRgcResult {
    public AddressCompents addressCompents;
    public String customLocationDescription;
    public List<CloudPoiInfo> customPois;
    public String formattedAddress;
    public LatLng location;
    public String message;
    public List<PoiInfo> pois;
    public String recommendedLocationDescription;
    public int status;

    public class AddressCompents {
        /* renamed from: a */
        final /* synthetic */ CloudRgcResult f2773a;
        public int adminAreaCode;
        public String city;
        public String country;
        public String countryCode;
        public String district;
        public String province;
        public String street;
        public String streetNumber;

        public AddressCompents(CloudRgcResult cloudRgcResult) {
            this.f2773a = cloudRgcResult;
        }

        /* renamed from: a */
        void m4033a(JSONObject jSONObject) throws JSONException {
            if (jSONObject != null) {
                this.country = jSONObject.optString(GeocodingCriteria.TYPE_COUNTRY);
                this.province = jSONObject.optString("province");
                this.city = jSONObject.optString("city");
                this.district = jSONObject.optString("district");
                this.street = jSONObject.optString("street");
                this.streetNumber = jSONObject.optString("street_number");
                this.adminAreaCode = jSONObject.optInt("admin_area_code");
                this.countryCode = jSONObject.optString("country_code");
            }
        }
    }

    public class PoiInfo {
        /* renamed from: a */
        final /* synthetic */ CloudRgcResult f2774a;
        public String address;
        public String direction;
        public int distance;
        public LatLng location;
        public String name;
        public String tag;
        public String uid;

        public PoiInfo(CloudRgcResult cloudRgcResult) {
            this.f2774a = cloudRgcResult;
        }

        public void parseFromJSON(JSONObject jSONObject) throws JSONException {
            if (jSONObject != null) {
                this.name = jSONObject.optString("name");
                this.uid = jSONObject.optString("id");
                this.address = jSONObject.optString(GeocodingCriteria.TYPE_ADDRESS);
                this.tag = jSONObject.optString(AnalyticsEvent.labelTag);
                JSONObject optJSONObject = jSONObject.optJSONObject(MapboxEvent.TYPE_LOCATION);
                if (optJSONObject != null) {
                    this.location = new LatLng(optJSONObject.optDouble(MapboxEvent.KEY_LATITUDE), optJSONObject.optDouble(MapboxEvent.KEY_LONGITUDE));
                    if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                        this.location = CoordTrans.baiduToGcj(this.location);
                    }
                }
                this.direction = jSONObject.optString("direction");
                this.distance = jSONObject.optInt("distance");
            }
        }
    }

    public void parseFromJSON(JSONObject jSONObject) throws JSONException {
        int i = 0;
        try {
            this.status = jSONObject.optInt("status");
            this.message = jSONObject.optString(AVStatus.MESSAGE_TAG);
            if (this.status == 6 || this.status == 7 || this.status == 8 || this.status == 9) {
                this.status = 1;
            }
            if (this.status == 0) {
                JSONObject optJSONObject = jSONObject.optJSONObject(MapboxEvent.TYPE_LOCATION);
                if (optJSONObject != null) {
                    this.location = new LatLng(optJSONObject.optDouble(MapboxEvent.KEY_LATITUDE), optJSONObject.optDouble(MapboxEvent.KEY_LONGITUDE));
                    if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                        this.location = CoordTrans.baiduToGcj(this.location);
                    }
                }
                optJSONObject = jSONObject.optJSONObject("address_component");
                if (optJSONObject != null) {
                    this.addressCompents = new AddressCompents(this);
                    this.addressCompents.m4033a(optJSONObject);
                }
                this.formattedAddress = jSONObject.optString("formatted_address");
                JSONArray optJSONArray = jSONObject.optJSONArray("pois");
                if (optJSONArray != null) {
                    this.pois = new ArrayList();
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        JSONObject optJSONObject2 = optJSONArray.optJSONObject(i2);
                        if (optJSONObject2 != null) {
                            PoiInfo poiInfo = new PoiInfo(this);
                            poiInfo.parseFromJSON(optJSONObject2);
                            this.pois.add(poiInfo);
                        }
                    }
                }
                JSONArray optJSONArray2 = jSONObject.optJSONArray("custom_pois");
                if (optJSONArray2 != null) {
                    this.customPois = new ArrayList();
                    while (i < optJSONArray2.length()) {
                        JSONObject optJSONObject3 = optJSONArray2.optJSONObject(i);
                        if (optJSONObject3 != null) {
                            CloudPoiInfo cloudPoiInfo = new CloudPoiInfo();
                            cloudPoiInfo.m4031b(optJSONObject3);
                            this.customPois.add(cloudPoiInfo);
                        }
                        i++;
                    }
                }
                this.customLocationDescription = jSONObject.optString("custom_location_description");
                this.recommendedLocationDescription = jSONObject.optString("recommended_location_description");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
