package com.baidu.mapapi.map;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.platform.comapi.map.C1235E;

/* renamed from: com.baidu.mapapi.map.w */
class C1140w implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ WearMapView f3281a;

    C1140w(WearMapView wearMapView) {
        this.f3281a = wearMapView;
    }

    public void onClick(View view) {
        C1235E D = this.f3281a.f3219d.m4760a().m4660D();
        D.f3678a += 1.0f;
        this.f3281a.f3219d.m4760a().m4682a(D, 300);
    }
}
