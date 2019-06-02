package com.beastbikes.android.modules.user.ui;

import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1794e.C1793a;
import com.beastbikes.framework.ui.android.utils.Toasts;
import java.util.Calendar;
import java.util.Date;

class CyclingRecordFilterTimeActivity$2 implements C1793a {
    /* renamed from: a */
    final /* synthetic */ CyclingRecordFilterTimeActivity f11570a;

    CyclingRecordFilterTimeActivity$2(CyclingRecordFilterTimeActivity cyclingRecordFilterTimeActivity) {
        this.f11570a = cyclingRecordFilterTimeActivity;
    }

    /* renamed from: a */
    public void mo3505a(int i, int i2, int i3, int i4) {
        Calendar instance = Calendar.getInstance();
        instance.set(i, i2 - 1, i3, 24, 0, 0);
        long timeInMillis = (instance.getTimeInMillis() / 1000) - 1;
        if (timeInMillis < CyclingRecordFilterTimeActivity.c(this.f11570a)) {
            Toasts.show(this.f11570a, (int) C1373R.string.str_select_end_time_error);
            return;
        }
        CyclingRecordFilterTimeActivity.b(this.f11570a, timeInMillis);
        CyclingRecordFilterTimeActivity.e(this.f11570a).setText(CyclingRecordFilterTimeActivity.a(this.f11570a).format(new Date(CyclingRecordFilterTimeActivity.d(this.f11570a) * 1000)));
    }
}
