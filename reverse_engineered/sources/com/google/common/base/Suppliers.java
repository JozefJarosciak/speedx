package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import com.google.common.annotations.VisibleForTesting;
import java.io.Serializable;
import java.util.concurrent.TimeUnit;

@GwtCompatible
public final class Suppliers {

    @VisibleForTesting
    static class ExpiringMemoizingSupplier<T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;
        final Supplier<T> delegate;
        final long durationNanos;
        volatile transient long expirationNanos;
        volatile transient T value;

        ExpiringMemoizingSupplier(Supplier<T> supplier, long j, TimeUnit timeUnit) {
            this.delegate = (Supplier) Preconditions.checkNotNull(supplier);
            this.durationNanos = timeUnit.toNanos(j);
            Preconditions.checkArgument(j > 0);
        }

        public T get() {
            long j = this.expirationNanos;
            long systemNanoTime = Platform.systemNanoTime();
            if (j == 0 || systemNanoTime - j >= 0) {
                synchronized (this) {
                    if (j == this.expirationNanos) {
                        T t = this.delegate.get();
                        this.value = t;
                        systemNanoTime += this.durationNanos;
                        if (systemNanoTime == 0) {
                            systemNanoTime = 1;
                        }
                        this.expirationNanos = systemNanoTime;
                        return t;
                    }
                }
            }
            return this.value;
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.delegate));
            return new StringBuilder(valueOf.length() + 62).append("Suppliers.memoizeWithExpiration(").append(valueOf).append(", ").append(this.durationNanos).append(", NANOS)").toString();
        }
    }

    @VisibleForTesting
    static class MemoizingSupplier<T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;
        final Supplier<T> delegate;
        volatile transient boolean initialized;
        transient T value;

        MemoizingSupplier(Supplier<T> supplier) {
            this.delegate = supplier;
        }

        public T get() {
            if (!this.initialized) {
                synchronized (this) {
                    if (!this.initialized) {
                        T t = this.delegate.get();
                        this.value = t;
                        this.initialized = true;
                        return t;
                    }
                }
            }
            return this.value;
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.delegate));
            return new StringBuilder(valueOf.length() + 19).append("Suppliers.memoize(").append(valueOf).append(")").toString();
        }
    }

    private static class SupplierComposition<F, T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;
        final Function<? super F, T> function;
        final Supplier<F> supplier;

        SupplierComposition(Function<? super F, T> function, Supplier<F> supplier) {
            this.function = function;
            this.supplier = supplier;
        }

        public T get() {
            return this.function.apply(this.supplier.get());
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof SupplierComposition)) {
                return false;
            }
            SupplierComposition supplierComposition = (SupplierComposition) obj;
            if (this.function.equals(supplierComposition.function) && this.supplier.equals(supplierComposition.supplier)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(new Object[]{this.function, this.supplier});
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.function));
            String valueOf2 = String.valueOf(String.valueOf(this.supplier));
            return new StringBuilder((valueOf.length() + 21) + valueOf2.length()).append("Suppliers.compose(").append(valueOf).append(", ").append(valueOf2).append(")").toString();
        }
    }

    private interface SupplierFunction<T> extends Function<Supplier<T>, T> {
    }

    private enum SupplierFunctionImpl implements SupplierFunction<Object> {
        INSTANCE;

        public Object apply(Supplier<Object> supplier) {
            return supplier.get();
        }

        public String toString() {
            return "Suppliers.supplierFunction()";
        }
    }

    private static class SupplierOfInstance<T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;
        final T instance;

        SupplierOfInstance(T t) {
            this.instance = t;
        }

        public T get() {
            return this.instance;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof SupplierOfInstance)) {
                return false;
            }
            return Objects.equal(this.instance, ((SupplierOfInstance) obj).instance);
        }

        public int hashCode() {
            return Objects.hashCode(new Object[]{this.instance});
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.instance));
            return new StringBuilder(valueOf.length() + 22).append("Suppliers.ofInstance(").append(valueOf).append(")").toString();
        }
    }

    private static class ThreadSafeSupplier<T> implements Supplier<T>, Serializable {
        private static final long serialVersionUID = 0;
        final Supplier<T> delegate;

        ThreadSafeSupplier(Supplier<T> supplier) {
            this.delegate = supplier;
        }

        public T get() {
            T t;
            synchronized (this.delegate) {
                t = this.delegate.get();
            }
            return t;
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.delegate));
            return new StringBuilder(valueOf.length() + 32).append("Suppliers.synchronizedSupplier(").append(valueOf).append(")").toString();
        }
    }

    private Suppliers() {
    }

    public static <F, T> Supplier<T> compose(Function<? super F, T> function, Supplier<F> supplier) {
        Preconditions.checkNotNull(function);
        Preconditions.checkNotNull(supplier);
        return new SupplierComposition(function, supplier);
    }

    public static <T> Supplier<T> memoize(Supplier<T> supplier) {
        return supplier instanceof MemoizingSupplier ? supplier : new MemoizingSupplier((Supplier) Preconditions.checkNotNull(supplier));
    }

    public static <T> Supplier<T> memoizeWithExpiration(Supplier<T> supplier, long j, TimeUnit timeUnit) {
        return new ExpiringMemoizingSupplier(supplier, j, timeUnit);
    }

    public static <T> Supplier<T> ofInstance(T t) {
        return new SupplierOfInstance(t);
    }

    public static <T> Supplier<T> synchronizedSupplier(Supplier<T> supplier) {
        return new ThreadSafeSupplier((Supplier) Preconditions.checkNotNull(supplier));
    }

    @Beta
    public static <T> Function<Supplier<T>, T> supplierFunction() {
        return SupplierFunctionImpl.INSTANCE;
    }
}
