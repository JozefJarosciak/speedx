package com.baidu.platform.comapi.util;

import com.baidu.mapapi.model.LatLng;
import com.baidu.platform.comjni.tools.JNITools;

public class CoordTrans {
    public static LatLng baiduToGcj(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        double[] baiduToGcj = JNITools.baiduToGcj(latLng.latitude, latLng.longitude);
        return baiduToGcj != null ? new LatLng(baiduToGcj[0], baiduToGcj[1]) : null;
    }

    public static LatLng gcjToBaidu(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        double[] gcjToBaidu = JNITools.gcjToBaidu(latLng.latitude, latLng.longitude);
        return gcjToBaidu != null ? new LatLng(gcjToBaidu[0], gcjToBaidu[1]) : null;
    }

    public static LatLng wgsToBaidu(LatLng latLng) {
        if (latLng == null) {
            return null;
        }
        double[] wgsToBaidu = JNITools.wgsToBaidu(latLng.latitude, latLng.longitude);
        return wgsToBaidu != null ? new LatLng(wgsToBaidu[0], wgsToBaidu[1]) : null;
    }
}
