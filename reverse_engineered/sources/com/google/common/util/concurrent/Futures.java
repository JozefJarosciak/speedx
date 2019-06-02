package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList$Builder;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.logging.Level;
import java.util.logging.Logger;

@Beta
public final class Futures {
    private static final AsyncFunction<ListenableFuture<Object>, Object> DEREFERENCER = new C38774();
    private static final Ordering<Constructor<?>> WITH_STRING_PARAM_FIRST = Ordering.natural().onResultOf(new C38807()).reverse();

    /* renamed from: com.google.common.util.concurrent.Futures$4 */
    static class C38774 implements AsyncFunction<ListenableFuture<Object>, Object> {
        C38774() {
        }

        public ListenableFuture<Object> apply(ListenableFuture<Object> listenableFuture) {
            return listenableFuture;
        }
    }

    /* renamed from: com.google.common.util.concurrent.Futures$7 */
    static class C38807 implements Function<Constructor<?>, Boolean> {
        C38807() {
        }

        public Boolean apply(Constructor<?> constructor) {
            return Boolean.valueOf(Arrays.asList(constructor.getParameterTypes()).contains(String.class));
        }
    }

    private interface FutureCombiner<V, C> {
        C combine(List<Optional<V>> list);
    }

    /* renamed from: com.google.common.util.concurrent.Futures$8 */
    static class C38818 implements FutureCombiner<V, List<V>> {
        C38818() {
        }

        public List<V> combine(List<Optional<V>> list) {
            List newArrayList = Lists.newArrayList();
            for (Optional optional : list) {
                newArrayList.add(optional != null ? optional.orNull() : null);
            }
            return Collections.unmodifiableList(newArrayList);
        }
    }

    private static class ChainingListenableFuture<I, O> extends AbstractFuture<O> implements Runnable {
        private AsyncFunction<? super I, ? extends O> function;
        private ListenableFuture<? extends I> inputFuture;
        private volatile ListenableFuture<? extends O> outputFuture;

        private ChainingListenableFuture(AsyncFunction<? super I, ? extends O> asyncFunction, ListenableFuture<? extends I> listenableFuture) {
            this.function = (AsyncFunction) Preconditions.checkNotNull(asyncFunction);
            this.inputFuture = (ListenableFuture) Preconditions.checkNotNull(listenableFuture);
        }

        public boolean cancel(boolean z) {
            if (!super.cancel(z)) {
                return false;
            }
            cancel(this.inputFuture, z);
            cancel(this.outputFuture, z);
            return true;
        }

        private void cancel(Future<?> future, boolean z) {
            if (future != null) {
                future.cancel(z);
            }
        }

        public void run() {
            try {
                try {
                    final ListenableFuture listenableFuture = (ListenableFuture) Preconditions.checkNotNull(this.function.apply(Uninterruptibles.getUninterruptibly(this.inputFuture)), "AsyncFunction may not return null.");
                    this.outputFuture = listenableFuture;
                    if (isCancelled()) {
                        listenableFuture.cancel(wasInterrupted());
                        this.outputFuture = null;
                        return;
                    }
                    listenableFuture.addListener(new Runnable() {
                        public void run() {
                            try {
                                ChainingListenableFuture.this.set(Uninterruptibles.getUninterruptibly(listenableFuture));
                            } catch (CancellationException e) {
                                ChainingListenableFuture.this.cancel(false);
                            } catch (ExecutionException e2) {
                                ChainingListenableFuture.this.setException(e2.getCause());
                            } finally {
                                ChainingListenableFuture.this.outputFuture = null;
                            }
                        }
                    }, MoreExecutors.directExecutor());
                    this.function = null;
                    this.inputFuture = null;
                } catch (UndeclaredThrowableException e) {
                    setException(e.getCause());
                } catch (Throwable th) {
                    setException(th);
                } finally {
                    this.function = null;
                    this.inputFuture = null;
                }
            } catch (CancellationException e2) {
                cancel(false);
                this.function = null;
                this.inputFuture = null;
            } catch (ExecutionException e3) {
                setException(e3.getCause());
                this.function = null;
                this.inputFuture = null;
            }
        }
    }

    private static class CombinedFuture<V, C> extends AbstractFuture<C> {
        private static final Logger logger = Logger.getLogger(CombinedFuture.class.getName());
        final boolean allMustSucceed;
        FutureCombiner<V, C> combiner;
        ImmutableCollection<? extends ListenableFuture<? extends V>> futures;
        final AtomicInteger remaining;
        Set<Throwable> seenExceptions;
        final Object seenExceptionsLock = new Object();
        List<Optional<V>> values;

        /* renamed from: com.google.common.util.concurrent.Futures$CombinedFuture$1 */
        class C38831 implements Runnable {
            C38831() {
            }

            public void run() {
                if (CombinedFuture.this.isCancelled()) {
                    Iterator it = CombinedFuture.this.futures.iterator();
                    while (it.hasNext()) {
                        ((ListenableFuture) it.next()).cancel(CombinedFuture.this.wasInterrupted());
                    }
                }
                CombinedFuture.this.futures = null;
                CombinedFuture.this.values = null;
                CombinedFuture.this.combiner = null;
            }
        }

        CombinedFuture(ImmutableCollection<? extends ListenableFuture<? extends V>> immutableCollection, boolean z, Executor executor, FutureCombiner<V, C> futureCombiner) {
            this.futures = immutableCollection;
            this.allMustSucceed = z;
            this.remaining = new AtomicInteger(immutableCollection.size());
            this.combiner = futureCombiner;
            this.values = Lists.newArrayListWithCapacity(immutableCollection.size());
            init(executor);
        }

        protected void init(Executor executor) {
            int i = 0;
            addListener(new C38831(), MoreExecutors.directExecutor());
            if (this.futures.isEmpty()) {
                set(this.combiner.combine(ImmutableList.of()));
                return;
            }
            for (int i2 = 0; i2 < this.futures.size(); i2++) {
                this.values.add(null);
            }
            Iterator it = this.futures.iterator();
            while (it.hasNext()) {
                final ListenableFuture listenableFuture = (ListenableFuture) it.next();
                int i3 = i + 1;
                listenableFuture.addListener(new Runnable() {
                    public void run() {
                        CombinedFuture.this.setOneValue(i, listenableFuture);
                    }
                }, executor);
                i = i3;
            }
        }

        private void setExceptionAndMaybeLog(Throwable th) {
            boolean z = false;
            boolean z2 = true;
            if (this.allMustSucceed) {
                z = super.setException(th);
                synchronized (this.seenExceptionsLock) {
                    if (this.seenExceptions == null) {
                        this.seenExceptions = Sets.newHashSet();
                    }
                    z2 = this.seenExceptions.add(th);
                }
            }
            if ((th instanceof Error) || (this.allMustSucceed && !r1 && r0)) {
                logger.log(Level.SEVERE, "input future failed.", th);
            }
        }

        private void setOneValue(int i, Future<? extends V> future) {
            int decrementAndGet;
            FutureCombiner futureCombiner;
            boolean z = true;
            List list = this.values;
            if (isDone() || list == null) {
                boolean z2 = this.allMustSucceed || isCancelled();
                Preconditions.checkState(z2, "Future was done before all dependencies completed");
            }
            try {
                Preconditions.checkState(future.isDone(), "Tried to set value from future which is not done");
                Object uninterruptibly = Uninterruptibles.getUninterruptibly(future);
                if (list != null) {
                    list.set(i, Optional.fromNullable(uninterruptibly));
                }
                decrementAndGet = this.remaining.decrementAndGet();
                if (decrementAndGet < 0) {
                    z = false;
                }
                Preconditions.checkState(z, "Less than 0 remaining futures");
                if (decrementAndGet == 0) {
                    futureCombiner = this.combiner;
                    if (futureCombiner == null || list == null) {
                        Preconditions.checkState(isDone());
                    } else {
                        set(futureCombiner.combine(list));
                    }
                }
            } catch (CancellationException e) {
                if (this.allMustSucceed) {
                    cancel(false);
                }
                decrementAndGet = this.remaining.decrementAndGet();
                if (decrementAndGet < 0) {
                    z = false;
                }
                Preconditions.checkState(z, "Less than 0 remaining futures");
                if (decrementAndGet == 0) {
                    futureCombiner = this.combiner;
                    if (futureCombiner == null || list == null) {
                        Preconditions.checkState(isDone());
                    } else {
                        set(futureCombiner.combine(list));
                    }
                }
            } catch (ExecutionException e2) {
                setExceptionAndMaybeLog(e2.getCause());
                decrementAndGet = this.remaining.decrementAndGet();
                if (decrementAndGet < 0) {
                    z = false;
                }
                Preconditions.checkState(z, "Less than 0 remaining futures");
                if (decrementAndGet == 0) {
                    futureCombiner = this.combiner;
                    if (futureCombiner == null || list == null) {
                        Preconditions.checkState(isDone());
                    } else {
                        set(futureCombiner.combine(list));
                    }
                }
            } catch (Throwable th) {
                int decrementAndGet2 = this.remaining.decrementAndGet();
                if (decrementAndGet2 < 0) {
                    z = false;
                }
                Preconditions.checkState(z, "Less than 0 remaining futures");
                if (decrementAndGet2 == 0) {
                    FutureCombiner futureCombiner2 = this.combiner;
                    if (futureCombiner2 == null || list == null) {
                        Preconditions.checkState(isDone());
                    } else {
                        set(futureCombiner2.combine(list));
                    }
                }
            }
        }
    }

    private static final class CombinerFuture<V> extends ListenableFutureTask<V> {
        ImmutableList<ListenableFuture<?>> futures;

        CombinerFuture(Callable<V> callable, ImmutableList<ListenableFuture<?>> immutableList) {
            super(callable);
            this.futures = immutableList;
        }

        public boolean cancel(boolean z) {
            ImmutableList immutableList = this.futures;
            if (!super.cancel(z)) {
                return false;
            }
            Iterator it = immutableList.iterator();
            while (it.hasNext()) {
                ((ListenableFuture) it.next()).cancel(z);
            }
            return true;
        }

        protected void done() {
            super.done();
            this.futures = null;
        }

        protected void setException(Throwable th) {
            super.setException(th);
        }
    }

    private static class FallbackFuture<V> extends AbstractFuture<V> {
        private volatile ListenableFuture<? extends V> running;

        FallbackFuture(ListenableFuture<? extends V> listenableFuture, final FutureFallback<? extends V> futureFallback, Executor executor) {
            this.running = listenableFuture;
            Futures.addCallback(this.running, new FutureCallback<V>() {

                /* renamed from: com.google.common.util.concurrent.Futures$FallbackFuture$1$1 */
                class C38851 implements FutureCallback<V> {
                    C38851() {
                    }

                    public void onSuccess(V v) {
                        FallbackFuture.this.set(v);
                    }

                    public void onFailure(Throwable th) {
                        if (FallbackFuture.this.running.isCancelled()) {
                            FallbackFuture.this.cancel(false);
                        } else {
                            FallbackFuture.this.setException(th);
                        }
                    }
                }

                public void onSuccess(V v) {
                    FallbackFuture.this.set(v);
                }

                public void onFailure(Throwable th) {
                    if (!FallbackFuture.this.isCancelled()) {
                        try {
                            FallbackFuture.this.running = futureFallback.create(th);
                            if (FallbackFuture.this.isCancelled()) {
                                FallbackFuture.this.running.cancel(FallbackFuture.this.wasInterrupted());
                            } else {
                                Futures.addCallback(FallbackFuture.this.running, new C38851(), MoreExecutors.directExecutor());
                            }
                        } catch (Throwable th2) {
                            FallbackFuture.this.setException(th2);
                        }
                    }
                }
            }, executor);
        }

        public boolean cancel(boolean z) {
            if (!super.cancel(z)) {
                return false;
            }
            this.running.cancel(z);
            return true;
        }
    }

    private static abstract class ImmediateFuture<V> implements ListenableFuture<V> {
        private static final Logger log = Logger.getLogger(ImmediateFuture.class.getName());

        public abstract V get() throws ExecutionException;

        private ImmediateFuture() {
        }

        public void addListener(Runnable runnable, Executor executor) {
            Preconditions.checkNotNull(runnable, "Runnable was null.");
            Preconditions.checkNotNull(executor, "Executor was null.");
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

        public boolean cancel(boolean z) {
            return false;
        }

        public V get(long j, TimeUnit timeUnit) throws ExecutionException {
            Preconditions.checkNotNull(timeUnit);
            return get();
        }

        public boolean isCancelled() {
            return false;
        }

        public boolean isDone() {
            return true;
        }
    }

    private static class ImmediateCancelledFuture<V> extends ImmediateFuture<V> {
        private final CancellationException thrown = new CancellationException("Immediate cancelled future.");

        ImmediateCancelledFuture() {
            super();
        }

        public boolean isCancelled() {
            return true;
        }

        public V get() {
            throw AbstractFuture.cancellationExceptionWithCause("Task was cancelled.", this.thrown);
        }
    }

    private static class ImmediateFailedCheckedFuture<V, X extends Exception> extends ImmediateFuture<V> implements CheckedFuture<V, X> {
        private final X thrown;

        ImmediateFailedCheckedFuture(X x) {
            super();
            this.thrown = x;
        }

        public V get() throws ExecutionException {
            throw new ExecutionException(this.thrown);
        }

        public V checkedGet() throws Exception {
            throw this.thrown;
        }

        public V checkedGet(long j, TimeUnit timeUnit) throws Exception {
            Preconditions.checkNotNull(timeUnit);
            throw this.thrown;
        }
    }

    private static class ImmediateFailedFuture<V> extends ImmediateFuture<V> {
        private final Throwable thrown;

        ImmediateFailedFuture(Throwable th) {
            super();
            this.thrown = th;
        }

        public V get() throws ExecutionException {
            throw new ExecutionException(this.thrown);
        }
    }

    private static class ImmediateSuccessfulCheckedFuture<V, X extends Exception> extends ImmediateFuture<V> implements CheckedFuture<V, X> {
        private final V value;

        ImmediateSuccessfulCheckedFuture(V v) {
            super();
            this.value = v;
        }

        public V get() {
            return this.value;
        }

        public V checkedGet() {
            return this.value;
        }

        public V checkedGet(long j, TimeUnit timeUnit) {
            Preconditions.checkNotNull(timeUnit);
            return this.value;
        }
    }

    private static class ImmediateSuccessfulFuture<V> extends ImmediateFuture<V> {
        private final V value;

        ImmediateSuccessfulFuture(V v) {
            super();
            this.value = v;
        }

        public V get() {
            return this.value;
        }
    }

    private static class MappingCheckedFuture<V, X extends Exception> extends AbstractCheckedFuture<V, X> {
        final Function<? super Exception, X> mapper;

        MappingCheckedFuture(ListenableFuture<V> listenableFuture, Function<? super Exception, X> function) {
            super(listenableFuture);
            this.mapper = (Function) Preconditions.checkNotNull(function);
        }

        protected X mapException(Exception exception) {
            return (Exception) this.mapper.apply(exception);
        }
    }

    private static class NonCancellationPropagatingFuture<V> extends AbstractFuture<V> {
        NonCancellationPropagatingFuture(final ListenableFuture<V> listenableFuture) {
            Preconditions.checkNotNull(listenableFuture);
            Futures.addCallback(listenableFuture, new FutureCallback<V>() {
                public void onSuccess(V v) {
                    NonCancellationPropagatingFuture.this.set(v);
                }

                public void onFailure(Throwable th) {
                    if (listenableFuture.isCancelled()) {
                        NonCancellationPropagatingFuture.this.cancel(false);
                    } else {
                        NonCancellationPropagatingFuture.this.setException(th);
                    }
                }
            }, MoreExecutors.directExecutor());
        }
    }

    private static final class WrappedCombiner<T> implements Callable<T> {
        final Callable<T> delegate;
        CombinerFuture<T> outputFuture;

        WrappedCombiner(Callable<T> callable) {
            this.delegate = (Callable) Preconditions.checkNotNull(callable);
        }

        public T call() throws Exception {
            try {
                return this.delegate.call();
            } catch (ExecutionException e) {
                this.outputFuture.setException(e.getCause());
                return null;
            } catch (CancellationException e2) {
                this.outputFuture.cancel(false);
                return null;
            }
        }
    }

    private Futures() {
    }

    public static <V, X extends Exception> CheckedFuture<V, X> makeChecked(ListenableFuture<V> listenableFuture, Function<? super Exception, X> function) {
        return new MappingCheckedFuture((ListenableFuture) Preconditions.checkNotNull(listenableFuture), function);
    }

    public static <V> ListenableFuture<V> immediateFuture(V v) {
        return new ImmediateSuccessfulFuture(v);
    }

    public static <V, X extends Exception> CheckedFuture<V, X> immediateCheckedFuture(V v) {
        return new ImmediateSuccessfulCheckedFuture(v);
    }

    public static <V> ListenableFuture<V> immediateFailedFuture(Throwable th) {
        Preconditions.checkNotNull(th);
        return new ImmediateFailedFuture(th);
    }

    public static <V> ListenableFuture<V> immediateCancelledFuture() {
        return new ImmediateCancelledFuture();
    }

    public static <V, X extends Exception> CheckedFuture<V, X> immediateFailedCheckedFuture(X x) {
        Preconditions.checkNotNull(x);
        return new ImmediateFailedCheckedFuture(x);
    }

    public static <V> ListenableFuture<V> withFallback(ListenableFuture<? extends V> listenableFuture, FutureFallback<? extends V> futureFallback) {
        return withFallback(listenableFuture, futureFallback, MoreExecutors.directExecutor());
    }

    public static <V> ListenableFuture<V> withFallback(ListenableFuture<? extends V> listenableFuture, FutureFallback<? extends V> futureFallback, Executor executor) {
        Preconditions.checkNotNull(futureFallback);
        return new FallbackFuture(listenableFuture, futureFallback, executor);
    }

    public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> listenableFuture, AsyncFunction<? super I, ? extends O> asyncFunction) {
        Object chainingListenableFuture = new ChainingListenableFuture(asyncFunction, listenableFuture);
        listenableFuture.addListener(chainingListenableFuture, MoreExecutors.directExecutor());
        return chainingListenableFuture;
    }

    public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> listenableFuture, AsyncFunction<? super I, ? extends O> asyncFunction, Executor executor) {
        Preconditions.checkNotNull(executor);
        ListenableFuture<O> chainingListenableFuture = new ChainingListenableFuture(asyncFunction, listenableFuture);
        listenableFuture.addListener(rejectionPropagatingRunnable(chainingListenableFuture, chainingListenableFuture, executor), MoreExecutors.directExecutor());
        return chainingListenableFuture;
    }

    private static Runnable rejectionPropagatingRunnable(final AbstractFuture<?> abstractFuture, final Runnable runnable, final Executor executor) {
        return new Runnable() {
            public void run() {
                final AtomicBoolean atomicBoolean = new AtomicBoolean(true);
                try {
                    executor.execute(new Runnable() {
                        public void run() {
                            atomicBoolean.set(false);
                            runnable.run();
                        }
                    });
                } catch (Throwable e) {
                    if (atomicBoolean.get()) {
                        abstractFuture.setException(e);
                    }
                }
            }
        };
    }

    public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> listenableFuture, Function<? super I, ? extends O> function) {
        Preconditions.checkNotNull(function);
        Object chainingListenableFuture = new ChainingListenableFuture(asAsyncFunction(function), listenableFuture);
        listenableFuture.addListener(chainingListenableFuture, MoreExecutors.directExecutor());
        return chainingListenableFuture;
    }

    public static <I, O> ListenableFuture<O> transform(ListenableFuture<I> listenableFuture, Function<? super I, ? extends O> function, Executor executor) {
        Preconditions.checkNotNull(function);
        return transform((ListenableFuture) listenableFuture, asAsyncFunction(function), executor);
    }

    private static <I, O> AsyncFunction<I, O> asAsyncFunction(final Function<? super I, ? extends O> function) {
        return new AsyncFunction<I, O>() {
            public ListenableFuture<O> apply(I i) {
                return Futures.immediateFuture(function.apply(i));
            }
        };
    }

    public static <I, O> Future<O> lazyTransform(final Future<I> future, final Function<? super I, ? extends O> function) {
        Preconditions.checkNotNull(future);
        Preconditions.checkNotNull(function);
        return new Future<O>() {
            public boolean cancel(boolean z) {
                return future.cancel(z);
            }

            public boolean isCancelled() {
                return future.isCancelled();
            }

            public boolean isDone() {
                return future.isDone();
            }

            public O get() throws InterruptedException, ExecutionException {
                return applyTransformation(future.get());
            }

            public O get(long j, TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
                return applyTransformation(future.get(j, timeUnit));
            }

            private O applyTransformation(I i) throws ExecutionException {
                try {
                    return function.apply(i);
                } catch (Throwable th) {
                    ExecutionException executionException = new ExecutionException(th);
                }
            }
        };
    }

    public static <V> ListenableFuture<V> dereference(ListenableFuture<? extends ListenableFuture<? extends V>> listenableFuture) {
        return transform((ListenableFuture) listenableFuture, DEREFERENCER);
    }

    @Beta
    public static <V> ListenableFuture<List<V>> allAsList(ListenableFuture<? extends V>... listenableFutureArr) {
        return listFuture(ImmutableList.copyOf(listenableFutureArr), true, MoreExecutors.directExecutor());
    }

    @Beta
    public static <V> ListenableFuture<List<V>> allAsList(Iterable<? extends ListenableFuture<? extends V>> iterable) {
        return listFuture(ImmutableList.copyOf(iterable), true, MoreExecutors.directExecutor());
    }

    public static <V> ListenableFuture<V> nonCancellationPropagating(ListenableFuture<V> listenableFuture) {
        return new NonCancellationPropagatingFuture(listenableFuture);
    }

    @Beta
    public static <V> ListenableFuture<List<V>> successfulAsList(ListenableFuture<? extends V>... listenableFutureArr) {
        return listFuture(ImmutableList.copyOf(listenableFutureArr), false, MoreExecutors.directExecutor());
    }

    @Beta
    public static <V> ListenableFuture<List<V>> successfulAsList(Iterable<? extends ListenableFuture<? extends V>> iterable) {
        return listFuture(ImmutableList.copyOf(iterable), false, MoreExecutors.directExecutor());
    }

    @Beta
    public static <T> ImmutableList<ListenableFuture<T>> inCompletionOrder(Iterable<? extends ListenableFuture<? extends T>> iterable) {
        final ConcurrentLinkedQueue newConcurrentLinkedQueue = Queues.newConcurrentLinkedQueue();
        ImmutableList$Builder builder = ImmutableList.builder();
        Executor serializingExecutor = new SerializingExecutor(MoreExecutors.directExecutor());
        for (final ListenableFuture listenableFuture : iterable) {
            Object create = AsyncSettableFuture.create();
            newConcurrentLinkedQueue.add(create);
            listenableFuture.addListener(new Runnable() {
                public void run() {
                    ((AsyncSettableFuture) newConcurrentLinkedQueue.remove()).setFuture(listenableFuture);
                }
            }, serializingExecutor);
            builder.add(create);
        }
        return builder.build();
    }

    public static <V> void addCallback(ListenableFuture<V> listenableFuture, FutureCallback<? super V> futureCallback) {
        addCallback(listenableFuture, futureCallback, MoreExecutors.directExecutor());
    }

    public static <V> void addCallback(final ListenableFuture<V> listenableFuture, final FutureCallback<? super V> futureCallback, Executor executor) {
        Preconditions.checkNotNull(futureCallback);
        listenableFuture.addListener(new Runnable() {
            public void run() {
                try {
                    futureCallback.onSuccess(Uninterruptibles.getUninterruptibly(listenableFuture));
                } catch (ExecutionException e) {
                    futureCallback.onFailure(e.getCause());
                } catch (Throwable e2) {
                    futureCallback.onFailure(e2);
                } catch (Throwable e22) {
                    futureCallback.onFailure(e22);
                }
            }
        }, executor);
    }

    public static <V, X extends Exception> V get(Future<V> future, Class<X> cls) throws Exception {
        boolean z;
        Preconditions.checkNotNull(future);
        if (RuntimeException.class.isAssignableFrom(cls)) {
            z = false;
        } else {
            z = true;
        }
        Preconditions.checkArgument(z, "Futures.get exception type (%s) must not be a RuntimeException", cls);
        try {
            return future.get();
        } catch (Throwable e) {
            Thread.currentThread().interrupt();
            throw newWithCause(cls, e);
        } catch (ExecutionException e2) {
            wrapAndThrowExceptionOrError(e2.getCause(), cls);
            throw new AssertionError();
        }
    }

    public static <V, X extends Exception> V get(Future<V> future, long j, TimeUnit timeUnit, Class<X> cls) throws Exception {
        boolean z;
        Preconditions.checkNotNull(future);
        Preconditions.checkNotNull(timeUnit);
        if (RuntimeException.class.isAssignableFrom(cls)) {
            z = false;
        } else {
            z = true;
        }
        Preconditions.checkArgument(z, "Futures.get exception type (%s) must not be a RuntimeException", cls);
        try {
            return future.get(j, timeUnit);
        } catch (Throwable e) {
            Thread.currentThread().interrupt();
            throw newWithCause(cls, e);
        } catch (Throwable e2) {
            throw newWithCause(cls, e2);
        } catch (ExecutionException e3) {
            wrapAndThrowExceptionOrError(e3.getCause(), cls);
            throw new AssertionError();
        }
    }

    private static <X extends Exception> void wrapAndThrowExceptionOrError(Throwable th, Class<X> cls) throws Exception {
        if (th instanceof Error) {
            throw new ExecutionError((Error) th);
        } else if (th instanceof RuntimeException) {
            throw new UncheckedExecutionException(th);
        } else {
            throw newWithCause(cls, th);
        }
    }

    public static <V> V getUnchecked(Future<V> future) {
        Preconditions.checkNotNull(future);
        try {
            return Uninterruptibles.getUninterruptibly(future);
        } catch (ExecutionException e) {
            wrapAndThrowUnchecked(e.getCause());
            throw new AssertionError();
        }
    }

    private static void wrapAndThrowUnchecked(Throwable th) {
        if (th instanceof Error) {
            throw new ExecutionError((Error) th);
        }
        throw new UncheckedExecutionException(th);
    }

    private static <X extends Exception> X newWithCause(Class<X> cls, Throwable th) {
        for (Constructor newFromConstructor : preferringStrings(Arrays.asList(cls.getConstructors()))) {
            Exception exception = (Exception) newFromConstructor(newFromConstructor, th);
            if (exception != null) {
                if (exception.getCause() == null) {
                    exception.initCause(th);
                }
                return exception;
            }
        }
        String valueOf = String.valueOf(String.valueOf(cls));
        throw new IllegalArgumentException(new StringBuilder(valueOf.length() + 82).append("No appropriate constructor for exception of type ").append(valueOf).append(" in response to chained exception").toString(), th);
    }

    private static <X extends Exception> List<Constructor<X>> preferringStrings(List<Constructor<X>> list) {
        return WITH_STRING_PARAM_FIRST.sortedCopy(list);
    }

    private static <X> X newFromConstructor(Constructor<X> constructor, Throwable th) {
        Class[] parameterTypes = constructor.getParameterTypes();
        Object[] objArr = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            Object obj = parameterTypes[i];
            if (obj.equals(String.class)) {
                objArr[i] = th.toString();
            } else if (!obj.equals(Throwable.class)) {
                return null;
            } else {
                objArr[i] = th;
            }
        }
        try {
            return constructor.newInstance(objArr);
        } catch (IllegalArgumentException e) {
            return null;
        } catch (InstantiationException e2) {
            return null;
        } catch (IllegalAccessException e3) {
            return null;
        } catch (InvocationTargetException e4) {
            return null;
        }
    }

    private static <V> ListenableFuture<List<V>> listFuture(ImmutableList<ListenableFuture<? extends V>> immutableList, boolean z, Executor executor) {
        return new CombinedFuture(immutableList, z, executor, new C38818());
    }
}
