package com.beastbikes.android.modules.user.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.beastbikes.android.modules.user.dto.C2414c;
import com.beastbikes.android.modules.user.filter.p155b.C2441a;
import com.squareup.picasso.Picasso$LoadedFrom;
import com.squareup.picasso.Target;

class WatermarkGalleryActivity$3 implements Target {
    /* renamed from: a */
    final /* synthetic */ C2414c f11811a;
    /* renamed from: b */
    final /* synthetic */ C2441a f11812b;
    /* renamed from: c */
    final /* synthetic */ WatermarkGalleryActivity f11813c;

    WatermarkGalleryActivity$3(WatermarkGalleryActivity watermarkGalleryActivity, C2414c c2414c, C2441a c2441a) {
        this.f11813c = watermarkGalleryActivity;
        this.f11811a = c2414c;
        this.f11812b = c2441a;
    }

    public void onBitmapLoaded(Bitmap bitmap, Picasso$LoadedFrom picasso$LoadedFrom) {
        this.f11811a.m12261a(bitmap);
        this.f11813c.b(this.f11811a, this.f11812b);
    }

    public void onBitmapFailed(Drawable drawable) {
    }

    public void onPrepareLoad(Drawable drawable) {
    }
}
