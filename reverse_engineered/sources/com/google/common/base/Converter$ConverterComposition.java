package com.google.common.base;

import java.io.Serializable;

final class Converter$ConverterComposition<A, B, C> extends Converter<A, C> implements Serializable {
    private static final long serialVersionUID = 0;
    final Converter<A, B> first;
    final Converter<B, C> second;

    Converter$ConverterComposition(Converter<A, B> converter, Converter<B, C> converter2) {
        this.first = converter;
        this.second = converter2;
    }

    protected C doForward(A a) {
        throw new AssertionError();
    }

    protected A doBackward(C c) {
        throw new AssertionError();
    }

    C correctedDoForward(A a) {
        return this.second.correctedDoForward(this.first.correctedDoForward(a));
    }

    A correctedDoBackward(C c) {
        return this.first.correctedDoBackward(this.second.correctedDoBackward(c));
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Converter$ConverterComposition)) {
            return false;
        }
        Converter$ConverterComposition converter$ConverterComposition = (Converter$ConverterComposition) obj;
        if (this.first.equals(converter$ConverterComposition.first) && this.second.equals(converter$ConverterComposition.second)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.first.hashCode() * 31) + this.second.hashCode();
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(this.first));
        String valueOf2 = String.valueOf(String.valueOf(this.second));
        return new StringBuilder((valueOf.length() + 10) + valueOf2.length()).append(valueOf).append(".andThen(").append(valueOf2).append(")").toString();
    }
}
