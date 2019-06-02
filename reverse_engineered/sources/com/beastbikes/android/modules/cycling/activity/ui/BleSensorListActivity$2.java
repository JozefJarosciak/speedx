package com.beastbikes.android.modules.cycling.activity.ui;

import com.beastbikes.android.C1373R;
import com.beastbikes.android.dialog.C1805k.C1804a;
import com.beastbikes.android.utils.p080a.C1454a;

class BleSensorListActivity$2 implements C1804a {
    /* renamed from: a */
    final /* synthetic */ BleSensorListActivity f8591a;

    BleSensorListActivity$2(BleSensorListActivity bleSensorListActivity) {
        this.f8591a = bleSensorListActivity;
    }

    /* renamed from: a */
    public void mo3284a(int i, String str) {
        BleSensorListActivity.a(this.f8591a).setText(str);
        int[] intArray = this.f8591a.getResources().getIntArray(C1373R.array.sensor_speed_wheel_value);
        C1454a.a().a(this.f8591a, "PREF.SENSOR.DIAMETER.LABEL", str).apply();
        C1454a.a().a(this.f8591a, "PREF.SENSOR.DIAMETER.VALUE", Integer.valueOf(intArray[i])).apply();
    }
}
