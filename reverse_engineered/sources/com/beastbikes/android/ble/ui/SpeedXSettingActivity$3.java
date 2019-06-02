package com.beastbikes.android.ble.ui;

import android.text.TextUtils;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.Wheelview.C1703f;
import com.umeng.analytics.MobclickAgent;

class SpeedXSettingActivity$3 implements C1703f {
    /* renamed from: a */
    final /* synthetic */ SpeedXSettingActivity f7808a;

    SpeedXSettingActivity$3(SpeedXSettingActivity speedXSettingActivity) {
        this.f7808a = speedXSettingActivity;
    }

    /* renamed from: a */
    public void mo3218a(int i, String str) {
        if (!TextUtils.isEmpty(str)) {
            SpeedXSettingActivity.c(this.f7808a).setText(str);
            int[] intArray = this.f7808a.getResources().getIntArray(C1373R.array.select_wheel_value_array);
            byte b = (byte) intArray[0];
            if (i < intArray.length) {
                b = (byte) intArray[i];
            }
            if (SpeedXSettingActivity.a(this.f7808a) != null && SpeedXSettingActivity.a(this.f7808a).mo3157a(b)) {
                if (SpeedXSettingActivity.b(this.f7808a) != null) {
                    SpeedXSettingActivity.b(this.f7808a).setWheelType(b);
                }
                MobclickAgent.a(this.f7808a, "BLE - 修改轮径");
            }
        }
    }
}
