package com.beastbikes.android.ble.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.ble.biz.p097b.C1623g;
import com.beastbikes.android.widget.C2621c;

class SpeedXOtaVersionActivity$4 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ int f7795a;
    /* renamed from: b */
    final /* synthetic */ SpeedXOtaVersionActivity f7796b;

    SpeedXOtaVersionActivity$4(SpeedXOtaVersionActivity speedXOtaVersionActivity, int i) {
        this.f7796b = speedXOtaVersionActivity;
        this.f7795a = i;
    }

    public void run() {
        if (this.f7795a == -1 && this.f7796b.getWindow() != null) {
            SpeedXOtaVersionActivity.a(this.f7796b, false);
            final C2621c c2621c = new C2621c(this.f7796b);
            c2621c.m13065b((int) C1373R.string.label_speed_force_restart_msg);
            c2621c.m13059a((int) C1373R.string.label_i_know, new OnClickListener(this) {
                /* renamed from: b */
                final /* synthetic */ SpeedXOtaVersionActivity$4 f7794b;

                public void onClick(View view) {
                    c2621c.m13069b();
                    C1623g c = SpeedXOtaVersionActivity.d(this.f7794b.f7796b).mo3167c();
                    if (c != null) {
                        c.mo3197b(2);
                    }
                    this.f7794b.f7796b.finish();
                }
            }).m13063a();
        }
        if (this.f7795a == 0) {
            SpeedXOtaVersionActivity.e(this.f7796b);
        }
    }
}
