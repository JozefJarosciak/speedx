package com.beastbikes.android.ble.dto;

import com.mapbox.services.directions.v5.models.LegStep;
import com.mapbox.services.directions.v5.models.RouteLeg;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NavigationLegDTO */
/* renamed from: com.beastbikes.android.ble.dto.b */
public class C1667b {
    /* renamed from: a */
    private double f7542a;
    /* renamed from: b */
    private double f7543b;
    /* renamed from: c */
    private ArrayList<C1669d> f7544c;

    public C1667b(JSONObject jSONObject) {
        this.f7544c = new ArrayList();
        if (jSONObject != null) {
            this.f7542a = jSONObject.optJSONObject("distance").optDouble("value");
            this.f7543b = jSONObject.optJSONObject("duration").optDouble("value");
            JSONArray optJSONArray = jSONObject.optJSONArray("steps");
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    this.f7544c.add(new C1669d(optJSONArray.optJSONObject(i)));
                }
            }
        }
    }

    public C1667b(RouteLeg routeLeg) {
        if (routeLeg != null) {
            this.f7544c = new ArrayList();
            this.f7542a = routeLeg.getDistance();
            this.f7543b = routeLeg.getDuration();
            List<LegStep> steps = routeLeg.getSteps();
            if (steps != null) {
                for (LegStep c1669d : steps) {
                    this.f7544c.add(new C1669d(c1669d));
                }
            }
        }
    }

    /* renamed from: a */
    public double m9045a() {
        return this.f7542a;
    }

    /* renamed from: b */
    public double m9046b() {
        return this.f7543b;
    }

    /* renamed from: c */
    public ArrayList<C1669d> m9047c() {
        return this.f7544c;
    }
}
