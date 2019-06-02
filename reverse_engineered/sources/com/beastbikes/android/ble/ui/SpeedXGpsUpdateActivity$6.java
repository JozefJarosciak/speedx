package com.beastbikes.android.ble.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.ble.biz.p097b.C1623g;
import com.beastbikes.android.widget.C2621c;

class SpeedXGpsUpdateActivity$6 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C2621c f7773a;
    /* renamed from: b */
    final /* synthetic */ boolean f7774b;
    /* renamed from: c */
    final /* synthetic */ SpeedXGpsUpdateActivity f7775c;

    SpeedXGpsUpdateActivity$6(SpeedXGpsUpdateActivity speedXGpsUpdateActivity, C2621c c2621c, boolean z) {
        this.f7775c = speedXGpsUpdateActivity;
        this.f7773a = c2621c;
        this.f7774b = z;
    }

    public void onClick(View view) {
        this.f7773a.m13069b();
        if (SpeedXGpsUpdateActivity.d(this.f7775c) != null) {
            C1623g c = SpeedXGpsUpdateActivity.d(this.f7775c).mo3167c();
            if (c != null) {
                c.mo3195a(1);
            }
        }
        if (this.f7774b) {
            this.f7775c.setResult(82, this.f7775c.getIntent());
        }
        this.f7775c.finish();
    }
}
