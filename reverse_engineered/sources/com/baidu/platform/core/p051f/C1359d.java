package com.baidu.platform.core.p051f;

import com.alipay.sdk.util.C0882j;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionResult.SuggestionInfo;
import com.baidu.platform.base.C1212e;
import com.baidu.platform.comapi.util.CoordTrans;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

/* renamed from: com.baidu.platform.core.f.d */
public class C1359d extends C1212e {
    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    /* renamed from: a */
    public static boolean m5192a(java.lang.String r3, com.baidu.mapapi.search.sug.SuggestionResult r4) {
        /*
        r2 = 0;
        r0 = new org.json.JSONObject;	 Catch:{ JSONException -> 0x0019 }
        r0.<init>(r3);	 Catch:{ JSONException -> 0x0019 }
        if (r0 != 0) goto L_0x0009;
    L_0x0008:
        return r2;
    L_0x0009:
        r1 = "status";
        r1 = r0.optInt(r1);	 Catch:{ JSONException -> 0x0019 }
        if (r1 == 0) goto L_0x002c;
    L_0x0011:
        switch(r1) {
            case 1: goto L_0x0022;
            case 2: goto L_0x0027;
            default: goto L_0x0014;
        };	 Catch:{ JSONException -> 0x0019 }
    L_0x0014:
        r0 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.RESULT_NOT_FOUND;	 Catch:{ JSONException -> 0x0019 }
        r4.error = r0;	 Catch:{ JSONException -> 0x0019 }
        goto L_0x0008;
    L_0x0019:
        r0 = move-exception;
        r0.printStackTrace();
        r0 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.RESULT_NOT_FOUND;
        r4.error = r0;
        goto L_0x0008;
    L_0x0022:
        r0 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.SEARCH_SERVER_INTERNAL_ERROR;	 Catch:{ JSONException -> 0x0019 }
        r4.error = r0;	 Catch:{ JSONException -> 0x0019 }
        goto L_0x0008;
    L_0x0027:
        r0 = com.baidu.mapapi.search.core.SearchResult.ERRORNO.SEARCH_OPTION_ERROR;	 Catch:{ JSONException -> 0x0019 }
        r4.error = r0;	 Catch:{ JSONException -> 0x0019 }
        goto L_0x0008;
    L_0x002c:
        com.baidu.platform.core.p051f.C1359d.m5193a(r0, r4);	 Catch:{ JSONException -> 0x0019 }
        goto L_0x0008;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.baidu.platform.core.f.d.a(java.lang.String, com.baidu.mapapi.search.sug.SuggestionResult):boolean");
    }

    /* renamed from: a */
    private static boolean m5193a(JSONObject jSONObject, SuggestionResult suggestionResult) {
        if (jSONObject == null) {
            return false;
        }
        suggestionResult.error = ERRORNO.NO_ERROR;
        JSONArray optJSONArray = jSONObject.optJSONArray(C0882j.f2229c);
        if (optJSONArray == null || optJSONArray.length() == 0) {
            suggestionResult.error = ERRORNO.RESULT_NOT_FOUND;
            return true;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < optJSONArray.length(); i++) {
            JSONObject jSONObject2 = (JSONObject) optJSONArray.opt(i);
            if (jSONObject2 != null) {
                SuggestionInfo suggestionInfo = new SuggestionInfo();
                String optString = jSONObject2.optString("name");
                if (optString != null) {
                    suggestionInfo.key = optString;
                }
                optString = jSONObject2.optString("city");
                if (optString != null) {
                    suggestionInfo.city = optString;
                }
                optString = jSONObject2.optString("district");
                if (optString != null) {
                    suggestionInfo.district = optString;
                }
                optString = jSONObject2.optString("uid");
                if (optString != null) {
                    suggestionInfo.uid = optString;
                }
                JSONObject optJSONObject = jSONObject2.optJSONObject(MapboxEvent.TYPE_LOCATION);
                if (optJSONObject != null) {
                    LatLng latLng = new LatLng(optJSONObject.optDouble(MapboxEvent.KEY_LATITUDE), optJSONObject.optDouble(MapboxEvent.KEY_LONGITUDE));
                    if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
                        latLng = CoordTrans.baiduToGcj(latLng);
                    }
                    suggestionInfo.pt = latLng;
                }
                arrayList.add(suggestionInfo);
            }
        }
        suggestionResult.setSuggestionInfo(arrayList);
        return true;
    }

    /* renamed from: a */
    public void mo2683a(String str) {
        SuggestionResult suggestionResult = new SuggestionResult();
        if (!m4539a(str, suggestionResult, true)) {
            C1359d.m5192a(str, suggestionResult);
        }
        this.a.mo2687a(suggestionResult);
    }
}
