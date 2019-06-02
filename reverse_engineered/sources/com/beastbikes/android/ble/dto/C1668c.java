package com.beastbikes.android.ble.dto;

import com.mapbox.services.directions.v5.models.DirectionsRoute;
import com.mapbox.services.directions.v5.models.RouteLeg;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/* compiled from: NavigationRouteDTO */
/* renamed from: com.beastbikes.android.ble.dto.c */
public class C1668c {
    /* renamed from: a */
    private String f7545a;
    /* renamed from: b */
    private String f7546b;
    /* renamed from: c */
    private ArrayList<C1667b> f7547c;

    public C1668c(String str, String str2, JSONObject jSONObject) {
        this.f7545a = str;
        this.f7546b = str2;
        this.f7547c = new ArrayList();
        if (jSONObject != null) {
            JSONArray optJSONArray = jSONObject.optJSONArray("legs");
            if (optJSONArray != null) {
                int length = optJSONArray.length();
                for (int i = 0; i < length; i++) {
                    this.f7547c.add(new C1667b(optJSONArray.optJSONObject(i)));
                }
            }
        }
    }

    public C1668c(DirectionsRoute directionsRoute) {
        if (directionsRoute != null) {
            this.f7547c = new ArrayList();
            List<RouteLeg> legs = directionsRoute.getLegs();
            if (legs != null) {
                for (RouteLeg c1667b : legs) {
                    this.f7547c.add(new C1667b(c1667b));
                }
            }
        }
    }

    /* renamed from: a */
    public String m9048a() {
        return this.f7545a;
    }

    /* renamed from: b */
    public String m9049b() {
        return this.f7546b;
    }

    /* renamed from: c */
    public ArrayList<C1667b> m9050c() {
        return this.f7547c;
    }
}
