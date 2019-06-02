package com.beastbikes.android.ble.ui;

import com.beastbikes.android.dialog.Wheelview.C1707d;

class SpeedXTrainingTargetActivity$6 implements C1707d {
    /* renamed from: a */
    final /* synthetic */ SpeedXTrainingTargetActivity f7832a;

    SpeedXTrainingTargetActivity$6(SpeedXTrainingTargetActivity speedXTrainingTargetActivity) {
        this.f7832a = speedXTrainingTargetActivity;
    }

    /* renamed from: a */
    public void mo3220a(String str) {
        SpeedXTrainingTargetActivity.b(this.f7832a, Integer.parseInt(str));
        SpeedXTrainingTargetActivity.c(this.f7832a).setText(Math.round((float) SpeedXTrainingTargetActivity.b(this.f7832a)) + "kg");
    }
}
