package com.beastbikes.android.modules.cycling.route.ui;

import android.graphics.Bitmap;
import android.widget.ImageView.ScaleType;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.beastbikes.android.modules.cycling.route.ui.RouteSelfActivity.C1422b;

class RouteSelfActivity$b$1 extends ImageLoader {
    /* renamed from: a */
    final /* synthetic */ C1422b f10343a;

    RouteSelfActivity$b$1(C1422b c1422b, RequestQueue requestQueue, ImageCache imageCache) {
        this.f10343a = c1422b;
        super(requestQueue, imageCache);
    }

    public ImageContainer get(String str, final ImageListener imageListener, int i, int i2) {
        return super.get(str, new ImageListener(this) {
            /* renamed from: b */
            final /* synthetic */ RouteSelfActivity$b$1 f10342b;

            public void onErrorResponse(VolleyError volleyError) {
                C1422b.a(this.f10342b.f10343a).setScaleType(ScaleType.CENTER);
                imageListener.onErrorResponse(volleyError);
            }

            public void onResponse(ImageContainer imageContainer, boolean z) {
                C1422b.a(this.f10342b.f10343a).setScaleType(ScaleType.CENTER_CROP);
                imageListener.onResponse(imageContainer, z);
            }
        }, i, i2);
    }

    protected void onGetImageError(String str, VolleyError volleyError) {
        super.onGetImageError(str, volleyError);
        C1422b.a(this.f10343a).setScaleType(ScaleType.CENTER);
    }

    protected void onGetImageSuccess(String str, Bitmap bitmap) {
        super.onGetImageSuccess(str, bitmap);
        C1422b.a(this.f10343a).setScaleType(ScaleType.CENTER_CROP);
        C1422b.b(this.f10343a).setVisibility(8);
    }
}
