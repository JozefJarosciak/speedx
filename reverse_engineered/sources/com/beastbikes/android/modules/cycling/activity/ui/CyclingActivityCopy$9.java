package com.beastbikes.android.modules.cycling.activity.ui;

import com.beastbikes.android.modules.cycling.activity.biz.ActivityService.C1909b;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;

class CyclingActivityCopy$9 implements C1909b {
    /* renamed from: a */
    final /* synthetic */ CyclingActivityCopy f8651a;

    CyclingActivityCopy$9(CyclingActivityCopy cyclingActivityCopy) {
        this.f8651a = cyclingActivityCopy;
    }

    /* renamed from: a */
    public void mo3287a(final LocalActivity localActivity) {
        this.f8651a.runOnUiThread(new Runnable(this) {
            /* renamed from: b */
            final /* synthetic */ CyclingActivityCopy$9 f8650b;

            public void run() {
                CyclingActivityCopy.a(this.f8650b.f8651a, localActivity);
            }
        });
    }
}
