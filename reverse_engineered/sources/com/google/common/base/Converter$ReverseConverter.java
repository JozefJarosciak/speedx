package com.google.common.base;

import java.io.Serializable;

final class Converter$ReverseConverter<A, B> extends Converter<B, A> implements Serializable {
    private static final long serialVersionUID = 0;
    final Converter<A, B> original;

    Converter$ReverseConverter(Converter<A, B> converter) {
        this.original = converter;
    }

    protected A doForward(B b) {
        throw new AssertionError();
    }

    protected B doBackward(A a) {
        throw new AssertionError();
    }

    A correctedDoForward(B b) {
        return this.original.correctedDoBackward(b);
    }

    B correctedDoBackward(A a) {
        return this.original.correctedDoForward(a);
    }

    public Converter<A, B> reverse() {
        return this.original;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Converter$ReverseConverter)) {
            return false;
        }
        return this.original.equals(((Converter$ReverseConverter) obj).original);
    }

    public int hashCode() {
        return this.original.hashCode() ^ -1;
    }

    public String toString() {
        String valueOf = String.valueOf(String.valueOf(this.original));
        return new StringBuilder(valueOf.length() + 10).append(valueOf).append(".reverse()").toString();
    }
}
