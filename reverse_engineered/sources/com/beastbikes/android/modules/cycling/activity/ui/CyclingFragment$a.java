package com.beastbikes.android.modules.cycling.activity.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;

final class CyclingFragment$a extends BroadcastReceiver {
    /* renamed from: a */
    final /* synthetic */ CyclingFragment f8663a;

    private CyclingFragment$a(CyclingFragment cyclingFragment) {
        this.f8663a = cyclingFragment;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if ("action.target.distance".equals(action)) {
                CyclingFragment.e(this.f8663a);
                CyclingFragment.c(this.f8663a);
            }
            if (action != null) {
                LocalActivity localActivity = (LocalActivity) intent.getSerializableExtra("activity");
                if (localActivity != null && "com.beastbikes.intent.action.ACTIVITY_COMPLETE".equals(action) && localActivity.getTotalDistance() > 10.0d) {
                    CyclingFragment.e(this.f8663a);
                    CyclingFragment.c(this.f8663a);
                }
            }
        }
    }
}
