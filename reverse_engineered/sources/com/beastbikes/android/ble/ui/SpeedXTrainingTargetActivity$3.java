package com.beastbikes.android.ble.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;

class SpeedXTrainingTargetActivity$3 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ PopupWindow f7828a;
    /* renamed from: b */
    final /* synthetic */ SpeedXTrainingTargetActivity f7829b;

    SpeedXTrainingTargetActivity$3(SpeedXTrainingTargetActivity speedXTrainingTargetActivity, PopupWindow popupWindow) {
        this.f7829b = speedXTrainingTargetActivity;
        this.f7828a = popupWindow;
    }

    public void onClick(View view) {
        this.f7828a.dismiss();
    }
}
