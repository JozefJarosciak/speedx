package com.baidu.platform.core.p049d;

import com.alipay.sdk.util.C0882j;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.core.TaxiInfo;
import com.baidu.mapapi.search.core.VehicleInfo;
import com.baidu.mapapi.search.route.TransitRouteLine;
import com.baidu.mapapi.search.route.TransitRouteLine.TransitStep;
import com.baidu.mapapi.search.route.TransitRouteLine.TransitStep.TransitRouteStepType;
import com.baidu.mapapi.search.route.TransitRouteResult;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.platform.core.d.r */
public class C1342r extends C1326q {
    /* renamed from: a */
    private RouteNode m5142a(JSONObject jSONObject, String str) {
        if (jSONObject == null || str == null || "".equals(str)) {
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(str);
        RouteNode routeNode = new RouteNode();
        routeNode.setTitle(optJSONObject.optString("wd"));
        routeNode.setUid(optJSONObject.optString("uid"));
        routeNode.setLocation(CoordUtil.decodeLocation(optJSONObject.optString("pt")));
        return routeNode;
    }

    /* renamed from: a */
    private TaxiInfo m5143a(JSONObject jSONObject) {
        float f = 0.0f;
        if (jSONObject == null) {
            return null;
        }
        TaxiInfo taxiInfo = new TaxiInfo();
        JSONArray optJSONArray = jSONObject.optJSONArray("detail");
        if (optJSONArray == null || optJSONArray.length() <= 0) {
            return null;
        }
        float optDouble;
        float optDouble2;
        int length = optJSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObject2 = (JSONObject) optJSONArray.opt(i);
            if (jSONObject2 != null && jSONObject2.optString("desc").contains("白天")) {
                optDouble = (float) jSONObject2.optDouble("km_price");
                f = (float) jSONObject2.optDouble("start_price");
                optDouble2 = (float) jSONObject2.optDouble("total_price");
                break;
            }
        }
        optDouble2 = 0.0f;
        optDouble = 0.0f;
        taxiInfo.setDesc(jSONObject.optString("remark"));
        taxiInfo.setDistance(jSONObject.optInt("distance"));
        taxiInfo.setDuration(jSONObject.optInt("duration"));
        taxiInfo.setTotalPrice(optDouble2);
        taxiInfo.setStartPrice(f);
        taxiInfo.setPerKMPrice(optDouble);
        return taxiInfo;
    }

    /* renamed from: b */
    private String m5144b(String str) {
        if (str == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        char[] toCharArray = str.toCharArray();
        Object obj = null;
        for (int i = 0; i < toCharArray.length; i++) {
            if (toCharArray[i] == '<') {
                obj = 1;
            } else if (toCharArray[i] == '>') {
                obj = null;
            } else if (obj == null) {
                stringBuilder.append(toCharArray[i]);
            }
        }
        return stringBuilder.toString();
    }

    /* renamed from: b */
    private boolean m5145b(String str, TransitRouteResult transitRouteResult) {
        if (str == null || str.length() <= 0) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject == null) {
                return false;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject(C0882j.f2229c);
            if (optJSONObject == null) {
                return false;
            }
            switch (optJSONObject.optInt("error")) {
                case 0:
                    jSONObject = jSONObject.optJSONObject("bus");
                    if (jSONObject == null) {
                        return false;
                    }
                    optJSONObject = jSONObject.optJSONObject("taxi");
                    if (optJSONObject != null) {
                        transitRouteResult.setTaxiInfo(m5143a(optJSONObject));
                    }
                    optJSONObject = jSONObject.optJSONObject("option");
                    if (optJSONObject == null) {
                        return false;
                    }
                    RouteNode a = m5142a(optJSONObject, "start");
                    RouteNode a2 = m5142a(optJSONObject, "end");
                    JSONArray optJSONArray = jSONObject.optJSONArray("routes");
                    if (optJSONArray == null || optJSONArray.length() <= 0) {
                        return false;
                    }
                    List arrayList = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        jSONObject = (JSONObject) ((JSONObject) optJSONArray.opt(i)).optJSONArray("legs").opt(0);
                        if (jSONObject != null) {
                            TransitRouteLine transitRouteLine = new TransitRouteLine();
                            transitRouteLine.setDistance(jSONObject.optInt("distance"));
                            transitRouteLine.setDuration(jSONObject.optInt("duration"));
                            transitRouteLine.setStarting(a);
                            transitRouteLine.setTerminal(a2);
                            JSONArray optJSONArray2 = jSONObject.optJSONArray("steps");
                            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                                List arrayList2 = new ArrayList();
                                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                    JSONArray optJSONArray3 = optJSONArray2.optJSONObject(i2).optJSONArray("step");
                                    if (optJSONArray3 != null && optJSONArray3.length() > 0) {
                                        JSONObject optJSONObject2 = optJSONArray3.optJSONObject(0);
                                        TransitStep transitStep = new TransitStep();
                                        transitStep.setEntrace(RouteNode.location(CoordUtil.decodeLocation(optJSONObject2.optString("start_location"))));
                                        transitStep.setExit(RouteNode.location(CoordUtil.decodeLocation(optJSONObject2.optString("end_location"))));
                                        if (optJSONObject2.optInt("type") == 5) {
                                            transitStep.setStepType(TransitRouteStepType.WAKLING);
                                        } else {
                                            transitStep.setStepType(TransitRouteStepType.BUSLINE);
                                        }
                                        transitStep.setInstructions(m5144b(optJSONObject2.optString("instructions")));
                                        transitStep.setDistance(optJSONObject2.optInt("distance"));
                                        transitStep.setDuration(optJSONObject2.optInt("duration"));
                                        transitStep.setPathString(optJSONObject2.optString("path"));
                                        if (optJSONObject2.has("vehicle")) {
                                            transitStep.setVehicleInfo(m5146c(optJSONObject2.optString("vehicle")));
                                            optJSONObject2 = optJSONObject2.optJSONObject("vehicle");
                                            transitStep.getEntrance().setUid(optJSONObject2.optString("start_uid"));
                                            transitStep.getEntrance().setTitle(optJSONObject2.optString("start_name"));
                                            transitStep.getExit().setUid(optJSONObject2.optString("end_uid"));
                                            transitStep.getExit().setTitle(optJSONObject2.optString("end_name"));
                                            Integer valueOf = Integer.valueOf(optJSONObject2.optInt("type"));
                                            if (valueOf == null) {
                                                transitStep.setStepType(TransitRouteStepType.BUSLINE);
                                            } else if (valueOf.intValue() == 1) {
                                                transitStep.setStepType(TransitRouteStepType.SUBWAY);
                                            } else {
                                                transitStep.setStepType(TransitRouteStepType.BUSLINE);
                                            }
                                        }
                                        arrayList2.add(transitStep);
                                    }
                                }
                                transitRouteLine.setSteps(arrayList2);
                                arrayList.add(transitRouteLine);
                            }
                        }
                    }
                    transitRouteResult.setRoutelines(arrayList);
                    return true;
                case 1:
                    transitRouteResult.error = ERRORNO.ST_EN_TOO_NEAR;
                    return true;
                case 200:
                    transitRouteResult.error = ERRORNO.NOT_SUPPORT_BUS_2CITY;
                    return true;
                default:
                    return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: c */
    private VehicleInfo m5146c(String str) {
        JSONObject jSONObject;
        try {
            jSONObject = new JSONObject(str);
        } catch (JSONException e) {
            e.printStackTrace();
            jSONObject = null;
        }
        if (jSONObject == null) {
            return null;
        }
        VehicleInfo vehicleInfo = new VehicleInfo();
        vehicleInfo.setZonePrice(jSONObject.optInt("zone_price"));
        vehicleInfo.setTotalPrice(jSONObject.optInt("total_price"));
        vehicleInfo.setTitle(jSONObject.optString("name"));
        vehicleInfo.setPassStationNum(jSONObject.optInt("stop_num"));
        vehicleInfo.setUid(jSONObject.optString("uid"));
        return vehicleInfo;
    }

    /* renamed from: a */
    public void m5147a(String str, TransitRouteResult transitRouteResult) {
        if (!m4539a(str, transitRouteResult, false) && !m5145b(str, transitRouteResult)) {
            transitRouteResult.error = ERRORNO.RESULT_NOT_FOUND;
        }
    }
}
