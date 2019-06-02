package com.beastbikes.android.modules.preferences.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import com.beastbikes.android.dialog.Wheelview;
import com.beastbikes.android.dialog.Wheelview.C1707d;

class UserSettingFragment$6 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C1707d f10990a;
    /* renamed from: b */
    final /* synthetic */ Wheelview f10991b;
    /* renamed from: c */
    final /* synthetic */ String f10992c;
    /* renamed from: d */
    final /* synthetic */ PopupWindow f10993d;
    /* renamed from: e */
    final /* synthetic */ UserSettingFragment f10994e;

    UserSettingFragment$6(UserSettingFragment userSettingFragment, C1707d c1707d, Wheelview wheelview, String str, PopupWindow popupWindow) {
        this.f10994e = userSettingFragment;
        this.f10990a = c1707d;
        this.f10991b = wheelview;
        this.f10992c = str;
        this.f10993d = popupWindow;
    }

    public void onClick(View view) {
        this.f10990a.mo3220a(this.f10991b.getSelectedText().replace(this.f10992c, ""));
        this.f10993d.dismiss();
    }
}
