package com.beastbikes.android.ble.ui;

import com.beastbikes.android.dialog.Wheelview.C1708e;
import com.beastbikes.android.locale.C1849a;

class SpeedXTrainingTargetActivity$5 implements C1708e {
    /* renamed from: a */
    final /* synthetic */ SpeedXTrainingTargetActivity f7831a;

    SpeedXTrainingTargetActivity$5(SpeedXTrainingTargetActivity speedXTrainingTargetActivity) {
        this.f7831a = speedXTrainingTargetActivity;
    }

    /* renamed from: a */
    public void mo3221a(String str, String str2) {
        SpeedXTrainingTargetActivity.a(this.f7831a).setText(str + "'" + str2 + "\"");
        SpeedXTrainingTargetActivity.a(this.f7831a, (int) C1849a.m9656k((12.0d * Double.parseDouble(str)) + Double.parseDouble(str2)));
    }
}
