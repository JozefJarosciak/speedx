package com.beastbikes.android.ble.ui;

import android.text.TextUtils;
import com.beastbikes.android.dialog.Wheelview.C1703f;
import com.beastbikes.android.utils.C2580w;

class SpeedXSettingActivity$5 implements C1703f {
    /* renamed from: a */
    final /* synthetic */ SpeedXSettingActivity f7810a;

    SpeedXSettingActivity$5(SpeedXSettingActivity speedXSettingActivity) {
        this.f7810a = speedXSettingActivity;
    }

    /* renamed from: a */
    public void mo3218a(int i, String str) {
        if (!TextUtils.isEmpty(str)) {
            SpeedXSettingActivity.a(this.f7810a).mo3172e(i);
            SpeedXSettingActivity.b(this.f7810a).setAutolight(SpeedXSettingActivity.a(this.f7810a, i));
            C2580w.m12905a(this.f7810a, "BLE - 修改尾灯设置", "");
        }
    }
}
