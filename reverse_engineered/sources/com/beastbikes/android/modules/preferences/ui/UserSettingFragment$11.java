package com.beastbikes.android.modules.preferences.ui;

import android.util.Log;
import com.beastbikes.android.dialog.Wheelview.C1707d;

class UserSettingFragment$11 implements C1707d {
    /* renamed from: a */
    final /* synthetic */ UserSettingFragment f10971a;

    UserSettingFragment$11(UserSettingFragment userSettingFragment) {
        this.f10971a = userSettingFragment;
    }

    /* renamed from: a */
    public void mo3220a(String str) {
        UserSettingFragment.b(this.f10971a, (double) Integer.parseInt(str));
        UserSettingFragment.j(this.f10971a).setText(Math.round((float) UserSettingFragment.g(this.f10971a)) + "kg");
        Log.e("weight", "" + UserSettingFragment.g(this.f10971a));
    }
}
