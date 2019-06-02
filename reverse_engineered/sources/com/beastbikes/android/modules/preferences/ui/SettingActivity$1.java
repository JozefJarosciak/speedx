package com.beastbikes.android.modules.preferences.ui;

import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.utils.C2580w;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch.C1706a;

class SettingActivity$1 implements C1706a {
    /* renamed from: a */
    final /* synthetic */ BeastBikes f10957a;
    /* renamed from: b */
    final /* synthetic */ SettingActivity f10958b;

    SettingActivity$1(SettingActivity settingActivity, BeastBikes beastBikes) {
        this.f10958b = settingActivity;
        this.f10957a = beastBikes;
    }

    /* renamed from: a */
    public void mo3219a(Switch switchR, boolean z) {
        this.f10957a.c(z);
        if (z) {
            C2580w.m12905a(this.f10957a, "地图样式优化开启", null);
        } else {
            C2580w.m12905a(this.f10957a, "地图样式优化关闭", null);
        }
    }
}
