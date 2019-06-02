package com.beastbikes.android.modules.preferences.ui;

import android.util.Log;
import com.beastbikes.android.dialog.Wheelview.C1708e;
import com.beastbikes.android.locale.C1849a;
import com.mapbox.mapboxsdk.style.layers.Property;

class UserSettingFragment$10 implements C1708e {
    /* renamed from: a */
    final /* synthetic */ UserSettingFragment f10970a;

    UserSettingFragment$10(UserSettingFragment userSettingFragment) {
        this.f10970a = userSettingFragment;
    }

    /* renamed from: a */
    public void mo3221a(String str, String str2) {
        UserSettingFragment.i(this.f10970a).setText(str + "'" + str2 + "\"");
        UserSettingFragment.a(this.f10970a, C1849a.m9656k((12.0d * Double.parseDouble(str)) + Double.parseDouble(str2)));
        Log.e(Property.ICON_TEXT_FIT_HEIGHT, UserSettingFragment.f(this.f10970a) + "");
    }
}
