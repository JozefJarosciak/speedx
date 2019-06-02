package com.beastbikes.android.modules.preferences.ui.offlineMap;

class OfflineMapActivity$2 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ boolean f11016a;
    /* renamed from: b */
    final /* synthetic */ OfflineMapActivity f11017b;

    OfflineMapActivity$2(OfflineMapActivity offlineMapActivity, boolean z) {
        this.f11017b = offlineMapActivity;
        this.f11016a = z;
    }

    public void run() {
        OfflineMapActivity.j(this.f11017b).setVisibility(this.f11016a ? 0 : 8);
        OfflineMapActivity.k(this.f11017b).mo3478a(OfflineMapActivity.f(this.f11017b));
        OfflineMapActivity.k(this.f11017b).notifyDataSetChanged();
        OfflineMapActivity.h(this.f11017b).mo3478a(OfflineMapActivity.c(this.f11017b));
        OfflineMapActivity.h(this.f11017b).notifyDataSetChanged();
        OfflineMapActivity.i(this.f11017b).m11855a(OfflineMapActivity.e(this.f11017b), OfflineMapActivity.d(this.f11017b));
        OfflineMapActivity.i(this.f11017b).notifyDataSetChanged();
    }
}
