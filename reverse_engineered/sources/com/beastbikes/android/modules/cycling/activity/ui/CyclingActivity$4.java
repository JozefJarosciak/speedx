package com.beastbikes.android.modules.cycling.activity.ui;

import android.content.Context;
import android.view.View;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.modules.cycling.activity.util.C1921b;

class CyclingActivity$4 extends C1921b {
    /* renamed from: a */
    final /* synthetic */ CyclingActivity f8624a;

    CyclingActivity$4(CyclingActivity cyclingActivity, Context context) {
        this.f8624a = cyclingActivity;
        super(context);
    }

    /* renamed from: a */
    public void mo3285a() {
        this.f8624a.f4539a.removeCallbacks(this.f8624a.f4540b);
        for (int i = 0; i < CyclingActivity.d(this.f8624a).size(); i++) {
            ((View) CyclingActivity.d(this.f8624a).get(i)).setBackgroundResource(C1373R.drawable.dot_green);
        }
        CyclingActivity.e(this.f8624a).setText(this.f8624a.getResources().getString(C1373R.string.str_locating_successed));
        CyclingActivity.e(this.f8624a).setTextColor(this.f8624a.getResources().getColor(C1373R.color.location_title_success_color));
    }

    /* renamed from: b */
    public void mo3286b() {
        CyclingActivity.a(this.f8624a, 0);
        this.f8624a.f4539a.postDelayed(this.f8624a.f4540b, 400);
    }
}
