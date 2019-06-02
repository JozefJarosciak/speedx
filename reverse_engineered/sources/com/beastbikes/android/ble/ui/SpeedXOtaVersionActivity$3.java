package com.beastbikes.android.ble.ui;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.beastbikes.android.ble.CentralService.C1596c;

class SpeedXOtaVersionActivity$3 implements ServiceConnection {
    /* renamed from: a */
    final /* synthetic */ SpeedXOtaVersionActivity f7792a;

    SpeedXOtaVersionActivity$3(SpeedXOtaVersionActivity speedXOtaVersionActivity) {
        this.f7792a = speedXOtaVersionActivity;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        SpeedXOtaVersionActivity.a().info("onServiceConnected");
        SpeedXOtaVersionActivity.a(this.f7792a, ((C1596c) iBinder).m8563a().m8582b());
        SpeedXOtaVersionActivity.d(this.f7792a).mo3148a(this.f7792a);
        if (SpeedXOtaVersionActivity.h(this.f7792a) != null) {
            SpeedXOtaVersionActivity.a(this.f7792a, SpeedXOtaVersionActivity.h(this.f7792a).getMcuVersion());
        }
    }

    public void onServiceDisconnected(ComponentName componentName) {
        SpeedXOtaVersionActivity.a().info("onServiceDisconnected");
    }
}
