package com.beastbikes.android.ble.dto;

import com.mapbox.mapboxsdk.geometry.LatLng;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: SavedOfflinePoint */
/* renamed from: com.beastbikes.android.ble.dto.e */
public class C1670e {
    /* renamed from: a */
    private LatLng f7550a;
    /* renamed from: b */
    private LatLng f7551b;
    /* renamed from: c */
    private LatLng f7552c;
    /* renamed from: d */
    private LatLng f7553d;

    public C1670e(LatLng latLng, LatLng latLng2, LatLng latLng3, LatLng latLng4) {
        this.f7550a = latLng;
        this.f7551b = latLng2;
        this.f7552c = latLng3;
        this.f7553d = latLng4;
    }

    public C1670e(JSONObject jSONObject) {
        if (jSONObject != null) {
            String[] split = jSONObject.optString("northWest").split(",");
            this.f7550a = new LatLng(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
            split = jSONObject.optString("northEast").split(",");
            this.f7551b = new LatLng(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
            split = jSONObject.optString("southWest").split(",");
            this.f7552c = new LatLng(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
            split = jSONObject.optString("southEast").split(",");
            this.f7553d = new LatLng(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
        }
    }

    /* renamed from: a */
    public LatLng m9052a() {
        return this.f7550a;
    }

    /* renamed from: b */
    public LatLng m9053b() {
        return this.f7551b;
    }

    /* renamed from: c */
    public LatLng m9054c() {
        return this.f7552c;
    }

    /* renamed from: d */
    public LatLng m9055d() {
        return this.f7553d;
    }

    /* renamed from: e */
    public JSONObject m9056e() throws JSONException {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("northWest", this.f7550a.getLatitude() + "," + this.f7550a.getLongitude());
        jSONObject.put("northEast", this.f7551b.getLatitude() + "," + this.f7551b.getLongitude());
        jSONObject.put("southWest", this.f7552c.getLatitude() + "," + this.f7552c.getLongitude());
        jSONObject.put("southEast", this.f7553d.getLatitude() + "," + this.f7553d.getLongitude());
        return jSONObject;
    }
}
