package com.beastbikes.android.ble.ui;

import android.os.Handler;
import android.os.Message;
import com.beastbikes.android.C1373R;
import java.lang.ref.WeakReference;

class SpeedXForceScreenSettingsActivity$a extends Handler {
    /* renamed from: a */
    final /* synthetic */ SpeedXForceScreenSettingsActivity f7755a;
    /* renamed from: b */
    private final WeakReference<SpeedXForceScreenSettingsActivity> f7756b;

    SpeedXForceScreenSettingsActivity$a(SpeedXForceScreenSettingsActivity speedXForceScreenSettingsActivity, SpeedXForceScreenSettingsActivity speedXForceScreenSettingsActivity2) {
        this.f7755a = speedXForceScreenSettingsActivity;
        this.f7756b = new WeakReference(speedXForceScreenSettingsActivity2);
    }

    public void handleMessage(Message message) {
        if (this.f7756b.get() != null && SpeedXForceScreenSettingsActivity.b((SpeedXForceScreenSettingsActivity) this.f7756b.get()).isShowing()) {
            SpeedXForceScreenSettingsActivity.a((SpeedXForceScreenSettingsActivity) this.f7756b.get(), this.f7755a.getString(C1373R.string.str_synchronization_error));
            SpeedXForceScreenSettingsActivity.c((SpeedXForceScreenSettingsActivity) this.f7756b.get());
        }
    }
}
