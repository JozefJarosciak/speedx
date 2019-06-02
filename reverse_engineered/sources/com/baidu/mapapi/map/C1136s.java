package com.baidu.mapapi.map;

import android.view.View;
import android.view.View.OnClickListener;
import com.baidu.platform.comapi.map.C1235E;

/* renamed from: com.baidu.mapapi.map.s */
class C1136s implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ TextureMapView f3273a;

    C1136s(TextureMapView textureMapView) {
        this.f3273a = textureMapView;
    }

    public void onClick(View view) {
        C1235E D = this.f3273a.f3157b.m4625b().m4660D();
        D.f3678a += 1.0f;
        this.f3273a.f3157b.m4625b().m4682a(D, 300);
    }
}
