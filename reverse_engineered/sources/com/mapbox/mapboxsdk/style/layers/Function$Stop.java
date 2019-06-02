package com.mapbox.mapboxsdk.style.layers;

public class Function$Stop<I, O> {
    public final I in;
    public final O out;

    Function$Stop(I i, O o) {
        this.in = i;
        this.out = o;
    }

    Object[] toValueObject() {
        return new Object[]{this.in, this.out};
    }

    public String toString() {
        return String.format("[%s, %s]", new Object[]{this.in, this.out});
    }
}
