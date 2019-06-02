package io.rong.imageloader.core;

import android.graphics.Bitmap;
import io.rong.imageloader.core.assist.LoadedFrom;
import io.rong.imageloader.core.display.BitmapDisplayer;
import io.rong.imageloader.core.imageaware.ImageAware;
import io.rong.imageloader.core.listener.ImageLoadingListener;
import io.rong.imageloader.utils.C1523L;

final class DisplayBitmapTask implements Runnable {
    private static final String LOG_DISPLAY_IMAGE_IN_IMAGEAWARE = "Display image in ImageAware (loaded from %1$s) [%2$s]";
    private static final String LOG_TASK_CANCELLED_IMAGEAWARE_COLLECTED = "ImageAware was collected by GC. Task is cancelled. [%s]";
    private static final String LOG_TASK_CANCELLED_IMAGEAWARE_REUSED = "ImageAware is reused for another image. Task is cancelled. [%s]";
    private final Bitmap bitmap;
    private final BitmapDisplayer displayer;
    private final ImageLoaderEngine engine;
    private final ImageAware imageAware;
    private final String imageUri;
    private final ImageLoadingListener listener;
    private final LoadedFrom loadedFrom;
    private final String memoryCacheKey;

    public DisplayBitmapTask(Bitmap bitmap, ImageLoadingInfo imageLoadingInfo, ImageLoaderEngine imageLoaderEngine, LoadedFrom loadedFrom) {
        this.bitmap = bitmap;
        this.imageUri = imageLoadingInfo.uri;
        this.imageAware = imageLoadingInfo.imageAware;
        this.memoryCacheKey = imageLoadingInfo.memoryCacheKey;
        this.displayer = imageLoadingInfo.options.getDisplayer();
        this.listener = imageLoadingInfo.listener;
        this.engine = imageLoaderEngine;
        this.loadedFrom = loadedFrom;
    }

    public void run() {
        if (this.imageAware.isCollected()) {
            C1523L.d(LOG_TASK_CANCELLED_IMAGEAWARE_COLLECTED, new Object[]{this.memoryCacheKey});
            this.listener.onLoadingCancelled(this.imageUri, this.imageAware.getWrappedView());
        } else if (isViewWasReused()) {
            C1523L.d(LOG_TASK_CANCELLED_IMAGEAWARE_REUSED, new Object[]{this.memoryCacheKey});
            this.listener.onLoadingCancelled(this.imageUri, this.imageAware.getWrappedView());
        } else {
            C1523L.d(LOG_DISPLAY_IMAGE_IN_IMAGEAWARE, new Object[]{this.loadedFrom, this.memoryCacheKey});
            this.displayer.display(this.bitmap, this.imageAware, this.loadedFrom);
            this.engine.cancelDisplayTaskFor(this.imageAware);
            this.listener.onLoadingComplete(this.imageUri, this.imageAware.getWrappedView(), this.bitmap);
        }
    }

    private boolean isViewWasReused() {
        return !this.memoryCacheKey.equals(this.engine.getLoadingUriForView(this.imageAware));
    }
}
