package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;
import java.io.Serializable;
import java.util.Map;
import org.apache.http.protocol.HTTP;

@GwtCompatible
public final class Functions {

    private static class ConstantFunction<E> implements Function<Object, E>, Serializable {
        private static final long serialVersionUID = 0;
        private final E value;

        public ConstantFunction(E e) {
            this.value = e;
        }

        public E apply(Object obj) {
            return this.value;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ConstantFunction)) {
                return false;
            }
            return Objects.equal(this.value, ((ConstantFunction) obj).value);
        }

        public int hashCode() {
            return this.value == null ? 0 : this.value.hashCode();
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.value));
            return new StringBuilder(valueOf.length() + 10).append("constant(").append(valueOf).append(")").toString();
        }
    }

    private static class ForMapWithDefault<K, V> implements Function<K, V>, Serializable {
        private static final long serialVersionUID = 0;
        final V defaultValue;
        final Map<K, ? extends V> map;

        ForMapWithDefault(Map<K, ? extends V> map, V v) {
            this.map = (Map) Preconditions.checkNotNull(map);
            this.defaultValue = v;
        }

        public V apply(K k) {
            V v = this.map.get(k);
            return (v != null || this.map.containsKey(k)) ? v : this.defaultValue;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof ForMapWithDefault)) {
                return false;
            }
            ForMapWithDefault forMapWithDefault = (ForMapWithDefault) obj;
            if (this.map.equals(forMapWithDefault.map) && Objects.equal(this.defaultValue, forMapWithDefault.defaultValue)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return Objects.hashCode(new Object[]{this.map, this.defaultValue});
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.map));
            String valueOf2 = String.valueOf(String.valueOf(this.defaultValue));
            return new StringBuilder((valueOf.length() + 23) + valueOf2.length()).append("forMap(").append(valueOf).append(", defaultValue=").append(valueOf2).append(")").toString();
        }
    }

    private static class FunctionComposition<A, B, C> implements Function<A, C>, Serializable {
        private static final long serialVersionUID = 0;
        /* renamed from: f */
        private final Function<A, ? extends B> f14260f;
        /* renamed from: g */
        private final Function<B, C> f14261g;

        public FunctionComposition(Function<B, C> function, Function<A, ? extends B> function2) {
            this.f14261g = (Function) Preconditions.checkNotNull(function);
            this.f14260f = (Function) Preconditions.checkNotNull(function2);
        }

        public C apply(A a) {
            return this.f14261g.apply(this.f14260f.apply(a));
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof FunctionComposition)) {
                return false;
            }
            FunctionComposition functionComposition = (FunctionComposition) obj;
            if (this.f14260f.equals(functionComposition.f14260f) && this.f14261g.equals(functionComposition.f14261g)) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return this.f14260f.hashCode() ^ this.f14261g.hashCode();
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.f14261g));
            String valueOf2 = String.valueOf(String.valueOf(this.f14260f));
            return new StringBuilder((valueOf.length() + 2) + valueOf2.length()).append(valueOf).append("(").append(valueOf2).append(")").toString();
        }
    }

    private static class FunctionForMapNoDefault<K, V> implements Function<K, V>, Serializable {
        private static final long serialVersionUID = 0;
        final Map<K, V> map;

        FunctionForMapNoDefault(Map<K, V> map) {
            this.map = (Map) Preconditions.checkNotNull(map);
        }

        public V apply(K k) {
            boolean z;
            V v = this.map.get(k);
            if (v != null || this.map.containsKey(k)) {
                z = true;
            } else {
                z = false;
            }
            Preconditions.checkArgument(z, "Key '%s' not present in map", k);
            return v;
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof FunctionForMapNoDefault)) {
                return false;
            }
            return this.map.equals(((FunctionForMapNoDefault) obj).map);
        }

        public int hashCode() {
            return this.map.hashCode();
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.map));
            return new StringBuilder(valueOf.length() + 8).append("forMap(").append(valueOf).append(")").toString();
        }
    }

    private enum IdentityFunction implements Function<Object, Object> {
        INSTANCE;

        public Object apply(Object obj) {
            return obj;
        }

        public String toString() {
            return HTTP.IDENTITY_CODING;
        }
    }

    private static class PredicateFunction<T> implements Function<T, Boolean>, Serializable {
        private static final long serialVersionUID = 0;
        private final Predicate<T> predicate;

        private PredicateFunction(Predicate<T> predicate) {
            this.predicate = (Predicate) Preconditions.checkNotNull(predicate);
        }

        public Boolean apply(T t) {
            return Boolean.valueOf(this.predicate.apply(t));
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof PredicateFunction)) {
                return false;
            }
            return this.predicate.equals(((PredicateFunction) obj).predicate);
        }

        public int hashCode() {
            return this.predicate.hashCode();
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.predicate));
            return new StringBuilder(valueOf.length() + 14).append("forPredicate(").append(valueOf).append(")").toString();
        }
    }

    private static class SupplierFunction<T> implements Function<Object, T>, Serializable {
        private static final long serialVersionUID = 0;
        private final Supplier<T> supplier;

        private SupplierFunction(Supplier<T> supplier) {
            this.supplier = (Supplier) Preconditions.checkNotNull(supplier);
        }

        public T apply(Object obj) {
            return this.supplier.get();
        }

        public boolean equals(Object obj) {
            if (!(obj instanceof SupplierFunction)) {
                return false;
            }
            return this.supplier.equals(((SupplierFunction) obj).supplier);
        }

        public int hashCode() {
            return this.supplier.hashCode();
        }

        public String toString() {
            String valueOf = String.valueOf(String.valueOf(this.supplier));
            return new StringBuilder(valueOf.length() + 13).append("forSupplier(").append(valueOf).append(")").toString();
        }
    }

    private enum ToStringFunction implements Function<Object, String> {
        INSTANCE;

        public String apply(Object obj) {
            Preconditions.checkNotNull(obj);
            return obj.toString();
        }

        public String toString() {
            return "toString";
        }
    }

    private Functions() {
    }

    public static Function<Object, String> toStringFunction() {
        return ToStringFunction.INSTANCE;
    }

    public static <E> Function<E, E> identity() {
        return IdentityFunction.INSTANCE;
    }

    public static <K, V> Function<K, V> forMap(Map<K, V> map) {
        return new FunctionForMapNoDefault(map);
    }

    public static <K, V> Function<K, V> forMap(Map<K, ? extends V> map, V v) {
        return new ForMapWithDefault(map, v);
    }

    public static <A, B, C> Function<A, C> compose(Function<B, C> function, Function<A, ? extends B> function2) {
        return new FunctionComposition(function, function2);
    }

    public static <T> Function<T, Boolean> forPredicate(Predicate<T> predicate) {
        return new PredicateFunction(predicate);
    }

    public static <E> Function<Object, E> constant(E e) {
        return new ConstantFunction(e);
    }

    @Beta
    public static <T> Function<Object, T> forSupplier(Supplier<T> supplier) {
        return new SupplierFunction(supplier);
    }
}
