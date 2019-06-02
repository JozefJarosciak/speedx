package com.baidu.platform.core.p048c;

import com.alipay.sdk.packet.C0861d;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.poi.PoiIndoorInfo;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.platform.base.C1212e;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import io.rong.imlib.statistics.UserData;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.platform.core.c.b */
public class C1312b extends C1212e {
    /* renamed from: a */
    private boolean m5036a(String str, PoiIndoorResult poiIndoorResult) {
        if (str == null || "".equals(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            switch (jSONObject.optInt("errNo")) {
                case 0:
                    JSONObject optJSONObject = jSONObject.optJSONObject(C0861d.f2139k);
                    if (optJSONObject == null) {
                        return false;
                    }
                    JSONArray optJSONArray = optJSONObject.optJSONArray("poi_list");
                    if (optJSONArray == null || optJSONArray.length() <= 0) {
                        poiIndoorResult.error = ERRORNO.RESULT_NOT_FOUND;
                    } else {
                        List arrayList = new ArrayList();
                        for (int i = 0; i < optJSONArray.length(); i++) {
                            JSONObject jSONObject2 = (JSONObject) optJSONArray.opt(i);
                            if (jSONObject2 != null) {
                                PoiIndoorInfo poiIndoorInfo = new PoiIndoorInfo();
                                poiIndoorInfo.address = jSONObject2.optString(GeocodingCriteria.TYPE_ADDRESS);
                                poiIndoorInfo.bid = jSONObject2.optString("bd_id");
                                poiIndoorInfo.cid = jSONObject2.optInt("cid");
                                poiIndoorInfo.discount = jSONObject2.optInt("discount");
                                poiIndoorInfo.floor = jSONObject2.optString("floor");
                                poiIndoorInfo.name = jSONObject2.optString("name");
                                poiIndoorInfo.phone = jSONObject2.optString(UserData.PHONE_KEY);
                                poiIndoorInfo.price = (double) jSONObject2.optInt("price");
                                poiIndoorInfo.starLevel = jSONObject2.optInt("star_level");
                                poiIndoorInfo.tag = jSONObject2.optString(AnalyticsEvent.labelTag);
                                poiIndoorInfo.uid = jSONObject2.optString("uid");
                                poiIndoorInfo.groupNum = jSONObject2.optInt("tuan_nums");
                                int parseInt = Integer.parseInt(jSONObject2.optString("twp"));
                                if ((parseInt & 1) == 1) {
                                    poiIndoorInfo.isGroup = true;
                                }
                                if ((parseInt & 2) == 1) {
                                    poiIndoorInfo.isTakeOut = true;
                                }
                                if ((parseInt & 4) == 1) {
                                    poiIndoorInfo.isWaited = true;
                                }
                                poiIndoorInfo.latLng = CoordUtil.mc2ll(new GeoPoint(jSONObject2.optDouble("pt_y"), jSONObject2.optDouble("pt_x")));
                                arrayList.add(poiIndoorInfo);
                            }
                        }
                        poiIndoorResult.error = ERRORNO.NO_ERROR;
                        poiIndoorResult.setmArrayPoiInfo(arrayList);
                    }
                    poiIndoorResult.pageNum = optJSONObject.optInt("page_num");
                    poiIndoorResult.poiNum = optJSONObject.optInt("poi_num");
                    poiIndoorResult.error = ERRORNO.NO_ERROR;
                    return true;
                case 1:
                    String optString = jSONObject.optString("Msg");
                    if (optString.contains("bid")) {
                        poiIndoorResult.error = ERRORNO.POIINDOOR_BID_ERROR;
                        return true;
                    } else if (!optString.contains("floor")) {
                        return false;
                    } else {
                        poiIndoorResult.error = ERRORNO.POIINDOOR_FLOOR_ERROR;
                        return true;
                    }
                case 5:
                    return false;
                default:
                    poiIndoorResult.error = ERRORNO.POIINDOOR_SERVER_ERROR;
                    return true;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    public void mo2683a(String str) {
        SearchResult poiIndoorResult = new PoiIndoorResult();
        if (!(m4539a(str, poiIndoorResult, false) || m5036a(str, poiIndoorResult))) {
            poiIndoorResult.error = ERRORNO.RESULT_NOT_FOUND;
        }
        this.a.mo2687a(poiIndoorResult);
    }
}
