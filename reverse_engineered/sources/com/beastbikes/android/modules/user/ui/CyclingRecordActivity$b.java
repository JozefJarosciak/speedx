package com.beastbikes.android.modules.user.ui;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build.VERSION;
import android.os.IBinder;
import android.text.TextUtils;
import com.beastbikes.android.ble.CentralService.C1596c;

class CyclingRecordActivity$b implements ServiceConnection {
    /* renamed from: a */
    final /* synthetic */ CyclingRecordActivity f11563a;

    CyclingRecordActivity$b(CyclingRecordActivity cyclingRecordActivity) {
        this.f11563a = cyclingRecordActivity;
    }

    public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        CyclingRecordActivity.j().info("onServiceConnected");
        CyclingRecordActivity.a(this.f11563a, ((C1596c) iBinder).m8563a().m8582b());
        CyclingRecordActivity.h(this.f11563a).mo3145a(this.f11563a);
    }

    public void onServiceDisconnected(ComponentName componentName) {
        CyclingRecordActivity.j().info("onServiceDisconnected");
    }

    /* renamed from: a */
    private void m12379a() {
        if (VERSION.SDK_INT >= 18 && !TextUtils.isEmpty(CyclingRecordActivity.i(this.f11563a))) {
            Intent intent = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
            intent.setPackage(this.f11563a.getPackageName());
            this.f11563a.bindService(intent, this, 1);
        }
    }

    /* renamed from: b */
    private void m12381b() {
        if (VERSION.SDK_INT >= 18) {
            this.f11563a.unbindService(this);
        }
    }
}
