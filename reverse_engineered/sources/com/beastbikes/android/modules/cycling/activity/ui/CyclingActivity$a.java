package com.beastbikes.android.modules.cycling.activity.ui;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.beastbikes.android.authentication.AVUser;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;

final class CyclingActivity$a extends BroadcastReceiver {
    /* renamed from: a */
    final /* synthetic */ CyclingActivity f8636a;

    CyclingActivity$a(CyclingActivity cyclingActivity) {
        this.f8636a = cyclingActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (action != null) {
                LocalActivity localActivity = (LocalActivity) intent.getSerializableExtra("activity");
                if (localActivity != null) {
                    if ("com.beastbikes.intent.action.ACTIVITY_COMPLETE".equals(action) && localActivity.getCourseId() > 0) {
                        AVUser currentUser = AVUser.getCurrentUser();
                        if (currentUser != null && currentUser.getTrainingType() == 1) {
                            if (localActivity.getTotalDistance() <= 10.0d) {
                                CyclingActivity.b(this.f8636a, currentUser.getTrainingId());
                            }
                            currentUser.setTrainingId(0);
                            currentUser.setTrainingType(0);
                            AVUser.saveCurrentUser(currentUser);
                        }
                    }
                    if ("com.beastbikes.intent.action.ACTIVITY_COMPLETE".equals(action) || "com.beastbikes.intent.action.ACTIVITY_PAUSE".equals(action) || "com.beastbikes.intent.action.ACTIVITY_AUTO_PAUSE".equals(action) || "com.beastbikes.intent.action.ACTIVITY_RESUME".equals(action)) {
                        CyclingActivity.c(this.f8636a, localActivity.getState());
                    } else if ("com.beastbikes.intent.action.ACTIVITY_START".equals(action)) {
                        CyclingActivity.g(this.f8636a);
                    }
                }
            }
        }
    }
}
