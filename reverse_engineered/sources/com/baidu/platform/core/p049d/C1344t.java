package com.baidu.platform.core.p049d;

import com.alipay.sdk.util.C0882j;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.core.TaxiInfo;
import com.baidu.mapapi.search.route.WalkingRouteLine;
import com.baidu.mapapi.search.route.WalkingRouteLine.WalkingStep;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.platform.core.d.t */
public class C1344t extends C1326q {
    /* renamed from: a */
    private RouteNode m5150a(JSONArray jSONArray, List<RouteNode> list) {
        if (jSONArray != null) {
            int length = jSONArray.length();
            if (length > 0) {
                int i = 0;
                while (i < length) {
                    RouteNode a = m5151a(jSONArray.optJSONObject(i));
                    if (i == length - 1) {
                        return a;
                    }
                    List<RouteNode> arrayList = list == null ? new ArrayList() : list;
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(a);
                    i++;
                    list = arrayList;
                }
                return null;
            }
        }
        return null;
    }

    /* renamed from: a */
    private RouteNode m5151a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        RouteNode routeNode = new RouteNode();
        routeNode.setTitle(jSONObject.optString("wd"));
        routeNode.setUid(jSONObject.optString("uid"));
        routeNode.setLocation(CoordUtil.decodeLocation(jSONObject.optString("pt")));
        return routeNode;
    }

    /* renamed from: a */
    private List<WalkingStep> m5152a(JSONArray jSONArray) {
        if (jSONArray != null) {
            int length = jSONArray.length();
            if (length > 0) {
                List<WalkingStep> arrayList = new ArrayList();
                for (int i = 0; i < length; i++) {
                    JSONObject optJSONObject = jSONArray.optJSONObject(i);
                    if (optJSONObject != null) {
                        WalkingStep walkingStep = new WalkingStep();
                        walkingStep.setDirection(optJSONObject.optInt("direction") * 30);
                        walkingStep.setDistance(optJSONObject.optInt("distance"));
                        walkingStep.setDuration(optJSONObject.optInt("duration"));
                        walkingStep.setEntrance(RouteNode.location(CoordUtil.decodeLocation(optJSONObject.optString("start_location"))));
                        walkingStep.setExit(RouteNode.location(CoordUtil.decodeLocation(optJSONObject.optString("end_location"))));
                        String optString = optJSONObject.optString("instructions");
                        if (optString != null || optString.length() >= 4) {
                            optString = optString.replaceAll("</?[a-z]>", "");
                        }
                        walkingStep.setInstructions(optString);
                        walkingStep.setEntranceInstructions(optJSONObject.optString("start_instructions"));
                        walkingStep.setExitInstructions(optJSONObject.optString("end_instructions"));
                        walkingStep.setPathString(optJSONObject.optString("path"));
                        arrayList.add(walkingStep);
                    }
                }
                return arrayList;
            }
        }
        return null;
    }

    /* renamed from: b */
    private TaxiInfo m5153b(String str) {
        if (str == null || str.length() == 0) {
            return null;
        }
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
        TaxiInfo taxiInfo = new TaxiInfo();
        taxiInfo.setDesc(jSONObject.optString("remark"));
        taxiInfo.setDistance(jSONObject.optInt("distance"));
        taxiInfo.setDuration(jSONObject.optInt("duration"));
        taxiInfo.setTotalPrice((float) jSONObject.optDouble("total_price"));
        taxiInfo.setStartPrice((float) jSONObject.optDouble("start_price"));
        taxiInfo.setPerKMPrice((float) jSONObject.optDouble("km_price"));
        return taxiInfo;
    }

    /* renamed from: b */
    private boolean m5154b(String str, WalkingRouteResult walkingRouteResult) {
        if (str == null || "".equals(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (walkingRouteResult == null) {
                return false;
            }
            if (jSONObject.has("taxi")) {
                walkingRouteResult.setTaxiInfo(m5153b(jSONObject.optString("taxi")));
            }
            JSONObject optJSONObject = jSONObject.optJSONObject(C0882j.f2229c);
            if (optJSONObject == null) {
                return false;
            }
            switch (optJSONObject.optInt("error")) {
                case 0:
                    jSONObject = jSONObject.optJSONObject("walk");
                    if (jSONObject == null) {
                        return false;
                    }
                    JSONArray optJSONArray = jSONObject.optJSONArray("routes");
                    jSONObject = jSONObject.optJSONObject("option");
                    if (jSONObject == null || optJSONArray == null) {
                        return false;
                    }
                    RouteNode a = m5151a(jSONObject.optJSONObject("start"));
                    RouteNode a2 = m5150a(jSONObject.optJSONArray("end"), null);
                    List arrayList = new ArrayList();
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        optJSONObject = optJSONArray.optJSONObject(i);
                        if (optJSONObject != null) {
                            JSONArray optJSONArray2 = optJSONObject.optJSONArray("legs");
                            if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                    JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i);
                                    if (optJSONObject2 != null) {
                                        WalkingRouteLine walkingRouteLine = new WalkingRouteLine();
                                        walkingRouteLine.setStarting(a);
                                        walkingRouteLine.setTerminal(a2);
                                        walkingRouteLine.setDistance(optJSONObject2.optInt("distance"));
                                        walkingRouteLine.setDuration(optJSONObject2.optInt("duration"));
                                        walkingRouteLine.setSteps(m5152a(optJSONObject2.optJSONArray("steps")));
                                        arrayList.add(walkingRouteLine);
                                    }
                                }
                            }
                        }
                    }
                    walkingRouteResult.setRouteLines(arrayList);
                    return true;
                case 4:
                    walkingRouteResult.error = ERRORNO.ST_EN_TOO_NEAR;
                    return true;
                default:
                    return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: a */
    public void m5155a(String str, WalkingRouteResult walkingRouteResult) {
        if (!m4539a(str, walkingRouteResult, false) && !m5154b(str, walkingRouteResult)) {
            walkingRouteResult.error = ERRORNO.RESULT_NOT_FOUND;
        }
    }
}
