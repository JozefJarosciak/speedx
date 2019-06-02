package com.google.common.util.concurrent;

import com.google.common.base.Supplier;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledExecutorService;

class MoreExecutors$4 extends WrappingScheduledExecutorService {
    final /* synthetic */ Supplier val$nameSupplier;

    MoreExecutors$4(ScheduledExecutorService scheduledExecutorService, Supplier supplier) {
        this.val$nameSupplier = supplier;
        super(scheduledExecutorService);
    }

    protected <T> Callable<T> wrapTask(Callable<T> callable) {
        return Callables.threadRenaming((Callable) callable, this.val$nameSupplier);
    }

    protected Runnable wrapTask(Runnable runnable) {
        return Callables.threadRenaming(runnable, this.val$nameSupplier);
    }
}
