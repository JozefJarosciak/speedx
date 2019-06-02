package com.beastbikes.framework.ui.android.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html.ImageGetter;
import android.view.View;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageLoader.ImageCache;
import com.android.volley.toolbox.ImageLoader.ImageContainer;
import com.android.volley.toolbox.ImageLoader.ImageListener;
import com.beastbikes.framework.android.p176b.C2790a;

public class HtmlImageGetter implements ImageGetter, ImageListener {
    private final RequestQueue requestQueue;
    private final View view;

    public HtmlImageGetter(RequestQueue requestQueue, View view) {
        this.requestQueue = requestQueue;
        this.view = view;
    }

    public Drawable getDrawable(String str) {
        ImageCache a = C2790a.m13728a();
        Resources resources = this.view.getResources();
        Bitmap bitmap = new ImageLoader(this.requestQueue, a) {
            protected void onGetImageSuccess(String str, Bitmap bitmap) {
                super.onGetImageSuccess(str, bitmap);
                HtmlImageGetter.this.view.requestLayout();
            }
        }.get(str, this).getBitmap();
        if (bitmap == null) {
            return null;
        }
        Drawable bitmapDrawable = new BitmapDrawable(resources, bitmap);
        bitmapDrawable.setBounds(0, 0, bitmap.getWidth(), bitmap.getHeight());
        return bitmapDrawable;
    }

    public void onErrorResponse(VolleyError volleyError) {
    }

    public void onResponse(ImageContainer imageContainer, boolean z) {
    }
}
