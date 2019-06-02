package com.baidu.mapapi.cloud;

import com.alipay.sdk.sys.C0869a;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.mapbox.services.geocoding.v5.GeocodingCriteria;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class LocalSearchInfo extends BaseCloudSearchInfo {
    public String region;

    public LocalSearchInfo() {
        this.a = "http://api.map.baidu.com/geosearch/v2/local";
    }

    /* renamed from: a */
    String mo2610a() {
        StringBuilder stringBuilder = new StringBuilder();
        if (super.mo2610a() == null) {
            return null;
        }
        stringBuilder.append(super.mo2610a());
        if (this.region == null || this.region.equals("") || this.region.length() > 25) {
            return null;
        }
        stringBuilder.append(C0869a.f2161b);
        stringBuilder.append(GeocodingCriteria.TYPE_REGION);
        stringBuilder.append(SimpleComparison.EQUAL_TO_OPERATION);
        try {
            stringBuilder.append(URLEncoder.encode(this.region, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }
}
