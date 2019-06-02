package com.beastbikes.android.ble.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.widget.C2621c;

class SpeedXGpsUpdateActivity$4 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C2621c f7769a;
    /* renamed from: b */
    final /* synthetic */ SpeedXGpsUpdateActivity f7770b;

    SpeedXGpsUpdateActivity$4(SpeedXGpsUpdateActivity speedXGpsUpdateActivity, C2621c c2621c) {
        this.f7770b = speedXGpsUpdateActivity;
        this.f7769a = c2621c;
    }

    public void onClick(View view) {
        SpeedXGpsUpdateActivity.b(this.f7770b).m8744i().m8750a(true);
        this.f7769a.m13069b();
        SpeedXGpsUpdateActivity.c(this.f7770b);
    }
}
