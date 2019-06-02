package com.baidu.platform.core.p049d;

import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.route.IndoorRouteLine;
import com.baidu.mapapi.search.route.IndoorRouteLine.IndoorRouteStep;
import com.baidu.mapapi.search.route.IndoorRouteLine.IndoorRouteStep.IndoorStepNode;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.platform.base.C1212e;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.platform.core.d.f */
public class C1330f extends C1212e {
    /* renamed from: a */
    private LatLng m5105a(JSONObject jSONObject, String str) {
        JSONArray optJSONArray = jSONObject.optJSONArray(str);
        if (optJSONArray == null) {
            return null;
        }
        GeoPoint geoPoint = new GeoPoint(0.0d, 0.0d);
        geoPoint.setLatitudeE6(optJSONArray.optDouble(1));
        geoPoint.setLongitudeE6(optJSONArray.optDouble(0));
        return CoordUtil.mc2ll(geoPoint);
    }

    /* renamed from: a */
    private boolean m5106a(String str, IndoorRouteResult indoorRouteResult) {
        if (str == null || "".equals(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject == null) {
                return false;
            }
            jSONObject = jSONObject.optJSONObject("indoor_navi");
            if (jSONObject == null) {
                return false;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject("option");
            if (optJSONObject == null) {
                return false;
            }
            switch (optJSONObject.optInt("error")) {
                case 0:
                    JSONArray optJSONArray = jSONObject.optJSONArray("routes");
                    if (optJSONArray == null) {
                        return false;
                    }
                    jSONObject = optJSONArray.optJSONObject(0);
                    if (jSONObject == null) {
                        return false;
                    }
                    List arrayList = new ArrayList();
                    JSONArray optJSONArray2 = jSONObject.optJSONArray("legs");
                    if (optJSONArray2 == null) {
                        return false;
                    }
                    for (int i = 0; i < optJSONArray2.length(); i++) {
                        IndoorRouteLine indoorRouteLine = new IndoorRouteLine();
                        optJSONObject = optJSONArray2.optJSONObject(i);
                        if (optJSONObject != null) {
                            indoorRouteLine.setDistance(optJSONObject.optInt("distance"));
                            indoorRouteLine.setDuration(optJSONObject.optInt("duration"));
                            indoorRouteLine.setStarting(RouteNode.location(m5105a(optJSONObject, "sstart_location")));
                            indoorRouteLine.setTerminal(RouteNode.location(m5105a(optJSONObject, "send_location")));
                            JSONArray optJSONArray3 = optJSONObject.optJSONArray("steps");
                            if (optJSONArray3 != null) {
                                List arrayList2 = new ArrayList();
                                for (int i2 = 0; i2 < optJSONArray3.length(); i2++) {
                                    IndoorRouteStep indoorRouteStep = new IndoorRouteStep();
                                    JSONObject optJSONObject2 = optJSONArray3.optJSONObject(i2);
                                    if (optJSONObject2 != null) {
                                        indoorRouteStep.setDistance(optJSONObject2.optInt("distance"));
                                        indoorRouteStep.setDuration(optJSONObject2.optInt("duration"));
                                        indoorRouteStep.setBuildingId(optJSONObject2.optString("buildingid"));
                                        indoorRouteStep.setFloorId(optJSONObject2.optString("floorid"));
                                        IndoorRouteStep indoorRouteStep2 = indoorRouteStep;
                                        indoorRouteStep2.setEntrace(RouteNode.location(m5105a(optJSONObject2, "sstart_location")));
                                        indoorRouteStep2 = indoorRouteStep;
                                        indoorRouteStep2.setExit(RouteNode.location(m5105a(optJSONObject2, "send_location")));
                                        JSONArray optJSONArray4 = optJSONObject2.optJSONArray("spath");
                                        if (optJSONArray4 != null) {
                                            int i3;
                                            List arrayList3 = new ArrayList();
                                            double d = 0.0d;
                                            double d2 = 0.0d;
                                            for (i3 = 5; i3 < optJSONArray4.length(); i3 += 2) {
                                                d += optJSONArray4.optDouble(i3 + 1);
                                                d2 += optJSONArray4.optDouble(i3);
                                                GeoPoint geoPoint = new GeoPoint(0.0d, 0.0d);
                                                geoPoint.setLatitudeE6(d);
                                                geoPoint.setLongitudeE6(d2);
                                                LatLng mc2ll = CoordUtil.mc2ll(geoPoint);
                                                arrayList3.add(Double.valueOf(mc2ll.latitude));
                                                arrayList3.add(Double.valueOf(mc2ll.longitude));
                                            }
                                            indoorRouteStep.setPath(arrayList3);
                                            indoorRouteStep.setInstructions(optJSONObject2.optString("instructions"));
                                            JSONArray optJSONArray5 = optJSONObject2.optJSONArray("pois");
                                            if (optJSONArray5 != null) {
                                                List arrayList4 = new ArrayList();
                                                for (i3 = 0; i3 < optJSONArray5.length(); i3++) {
                                                    JSONObject optJSONObject3 = optJSONArray5.optJSONObject(i3);
                                                    if (optJSONObject3 != null) {
                                                        IndoorStepNode indoorStepNode = new IndoorStepNode();
                                                        indoorStepNode.setDetail(optJSONObject3.optString("detail"));
                                                        indoorStepNode.setName(optJSONObject3.optString("name"));
                                                        indoorStepNode.setType(optJSONObject3.optInt("type"));
                                                        indoorStepNode.setLocation(m5105a(optJSONObject3, MapboxEvent.TYPE_LOCATION));
                                                        arrayList4.add(indoorStepNode);
                                                    }
                                                }
                                                indoorRouteStep.setStepNodes(arrayList4);
                                            }
                                            arrayList2.add(indoorRouteStep);
                                        }
                                    }
                                }
                                if (arrayList2.size() > 0) {
                                    indoorRouteLine.setSteps(arrayList2);
                                }
                            }
                            arrayList.add(indoorRouteLine);
                        }
                    }
                    indoorRouteResult.setRouteLines(arrayList);
                    return true;
                case 6:
                    indoorRouteResult.error = ERRORNO.INDOOR_ROUTE_NO_IN_BUILDING;
                    return true;
                case 7:
                    indoorRouteResult.error = ERRORNO.INDOOR_ROUTE_NO_IN_SAME_BUILDING;
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
    public void mo2683a(String str) {
        IndoorRouteResult indoorRouteResult = new IndoorRouteResult();
        if (!m5106a(str, indoorRouteResult)) {
            indoorRouteResult.error = ERRORNO.RESULT_NOT_FOUND;
        }
        this.a.mo2687a(indoorRouteResult);
    }
}
