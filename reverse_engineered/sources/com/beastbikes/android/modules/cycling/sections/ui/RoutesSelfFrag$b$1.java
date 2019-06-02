package com.beastbikes.android.modules.cycling.sections.ui;

import android.graphics.Bitmap;
import android.widget.ImageView.ScaleType;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.beastbikes.android.modules.cycling.sections.ui.RoutesSelfFrag.C1425b;

class RoutesSelfFrag$b$1 extends ImageLoader {
    /* renamed from: a */
    final /* synthetic */ C1425b f10582a;

    RoutesSelfFrag$b$1(C1425b c1425b, RequestQueue requestQueue, ImageCache imageCache) {
        this.f10582a = c1425b;
        super(requestQueue, imageCache);
    }

    public ImageContainer get(String str, final ImageListener imageListener, int i, int i2) {
        return super.get(str, new ImageListener(this) {
            /* renamed from: b */
            final /* synthetic */ RoutesSelfFrag$b$1 f10581b;

            public void onErrorResponse(VolleyError volleyError) {
                C1425b.a(this.f10581b.f10582a).setScaleType(ScaleType.CENTER);
                imageListener.onErrorResponse(volleyError);
            }

            public void onResponse(ImageContainer imageContainer, boolean z) {
                C1425b.a(this.f10581b.f10582a).setScaleType(ScaleType.CENTER_CROP);
                imageListener.onResponse(imageContainer, z);
            }
        }, i, i2);
    }

    protected void onGetImageError(String str, VolleyError volleyError) {
        super.onGetImageError(str, volleyError);
        C1425b.a(this.f10582a).setScaleType(ScaleType.CENTER);
    }

    protected void onGetImageSuccess(String str, Bitmap bitmap) {
        super.onGetImageSuccess(str, bitmap);
        C1425b.a(this.f10582a).setScaleType(ScaleType.CENTER_CROP);
        C1425b.b(this.f10582a).setVisibility(8);
    }
}
