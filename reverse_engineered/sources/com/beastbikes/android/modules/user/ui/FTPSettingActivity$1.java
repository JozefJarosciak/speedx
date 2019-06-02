package com.beastbikes.android.modules.user.ui;

import com.beastbikes.android.dialog.C1794e.C1793a;
import java.util.Calendar;

class FTPSettingActivity$1 implements C1793a {
    /* renamed from: a */
    final /* synthetic */ FTPSettingActivity f11582a;

    FTPSettingActivity$1(FTPSettingActivity fTPSettingActivity) {
        this.f11582a = fTPSettingActivity;
    }

    /* renamed from: a */
    public void mo3505a(int i, int i2, int i3, int i4) {
        Calendar instance = Calendar.getInstance();
        instance.set(i, i2 - 1, i3);
        FTPSettingActivity.b(this.f11582a).setText(FTPSettingActivity.a(this.f11582a).format(instance.getTime()));
        FTPSettingActivity.a(this.f11582a, instance.getTimeInMillis() / 1000);
    }
}
