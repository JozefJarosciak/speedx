package com.beastbikes.android.modules.cycling.route.ui;

import android.content.Context;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.beastbikes.android.C1373R;
import com.beastbikes.framework.ui.android.utils.Toasts;

class RouteElevationActivity$2 implements ErrorListener {
    /* renamed from: a */
    final /* synthetic */ Context f10297a;
    /* renamed from: b */
    final /* synthetic */ RouteElevationActivity f10298b;

    RouteElevationActivity$2(RouteElevationActivity routeElevationActivity, Context context) {
        this.f10298b = routeElevationActivity;
        this.f10297a = context;
    }

    public void onErrorResponse(VolleyError volleyError) {
        if (RouteElevationActivity.c(this.f10298b) != null && RouteElevationActivity.c(this.f10298b).isShowing()) {
            RouteElevationActivity.c(this.f10298b).dismiss();
        }
        Toasts.show(this.f10297a, this.f10298b.getString(C1373R.string.route_elevation_activity_error));
        this.f10298b.finish();
        RouteElevationActivity.a().error("get elevation error", volleyError.getMessage());
    }
}
