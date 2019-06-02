package com.beastbikes.android.ble.ui;

import android.text.TextUtils;
import com.beastbikes.android.dialog.Wheelview.C1703f;

class SpeedXTrainingTargetActivity$4 implements C1703f {
    /* renamed from: a */
    final /* synthetic */ SpeedXTrainingTargetActivity f7830a;

    SpeedXTrainingTargetActivity$4(SpeedXTrainingTargetActivity speedXTrainingTargetActivity) {
        this.f7830a = speedXTrainingTargetActivity;
    }

    /* renamed from: a */
    public void mo3218a(int i, String str) {
        if (!TextUtils.isEmpty(str)) {
            SpeedXTrainingTargetActivity.j(this.f7830a).setText(str);
            SpeedXTrainingTargetActivity.d(this.f7830a, Integer.parseInt(str));
        }
    }
}
