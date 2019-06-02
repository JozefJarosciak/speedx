package com.beastbikes.android.ble.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.widget.C2621c;

class SpeedXOtaVersionActivity$10 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C2621c f7785a;
    /* renamed from: b */
    final /* synthetic */ SpeedXOtaVersionActivity f7786b;

    SpeedXOtaVersionActivity$10(SpeedXOtaVersionActivity speedXOtaVersionActivity, C2621c c2621c) {
        this.f7786b = speedXOtaVersionActivity;
        this.f7785a = c2621c;
    }

    public void onClick(View view) {
        this.f7785a.m13069b();
        this.f7786b.setResult(82, this.f7786b.getIntent());
        this.f7786b.finish();
    }
}
