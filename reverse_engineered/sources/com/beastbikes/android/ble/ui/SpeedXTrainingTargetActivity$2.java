package com.beastbikes.android.ble.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import com.beastbikes.android.dialog.Wheelview;
import com.beastbikes.android.dialog.Wheelview.C1708e;

class SpeedXTrainingTargetActivity$2 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C1708e f7821a;
    /* renamed from: b */
    final /* synthetic */ Wheelview f7822b;
    /* renamed from: c */
    final /* synthetic */ String f7823c;
    /* renamed from: d */
    final /* synthetic */ Wheelview f7824d;
    /* renamed from: e */
    final /* synthetic */ String f7825e;
    /* renamed from: f */
    final /* synthetic */ PopupWindow f7826f;
    /* renamed from: g */
    final /* synthetic */ SpeedXTrainingTargetActivity f7827g;

    SpeedXTrainingTargetActivity$2(SpeedXTrainingTargetActivity speedXTrainingTargetActivity, C1708e c1708e, Wheelview wheelview, String str, Wheelview wheelview2, String str2, PopupWindow popupWindow) {
        this.f7827g = speedXTrainingTargetActivity;
        this.f7821a = c1708e;
        this.f7822b = wheelview;
        this.f7823c = str;
        this.f7824d = wheelview2;
        this.f7825e = str2;
        this.f7826f = popupWindow;
    }

    public void onClick(View view) {
        this.f7821a.mo3221a(this.f7822b.getSelectedText().replace(this.f7823c, ""), this.f7824d.getSelectedText().replace(this.f7825e, ""));
        this.f7826f.dismiss();
    }
}
