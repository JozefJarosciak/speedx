package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Beta
public final class Monitor {
    private Guard activeGuards;
    private final boolean fair;
    private final ReentrantLock lock;

    @Beta
    public static abstract class Guard {
        final Condition condition;
        final Monitor monitor;
        Guard next;
        int waiterCount = 0;

        public abstract boolean isSatisfied();

        protected Guard(Monitor monitor) {
            this.monitor = (Monitor) Preconditions.checkNotNull(monitor, "monitor");
            this.condition = monitor.lock.newCondition();
        }
    }

    public Monitor() {
        this(false);
    }

    public Monitor(boolean z) {
        this.activeGuards = null;
        this.fair = z;
        this.lock = new ReentrantLock(z);
    }

    public void enter() {
        this.lock.lock();
    }

    public void enterInterruptibly() throws InterruptedException {
        this.lock.lockInterruptibly();
    }

    public boolean enter(long j, TimeUnit timeUnit) {
        boolean interrupted;
        Throwable th;
        boolean z = true;
        long toNanos = timeUnit.toNanos(j);
        ReentrantLock reentrantLock = this.lock;
        if (this.fair || !reentrantLock.tryLock()) {
            long nanoTime = System.nanoTime() + toNanos;
            interrupted = Thread.interrupted();
            while (true) {
                try {
                    z = reentrantLock.tryLock(toNanos, TimeUnit.NANOSECONDS);
                    break;
                } catch (InterruptedException e) {
                    toNanos = nanoTime - System.nanoTime();
                    interrupted = z;
                } catch (Throwable th2) {
                    Throwable th3 = th2;
                    interrupted = z;
                    th = th3;
                }
            }
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
        return z;
        if (interrupted) {
            Thread.currentThread().interrupt();
        }
        throw th;
    }

    public boolean enterInterruptibly(long j, TimeUnit timeUnit) throws InterruptedException {
        return this.lock.tryLock(j, timeUnit);
    }

    public boolean tryEnter() {
        return this.lock.tryLock();
    }

    public void enterWhen(Guard guard) throws InterruptedException {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
        reentrantLock.lockInterruptibly();
        try {
            if (!guard.isSatisfied()) {
                await(guard, isHeldByCurrentThread);
            }
        } catch (Throwable th) {
            leave();
        }
    }

    public void enterWhenUninterruptibly(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
        reentrantLock.lock();
        try {
            if (!guard.isSatisfied()) {
                awaitUninterruptibly(guard, isHeldByCurrentThread);
            }
        } catch (Throwable th) {
            leave();
        }
    }

    public boolean enterWhen(Guard guard, long j, TimeUnit timeUnit) throws InterruptedException {
        long toNanos = timeUnit.toNanos(j);
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        boolean isHeldByCurrentThread = reentrantLock.isHeldByCurrentThread();
        if (this.fair || !reentrantLock.tryLock()) {
            toNanos += System.nanoTime();
            if (!reentrantLock.tryLock(j, timeUnit)) {
                return false;
            }
            toNanos -= System.nanoTime();
        }
        try {
            boolean z = guard.isSatisfied() || awaitNanos(guard, toNanos, isHeldByCurrentThread);
            if (!z) {
                reentrantLock.unlock();
            }
            return z;
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean enterWhenUninterruptibly(com.google.common.util.concurrent.Monitor.Guard r11, long r12, java.util.concurrent.TimeUnit r14) {
        /*
        r10 = this;
        r2 = r14.toNanos(r12);
        r0 = r11.monitor;
        if (r0 == r10) goto L_0x000e;
    L_0x0008:
        r0 = new java.lang.IllegalMonitorStateException;
        r0.<init>();
        throw r0;
    L_0x000e:
        r5 = r10.lock;
        r0 = java.lang.System.nanoTime();
        r6 = r0 + r2;
        r4 = r5.isHeldByCurrentThread();
        r0 = java.lang.Thread.interrupted();
        r1 = r10.fair;	 Catch:{ all -> 0x008a }
        if (r1 != 0) goto L_0x0028;
    L_0x0022:
        r1 = r5.tryLock();	 Catch:{ all -> 0x008a }
        if (r1 != 0) goto L_0x004a;
    L_0x0028:
        r1 = 0;
        r9 = r1;
        r1 = r0;
        r0 = r9;
    L_0x002c:
        r8 = java.util.concurrent.TimeUnit.NANOSECONDS;	 Catch:{ InterruptedException -> 0x0068 }
        r0 = r5.tryLock(r2, r8);	 Catch:{ InterruptedException -> 0x0068 }
        if (r0 != 0) goto L_0x003f;
    L_0x0034:
        r0 = 0;
        if (r1 == 0) goto L_0x003e;
    L_0x0037:
        r1 = java.lang.Thread.currentThread();
        r1.interrupt();
    L_0x003e:
        return r0;
    L_0x003f:
        r9 = r0;
        r0 = r1;
        r1 = r9;
    L_0x0042:
        r2 = java.lang.System.nanoTime();	 Catch:{ all -> 0x008f }
        r2 = r6 - r2;
        if (r1 == 0) goto L_0x0094;
    L_0x004a:
        r1 = r0;
        r0 = r4;
    L_0x004c:
        r4 = r11.isSatisfied();	 Catch:{ InterruptedException -> 0x0070 }
        if (r4 != 0) goto L_0x0058;
    L_0x0052:
        r0 = r10.awaitNanos(r11, r2, r0);	 Catch:{ InterruptedException -> 0x0070 }
        if (r0 == 0) goto L_0x006e;
    L_0x0058:
        r0 = 1;
    L_0x0059:
        if (r0 != 0) goto L_0x005e;
    L_0x005b:
        r5.unlock();	 Catch:{ all -> 0x007f }
    L_0x005e:
        if (r1 == 0) goto L_0x003e;
    L_0x0060:
        r1 = java.lang.Thread.currentThread();
        r1.interrupt();
        goto L_0x003e;
    L_0x0068:
        r1 = move-exception;
        r1 = 1;
        r9 = r0;
        r0 = r1;
        r1 = r9;
        goto L_0x0042;
    L_0x006e:
        r0 = 0;
        goto L_0x0059;
    L_0x0070:
        r0 = move-exception;
        r1 = 1;
        r0 = 0;
        r2 = java.lang.System.nanoTime();	 Catch:{ all -> 0x007a }
        r2 = r6 - r2;
        goto L_0x004c;
    L_0x007a:
        r0 = move-exception;
        r5.unlock();	 Catch:{ all -> 0x007f }
        throw r0;	 Catch:{ all -> 0x007f }
    L_0x007f:
        r0 = move-exception;
    L_0x0080:
        if (r1 == 0) goto L_0x0089;
    L_0x0082:
        r1 = java.lang.Thread.currentThread();
        r1.interrupt();
    L_0x0089:
        throw r0;
    L_0x008a:
        r1 = move-exception;
        r9 = r1;
        r1 = r0;
        r0 = r9;
        goto L_0x0080;
    L_0x008f:
        r1 = move-exception;
        r9 = r1;
        r1 = r0;
        r0 = r9;
        goto L_0x0080;
    L_0x0094:
        r9 = r1;
        r1 = r0;
        r0 = r9;
        goto L_0x002c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Monitor.enterWhenUninterruptibly(com.google.common.util.concurrent.Monitor$Guard, long, java.util.concurrent.TimeUnit):boolean");
    }

    public boolean enterIf(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lock();
        try {
            boolean isSatisfied = guard.isSatisfied();
            if (!isSatisfied) {
            }
            return isSatisfied;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean enterIfInterruptibly(Guard guard) throws InterruptedException {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        reentrantLock.lockInterruptibly();
        try {
            boolean isSatisfied = guard.isSatisfied();
            if (!isSatisfied) {
            }
            return isSatisfied;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean enterIf(Guard guard, long j, TimeUnit timeUnit) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        } else if (!enter(j, timeUnit)) {
            return false;
        } else {
            try {
                boolean isSatisfied = guard.isSatisfied();
                return !isSatisfied ? isSatisfied : isSatisfied;
            } finally {
                this.lock.unlock();
            }
        }
    }

    public boolean enterIfInterruptibly(Guard guard, long j, TimeUnit timeUnit) throws InterruptedException {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        if (!reentrantLock.tryLock(j, timeUnit)) {
            return false;
        }
        try {
            boolean isSatisfied = guard.isSatisfied();
            return !isSatisfied ? isSatisfied : isSatisfied;
        } finally {
            reentrantLock.unlock();
        }
    }

    public boolean tryEnterIf(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock reentrantLock = this.lock;
        if (!reentrantLock.tryLock()) {
            return false;
        }
        try {
            boolean isSatisfied = guard.isSatisfied();
            return !isSatisfied ? isSatisfied : isSatisfied;
        } finally {
            reentrantLock.unlock();
        }
    }

    public void waitFor(Guard guard) throws InterruptedException {
        if (((guard.monitor == this ? 1 : 0) & this.lock.isHeldByCurrentThread()) == 0) {
            throw new IllegalMonitorStateException();
        } else if (!guard.isSatisfied()) {
            await(guard, true);
        }
    }

    public void waitForUninterruptibly(Guard guard) {
        if (((guard.monitor == this ? 1 : 0) & this.lock.isHeldByCurrentThread()) == 0) {
            throw new IllegalMonitorStateException();
        } else if (!guard.isSatisfied()) {
            awaitUninterruptibly(guard, true);
        }
    }

    public boolean waitFor(Guard guard, long j, TimeUnit timeUnit) throws InterruptedException {
        int i;
        long toNanos = timeUnit.toNanos(j);
        if (guard.monitor == this) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i & this.lock.isHeldByCurrentThread()) == 0) {
            throw new IllegalMonitorStateException();
        } else if (guard.isSatisfied() || awaitNanos(guard, toNanos, true)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean waitForUninterruptibly(Guard guard, long j, TimeUnit timeUnit) {
        int i;
        Throwable th;
        boolean z = true;
        long toNanos = timeUnit.toNanos(j);
        if (guard.monitor == this) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i & this.lock.isHeldByCurrentThread()) == 0) {
            throw new IllegalMonitorStateException();
        }
        if (!guard.isSatisfied()) {
            long nanoTime = System.nanoTime() + toNanos;
            boolean interrupted = Thread.interrupted();
            boolean z2 = true;
            while (true) {
                try {
                    z = awaitNanos(guard, toNanos, z2);
                    break;
                } catch (InterruptedException e) {
                    if (guard.isSatisfied()) {
                        Thread.currentThread().interrupt();
                    } else {
                        toNanos = nanoTime - System.nanoTime();
                        interrupted = z;
                        z2 = false;
                    }
                } catch (Throwable th2) {
                    th = th2;
                }
            }
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
        return z;
        if (z) {
            Thread.currentThread().interrupt();
        }
        throw th;
    }

    public void leave() {
        ReentrantLock reentrantLock = this.lock;
        try {
            if (reentrantLock.getHoldCount() == 1) {
                signalNextWaiter();
            }
            reentrantLock.unlock();
        } catch (Throwable th) {
            reentrantLock.unlock();
        }
    }

    public boolean isFair() {
        return this.fair;
    }

    public boolean isOccupied() {
        return this.lock.isLocked();
    }

    public boolean isOccupiedByCurrentThread() {
        return this.lock.isHeldByCurrentThread();
    }

    public int getOccupiedDepth() {
        return this.lock.getHoldCount();
    }

    public int getQueueLength() {
        return this.lock.getQueueLength();
    }

    public boolean hasQueuedThreads() {
        return this.lock.hasQueuedThreads();
    }

    public boolean hasQueuedThread(Thread thread) {
        return this.lock.hasQueuedThread(thread);
    }

    public boolean hasWaiters(Guard guard) {
        return getWaitQueueLength(guard) > 0;
    }

    public int getWaitQueueLength(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        this.lock.lock();
        try {
            int i = guard.waiterCount;
            return i;
        } finally {
            this.lock.unlock();
        }
    }

    private void signalNextWaiter() {
        for (Guard guard = this.activeGuards; guard != null; guard = guard.next) {
            if (isSatisfied(guard)) {
                guard.condition.signal();
                return;
            }
        }
    }

    private boolean isSatisfied(Guard guard) {
        try {
            return guard.isSatisfied();
        } catch (Throwable th) {
            signalAllWaiters();
            RuntimeException propagate = Throwables.propagate(th);
        }
    }

    private void signalAllWaiters() {
        for (Guard guard = this.activeGuards; guard != null; guard = guard.next) {
            guard.condition.signalAll();
        }
    }

    private void beginWaitingFor(Guard guard) {
        int i = guard.waiterCount;
        guard.waiterCount = i + 1;
        if (i == 0) {
            guard.next = this.activeGuards;
            this.activeGuards = guard;
        }
    }

    private void endWaitingFor(Guard guard) {
        int i = guard.waiterCount - 1;
        guard.waiterCount = i;
        if (i == 0) {
            Guard guard2 = this.activeGuards;
            Guard guard3 = null;
            while (guard2 != guard) {
                Guard guard4 = guard2;
                guard2 = guard2.next;
                guard3 = guard4;
            }
            if (guard3 == null) {
                this.activeGuards = guard2.next;
            } else {
                guard3.next = guard2.next;
            }
            guard2.next = null;
        }
    }

    private void await(Guard guard, boolean z) throws InterruptedException {
        if (z) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        while (true) {
            try {
                guard.condition.await();
                if (guard.isSatisfied()) {
                    break;
                }
            } finally {
                endWaitingFor(guard);
            }
        }
    }

    private void awaitUninterruptibly(Guard guard, boolean z) {
        if (z) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        while (true) {
            try {
                guard.condition.awaitUninterruptibly();
                if (guard.isSatisfied()) {
                    break;
                }
            } finally {
                endWaitingFor(guard);
            }
        }
    }

    private boolean awaitNanos(Guard guard, long j, boolean z) throws InterruptedException {
        if (z) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        while (j >= 0) {
            try {
                j = guard.condition.awaitNanos(j);
                if (guard.isSatisfied()) {
                    return true;
                }
            } finally {
                endWaitingFor(guard);
            }
        }
        endWaitingFor(guard);
        return false;
    }
}
