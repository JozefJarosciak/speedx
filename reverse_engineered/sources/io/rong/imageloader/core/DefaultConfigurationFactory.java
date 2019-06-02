package io.rong.imageloader.core;

import android.annotation.TargetApi;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build.VERSION;
import io.rong.imageloader.cache.disc.DiskCache;
import io.rong.imageloader.cache.disc.impl.UnlimitedDiskCache;
import io.rong.imageloader.cache.disc.impl.ext.LruDiskCache;
import io.rong.imageloader.cache.disc.naming.FileNameGenerator;
import io.rong.imageloader.cache.disc.naming.HashCodeFileNameGenerator;
import io.rong.imageloader.cache.memory.MemoryCache;
import io.rong.imageloader.cache.memory.impl.LruMemoryCache;
import io.rong.imageloader.core.assist.QueueProcessingType;
import io.rong.imageloader.core.assist.deque.LIFOLinkedBlockingDeque;
import io.rong.imageloader.core.decode.BaseImageDecoder;
import io.rong.imageloader.core.decode.ImageDecoder;
import io.rong.imageloader.core.display.BitmapDisplayer;
import io.rong.imageloader.core.display.SimpleBitmapDisplayer;
import io.rong.imageloader.core.download.BaseImageDownloader;
import io.rong.imageloader.core.download.ImageDownloader;
import io.rong.imageloader.utils.C1523L;
import io.rong.imageloader.utils.StorageUtils;
import java.io.File;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class DefaultConfigurationFactory {

    private static class DefaultThreadFactory implements ThreadFactory {
        private static final AtomicInteger poolNumber = new AtomicInteger(1);
        private final ThreadGroup group;
        private final String namePrefix;
        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private final int threadPriority;

        DefaultThreadFactory(int i, String str) {
            this.threadPriority = i;
            this.group = Thread.currentThread().getThreadGroup();
            this.namePrefix = str + poolNumber.getAndIncrement() + "-thread-";
        }

        public Thread newThread(Runnable runnable) {
            Thread thread = new Thread(this.group, runnable, this.namePrefix + this.threadNumber.getAndIncrement(), 0);
            if (thread.isDaemon()) {
                thread.setDaemon(false);
            }
            thread.setPriority(this.threadPriority);
            return thread;
        }
    }

    public static Executor createExecutor(int i, int i2, QueueProcessingType queueProcessingType) {
        return new ThreadPoolExecutor(i, i, 0, TimeUnit.MILLISECONDS, (queueProcessingType == QueueProcessingType.LIFO ? 1 : null) != null ? new LIFOLinkedBlockingDeque() : new LinkedBlockingQueue(), createThreadFactory(i2, "uil-pool-"));
    }

    public static Executor createTaskDistributor() {
        return Executors.newCachedThreadPool(createThreadFactory(5, "uil-pool-d-"));
    }

    public static FileNameGenerator createFileNameGenerator() {
        return new HashCodeFileNameGenerator();
    }

    public static DiskCache createDiskCache(Context context, FileNameGenerator fileNameGenerator, long j, int i) {
        File createReserveDiskCacheDir = createReserveDiskCacheDir(context);
        if (j > 0 || i > 0) {
            try {
                return new LruDiskCache(StorageUtils.getIndividualCacheDirectory(context), createReserveDiskCacheDir, fileNameGenerator, j, i);
            } catch (Throwable e) {
                C1523L.e(e);
            }
        }
        return new UnlimitedDiskCache(StorageUtils.getCacheDirectory(context), createReserveDiskCacheDir, fileNameGenerator);
    }

    private static File createReserveDiskCacheDir(Context context) {
        File cacheDirectory = StorageUtils.getCacheDirectory(context, false);
        File file = new File(cacheDirectory, "uil-images");
        if (file.exists() || file.mkdir()) {
            return file;
        }
        return cacheDirectory;
    }

    public static MemoryCache createMemoryCache(Context context, int i) {
        if (i == 0) {
            int largeMemoryClass;
            ActivityManager activityManager = (ActivityManager) context.getSystemService("activity");
            int memoryClass = activityManager.getMemoryClass();
            if (hasHoneycomb() && isLargeHeap(context)) {
                largeMemoryClass = getLargeMemoryClass(activityManager);
            } else {
                largeMemoryClass = memoryClass;
            }
            i = (largeMemoryClass * 1048576) / 8;
        }
        return new LruMemoryCache(i);
    }

    private static boolean hasHoneycomb() {
        return VERSION.SDK_INT >= 11;
    }

    @TargetApi(11)
    private static boolean isLargeHeap(Context context) {
        return (context.getApplicationInfo().flags & 1048576) != 0;
    }

    @TargetApi(11)
    private static int getLargeMemoryClass(ActivityManager activityManager) {
        return activityManager.getLargeMemoryClass();
    }

    public static ImageDownloader createImageDownloader(Context context) {
        return new BaseImageDownloader(context);
    }

    public static ImageDecoder createImageDecoder(boolean z) {
        return new BaseImageDecoder(z);
    }

    public static BitmapDisplayer createBitmapDisplayer() {
        return new SimpleBitmapDisplayer();
    }

    private static ThreadFactory createThreadFactory(int i, String str) {
        return new DefaultThreadFactory(i, str);
    }
}
