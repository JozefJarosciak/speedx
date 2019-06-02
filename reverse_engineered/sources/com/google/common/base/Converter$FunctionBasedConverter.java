package com.google.common.base;

import java.io.Serializable;

final class Converter$FunctionBasedConverter<A, B> extends Converter<A, B> implements Serializable {
    private final Function<? super B, ? extends A> backwardFunction;
    private final Function<? super A, ? extends B> forwardFunction;

    private Converter$FunctionBasedConverter(Function<? super A, ? extends B> function, Function<? super B, ? extends A> function2) {
        this.forwardFunction = (Function) Preconditions.checkNotNull(function);
        this.backwardFunction = (Function) Preconditions.checkNotNull(function2);
    }

    protected B doForward(A a) {
        return this.forwardFunction.apply(a);
    }

    protected A doBackward(B b) {
        return this.backwardFunction.apply(b);
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Converter$FunctionBasedConverter)) {
            return false;
        }
        Converter$FunctionBasedConverter converter$FunctionBasedConverter = (Converter$FunctionBasedConverter) obj;
        if (this.forwardFunction.equals(converter$FunctionBasedConverter.forwardFunction) && this.backwardFunction.equals(converter$FunctionBasedConverter.backwardFunction)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.forwardFunction.hashCode() * 31) + this.backwardFunction.hashCode();
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(this.forwardFunction));
        String valueOf2 = String.valueOf(String.valueOf(this.backwardFunction));
        return new StringBuilder((valueOf.length() + 18) + valueOf2.length()).append("Converter.from(").append(valueOf).append(", ").append(valueOf2).append(")").toString();
    }
}
