package com.beastbikes.android.ble.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;

class SpeedXTrainingTargetActivity$11 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ PopupWindow f7817a;
    /* renamed from: b */
    final /* synthetic */ SpeedXTrainingTargetActivity f7818b;

    SpeedXTrainingTargetActivity$11(SpeedXTrainingTargetActivity speedXTrainingTargetActivity, PopupWindow popupWindow) {
        this.f7818b = speedXTrainingTargetActivity;
        this.f7817a = popupWindow;
    }

    public void onClick(View view) {
        this.f7817a.dismiss();
    }
}
