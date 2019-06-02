package com.beastbikes.android.ble.ui;

import android.text.TextUtils;
import com.beastbikes.android.dialog.Wheelview.C1703f;
import com.beastbikes.android.utils.C2580w;

class SpeedXSettingActivity$6 implements C1703f {
    /* renamed from: a */
    final /* synthetic */ SpeedXSettingActivity f7811a;

    SpeedXSettingActivity$6(SpeedXSettingActivity speedXSettingActivity) {
        this.f7811a = speedXSettingActivity;
    }

    /* renamed from: a */
    public void mo3218a(int i, String str) {
        if (!TextUtils.isEmpty(str)) {
            SpeedXSettingActivity.e(this.f7811a).setText(str);
            if (!(SpeedXSettingActivity.a(this.f7811a) == null || !SpeedXSettingActivity.a(this.f7811a).mo3165b(i) || SpeedXSettingActivity.b(this.f7811a) == null)) {
                SpeedXSettingActivity.b(this.f7811a).setMileageUnit(i);
            }
            C2580w.m12905a(this.f7811a, "BLE - 修改里程单位", "");
        }
    }
}
