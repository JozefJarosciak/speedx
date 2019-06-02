package com.beastbikes.android.modules.cycling.activity.ui;

import com.beastbikes.android.ble.biz.p058c.C1386e;
import com.beastbikes.android.utils.p080a.C1454a;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch.C1706a;

class BleSensorManagerActivity$4 implements C1706a {
    /* renamed from: a */
    final /* synthetic */ BleSensorManagerActivity f8604a;

    BleSensorManagerActivity$4(BleSensorManagerActivity bleSensorManagerActivity) {
        this.f8604a = bleSensorManagerActivity;
    }

    /* renamed from: a */
    public void mo3219a(Switch switchR, boolean z) {
        C1454a.a().a(this.f8604a, "PREF.SENSOR.SPEED", Boolean.valueOf(z)).apply();
        C1386e.b().a(z);
    }
}
