package com.beastbikes.android.modules.cycling.activity.ui;

import com.beastbikes.android.ble.biz.p058c.C1386e;
import com.beastbikes.android.utils.p080a.C1454a;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch.C1706a;

class BleSensorManagerActivity$5 implements C1706a {
    /* renamed from: a */
    final /* synthetic */ BleSensorManagerActivity f8605a;

    BleSensorManagerActivity$5(BleSensorManagerActivity bleSensorManagerActivity) {
        this.f8605a = bleSensorManagerActivity;
    }

    /* renamed from: a */
    public void mo3219a(Switch switchR, boolean z) {
        C1454a.a().a(this.f8605a, "PREF.SENSOR.CADENCE", Boolean.valueOf(z)).apply();
        C1386e.b().a(z);
    }
}
