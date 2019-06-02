package com.beastbikes.android.modules.cycling.activity.ui;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.beastbikes.android.modules.cycling.activity.biz.ActivityService.C1908a;

class CyclingActivity$5 implements ServiceConnection {
    /* renamed from: a */
    final /* synthetic */ CyclingActivity f8625a;

    CyclingActivity$5(CyclingActivity cyclingActivity) {
        this.f8625a = cyclingActivity;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        ((C1908a) iBinder).m9816a().m9879a(CyclingActivity.h(this.f8625a));
    }

    public void onServiceDisconnected(ComponentName componentName) {
    }
}
