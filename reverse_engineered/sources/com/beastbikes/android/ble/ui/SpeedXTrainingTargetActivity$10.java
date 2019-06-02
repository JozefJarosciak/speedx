package com.beastbikes.android.ble.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import com.beastbikes.android.dialog.Wheelview;
import com.beastbikes.android.dialog.Wheelview.C1707d;

class SpeedXTrainingTargetActivity$10 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C1707d f7812a;
    /* renamed from: b */
    final /* synthetic */ Wheelview f7813b;
    /* renamed from: c */
    final /* synthetic */ String f7814c;
    /* renamed from: d */
    final /* synthetic */ PopupWindow f7815d;
    /* renamed from: e */
    final /* synthetic */ SpeedXTrainingTargetActivity f7816e;

    SpeedXTrainingTargetActivity$10(SpeedXTrainingTargetActivity speedXTrainingTargetActivity, C1707d c1707d, Wheelview wheelview, String str, PopupWindow popupWindow) {
        this.f7816e = speedXTrainingTargetActivity;
        this.f7812a = c1707d;
        this.f7813b = wheelview;
        this.f7814c = str;
        this.f7815d = popupWindow;
    }

    public void onClick(View view) {
        this.f7812a.mo3220a(this.f7813b.getSelectedText().replace(this.f7814c, ""));
        this.f7815d.dismiss();
    }
}
