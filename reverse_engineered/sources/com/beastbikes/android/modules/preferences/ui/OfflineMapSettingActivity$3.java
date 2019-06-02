package com.beastbikes.android.modules.preferences.ui;

import android.view.View;
import android.view.View.OnClickListener;
import com.beastbikes.android.C1373R;
import com.beastbikes.android.widget.C2621c;
import com.beastbikes.framework.ui.android.utils.Toasts;

class OfflineMapSettingActivity$3 implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ C2621c f10939a;
    /* renamed from: b */
    final /* synthetic */ int[] f10940b;
    /* renamed from: c */
    final /* synthetic */ OfflineMapSettingActivity$f f10941c;
    /* renamed from: d */
    final /* synthetic */ OfflineMapSettingActivity f10942d;

    OfflineMapSettingActivity$3(OfflineMapSettingActivity offlineMapSettingActivity, C2621c c2621c, int[] iArr, OfflineMapSettingActivity$f offlineMapSettingActivity$f) {
        this.f10942d = offlineMapSettingActivity;
        this.f10939a = c2621c;
        this.f10940b = iArr;
        this.f10941c = offlineMapSettingActivity$f;
    }

    public void onClick(View view) {
        this.f10939a.m13069b();
        for (int start : this.f10940b) {
            OfflineMapSettingActivity.c(this.f10942d).start(start);
        }
        Toasts.show(this.f10942d, (int) C1373R.string.offline_map_setting_activity_toast_start_downloading);
        this.f10941c.f10946a = this.f10942d.getString(C1373R.string.offline_map_setting_activity_downloading);
    }
}
