package com.baidu.platform.comapi.map;

import android.graphics.Point;
import com.baidu.mapapi.model.inner.GeoPoint;
import com.baidu.platform.comjni.map.basemap.C1283a;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.baidu.platform.comapi.map.I */
public class C1240I {
    /* renamed from: a */
    private C1283a f3704a;

    public C1240I(C1283a c1283a) {
        this.f3704a = c1283a;
    }

    /* renamed from: a */
    public Point m4629a(GeoPoint geoPoint) {
        if (geoPoint == null) {
            return null;
        }
        Point point = new Point(0, 0);
        String b = this.f3704a.m4893b((int) geoPoint.getLongitudeE6(), (int) geoPoint.getLatitudeE6());
        if (b == null) {
            return point;
        }
        try {
            JSONObject jSONObject = new JSONObject(b);
            point.x = jSONObject.getInt("scrx");
            point.y = jSONObject.getInt("scry");
            return point;
        } catch (JSONException e) {
            e.printStackTrace();
            return point;
        }
    }

    /* renamed from: a */
    public GeoPoint m4630a(int i, int i2) {
        String a = this.f3704a.m4877a(i, i2);
        GeoPoint geoPoint = new GeoPoint(0.0d, 0.0d);
        if (a != null) {
            try {
                JSONObject jSONObject = new JSONObject(a);
                geoPoint.setLongitudeE6((double) jSONObject.getInt("geox"));
                geoPoint.setLatitudeE6((double) jSONObject.getInt("geoy"));
                return geoPoint;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
