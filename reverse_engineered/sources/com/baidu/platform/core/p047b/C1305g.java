package com.baidu.platform.core.p047b;

import com.alipay.sdk.util.C0882j;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult;
import com.baidu.mapapi.search.geocode.ReverseGeoCodeResult.AddressComponent;
import com.baidu.platform.base.C1212e;
import com.baidu.platform.comapi.util.CoordTrans;
import com.mapbox.mapboxsdk.style.layers.Property;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.platform.core.b.g */
public class C1305g extends C1212e {
    /* renamed from: a */
    private AddressComponent m5008a(JSONObject jSONObject, String str) {
        if (jSONObject == null || str == null || "".equals(str)) {
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            return null;
        }
        AddressComponent addressComponent = new AddressComponent();
        addressComponent.city = optJSONObject.optString("city");
        addressComponent.district = optJSONObject.optString("district");
        addressComponent.province = optJSONObject.optString("province");
        addressComponent.street = optJSONObject.optString("street");
        addressComponent.streetNumber = optJSONObject.optString("street_number");
        addressComponent.countryName = optJSONObject.optString(GeocodingCriteria.TYPE_COUNTRY);
        addressComponent.countryCode = optJSONObject.optInt("country_code");
        return addressComponent;
    }

    /* renamed from: a */
    private List<PoiInfo> m5009a(JSONObject jSONObject, String str, String str2) {
        if (jSONObject == null || str == null || "".equals(str)) {
            return null;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        List<PoiInfo> arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                PoiInfo poiInfo = new PoiInfo();
                poiInfo.address = optJSONObject.optString("addr");
                poiInfo.phoneNum = optJSONObject.optString("tel");
                poiInfo.uid = optJSONObject.optString("uid");
                poiInfo.postCode = optJSONObject.optString("zip");
                poiInfo.name = optJSONObject.optString("name");
                poiInfo.location = m5012b(optJSONObject, Property.SYMBOL_PLACEMENT_POINT);
                poiInfo.city = str2;
                arrayList.add(poiInfo);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    private boolean m5010a(String str, ReverseGeoCodeResult reverseGeoCodeResult) {
        if (str != null) {
            try {
                if (str.length() > 0) {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject == null) {
                        reverseGeoCodeResult.error = ERRORNO.RESULT_NOT_FOUND;
                        return false;
                    }
                    int optInt = jSONObject.optInt("status");
                    if (optInt != 0) {
                        switch (optInt) {
                            case 1:
                                reverseGeoCodeResult.error = ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                                return false;
                            case 2:
                                reverseGeoCodeResult.error = ERRORNO.SEARCH_OPTION_ERROR;
                                return false;
                            default:
                                reverseGeoCodeResult.error = ERRORNO.RESULT_NOT_FOUND;
                                return false;
                        }
                    } else if (m5011a(jSONObject, reverseGeoCodeResult)) {
                        return true;
                    } else {
                        reverseGeoCodeResult.error = ERRORNO.RESULT_NOT_FOUND;
                        return false;
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
                reverseGeoCodeResult.error = ERRORNO.RESULT_NOT_FOUND;
                return false;
            }
        }
        reverseGeoCodeResult.error = ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
        return false;
    }

    /* renamed from: a */
    private boolean m5011a(JSONObject jSONObject, ReverseGeoCodeResult reverseGeoCodeResult) {
        if (jSONObject == null) {
            return false;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(C0882j.f2229c);
        if (optJSONObject == null) {
            return false;
        }
        reverseGeoCodeResult.setAddress(optJSONObject.optString("formatted_address"));
        reverseGeoCodeResult.setBusinessCircle(optJSONObject.optString("business"));
        reverseGeoCodeResult.setAddressDetail(m5008a(optJSONObject, "addressComponent"));
        reverseGeoCodeResult.setLocation(m5013c(optJSONObject, MapboxEvent.TYPE_LOCATION));
        String str = "";
        if (reverseGeoCodeResult.getAddressDetail() != null) {
            str = reverseGeoCodeResult.getAddressDetail().city;
        }
        reverseGeoCodeResult.setPoiList(m5009a(optJSONObject, "pois", str));
        reverseGeoCodeResult.setSematicDescription(optJSONObject.optString("sematic_description"));
        reverseGeoCodeResult.error = ERRORNO.NO_ERROR;
        return true;
    }

    /* renamed from: b */
    private LatLng m5012b(JSONObject jSONObject, String str) {
        if (jSONObject == null || str == null || "".equals(str)) {
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            return null;
        }
        LatLng latLng = new LatLng(optJSONObject.optDouble("y"), optJSONObject.optDouble("x"));
        return SDKInitializer.getCoordType() == CoordType.GCJ02 ? CoordTrans.baiduToGcj(latLng) : latLng;
    }

    /* renamed from: c */
    private LatLng m5013c(JSONObject jSONObject, String str) {
        if (jSONObject == null || str == null || "".equals(str)) {
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            return null;
        }
        LatLng latLng = new LatLng(optJSONObject.optDouble(MapboxEvent.KEY_LATITUDE), optJSONObject.optDouble(MapboxEvent.KEY_LONGITUDE));
        return SDKInitializer.getCoordType() == CoordType.GCJ02 ? CoordTrans.baiduToGcj(latLng) : latLng;
    }

    /* renamed from: a */
    public void mo2683a(String str) {
        ReverseGeoCodeResult reverseGeoCodeResult = new ReverseGeoCodeResult();
        if (!m4539a(str, reverseGeoCodeResult, true)) {
            m5010a(str, reverseGeoCodeResult);
        }
        this.a.mo2687a(reverseGeoCodeResult);
    }
}
