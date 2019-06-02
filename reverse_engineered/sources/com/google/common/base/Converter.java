package com.google.common.base;

import com.google.common.annotations.Beta;
import com.google.common.annotations.GwtCompatible;

@GwtCompatible
@Beta
public abstract class Converter<A, B> implements Function<A, B> {
    private final boolean handleNullAutomatically;
    private transient Converter<B, A> reverse;

    protected abstract A doBackward(B b);

    protected abstract B doForward(A a);

    protected Converter() {
        this(true);
    }

    Converter(boolean z) {
        this.handleNullAutomatically = z;
    }

    public final B convert(A a) {
        return correctedDoForward(a);
    }

    B correctedDoForward(A a) {
        if (this.handleNullAutomatically) {
            return a == null ? null : Preconditions.checkNotNull(doForward(a));
        } else {
            return doForward(a);
        }
    }

    A correctedDoBackward(B b) {
        if (this.handleNullAutomatically) {
            return b == null ? null : Preconditions.checkNotNull(doBackward(b));
        } else {
            return doBackward(b);
        }
    }

    public Iterable<B> convertAll(Iterable<? extends A> iterable) {
        Preconditions.checkNotNull(iterable, "fromIterable");
        return new Converter$1(this, iterable);
    }

    public Converter<B, A> reverse() {
        Converter<B, A> converter = this.reverse;
        if (converter != null) {
            return converter;
        }
        converter = new Converter$ReverseConverter(this);
        this.reverse = converter;
        return converter;
    }

    public final <C> Converter<A, C> andThen(Converter<B, C> converter) {
        return doAndThen(converter);
    }

    <C> Converter<A, C> doAndThen(Converter<B, C> converter) {
        return new Converter$ConverterComposition(this, (Converter) Preconditions.checkNotNull(converter));
    }

    @Deprecated
    public final B apply(A a) {
        return convert(a);
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public static <A, B> Converter<A, B> from(Function<? super A, ? extends B> function, Function<? super B, ? extends A> function2) {
        return new Converter$FunctionBasedConverter(function, function2, null);
    }

    public static <T> Converter<T, T> identity() {
        return Converter$IdentityConverter.INSTANCE;
    }
}
