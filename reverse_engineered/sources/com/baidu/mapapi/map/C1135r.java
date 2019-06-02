package com.baidu.mapapi.map;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.platform.comapi.map.C1235E;

/* renamed from: com.baidu.mapapi.map.r */
class C1135r implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ TextureMapView f3272a;

    C1135r(TextureMapView textureMapView) {
        this.f3272a = textureMapView;
    }

    public void onClick(View view) {
        C1235E D = this.f3272a.f3157b.m4625b().m4660D();
        D.f3678a -= 1.0f;
        this.f3272a.f3157b.m4625b().m4682a(D, 300);
    }
}
