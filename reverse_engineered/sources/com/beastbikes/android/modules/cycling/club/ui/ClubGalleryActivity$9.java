package com.beastbikes.android.modules.cycling.club.ui;

import java.util.List;

class ClubGalleryActivity$9 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ List f9683a;
    /* renamed from: b */
    final /* synthetic */ ClubGalleryActivity f9684b;

    ClubGalleryActivity$9(ClubGalleryActivity clubGalleryActivity, List list) {
        this.f9684b = clubGalleryActivity;
        this.f9683a = list;
    }

    public void run() {
        if (this.f9683a != null && !this.f9683a.isEmpty()) {
            ClubGalleryActivity.a(this.f9684b).clear();
            ClubGalleryActivity.a(this.f9684b).addAll(this.f9683a);
            ClubGalleryActivity.g(this.f9684b).notifyDataSetChanged();
        }
    }
}
