package com.google.common.util.concurrent;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@VisibleForTesting
class MoreExecutors$Application {
    MoreExecutors$Application() {
    }

    final ExecutorService getExitingExecutorService(ThreadPoolExecutor threadPoolExecutor, long j, TimeUnit timeUnit) {
        MoreExecutors.access$000(threadPoolExecutor);
        ExecutorService unconfigurableExecutorService = Executors.unconfigurableExecutorService(threadPoolExecutor);
        addDelayedShutdownHook(unconfigurableExecutorService, j, timeUnit);
        return unconfigurableExecutorService;
    }

    final ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, long j, TimeUnit timeUnit) {
        MoreExecutors.access$000(scheduledThreadPoolExecutor);
        Object unconfigurableScheduledExecutorService = Executors.unconfigurableScheduledExecutorService(scheduledThreadPoolExecutor);
        addDelayedShutdownHook(unconfigurableScheduledExecutorService, j, timeUnit);
        return unconfigurableScheduledExecutorService;
    }

    final void addDelayedShutdownHook(ExecutorService executorService, long j, TimeUnit timeUnit) {
        Preconditions.checkNotNull(executorService);
        Preconditions.checkNotNull(timeUnit);
        String valueOf = String.valueOf(String.valueOf(executorService));
        final ExecutorService executorService2 = executorService;
        final long j2 = j;
        final TimeUnit timeUnit2 = timeUnit;
        addShutdownHook(MoreExecutors.newThread(new StringBuilder(valueOf.length() + 24).append("DelayedShutdownHook-for-").append(valueOf).toString(), new Runnable() {
            public void run() {
                try {
                    executorService2.shutdown();
                    executorService2.awaitTermination(j2, timeUnit2);
                } catch (InterruptedException e) {
                }
            }
        }));
    }

    final ExecutorService getExitingExecutorService(ThreadPoolExecutor threadPoolExecutor) {
        return getExitingExecutorService(threadPoolExecutor, 120, TimeUnit.SECONDS);
    }

    final ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        return getExitingScheduledExecutorService(scheduledThreadPoolExecutor, 120, TimeUnit.SECONDS);
    }

    @VisibleForTesting
    void addShutdownHook(Thread thread) {
        Runtime.getRuntime().addShutdownHook(thread);
    }
}
