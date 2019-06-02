package com.beastbikes.android.ble.ui;

import com.beastbikes.android.ble.dao.entity.BleDevice;
import java.util.Comparator;

class SpeedXDevicesActivity$a implements Comparator<BleDevice> {
    /* renamed from: a */
    final /* synthetic */ SpeedXDevicesActivity f7720a;

    private SpeedXDevicesActivity$a(SpeedXDevicesActivity speedXDevicesActivity) {
        this.f7720a = speedXDevicesActivity;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m9197a((BleDevice) obj, (BleDevice) obj2);
    }

    /* renamed from: a */
    public int m9197a(BleDevice bleDevice, BleDevice bleDevice2) {
        if (bleDevice == null || bleDevice2 == null) {
            return 0;
        }
        return bleDevice.getLastBindTime() < bleDevice2.getLastBindTime() ? 1 : -1;
    }
}
