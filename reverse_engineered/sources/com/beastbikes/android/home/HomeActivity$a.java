package com.beastbikes.android.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.beastbikes.android.modules.cycling.club.ui.widget.MyFrameLayout;

class HomeActivity$a extends BroadcastReceiver {
    /* renamed from: a */
    final /* synthetic */ HomeActivity f8235a;

    HomeActivity$a(HomeActivity homeActivity) {
        this.f8235a = homeActivity;
    }

    public void onReceive(Context context, Intent intent) {
        if (intent != null) {
            if (intent.getAction().equals(MyFrameLayout.f10047a)) {
                boolean booleanExtra = intent.getBooleanExtra(MyFrameLayout.f10048b, false);
                if (HomeActivity.g(this.f8235a) == null) {
                    return;
                }
                if (booleanExtra) {
                    HomeActivity.g(this.f8235a).setVisibility(8);
                } else {
                    HomeActivity.g(this.f8235a).setVisibility(0);
                }
            } else if (TextUtils.equals(intent.getAction(), "action_finish_home_activity")) {
                this.f8235a.finish();
            }
        }
    }
}
