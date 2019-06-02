package com.google.common.util.concurrent;

import java.util.concurrent.Executor;

enum MoreExecutors$DirectExecutor implements Executor {
    INSTANCE;

    public void execute(Runnable runnable) {
        runnable.run();
    }
}
