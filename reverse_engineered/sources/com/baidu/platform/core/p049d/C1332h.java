package com.baidu.platform.core.p049d;

import com.alipay.sdk.util.C0882j;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.BusInfo;
import com.baidu.mapapi.search.core.CoachInfo;
import com.baidu.mapapi.search.core.PlaneInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.PriceInfo;
import com.baidu.mapapi.search.core.RouteNode;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.core.TaxiInfo;
import com.baidu.mapapi.search.core.TrainInfo;
import com.baidu.mapapi.search.core.TransitResultNode;
import com.baidu.mapapi.search.route.MassTransitRouteLine;
import com.baidu.mapapi.search.route.MassTransitRouteLine.TransitStep;
import com.baidu.mapapi.search.route.MassTransitRouteLine.TransitStep.StepVehicleInfoType;
import com.baidu.mapapi.search.route.MassTransitRouteLine.TransitStep.TrafficCondition;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
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

/* renamed from: com.baidu.platform.core.d.h */
public class C1332h extends C1212e {
    /* renamed from: a */
    private TransitResultNode m5110a(int i, JSONObject jSONObject) {
        LatLng latLng = null;
        if (jSONObject == null) {
            return null;
        }
        String optString = jSONObject.optString("wd");
        String optString2 = jSONObject.optString("city_name");
        int optInt = i == 1 ? jSONObject.optInt("city_code") : jSONObject.optInt("city_id");
        JSONObject optJSONObject = jSONObject.optJSONObject(MapboxEvent.TYPE_LOCATION);
        if (optJSONObject != null) {
            latLng = new LatLng(optJSONObject.optDouble(MapboxEvent.KEY_LATITUDE), optJSONObject.optDouble(MapboxEvent.KEY_LONGITUDE));
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                latLng = CoordTrans.baiduToGcj(latLng);
            }
        }
        return new TransitResultNode(optInt, optString2, latLng, optString);
    }

    /* renamed from: a */
    private TransitStep m5111a(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        LatLng latLng;
        TransitStep transitStep = new TransitStep();
        transitStep.setDistance((int) jSONObject.optDouble("distance"));
        transitStep.setDuration((int) jSONObject.optDouble("duration"));
        transitStep.setInstructions(jSONObject.optString("instructions"));
        transitStep.setPathString(jSONObject.optString("path"));
        transitStep.setTrafficConditions(m5115b(jSONObject.optJSONArray("traffic_condition")));
        JSONObject optJSONObject = jSONObject.optJSONObject("start_location");
        if (optJSONObject != null) {
            latLng = new LatLng(optJSONObject.optDouble(MapboxEvent.KEY_LATITUDE), optJSONObject.optDouble(MapboxEvent.KEY_LONGITUDE));
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                latLng = CoordTrans.baiduToGcj(latLng);
            }
            transitStep.setStartLocation(latLng);
        }
        optJSONObject = jSONObject.optJSONObject("end_location");
        if (optJSONObject != null) {
            latLng = new LatLng(optJSONObject.optDouble(MapboxEvent.KEY_LATITUDE), optJSONObject.optDouble(MapboxEvent.KEY_LONGITUDE));
            if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                latLng = CoordTrans.baiduToGcj(latLng);
            }
            transitStep.setEndLocation(latLng);
        }
        JSONObject optJSONObject2 = jSONObject.optJSONObject("vehicle_info");
        if (optJSONObject2 != null) {
            int optInt = optJSONObject2.optInt("type");
            optJSONObject2 = optJSONObject2.optJSONObject("detail");
            switch (optInt) {
                case 1:
                    transitStep.setVehileType(StepVehicleInfoType.ESTEP_TRAIN);
                    if (optJSONObject2 != null) {
                        TrainInfo trainInfo = new TrainInfo();
                        trainInfo.setName(optJSONObject2.optString("name"));
                        trainInfo.m4371a(optJSONObject2.optDouble("price"));
                        trainInfo.m4372a(optJSONObject2.optString("booking"));
                        trainInfo.setDepartureStation(optJSONObject2.optString("departure_station"));
                        trainInfo.setArriveStation(optJSONObject2.optString("arrive_station"));
                        trainInfo.setDepartureTime(optJSONObject2.optString("departure_time"));
                        trainInfo.setArriveTime(optJSONObject2.optString("arrive_time"));
                        transitStep.setTrainInfo(trainInfo);
                        break;
                    }
                    break;
                case 2:
                    transitStep.setVehileType(StepVehicleInfoType.ESTEP_PLANE);
                    if (optJSONObject2 != null) {
                        PlaneInfo planeInfo = new PlaneInfo();
                        planeInfo.setName(optJSONObject2.optString("name"));
                        planeInfo.setPrice(optJSONObject2.optDouble("price"));
                        planeInfo.setDiscount(optJSONObject2.optDouble("discount"));
                        planeInfo.setAirlines(optJSONObject2.optString("airlines"));
                        planeInfo.setBooking(optJSONObject2.optString("booking"));
                        planeInfo.setDepartureStation(optJSONObject2.optString("departure_station"));
                        planeInfo.setArriveStation(optJSONObject2.optString("arrive_station"));
                        planeInfo.setDepartureTime(optJSONObject2.optString("departure_time"));
                        planeInfo.setArriveTime(optJSONObject2.optString("arrive_time"));
                        transitStep.setPlaneInfo(planeInfo);
                        break;
                    }
                    break;
                case 3:
                    transitStep.setVehileType(StepVehicleInfoType.ESTEP_BUS);
                    if (optJSONObject2 != null) {
                        BusInfo busInfo = new BusInfo();
                        busInfo.setName(optJSONObject2.optString("name"));
                        busInfo.setType(optJSONObject2.optInt("type"));
                        busInfo.setStopNum(optJSONObject2.optInt("stop_num"));
                        busInfo.setDepartureStation(optJSONObject2.optString("on_station"));
                        busInfo.setArriveStation(optJSONObject2.optString("off_station"));
                        busInfo.setDepartureTime(optJSONObject2.optString("first_time"));
                        busInfo.setArriveTime(optJSONObject2.optString("last_time"));
                        transitStep.setBusInfo(busInfo);
                        break;
                    }
                    break;
                case 4:
                    transitStep.setVehileType(StepVehicleInfoType.ESTEP_DRIVING);
                    break;
                case 5:
                    transitStep.setVehileType(StepVehicleInfoType.ESTEP_WALK);
                    break;
                case 6:
                    transitStep.setVehileType(StepVehicleInfoType.ESTEP_COACH);
                    if (optJSONObject2 != null) {
                        CoachInfo coachInfo = new CoachInfo();
                        coachInfo.setName(optJSONObject2.optString("name"));
                        coachInfo.setPrice(optJSONObject2.optDouble("price"));
                        coachInfo.setBooking(optJSONObject2.optString("booking"));
                        coachInfo.setProviderName(optJSONObject2.optString("provider_name"));
                        coachInfo.setProviderUrl(optJSONObject2.optString("provider_url"));
                        coachInfo.setDepartureStation(optJSONObject2.optString("departure_station"));
                        coachInfo.setArriveStation(optJSONObject2.optString("arrive_station"));
                        coachInfo.setDepartureTime(optJSONObject2.optString("departure_time"));
                        coachInfo.setArriveTime(optJSONObject2.optString("arrive_time"));
                        transitStep.setCoachInfo(coachInfo);
                        break;
                    }
                    break;
            }
        }
        return transitStep;
    }

    /* renamed from: a */
    private List<List<TransitStep>> m5112a(JSONArray jSONArray) {
        List<List<TransitStep>> arrayList = new ArrayList();
        if (jSONArray == null || jSONArray.length() < 0) {
            return null;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONArray optJSONArray = jSONArray.optJSONArray(i);
            if (optJSONArray != null && optJSONArray.length() > 0) {
                List arrayList2 = new ArrayList();
                for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i2);
                    if (optJSONObject != null) {
                        arrayList2.add(m5111a(optJSONObject));
                    }
                }
                arrayList.add(arrayList2);
            }
        }
        return arrayList;
    }

    /* renamed from: b */
    private TaxiInfo m5113b(String str) {
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
    private SuggestAddrInfo m5114b(JSONObject jSONObject) {
        SuggestAddrInfo suggestAddrInfo = new SuggestAddrInfo();
        suggestAddrInfo.setSuggestStartNode(m5117d(jSONObject.optJSONArray("origin_list")));
        suggestAddrInfo.setSuggestEndNode(m5117d(jSONObject.optJSONArray("destination_list")));
        return suggestAddrInfo;
    }

    /* renamed from: b */
    private List<TrafficCondition> m5115b(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() < 0) {
            return null;
        }
        List<TrafficCondition> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                TrafficCondition trafficCondition = new TrafficCondition();
                trafficCondition.setTrafficStatus(optJSONObject.optInt("status"));
                trafficCondition.setTrafficGeoCnt(optJSONObject.optInt("geo_cnt"));
                arrayList.add(trafficCondition);
            }
        }
        return arrayList;
    }

    /* renamed from: c */
    private List<PriceInfo> m5116c(JSONArray jSONArray) {
        if (jSONArray == null) {
            return null;
        }
        List<PriceInfo> arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            PriceInfo priceInfo = new PriceInfo();
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            if (optJSONObject != null) {
                priceInfo.setTicketType(optJSONObject.optInt("ticket_type"));
                priceInfo.setTicketPrice(optJSONObject.optDouble("ticket_price"));
            }
            arrayList.add(priceInfo);
        }
        return arrayList;
    }

    /* renamed from: d */
    private List<PoiInfo> m5117d(JSONArray jSONArray) {
        if (jSONArray != null) {
            List<PoiInfo> arrayList = new ArrayList();
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject jSONObject = (JSONObject) jSONArray.opt(i);
                if (jSONObject != null) {
                    PoiInfo poiInfo = new PoiInfo();
                    poiInfo.address = jSONObject.optString(GeocodingCriteria.TYPE_ADDRESS);
                    poiInfo.uid = jSONObject.optString("uid");
                    poiInfo.name = jSONObject.optString("name");
                    jSONObject = jSONObject.optJSONObject(MapboxEvent.TYPE_LOCATION);
                    if (jSONObject != null) {
                        poiInfo.location = new LatLng(jSONObject.optDouble(MapboxEvent.KEY_LATITUDE), jSONObject.optDouble(MapboxEvent.KEY_LONGITUDE));
                        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                            poiInfo.location = CoordTrans.baiduToGcj(poiInfo.location);
                        }
                    }
                    arrayList.add(poiInfo);
                }
            }
            if (!arrayList.isEmpty()) {
                return arrayList;
            }
        }
        return null;
    }

    /* renamed from: a */
    public void mo2683a(String str) {
        MassTransitRouteResult massTransitRouteResult = new MassTransitRouteResult();
        if (!(m4539a(str, massTransitRouteResult, false) || m5119a(str, massTransitRouteResult))) {
            massTransitRouteResult.error = ERRORNO.RESULT_NOT_FOUND;
        }
        this.a.mo2687a(massTransitRouteResult);
    }

    /* renamed from: a */
    public boolean m5119a(String str, MassTransitRouteResult massTransitRouteResult) {
        int i = 0;
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
                    int optInt = jSONObject.optInt("type");
                    jSONObject = jSONObject.optJSONObject(C0882j.f2229c);
                    if (jSONObject == null) {
                        return false;
                    }
                    if (optInt == 1) {
                        massTransitRouteResult.setOrigin(m5110a(optInt, jSONObject.optJSONObject("origin_info")));
                        massTransitRouteResult.setDestination(m5110a(optInt, jSONObject.optJSONObject("destination_info")));
                        massTransitRouteResult.setSuggestAddrInfo(m5114b(jSONObject));
                        massTransitRouteResult.error = ERRORNO.AMBIGUOUS_ROURE_ADDR;
                    } else if (optInt == 2) {
                        TransitResultNode a = m5110a(optInt, jSONObject.optJSONObject("origin"));
                        massTransitRouteResult.setOrigin(a);
                        TransitResultNode a2 = m5110a(optInt, jSONObject.optJSONObject("destination"));
                        massTransitRouteResult.setDestination(a2);
                        massTransitRouteResult.setTotal(jSONObject.optInt("total"));
                        massTransitRouteResult.setTaxiInfo(m5113b(jSONObject.optString("taxi")));
                        JSONArray optJSONArray = jSONObject.optJSONArray("routes");
                        if (optJSONArray == null || optJSONArray.length() <= 0) {
                            return false;
                        }
                        List arrayList = new ArrayList();
                        while (i < optJSONArray.length()) {
                            JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                            if (optJSONObject != null) {
                                RouteNode routeNode;
                                MassTransitRouteLine massTransitRouteLine = new MassTransitRouteLine();
                                massTransitRouteLine.setDistance(optJSONObject.optInt("distance"));
                                massTransitRouteLine.setDuration(optJSONObject.optInt("duration"));
                                massTransitRouteLine.setArriveTime(optJSONObject.optString("arrive_time"));
                                massTransitRouteLine.setPrice(optJSONObject.optDouble("price"));
                                massTransitRouteLine.setPriceInfo(m5116c(optJSONObject.optJSONArray("price_detail")));
                                if (a != null) {
                                    routeNode = new RouteNode();
                                    routeNode.setLocation(a.getLocation());
                                    massTransitRouteLine.setStarting(routeNode);
                                }
                                if (a2 != null) {
                                    routeNode = new RouteNode();
                                    routeNode.setLocation(a2.getLocation());
                                    massTransitRouteLine.setTerminal(routeNode);
                                }
                                JSONArray optJSONArray2 = optJSONObject.optJSONArray("steps");
                                if (optJSONArray2 != null && optJSONArray2.length() > 0) {
                                    massTransitRouteLine.setNewSteps(m5112a(optJSONArray2));
                                    arrayList.add(massTransitRouteLine);
                                }
                            }
                            i++;
                        }
                        massTransitRouteResult.setRoutelines(arrayList);
                        massTransitRouteResult.error = ERRORNO.NO_ERROR;
                    }
                    return true;
                case 1:
                    massTransitRouteResult.error = ERRORNO.MASS_TRANSIT_SERVER_ERROR;
                    return true;
                case 2:
                    massTransitRouteResult.error = ERRORNO.MASS_TRANSIT_OPTION_ERROR;
                    return true;
                case 1002:
                    massTransitRouteResult.error = ERRORNO.MASS_TRANSIT_NO_POI_ERROR;
                    return true;
                default:
                    return false;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
    }
}
