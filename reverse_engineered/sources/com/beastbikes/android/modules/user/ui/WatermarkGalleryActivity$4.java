package com.beastbikes.android.modules.user.ui;

import com.beastbikes.android.modules.user.dto.C2414c;
import com.beastbikes.android.modules.user.filter.p155b.C2441a;

class WatermarkGalleryActivity$4 implements Runnable {
    /* renamed from: a */
    final /* synthetic */ C2441a f11814a;
    /* renamed from: b */
    final /* synthetic */ C2414c f11815b;
    /* renamed from: c */
    final /* synthetic */ WatermarkGalleryActivity f11816c;

    WatermarkGalleryActivity$4(WatermarkGalleryActivity watermarkGalleryActivity, C2441a c2441a, C2414c c2414c) {
        this.f11816c = watermarkGalleryActivity;
        this.f11814a = c2441a;
        this.f11815b = c2414c;
    }

    public void run() {
        this.f11814a.m12308a(this.f11815b);
    }
}
