package com.beastbikes.android.modules.preferences.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.PopupWindow;
import com.beastbikes.android.dialog.Wheelview;
import com.beastbikes.android.dialog.Wheelview.C1708e;

class UserSettingFragment$3 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C1708e f10980a;
    /* renamed from: b */
    final /* synthetic */ Wheelview f10981b;
    /* renamed from: c */
    final /* synthetic */ String f10982c;
    /* renamed from: d */
    final /* synthetic */ Wheelview f10983d;
    /* renamed from: e */
    final /* synthetic */ String f10984e;
    /* renamed from: f */
    final /* synthetic */ PopupWindow f10985f;
    /* renamed from: g */
    final /* synthetic */ UserSettingFragment f10986g;

    UserSettingFragment$3(UserSettingFragment userSettingFragment, C1708e c1708e, Wheelview wheelview, String str, Wheelview wheelview2, String str2, PopupWindow popupWindow) {
        this.f10986g = userSettingFragment;
        this.f10980a = c1708e;
        this.f10981b = wheelview;
        this.f10982c = str;
        this.f10983d = wheelview2;
        this.f10984e = str2;
        this.f10985f = popupWindow;
    }

    public void onClick(View view) {
        this.f10980a.mo3221a(this.f10981b.getSelectedText().replace(this.f10982c, ""), this.f10983d.getSelectedText().replace(this.f10984e, ""));
        this.f10985f.dismiss();
    }
}
