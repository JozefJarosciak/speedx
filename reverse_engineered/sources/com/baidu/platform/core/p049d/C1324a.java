package com.baidu.platform.core.p049d;

import com.alipay.sdk.util.C0882j;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.route.BikingRouteLine;
import com.baidu.mapapi.search.route.BikingRouteLine.BikingStep;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.SuggestAddrInfo;
import com.baidu.platform.base.C1212e;
import com.baidu.platform.comapi.util.CoordTrans;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.platform.core.d.a */
public class C1324a extends C1212e {
    /* renamed from: a */
    private LatLng m5069a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        LatLng latLng = new LatLng(jSONObject.optDouble(MapboxEvent.KEY_LATITUDE), jSONObject.optDouble(MapboxEvent.KEY_LONGITUDE));
        return SDKInitializer.getCoordType() == CoordType.GCJ02 ? CoordTrans.baiduToGcj(latLng) : latLng;
    }

    /* renamed from: a */
    private RouteNode m5070a(JSONObject jSONObject, String str, String str2) {
        if (jSONObject == null || str == null || "".equals(str)) {
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        if (optJSONObject == null) {
            return null;
        }
        RouteNode routeNode = new RouteNode();
        routeNode.setTitle(optJSONObject.optString("cname"));
        routeNode.setUid(optJSONObject.optString("uid"));
        optJSONObject = optJSONObject.optJSONObject(str2);
        if (optJSONObject != null) {
            LatLng latLng = new LatLng(optJSONObject.optDouble(MapboxEvent.KEY_LATITUDE), optJSONObject.optDouble(MapboxEvent.KEY_LONGITUDE));
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                latLng = CoordTrans.baiduToGcj(latLng);
            }
            routeNode.setLocation(latLng);
        }
        return routeNode;
    }

    /* renamed from: a */
    private List<BikingStep> m5071a(JSONArray jSONArray) {
        int i = 1;
        int i2 = 0;
        int i3 = jSONArray == null ? 1 : 0;
        int length = jSONArray.length();
        if (length > 0) {
            i = 0;
        }
        if ((i3 | i) != 0) {
            return null;
        }
        List<BikingStep> arrayList = new ArrayList();
        while (i2 < length) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i2);
            if (optJSONObject != null) {
                BikingStep bikingStep = new BikingStep();
                bikingStep.setDirection(optJSONObject.optInt("direction") * 30);
                bikingStep.setDistance(optJSONObject.optInt("distance"));
                bikingStep.setDuration(optJSONObject.optInt("duration"));
                bikingStep.setEntrance(RouteNode.location(m5069a(optJSONObject.optJSONObject("stepOriginLocation"))));
                bikingStep.setExit(RouteNode.location(m5069a(optJSONObject.optJSONObject("stepDestinationLocation"))));
                String optString = optJSONObject.optString("instructions");
                if (optString != null || optString.length() >= 4) {
                    optString = optString.replaceAll("</?[a-z]>", "");
                }
                bikingStep.setInstructions(optString);
                bikingStep.setEntranceInstructions(optJSONObject.optString("stepOriginInstruction"));
                bikingStep.setExitInstructions(optJSONObject.optString("stepDestinationInstruction"));
                bikingStep.setPathString(optJSONObject.optString("path"));
                arrayList.add(bikingStep);
            }
            i2++;
        }
        return arrayList.size() > 0 ? arrayList : null;
    }

    /* renamed from: a */
    private boolean m5072a(String str, BikingRouteResult bikingRouteResult) {
        if (str == null || str.length() <= 0) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject == null) {
                return false;
            }
            switch (jSONObject.optInt("status_sdk")) {
                case 0:
                    JSONObject optJSONObject = jSONObject.optJSONObject(C0882j.f2229c);
                    if (optJSONObject == null) {
                        return false;
                    }
                    int optInt = jSONObject.optInt("type");
                    if (optInt == 1) {
                        bikingRouteResult.setSuggestAddrInfo(m5073b(optJSONObject));
                        bikingRouteResult.error = ERRORNO.AMBIGUOUS_ROURE_ADDR;
                    } else if (optInt != 2) {
                        return false;
                    } else {
                        JSONArray optJSONArray = optJSONObject.optJSONArray("routes");
                        if (optJSONArray == null || optJSONArray.length() <= 0) {
                            return false;
                        }
                        RouteNode a = m5070a(optJSONObject, "origin", "originPt");
                        RouteNode a2 = m5070a(optJSONObject, "destination", "destinationPt");
                        List arrayList = new ArrayList();
                        optInt = 0;
                        while (optInt < optJSONArray.length()) {
                            BikingRouteLine bikingRouteLine = new BikingRouteLine();
                            try {
                                JSONObject optJSONObject2 = optJSONArray.optJSONObject(optInt);
                                if (optJSONObject2 == null) {
                                    return false;
                                }
                                bikingRouteLine.setStarting(a);
                                bikingRouteLine.setTerminal(a2);
                                bikingRouteLine.setDistance(optJSONObject2.optInt("distance"));
                                bikingRouteLine.setDuration(optJSONObject2.optInt("duration"));
                                bikingRouteLine.setSteps(m5071a(optJSONObject2.optJSONArray("steps")));
                                arrayList.add(bikingRouteLine);
                                optInt++;
                            } catch (Exception e) {
                            }
                        }
                        bikingRouteResult.setRouteLines(arrayList);
                    }
                    return true;
                case 1:
                    bikingRouteResult.error = ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;
                    return true;
                case 2:
                    bikingRouteResult.error = ERRORNO.SEARCH_OPTION_ERROR;
                    return false;
                default:
                    return false;
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    private SuggestAddrInfo m5073b(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        SuggestAddrInfo suggestAddrInfo = new SuggestAddrInfo();
        JSONObject optJSONObject = jSONObject.optJSONObject("origin");
        JSONObject optJSONObject2 = jSONObject.optJSONObject("destination");
        if (optJSONObject != null) {
            int optInt = optJSONObject.optInt("listType");
            String optString = optJSONObject.optString("cityName");
            if (optInt == 1) {
                suggestAddrInfo.setSuggestStartCity(m5075a(optJSONObject, "content"));
            } else if (optInt == 0) {
                suggestAddrInfo.setSuggestStartNode(m5074b(optJSONObject, "content", optString));
            }
        }
        if (optJSONObject2 == null) {
            return suggestAddrInfo;
        }
        int optInt2 = optJSONObject2.optInt("listType");
        String optString2 = optJSONObject2.optString("cityName");
        if (optInt2 == 1) {
            suggestAddrInfo.setSuggestEndCity(m5075a(optJSONObject2, "content"));
            return suggestAddrInfo;
        } else if (optInt2 != 0) {
            return suggestAddrInfo;
        } else {
            suggestAddrInfo.setSuggestEndNode(m5074b(optJSONObject2, "content", optString2));
            return suggestAddrInfo;
        }
    }

    /* renamed from: b */
    private List<PoiInfo> m5074b(JSONObject jSONObject, String str, String str2) {
        if (jSONObject == null || str == null || "".equals(str)) {
            return null;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray != null) {
            List<PoiInfo> arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject jSONObject2 = (JSONObject) optJSONArray.opt(i);
                if (jSONObject2 != null) {
                    PoiInfo poiInfo = new PoiInfo();
                    if (jSONObject2.has(GeocodingCriteria.TYPE_ADDRESS)) {
                        poiInfo.address = jSONObject2.optString(GeocodingCriteria.TYPE_ADDRESS);
                    }
                    poiInfo.uid = jSONObject2.optString("uid");
                    poiInfo.name = jSONObject2.optString("name");
                    jSONObject2 = jSONObject2.optJSONObject(MapboxEvent.TYPE_LOCATION);
                    if (jSONObject2 != null) {
                        poiInfo.location = new LatLng(jSONObject2.optDouble(MapboxEvent.KEY_LATITUDE), jSONObject2.optDouble(MapboxEvent.KEY_LONGITUDE));
                        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                            poiInfo.location = CoordTrans.baiduToGcj(poiInfo.location);
                        }
                    }
                    poiInfo.city = str2;
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
    public List<CityInfo> m5075a(JSONObject jSONObject, String str) {
        if (jSONObject == null || str == null || str.equals("")) {
            return null;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject jSONObject2 = (JSONObject) optJSONArray.opt(i);
            if (jSONObject2 != null) {
                CityInfo cityInfo = new CityInfo();
                cityInfo.num = jSONObject2.optInt("number");
                cityInfo.city = jSONObject2.optString("name");
                arrayList.add(cityInfo);
            }
        }
        arrayList.trimToSize();
        return arrayList;
    }

    /* renamed from: a */
    public void mo2683a(String str) {
        BikingRouteResult bikingRouteResult = new BikingRouteResult();
        if (!(m4539a(str, bikingRouteResult, false) || m5072a(str, bikingRouteResult))) {
            bikingRouteResult.error = ERRORNO.RESULT_NOT_FOUND;
        }
        this.a.mo2687a(bikingRouteResult);
    }
}
