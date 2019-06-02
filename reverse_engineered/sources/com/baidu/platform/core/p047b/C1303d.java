package com.baidu.platform.core.p047b;

import com.alipay.sdk.util.C0882j;
import com.baidu.mapapi.model.CoordUtil;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.core.SearchResult.ERRORNO;
import com.baidu.mapapi.search.geocode.GeoCodeResult;
import com.baidu.platform.base.C1212e;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.platform.core.b.d */
public class C1303d extends C1212e {
    /* renamed from: a */
    private boolean m5004a(String str, GeoCodeResult geoCodeResult) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            if (jSONObject == null) {
                return false;
            }
            JSONObject optJSONObject = jSONObject.optJSONObject(C0882j.f2229c);
            if (optJSONObject == null || optJSONObject.optInt("error") != 0) {
                return false;
            }
            jSONObject = jSONObject.optJSONObject("content");
            if (jSONObject == null) {
                return false;
            }
            optJSONObject = jSONObject.optJSONObject("coord");
            if (optJSONObject == null) {
                return false;
            }
            geoCodeResult.setLocation(CoordUtil.mc2ll(new GeoPoint((double) optJSONObject.optInt("y"), (double) optJSONObject.optInt("x"))));
            geoCodeResult.setAddress(jSONObject.optString("wd"));
            geoCodeResult.error = ERRORNO.NO_ERROR;
            return true;
        } catch (JSONException e) {
            geoCodeResult.error = ERRORNO.RESULT_NOT_FOUND;
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void mo2683a(String str) {
        SearchResult geoCodeResult = new GeoCodeResult();
        if (!(m4539a(str, geoCodeResult, false) || m5004a(str, geoCodeResult))) {
            geoCodeResult.error = ERRORNO.RESULT_NOT_FOUND;
        }
        this.a.mo2687a(geoCodeResult);
    }
}
