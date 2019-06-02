package com.baidu.platform.core.p048c;

import com.alipay.sdk.util.C0882j;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.platform.base.C1212e;
import com.baidu.platform.comapi.util.CoordTrans;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.platform.core.c.d */
public class C1314d extends C1212e {
    /* renamed from: a */
    private boolean m5040a(String str, PoiDetailResult poiDetailResult) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject.optInt("status") != 0) {
                return false;
            }
            jSONObject = jSONObject.optJSONObject(C0882j.f2229c);
            if (jSONObject == null) {
                return false;
            }
            poiDetailResult.name = jSONObject.optString("name");
            JSONObject optJSONObject = jSONObject.optJSONObject(MapboxEvent.TYPE_LOCATION);
            if (optJSONObject != null) {
                double optDouble = optJSONObject.optDouble(MapboxEvent.KEY_LATITUDE);
                double optDouble2 = optJSONObject.optDouble(MapboxEvent.KEY_LONGITUDE);
                if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                    poiDetailResult.location = CoordTrans.baiduToGcj(new LatLng(optDouble, optDouble2));
                } else {
                    poiDetailResult.location = new LatLng(optDouble, optDouble2);
                }
            }
            poiDetailResult.address = jSONObject.optString(GeocodingCriteria.TYPE_ADDRESS);
            poiDetailResult.telephone = jSONObject.optString("telephone");
            poiDetailResult.uid = jSONObject.optString("uid");
            jSONObject = jSONObject.optJSONObject("detail_info");
            if (jSONObject != null) {
                poiDetailResult.tag = jSONObject.optString(AnalyticsEvent.labelTag);
                poiDetailResult.detailUrl = jSONObject.optString("detail_url");
                poiDetailResult.type = jSONObject.optString("type");
                poiDetailResult.price = jSONObject.optDouble("price", 0.0d);
                poiDetailResult.overallRating = jSONObject.optDouble("overall_rating", 0.0d);
                poiDetailResult.tasteRating = jSONObject.optDouble("taste_rating", 0.0d);
                poiDetailResult.serviceRating = jSONObject.optDouble("service_rating", 0.0d);
                poiDetailResult.environmentRating = jSONObject.optDouble("environment_rating", 0.0d);
                poiDetailResult.facilityRating = jSONObject.optDouble("facility_rating", 0.0d);
                poiDetailResult.hygieneRating = jSONObject.optDouble("hygiene_rating", 0.0d);
                poiDetailResult.technologyRating = jSONObject.optDouble("technology_rating", 0.0d);
                poiDetailResult.imageNum = jSONObject.optInt("image_num");
                poiDetailResult.grouponNum = jSONObject.optInt("groupon_num");
                poiDetailResult.commentNum = jSONObject.optInt("comment_num");
                poiDetailResult.favoriteNum = jSONObject.optInt("favorite_num");
                poiDetailResult.checkinNum = jSONObject.optInt("checkin_num");
                poiDetailResult.shopHours = jSONObject.optString("shop_hours");
            }
            poiDetailResult.error = ERRORNO.NO_ERROR;
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    public void mo2683a(String str) {
        PoiDetailResult poiDetailResult = new PoiDetailResult();
        if (!m5040a(str, poiDetailResult)) {
            poiDetailResult.error = ERRORNO.RESULT_NOT_FOUND;
        }
        this.a.mo2687a(poiDetailResult);
    }
}
