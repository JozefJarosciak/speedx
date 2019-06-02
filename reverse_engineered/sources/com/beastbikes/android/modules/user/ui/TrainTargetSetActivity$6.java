package com.beastbikes.android.modules.user.ui;

import com.beastbikes.android.dialog.C1794e.C1793a;
import java.util.GregorianCalendar;
import org.apache.commons.cli.HelpFormatter;

class TrainTargetSetActivity$6 implements C1793a {
    /* renamed from: a */
    final /* synthetic */ TrainTargetSetActivity f11783a;

    TrainTargetSetActivity$6(TrainTargetSetActivity trainTargetSetActivity) {
        this.f11783a = trainTargetSetActivity;
    }

    /* renamed from: a */
    public void mo3505a(int i, int i2, int i3, int i4) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.set(i, i2 - 1, i3);
        TrainTargetSetActivity.a(this.f11783a, gregorianCalendar.getTimeInMillis() / 1000);
        TrainTargetSetActivity.e(this.f11783a, i4);
        TrainTargetSetActivity.m(this.f11783a).setText(i + HelpFormatter.DEFAULT_OPT_PREFIX + i2 + HelpFormatter.DEFAULT_OPT_PREFIX + i3);
        if (TrainTargetSetActivity.p(this.f11783a).getBoolean("PREF.USER.MAX.HEART.RATE.BY.BIRTHDAY", true)) {
            TrainTargetSetActivity.f(this.f11783a, (int) (208.754d - (((double) TrainTargetSetActivity.q(this.f11783a)) * 0.734d)));
            TrainTargetSetActivity.o(this.f11783a).setText(String.valueOf(TrainTargetSetActivity.n(this.f11783a)));
        }
    }
}
