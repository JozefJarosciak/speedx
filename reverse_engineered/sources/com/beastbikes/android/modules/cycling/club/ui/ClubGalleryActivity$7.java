package com.beastbikes.android.modules.cycling.club.ui;

import com.beastbikes.android.C1373R;

class ClubGalleryActivity$7 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ int f9679a;
    /* renamed from: b */
    final /* synthetic */ int f9680b;
    /* renamed from: c */
    final /* synthetic */ ClubGalleryActivity f9681c;

    ClubGalleryActivity$7(ClubGalleryActivity clubGalleryActivity, int i, int i2) {
        this.f9681c = clubGalleryActivity;
        this.f9679a = i;
        this.f9680b = i2;
    }

    public void run() {
        ClubGalleryActivity.n(this.f9681c).setVisibility(0);
        ClubGalleryActivity.o(this.f9681c).setText(this.f9681c.getString(C1373R.string.club_gallery_upload_image_label) + this.f9679a + "/" + this.f9680b);
    }
}
