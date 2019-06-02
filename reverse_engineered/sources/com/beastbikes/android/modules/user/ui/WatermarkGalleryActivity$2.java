package com.beastbikes.android.modules.user.ui;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import com.beastbikes.android.modules.user.dto.C2414c;
import com.beastbikes.android.modules.user.filter.p155b.C2441a;
import com.squareup.picasso.Picasso$LoadedFrom;
import com.squareup.picasso.Target;

class WatermarkGalleryActivity$2 implements Target {
    /* renamed from: a */
    final /* synthetic */ C2414c f11808a;
    /* renamed from: b */
    final /* synthetic */ C2441a f11809b;
    /* renamed from: c */
    final /* synthetic */ WatermarkGalleryActivity f11810c;

    WatermarkGalleryActivity$2(WatermarkGalleryActivity watermarkGalleryActivity, C2414c c2414c, C2441a c2441a) {
        this.f11810c = watermarkGalleryActivity;
        this.f11808a = c2414c;
        this.f11809b = c2441a;
    }

    public void onBitmapLoaded(Bitmap bitmap, Picasso$LoadedFrom picasso$LoadedFrom) {
        this.f11808a.m12263b(bitmap);
        this.f11810c.b(this.f11808a, this.f11809b);
    }

    public void onBitmapFailed(Drawable drawable) {
    }

    public void onPrepareLoad(Drawable drawable) {
    }
}
