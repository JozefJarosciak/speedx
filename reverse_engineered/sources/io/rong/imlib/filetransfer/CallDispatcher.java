package io.rong.imlib.filetransfer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class CallDispatcher {
    private static final int MAX_RUNNING_TASK = 4;
    private ExecutorService executorService;
    private final Deque<AsyncCall> readyCalls = new ArrayDeque();
    private final Deque<AsyncCall> runningCalls = new ArrayDeque();

    public synchronized ExecutorService getExecutorService() {
        if (this.executorService == null) {
            this.executorService = new ThreadPoolExecutor(4, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), threadFactory("HttpEngine Dispatcher", false));
        }
        return this.executorService;
    }

    private ThreadFactory threadFactory(final String str, final boolean z) {
        return new ThreadFactory() {
            public Thread newThread(Runnable runnable) {
                Thread thread = new Thread(runnable, str);
                thread.setDaemon(z);
                return thread;
            }
        };
    }

    public synchronized void enqueue(AsyncCall asyncCall) {
        if (this.runningCalls.size() < 4) {
            getExecutorService().execute(asyncCall);
            this.runningCalls.add(asyncCall);
        } else {
            this.readyCalls.add(asyncCall);
        }
    }

    public synchronized void cancel(Object obj) {
        for (AsyncCall asyncCall : this.readyCalls) {
            if (asyncCall.tag().equals(obj)) {
                this.readyCalls.remove(asyncCall);
                asyncCall.cancelDirectly();
            }
        }
        for (AsyncCall asyncCall2 : this.runningCalls) {
            if (asyncCall2.tag().equals(obj)) {
                asyncCall2.cancel();
            }
        }
    }

    public synchronized void finish(AsyncCall asyncCall) {
        if (this.runningCalls.remove(asyncCall)) {
            promoteCalls();
        } else {
            throw new RuntimeException("Not in running list.");
        }
    }

    private void promoteCalls() {
        if (this.runningCalls.size() < 4 && !this.readyCalls.isEmpty()) {
            Iterator it = this.readyCalls.iterator();
            while (it.hasNext()) {
                AsyncCall asyncCall = (AsyncCall) it.next();
                this.runningCalls.add(asyncCall);
                getExecutorService().execute(asyncCall);
                it.remove();
                if (this.runningCalls.size() >= 4) {
                    return;
                }
            }
        }
    }
}
