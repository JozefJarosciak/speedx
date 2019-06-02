package com.baidu.mapapi.cloud;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.model.LatLng;
import com.baidu.platform.comapi.util.CoordTrans;

public class CloudRgcInfo {
    public int geoTableId;
    public String location;

    /* renamed from: a */
    String m4032a() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("http://api.map.baidu.com/sdkproxy/lbs_androidsdk/cloudrgc/v1");
        stringBuilder.append('?');
        if (this.location == null || this.location.equals("")) {
            return null;
        }
        if (SDKInitializer.getCoordType() == CoordType.GCJ02) {
            String[] split = this.location.split(",");
            try {
                LatLng gcjToBaidu = CoordTrans.gcjToBaidu(new LatLng(Double.parseDouble(split[0]), Double.parseDouble(split[1])));
                this.location = gcjToBaidu.latitude + "," + gcjToBaidu.longitude;
            } catch (Exception e) {
            }
        }
        stringBuilder.append("location=" + this.location);
        if (this.geoTableId == 0) {
            return null;
        }
        stringBuilder.append("&geotable_id=" + this.geoTableId);
        stringBuilder.append("&coord_type=bd09ll&extensions=pois&from=android_map_sdk");
        return stringBuilder.toString();
    }
}
