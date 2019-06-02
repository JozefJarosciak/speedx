package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Throwables;
import java.lang.reflect.InvocationTargetException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public final class MoreExecutors {
    private MoreExecutors() {
    }

    @Beta
    public static ExecutorService getExitingExecutorService(ThreadPoolExecutor threadPoolExecutor, long j, TimeUnit timeUnit) {
        return new MoreExecutors$Application().getExitingExecutorService(threadPoolExecutor, j, timeUnit);
    }

    @Beta
    public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor, long j, TimeUnit timeUnit) {
        return new MoreExecutors$Application().getExitingScheduledExecutorService(scheduledThreadPoolExecutor, j, timeUnit);
    }

    @Beta
    public static void addDelayedShutdownHook(ExecutorService executorService, long j, TimeUnit timeUnit) {
        new MoreExecutors$Application().addDelayedShutdownHook(executorService, j, timeUnit);
    }

    @Beta
    public static ExecutorService getExitingExecutorService(ThreadPoolExecutor threadPoolExecutor) {
        return new MoreExecutors$Application().getExitingExecutorService(threadPoolExecutor);
    }

    @Beta
    public static ScheduledExecutorService getExitingScheduledExecutorService(ScheduledThreadPoolExecutor scheduledThreadPoolExecutor) {
        return new MoreExecutors$Application().getExitingScheduledExecutorService(scheduledThreadPoolExecutor);
    }

    private static void useDaemonThreadFactory(ThreadPoolExecutor threadPoolExecutor) {
        threadPoolExecutor.setThreadFactory(new ThreadFactoryBuilder().setDaemon(true).setThreadFactory(threadPoolExecutor.getThreadFactory()).build());
    }

    @Deprecated
    public static ListeningExecutorService sameThreadExecutor() {
        return new MoreExecutors$DirectExecutorService(null);
    }

    public static ListeningExecutorService newDirectExecutorService() {
        return new MoreExecutors$DirectExecutorService(null);
    }

    public static Executor directExecutor() {
        return MoreExecutors$DirectExecutor.INSTANCE;
    }

    public static ListeningExecutorService listeningDecorator(ExecutorService executorService) {
        if (executorService instanceof ListeningExecutorService) {
            return (ListeningExecutorService) executorService;
        }
        return executorService instanceof ScheduledExecutorService ? new MoreExecutors$ScheduledListeningDecorator((ScheduledExecutorService) executorService) : new MoreExecutors$ListeningDecorator(executorService);
    }

    public static ListeningScheduledExecutorService listeningDecorator(ScheduledExecutorService scheduledExecutorService) {
        return scheduledExecutorService instanceof ListeningScheduledExecutorService ? (ListeningScheduledExecutorService) scheduledExecutorService : new MoreExecutors$ScheduledListeningDecorator(scheduledExecutorService);
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static <T> T invokeAnyImpl(com.google.common.util.concurrent.ListeningExecutorService r19, java.util.Collection<? extends java.util.concurrent.Callable<T>> r20, boolean r21, long r22) throws java.lang.InterruptedException, java.util.concurrent.ExecutionException, java.util.concurrent.TimeoutException {
        /*
        com.google.common.base.Preconditions.checkNotNull(r19);
        r3 = r20.size();
        if (r3 <= 0) goto L_0x0073;
    L_0x0009:
        r2 = 1;
    L_0x000a:
        com.google.common.base.Preconditions.checkArgument(r2);
        r12 = com.google.common.collect.Lists.newArrayListWithCapacity(r3);
        r13 = com.google.common.collect.Queues.newLinkedBlockingQueue();
        r4 = 0;
        if (r21 == 0) goto L_0x0075;
    L_0x0018:
        r6 = java.lang.System.nanoTime();	 Catch:{ all -> 0x0083 }
    L_0x001c:
        r14 = r20.iterator();	 Catch:{ all -> 0x0083 }
        r2 = r14.next();	 Catch:{ all -> 0x0083 }
        r2 = (java.util.concurrent.Callable) r2;	 Catch:{ all -> 0x0083 }
        r0 = r19;
        r2 = submitAndAddQueueListener(r0, r2, r13);	 Catch:{ all -> 0x0083 }
        r12.add(r2);	 Catch:{ all -> 0x0083 }
        r3 = r3 + -1;
        r5 = 1;
        r10 = r22;
    L_0x0034:
        r2 = r13.poll();	 Catch:{ all -> 0x0083 }
        r2 = (java.util.concurrent.Future) r2;	 Catch:{ all -> 0x0083 }
        if (r2 != 0) goto L_0x00dc;
    L_0x003c:
        if (r3 <= 0) goto L_0x0078;
    L_0x003e:
        r8 = r3 + -1;
        r3 = r14.next();	 Catch:{ all -> 0x0083 }
        r3 = (java.util.concurrent.Callable) r3;	 Catch:{ all -> 0x0083 }
        r0 = r19;
        r3 = submitAndAddQueueListener(r0, r3, r13);	 Catch:{ all -> 0x0083 }
        r12.add(r3);	 Catch:{ all -> 0x0083 }
        r3 = r5 + 1;
        r5 = r8;
        r8 = r10;
        r15 = r2;
        r2 = r3;
        r3 = r15;
    L_0x0056:
        if (r3 == 0) goto L_0x00da;
    L_0x0058:
        r2 = r2 + -1;
        r3 = r3.get();	 Catch:{ ExecutionException -> 0x00d7, RuntimeException -> 0x00ca }
        r4 = r12.iterator();
    L_0x0062:
        r2 = r4.hasNext();
        if (r2 == 0) goto L_0x00d9;
    L_0x0068:
        r2 = r4.next();
        r2 = (java.util.concurrent.Future) r2;
        r5 = 1;
        r2.cancel(r5);
        goto L_0x0062;
    L_0x0073:
        r2 = 0;
        goto L_0x000a;
    L_0x0075:
        r6 = 0;
        goto L_0x001c;
    L_0x0078:
        if (r5 != 0) goto L_0x009a;
    L_0x007a:
        if (r4 != 0) goto L_0x0082;
    L_0x007c:
        r4 = new java.util.concurrent.ExecutionException;	 Catch:{ all -> 0x0083 }
        r2 = 0;
        r4.<init>(r2);	 Catch:{ all -> 0x0083 }
    L_0x0082:
        throw r4;	 Catch:{ all -> 0x0083 }
    L_0x0083:
        r2 = move-exception;
        r3 = r2;
        r4 = r12.iterator();
    L_0x0089:
        r2 = r4.hasNext();
        if (r2 == 0) goto L_0x00d6;
    L_0x008f:
        r2 = r4.next();
        r2 = (java.util.concurrent.Future) r2;
        r5 = 1;
        r2.cancel(r5);
        goto L_0x0089;
    L_0x009a:
        if (r21 == 0) goto L_0x00be;
    L_0x009c:
        r2 = java.util.concurrent.TimeUnit.NANOSECONDS;	 Catch:{ all -> 0x0083 }
        r2 = r13.poll(r10, r2);	 Catch:{ all -> 0x0083 }
        r2 = (java.util.concurrent.Future) r2;	 Catch:{ all -> 0x0083 }
        if (r2 != 0) goto L_0x00ac;
    L_0x00a6:
        r2 = new java.util.concurrent.TimeoutException;	 Catch:{ all -> 0x0083 }
        r2.<init>();	 Catch:{ all -> 0x0083 }
        throw r2;	 Catch:{ all -> 0x0083 }
    L_0x00ac:
        r8 = java.lang.System.nanoTime();	 Catch:{ all -> 0x0083 }
        r6 = r8 - r6;
        r6 = r10 - r6;
        r15 = r2;
        r2 = r5;
        r5 = r3;
        r3 = r15;
        r16 = r8;
        r8 = r6;
        r6 = r16;
        goto L_0x0056;
    L_0x00be:
        r2 = r13.take();	 Catch:{ all -> 0x0083 }
        r2 = (java.util.concurrent.Future) r2;	 Catch:{ all -> 0x0083 }
        r8 = r10;
        r15 = r5;
        r5 = r3;
        r3 = r2;
        r2 = r15;
        goto L_0x0056;
    L_0x00ca:
        r4 = move-exception;
        r3 = new java.util.concurrent.ExecutionException;	 Catch:{ all -> 0x0083 }
        r3.<init>(r4);	 Catch:{ all -> 0x0083 }
    L_0x00d0:
        r4 = r3;
        r10 = r8;
        r3 = r5;
        r5 = r2;
        goto L_0x0034;
    L_0x00d6:
        throw r3;
    L_0x00d7:
        r3 = move-exception;
        goto L_0x00d0;
    L_0x00d9:
        return r3;
    L_0x00da:
        r3 = r4;
        goto L_0x00d0;
    L_0x00dc:
        r8 = r10;
        r15 = r5;
        r5 = r3;
        r3 = r2;
        r2 = r15;
        goto L_0x0056;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.MoreExecutors.invokeAnyImpl(com.google.common.util.concurrent.ListeningExecutorService, java.util.Collection, boolean, long):T");
    }

    private static <T> ListenableFuture<T> submitAndAddQueueListener(ListeningExecutorService listeningExecutorService, Callable<T> callable, BlockingQueue<Future<T>> blockingQueue) {
        ListenableFuture<T> submit = listeningExecutorService.submit(callable);
        submit.addListener(new MoreExecutors$1(blockingQueue, submit), directExecutor());
        return submit;
    }

    @Beta
    public static ThreadFactory platformThreadFactory() {
        if (!isAppEngine()) {
            return Executors.defaultThreadFactory();
        }
        try {
            return (ThreadFactory) Class.forName("com.google.appengine.api.ThreadManager").getMethod("currentRequestThreadFactory", new Class[0]).invoke(null, new Object[0]);
        } catch (Throwable e) {
            throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", e);
        } catch (Throwable e2) {
            throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", e2);
        } catch (Throwable e22) {
            throw new RuntimeException("Couldn't invoke ThreadManager.currentRequestThreadFactory", e22);
        } catch (InvocationTargetException e3) {
            throw Throwables.propagate(e3.getCause());
        }
    }

    private static boolean isAppEngine() {
        if (System.getProperty("com.google.appengine.runtime.environment") == null) {
            return false;
        }
        try {
            if (Class.forName("com.google.apphosting.api.ApiProxy").getMethod("getCurrentEnvironment", new Class[0]).invoke(null, new Object[0]) != null) {
                return true;
            }
            return false;
        } catch (ClassNotFoundException e) {
            return false;
        } catch (InvocationTargetException e2) {
            return false;
        } catch (IllegalAccessException e3) {
            return false;
        } catch (NoSuchMethodException e4) {
            return false;
        }
    }

    static Thread newThread(String str, Runnable runnable) {
        Preconditions.checkNotNull(str);
        Preconditions.checkNotNull(runnable);
        Thread newThread = platformThreadFactory().newThread(runnable);
        try {
            newThread.setName(str);
        } catch (SecurityException e) {
        }
        return newThread;
    }

    static Executor renamingDecorator(Executor executor, Supplier<String> supplier) {
        Preconditions.checkNotNull(executor);
        Preconditions.checkNotNull(supplier);
        return isAppEngine() ? executor : new MoreExecutors$2(executor, supplier);
    }

    static ExecutorService renamingDecorator(ExecutorService executorService, Supplier<String> supplier) {
        Preconditions.checkNotNull(executorService);
        Preconditions.checkNotNull(supplier);
        return isAppEngine() ? executorService : new MoreExecutors$3(executorService, supplier);
    }

    static ScheduledExecutorService renamingDecorator(ScheduledExecutorService scheduledExecutorService, Supplier<String> supplier) {
        Preconditions.checkNotNull(scheduledExecutorService);
        Preconditions.checkNotNull(supplier);
        return isAppEngine() ? scheduledExecutorService : new MoreExecutors$4(scheduledExecutorService, supplier);
    }

    @Beta
    public static boolean shutdownAndAwaitTermination(ExecutorService executorService, long j, TimeUnit timeUnit) {
        Preconditions.checkNotNull(timeUnit);
        executorService.shutdown();
        try {
            long convert = TimeUnit.NANOSECONDS.convert(j, timeUnit) / 2;
            if (!executorService.awaitTermination(convert, TimeUnit.NANOSECONDS)) {
                executorService.shutdownNow();
                executorService.awaitTermination(convert, TimeUnit.NANOSECONDS);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            executorService.shutdownNow();
        }
        return executorService.isTerminated();
    }
}
