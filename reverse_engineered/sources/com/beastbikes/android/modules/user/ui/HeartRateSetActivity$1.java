package com.beastbikes.android.modules.user.ui;

import com.beastbikes.android.dialog.C1805k.C1804a;

class HeartRateSetActivity$1 implements C1804a {
    /* renamed from: a */
    final /* synthetic */ HeartRateSetActivity f11719a;

    HeartRateSetActivity$1(HeartRateSetActivity heartRateSetActivity) {
        this.f11719a = heartRateSetActivity;
    }

    /* renamed from: a */
    public void mo3284a(int i, String str) {
        HeartRateSetActivity.a(this.f11719a, Integer.parseInt(str));
        HeartRateSetActivity.a(this.f11719a).setText(str);
        HeartRateSetActivity.b(this.f11719a).setChecked(false);
    }
}
