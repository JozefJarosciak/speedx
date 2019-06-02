package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;

final class ListenerCallQueue<L> implements Runnable {
    private static final Logger logger = Logger.getLogger(ListenerCallQueue.class.getName());
    private final Executor executor;
    private boolean isThreadScheduled;
    private final L listener;
    private final Queue<Callback<L>> waitQueue = Queues.newArrayDeque();

    static abstract class Callback<L> {
        private final String methodCall;

        abstract void call(L l);

        Callback(String str) {
            this.methodCall = str;
        }

        void enqueueOn(Iterable<ListenerCallQueue<L>> iterable) {
            for (ListenerCallQueue add : iterable) {
                add.add(this);
            }
        }
    }

    ListenerCallQueue(L l, Executor executor) {
        this.listener = Preconditions.checkNotNull(l);
        this.executor = (Executor) Preconditions.checkNotNull(executor);
    }

    synchronized void add(Callback<L> callback) {
        this.waitQueue.add(callback);
    }

    void execute() {
        Object obj = 1;
        synchronized (this) {
            if (this.isThreadScheduled) {
                obj = null;
            } else {
                this.isThreadScheduled = true;
            }
        }
        if (obj != null) {
            try {
                this.executor.execute(this);
            } catch (Throwable e) {
                synchronized (this) {
                    this.isThreadScheduled = false;
                    Logger logger = logger;
                    Level level = Level.SEVERE;
                    String valueOf = String.valueOf(String.valueOf(this.listener));
                    String valueOf2 = String.valueOf(String.valueOf(this.executor));
                    logger.log(level, new StringBuilder((valueOf.length() + 42) + valueOf2.length()).append("Exception while running callbacks for ").append(valueOf).append(" on ").append(valueOf2).toString(), e);
                    throw e;
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r10 = this;
        r1 = 0;
        r2 = 1;
    L_0x0002:
        monitor-enter(r10);	 Catch:{ all -> 0x0065 }
        r0 = r10.isThreadScheduled;	 Catch:{ all -> 0x006e }
        com.google.common.base.Preconditions.checkState(r0);	 Catch:{ all -> 0x006e }
        r0 = r10.waitQueue;	 Catch:{ all -> 0x006e }
        r0 = r0.poll();	 Catch:{ all -> 0x006e }
        r0 = (com.google.common.util.concurrent.ListenerCallQueue.Callback) r0;	 Catch:{ all -> 0x006e }
        if (r0 != 0) goto L_0x0017;
    L_0x0012:
        r0 = 0;
        r10.isThreadScheduled = r0;	 Catch:{ all -> 0x006e }
        monitor-exit(r10);	 Catch:{ all -> 0x0078 }
        return;
    L_0x0017:
        monitor-exit(r10);	 Catch:{ all -> 0x006e }
        r3 = r10.listener;	 Catch:{ RuntimeException -> 0x001e }
        r0.call(r3);	 Catch:{ RuntimeException -> 0x001e }
        goto L_0x0002;
    L_0x001e:
        r3 = move-exception;
        r4 = logger;	 Catch:{ all -> 0x0065 }
        r5 = java.util.logging.Level.SEVERE;	 Catch:{ all -> 0x0065 }
        r6 = r10.listener;	 Catch:{ all -> 0x0065 }
        r6 = java.lang.String.valueOf(r6);	 Catch:{ all -> 0x0065 }
        r6 = java.lang.String.valueOf(r6);	 Catch:{ all -> 0x0065 }
        r0 = r0.methodCall;	 Catch:{ all -> 0x0065 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ all -> 0x0065 }
        r0 = java.lang.String.valueOf(r0);	 Catch:{ all -> 0x0065 }
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0065 }
        r8 = r6.length();	 Catch:{ all -> 0x0065 }
        r8 = r8 + 37;
        r9 = r0.length();	 Catch:{ all -> 0x0065 }
        r8 = r8 + r9;
        r7.<init>(r8);	 Catch:{ all -> 0x0065 }
        r8 = "Exception while executing callback: ";
        r7 = r7.append(r8);	 Catch:{ all -> 0x0065 }
        r6 = r7.append(r6);	 Catch:{ all -> 0x0065 }
        r7 = ".";
        r6 = r6.append(r7);	 Catch:{ all -> 0x0065 }
        r0 = r6.append(r0);	 Catch:{ all -> 0x0065 }
        r0 = r0.toString();	 Catch:{ all -> 0x0065 }
        r4.log(r5, r0, r3);	 Catch:{ all -> 0x0065 }
        goto L_0x0002;
    L_0x0065:
        r0 = move-exception;
    L_0x0066:
        if (r2 == 0) goto L_0x006d;
    L_0x0068:
        monitor-enter(r10);
        r1 = 0;
        r10.isThreadScheduled = r1;	 Catch:{ all -> 0x0075 }
        monitor-exit(r10);	 Catch:{ all -> 0x0075 }
    L_0x006d:
        throw r0;
    L_0x006e:
        r0 = move-exception;
        r1 = r2;
    L_0x0070:
        monitor-exit(r10);	 Catch:{ all -> 0x0078 }
        throw r0;	 Catch:{ all -> 0x0072 }
    L_0x0072:
        r0 = move-exception;
        r2 = r1;
        goto L_0x0066;
    L_0x0075:
        r0 = move-exception;
        monitor-exit(r10);	 Catch:{ all -> 0x0075 }
        throw r0;
    L_0x0078:
        r0 = move-exception;
        goto L_0x0070;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ListenerCallQueue.run():void");
    }
}
