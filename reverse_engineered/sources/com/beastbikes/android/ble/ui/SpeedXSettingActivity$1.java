package com.beastbikes.android.ble.ui;

import com.beastbikes.android.widget.materialdesign.mdswitch.Switch;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch.C1706a;

class SpeedXSettingActivity$1 implements C1706a {
    /* renamed from: a */
    final /* synthetic */ SpeedXSettingActivity f7806a;

    SpeedXSettingActivity$1(SpeedXSettingActivity speedXSettingActivity) {
        this.f7806a = speedXSettingActivity;
    }

    /* renamed from: a */
    public void mo3219a(Switch switchR, boolean z) {
        if (SpeedXSettingActivity.a(this.f7806a) != null) {
            SpeedXSettingActivity.a(this.f7806a).mo3162a(z);
            if (SpeedXSettingActivity.b(this.f7806a) != null) {
                SpeedXSettingActivity.b(this.f7806a).setNotification(z ? 1 : 0);
            }
        }
    }
}
