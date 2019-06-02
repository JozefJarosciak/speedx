package com.beastbikes.android.modules.cycling.activity.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;

final class CyclingActivityCopy$a extends BroadcastReceiver {
    /* renamed from: a */
    final /* synthetic */ CyclingActivityCopy f8652a;

    CyclingActivityCopy$a(CyclingActivityCopy cyclingActivityCopy) {
        this.f8652a = cyclingActivityCopy;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (action != null) {
                LocalActivity localActivity = (LocalActivity) intent.getSerializableExtra("activity");
                if (localActivity == null) {
                    return;
                }
                if ("com.beastbikes.intent.action.ACTIVITY_COMPLETE".equals(action) || "com.beastbikes.intent.action.ACTIVITY_PAUSE".equals(action) || "com.beastbikes.intent.action.ACTIVITY_AUTO_PAUSE".equals(action) || "com.beastbikes.intent.action.ACTIVITY_RESUME".equals(action)) {
                    CyclingActivityCopy.b(this.f8652a, localActivity.getState());
                } else if ("com.beastbikes.intent.action.ACTIVITY_START".equals(action)) {
                    CyclingActivityCopy.f(this.f8652a);
                }
            }
        }
    }
}
