package com.beastbikes.android.modules.cycling.activity.ui;

import android.location.GpsStatus.Listener;

class MapFragment$a implements Listener {
    /* renamed from: a */
    final /* synthetic */ MapFragment f8718a;

    private MapFragment$a(MapFragment mapFragment) {
        this.f8718a = mapFragment;
    }

    public void onGpsStatusChanged(int i) {
        switch (i) {
            case 1:
                MapFragment.e().error("定位启动");
                return;
            case 2:
                MapFragment.e().error("定位结束");
                return;
            case 3:
                MapFragment.e().error("第一次定位");
                return;
            case 4:
                MapFragment.a(this.f8718a, i, MapFragment.n(this.f8718a).getGpsStatus(null));
                return;
            default:
                return;
        }
    }
}
