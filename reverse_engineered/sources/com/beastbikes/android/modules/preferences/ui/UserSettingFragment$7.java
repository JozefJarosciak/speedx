package com.beastbikes.android.modules.preferences.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;

class UserSettingFragment$7 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ PopupWindow f10995a;
    /* renamed from: b */
    final /* synthetic */ UserSettingFragment f10996b;

    UserSettingFragment$7(UserSettingFragment userSettingFragment, PopupWindow popupWindow) {
        this.f10996b = userSettingFragment;
        this.f10995a = popupWindow;
    }

    public void onClick(View view) {
        this.f10995a.dismiss();
    }
}
