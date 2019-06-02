package com.beastbikes.android.modules.cycling.activity.ui;

import android.view.View;
import com.beastbikes.android.C1373R;

class CyclingActivityCopy$5 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ CyclingActivityCopy f8645a;

    CyclingActivityCopy$5(CyclingActivityCopy cyclingActivityCopy) {
        this.f8645a = cyclingActivityCopy;
    }

    public void run() {
        CyclingActivityCopy.a(this.f8645a, CyclingActivityCopy.b(this.f8645a) % 3);
        for (int i = 0; i < CyclingActivityCopy.c(this.f8645a).size(); i++) {
            ((View) CyclingActivityCopy.c(this.f8645a).get(i)).setBackgroundResource(C1373R.drawable.dot_white);
        }
        ((View) CyclingActivityCopy.c(this.f8645a).get(CyclingActivityCopy.b(this.f8645a))).setBackgroundResource(C1373R.drawable.dot_red_bg);
        CyclingActivityCopy.d(this.f8645a).setText(this.f8645a.getResources().getString(C1373R.string.str_locating));
        CyclingActivityCopy.d(this.f8645a).setTextColor(this.f8645a.getResources().getColor(C1373R.color.design_color_c7));
        CyclingActivityCopy.e(this.f8645a);
        this.f8645a.f4570a.postDelayed(this, 400);
    }
}
