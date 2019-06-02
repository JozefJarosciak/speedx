package com.beastbikes.android.modules.cycling.route.ui;

import com.beastbikes.android.C1373R;
import com.beastbikes.framework.ui.android.utils.Toasts;

class RouteMapSearchGeoActivity$3 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ RouteMapSearchGeoActivity f10305a;

    RouteMapSearchGeoActivity$3(RouteMapSearchGeoActivity routeMapSearchGeoActivity) {
        this.f10305a = routeMapSearchGeoActivity;
    }

    public void run() {
        Toasts.show(this.f10305a, this.f10305a.getResources().getString(C1373R.string.get_poi_no_result));
    }
}
