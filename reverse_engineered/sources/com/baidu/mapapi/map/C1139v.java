package com.baidu.mapapi.map;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.platform.comapi.map.C1235E;

/* renamed from: com.baidu.mapapi.map.v */
class C1139v implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ WearMapView f3280a;

    C1139v(WearMapView wearMapView) {
        this.f3280a = wearMapView;
    }

    public void onClick(View view) {
        C1235E D = this.f3280a.f3219d.m4760a().m4660D();
        D.f3678a -= 1.0f;
        this.f3280a.f3219d.m4760a().m4682a(D, 300);
    }
}
