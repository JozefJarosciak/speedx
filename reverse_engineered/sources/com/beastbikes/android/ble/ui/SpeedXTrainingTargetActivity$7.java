package com.beastbikes.android.ble.ui;

import com.beastbikes.android.dialog.Wheelview.C1707d;
import com.beastbikes.android.locale.C1849a;

class SpeedXTrainingTargetActivity$7 implements C1707d {
    /* renamed from: a */
    final /* synthetic */ SpeedXTrainingTargetActivity f7833a;

    SpeedXTrainingTargetActivity$7(SpeedXTrainingTargetActivity speedXTrainingTargetActivity) {
        this.f7833a = speedXTrainingTargetActivity;
    }

    /* renamed from: a */
    public void mo3220a(String str) {
        SpeedXTrainingTargetActivity.b(this.f7833a, (int) C1849a.m9654i(Double.parseDouble(str)));
        SpeedXTrainingTargetActivity.c(this.f7833a).setText(str + "lb");
    }
}
