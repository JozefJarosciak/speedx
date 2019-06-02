package com.beastbikes.android.modules.preferences.ui;

import com.beastbikes.android.BeastBikes;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch;
import com.beastbikes.android.widget.materialdesign.mdswitch.Switch.C1706a;

class VoiceFeedbackSettingActivity$1 implements C1706a {
    /* renamed from: a */
    final /* synthetic */ BeastBikes f10999a;
    /* renamed from: b */
    final /* synthetic */ VoiceFeedbackSettingActivity f11000b;

    VoiceFeedbackSettingActivity$1(VoiceFeedbackSettingActivity voiceFeedbackSettingActivity, BeastBikes beastBikes) {
        this.f11000b = voiceFeedbackSettingActivity;
        this.f10999a = beastBikes;
    }

    /* renamed from: a */
    public void mo3219a(Switch switchR, boolean z) {
        this.f10999a.a(Integer.MIN_VALUE, z);
        VoiceFeedbackSettingActivity.a(this.f11000b).setVisibility(z ? 0 : 8);
    }
}
