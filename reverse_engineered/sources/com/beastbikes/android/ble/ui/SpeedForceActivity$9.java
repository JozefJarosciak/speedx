package com.beastbikes.android.ble.ui;

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import ch.qos.logback.core.spi.AbstractComponentTracker;
import com.beastbikes.android.ble.biz.C1661h;
import com.beastbikes.android.ble.biz.p096a.C1614a;

class SpeedForceActivity$9 extends BroadcastReceiver {
    /* renamed from: a */
    final /* synthetic */ SpeedForceActivity f7711a;

    SpeedForceActivity$9(SpeedForceActivity speedForceActivity) {
        this.f7711a = speedForceActivity;
    }

    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if ("com.beastbikes.android.ble.connected.action".equals(action)) {
            SpeedForceActivity.p(this.f7711a).info("BroadcastReceiver 已连接");
            C1614a b = C1661h.m8999a().m9004b();
            if (b != null) {
                SpeedForceActivity.q(this.f7711a);
                SpeedForceActivity.r(this.f7711a).setText(b.m8743h());
                if (SpeedForceActivity.e(this.f7711a) != null && SpeedForceActivity.e(this.f7711a).isShowing()) {
                    SpeedForceActivity.e(this.f7711a).dismiss();
                }
                SpeedForceActivity.s(this.f7711a);
            }
        } else if ("com.beastbikes.android.ble.disconnected.action".equals(action)) {
            SpeedForceActivity.p(this.f7711a).info("BroadcastReceiver 已断开");
            SpeedForceActivity.t(this.f7711a);
            SpeedForceActivity.a(this.f7711a, -1);
        } else {
            Intent intent2;
            if ("android.bluetooth.adapter.action.STATE_CHANGED".equals(action)) {
                switch (intent.getIntExtra("android.bluetooth.adapter.extra.STATE", 10)) {
                    case 10:
                        SpeedForceActivity.p(this.f7711a).info("蓝牙关闭");
                        break;
                    case 12:
                        SpeedForceActivity.p(this.f7711a).info("中控首页－－蓝牙打开");
                        intent2 = new Intent("com.beastbikes.android.ble.intent.action.CENTRAL_CONTROL");
                        intent2.setPackage(this.f7711a.getPackageName());
                        this.f7711a.startService(intent2);
                        break;
                }
            }
            if ("android.bluetooth.device.action.BOND_STATE_CHANGED".equals(action)) {
                BluetoothDevice bluetoothDevice = (BluetoothDevice) intent.getParcelableExtra("android.bluetooth.device.extra.DEVICE");
                if (bluetoothDevice != null) {
                    switch (bluetoothDevice.getBondState()) {
                        case 10:
                            SpeedForceActivity.p(this.f7711a).info("取消配对");
                            break;
                        case 11:
                            SpeedForceActivity.p(this.f7711a).info("正在配对......");
                            SpeedForceActivity.u(this.f7711a).sendEmptyMessageDelayed(1, AbstractComponentTracker.LINGERING_TIMEOUT);
                            break;
                        case 12:
                            SpeedForceActivity.c(this.f7711a, true);
                            SpeedForceActivity.p(this.f7711a).info("完成配对");
                            break;
                    }
                }
                return;
            }
            if ("com.beastbikes.android.connect.no.token".equals(action)) {
                intent2 = new Intent(this.f7711a, SpeedXTrainingTargetActivity.class);
                intent2.putExtra("show_menu", true);
                intent2.putExtra("from_speedx_force", true);
                this.f7711a.startActivity(intent2);
            }
        }
    }
}
