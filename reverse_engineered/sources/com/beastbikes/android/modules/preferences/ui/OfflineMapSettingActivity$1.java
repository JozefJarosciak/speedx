package com.beastbikes.android.modules.preferences.ui;

import android.database.DataSetObserver;

class OfflineMapSettingActivity$1 extends DataSetObserver {
    /* renamed from: a */
    final /* synthetic */ OfflineMapSettingActivity f10936a;

    OfflineMapSettingActivity$1(OfflineMapSettingActivity offlineMapSettingActivity) {
        this.f10936a = offlineMapSettingActivity;
    }

    public void onChanged() {
        if (OfflineMapSettingActivity.a(this.f10936a) != null && OfflineMapSettingActivity.b(this.f10936a) != null) {
            int groupCount = OfflineMapSettingActivity.a(this.f10936a).getGroupCount();
            for (int i = 0; i < groupCount; i++) {
                OfflineMapSettingActivity.b(this.f10936a).expandGroup(i, false);
            }
        }
    }
}
