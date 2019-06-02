package com.beastbikes.android.modules.setting.ui;

import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch.C1706a;

class LaboratoryActivity$3 implements C1706a {
    /* renamed from: a */
    final /* synthetic */ LaboratoryActivity f11059a;

    LaboratoryActivity$3(LaboratoryActivity laboratoryActivity) {
        this.f11059a = laboratoryActivity;
    }

    /* renamed from: a */
    public void mo3219a(Switch switchR, boolean z) {
        if (z) {
            LaboratoryActivity.b(this.f11059a);
            C2580w.m12905a(LaboratoryActivity.a(this.f11059a), "允许前台运行", null);
            return;
        }
        C2580w.m12905a(LaboratoryActivity.a(this.f11059a), "关闭前台运行", null);
    }
}
