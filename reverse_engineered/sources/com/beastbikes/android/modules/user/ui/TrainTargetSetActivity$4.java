package com.beastbikes.android.modules.user.ui;

import com.beastbikes.android.dialog.C1796f.C1795a;
import com.beastbikes.android.locale.C1849a;

class TrainTargetSetActivity$4 implements C1795a {
    /* renamed from: a */
    final /* synthetic */ TrainTargetSetActivity f11781a;

    TrainTargetSetActivity$4(TrainTargetSetActivity trainTargetSetActivity) {
        this.f11781a = trainTargetSetActivity;
    }

    /* renamed from: a */
    public void mo3507a(int i, String str, int i2, String str2) {
        int parseInt = Integer.parseInt(str.replace("'", ""));
        int parseInt2 = Integer.parseInt(str2.replace("\"", ""));
        TrainTargetSetActivity.a(this.f11781a, C1849a.m9656k((double) parseInt2) + C1849a.m9655j((double) parseInt));
        TrainTargetSetActivity.f(this.f11781a).setText(str + str2);
    }
}
