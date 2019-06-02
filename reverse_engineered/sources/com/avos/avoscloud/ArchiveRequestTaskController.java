package com.avos.avoscloud;

import android.annotation.SuppressLint;
import com.avos.avoscloud.LogUtil.avlog;
import com.avos.avoscloud.LogUtil.log;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@SuppressLint({"NewApi"})
public class ArchiveRequestTaskController {
    private static final long TIME_DELAY_FOR_ARCHIVEREQUEST = 30;
    static Runnable archiveRequestTask = new C09731();
    private static Lock lock = new ReentrantLock();
    static ScheduledExecutorService scheduledExecutorService;
    static ScheduledFuture<?> scheduledFuture;

    /* renamed from: com.avos.avoscloud.ArchiveRequestTaskController$1 */
    static class C09731 implements Runnable {
        C09731() {
        }

        public void run() {
            if (AVOSCloud.showInternalDebugLog()) {
                avlog.m3506d("trying to send archive request");
            }
            if (AVUtils.isBlankString(AVOSCloud.applicationId) || AVOSCloud.applicationContext == null) {
                log.m3519e("applicationContext is null, Please call AVOSCloud.initialize first");
            } else if (ArchiveRequestTaskController.lock.tryLock()) {
                try {
                    PaasClient.storageInstance().handleAllArchivedRequest(true);
                } catch (Exception e) {
                    log.m3520e("Exception happended during processing archive requests", e);
                } finally {
                    ArchiveRequestTaskController.lock.unlock();
                }
            }
        }
    }

    public static synchronized void schedule() {
        boolean z = true;
        synchronized (ArchiveRequestTaskController.class) {
            if (scheduledExecutorService == null) {
                scheduledExecutorService = Executors.newScheduledThreadPool(1);
            }
            if (!(scheduledFuture == null || scheduledFuture.isDone())) {
                z = scheduledFuture.cancel(false);
            }
            if (z) {
                scheduledFuture = scheduledExecutorService.schedule(archiveRequestTask, TIME_DELAY_FOR_ARCHIVEREQUEST, TimeUnit.SECONDS);
            }
        }
    }
}
