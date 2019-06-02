package com.google.common.util.concurrent;

import com.google.common.base.Supplier;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;

class MoreExecutors$3 extends WrappingExecutorService {
    final /* synthetic */ Supplier val$nameSupplier;

    MoreExecutors$3(ExecutorService executorService, Supplier supplier) {
        this.val$nameSupplier = supplier;
        super(executorService);
    }

    protected <T> Callable<T> wrapTask(Callable<T> callable) {
        return Callables.threadRenaming((Callable) callable, this.val$nameSupplier);
    }

    protected Runnable wrapTask(Runnable runnable) {
        return Callables.threadRenaming(runnable, this.val$nameSupplier);
    }
}
