package com.beastbikes.android.modules.cycling.activity.ui;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.beastbikes.android.modules.cycling.activity.biz.ActivityService.C1908a;

class CyclingActivityCopy$8 implements ServiceConnection {
    /* renamed from: a */
    final /* synthetic */ CyclingActivityCopy f8648a;

    CyclingActivityCopy$8(CyclingActivityCopy cyclingActivityCopy) {
        this.f8648a = cyclingActivityCopy;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        ((C1908a) iBinder).m9816a().m9879a(CyclingActivityCopy.g(this.f8648a));
    }

    public void onServiceDisconnected(ComponentName componentName) {
    }
}
