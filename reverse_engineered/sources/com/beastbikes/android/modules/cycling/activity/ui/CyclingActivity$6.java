package com.beastbikes.android.modules.cycling.activity.ui;

import com.beastbikes.android.modules.cycling.activity.biz.ActivityService.C1909b;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;

class CyclingActivity$6 implements C1909b {
    /* renamed from: a */
    final /* synthetic */ CyclingActivity f8628a;

    CyclingActivity$6(CyclingActivity cyclingActivity) {
        this.f8628a = cyclingActivity;
    }

    /* renamed from: a */
    public void mo3287a(final LocalActivity localActivity) {
        this.f8628a.runOnUiThread(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ CyclingActivity$6 f8627b;

            public void run() {
                CyclingActivity.a(this.f8627b.f8628a, localActivity);
            }
        });
    }
}
