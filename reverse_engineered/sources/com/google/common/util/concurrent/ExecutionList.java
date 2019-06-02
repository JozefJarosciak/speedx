package com.google.common.util.concurrent;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ExecutionList {
    @VisibleForTesting
    static final Logger log = Logger.getLogger(ExecutionList.class.getName());
    private boolean executed;
    private RunnableExecutorPair runnables;

    private static final class RunnableExecutorPair {
        final Executor executor;
        RunnableExecutorPair next;
        final Runnable runnable;

        RunnableExecutorPair(Runnable runnable, Executor executor, RunnableExecutorPair runnableExecutorPair) {
            this.runnable = runnable;
            this.executor = executor;
            this.next = runnableExecutorPair;
        }
    }

    public void add(Runnable runnable, Executor executor) {
        Preconditions.checkNotNull(runnable, "Runnable was null.");
        Preconditions.checkNotNull(executor, "Executor was null.");
        synchronized (this) {
            if (this.executed) {
                executeListener(runnable, executor);
                return;
            }
            this.runnables = new RunnableExecutorPair(runnable, executor, this.runnables);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void execute() {
        /*
        r3 = this;
        r0 = 0;
        monitor-enter(r3);
        r1 = r3.executed;	 Catch:{ all -> 0x001a }
        if (r1 == 0) goto L_0x0008;
    L_0x0006:
        monitor-exit(r3);	 Catch:{ all -> 0x001a }
    L_0x0007:
        return;
    L_0x0008:
        r1 = 1;
        r3.executed = r1;	 Catch:{ all -> 0x001a }
        r1 = r3.runnables;	 Catch:{ all -> 0x001a }
        r2 = 0;
        r3.runnables = r2;	 Catch:{ all -> 0x001a }
        monitor-exit(r3);	 Catch:{ all -> 0x001a }
    L_0x0011:
        if (r1 == 0) goto L_0x001d;
    L_0x0013:
        r2 = r1.next;
        r1.next = r0;
        r0 = r1;
        r1 = r2;
        goto L_0x0011;
    L_0x001a:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x001a }
        throw r0;
    L_0x001d:
        if (r0 == 0) goto L_0x0007;
    L_0x001f:
        r1 = r0.runnable;
        r2 = r0.executor;
        executeListener(r1, r2);
        r0 = r0.next;
        goto L_0x001d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ExecutionList.execute():void");
    }

    private static void executeListener(Runnable runnable, Executor executor) {
        try {
            executor.execute(runnable);
        } catch (Throwable e) {
            Logger logger = log;
            Level level = Level.SEVERE;
            String valueOf = String.valueOf(String.valueOf(runnable));
            String valueOf2 = String.valueOf(String.valueOf(executor));
            logger.log(level, new StringBuilder((valueOf.length() + 57) + valueOf2.length()).append("RuntimeException while executing runnable ").append(valueOf).append(" with executor ").append(valueOf2).toString(), e);
        }
    }
}
