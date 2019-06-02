package com.beastbikes.android.ble.ui;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.beastbikes.android.ble.CentralService.C1596c;

class SpeedXHeartRateSettingActivity$4 implements ServiceConnection {
    /* renamed from: a */
    final /* synthetic */ SpeedXHeartRateSettingActivity f7784a;

    SpeedXHeartRateSettingActivity$4(SpeedXHeartRateSettingActivity speedXHeartRateSettingActivity) {
        this.f7784a = speedXHeartRateSettingActivity;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        SpeedXHeartRateSettingActivity.a(this.f7784a, ((C1596c) iBinder).m8563a().m8582b());
    }

    public void onServiceDisconnected(ComponentName componentName) {
    }
}
