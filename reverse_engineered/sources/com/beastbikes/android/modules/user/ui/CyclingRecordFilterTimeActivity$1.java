package com.beastbikes.android.modules.user.ui;

import com.beastbikes.android.dialog.C1794e.C1793a;
import java.util.Calendar;

class CyclingRecordFilterTimeActivity$1 implements C1793a {
    /* renamed from: a */
    final /* synthetic */ CyclingRecordFilterTimeActivity f11569a;

    CyclingRecordFilterTimeActivity$1(CyclingRecordFilterTimeActivity cyclingRecordFilterTimeActivity) {
        this.f11569a = cyclingRecordFilterTimeActivity;
    }

    /* renamed from: a */
    public void mo3505a(int i, int i2, int i3, int i4) {
        Calendar instance = Calendar.getInstance();
        instance.set(i, i2 - 1, i3, 0, 0, 0);
        CyclingRecordFilterTimeActivity.b(this.f11569a).setText(CyclingRecordFilterTimeActivity.a(this.f11569a).format(instance.getTime()));
        CyclingRecordFilterTimeActivity.a(this.f11569a, instance.getTimeInMillis() / 1000);
    }
}
