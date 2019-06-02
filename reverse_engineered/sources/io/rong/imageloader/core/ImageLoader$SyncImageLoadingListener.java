package io.rong.imageloader.core;

import android.graphics.Bitmap;
import android.view.View;
import io.rong.imageloader.core.listener.SimpleImageLoadingListener;

class ImageLoader$SyncImageLoadingListener extends SimpleImageLoadingListener {
    private Bitmap loadedImage;

    private ImageLoader$SyncImageLoadingListener() {
    }

    public void onLoadingComplete(String str, View view, Bitmap bitmap) {
        this.loadedImage = bitmap;
    }

    public Bitmap getLoadedBitmap() {
        return this.loadedImage;
    }
}
