package io.rong.imageloader.core;

import io.rong.imageloader.core.assist.ImageSize;
import io.rong.imageloader.core.imageaware.ImageAware;
import io.rong.imageloader.core.listener.ImageLoadingListener;
import io.rong.imageloader.core.listener.ImageLoadingProgressListener;
import java.util.concurrent.locks.ReentrantLock;

final class ImageLoadingInfo {
    final ImageAware imageAware;
    final ImageLoadingListener listener;
    final ReentrantLock loadFromUriLock;
    final String memoryCacheKey;
    final DisplayImageOptions options;
    final ImageLoadingProgressListener progressListener;
    final ImageSize targetSize;
    final String uri;

    public ImageLoadingInfo(String str, ImageAware imageAware, ImageSize imageSize, String str2, DisplayImageOptions displayImageOptions, ImageLoadingListener imageLoadingListener, ImageLoadingProgressListener imageLoadingProgressListener, ReentrantLock reentrantLock) {
        this.uri = str;
        this.imageAware = imageAware;
        this.targetSize = imageSize;
        this.options = displayImageOptions;
        this.listener = imageLoadingListener;
        this.progressListener = imageLoadingProgressListener;
        this.loadFromUriLock = reentrantLock;
        this.memoryCacheKey = str2;
    }
}
