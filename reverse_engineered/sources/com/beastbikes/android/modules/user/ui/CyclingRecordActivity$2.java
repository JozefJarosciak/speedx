package com.beastbikes.android.modules.user.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.modules.user.dto.ActivityDTO;

class CyclingRecordActivity$2 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ ActivityDTO f11556a;
    /* renamed from: b */
    final /* synthetic */ CyclingRecordActivity f11557b;

    CyclingRecordActivity$2(CyclingRecordActivity cyclingRecordActivity, ActivityDTO activityDTO) {
        this.f11557b = cyclingRecordActivity;
        this.f11556a = activityDTO;
    }

    public void onClick(View view) {
        CyclingRecordActivity.c(this.f11557b).m13069b();
        CyclingRecordActivity.a(this.f11557b, this.f11556a);
    }
}
