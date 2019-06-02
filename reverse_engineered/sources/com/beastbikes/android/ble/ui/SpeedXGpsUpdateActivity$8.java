package com.beastbikes.android.ble.ui;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.beastbikes.android.ble.CentralService.C1596c;

class SpeedXGpsUpdateActivity$8 implements ServiceConnection {
    /* renamed from: a */
    final /* synthetic */ SpeedXGpsUpdateActivity f7778a;

    SpeedXGpsUpdateActivity$8(SpeedXGpsUpdateActivity speedXGpsUpdateActivity) {
        this.f7778a = speedXGpsUpdateActivity;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        SpeedXGpsUpdateActivity.e(this.f7778a).info("onServiceConnected");
        SpeedXGpsUpdateActivity.a(this.f7778a, ((C1596c) iBinder).m8563a().m8582b());
    }

    public void onServiceDisconnected(ComponentName componentName) {
        SpeedXGpsUpdateActivity.e(this.f7778a).info("onServiceDisconnected");
    }
}
