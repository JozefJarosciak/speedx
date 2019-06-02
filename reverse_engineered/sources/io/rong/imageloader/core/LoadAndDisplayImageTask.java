package io.rong.imageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import io.rong.imageloader.core.assist.FailReason;
import io.rong.imageloader.core.assist.FailReason.FailType;
import io.rong.imageloader.core.assist.ImageScaleType;
import io.rong.imageloader.core.assist.ImageSize;
import io.rong.imageloader.core.assist.LoadedFrom;
import io.rong.imageloader.core.assist.ViewScaleType;
import io.rong.imageloader.core.decode.ImageDecoder;
import io.rong.imageloader.core.decode.ImageDecodingInfo;
import io.rong.imageloader.core.download.ImageDownloader;
import io.rong.imageloader.core.download.ImageDownloader.Scheme;
import io.rong.imageloader.core.imageaware.ImageAware;
import io.rong.imageloader.core.listener.ImageLoadingListener;
import io.rong.imageloader.core.listener.ImageLoadingProgressListener;
import io.rong.imageloader.utils.C1523L;
import io.rong.imageloader.utils.IoUtils;
import io.rong.imageloader.utils.IoUtils.CopyListener;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

final class LoadAndDisplayImageTask implements CopyListener, Runnable {
    private static final String ERROR_NO_IMAGE_STREAM = "No stream for image [%s]";
    private static final String ERROR_POST_PROCESSOR_NULL = "Post-processor returned null [%s]";
    private static final String ERROR_PRE_PROCESSOR_NULL = "Pre-processor returned null [%s]";
    private static final String ERROR_PROCESSOR_FOR_DISK_CACHE_NULL = "Bitmap processor for disk cache returned null [%s]";
    private static final String LOG_CACHE_IMAGE_IN_MEMORY = "Cache image in memory [%s]";
    private static final String LOG_CACHE_IMAGE_ON_DISK = "Cache image on disk [%s]";
    private static final String LOG_DELAY_BEFORE_LOADING = "Delay %d ms before loading...  [%s]";
    private static final String LOG_GET_IMAGE_FROM_MEMORY_CACHE_AFTER_WAITING = "...Get cached bitmap from memory after waiting. [%s]";
    private static final String LOG_LOAD_IMAGE_FROM_DISK_CACHE = "Load image from disk cache [%s]";
    private static final String LOG_LOAD_IMAGE_FROM_NETWORK = "Load image from network [%s]";
    private static final String LOG_POSTPROCESS_IMAGE = "PostProcess image before displaying [%s]";
    private static final String LOG_PREPROCESS_IMAGE = "PreProcess image before caching in memory [%s]";
    private static final String LOG_PROCESS_IMAGE_BEFORE_CACHE_ON_DISK = "Process image before cache on disk [%s]";
    private static final String LOG_RESIZE_CACHED_IMAGE_FILE = "Resize image in disk cache [%s]";
    private static final String LOG_RESUME_AFTER_PAUSE = ".. Resume loading [%s]";
    private static final String LOG_START_DISPLAY_IMAGE_TASK = "Start display image task [%s]";
    private static final String LOG_TASK_CANCELLED_IMAGEAWARE_COLLECTED = "ImageAware was collected by GC. Task is cancelled. [%s]";
    private static final String LOG_TASK_CANCELLED_IMAGEAWARE_REUSED = "ImageAware is reused for another image. Task is cancelled. [%s]";
    private static final String LOG_TASK_INTERRUPTED = "Task was interrupted [%s]";
    private static final String LOG_WAITING_FOR_IMAGE_LOADED = "Image already is loading. Waiting... [%s]";
    private static final String LOG_WAITING_FOR_RESUME = "ImageLoader is paused. Waiting...  [%s]";
    private final ImageLoaderConfiguration configuration;
    private final ImageDecoder decoder;
    private final ImageDownloader downloader;
    private final ImageLoaderEngine engine;
    private final Handler handler;
    final ImageAware imageAware;
    private final ImageLoadingInfo imageLoadingInfo;
    final ImageLoadingListener listener;
    private LoadedFrom loadedFrom = LoadedFrom.NETWORK;
    private final String memoryCacheKey;
    private final ImageDownloader networkDeniedDownloader;
    final DisplayImageOptions options;
    final ImageLoadingProgressListener progressListener;
    private final ImageDownloader slowNetworkDownloader;
    private final boolean syncLoading;
    private final ImageSize targetSize;
    final String uri;

    /* renamed from: io.rong.imageloader.core.LoadAndDisplayImageTask$3 */
    class C49683 implements Runnable {
        C49683() {
        }

        public void run() {
            LoadAndDisplayImageTask.this.listener.onLoadingCancelled(LoadAndDisplayImageTask.this.uri, LoadAndDisplayImageTask.this.imageAware.getWrappedView());
        }
    }

    class TaskCancelledException extends Exception {
        TaskCancelledException() {
        }
    }

    public LoadAndDisplayImageTask(ImageLoaderEngine imageLoaderEngine, ImageLoadingInfo imageLoadingInfo, Handler handler) {
        this.engine = imageLoaderEngine;
        this.imageLoadingInfo = imageLoadingInfo;
        this.handler = handler;
        this.configuration = imageLoaderEngine.configuration;
        this.downloader = this.configuration.downloader;
        this.networkDeniedDownloader = this.configuration.networkDeniedDownloader;
        this.slowNetworkDownloader = this.configuration.slowNetworkDownloader;
        this.decoder = this.configuration.decoder;
        this.uri = imageLoadingInfo.uri;
        this.memoryCacheKey = imageLoadingInfo.memoryCacheKey;
        this.imageAware = imageLoadingInfo.imageAware;
        this.targetSize = imageLoadingInfo.targetSize;
        this.options = imageLoadingInfo.options;
        this.listener = imageLoadingInfo.listener;
        this.progressListener = imageLoadingInfo.progressListener;
        this.syncLoading = this.options.isSyncLoading();
    }

    public void run() {
        if (!waitIfPaused() && !delayIfNeed()) {
            ReentrantLock reentrantLock = this.imageLoadingInfo.loadFromUriLock;
            C1523L.d(LOG_START_DISPLAY_IMAGE_TASK, new Object[]{this.memoryCacheKey});
            if (reentrantLock.isLocked()) {
                C1523L.d(LOG_WAITING_FOR_IMAGE_LOADED, new Object[]{this.memoryCacheKey});
            }
            reentrantLock.lock();
            try {
                checkTaskNotActual();
                Bitmap bitmap = this.configuration.memoryCache.get(this.memoryCacheKey);
                if (bitmap == null || bitmap.isRecycled()) {
                    bitmap = tryLoadBitmap();
                    if (bitmap != null) {
                        checkTaskNotActual();
                        checkTaskInterrupted();
                        if (this.options.shouldPreProcess()) {
                            C1523L.d(LOG_PREPROCESS_IMAGE, new Object[]{this.memoryCacheKey});
                            bitmap = this.options.getPreProcessor().process(bitmap);
                            if (bitmap == null) {
                                C1523L.e(ERROR_PRE_PROCESSOR_NULL, new Object[]{this.memoryCacheKey});
                            }
                        }
                        if (bitmap != null && this.options.isCacheInMemory()) {
                            C1523L.d(LOG_CACHE_IMAGE_IN_MEMORY, new Object[]{this.memoryCacheKey});
                            this.configuration.memoryCache.put(this.memoryCacheKey, bitmap);
                        }
                    } else {
                        return;
                    }
                }
                this.loadedFrom = LoadedFrom.MEMORY_CACHE;
                C1523L.d(LOG_GET_IMAGE_FROM_MEMORY_CACHE_AFTER_WAITING, new Object[]{this.memoryCacheKey});
                if (bitmap != null && this.options.shouldPostProcess()) {
                    C1523L.d(LOG_POSTPROCESS_IMAGE, new Object[]{this.memoryCacheKey});
                    bitmap = this.options.getPostProcessor().process(bitmap);
                    if (bitmap == null) {
                        C1523L.e(ERROR_POST_PROCESSOR_NULL, new Object[]{this.memoryCacheKey});
                    }
                }
                checkTaskNotActual();
                checkTaskInterrupted();
                reentrantLock.unlock();
                runTask(new DisplayBitmapTask(bitmap, this.imageLoadingInfo, this.engine, this.loadedFrom), this.syncLoading, this.handler, this.engine);
            } catch (TaskCancelledException e) {
                fireCancelEvent();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    private boolean waitIfPaused() {
        AtomicBoolean pause = this.engine.getPause();
        if (pause.get()) {
            synchronized (this.engine.getPauseLock()) {
                if (pause.get()) {
                    C1523L.d(LOG_WAITING_FOR_RESUME, new Object[]{this.memoryCacheKey});
                    try {
                        this.engine.getPauseLock().wait();
                        C1523L.d(LOG_RESUME_AFTER_PAUSE, new Object[]{this.memoryCacheKey});
                    } catch (InterruptedException e) {
                        C1523L.e(LOG_TASK_INTERRUPTED, new Object[]{this.memoryCacheKey});
                        return true;
                    }
                }
            }
        }
        return isTaskNotActual();
    }

    private boolean delayIfNeed() {
        if (!this.options.shouldDelayBeforeLoading()) {
            return false;
        }
        C1523L.d(LOG_DELAY_BEFORE_LOADING, new Object[]{Integer.valueOf(this.options.getDelayBeforeLoading()), this.memoryCacheKey});
        try {
            Thread.sleep((long) this.options.getDelayBeforeLoading());
            return isTaskNotActual();
        } catch (InterruptedException e) {
            C1523L.e(LOG_TASK_INTERRUPTED, new Object[]{this.memoryCacheKey});
            return true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap tryLoadBitmap() throws io.rong.imageloader.core.LoadAndDisplayImageTask.TaskCancelledException {
        /*
        r7 = this;
        r1 = 0;
        r0 = r7.configuration;	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r0.diskCache;	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r2 = r7.uri;	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r0.get(r2);	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        if (r0 == 0) goto L_0x00d9;
    L_0x000d:
        r2 = r0.exists();	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        if (r2 == 0) goto L_0x00d9;
    L_0x0013:
        r2 = r0.length();	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r4 = 0;
        r2 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1));
        if (r2 <= 0) goto L_0x00d9;
    L_0x001d:
        r2 = "Load image from disk cache [%s]";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r4 = 0;
        r5 = r7.memoryCacheKey;	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r3[r4] = r5;	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        io.rong.imageloader.utils.C1523L.d(r2, r3);	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r2 = io.rong.imageloader.core.assist.LoadedFrom.DISC_CACHE;	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r7.loadedFrom = r2;	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r7.checkTaskNotActual();	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r2 = io.rong.imageloader.core.download.ImageDownloader.Scheme.FILE;	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r0.getAbsolutePath();	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r2.wrap(r0);	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
        r0 = r7.decodeImage(r0);	 Catch:{ IllegalStateException -> 0x00a0, TaskCancelledException -> 0x00a8, IOException -> 0x00aa, OutOfMemoryError -> 0x00b7, Throwable -> 0x00c4 }
    L_0x003f:
        if (r0 == 0) goto L_0x004d;
    L_0x0041:
        r2 = r0.getWidth();	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r2 <= 0) goto L_0x004d;
    L_0x0047:
        r2 = r0.getHeight();	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r2 > 0) goto L_0x009f;
    L_0x004d:
        r2 = "Load image from network [%s]";
        r3 = 1;
        r3 = new java.lang.Object[r3];	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r4 = 0;
        r5 = r7.memoryCacheKey;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3[r4] = r5;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        io.rong.imageloader.utils.C1523L.d(r2, r3);	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r2 = io.rong.imageloader.core.assist.LoadedFrom.NETWORK;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r7.loadedFrom = r2;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r2 = r7.uri;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r7.options;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r3.isCacheOnDisk();	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r3 == 0) goto L_0x0084;
    L_0x0068:
        r3 = r7.tryCacheImageOnDisk();	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r3 == 0) goto L_0x0084;
    L_0x006e:
        r3 = r7.configuration;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r3.diskCache;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r4 = r7.uri;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r3.get(r4);	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r3 == 0) goto L_0x0084;
    L_0x007a:
        r2 = io.rong.imageloader.core.download.ImageDownloader.Scheme.FILE;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = r3.getAbsolutePath();	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r2 = r2.wrap(r3);	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
    L_0x0084:
        r7.checkTaskNotActual();	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r0 = r7.decodeImage(r2);	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r0 == 0) goto L_0x0099;
    L_0x008d:
        r2 = r0.getWidth();	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r2 <= 0) goto L_0x0099;
    L_0x0093:
        r2 = r0.getHeight();	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        if (r2 > 0) goto L_0x009f;
    L_0x0099:
        r2 = io.rong.imageloader.core.assist.FailReason.FailType.DECODING_ERROR;	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
        r3 = 0;
        r7.fireFailEvent(r2, r3);	 Catch:{ IllegalStateException -> 0x00d7, TaskCancelledException -> 0x00a8, IOException -> 0x00d5, OutOfMemoryError -> 0x00d3, Throwable -> 0x00d1 }
    L_0x009f:
        return r0;
    L_0x00a0:
        r0 = move-exception;
        r0 = r1;
    L_0x00a2:
        r2 = io.rong.imageloader.core.assist.FailReason.FailType.NETWORK_DENIED;
        r7.fireFailEvent(r2, r1);
        goto L_0x009f;
    L_0x00a8:
        r0 = move-exception;
        throw r0;
    L_0x00aa:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x00ae:
        io.rong.imageloader.utils.C1523L.e(r1);
        r2 = io.rong.imageloader.core.assist.FailReason.FailType.IO_ERROR;
        r7.fireFailEvent(r2, r1);
        goto L_0x009f;
    L_0x00b7:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x00bb:
        io.rong.imageloader.utils.C1523L.e(r1);
        r2 = io.rong.imageloader.core.assist.FailReason.FailType.OUT_OF_MEMORY;
        r7.fireFailEvent(r2, r1);
        goto L_0x009f;
    L_0x00c4:
        r0 = move-exception;
        r6 = r0;
        r0 = r1;
        r1 = r6;
    L_0x00c8:
        io.rong.imageloader.utils.C1523L.e(r1);
        r2 = io.rong.imageloader.core.assist.FailReason.FailType.UNKNOWN;
        r7.fireFailEvent(r2, r1);
        goto L_0x009f;
    L_0x00d1:
        r1 = move-exception;
        goto L_0x00c8;
    L_0x00d3:
        r1 = move-exception;
        goto L_0x00bb;
    L_0x00d5:
        r1 = move-exception;
        goto L_0x00ae;
    L_0x00d7:
        r2 = move-exception;
        goto L_0x00a2;
    L_0x00d9:
        r0 = r1;
        goto L_0x003f;
        */
        throw new UnsupportedOperationException("Method not decompiled: io.rong.imageloader.core.LoadAndDisplayImageTask.tryLoadBitmap():android.graphics.Bitmap");
    }

    private Bitmap decodeImage(String str) throws IOException {
        String str2 = str;
        return this.decoder.decode(new ImageDecodingInfo(this.memoryCacheKey, str2, this.uri, this.targetSize, this.imageAware.getScaleType(), getDownloader(), this.options));
    }

    private boolean tryCacheImageOnDisk() throws TaskCancelledException {
        C1523L.d(LOG_CACHE_IMAGE_ON_DISK, new Object[]{this.memoryCacheKey});
        try {
            boolean downloadImage = downloadImage();
            if (!downloadImage) {
                return downloadImage;
            }
            int i = this.configuration.maxImageWidthForDiskCache;
            int i2 = this.configuration.maxImageHeightForDiskCache;
            if (i <= 0 && i2 <= 0) {
                return downloadImage;
            }
            C1523L.d(LOG_RESIZE_CACHED_IMAGE_FILE, new Object[]{this.memoryCacheKey});
            resizeAndSaveImage(i, i2);
            return downloadImage;
        } catch (Throwable e) {
            C1523L.e(e);
            return false;
        }
    }

    private boolean downloadImage() throws IOException {
        boolean z = false;
        Closeable stream = getDownloader().getStream(this.uri, this.options.getExtraForDownloader());
        if (stream == null) {
            C1523L.e(ERROR_NO_IMAGE_STREAM, new Object[]{this.memoryCacheKey});
        } else {
            try {
                z = this.configuration.diskCache.save(this.uri, stream, this);
            } finally {
                IoUtils.closeSilently(stream);
            }
        }
        return z;
    }

    private boolean resizeAndSaveImage(int i, int i2) throws IOException {
        File file = this.configuration.diskCache.get(this.uri);
        if (file != null && file.exists()) {
            Bitmap decode = this.decoder.decode(new ImageDecodingInfo(this.memoryCacheKey, Scheme.FILE.wrap(file.getAbsolutePath()), this.uri, new ImageSize(i, i2), ViewScaleType.FIT_INSIDE, getDownloader(), new DisplayImageOptions$Builder().cloneFrom(this.options).imageScaleType(ImageScaleType.IN_SAMPLE_INT).build()));
            if (!(decode == null || this.configuration.processorForDiskCache == null)) {
                C1523L.d(LOG_PROCESS_IMAGE_BEFORE_CACHE_ON_DISK, new Object[]{this.memoryCacheKey});
                decode = this.configuration.processorForDiskCache.process(decode);
                if (decode == null) {
                    C1523L.e(ERROR_PROCESSOR_FOR_DISK_CACHE_NULL, new Object[]{this.memoryCacheKey});
                }
            }
            Bitmap bitmap = decode;
            if (bitmap != null) {
                boolean save = this.configuration.diskCache.save(this.uri, bitmap);
                bitmap.recycle();
                return save;
            }
        }
        return false;
    }

    public boolean onBytesCopied(int i, int i2) {
        return this.syncLoading || fireProgressEvent(i, i2);
    }

    private boolean fireProgressEvent(final int i, final int i2) {
        if (isTaskInterrupted() || isTaskNotActual()) {
            return false;
        }
        if (this.progressListener != null) {
            runTask(new Runnable() {
                public void run() {
                    LoadAndDisplayImageTask.this.progressListener.onProgressUpdate(LoadAndDisplayImageTask.this.uri, LoadAndDisplayImageTask.this.imageAware.getWrappedView(), i, i2);
                }
            }, false, this.handler, this.engine);
        }
        return true;
    }

    private void fireFailEvent(final FailType failType, final Throwable th) {
        if (!this.syncLoading && !isTaskInterrupted() && !isTaskNotActual()) {
            runTask(new Runnable() {
                public void run() {
                    if (LoadAndDisplayImageTask.this.options.shouldShowImageOnFail()) {
                        if (LoadAndDisplayImageTask.this.options.getDisplayer() != null) {
                            Bitmap drawableToBitmap = LoadAndDisplayImageTask.this.options.drawableToBitmap(LoadAndDisplayImageTask.this.options.getImageOnFail(LoadAndDisplayImageTask.this.configuration.resources));
                            if (drawableToBitmap != null) {
                                LoadAndDisplayImageTask.this.options.getDisplayer().display(drawableToBitmap, LoadAndDisplayImageTask.this.imageAware, LoadedFrom.DISC_CACHE);
                            }
                        } else {
                            LoadAndDisplayImageTask.this.imageAware.setImageDrawable(LoadAndDisplayImageTask.this.options.getImageOnFail(LoadAndDisplayImageTask.this.configuration.resources));
                        }
                    }
                    LoadAndDisplayImageTask.this.listener.onLoadingFailed(LoadAndDisplayImageTask.this.uri, LoadAndDisplayImageTask.this.imageAware.getWrappedView(), new FailReason(failType, th));
                }
            }, false, this.handler, this.engine);
        }
    }

    private void fireCancelEvent() {
        if (!this.syncLoading && !isTaskInterrupted()) {
            runTask(new C49683(), false, this.handler, this.engine);
        }
    }

    private ImageDownloader getDownloader() {
        if (this.engine.isNetworkDenied()) {
            return this.networkDeniedDownloader;
        }
        if (this.engine.isSlowNetwork()) {
            return this.slowNetworkDownloader;
        }
        return this.downloader;
    }

    private void checkTaskNotActual() throws TaskCancelledException {
        checkViewCollected();
        checkViewReused();
    }

    private boolean isTaskNotActual() {
        return isViewCollected() || isViewReused();
    }

    private void checkViewCollected() throws TaskCancelledException {
        if (isViewCollected()) {
            throw new TaskCancelledException();
        }
    }

    private boolean isViewCollected() {
        if (!this.imageAware.isCollected()) {
            return false;
        }
        C1523L.d(LOG_TASK_CANCELLED_IMAGEAWARE_COLLECTED, new Object[]{this.memoryCacheKey});
        return true;
    }

    private void checkViewReused() throws TaskCancelledException {
        if (isViewReused()) {
            throw new TaskCancelledException();
        }
    }

    private boolean isViewReused() {
        if (!(!this.memoryCacheKey.equals(this.engine.getLoadingUriForView(this.imageAware)))) {
            return false;
        }
        C1523L.d(LOG_TASK_CANCELLED_IMAGEAWARE_REUSED, new Object[]{this.memoryCacheKey});
        return true;
    }

    private void checkTaskInterrupted() throws TaskCancelledException {
        if (isTaskInterrupted()) {
            throw new TaskCancelledException();
        }
    }

    private boolean isTaskInterrupted() {
        if (!Thread.interrupted()) {
            return false;
        }
        C1523L.d(LOG_TASK_INTERRUPTED, new Object[]{this.memoryCacheKey});
        return true;
    }

    String getLoadingUri() {
        return this.uri;
    }

    static void runTask(Runnable runnable, boolean z, Handler handler, ImageLoaderEngine imageLoaderEngine) {
        if (z) {
            runnable.run();
        } else if (handler == null) {
            imageLoaderEngine.fireCallback(runnable);
        } else {
            handler.post(runnable);
        }
    }
}
