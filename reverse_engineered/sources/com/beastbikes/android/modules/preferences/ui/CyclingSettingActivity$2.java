package com.beastbikes.android.modules.preferences.ui;

import android.content.Intent;
import com.alipay.sdk.packet.C0861d;
import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.modules.cycling.activity.biz.C1398a;
import com.beastbikes.android.modules.cycling.activity.dao.entity.LocalActivity;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch.C1706a;

class CyclingSettingActivity$2 implements C1706a {
    /* renamed from: a */
    final /* synthetic */ C1398a f10928a;
    /* renamed from: b */
    final /* synthetic */ CyclingSettingActivity f10929b;

    CyclingSettingActivity$2(CyclingSettingActivity cyclingSettingActivity, C1398a c1398a) {
        this.f10929b = cyclingSettingActivity;
        this.f10928a = c1398a;
    }

    /* renamed from: a */
    public void mo3219a(Switch switchR, boolean z) {
        ((BeastBikes) this.f10929b.getApplication()).a(z);
        if (!z) {
            LocalActivity a = this.f10928a.a();
            if (a != null && a.getState() == 3) {
                Intent intent = new Intent("com.beastbikes.intent.action.ACTIVITY_MANAGER");
                intent.addCategory("android.intent.category.DEFAULT");
                intent.putExtra(C0861d.f2143o, "com.beastbikes.intent.action.ACTIVITY_RESUME");
                intent.setPackage(this.f10929b.getPackageName());
                this.f10929b.startService(intent);
            }
        }
    }
}
