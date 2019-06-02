package io.rong.imageloader.core;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import io.rong.imageloader.cache.disc.DiskCache;
import io.rong.imageloader.cache.memory.MemoryCache;
import io.rong.imageloader.core.assist.FlushedInputStream;
import io.rong.imageloader.core.assist.ImageSize;
import io.rong.imageloader.core.assist.QueueProcessingType;
import io.rong.imageloader.core.decode.ImageDecoder;
import io.rong.imageloader.core.download.ImageDownloader;
import io.rong.imageloader.core.download.ImageDownloader.Scheme;
import io.rong.imageloader.core.process.BitmapProcessor;
import io.rong.imageloader.utils.C1523L;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.Executor;

public final class ImageLoaderConfiguration {
    final boolean customExecutor;
    final boolean customExecutorForCachedImages;
    final ImageDecoder decoder;
    final DisplayImageOptions defaultDisplayImageOptions;
    final DiskCache diskCache;
    final ImageDownloader downloader;
    final int maxImageHeightForDiskCache;
    final int maxImageHeightForMemoryCache;
    final int maxImageWidthForDiskCache;
    final int maxImageWidthForMemoryCache;
    final MemoryCache memoryCache;
    final ImageDownloader networkDeniedDownloader;
    final BitmapProcessor processorForDiskCache;
    final Resources resources;
    final ImageDownloader slowNetworkDownloader;
    final Executor taskExecutor;
    final Executor taskExecutorForCachedImages;
    final QueueProcessingType tasksProcessingType;
    final int threadPoolSize;
    final int threadPriority;

    private static class NetworkDeniedImageDownloader implements ImageDownloader {
        private final ImageDownloader wrappedDownloader;

        public NetworkDeniedImageDownloader(ImageDownloader imageDownloader) {
            this.wrappedDownloader = imageDownloader;
        }

        public InputStream getStream(String str, Object obj) throws IOException {
            switch (Scheme.ofUri(str)) {
                case HTTP:
                case HTTPS:
                    throw new IllegalStateException();
                default:
                    return this.wrappedDownloader.getStream(str, obj);
            }
        }
    }

    private static class SlowNetworkImageDownloader implements ImageDownloader {
        private final ImageDownloader wrappedDownloader;

        public SlowNetworkImageDownloader(ImageDownloader imageDownloader) {
            this.wrappedDownloader = imageDownloader;
        }

        public InputStream getStream(String str, Object obj) throws IOException {
            InputStream stream = this.wrappedDownloader.getStream(str, obj);
            switch (Scheme.ofUri(str)) {
                case HTTP:
                case HTTPS:
                    return new FlushedInputStream(stream);
                default:
                    return stream;
            }
        }
    }

    private ImageLoaderConfiguration(ImageLoaderConfiguration$Builder imageLoaderConfiguration$Builder) {
        this.resources = ImageLoaderConfiguration$Builder.access$000(imageLoaderConfiguration$Builder).getResources();
        this.maxImageWidthForMemoryCache = ImageLoaderConfiguration$Builder.access$100(imageLoaderConfiguration$Builder);
        this.maxImageHeightForMemoryCache = ImageLoaderConfiguration$Builder.access$200(imageLoaderConfiguration$Builder);
        this.maxImageWidthForDiskCache = ImageLoaderConfiguration$Builder.access$300(imageLoaderConfiguration$Builder);
        this.maxImageHeightForDiskCache = ImageLoaderConfiguration$Builder.access$400(imageLoaderConfiguration$Builder);
        this.processorForDiskCache = ImageLoaderConfiguration$Builder.access$500(imageLoaderConfiguration$Builder);
        this.taskExecutor = ImageLoaderConfiguration$Builder.access$600(imageLoaderConfiguration$Builder);
        this.taskExecutorForCachedImages = ImageLoaderConfiguration$Builder.access$700(imageLoaderConfiguration$Builder);
        this.threadPoolSize = ImageLoaderConfiguration$Builder.access$800(imageLoaderConfiguration$Builder);
        this.threadPriority = ImageLoaderConfiguration$Builder.access$900(imageLoaderConfiguration$Builder);
        this.tasksProcessingType = ImageLoaderConfiguration$Builder.access$1000(imageLoaderConfiguration$Builder);
        this.diskCache = ImageLoaderConfiguration$Builder.access$1100(imageLoaderConfiguration$Builder);
        this.memoryCache = ImageLoaderConfiguration$Builder.access$1200(imageLoaderConfiguration$Builder);
        this.defaultDisplayImageOptions = ImageLoaderConfiguration$Builder.access$1300(imageLoaderConfiguration$Builder);
        this.downloader = ImageLoaderConfiguration$Builder.access$1400(imageLoaderConfiguration$Builder);
        this.decoder = ImageLoaderConfiguration$Builder.access$1500(imageLoaderConfiguration$Builder);
        this.customExecutor = ImageLoaderConfiguration$Builder.access$1600(imageLoaderConfiguration$Builder);
        this.customExecutorForCachedImages = ImageLoaderConfiguration$Builder.access$1700(imageLoaderConfiguration$Builder);
        this.networkDeniedDownloader = new NetworkDeniedImageDownloader(this.downloader);
        this.slowNetworkDownloader = new SlowNetworkImageDownloader(this.downloader);
        C1523L.writeDebugLogs(ImageLoaderConfiguration$Builder.access$1800(imageLoaderConfiguration$Builder));
    }

    public static ImageLoaderConfiguration createDefault(Context context) {
        return new ImageLoaderConfiguration$Builder(context).build();
    }

    ImageSize getMaxImageSize() {
        DisplayMetrics displayMetrics = this.resources.getDisplayMetrics();
        int i = this.maxImageWidthForMemoryCache;
        if (i <= 0) {
            i = displayMetrics.widthPixels;
        }
        int i2 = this.maxImageHeightForMemoryCache;
        if (i2 <= 0) {
            i2 = displayMetrics.heightPixels;
        }
        return new ImageSize(i, i2);
    }
}
