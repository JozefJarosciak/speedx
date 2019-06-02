package com.beastbikes.android.modules.cycling.activity.ui;

import android.content.Context;
import android.view.View;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.activity.util.C1921b;

class CyclingActivityCopy$6 extends C1921b {
    /* renamed from: a */
    final /* synthetic */ CyclingActivityCopy f8646a;

    CyclingActivityCopy$6(CyclingActivityCopy cyclingActivityCopy, Context context) {
        this.f8646a = cyclingActivityCopy;
        super(context);
    }

    /* renamed from: a */
    public void mo3285a() {
        this.f8646a.f4570a.removeCallbacks(this.f8646a.f4571b);
        for (int i = 0; i < CyclingActivityCopy.c(this.f8646a).size(); i++) {
            ((View) CyclingActivityCopy.c(this.f8646a).get(i)).setBackgroundResource(C1373R.color.location_title_success_color);
        }
        CyclingActivityCopy.d(this.f8646a).setText(this.f8646a.getResources().getString(C1373R.string.str_locating_successed));
        CyclingActivityCopy.d(this.f8646a).setTextColor(this.f8646a.getResources().getColor(C1373R.color.location_title_success_color));
    }

    /* renamed from: b */
    public void mo3286b() {
        CyclingActivityCopy.a(this.f8646a, 0);
        this.f8646a.f4570a.postDelayed(this.f8646a.f4571b, 400);
    }
}
