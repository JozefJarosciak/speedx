package com.beastbikes.android.ble.ui;

import com.beastbikes.android.widget.materialdesign.mdswitch.Switch;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch.C1706a;

class SpeedXSettingActivity$2 implements C1706a {
    /* renamed from: a */
    final /* synthetic */ SpeedXSettingActivity f7807a;

    SpeedXSettingActivity$2(SpeedXSettingActivity speedXSettingActivity) {
        this.f7807a = speedXSettingActivity;
    }

    /* renamed from: a */
    public void mo3219a(Switch switchR, boolean z) {
        if (SpeedXSettingActivity.a(this.f7807a) != null) {
            SpeedXSettingActivity.a(this.f7807a).mo3166b(z);
            if (SpeedXSettingActivity.b(this.f7807a) != null) {
                SpeedXSettingActivity.b(this.f7807a).setShakeUp(z ? 1 : 0);
            }
        }
    }
}
