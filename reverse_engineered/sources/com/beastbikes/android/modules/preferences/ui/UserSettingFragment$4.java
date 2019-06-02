package com.beastbikes.android.modules.preferences.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;

class UserSettingFragment$4 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ PopupWindow f10987a;
    /* renamed from: b */
    final /* synthetic */ UserSettingFragment f10988b;

    UserSettingFragment$4(UserSettingFragment userSettingFragment, PopupWindow popupWindow) {
        this.f10988b = userSettingFragment;
        this.f10987a = popupWindow;
    }

    public void onClick(View view) {
        this.f10987a.dismiss();
    }
}
