package com.squareup.picasso;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.net.NetworkInfo;
import com.squareup.picasso.RequestHandler.Result;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

class BitmapHunter implements Runnable {
    private static final Object DECODE_LOCK = new Object();
    private static final RequestHandler ERRORING_HANDLER = new C43792();
    private static final ThreadLocal<StringBuilder> NAME_BUILDER = new C43781();
    private static final AtomicInteger SEQUENCE_GENERATOR = new AtomicInteger();
    Action action;
    List<Action> actions;
    final Cache cache;
    final Request data;
    final Dispatcher dispatcher;
    Exception exception;
    int exifRotation;
    Future<?> future;
    final String key;
    Picasso$LoadedFrom loadedFrom;
    final int memoryPolicy;
    int networkPolicy;
    final Picasso picasso;
    Picasso$Priority priority;
    final RequestHandler requestHandler;
    Bitmap result;
    int retryCount;
    final int sequence = SEQUENCE_GENERATOR.incrementAndGet();
    final Stats stats;

    /* renamed from: com.squareup.picasso.BitmapHunter$1 */
    static class C43781 extends ThreadLocal<StringBuilder> {
        C43781() {
        }

        protected StringBuilder initialValue() {
            return new StringBuilder("Picasso-");
        }
    }

    /* renamed from: com.squareup.picasso.BitmapHunter$2 */
    static class C43792 extends RequestHandler {
        C43792() {
        }

        public boolean canHandleRequest(Request request) {
            return true;
        }

        public Result load(Request request, int i) throws IOException {
            throw new IllegalStateException("Unrecognized type of request: " + request);
        }
    }

    BitmapHunter(Picasso picasso, Dispatcher dispatcher, Cache cache, Stats stats, Action action, RequestHandler requestHandler) {
        this.picasso = picasso;
        this.dispatcher = dispatcher;
        this.cache = cache;
        this.stats = stats;
        this.action = action;
        this.key = action.getKey();
        this.data = action.getRequest();
        this.priority = action.getPriority();
        this.memoryPolicy = action.getMemoryPolicy();
        this.networkPolicy = action.getNetworkPolicy();
        this.requestHandler = requestHandler;
        this.retryCount = requestHandler.getRetryCount();
    }

    static Bitmap decodeStream(InputStream inputStream, Request request) throws IOException {
        InputStream markableInputStream = new MarkableInputStream(inputStream);
        long savePosition = markableInputStream.savePosition(65536);
        Options createBitmapOptions = RequestHandler.createBitmapOptions(request);
        boolean requiresInSampleSize = RequestHandler.requiresInSampleSize(createBitmapOptions);
        boolean isWebPFile = Utils.isWebPFile(markableInputStream);
        markableInputStream.reset(savePosition);
        if (isWebPFile) {
            byte[] toByteArray = Utils.toByteArray(markableInputStream);
            if (requiresInSampleSize) {
                BitmapFactory.decodeByteArray(toByteArray, 0, toByteArray.length, createBitmapOptions);
                RequestHandler.calculateInSampleSize(request.targetWidth, request.targetHeight, createBitmapOptions, request);
            }
            return BitmapFactory.decodeByteArray(toByteArray, 0, toByteArray.length, createBitmapOptions);
        }
        if (requiresInSampleSize) {
            BitmapFactory.decodeStream(markableInputStream, null, createBitmapOptions);
            RequestHandler.calculateInSampleSize(request.targetWidth, request.targetHeight, createBitmapOptions, request);
            markableInputStream.reset(savePosition);
        }
        Bitmap decodeStream = BitmapFactory.decodeStream(markableInputStream, null, createBitmapOptions);
        if (decodeStream != null) {
            return decodeStream;
        }
        throw new IOException("Failed to decode stream.");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r4 = this;
        r0 = r4.data;	 Catch:{ ResponseException -> 0x0035, ContentLengthException -> 0x0051, IOException -> 0x0063, OutOfMemoryError -> 0x0075, Exception -> 0x00a3 }
        updateThreadName(r0);	 Catch:{ ResponseException -> 0x0035, ContentLengthException -> 0x0051, IOException -> 0x0063, OutOfMemoryError -> 0x0075, Exception -> 0x00a3 }
        r0 = r4.picasso;	 Catch:{ ResponseException -> 0x0035, ContentLengthException -> 0x0051, IOException -> 0x0063, OutOfMemoryError -> 0x0075, Exception -> 0x00a3 }
        r0 = r0.loggingEnabled;	 Catch:{ ResponseException -> 0x0035, ContentLengthException -> 0x0051, IOException -> 0x0063, OutOfMemoryError -> 0x0075, Exception -> 0x00a3 }
        if (r0 == 0) goto L_0x0016;
    L_0x000b:
        r0 = "Hunter";
        r1 = "executing";
        r2 = com.squareup.picasso.Utils.getLogIdsForHunter(r4);	 Catch:{ ResponseException -> 0x0035, ContentLengthException -> 0x0051, IOException -> 0x0063, OutOfMemoryError -> 0x0075, Exception -> 0x00a3 }
        com.squareup.picasso.Utils.log(r0, r1, r2);	 Catch:{ ResponseException -> 0x0035, ContentLengthException -> 0x0051, IOException -> 0x0063, OutOfMemoryError -> 0x0075, Exception -> 0x00a3 }
    L_0x0016:
        r0 = r4.hunt();	 Catch:{ ResponseException -> 0x0035, ContentLengthException -> 0x0051, IOException -> 0x0063, OutOfMemoryError -> 0x0075, Exception -> 0x00a3 }
        r4.result = r0;	 Catch:{ ResponseException -> 0x0035, ContentLengthException -> 0x0051, IOException -> 0x0063, OutOfMemoryError -> 0x0075, Exception -> 0x00a3 }
        r0 = r4.result;	 Catch:{ ResponseException -> 0x0035, ContentLengthException -> 0x0051, IOException -> 0x0063, OutOfMemoryError -> 0x0075, Exception -> 0x00a3 }
        if (r0 != 0) goto L_0x002f;
    L_0x0020:
        r0 = r4.dispatcher;	 Catch:{ ResponseException -> 0x0035, ContentLengthException -> 0x0051, IOException -> 0x0063, OutOfMemoryError -> 0x0075, Exception -> 0x00a3 }
        r0.dispatchFailed(r4);	 Catch:{ ResponseException -> 0x0035, ContentLengthException -> 0x0051, IOException -> 0x0063, OutOfMemoryError -> 0x0075, Exception -> 0x00a3 }
    L_0x0025:
        r0 = java.lang.Thread.currentThread();
        r1 = "Picasso-Idle";
        r0.setName(r1);
    L_0x002e:
        return;
    L_0x002f:
        r0 = r4.dispatcher;	 Catch:{ ResponseException -> 0x0035, ContentLengthException -> 0x0051, IOException -> 0x0063, OutOfMemoryError -> 0x0075, Exception -> 0x00a3 }
        r0.dispatchComplete(r4);	 Catch:{ ResponseException -> 0x0035, ContentLengthException -> 0x0051, IOException -> 0x0063, OutOfMemoryError -> 0x0075, Exception -> 0x00a3 }
        goto L_0x0025;
    L_0x0035:
        r0 = move-exception;
        r1 = r0.localCacheOnly;	 Catch:{ all -> 0x00b6 }
        if (r1 == 0) goto L_0x0040;
    L_0x003a:
        r1 = r0.responseCode;	 Catch:{ all -> 0x00b6 }
        r2 = 504; // 0x1f8 float:7.06E-43 double:2.49E-321;
        if (r1 == r2) goto L_0x0042;
    L_0x0040:
        r4.exception = r0;	 Catch:{ all -> 0x00b6 }
    L_0x0042:
        r0 = r4.dispatcher;	 Catch:{ all -> 0x00b6 }
        r0.dispatchFailed(r4);	 Catch:{ all -> 0x00b6 }
        r0 = java.lang.Thread.currentThread();
        r1 = "Picasso-Idle";
        r0.setName(r1);
        goto L_0x002e;
    L_0x0051:
        r0 = move-exception;
        r4.exception = r0;	 Catch:{ all -> 0x00b6 }
        r0 = r4.dispatcher;	 Catch:{ all -> 0x00b6 }
        r0.dispatchRetry(r4);	 Catch:{ all -> 0x00b6 }
        r0 = java.lang.Thread.currentThread();
        r1 = "Picasso-Idle";
        r0.setName(r1);
        goto L_0x002e;
    L_0x0063:
        r0 = move-exception;
        r4.exception = r0;	 Catch:{ all -> 0x00b6 }
        r0 = r4.dispatcher;	 Catch:{ all -> 0x00b6 }
        r0.dispatchRetry(r4);	 Catch:{ all -> 0x00b6 }
        r0 = java.lang.Thread.currentThread();
        r1 = "Picasso-Idle";
        r0.setName(r1);
        goto L_0x002e;
    L_0x0075:
        r0 = move-exception;
        r1 = new java.io.StringWriter;	 Catch:{ all -> 0x00b6 }
        r1.<init>();	 Catch:{ all -> 0x00b6 }
        r2 = r4.stats;	 Catch:{ all -> 0x00b6 }
        r2 = r2.createSnapshot();	 Catch:{ all -> 0x00b6 }
        r3 = new java.io.PrintWriter;	 Catch:{ all -> 0x00b6 }
        r3.<init>(r1);	 Catch:{ all -> 0x00b6 }
        r2.dump(r3);	 Catch:{ all -> 0x00b6 }
        r2 = new java.lang.RuntimeException;	 Catch:{ all -> 0x00b6 }
        r1 = r1.toString();	 Catch:{ all -> 0x00b6 }
        r2.<init>(r1, r0);	 Catch:{ all -> 0x00b6 }
        r4.exception = r2;	 Catch:{ all -> 0x00b6 }
        r0 = r4.dispatcher;	 Catch:{ all -> 0x00b6 }
        r0.dispatchFailed(r4);	 Catch:{ all -> 0x00b6 }
        r0 = java.lang.Thread.currentThread();
        r1 = "Picasso-Idle";
        r0.setName(r1);
        goto L_0x002e;
    L_0x00a3:
        r0 = move-exception;
        r4.exception = r0;	 Catch:{ all -> 0x00b6 }
        r0 = r4.dispatcher;	 Catch:{ all -> 0x00b6 }
        r0.dispatchFailed(r4);	 Catch:{ all -> 0x00b6 }
        r0 = java.lang.Thread.currentThread();
        r1 = "Picasso-Idle";
        r0.setName(r1);
        goto L_0x002e;
    L_0x00b6:
        r0 = move-exception;
        r1 = java.lang.Thread.currentThread();
        r2 = "Picasso-Idle";
        r1.setName(r2);
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.picasso.BitmapHunter.run():void");
    }

    Bitmap hunt() throws IOException {
        Bitmap bitmap = null;
        if (MemoryPolicy.shouldReadFromMemoryCache(this.memoryPolicy)) {
            bitmap = this.cache.get(this.key);
            if (bitmap != null) {
                this.stats.dispatchCacheHit();
                this.loadedFrom = Picasso$LoadedFrom.MEMORY;
                if (this.picasso.loggingEnabled) {
                    Utils.log("Hunter", "decoded", this.data.logId(), "from cache");
                }
                return bitmap;
            }
        }
        this.data.networkPolicy = this.retryCount == 0 ? NetworkPolicy.OFFLINE.index : this.networkPolicy;
        Result load = this.requestHandler.load(this.data, this.networkPolicy);
        if (load != null) {
            this.loadedFrom = load.getLoadedFrom();
            this.exifRotation = load.getExifOrientation();
            bitmap = load.getBitmap();
            if (bitmap == null) {
                InputStream stream = load.getStream();
                try {
                    bitmap = decodeStream(stream, this.data);
                } finally {
                    Utils.closeQuietly(stream);
                }
            }
        }
        if (bitmap != null) {
            if (this.picasso.loggingEnabled) {
                Utils.log("Hunter", "decoded", this.data.logId());
            }
            this.stats.dispatchBitmapDecoded(bitmap);
            if (this.data.needsTransformation() || this.exifRotation != 0) {
                synchronized (DECODE_LOCK) {
                    if (this.data.needsMatrixTransform() || this.exifRotation != 0) {
                        bitmap = transformResult(this.data, bitmap, this.exifRotation);
                        if (this.picasso.loggingEnabled) {
                            Utils.log("Hunter", "transformed", this.data.logId());
                        }
                    }
                    if (this.data.hasCustomTransformations()) {
                        bitmap = applyCustomTransformations(this.data.transformations, bitmap);
                        if (this.picasso.loggingEnabled) {
                            Utils.log("Hunter", "transformed", this.data.logId(), "from custom transformations");
                        }
                    }
                }
                if (bitmap != null) {
                    this.stats.dispatchBitmapTransformed(bitmap);
                }
            }
        }
        return bitmap;
    }

    void attach(Action action) {
        boolean z = this.picasso.loggingEnabled;
        Request request = action.request;
        if (this.action == null) {
            this.action = action;
            if (!z) {
                return;
            }
            if (this.actions == null || this.actions.isEmpty()) {
                Utils.log("Hunter", "joined", request.logId(), "to empty hunter");
                return;
            } else {
                Utils.log("Hunter", "joined", request.logId(), Utils.getLogIdsForHunter(this, "to "));
                return;
            }
        }
        if (this.actions == null) {
            this.actions = new ArrayList(3);
        }
        this.actions.add(action);
        if (z) {
            Utils.log("Hunter", "joined", request.logId(), Utils.getLogIdsForHunter(this, "to "));
        }
        Picasso$Priority priority = action.getPriority();
        if (priority.ordinal() > this.priority.ordinal()) {
            this.priority = priority;
        }
    }

    void detach(Action action) {
        boolean z = false;
        if (this.action == action) {
            this.action = null;
            z = true;
        } else if (this.actions != null) {
            z = this.actions.remove(action);
        }
        if (z && action.getPriority() == this.priority) {
            this.priority = computeNewPriority();
        }
        if (this.picasso.loggingEnabled) {
            Utils.log("Hunter", "removed", action.request.logId(), Utils.getLogIdsForHunter(this, "from "));
        }
    }

    private Picasso$Priority computeNewPriority() {
        int i = 1;
        int i2 = 0;
        Picasso$Priority picasso$Priority = Picasso$Priority.LOW;
        int i3 = (this.actions == null || this.actions.isEmpty()) ? 0 : 1;
        if (this.action == null && i3 == 0) {
            i = 0;
        }
        if (i == 0) {
            return picasso$Priority;
        }
        Picasso$Priority priority;
        if (this.action != null) {
            priority = this.action.getPriority();
        } else {
            priority = picasso$Priority;
        }
        if (i3 == 0) {
            return priority;
        }
        int size = this.actions.size();
        while (i2 < size) {
            Picasso$Priority priority2 = ((Action) this.actions.get(i2)).getPriority();
            if (priority2.ordinal() <= priority.ordinal()) {
                priority2 = priority;
            }
            i2++;
            priority = priority2;
        }
        return priority;
    }

    boolean cancel() {
        if (this.action != null) {
            return false;
        }
        if ((this.actions == null || this.actions.isEmpty()) && this.future != null && this.future.cancel(false)) {
            return true;
        }
        return false;
    }

    boolean isCancelled() {
        return this.future != null && this.future.isCancelled();
    }

    boolean shouldRetry(boolean z, NetworkInfo networkInfo) {
        if (!(this.retryCount > 0)) {
            return false;
        }
        this.retryCount--;
        return this.requestHandler.shouldRetry(z, networkInfo);
    }

    boolean supportsReplay() {
        return this.requestHandler.supportsReplay();
    }

    Bitmap getResult() {
        return this.result;
    }

    String getKey() {
        return this.key;
    }

    int getMemoryPolicy() {
        return this.memoryPolicy;
    }

    Request getData() {
        return this.data;
    }

    Action getAction() {
        return this.action;
    }

    Picasso getPicasso() {
        return this.picasso;
    }

    List<Action> getActions() {
        return this.actions;
    }

    Exception getException() {
        return this.exception;
    }

    Picasso$LoadedFrom getLoadedFrom() {
        return this.loadedFrom;
    }

    Picasso$Priority getPriority() {
        return this.priority;
    }

    static void updateThreadName(Request request) {
        String name = request.getName();
        StringBuilder stringBuilder = (StringBuilder) NAME_BUILDER.get();
        stringBuilder.ensureCapacity("Picasso-".length() + name.length());
        stringBuilder.replace("Picasso-".length(), stringBuilder.length(), name);
        Thread.currentThread().setName(stringBuilder.toString());
    }

    static BitmapHunter forRequest(Picasso picasso, Dispatcher dispatcher, Cache cache, Stats stats, Action action) {
        Request request = action.getRequest();
        List requestHandlers = picasso.getRequestHandlers();
        int size = requestHandlers.size();
        for (int i = 0; i < size; i++) {
            RequestHandler requestHandler = (RequestHandler) requestHandlers.get(i);
            if (requestHandler.canHandleRequest(request)) {
                return new BitmapHunter(picasso, dispatcher, cache, stats, action, requestHandler);
            }
        }
        return new BitmapHunter(picasso, dispatcher, cache, stats, action, ERRORING_HANDLER);
    }

    static Bitmap applyCustomTransformations(List<Transformation> list, Bitmap bitmap) {
        int size = list.size();
        int i = 0;
        Bitmap bitmap2 = bitmap;
        while (i < size) {
            final Transformation transformation = (Transformation) list.get(i);
            try {
                bitmap = transformation.transform(bitmap2);
                if (bitmap == null) {
                    final StringBuilder append = new StringBuilder().append("Transformation ").append(transformation.key()).append(" returned null after ").append(i).append(" previous transformation(s).\n\nTransformation list:\n");
                    for (Transformation transformation2 : list) {
                        append.append(transformation2.key()).append('\n');
                    }
                    Picasso.HANDLER.post(new Runnable() {
                        public void run() {
                            throw new NullPointerException(append.toString());
                        }
                    });
                    return null;
                } else if (bitmap == bitmap2 && bitmap2.isRecycled()) {
                    Picasso.HANDLER.post(new Runnable() {
                        public void run() {
                            throw new IllegalStateException("Transformation " + transformation2.key() + " returned input Bitmap but recycled it.");
                        }
                    });
                    return null;
                } else if (bitmap == bitmap2 || bitmap2.isRecycled()) {
                    i++;
                    bitmap2 = bitmap;
                } else {
                    Picasso.HANDLER.post(new Runnable() {
                        public void run() {
                            throw new IllegalStateException("Transformation " + transformation2.key() + " mutated input Bitmap but failed to recycle the original.");
                        }
                    });
                    return null;
                }
            } catch (final RuntimeException e) {
                Picasso.HANDLER.post(new Runnable() {
                    public void run() {
                        throw new RuntimeException("Transformation " + transformation2.key() + " crashed with exception.", e);
                    }
                });
                return null;
            }
        }
        return bitmap2;
    }

    static Bitmap transformResult(Request request, Bitmap bitmap, int i) {
        int ceil;
        int i2;
        int i3;
        Bitmap createBitmap;
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        boolean z = request.onlyScaleDown;
        Matrix matrix = new Matrix();
        if (request.needsMatrixTransform()) {
            int i4 = request.targetWidth;
            int i5 = request.targetHeight;
            float f = request.rotationDegrees;
            if (f != 0.0f) {
                if (request.hasRotationPivot) {
                    matrix.setRotate(f, request.rotationPivotX, request.rotationPivotY);
                } else {
                    matrix.setRotate(f);
                }
            }
            float f2;
            if (request.centerCrop) {
                int i6;
                int i7;
                f2 = ((float) i4) / ((float) width);
                f = ((float) i5) / ((float) height);
                if (f2 > f) {
                    ceil = (int) Math.ceil((double) ((f / f2) * ((float) height)));
                    f = ((float) i5) / ((float) ceil);
                    i6 = 0;
                    i7 = (height - ceil) / 2;
                    i2 = width;
                } else {
                    ceil = (int) Math.ceil((double) ((f2 / f) * ((float) width)));
                    f2 = ((float) i4) / ((float) ceil);
                    i6 = (width - ceil) / 2;
                    i7 = 0;
                    i2 = ceil;
                    ceil = height;
                }
                if (shouldResize(z, width, height, i4, i5)) {
                    matrix.preScale(f2, f);
                }
                height = i2;
                i3 = i6;
                i2 = ceil;
                ceil = i7;
            } else if (request.centerInside) {
                f = ((float) i4) / ((float) width);
                f2 = ((float) i5) / ((float) height);
                if (f >= f2) {
                    f = f2;
                }
                if (shouldResize(z, width, height, i4, i5)) {
                    matrix.preScale(f, f);
                }
                ceil = 0;
                i3 = 0;
                i2 = height;
                height = width;
            } else if (!((i4 == 0 && i5 == 0) || (i4 == width && i5 == height))) {
                f2 = i4 != 0 ? ((float) i4) / ((float) width) : ((float) i5) / ((float) height);
                f = i5 != 0 ? ((float) i5) / ((float) height) : ((float) i4) / ((float) width);
                if (shouldResize(z, width, height, i4, i5)) {
                    matrix.preScale(f2, f);
                }
            }
            if (i != 0) {
                matrix.preRotate((float) i);
            }
            createBitmap = Bitmap.createBitmap(bitmap, i3, ceil, height, i2, matrix, true);
            if (createBitmap != bitmap) {
                return bitmap;
            }
            bitmap.recycle();
            return createBitmap;
        }
        ceil = 0;
        i3 = 0;
        i2 = height;
        height = width;
        if (i != 0) {
            matrix.preRotate((float) i);
        }
        createBitmap = Bitmap.createBitmap(bitmap, i3, ceil, height, i2, matrix, true);
        if (createBitmap != bitmap) {
            return bitmap;
        }
        bitmap.recycle();
        return createBitmap;
    }

    private static boolean shouldResize(boolean z, int i, int i2, int i3, int i4) {
        return !z || i > i3 || i2 > i4;
    }
}
