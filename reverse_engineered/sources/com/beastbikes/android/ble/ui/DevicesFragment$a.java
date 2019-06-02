package com.beastbikes.android.ble.ui;

import com.beastbikes.android.ble.dao.entity.BleDevice;
import java.util.Comparator;

class DevicesFragment$a implements Comparator<BleDevice> {
    /* renamed from: a */
    final /* synthetic */ DevicesFragment f7571a;

    private DevicesFragment$a(DevicesFragment devicesFragment) {
        this.f7571a = devicesFragment;
    }

    public /* synthetic */ int compare(Object obj, Object obj2) {
        return m9075a((BleDevice) obj, (BleDevice) obj2);
    }

    /* renamed from: a */
    public int m9075a(BleDevice bleDevice, BleDevice bleDevice2) {
        if (bleDevice == null || bleDevice2 == null) {
            return 0;
        }
        return bleDevice.getLastBindTime() < bleDevice2.getLastBindTime() ? 1 : -1;
    }
}
