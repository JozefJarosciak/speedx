package com.beastbikes.android.modules.cycling.route.ui;

import com.beastbikes.android.modules.cycling.sections.ui.widget.DrawableClickListener;
import com.beastbikes.android.modules.cycling.sections.ui.widget.DrawableClickListener.DrawablePosition;

class RouteMapSearchGeoActivity$2 implements DrawableClickListener {
    /* renamed from: a */
    final /* synthetic */ RouteMapSearchGeoActivity f10304a;

    RouteMapSearchGeoActivity$2(RouteMapSearchGeoActivity routeMapSearchGeoActivity) {
        this.f10304a = routeMapSearchGeoActivity;
    }

    public void onClick(DrawablePosition drawablePosition) {
        switch (RouteMapSearchGeoActivity$4.f10306a[drawablePosition.ordinal()]) {
            case 1:
                RouteMapSearchGeoActivity.e(this.f10304a).setText("");
                return;
            default:
                return;
        }
    }
}
