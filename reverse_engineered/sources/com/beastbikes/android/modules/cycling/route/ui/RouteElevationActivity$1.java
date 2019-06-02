package com.beastbikes.android.modules.cycling.route.ui;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout.LayoutParams;
import com.android.volley.Response.Listener;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.route.p133b.C2186a;
import com.beastbikes.framework.ui.android.utils.Toasts;
import org.json.JSONArray;
import org.json.JSONObject;

class RouteElevationActivity$1 implements Listener<JSONObject> {
    /* renamed from: a */
    final /* synthetic */ Context f10295a;
    /* renamed from: b */
    final /* synthetic */ RouteElevationActivity f10296b;

    RouteElevationActivity$1(RouteElevationActivity routeElevationActivity, Context context) {
        this.f10296b = routeElevationActivity;
        this.f10295a = context;
    }

    public /* synthetic */ void onResponse(Object obj) {
        m11245a((JSONObject) obj);
    }

    /* renamed from: a */
    public void m11245a(JSONObject jSONObject) {
        if ("OK".equals(jSONObject.optString("status"))) {
            JSONArray optJSONArray = jSONObject.optJSONArray("results");
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    RouteElevationActivity.a(this.f10296b).add(Double.valueOf(((JSONObject) optJSONArray.get(i)).optDouble("elevation")));
                } catch (Throwable e) {
                    RouteElevationActivity.a().error("get elevation error", e);
                }
            }
            double min = Math.min(0.0d, RouteElevationActivity.a(this.f10296b, RouteElevationActivity.a(this.f10296b)));
            double b = RouteElevationActivity.b(this.f10296b, RouteElevationActivity.a(this.f10296b));
            View c2186a = new C2186a(this.f10296b);
            c2186a.setLabelsData(RouteElevationActivity.a(this.f10296b).size());
            c2186a.setData(RouteElevationActivity.a(this.f10296b));
            c2186a.m11209a(b, min, RouteElevationActivity.a(this.f10296b).size());
            RouteElevationActivity.b(this.f10296b).addView(c2186a, new LayoutParams(-1, -1));
            if (RouteElevationActivity.c(this.f10296b) != null && RouteElevationActivity.c(this.f10296b).isShowing()) {
                RouteElevationActivity.c(this.f10296b).dismiss();
            }
            RouteElevationActivity.a().info("the min elevation is " + String.valueOf(min) + " and the max is : " + String.valueOf(b));
            return;
        }
        if (RouteElevationActivity.c(this.f10296b) != null && RouteElevationActivity.c(this.f10296b).isShowing()) {
            RouteElevationActivity.c(this.f10296b).dismiss();
        }
        Toasts.show(this.f10295a, this.f10296b.getString(C1373R.string.route_elevation_activity_error));
    }
}
