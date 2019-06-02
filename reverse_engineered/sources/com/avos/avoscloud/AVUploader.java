package com.avos.avoscloud;

import android.os.Build.VERSION;
import com.avos.avoscloud.LogUtil.log;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

abstract class AVUploader {
    private static final int CORE_POOL_SIZE = (CPU_COUNT + 1);
    private static final int CPU_COUNT = Runtime.getRuntime().availableProcessors();
    private static final long KEEP_ALIVE_TIME = 1;
    private static final int MAX_POOL_SIZE = ((CPU_COUNT * 2) + 1);
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, 1, TimeUnit.SECONDS, new LinkedBlockingQueue());
    private volatile boolean cancelled = false;
    private volatile boolean complete = false;
    protected final int defaultFileKeyLength = 40;
    private volatile Future future;
    protected final AVFile parseFile;
    protected ProgressCallback progressCallback;
    protected SaveCallback saveCallback;
    protected long totalSize;

    /* renamed from: com.avos.avoscloud.AVUploader$1 */
    class C09541 implements Runnable {
        C09541() {
        }

        public void run() {
            AVException doWork = AVUploader.this.doWork();
            if (AVUploader.this.cancelled) {
                AVUploader.this.onPostExecute(AVErrorUtils.createException((int) AVException.UNKNOWN, "Uploading file task is canceled."));
                return;
            }
            AVUploader.this.complete = true;
            AVUploader.this.onPostExecute(doWork);
        }
    }

    abstract AVException doWork();

    static {
        if (VERSION.SDK_INT >= 9) {
            executor.allowCoreThreadTimeOut(true);
        }
    }

    protected AVUploader(AVFile aVFile, SaveCallback saveCallback, ProgressCallback progressCallback) {
        this.parseFile = aVFile;
        this.saveCallback = saveCallback;
        this.progressCallback = progressCallback;
        this.cancelled = false;
        this.complete = false;
    }

    public void execute() {
        this.future = executor.submit(new C09541());
    }

    protected AVException doInBackground(Void... voidArr) {
        return doWork();
    }

    protected void onProgressUpdate(Integer num) {
        if (this.progressCallback != null) {
            this.progressCallback.internalDone(num, null);
        }
    }

    protected void onPostExecute(AVException aVException) {
        if (this.saveCallback != null) {
            this.saveCallback.internalDone(aVException);
        }
    }

    protected void onCancelled() {
        log.m3514d("upload cancel");
    }

    public boolean cancel(boolean z) {
        if (this.cancelled || this.complete) {
            return false;
        }
        this.cancelled = true;
        if (z) {
            interruptImmediately();
        } else if (this.future != null) {
            this.future.cancel(false);
        }
        onCancelled();
        return true;
    }

    public void interruptImmediately() {
        if (this.future != null) {
            this.future.cancel(true);
        }
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void publishProgress(int i) {
        onProgressUpdate(Integer.valueOf(i));
    }
}
