package com.beastbikes.android.ble.ui;

import android.text.TextUtils;
import com.beastbikes.android.dialog.Wheelview.C1703f;
import com.beastbikes.android.utils.C2580w;

class SpeedXSettingActivity$4 implements C1703f {
    /* renamed from: a */
    final /* synthetic */ SpeedXSettingActivity f7809a;

    SpeedXSettingActivity$4(SpeedXSettingActivity speedXSettingActivity) {
        this.f7809a = speedXSettingActivity;
    }

    /* renamed from: a */
    public void mo3218a(int i, String str) {
        if (!TextUtils.isEmpty(str)) {
            SpeedXSettingActivity.d(this.f7809a).setText(str);
            if (!(SpeedXSettingActivity.a(this.f7809a) == null || !SpeedXSettingActivity.a(this.f7809a).mo3158a(i) || SpeedXSettingActivity.b(this.f7809a) == null)) {
                SpeedXSettingActivity.b(this.f7809a).setLocale(i);
            }
            C2580w.m12905a(this.f7809a, "BLE - 修改语言", "");
        }
    }
}
