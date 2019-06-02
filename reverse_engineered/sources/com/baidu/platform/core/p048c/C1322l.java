package com.baidu.platform.core.p048c;

import com.alipay.sdk.util.C0882j;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.PoiInfo.POITYPE;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.poi.PoiAddrInfo;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.platform.base.C1212e;
import com.baidu.platform.base.SearchType;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.platform.core.c.l */
public class C1322l extends C1212e {
    /* renamed from: c */
    int f3961c;
    /* renamed from: d */
    int f3962d;

    C1322l(int i, int i2) {
        this.f3961c = i;
        this.f3962d = i2;
    }

    /* renamed from: a */
    private boolean m5062a(String str, PoiResult poiResult) {
        if (str == null || str.equals("")) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject optJSONObject = jSONObject.optJSONObject(C0882j.f2229c);
            if (optJSONObject == null || optJSONObject.optInt("error") != 0) {
                return false;
            }
            JSONObject optJSONObject2 = jSONObject.optJSONObject("poi_result");
            if (optJSONObject2 == null) {
                return false;
            }
            jSONObject = optJSONObject2.optJSONObject("option");
            JSONArray optJSONArray = optJSONObject2.optJSONArray("contents");
            if (jSONObject == null || optJSONArray == null) {
                return false;
            }
            int length = optJSONArray.length();
            if (length <= 0) {
                return false;
            }
            int optInt = jSONObject.optInt("total");
            poiResult.setTotalPoiNum(optInt);
            poiResult.setCurrentPageCapacity(length);
            poiResult.setCurrentPageNum(this.f3961c);
            if (length != 0) {
                poiResult.setTotalPageNum((optInt % this.f3962d > 0 ? 1 : 0) + (optInt / this.f3962d));
            }
            optJSONObject = optJSONObject2.optJSONObject("current_city");
            String str2 = null;
            if (optJSONObject != null) {
                str2 = optJSONObject.optString("name");
            }
            Object arrayList = new ArrayList();
            for (int i = 0; i < length; i++) {
                JSONObject optJSONObject3 = optJSONArray.optJSONObject(i);
                PoiInfo poiInfo = new PoiInfo();
                if (optJSONObject3 != null) {
                    poiInfo.name = optJSONObject3.optString("name");
                    poiInfo.address = optJSONObject3.optString("addr");
                    poiInfo.uid = optJSONObject3.optString("uid");
                    poiInfo.phoneNum = optJSONObject3.optString("tel");
                    poiInfo.type = POITYPE.fromInt(optJSONObject3.optInt("poiType"));
                    poiInfo.isPano = optJSONObject3.optInt("pano") == 1;
                    if (!(poiInfo.type == POITYPE.BUS_LINE || poiInfo.type == POITYPE.SUBWAY_LINE)) {
                        poiInfo.location = CoordUtil.decodeLocation(optJSONObject3.optString("geo"));
                    }
                    poiInfo.city = str2;
                    optJSONObject = optJSONObject3.optJSONObject("ext");
                    if (optJSONObject != null) {
                        if ("cater".equals(optJSONObject.optString("src_name")) && optJSONObject3.optJSONObject("detail_info") != null) {
                            poiInfo.hasCaterDetails = true;
                        }
                    }
                    arrayList.add(poiInfo);
                }
            }
            if (arrayList.size() > 0) {
                poiResult.setPoiInfo(arrayList);
            }
            JSONArray optJSONArray2 = optJSONObject2.optJSONArray("addrs");
            Object arrayList2 = new ArrayList();
            if (optJSONArray2 != null) {
                for (optInt = 0; optInt < optJSONArray2.length(); optInt++) {
                    optJSONObject2 = optJSONArray2.optJSONObject(optInt);
                    PoiAddrInfo poiAddrInfo = new PoiAddrInfo();
                    if (optJSONObject2 != null) {
                        poiAddrInfo.name = optJSONObject2.optString("name");
                        poiAddrInfo.address = optJSONObject2.optString("addr");
                        poiAddrInfo.location = CoordUtil.decodeLocation(optJSONObject2.optString("geo"));
                        arrayList2.add(poiAddrInfo);
                    }
                }
            }
            if (arrayList2.size() > 0) {
                poiResult.setAddrInfo(arrayList2);
                poiResult.setHasAddrInfo(true);
            }
            if (arrayList2.size() <= 0 && arrayList.size() <= 0) {
                return false;
            }
            poiResult.error = ERRORNO.NO_ERROR;
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    private boolean m5063b(String str, PoiResult poiResult) {
        if (str == null || str.equals("") || poiResult == null) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject == null) {
                return false;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject(C0882j.f2229c);
            jSONObject = jSONObject.optJSONObject("traffic_citys");
            if (optJSONObject == null || jSONObject == null || optJSONObject.optInt("type") != 7 || optJSONObject.optInt("error") != 0) {
                return false;
            }
            JSONArray optJSONArray = jSONObject.optJSONArray("contents");
            if (optJSONArray == null || optJSONArray.length() <= 0) {
                return false;
            }
            List arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                if (optJSONObject2 != null) {
                    CityInfo cityInfo = new CityInfo();
                    cityInfo.num = optJSONObject2.optInt("num");
                    cityInfo.city = optJSONObject2.optString("name");
                    arrayList.add(cityInfo);
                }
            }
            if (arrayList.size() <= 0) {
                return false;
            }
            poiResult.setSuggestCityList(arrayList);
            poiResult.error = ERRORNO.AMBIGUOUS_KEYWORD;
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    public void mo2683a(String str) {
        boolean z = false;
        SearchResult poiResult = new PoiResult();
        SearchType searchType = this.b;
        if (SearchType.POI_IN_CITY_SEARCH == m4535a()) {
            z = m4539a(str, poiResult, false);
        }
        if (!(z || m5063b(str, poiResult) || m5062a(str, poiResult))) {
            poiResult.error = ERRORNO.RESULT_NOT_FOUND;
        }
        this.a.mo2687a(poiResult);
    }
}
