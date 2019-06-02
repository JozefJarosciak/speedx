package com.beastbikes.android.modules.social.im.ui.conversation;

import com.baidu.mapapi.model.LatLng;
import com.beastbikes.android.modules.cycling.route.dto.PoiInfoDTO;

class LocationSelectActivity$2 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ double f11186a;
    /* renamed from: b */
    final /* synthetic */ double f11187b;
    /* renamed from: c */
    final /* synthetic */ LocationSelectActivity f11188c;

    LocationSelectActivity$2(LocationSelectActivity locationSelectActivity, double d, double d2) {
        this.f11188c = locationSelectActivity;
        this.f11186a = d;
        this.f11187b = d2;
    }

    public void run() {
        LocationSelectActivity.d(this.f11188c).startAnimation(LocationSelectActivity.c(this.f11188c));
        LocationSelectActivity.a(this.f11188c, new LatLng(this.f11186a, this.f11187b));
        if (LocationSelectActivity.e(this.f11188c) == null) {
            LocationSelectActivity.a(this.f11188c, new PoiInfoDTO());
        }
        if (LocationSelectActivity.f(this.f11188c)) {
            LocationSelectActivity.e(this.f11188c).setLatitude(LocationSelectActivity.g(this.f11188c).latitude);
            LocationSelectActivity.e(this.f11188c).setLongitude(LocationSelectActivity.g(this.f11188c).longitude);
            LocationSelectActivity.h(this.f11188c).m9671a(this.f11188c, this.f11188c.getRequestQueueFactory(), LocationSelectActivity.g(this.f11188c).latitude, LocationSelectActivity.g(this.f11188c).longitude, this.f11188c);
            LocationSelectActivity.a(this.f11188c, false);
        }
    }
}
