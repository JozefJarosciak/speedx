package com.beastbikes.android.modules.cycling.route.ui;

import com.squareup.picasso.Callback;

class RouteActivity$1 implements Callback {
    /* renamed from: a */
    final /* synthetic */ RouteActivity f10283a;

    RouteActivity$1(RouteActivity routeActivity) {
        this.f10283a = routeActivity;
    }

    public void onSuccess() {
        RouteActivity.a(this.f10283a).setVisibility(8);
    }

    public void onError() {
        RouteActivity.b(this.f10283a).setVisibility(8);
    }
}
