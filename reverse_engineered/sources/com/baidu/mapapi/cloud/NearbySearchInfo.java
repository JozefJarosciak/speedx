package com.baidu.mapapi.cloud;

import com.alipay.sdk.sys.C0869a;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.platform.comapi.util.CoordTrans;
import com.j256.ormlite.stmt.query.SimpleComparison;
import com.mapbox.mapboxsdk.telemetry.MapboxEvent;

public class NearbySearchInfo extends BaseCloudSearchInfo {
    public String location;
    public int radius;

    public NearbySearchInfo() {
        this.a = "http://api.map.baidu.com/geosearch/v2/nearby";
        this.radius = 1000;
    }

    /* renamed from: a */
    String mo2610a() {
        StringBuilder stringBuilder = new StringBuilder();
        if (super.mo2610a() == null) {
            return null;
        }
        stringBuilder.append(super.mo2610a());
        if (this.location == null || this.location.equals("")) {
            return null;
        }
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            String[] split = this.location.split(",");
            try {
                LatLng gcjToBaidu = CoordTrans.gcjToBaidu(new LatLng(Double.parseDouble(split[1]), Double.parseDouble(split[0])));
                this.location = gcjToBaidu.longitude + "," + gcjToBaidu.latitude;
            } catch (Exception e) {
            }
        }
        stringBuilder.append(C0869a.f2161b);
        stringBuilder.append(MapboxEvent.TYPE_LOCATION);
        stringBuilder.append(SimpleComparison.EQUAL_TO_OPERATION);
        stringBuilder.append(this.location);
        if (this.radius >= 0) {
            stringBuilder.append(C0869a.f2161b);
            stringBuilder.append("radius");
            stringBuilder.append(SimpleComparison.EQUAL_TO_OPERATION);
            stringBuilder.append(this.radius);
        }
        return stringBuilder.toString();
    }
}
