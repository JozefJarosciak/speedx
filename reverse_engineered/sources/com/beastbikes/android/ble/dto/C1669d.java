package com.beastbikes.android.ble.dto;

import com.mapbox.services.commons.models.Position;
import com.mapbox.services.commons.utils.PolylineUtils;
import com.mapbox.services.directions.v5.models.LegStep;
import java.util.List;
import org.json.JSONObject;

/* compiled from: NavigationStepDTO */
/* renamed from: com.beastbikes.android.ble.dto.d */
public class C1669d {
    /* renamed from: a */
    private List<Position> f7548a;
    /* renamed from: b */
    private String f7549b;

    public C1669d(JSONObject jSONObject) {
        if (jSONObject != null) {
            JSONObject optJSONObject = jSONObject.optJSONObject("polyline");
            if (optJSONObject != null) {
                this.f7549b = optJSONObject.optString("points");
                this.f7548a = PolylineUtils.decode(this.f7549b, 5);
            }
        }
    }

    public C1669d(LegStep legStep) {
        if (legStep != null) {
            this.f7549b = legStep.getGeometry();
            this.f7548a = PolylineUtils.decode(this.f7549b, 5);
        }
    }

    /* renamed from: a */
    public List<Position> m9051a() {
        return this.f7548a;
    }
}
