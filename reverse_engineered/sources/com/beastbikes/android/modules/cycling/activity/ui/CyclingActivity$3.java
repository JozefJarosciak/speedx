package com.beastbikes.android.modules.cycling.activity.ui;

import android.view.View;
import com.beastbikes.android.C1373R;

class CyclingActivity$3 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ CyclingActivity f8619a;

    CyclingActivity$3(CyclingActivity cyclingActivity) {
        this.f8619a = cyclingActivity;
    }

    public void run() {
        CyclingActivity.a(this.f8619a, CyclingActivity.c(this.f8619a) % 3);
        for (int i = 0; i < CyclingActivity.d(this.f8619a).size(); i++) {
            ((View) CyclingActivity.d(this.f8619a).get(i)).setBackgroundResource(C1373R.drawable.dot_white);
        }
        ((View) CyclingActivity.d(this.f8619a).get(CyclingActivity.c(this.f8619a))).setBackgroundResource(C1373R.drawable.dot_red_bg);
        CyclingActivity.e(this.f8619a).setText(this.f8619a.getResources().getString(C1373R.string.str_locating));
        CyclingActivity.e(this.f8619a).setTextColor(this.f8619a.getResources().getColor(C1373R.color.design_color_c7));
        CyclingActivity.f(this.f8619a);
        this.f8619a.f4539a.postDelayed(this, 400);
    }
}
