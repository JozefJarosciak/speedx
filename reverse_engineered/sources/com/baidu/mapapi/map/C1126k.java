package com.baidu.mapapi.map;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.platform.comapi.map.C1235E;

/* renamed from: com.baidu.mapapi.map.k */
class C1126k implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ MapView f3255a;

    C1126k(MapView mapView) {
        this.f3255a = mapView;
    }

    public void onClick(View view) {
        C1235E D = this.f3255a.f2999c.m4760a().m4660D();
        D.f3678a += 1.0f;
        this.f3255a.f2999c.m4760a().m4682a(D, 300);
    }
}
