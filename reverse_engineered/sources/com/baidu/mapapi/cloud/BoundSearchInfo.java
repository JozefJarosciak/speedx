package com.baidu.mapapi.cloud;

import com.alipay.sdk.sys.C0869a;
import com.alipay.sdk.util.C0880h;
import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.platform.comapi.util.CoordTrans;
import com.j256.ormlite.stmt.query.SimpleComparison;

public class BoundSearchInfo extends BaseCloudSearchInfo {
    public String bound;

    public BoundSearchInfo() {
        this.a = "http://api.map.baidu.com/geosearch/v2/bound";
    }

    /* renamed from: a */
    String mo2610a() {
        StringBuilder stringBuilder = new StringBuilder();
        if (super.mo2610a() == null) {
            return null;
        }
        stringBuilder.append(super.mo2610a());
        if (this.bound == null || this.bound.equals("")) {
            return null;
        }
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            try {
                String[] split = this.bound.split(C0880h.f2220b);
                String[] split2 = split[0].split(",");
                split = split[1].split(",");
                LatLng latLng = new LatLng(Double.parseDouble(split2[1]), Double.parseDouble(split2[0]));
                LatLng latLng2 = new LatLng(Double.parseDouble(split[1]), Double.parseDouble(split[0]));
                LatLng gcjToBaidu = CoordTrans.gcjToBaidu(latLng);
                latLng2 = CoordTrans.gcjToBaidu(latLng2);
                this.bound = gcjToBaidu.longitude + "," + gcjToBaidu.latitude + C0880h.f2220b + latLng2.longitude + "," + latLng2.latitude;
            } catch (Exception e) {
            }
        }
        stringBuilder.append(C0869a.f2161b);
        stringBuilder.append("bounds");
        stringBuilder.append(SimpleComparison.EQUAL_TO_OPERATION);
        stringBuilder.append(this.bound);
        return stringBuilder.toString();
    }
}
