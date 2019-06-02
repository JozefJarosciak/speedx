package com.beastbikes.android.modules.preferences.ui;

import android.util.Log;
import com.beastbikes.android.dialog.Wheelview.C1707d;
import com.mapbox.mapboxsdk.style.layers.Property;

class UserSettingFragment$9 implements C1707d {
    /* renamed from: a */
    final /* synthetic */ UserSettingFragment f10998a;

    UserSettingFragment$9(UserSettingFragment userSettingFragment) {
        this.f10998a = userSettingFragment;
    }

    /* renamed from: a */
    public void mo3220a(String str) {
        UserSettingFragment.a(this.f10998a, Double.parseDouble(str));
        Log.e(Property.ICON_TEXT_FIT_HEIGHT, UserSettingFragment.f(this.f10998a) + "");
        UserSettingFragment.i(this.f10998a).setText(String.valueOf(Integer.parseInt(str)) + "cm");
    }
}
