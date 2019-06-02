package com.beastbikes.android.modules.preferences.ui;

import android.util.Log;
import com.beastbikes.android.dialog.Wheelview.C1707d;
import com.beastbikes.android.locale.C1849a;
import com.google.zxing.client.result.ExpandedProductParsedResult;

class UserSettingFragment$12 implements C1707d {
    /* renamed from: a */
    final /* synthetic */ UserSettingFragment f10972a;

    UserSettingFragment$12(UserSettingFragment userSettingFragment) {
        this.f10972a = userSettingFragment;
    }

    /* renamed from: a */
    public void mo3220a(String str) {
        UserSettingFragment.b(this.f10972a, C1849a.m9654i(Double.parseDouble(str)));
        UserSettingFragment.j(this.f10972a).setText(str + "lb");
        Log.e("weight", UserSettingFragment.g(this.f10972a) + "lb");
        Log.e(ExpandedProductParsedResult.POUND, C1849a.m9653h(UserSettingFragment.g(this.f10972a)) + "");
        Log.e(ExpandedProductParsedResult.POUND, Math.round(C1849a.m9653h(UserSettingFragment.g(this.f10972a))) + "");
    }
}
