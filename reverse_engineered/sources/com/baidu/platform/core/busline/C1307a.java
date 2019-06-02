package com.baidu.platform.core.busline;

import com.alipay.sdk.util.C0882j;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.search.busline.BusLineResult;
import com.baidu.mapapi.search.busline.BusLineResult.BusStation;
import com.baidu.mapapi.search.busline.BusLineResult.BusStep;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.platform.base.C1212e;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.platform.core.busline.a */
public class C1307a extends C1212e {
    /* renamed from: a */
    public void mo2683a(String str) {
        SearchResult busLineResult = new BusLineResult();
        if (!(m4539a(str, busLineResult, false) || m5021a(str, busLineResult))) {
            busLineResult.error = ERRORNO.RESULT_NOT_FOUND;
        }
        this.a.mo2687a(busLineResult);
    }

    /* renamed from: a */
    public boolean m5021a(String str, BusLineResult busLineResult) {
        int i = 0;
        if (str == null || "".equals(str)) {
            return false;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            JSONObject optJSONObject = jSONObject.optJSONObject(C0882j.f2229c);
            JSONArray optJSONArray = jSONObject.optJSONArray("content");
            if (optJSONObject == null || optJSONArray == null || optJSONArray.length() <= 0) {
                return false;
            }
            optJSONObject = optJSONArray.optJSONObject(0);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
            try {
                busLineResult.setStartTime(simpleDateFormat.parse(optJSONObject.optString("startTime")));
                busLineResult.setEndTime(simpleDateFormat.parse(optJSONObject.optString("endTime")));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            busLineResult.setBusLineName(optJSONObject.optString("name"));
            busLineResult.setMonthTicket(optJSONObject.optInt("isMonTicket") == 1);
            busLineResult.setUid(optJSONObject.optString("uid"));
            busLineResult.setBasePrice(((float) optJSONObject.optInt("ticketPrice")) / 100.0f);
            busLineResult.setLineDirection(optJSONObject.optString("line_direction"));
            busLineResult.setMaxPrice(((float) optJSONObject.optInt("maxPrice")) / 100.0f);
            List arrayList = new ArrayList();
            List<List> decodeLocationList2D = CoordUtil.decodeLocationList2D(optJSONObject.optString("geo"));
            if (decodeLocationList2D != null) {
                for (List list : decodeLocationList2D) {
                    BusStep busStep = new BusStep();
                    busStep.setWayPoints(list);
                    arrayList.add(busStep);
                }
            }
            if (arrayList.size() > 0) {
                busLineResult.setSteps(arrayList);
            }
            optJSONArray = optJSONObject.optJSONArray("stations");
            if (optJSONArray != null) {
                List arrayList2 = new ArrayList();
                while (i < optJSONArray.length()) {
                    JSONObject optJSONObject2 = optJSONArray.optJSONObject(i);
                    if (optJSONObject2 != null) {
                        BusStation busStation = new BusStation();
                        busStation.setTitle(optJSONObject2.optString("name"));
                        busStation.setLocation(CoordUtil.decodeLocation(optJSONObject2.optString("geo")));
                        busStation.setUid(optJSONObject2.optString("uid"));
                        arrayList2.add(busStation);
                    }
                    i++;
                }
                if (arrayList2.size() > 0) {
                    busLineResult.setStations(arrayList2);
                }
            }
            busLineResult.error = ERRORNO.NO_ERROR;
            return true;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return false;
        }
    }
}
