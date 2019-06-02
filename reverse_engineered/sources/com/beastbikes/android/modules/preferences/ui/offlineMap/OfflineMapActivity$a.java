package com.beastbikes.android.modules.preferences.ui.offlineMap;

import android.view.View;
import android.view.View.OnClickListener;

class OfflineMapActivity$a implements OnClickListener {
    /* renamed from: a */
    final /* synthetic */ OfflineMapActivity f11018a;
    /* renamed from: b */
    private int f11019b = 0;

    public OfflineMapActivity$a(OfflineMapActivity offlineMapActivity, int i) {
        this.f11018a = offlineMapActivity;
        this.f11019b = i;
    }

    public void onClick(View view) {
        OfflineMapActivity.r(this.f11018a).setCurrentItem(this.f11019b);
    }
}
