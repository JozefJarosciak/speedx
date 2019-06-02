package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import java.util.concurrent.Callable;

public final class Callables {
    private Callables() {
    }

    public static <T> Callable<T> returning(final T t) {
        return new Callable<T>() {
            public T call() {
                return t;
            }
        };
    }

    static <T> Callable<T> threadRenaming(final Callable<T> callable, final Supplier<String> supplier) {
        Preconditions.checkNotNull(supplier);
        Preconditions.checkNotNull(callable);
        return new Callable<T>() {
            public T call() throws Exception {
                Thread currentThread = Thread.currentThread();
                String name = currentThread.getName();
                boolean access$000 = Callables.trySetName((String) supplier.get(), currentThread);
                try {
                    T call = callable.call();
                    return call;
                } finally {
                    if (access$000) {
                        Callables.trySetName(name, currentThread);
                    }
                }
            }
        };
    }

    static Runnable threadRenaming(final Runnable runnable, final Supplier<String> supplier) {
        Preconditions.checkNotNull(supplier);
        Preconditions.checkNotNull(runnable);
        return new Runnable() {
            public void run() {
                Thread currentThread = Thread.currentThread();
                String name = currentThread.getName();
                boolean access$000 = Callables.trySetName((String) supplier.get(), currentThread);
                try {
                    runnable.run();
                } finally {
                    if (access$000) {
                        Callables.trySetName(name, currentThread);
                    }
                }
            }
        };
    }

    private static boolean trySetName(String str, Thread thread) {
        try {
            thread.setName(str);
            return true;
        } catch (SecurityException e) {
            return false;
        }
    }
}
