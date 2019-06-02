package com.beastbikes.android.modules.cycling.club.ui;

import android.graphics.Bitmap;
import android.widget.ImageView.ScaleType;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.beastbikes.android.modules.cycling.club.ui.ClubActRouteSelfActivity.C1406b;

class ClubActRouteSelfActivity$b$1 extends ImageLoader {
    /* renamed from: a */
    final /* synthetic */ C1406b f9438a;

    ClubActRouteSelfActivity$b$1(C1406b c1406b, RequestQueue requestQueue, ImageCache imageCache) {
        this.f9438a = c1406b;
        super(requestQueue, imageCache);
    }

    public ImageContainer get(String str, final ImageListener imageListener, int i, int i2) {
        return super.get(str, new ImageListener(this) {
            /* renamed from: b */
            final /* synthetic */ ClubActRouteSelfActivity$b$1 f9437b;

            public void onErrorResponse(VolleyError volleyError) {
                C1406b.a(this.f9437b.f9438a).setScaleType(ScaleType.CENTER);
                imageListener.onErrorResponse(volleyError);
            }

            public void onResponse(ImageContainer imageContainer, boolean z) {
                C1406b.a(this.f9437b.f9438a).setScaleType(ScaleType.CENTER_CROP);
                imageListener.onResponse(imageContainer, z);
            }
        }, i, i2);
    }

    protected void onGetImageError(String str, VolleyError volleyError) {
        super.onGetImageError(str, volleyError);
        C1406b.a(this.f9438a).setScaleType(ScaleType.CENTER);
    }

    protected void onGetImageSuccess(String str, Bitmap bitmap) {
        super.onGetImageSuccess(str, bitmap);
        C1406b.a(this.f9438a).setScaleType(ScaleType.CENTER_CROP);
        C1406b.b(this.f9438a).setVisibility(8);
    }
}
