package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

final class SerializingExecutor implements Executor {
    private static final Logger log = Logger.getLogger(SerializingExecutor.class.getName());
    private final Executor executor;
    private final Object internalLock = new C38921();
    private boolean isThreadScheduled = false;
    private final TaskRunner taskRunner = new TaskRunner();
    private final Queue<Runnable> waitQueue = new ArrayDeque();

    /* renamed from: com.google.common.util.concurrent.SerializingExecutor$1 */
    class C38921 {
        C38921() {
        }

        public String toString() {
            String str = "SerializingExecutor lock: ";
            String valueOf = String.valueOf(super.toString());
            return valueOf.length() != 0 ? str.concat(valueOf) : new String(str);
        }
    }

    private class TaskRunner implements Runnable {
        private TaskRunner() {
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r8 = this;
            r1 = 0;
            r2 = 1;
        L_0x0002:
            r0 = com.google.common.util.concurrent.SerializingExecutor.this;	 Catch:{ all -> 0x0059 }
            r0 = r0.isThreadScheduled;	 Catch:{ all -> 0x0059 }
            com.google.common.base.Preconditions.checkState(r0);	 Catch:{ all -> 0x0059 }
            r0 = com.google.common.util.concurrent.SerializingExecutor.this;	 Catch:{ all -> 0x0059 }
            r3 = r0.internalLock;	 Catch:{ all -> 0x0059 }
            monitor-enter(r3);	 Catch:{ all -> 0x0059 }
            r0 = com.google.common.util.concurrent.SerializingExecutor.this;	 Catch:{ all -> 0x006b }
            r0 = r0.waitQueue;	 Catch:{ all -> 0x006b }
            r0 = r0.poll();	 Catch:{ all -> 0x006b }
            r0 = (java.lang.Runnable) r0;	 Catch:{ all -> 0x006b }
            if (r0 != 0) goto L_0x0028;
        L_0x0020:
            r0 = com.google.common.util.concurrent.SerializingExecutor.this;	 Catch:{ all -> 0x006b }
            r4 = 0;
            r0.isThreadScheduled = r4;	 Catch:{ all -> 0x006b }
            monitor-exit(r3);	 Catch:{ all -> 0x0075 }
            return;
        L_0x0028:
            monitor-exit(r3);	 Catch:{ all -> 0x006b }
            r0.run();	 Catch:{ RuntimeException -> 0x002d }
            goto L_0x0002;
        L_0x002d:
            r3 = move-exception;
            r4 = com.google.common.util.concurrent.SerializingExecutor.log;	 Catch:{ all -> 0x0059 }
            r5 = java.util.logging.Level.SEVERE;	 Catch:{ all -> 0x0059 }
            r0 = java.lang.String.valueOf(r0);	 Catch:{ all -> 0x0059 }
            r0 = java.lang.String.valueOf(r0);	 Catch:{ all -> 0x0059 }
            r6 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0059 }
            r7 = r0.length();	 Catch:{ all -> 0x0059 }
            r7 = r7 + 35;
            r6.<init>(r7);	 Catch:{ all -> 0x0059 }
            r7 = "Exception while executing runnable ";
            r6 = r6.append(r7);	 Catch:{ all -> 0x0059 }
            r0 = r6.append(r0);	 Catch:{ all -> 0x0059 }
            r0 = r0.toString();	 Catch:{ all -> 0x0059 }
            r4.log(r5, r0, r3);	 Catch:{ all -> 0x0059 }
            goto L_0x0002;
        L_0x0059:
            r0 = move-exception;
        L_0x005a:
            if (r2 == 0) goto L_0x006a;
        L_0x005c:
            r1 = com.google.common.util.concurrent.SerializingExecutor.this;
            r1 = r1.internalLock;
            monitor-enter(r1);
            r2 = com.google.common.util.concurrent.SerializingExecutor.this;	 Catch:{ all -> 0x0072 }
            r3 = 0;
            r2.isThreadScheduled = r3;	 Catch:{ all -> 0x0072 }
            monitor-exit(r1);	 Catch:{ all -> 0x0072 }
        L_0x006a:
            throw r0;
        L_0x006b:
            r0 = move-exception;
            r1 = r2;
        L_0x006d:
            monitor-exit(r3);	 Catch:{ all -> 0x0075 }
            throw r0;	 Catch:{ all -> 0x006f }
        L_0x006f:
            r0 = move-exception;
            r2 = r1;
            goto L_0x005a;
        L_0x0072:
            r0 = move-exception;
            monitor-exit(r1);	 Catch:{ all -> 0x0072 }
            throw r0;
        L_0x0075:
            r0 = move-exception;
            goto L_0x006d;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.SerializingExecutor.TaskRunner.run():void");
        }
    }

    public SerializingExecutor(Executor executor) {
        Preconditions.checkNotNull(executor, "'executor' must not be null.");
        this.executor = executor;
    }

    public void execute(Runnable runnable) {
        Object obj = 1;
        Preconditions.checkNotNull(runnable, "'r' must not be null.");
        synchronized (this.internalLock) {
            this.waitQueue.add(runnable);
            if (this.isThreadScheduled) {
                obj = null;
            } else {
                this.isThreadScheduled = true;
            }
        }
        if (obj != null) {
            try {
                this.executor.execute(this.taskRunner);
            } catch (Throwable th) {
                synchronized (this.internalLock) {
                    this.isThreadScheduled = false;
                }
            }
        }
    }
}
