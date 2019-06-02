package com.baidu.platform.core.p049d;

import com.alipay.sdk.util.C0882j;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.SuggestAddrInfo;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.platform.base.C1212e;
import com.baidu.platform.base.SearchType;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.platform.core.d.q */
public class C1326q extends C1212e {
    /* renamed from: c */
    SuggestAddrInfo f3963c = null;
    /* renamed from: d */
    protected boolean f3964d;

    /* renamed from: a */
    private SuggestAddrInfo m5079a(JSONObject jSONObject) {
        int i = 0;
        SuggestAddrInfo suggestAddrInfo = null;
        if (jSONObject != null) {
            JSONObject optJSONObject = jSONObject.optJSONObject("traffic_pois");
            if (optJSONObject != null) {
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("option");
                JSONObject optJSONObject3 = optJSONObject.optJSONObject("content");
                if (!(optJSONObject2 == null || optJSONObject3 == null)) {
                    String optString;
                    JSONArray optJSONArray;
                    JSONArray optJSONArray2;
                    int length;
                    boolean[] zArr;
                    boolean[] zArr2;
                    int i2;
                    int parseInt;
                    int parseInt2;
                    optJSONObject = optJSONObject2.optJSONObject("start_city");
                    String optString2 = optJSONObject != null ? optJSONObject.optString("cname") : null;
                    JSONArray optJSONArray3 = optJSONObject2.optJSONArray("end_city");
                    if (optJSONArray3 != null) {
                        optJSONObject = (JSONObject) optJSONArray3.opt(0);
                        if (optJSONObject != null) {
                            optString = optJSONObject.optString("cname");
                            optJSONArray = optJSONObject2.optJSONArray("city_list");
                            optJSONArray2 = optJSONObject2.optJSONArray("prio_flag");
                            if (!(optJSONArray == null || optJSONArray2 == null)) {
                                length = optJSONArray.length();
                                zArr = new boolean[length];
                                zArr2 = new boolean[length];
                                for (i2 = 0; i2 < length; i2++) {
                                    parseInt = Integer.parseInt(optJSONArray.optString(i2));
                                    parseInt2 = Integer.parseInt(optJSONArray2.optString(i2));
                                    zArr[i2] = parseInt != 1;
                                    zArr2[i2] = parseInt2 != 1;
                                }
                                suggestAddrInfo = new SuggestAddrInfo();
                                while (i < length) {
                                    if (!zArr2[i]) {
                                        if (zArr[i]) {
                                            if (i != 0) {
                                                suggestAddrInfo.setSuggestStartNode(m5081a(optJSONObject3.optJSONArray("start"), optString2));
                                            } else if (i == length - 1 || i <= 0) {
                                                suggestAddrInfo.setSuggestWpNode(m5083b(optJSONObject3, "multi_waypoints"));
                                            } else {
                                                suggestAddrInfo.setSuggestEndNode(m5081a(optJSONObject3.optJSONArray("end"), optString));
                                            }
                                        } else if (i != 0) {
                                            suggestAddrInfo.setSuggestStartCity(m5080a(optJSONObject3.optJSONArray("start")));
                                        } else if (i == length - 1 || i <= 0) {
                                            suggestAddrInfo.setSuggestWpCity(m5082a(optJSONObject3, "multi_waypoints"));
                                        } else {
                                            suggestAddrInfo.setSuggestEndCity(m5080a(optJSONObject3.optJSONArray("end")));
                                        }
                                    }
                                    i++;
                                }
                            }
                        }
                    }
                    optString = null;
                    optJSONArray = optJSONObject2.optJSONArray("city_list");
                    optJSONArray2 = optJSONObject2.optJSONArray("prio_flag");
                    length = optJSONArray.length();
                    zArr = new boolean[length];
                    zArr2 = new boolean[length];
                    for (i2 = 0; i2 < length; i2++) {
                        parseInt = Integer.parseInt(optJSONArray.optString(i2));
                        parseInt2 = Integer.parseInt(optJSONArray2.optString(i2));
                        if (parseInt != 1) {
                        }
                        zArr[i2] = parseInt != 1;
                        if (parseInt2 != 1) {
                        }
                        zArr2[i2] = parseInt2 != 1;
                    }
                    suggestAddrInfo = new SuggestAddrInfo();
                    while (i < length) {
                        if (!zArr2[i]) {
                            if (zArr[i]) {
                                if (i != 0) {
                                    if (i == length - 1) {
                                    }
                                    suggestAddrInfo.setSuggestWpNode(m5083b(optJSONObject3, "multi_waypoints"));
                                } else {
                                    suggestAddrInfo.setSuggestStartNode(m5081a(optJSONObject3.optJSONArray("start"), optString2));
                                }
                            } else if (i != 0) {
                                if (i == length - 1) {
                                }
                                suggestAddrInfo.setSuggestWpCity(m5082a(optJSONObject3, "multi_waypoints"));
                            } else {
                                suggestAddrInfo.setSuggestStartCity(m5080a(optJSONObject3.optJSONArray("start")));
                            }
                        }
                        i++;
                    }
                }
            }
        }
        return suggestAddrInfo;
    }

    /* renamed from: a */
    private List<CityInfo> m5080a(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject jSONObject = (JSONObject) jSONArray.opt(i);
            if (jSONObject != null) {
                CityInfo cityInfo = new CityInfo();
                cityInfo.num = jSONObject.optInt("num");
                cityInfo.city = jSONObject.optString("name");
                arrayList.add(cityInfo);
            }
        }
        arrayList.trimToSize();
        return arrayList;
    }

    /* renamed from: a */
    private List<PoiInfo> m5081a(JSONArray jSONArray, String str) {
        if (jSONArray != null) {
            List<PoiInfo> arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = (JSONObject) jSONArray.opt(i);
                if (jSONObject != null) {
                    PoiInfo poiInfo = new PoiInfo();
                    poiInfo.address = jSONObject.optString("addr");
                    poiInfo.uid = jSONObject.optString("uid");
                    poiInfo.name = jSONObject.optString("name");
                    poiInfo.location = CoordUtil.decodeLocation(jSONObject.optString("geo"));
                    poiInfo.city = str;
                    arrayList.add(poiInfo);
                }
            }
            if (arrayList.size() > 0) {
                return arrayList;
            }
        }
        return null;
    }

    /* renamed from: a */
    private List<List<CityInfo>> m5082a(JSONObject jSONObject, String str) {
        List<List<CityInfo>> arrayList = new ArrayList();
        if (jSONObject == null) {
            return null;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            List a = m5080a((JSONArray) optJSONArray.opt(i));
            if (a != null) {
                arrayList.add(a);
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    private List<List<PoiInfo>> m5083b(JSONObject jSONObject, String str) {
        List<List<PoiInfo>> arrayList = new ArrayList();
        if (jSONObject == null) {
            return null;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        for (int i = 0; i < optJSONArray.length(); i++) {
            List a = m5081a((JSONArray) optJSONArray.opt(i), "");
            if (a != null) {
                arrayList.add(a);
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    private boolean m5084b(String str) {
        if (str == null || str.length() <= 0) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject == null) {
                return false;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject(C0882j.f2229c);
            if (optJSONObject == null || optJSONObject.optInt("type") != 23 || optJSONObject.optInt("error") != 0) {
                return false;
            }
            this.f3963c = m5079a(jSONObject);
            return this.f3963c != null;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    public void mo2683a(String str) {
        SearchType a = m4535a();
        if (m5084b(str)) {
            this.f3964d = true;
        } else {
            this.f3964d = false;
        }
        switch (a) {
            case TRANSIT_ROUTE:
                TransitRouteResult transitRouteResult = new TransitRouteResult();
                if (this.f3964d) {
                    transitRouteResult.setSuggestAddrInfo(this.f3963c);
                    transitRouteResult.error = ERRORNO.AMBIGUOUS_ROURE_ADDR;
                } else {
                    ((C1342r) this).m5147a(str, transitRouteResult);
                }
                this.a.mo2687a(transitRouteResult);
                return;
            case DRIVE_ROUTE:
                DrivingRouteResult drivingRouteResult = new DrivingRouteResult();
                if (this.f3964d) {
                    drivingRouteResult.setSuggestAddrInfo(this.f3963c);
                    drivingRouteResult.error = ERRORNO.AMBIGUOUS_ROURE_ADDR;
                } else {
                    ((C1327c) this).m5094a(str, drivingRouteResult);
                }
                this.a.mo2687a(drivingRouteResult);
                return;
            case WALK_ROUTE:
                WalkingRouteResult walkingRouteResult = new WalkingRouteResult();
                if (this.f3964d) {
                    walkingRouteResult.setSuggestAddrInfo(this.f3963c);
                    walkingRouteResult.error = ERRORNO.AMBIGUOUS_ROURE_ADDR;
                } else {
                    ((C1344t) this).m5155a(str, walkingRouteResult);
                }
                this.a.mo2687a(walkingRouteResult);
                return;
            default:
                return;
        }
    }
}
