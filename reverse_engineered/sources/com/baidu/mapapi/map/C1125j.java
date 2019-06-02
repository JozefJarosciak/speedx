package com.baidu.mapapi.map;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.platform.comapi.map.C1235E;

/* renamed from: com.baidu.mapapi.map.j */
class C1125j implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ MapView f3254a;

    C1125j(MapView mapView) {
        this.f3254a = mapView;
    }

    public void onClick(View view) {
        C1235E D = this.f3254a.f2999c.m4760a().m4660D();
        D.f3678a -= 1.0f;
        this.f3254a.f2999c.m4760a().m4682a(D, 300);
    }
}
