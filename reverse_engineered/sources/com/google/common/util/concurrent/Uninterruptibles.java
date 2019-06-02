package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Beta
public final class Uninterruptibles {
    public static void awaitUninterruptibly(CountDownLatch countDownLatch) {
        Object obj = null;
        while (true) {
            try {
                countDownLatch.await();
                break;
            } catch (InterruptedException e) {
                obj = 1;
            } catch (Throwable th) {
                if (obj != null) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (obj != null) {
            Thread.currentThread().interrupt();
        }
    }

    public static boolean awaitUninterruptibly(CountDownLatch countDownLatch, long j, TimeUnit timeUnit) {
        long nanoTime;
        Object obj = null;
        long toNanos;
        try {
            boolean await;
            toNanos = timeUnit.toNanos(j);
            nanoTime = System.nanoTime() + toNanos;
            while (true) {
                await = countDownLatch.await(toNanos, TimeUnit.NANOSECONDS);
                break;
            }
            if (obj != null) {
                Thread.currentThread().interrupt();
            }
            return await;
        } catch (InterruptedException e) {
            obj = 1;
            toNanos = nanoTime - System.nanoTime();
        } catch (Throwable th) {
            if (obj != null) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void joinUninterruptibly(Thread thread) {
        Object obj = null;
        while (true) {
            try {
                thread.join();
                break;
            } catch (InterruptedException e) {
                obj = 1;
            } catch (Throwable th) {
                if (obj != null) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (obj != null) {
            Thread.currentThread().interrupt();
        }
    }

    public static <V> V getUninterruptibly(Future<V> future) throws ExecutionException {
        V v;
        Object obj = null;
        while (true) {
            try {
                v = future.get();
                break;
            } catch (InterruptedException e) {
                obj = 1;
            } catch (Throwable th) {
                if (obj != null) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (obj != null) {
            Thread.currentThread().interrupt();
        }
        return v;
    }

    public static <V> V getUninterruptibly(Future<V> future, long j, TimeUnit timeUnit) throws ExecutionException, TimeoutException {
        long nanoTime;
        Object obj = null;
        long toNanos;
        try {
            V v;
            toNanos = timeUnit.toNanos(j);
            nanoTime = System.nanoTime() + toNanos;
            while (true) {
                v = future.get(toNanos, TimeUnit.NANOSECONDS);
                break;
            }
            if (obj != null) {
                Thread.currentThread().interrupt();
            }
            return v;
        } catch (InterruptedException e) {
            obj = 1;
            toNanos = nanoTime - System.nanoTime();
        } catch (Throwable th) {
            if (obj != null) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void joinUninterruptibly(Thread thread, long j, TimeUnit timeUnit) {
        long nanoTime;
        Preconditions.checkNotNull(thread);
        Object obj = null;
        long toNanos;
        try {
            toNanos = timeUnit.toNanos(j);
            nanoTime = System.nanoTime() + toNanos;
            while (true) {
                TimeUnit.NANOSECONDS.timedJoin(thread, toNanos);
                break;
            }
            if (obj != null) {
                Thread.currentThread().interrupt();
            }
        } catch (InterruptedException e) {
            obj = 1;
            toNanos = nanoTime - System.nanoTime();
        } catch (Throwable th) {
            if (obj != null) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static <E> E takeUninterruptibly(BlockingQueue<E> blockingQueue) {
        E take;
        Object obj = null;
        while (true) {
            try {
                take = blockingQueue.take();
                break;
            } catch (InterruptedException e) {
                obj = 1;
            } catch (Throwable th) {
                if (obj != null) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (obj != null) {
            Thread.currentThread().interrupt();
        }
        return take;
    }

    public static <E> void putUninterruptibly(BlockingQueue<E> blockingQueue, E e) {
        Object obj = null;
        while (true) {
            try {
                blockingQueue.put(e);
                break;
            } catch (InterruptedException e2) {
                obj = 1;
            } catch (Throwable th) {
                if (obj != null) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (obj != null) {
            Thread.currentThread().interrupt();
        }
    }

    public static void sleepUninterruptibly(long j, TimeUnit timeUnit) {
        long nanoTime;
        Object obj = null;
        long toNanos;
        try {
            toNanos = timeUnit.toNanos(j);
            nanoTime = System.nanoTime() + toNanos;
            while (true) {
                TimeUnit.NANOSECONDS.sleep(toNanos);
                break;
            }
            if (obj != null) {
                Thread.currentThread().interrupt();
            }
        } catch (InterruptedException e) {
            obj = 1;
            toNanos = nanoTime - System.nanoTime();
        } catch (Throwable th) {
            if (obj != null) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static boolean tryAcquireUninterruptibly(Semaphore semaphore, long j, TimeUnit timeUnit) {
        return tryAcquireUninterruptibly(semaphore, 1, j, timeUnit);
    }

    public static boolean tryAcquireUninterruptibly(Semaphore semaphore, int i, long j, TimeUnit timeUnit) {
        long nanoTime;
        Object obj = null;
        long toNanos;
        try {
            boolean tryAcquire;
            toNanos = timeUnit.toNanos(j);
            nanoTime = System.nanoTime() + toNanos;
            while (true) {
                tryAcquire = semaphore.tryAcquire(i, toNanos, TimeUnit.NANOSECONDS);
                break;
            }
            if (obj != null) {
                Thread.currentThread().interrupt();
            }
            return tryAcquire;
        } catch (InterruptedException e) {
            obj = 1;
            toNanos = nanoTime - System.nanoTime();
        } catch (Throwable th) {
            if (obj != null) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private Uninterruptibles() {
    }
}
